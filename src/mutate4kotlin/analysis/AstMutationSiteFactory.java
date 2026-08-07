package mutate4kotlin.analysis;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.manifest.*;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.UnaryTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

import java.nio.file.Path;

final class AstMutationSiteFactory {

    private final Path file;
    private final String source;
    private final com.sun.source.tree.CompilationUnitTree unit;
    private final Trees trees;
    private final LineNumberTable lineNumbers;
    private final TreeTypePredicates typePredicates;
    private final AstScopeTracker scopeTracker;

    AstMutationSiteFactory(Path file,
                           String source,
                           com.sun.source.tree.CompilationUnitTree unit,
                           Trees trees,
                           LineNumberTable lineNumbers,
                           AstScopeTracker scopeTracker) {
        this.file = file;
        this.source = source;
        this.unit = unit;
        this.trees = trees;
        this.lineNumbers = lineNumbers;
        this.typePredicates = new TreeTypePredicates(trees);
        this.scopeTracker = scopeTracker;
    }

    MutationSite literal(TreePath path, LiteralTree node) {
        if (node.getValue() instanceof Boolean bool) {
            return literalSite(path, node, bool ? "true" : "false", bool ? "false" : "true");
        }
        if (node.getValue() instanceof Integer value && (value == 0 || value == 1)) {
            return literalSite(path, node, Integer.toString(value), value == 0 ? "1" : "0");
        }
        return null;
    }

    MutationSite binary(TreePath path, BinaryTree node) {
        BinaryMutationOperator operator = BinaryMutationOperator.forKind(node.getKind());
        if (operator == null || (operator.numericOnly() && !typePredicates.isNumeric(path))) {
            return null;
        }
        long leftEnd = trees.getSourcePositions().getEndPosition(unit, node.getLeftOperand());
        long rightStart = trees.getSourcePositions().getStartPosition(unit, node.getRightOperand());
        if (leftEnd < 0 || rightStart < 0 || leftEnd > rightStart || rightStart > source.length()) {
            return null;
        }
        String between = source.substring((int) leftEnd, (int) rightStart);
        int offset = between.indexOf(operator.original());
        if (offset < 0) {
            return null;
        }
        int start = (int) leftEnd + offset;
        return site(path, start, start + operator.original().length(), operator.original(), operator.replacement());
    }

    MutationSite unary(TreePath path, UnaryTree node) {
        return switch (node.getKind()) {
            case LOGICAL_COMPLEMENT -> removablePrefix(path, node, "!");
            case UNARY_MINUS -> typePredicates.isNumeric(path) ? removablePrefix(path, node, "-") : null;
            default -> null;
        };
    }

    MutationSite nullReplacement(TreePath path, ExpressionTree expression) {
        if (expression == null || !typePredicates.isReference(path, expression)) {
            return null;
        }
        int start = (int) trees.getSourcePositions().getStartPosition(unit, expression);
        int end = (int) trees.getSourcePositions().getEndPosition(unit, expression);
        if (start < 0 || end <= start) {
            return null;
        }
        String original = source.substring(start, end);
        return "null".equals(original) ? null : site(path, start, end, original, "null");
    }

    private MutationSite literalSite(TreePath path, LiteralTree node, String original, String replacement) {
        int start = (int) trees.getSourcePositions().getStartPosition(unit, node);
        return start < 0 ? null : site(path, start, start + original.length(), original, replacement);
    }

    private MutationSite removablePrefix(TreePath path, UnaryTree node, String operator) {
        int start = (int) trees.getSourcePositions().getStartPosition(unit, node);
        if (start < 0) {
            return null;
        }
        int operatorStart = lineNumbers.skipWhitespace(start);
        if (!source.startsWith(operator, operatorStart)) {
            return null;
        }
        return buildSite(path, operatorStart, operatorStart + operator.length(),
                operator, "", "replace " + operator + " with removed " + operator);
    }

    private MutationSite site(TreePath path, int start, int end, String original, String replacement) {
        return buildSite(path, start, end, original, replacement, "replace " + original + " with " + replacement);
    }

