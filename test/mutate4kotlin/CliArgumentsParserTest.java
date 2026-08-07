package mutate4kotlin;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CliArgumentsParserTest {

    @Test
    void rejectsMissingFileArgument() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[0]));

        assertEquals("mutate4kotlin requires exactly one Kotlin file", error.getMessage());
    }

    @Test
    void parsesSingleExplicitFileArgument() {
        CliArguments parsed = CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt"});

        assertEquals(CliMode.EXPLICIT_FILES, parsed.mode());
        assertEquals(List.of("src/main/kotlin/demo/App.kt"), parsed.fileArgs());
        assertEquals(Set.of(), parsed.lines());
        assertEquals(false, parsed.scan());
        assertEquals(false, parsed.updateManifest());
        assertEquals(false, parsed.reuseCoverage());
        assertEquals(false, parsed.sinceLastRun());
        assertEquals(false, parsed.mutateAll());
        assertEquals(10, parsed.timeoutFactor());
        assertEquals(50, parsed.mutationWarning());
        assertEquals(Math.max(1, Runtime.getRuntime().availableProcessors() / 2), parsed.maxWorkers());
        assertEquals(null, parsed.testCommand());
        assertEquals(false, parsed.verbose());
    }

    @Test
    void parsesLineFilterAndTimeoutFactor() {
        CliArguments parsed = CliArgumentsParser.parse(new String[]{
                "src/main/kotlin/demo/App.kt", "--lines", "12,18", "--timeout-factor", "15"
        });

        assertEquals(CliMode.EXPLICIT_FILES, parsed.mode());
        assertEquals(Set.of(12, 18), parsed.lines());
        assertEquals(15, parsed.timeoutFactor());
    }

    @Test
    void parsesMaxWorkers() {
        CliArguments parsed = CliArgumentsParser.parse(new String[]{
                "src/main/kotlin/demo/App.kt", "--max-workers", "4"
        });

        assertEquals(4, parsed.maxWorkers());
    }

    @Test
    void parsesVerboseFlag() {
        CliArguments parsed = CliArgumentsParser.parse(new String[]{
                "src/main/kotlin/demo/App.kt", "--verbose"
        });

        assertEquals(true, parsed.verbose());
    }

    @Test
    void parsesScanFlag() {
        CliArguments parsed = CliArgumentsParser.parse(new String[]{
                "src/main/kotlin/demo/App.kt", "--scan"
        });

        assertEquals(true, parsed.scan());
    }

    @Test
    void parsesUpdateManifestFlag() {
        CliArguments parsed = CliArgumentsParser.parse(new String[]{
                "src/main/kotlin/demo/App.kt", "--update-manifest"
        });

        assertEquals(true, parsed.updateManifest());
    }

    @Test
    void parsesReuseCoverageFlag() {
        CliArguments parsed = CliArgumentsParser.parse(new String[]{
                "src/main/kotlin/demo/App.kt", "--reuse-coverage"
        });

        assertEquals(true, parsed.reuseCoverage());
    }

    @Test
    void parsesDifferentialFlagsAndTestCommand() {
        CliArguments parsed = CliArgumentsParser.parse(new String[]{
                "src/main/kotlin/demo/App.kt",
                "--since-last-run",
                "--mutation-warning", "75",
                "--test-command", "mvn test -DexcludeTags=no-mutate"
        });

        assertEquals(true, parsed.sinceLastRun());
        assertEquals(false, parsed.mutateAll());
        assertEquals(75, parsed.mutationWarning());
        assertEquals("mvn test -DexcludeTags=no-mutate", parsed.testCommand());
    }

    @Test
    void parsesMutateAllFlag() {
        CliArguments parsed = CliArgumentsParser.parse(new String[]{
                "src/main/kotlin/demo/App.kt", "--mutate-all"
        });

        assertEquals(true, parsed.mutateAll());
    }

    @Test
    void parsesHelpMode() {
        CliArguments parsed = CliArgumentsParser.parse(new String[]{"--help"});

        assertEquals(CliMode.HELP, parsed.mode());
    }

    @Test
    void rejectsMultipleFileArguments() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "src/main/kotlin/demo/Other.kt"}));

        assertEquals("mutate4kotlin accepts exactly one Kotlin file", error.getMessage());
    }

    @Test
    void rejectsLinesWithoutFileArgument() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"--lines", "12"}));

        assertEquals("mutate4kotlin requires exactly one Kotlin file", error.getMessage());
    }

    @Test
    void rejectsNonKotlinTarget() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"crap4java"}));

        assertEquals("mutate4kotlin target must be a .kt file", error.getMessage());
    }

    @Test
    void rejectsNonPositiveTimeoutFactor() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--timeout-factor", "0"}));

        assertEquals("--timeout-factor must be a positive integer", error.getMessage());
    }

    @Test
    void rejectsNonPositiveMaxWorkers() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--max-workers", "0"}));

        assertEquals("--max-workers must be a positive integer", error.getMessage());
    }

    @Test
    void rejectsUnknownOption() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--bogus"}));

        assertEquals("Unknown option: --bogus", error.getMessage());
    }

    @Test
    void rejectsLinesCombinedWithSinceLastRun() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--lines", "5", "--since-last-run"}));

        assertEquals("--lines may not be combined with --since-last-run", error.getMessage());
    }

    @Test
    void rejectsLinesCombinedWithMutateAll() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--lines", "5", "--mutate-all"}));

        assertEquals("--lines may not be combined with --mutate-all", error.getMessage());
    }

    @Test
    void rejectsSinceLastRunCombinedWithMutateAll() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--since-last-run", "--mutate-all"}));

        assertEquals("--since-last-run may not be combined with --mutate-all", error.getMessage());
    }

    @Test
    void rejectsScanCombinedWithSinceLastRun() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--scan", "--since-last-run"}));

        assertEquals("--scan may not be combined with --since-last-run", error.getMessage());
    }

    @Test
    void rejectsScanCombinedWithUpdateManifest() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--scan", "--update-manifest"}));

        assertEquals("--scan may not be combined with --update-manifest", error.getMessage());
    }

    @Test
    void rejectsScanCombinedWithReuseCoverage() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--scan", "--reuse-coverage"}));

        assertEquals("--scan may not be combined with --reuse-coverage", error.getMessage());
    }

    @Test
    void rejectsUpdateManifestCombinedWithSinceLastRun() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--update-manifest", "--since-last-run"}));

        assertEquals("--update-manifest may not be combined with --since-last-run", error.getMessage());
    }

    @Test
    void rejectsUpdateManifestCombinedWithMutateAll() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--update-manifest", "--mutate-all"}));

        assertEquals("--update-manifest may not be combined with --mutate-all", error.getMessage());
    }

    @Test
    void rejectsUpdateManifestCombinedWithReuseCoverage() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--update-manifest", "--reuse-coverage"}));

        assertEquals("--update-manifest may not be combined with --reuse-coverage", error.getMessage());
    }

    @Test
    void rejectsUpdateManifestCombinedWithLines() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--update-manifest", "--lines", "5"}));

        assertEquals("--update-manifest may not be combined with --lines", error.getMessage());
    }

    @Test
    void rejectsScanCombinedWithMutateAll() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--scan", "--mutate-all"}));

        assertEquals("--scan may not be combined with --mutate-all", error.getMessage());
    }

    @Test
    void rejectsMissingLinesValue() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--lines"}));

        assertEquals("--lines requires a value", error.getMessage());
    }

    @Test
    void rejectsBlankLinesValue() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--lines", ",,"}));

        assertEquals("--lines requires at least one line number", error.getMessage());
    }

    @Test
    void rejectsNonNumericLinesValue() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--lines", "a"}));

        assertEquals("--lines must be a positive integer", error.getMessage());
    }

    @Test
    void rejectsMissingTimeoutFactorValue() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--timeout-factor"}));

        assertEquals("--timeout-factor requires a value", error.getMessage());
    }

    @Test
    void rejectsMissingMaxWorkersValue() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--max-workers"}));

        assertEquals("--max-workers requires a value", error.getMessage());
    }

    @Test
    void rejectsBlankTestCommand() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> CliArgumentsParser.parse(new String[]{"src/main/kotlin/demo/App.kt", "--test-command", "   "}));

        assertEquals("--test-command must not be blank", error.getMessage());
    }
}
