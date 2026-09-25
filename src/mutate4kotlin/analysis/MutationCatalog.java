package mutate4kotlin.analysis;

import mutate4kotlin.manifest.ManifestSupport;
import mutate4kotlin.model.MutationScope;
import mutate4kotlin.model.MutationSite;
import mutate4kotlin.model.SourceAnalysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MutationCatalog {

    private static final Pattern CLASS_PATTERN = Pattern.compile("\\b(class|object|interface)\\s+([A-Za-z_][A-Za-z0-9_]*)");
    private static final Pattern FUNCTION_PATTERN = Pattern.compile("\\bfun\\s+([A-Za-z_][A-Za-z0-9_]*)\\s*\\(");
    private static final Pattern IMPORT_STAR_PATTERN = Pattern.compile("^\\s*import\\s+[^\\n]+\\*\\s*$", Pattern.MULTILINE);
    private final ManifestSupport manifestSupport = new ManifestSupport();

    public List<MutationSite> discover(List<Path> files) throws IOException {
        List<MutationSite> sites = new ArrayList<>();
        for (Path file : files) {
            try {
                sites.addAll(analyze(file).sites());
            } catch (IOException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new IOException("Failed analyzing mutations for " + file, ex);
            }
        }
        sites.sort(Comparator.comparing(MutationSite::file).thenComparingInt(MutationSite::start));
        return sites;
    }

    public SourceAnalysis analyze(Path file) throws Exception {
        String raw = Files.readString(file);
        String source = manifestSupport.stripManifest(raw);
        String sanitized = sanitizeForScan(source);
        LineNumberTable lineNumberTable = new LineNumberTable(source);
        List<MutationScope> scopes = discoverScopes(sanitized, source, lineNumberTable);
        List<MutationSite> sites = discoverSites(file, source, sanitized, lineNumberTable, scopes);
        return new SourceAnalysis(source, sites, scopes, manifestSupport.hashScopes(scopes));
    }

    private List<MutationScope> discoverScopes(String sanitized, String source, LineNumberTable lineNumberTable) {
        List<ScopeCandidate> candidates = new ArrayList<>();
        discoverClassScopes(sanitized, source, lineNumberTable, candidates);
        discoverFunctionScopes(sanitized, source, lineNumberTable, candidates);
        candidates.sort(Comparator.comparingInt(ScopeCandidate::start));
        List<MutationScope> scopes = new ArrayList<>();
        for (ScopeCandidate candidate : candidates) {
            scopes.add(new MutationScope(
                    candidate.id(),
                    candidate.kind(),
                    candidate.startLine(),
                    candidate.endLine(),
                    manifestSupport.hash(source.substring(candidate.start(), candidate.end()))
            ));
        }
        return scopes;
    }

    private void discoverClassScopes(String sanitized, String source, LineNumberTable lineNumberTable, List<ScopeCandidate> candidates) {
        Matcher matcher = CLASS_PATTERN.matcher(sanitized);
        while (matcher.find()) {
            int braceIndex = sanitized.indexOf('{', matcher.end());
            if (braceIndex < 0) {
                continue;
            }
            int endBrace = findMatchingBrace(sanitized, braceIndex);
            if (endBrace < 0) {
                continue;
            }
            int start = matcher.start();
            int end = endBrace + 1;
            int startLine = lineNumberTable.lineNumber(start);
            int endLine = lineNumberTable.lineNumber(endBrace);
            String kind = matcher.group(1);
            String name = matcher.group(2);
            candidates.add(new ScopeCandidate(
                    "class:" + name + ":" + startLine,
                    kind,
                    start,
                    end,
                    startLine,
                    endLine
            ));
        }
    }

    private void discoverFunctionScopes(String sanitized, String source, LineNumberTable lineNumberTable, List<ScopeCandidate> candidates) {
        Matcher matcher = FUNCTION_PATTERN.matcher(sanitized);
        while (matcher.find()) {
            String name = matcher.group(1);
            int functionStart = matcher.start();
            int openParenthesis = matcher.end() - 1;
            int closeParenthesis = findMatchingPair(sanitized, openParenthesis, '(', ')');
            if (closeParenthesis < 0) {
                continue;
            }
            int bodyStart = findFunctionBodyStart(sanitized, closeParenthesis + 1);
            if (bodyStart < 0) {
                continue;
            }
            int bodyEnd;
            if (sanitized.charAt(bodyStart) == '=') {
                bodyEnd = findExpressionEnd(sanitized, bodyStart + 1);
            } else {
                bodyEnd = findMatchingBrace(sanitized, bodyStart);
            }
            if (bodyEnd < 0) {
                continue;
            }
            int endExclusive = Math.min(source.length(), bodyEnd + 1);
            int startLine = lineNumberTable.lineNumber(functionStart);
            int endLine = lineNumberTable.lineNumber(Math.max(functionStart, bodyEnd));
            candidates.add(new ScopeCandidate(
                    "function:" + name + ":" + startLine,
                    "function",
                    functionStart,
                    endExclusive,
                    startLine,
                    endLine
            ));
        }
    }

    private List<MutationSite> discoverSites(Path file,
                                             String source,
                                             String sanitized,
                                             LineNumberTable lineNumberTable,
                                             List<MutationScope> scopes) {
        List<MutationSite> sites = new ArrayList<>();
        List<IntRange> ignoredRanges = ignoredRanges(sanitized);
        addLiteralSites(file, source, sanitized, lineNumberTable, scopes, sites);
        addBinarySites(file, source, sanitized, lineNumberTable, scopes, sites, ignoredRanges);
        addUnarySites(file, source, sanitized, lineNumberTable, scopes, sites);
        addNullReplacementSites(file, source, sanitized, lineNumberTable, scopes, sites);
        sites.sort(Comparator.comparing(MutationSite::file).thenComparingInt(MutationSite::start));
        return sites;
    }

    private void addLiteralSites(Path file,
                                 String source,
                                 String sanitized,
                                 LineNumberTable lineNumberTable,
                                 List<MutationScope> scopes,
                                 List<MutationSite> sites) {
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites,
                Pattern.compile("(?<![A-Za-z0-9_])true(?![A-Za-z0-9_])"), "true", "false");
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites,
                Pattern.compile("(?<![A-Za-z0-9_])false(?![A-Za-z0-9_])"), "false", "true");
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites,
                Pattern.compile("(?<![A-Za-z0-9_])0(?![A-Za-z0-9_])"), "0", "1");
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites,
                Pattern.compile("(?<![A-Za-z0-9_])1(?![A-Za-z0-9_])"), "1", "0");
    }

    private void addBinarySites(Path file,
                                String source,
                                String sanitized,
                                LineNumberTable lineNumberTable,
                                List<MutationScope> scopes,
                                List<MutationSite> sites,
                                List<IntRange> ignoredRanges) {
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("!=="), "!==", "===", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("==="), "===", "!==", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("&&"), "&&", "||", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("\\|\\|"), "||", "&&", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("=="), "==", "!=", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("!="), "!=", "==", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("<="), "<=", "<", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile(">="), ">=", ">", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("(?<!<)<(?![<=])"), "<", "<=", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("(?<![>=-])>(?![>=])"), ">", ">=", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("(?<![+])\\+(?![+=])"), "+", "-", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("(?<![-])-(?![-=>])"), "-", "+", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("\\*"), "*", "/", ignoredRanges);
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, Pattern.compile("/"), "/", "*", ignoredRanges);
    }

    private void addUnarySites(Path file,
                               String source,
                               String sanitized,
                               LineNumberTable lineNumberTable,
                               List<MutationScope> scopes,
                               List<MutationSite> sites) {
        addUnaryRemoval(file, source, sanitized, lineNumberTable, scopes, sites,
                Pattern.compile("!(?!=)"), "!");
        addUnaryRemoval(file, source, sanitized, lineNumberTable, scopes, sites,
                Pattern.compile("(?<![A-Za-z0-9_\\)])-(?=[A-Za-z0-9_(])"), "-");
    }

    private void addNullReplacementSites(Path file,
                                         String source,
                                         String sanitized,
                                         LineNumberTable lineNumberTable,
                                         List<MutationScope> scopes,
                                         List<MutationSite> sites) {
        Pattern returnExpression = Pattern.compile("\\breturn\\s+([^;\\n]+)");
        Matcher matcher = returnExpression.matcher(sanitized);
        while (matcher.find()) {
            int expressionStart = matcher.start(1);
            int expressionEnd = matcher.end(1);
            String expression = source.substring(expressionStart, expressionEnd).trim();
            if (shouldSkipNullReplacementExpression(expression)) {
                continue;
            }
            int leading = expressionStart;
            while (leading < expressionEnd && Character.isWhitespace(source.charAt(leading))) {
                leading++;
            }
            int trailing = expressionEnd;
            while (trailing > leading && Character.isWhitespace(source.charAt(trailing - 1))) {
                trailing--;
            }
            String candidate = source.substring(leading, trailing);
            if (shouldSkipNullReplacementExpression(candidate.trim())) {
                continue;
            }
            sites.add(buildSite(file, lineNumberTable, scopes, leading, trailing, candidate, "null"));
        }
    }

    private boolean shouldSkipNullReplacementExpression(String expression) {
        if (expression.isBlank() || "null".equals(expression) || "}".equals(expression)) {
            return true;
        }
        if ("true".equals(expression) || "false".equals(expression)) {
            return true;
        }
        if (expression.matches("[-+]?\\d+(\\.\\d+)?")) {
            return true;
        }
        if (expression.contains("==") || expression.contains("!=")
                || expression.contains("<=") || expression.contains(">=")
                || expression.contains("&&") || expression.contains("||")
                || expression.contains(" + ") || expression.contains(" - ")
                || expression.contains(" * ") || expression.contains(" / ")) {
            return true;
        }
        if (expression.startsWith("!") || expression.startsWith("-")) {
            return true;
        }
        return false;
    }

    private void addTokenReplacement(Path file,
                                     String source,
                                     String sanitized,
                                     LineNumberTable lineNumberTable,
                                     List<MutationScope> scopes,
                                     List<MutationSite> sites,
                                     Pattern pattern,
                                     String original,
                                     String replacement) {
        addTokenReplacement(file, source, sanitized, lineNumberTable, scopes, sites, pattern, original, replacement, List.of());
    }

    private void addTokenReplacement(Path file,
                                     String source,
                                     String sanitized,
                                     LineNumberTable lineNumberTable,
                                     List<MutationScope> scopes,
                                     List<MutationSite> sites,
                                     Pattern pattern,
                                     String original,
                                     String replacement,
                                     List<IntRange> ignoredRanges) {
        Matcher matcher = pattern.matcher(sanitized);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (isInsideIgnoredRange(start, ignoredRanges)) {
                continue;
            }
            if (!source.regionMatches(start, original, 0, original.length())) {
                continue;
            }
            sites.add(buildSite(file, lineNumberTable, scopes, start, end, original, replacement));
        }
    }

    private List<IntRange> ignoredRanges(String sanitized) {
        List<IntRange> ranges = new ArrayList<>();
        Matcher matcher = IMPORT_STAR_PATTERN.matcher(sanitized);
        while (matcher.find()) {
            ranges.add(new IntRange(matcher.start(), matcher.end()));
        }
        addDeclarationGenericRanges(sanitized, ranges);
        addGenericTypeRanges(sanitized, ranges);
        return ranges;
    }

    private void addDeclarationGenericRanges(String sanitized, List<IntRange> ranges) {
        Matcher declaration = Pattern.compile("(?m)^[^\\n]*(?:class|interface|object)\\b").matcher(sanitized);
        while (declaration.find()) {
            int end = declarationEnd(sanitized, declaration.end());
            String declarationText = sanitized.substring(declaration.start(), end);
            Matcher generic = Pattern.compile("<(?:[A-Za-z0-9_.,? :<>\\[\\]]+)>").matcher(declarationText);
            while (generic.find()) {
                ranges.add(new IntRange(declaration.start() + generic.start(), declaration.start() + generic.end()));
            }
        }
    }

    private int declarationEnd(String text, int start) {
        int end = text.indexOf('{', start);
        int blankLine = text.indexOf("\n\n", start);
        if (end < 0) {
            return blankLine < 0 ? text.length() : blankLine;
        }
        return blankLine >= 0 && blankLine < end ? blankLine : end;
    }

    private void addGenericTypeRanges(String sanitized, List<IntRange> ranges) {
        for (int start = 0; start < sanitized.length(); start++) {
            if (sanitized.charAt(start) != '<' || start == 0) {
                continue;
            }
            int end = findGenericTypeEnd(sanitized, start);
            if (end < 0) {
                continue;
            }
            String content = sanitized.substring(start + 1, end);
            if (!content.isBlank() && content.matches("[A-Za-z0-9_.,? :<>\\[\\]]+")) {
                ranges.add(new IntRange(start, end + 1));
                start = end;
            }
        }
    }

    private int findGenericTypeEnd(String text, int start) {
        int depth = 0;
        for (int index = start; index < text.length(); index++) {
            char character = text.charAt(index);
            if (character == '\n' || character == ';' || character == '{') {
                return -1;
            }
            if (character == '<') {
                depth++;
            } else if (character == '>') {
                depth--;
                if (depth == 0) {
                    int next = index + 1;
                    while (next < text.length() && Character.isWhitespace(text.charAt(next))) {
                        next++;
                    }
                    return next < text.length() && ")(:,.=!?[]{".indexOf(text.charAt(next)) >= 0
                            ? index
                            : -1;
                }
            }
        }
        return -1;
    }

    private boolean isInsideIgnoredRange(int offset, List<IntRange> ranges) {
        for (IntRange range : ranges) {
            if (offset >= range.start() && offset < range.end()) {
                return true;
            }
        }
        return false;
    }

    private void addUnaryRemoval(Path file,
                                 String source,
                                 String sanitized,
                                 LineNumberTable lineNumberTable,
                                 List<MutationScope> scopes,
                                 List<MutationSite> sites,
                                 Pattern pattern,
                                 String operator) {
        Matcher matcher = pattern.matcher(sanitized);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (!source.regionMatches(start, operator, 0, operator.length())) {
                continue;
            }
            sites.add(buildSite(file, lineNumberTable, scopes, start, end, operator, "", "replace " + operator + " with removed " + operator));
        }
    }

    private MutationSite buildSite(Path file,
                                   LineNumberTable lineNumberTable,
                                   List<MutationScope> scopes,
                                   int start,
                                   int end,
                                   String original,
                                   String replacement) {
        return buildSite(file, lineNumberTable, scopes, start, end, original, replacement,
                "replace " + original + " with " + replacement);
    }

    private MutationSite buildSite(Path file,
                                   LineNumberTable lineNumberTable,
                                   List<MutationScope> scopes,
                                   int start,
                                   int end,
                                   String original,
                                   String replacement,
                                   String description) {
        MutationScope scope = scopeFor(start, lineNumberTable, scopes);
        return new MutationSite(
                file,
                lineNumberTable.lineNumber(start),
                start,
                end,
                original,
                replacement,
                description,
                scope.id(),
                scope.kind(),
                scope.startLine(),
                scope.endLine()
        );
    }

    private MutationScope scopeFor(int offset, LineNumberTable lineNumberTable, List<MutationScope> scopes) {
        int line = lineNumberTable.lineNumber(offset);
        MutationScope selected = null;
        for (MutationScope scope : scopes) {
            if (line < scope.startLine() || line > scope.endLine()) {
                continue;
            }
            if (selected == null || scope.endLine() - scope.startLine() < selected.endLine() - selected.startLine()) {
                selected = scope;
            }
        }
        if (selected != null) {
            return selected;
        }
        return new MutationScope("scope:" + line, "unknown", line, line, manifestSupport.hash("scope:" + line));
    }

    private int findMatchingBrace(String text, int openBraceIndex) {
        return findMatchingPair(text, openBraceIndex, '{', '}');
    }

    private int findMatchingPair(String text, int openIndex, char openChar, char closeChar) {
        if (openIndex < 0 || openIndex >= text.length() || text.charAt(openIndex) != openChar) {
            return -1;
        }
        int depth = 0;
        for (int index = openIndex; index < text.length(); index++) {
            char character = text.charAt(index);
            if (character == openChar) {
                depth++;
            } else if (character == closeChar) {
                depth--;
                if (depth == 0) {
                    return index;
                }
            }
        }
        return -1;
    }

    private int findFunctionBodyStart(String text, int fromIndex) {
        for (int index = fromIndex; index < text.length(); index++) {
            char character = text.charAt(index);
            if (character == '{' || character == '=') {
                return index;
            }
            if (character == ';') {
                return -1;
            }
        }
        return -1;
    }

    private int findExpressionEnd(String text, int fromIndex) {
        int roundDepth = 0;
        int squareDepth = 0;
        int curlyDepth = 0;
        for (int index = fromIndex; index < text.length(); index++) {
            char character = text.charAt(index);
            if (character == '(') {
                roundDepth++;
            } else if (character == ')') {
                roundDepth--;
            } else if (character == '[') {
                squareDepth++;
            } else if (character == ']') {
                squareDepth--;
            } else if (character == '{') {
                curlyDepth++;
            } else if (character == '}') {
                if (curlyDepth == 0 && roundDepth == 0 && squareDepth == 0) {
                    return index - 1;
                }
                curlyDepth--;
            }
            if ((character == '\n' || character == ';') && roundDepth == 0 && squareDepth == 0 && curlyDepth == 0) {
                return index - 1;
            }
        }
        return text.length() - 1;
    }

    private String sanitizeForScan(String source) {
        StringBuilder builder = new StringBuilder(source.length());
        boolean inLineComment = false;
        boolean inBlockComment = false;
        boolean inDoubleQuote = false;
        boolean inSingleQuote = false;
        boolean inTripleDoubleQuote = false;
        boolean escape = false;
        for (int index = 0; index < source.length(); index++) {
            char current = source.charAt(index);
            char next = index + 1 < source.length() ? source.charAt(index + 1) : '\0';
            char nextTwo = index + 2 < source.length() ? source.charAt(index + 2) : '\0';
            if (inLineComment) {
                if (current == '\n') {
                    inLineComment = false;
                    builder.append('\n');
                } else {
                    builder.append(' ');
                }
                continue;
            }
            if (inBlockComment) {
                if (current == '*' && next == '/') {
                    inBlockComment = false;
                    builder.append(' ');
                    builder.append(' ');
                    index++;
                } else {
                    builder.append(current == '\n' ? '\n' : ' ');
                }
                continue;
            }
            if (inTripleDoubleQuote) {
                if (current == '"' && next == '"' && nextTwo == '"') {
                    inTripleDoubleQuote = false;
                    builder.append(' ');
                    builder.append(' ');
                    builder.append(' ');
                    index += 2;
                } else {
                    builder.append(current == '\n' ? '\n' : ' ');
                }
                continue;
            }
            if (inDoubleQuote) {
                if (!escape && current == '"') {
                    inDoubleQuote = false;
                }
                escape = !escape && current == '\\';
                builder.append(current == '\n' ? '\n' : ' ');
                continue;
            }
            if (inSingleQuote) {
                if (!escape && current == '\'') {
                    inSingleQuote = false;
                }
                escape = !escape && current == '\\';
                builder.append(current == '\n' ? '\n' : ' ');
                continue;
            }
            if (current == '/' && next == '/') {
                inLineComment = true;
                builder.append(' ');
                builder.append(' ');
                index++;
                continue;
            }
            if (current == '/' && next == '*') {
                inBlockComment = true;
                builder.append(' ');
                builder.append(' ');
                index++;
                continue;
            }
            if (current == '"' && next == '"' && nextTwo == '"') {
                inTripleDoubleQuote = true;
                builder.append(' ');
                builder.append(' ');
                builder.append(' ');
                index += 2;
                continue;
            }
            if (current == '"') {
                inDoubleQuote = true;
                escape = false;
                builder.append(' ');
                continue;
            }
            if (current == '\'') {
                inSingleQuote = true;
                escape = false;
                builder.append(' ');
                continue;
            }
            builder.append(current);
        }
        return builder.toString();
    }

    private record ScopeCandidate(String id, String kind, int start, int end, int startLine, int endLine) {
    }

    private record IntRange(int start, int end) {
    }
}