    private MutationSite buildSite(TreePath path, int start, int end, String original, String replacement, String description) {
        ScopeRef scope = scopeTracker.currentScope(path);
        return new MutationSite(file, lineNumbers.lineNumber(start), start, end, original, replacement,
                description, scope.id(), scope.kind(), scope.startLine(), scope.endLine());
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=d312e581a30674e535e0295a606f6bf067ca48c416a0a87cd559efde00096dba
scope.0.id=Y2xhc3M6QXN0TXV0YXRpb25TaXRlRmFjdG9yeSNBc3RNdXRhdGlvblNpdGVGYWN0b3J5OjIx
scope.0.kind=class
scope.0.startLine=21
scope.0.endLine=123
scope.0.semanticHash=9cd1e9a0bee8f260b51a5b85b9f0754d54fbf4652a41879f993a6930f6a5005a
scope.1.id=ZmllbGQ6QXN0TXV0YXRpb25TaXRlRmFjdG9yeSNmaWxlOjIz
scope.1.kind=field
scope.1.startLine=23
scope.1.endLine=23
scope.1.semanticHash=9042a4679166911f8012023a394a64d0f9c2541fc9520ae0147f655250891eb5
scope.2.id=ZmllbGQ6QXN0TXV0YXRpb25TaXRlRmFjdG9yeSNsaW5lTnVtYmVyczoyNw
scope.2.kind=field
scope.2.startLine=27
scope.2.endLine=27
scope.2.semanticHash=ef6ee9bcfe04f326ca652abe258253055be2a73bcc39bc022bee59dece653a7a
scope.3.id=ZmllbGQ6QXN0TXV0YXRpb25TaXRlRmFjdG9yeSNzY29wZVRyYWNrZXI6Mjk
scope.3.kind=field
scope.3.startLine=29
scope.3.endLine=29
scope.3.semanticHash=981eb87cc60b755b1fb8c9b850a4a47ae987134bc0e67790140b222354df09a8
scope.4.id=ZmllbGQ6QXN0TXV0YXRpb25TaXRlRmFjdG9yeSNzb3VyY2U6MjQ
scope.4.kind=field
scope.4.startLine=24
scope.4.endLine=24
scope.4.semanticHash=97d0b5d76eb96c0b49baebabd2d8302b3f93dd5e9b34c3bf876e0156e47dafb5
scope.5.id=ZmllbGQ6QXN0TXV0YXRpb25TaXRlRmFjdG9yeSN0cmVlczoyNg
scope.5.kind=field
scope.5.startLine=26
scope.5.endLine=26
scope.5.semanticHash=9d745a8b082cf229bd6c9d2f33c79006a3ea76b6e039d0e7fce733607b392d9e
scope.6.id=ZmllbGQ6QXN0TXV0YXRpb25TaXRlRmFjdG9yeSN0eXBlUHJlZGljYXRlczoyOA
scope.6.kind=field
scope.6.startLine=28
scope.6.endLine=28
scope.6.semanticHash=57e005aecdf125de7483d2fa0ccc10bcdc331d4d9bf833c24c785d6171af44fc
scope.7.id=ZmllbGQ6QXN0TXV0YXRpb25TaXRlRmFjdG9yeSN1bml0OjI1
scope.7.kind=field
scope.7.startLine=25
scope.7.endLine=25
scope.7.semanticHash=ac4af8e7d584e0642703e6338e5dc722bdacc1c2fe084a79d4ab19090bf87ee8
scope.8.id=bWV0aG9kOkFzdE11dGF0aW9uU2l0ZUZhY3RvcnkjYmluYXJ5KDIpOjU2
scope.8.kind=method
scope.8.startLine=56
scope.8.endLine=73
scope.8.semanticHash=a327866549d050400a91f235fa035f329252f4fc1cccab68cfbc9c286d4e90e2
scope.9.id=bWV0aG9kOkFzdE11dGF0aW9uU2l0ZUZhY3RvcnkjYnVpbGRTaXRlKDYpOjExOA
scope.9.kind=method
scope.9.startLine=118
scope.9.endLine=122
scope.9.semanticHash=781321b65af7106fcef098cbb3564864151594c5eb3fe82721c87a25a089bdfc
scope.10.id=bWV0aG9kOkFzdE11dGF0aW9uU2l0ZUZhY3RvcnkjY3Rvcig2KTozMQ
scope.10.kind=method
scope.10.startLine=31
scope.10.endLine=44
scope.10.semanticHash=ff84afe5e83cafbb96d135d25c33e6c7c8d2db6dcfd9432cfe1cab36cf435560
scope.11.id=bWV0aG9kOkFzdE11dGF0aW9uU2l0ZUZhY3RvcnkjbGl0ZXJhbCgyKTo0Ng
scope.11.kind=method
scope.11.startLine=46
scope.11.endLine=54
scope.11.semanticHash=f5284fd6cfe7d16eb864c9fb6bf84630b8d6bd1504084d461e33a8f72fac8e8c
scope.12.id=bWV0aG9kOkFzdE11dGF0aW9uU2l0ZUZhY3RvcnkjbGl0ZXJhbFNpdGUoNCk6OTY
scope.12.kind=method
scope.12.startLine=96
scope.12.endLine=99
scope.12.semanticHash=4424499bbbfdeffcd6942d79cfa52489777a7efc540f5e595000b0e270deba83
scope.13.id=bWV0aG9kOkFzdE11dGF0aW9uU2l0ZUZhY3RvcnkjbnVsbFJlcGxhY2VtZW50KDIpOjgz
scope.13.kind=method
scope.13.startLine=83
scope.13.endLine=94
scope.13.semanticHash=f6d1f6fc2892421e93c24be95fe79a04a44e1e6dcc99aabde64c2fb415f733d4
scope.14.id=bWV0aG9kOkFzdE11dGF0aW9uU2l0ZUZhY3RvcnkjcmVtb3ZhYmxlUHJlZml4KDMpOjEwMQ
scope.14.kind=method
scope.14.startLine=101
scope.14.endLine=112
scope.14.semanticHash=e053e264569f28cff925a87fa9f0a7b5762e73abddf06b8fadffa72f3717de6e
scope.15.id=bWV0aG9kOkFzdE11dGF0aW9uU2l0ZUZhY3Rvcnkjc2l0ZSg1KToxMTQ
scope.15.kind=method
scope.15.startLine=114
scope.15.endLine=116
scope.15.semanticHash=211dd4d91da444d9f8e9e9208ac054e752b4c04fbf77e9e79a3f27045c6a6d2c
scope.16.id=bWV0aG9kOkFzdE11dGF0aW9uU2l0ZUZhY3RvcnkjdW5hcnkoMik6NzU
scope.16.kind=method
scope.16.startLine=75
scope.16.endLine=81
scope.16.semanticHash=421e6e552d83890f1b4e2027d58ea20023be1d30e1713a752f60658b7e718051
*/
