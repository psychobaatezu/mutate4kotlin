package mutate4kotlin.engine;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.analysis.*;
import mutate4kotlin.cli.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;
import mutate4kotlin.selection.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import java.io.PrintStream;
import java.nio.file.Path;
import java.util.List;

public final class CliExecution {

    private final Path workspaceRoot;
    private final PrintStream out;
    private final TestCommandExecutor testExecutor;
    private final ProgressReporter verboseProgressReporter;
    private final MutationCatalog catalog;
    private final ReportFormatter formatter;
    private final ProjectLayout layout;
    private final BaselineRunner baselineRunner;
    private final MutationRunPlanner mutationRunPlanner;
    private final ScanMode scanMode;
    private final ExecutionOutcomeWriter outcomeWriter;
    private final ManifestWriter manifestWriter;

    public CliExecution(Path workspaceRoot,
                        PrintStream out,
                        PrintStream err,
                        TestCommandExecutor testExecutor,
                        CoverageRunner coverageRunner,
                        ProgressReporter verboseProgressReporter,
                        MutationCatalog catalog,
                        ReportFormatter formatter,
                        ManifestSupport manifestSupport,
                        ProjectLayout layout,
                        DifferentialSelector selector,
                        MutationCoverageFilter coverageFilter,
                        MutationExecution mutationExecution,
                        ScanReportFormatter scanReportFormatter) {
        this.workspaceRoot = workspaceRoot;
        this.out = out;
        this.testExecutor = testExecutor;
        this.verboseProgressReporter = verboseProgressReporter;
        this.catalog = catalog;
        this.formatter = formatter;
        this.layout = layout;
        this.baselineRunner = new BaselineRunner(coverageRunner, err);
        LineFilter lineFilter = new LineFilter();
        this.mutationRunPlanner = new MutationRunPlanner(selector, coverageFilter, mutationExecution,
                new ExecutionMessages(), lineFilter);
        this.scanMode = new ScanMode(selector, scanReportFormatter, lineFilter);
        this.manifestWriter = new ManifestWriter(manifestSupport);
        this.outcomeWriter = new ExecutionOutcomeWriter(workspaceRoot, out, formatter, manifestWriter);
    }

    public int execute(CliArguments parsed) throws Exception {
        ExecutionContext context = ExecutionContext.create(parsed, testExecutor, verboseProgressReporter, layout, catalog);
        if (parsed.scan()) {
            out.print(scanMode.render(parsed, context));
            return 0;
        }
        if (parsed.updateManifest()) {
            manifestWriter.write(context.sourceFile(), context.analysis());
            out.printf("Updated manifest for %s%n", workspaceRoot.relativize(context.sourceFile()));
            return 0;
        }

        CoverageRun coverageRun = baselineRunner.run(parsed, context.executor(), context.moduleRoot(), context.progressReporter());
        TestRun baseline = coverageRun.baseline();
        if (!baseline.passed()) {
            return baselineRunner.fail(baseline);
        }

        MutantResultSummary summary = mutationRunPlanner.run(parsed, context, baseline, coverageRun.report());
        return outcomeWriter.write(summary, context.analysis());
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=010c9391f80cdd667e6bc24a244836e22cbf546a1b37a18c660d0bbe54ea7350
scope.0.id=Y2xhc3M6Q2xpRXhlY3V0aW9uI0NsaUV4ZWN1dGlvbjoyNw
scope.0.kind=class
scope.0.startLine=27
scope.0.endLine=93
scope.0.semanticHash=6720b69e61036c581971234e574fbe1c232e26ebabe488a2d328ea1dd3d50681
scope.1.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI2Jhc2VsaW5lUnVubmVyOjM2
scope.1.kind=field
scope.1.startLine=36
scope.1.endLine=36
scope.1.semanticHash=6c17d4ff9190ba93e839f855d56a4c3fac47073e06b8adb556a005896fcd60c1
scope.2.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI2NhdGFsb2c6MzM
scope.2.kind=field
scope.2.startLine=33
scope.2.endLine=33
scope.2.semanticHash=44b8181c5873679c5f33e466da883ac0e6e863e4a1b5fef3a51b1cdee47cf346
scope.3.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI2Zvcm1hdHRlcjozNA
scope.3.kind=field
scope.3.startLine=34
scope.3.endLine=34
scope.3.semanticHash=80b22fb2f02647cdbf8821419bfe71dfb597135cddc85a2cfae2fa3af41dcf27
scope.4.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI2xheW91dDozNQ
scope.4.kind=field
scope.4.startLine=35
scope.4.endLine=35
scope.4.semanticHash=16ed98fa39282b5fde699cc6cd9ba90e34dced0e14519c4201e4d5401638a53e
scope.5.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI21hbmlmZXN0V3JpdGVyOjQw
scope.5.kind=field
scope.5.startLine=40
scope.5.endLine=40
scope.5.semanticHash=5b00c399104953594ae3dd7660010cb73288b3ed5908001920e56eb252cd6466
scope.6.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI211dGF0aW9uUnVuUGxhbm5lcjozNw
scope.6.kind=field
scope.6.startLine=37
scope.6.endLine=37
scope.6.semanticHash=37420526f4dc7a6cb1a3727ff6620393cb7050421824ca2e8c880038d12dae59
scope.7.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI291dDozMA
scope.7.kind=field
scope.7.startLine=30
scope.7.endLine=30
scope.7.semanticHash=b98df4fbf291f7cd01ba32f6b30c169fc64c08011a73e48a271561a4fcdd0a52
scope.8.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI291dGNvbWVXcml0ZXI6Mzk
scope.8.kind=field
scope.8.startLine=39
scope.8.endLine=39
scope.8.semanticHash=bb052bc74f81d2d8d8e4727ac2c37ab9ae2732af8adeee4ba70f34d5760e71c4
scope.9.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI3NjYW5Nb2RlOjM4
scope.9.kind=field
scope.9.startLine=38
scope.9.endLine=38
scope.9.semanticHash=77b3fcfc3658eb692e0738b777cca8d71ce480fb7be59bf42d5b3c639f017aed
scope.10.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI3Rlc3RFeGVjdXRvcjozMQ
scope.10.kind=field
scope.10.startLine=31
scope.10.endLine=31
scope.10.semanticHash=c8a5fb812b9449abf5a1c525101069b9e65812b7b7465472a0c3e085d92b044a
scope.11.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI3ZlcmJvc2VQcm9ncmVzc1JlcG9ydGVyOjMy
scope.11.kind=field
scope.11.startLine=32
scope.11.endLine=32
scope.11.semanticHash=8d78b47f26470fa033746e3f7b9b50a6adabdfd940846dbc59662a3639d9a01e
scope.12.id=ZmllbGQ6Q2xpRXhlY3V0aW9uI3dvcmtzcGFjZVJvb3Q6Mjk
scope.12.kind=field
scope.12.startLine=29
scope.12.endLine=29
scope.12.semanticHash=ea8f3cda099696b9fbb3052dac911c417ff5d1db470f4d0f8f5ec10ce687102c
scope.13.id=bWV0aG9kOkNsaUV4ZWN1dGlvbiNjdG9yKDE0KTo0Mg
scope.13.kind=method
scope.13.startLine=42
scope.13.endLine=70
scope.13.semanticHash=8d91f3f7a615c6f793b8a1632ec44423ed90e6aab621343c16a909a730e43805
scope.14.id=bWV0aG9kOkNsaUV4ZWN1dGlvbiNleGVjdXRlKDEpOjcy
scope.14.kind=method
scope.14.startLine=72
scope.14.endLine=92
scope.14.semanticHash=85d13dc4075810e53775ff6fe6839a51696b58a49cb7e4889cc7d84a0eaa60fb
*/
