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

final class BaselineRunner {

    private final CoverageRunner coverageRunner;
    private final PrintStream err;

    BaselineRunner(CoverageRunner coverageRunner, PrintStream err) {
        this.coverageRunner = coverageRunner;
        this.err = err;
    }

    CoverageRun run(CliArguments parsed,
                    TestCommandExecutor executor,
                    Path moduleRoot,
                    ProgressReporter progressReporter) throws Exception {
        progressReporter.baselineStarting(moduleRoot);
        CoverageRun coverageRun;
        if (parsed.testCommand() == null) {
            if (parsed.reuseCoverage()) {
                TestRun baseline = executor.runTests(moduleRoot, 0L);
                CoverageRun reusedCoverage = coverageRunner.generateCoverage(moduleRoot, true);
                coverageRun = new CoverageRun(baseline, reusedCoverage.report(), true, reusedCoverage.reportAvailable());
                printReuseMessage(moduleRoot, coverageRun.reportAvailable());
            } else {
                coverageRun = coverageRunner.generateCoverage(moduleRoot, false);
            }
        } else {
            coverageRun = new CoverageRun(executor.runTests(moduleRoot, 0L), CoverageReport.allCovered(), false, false);
        }
        progressReporter.baselineFinished(coverageRun.baseline());
        return coverageRun;
    }

    int fail(TestRun baseline) {
        if (baseline.timedOut()) {
            err.println("Baseline tests timed out.");
        }
        err.println("Baseline tests failed.");
        err.print(baseline.output());
        return 2;
    }

    private void printReuseMessage(Path moduleRoot, boolean reportAvailable) {
        Path reportPath = moduleRoot.resolve("target/site/jacoco/jacoco.xml");
        if (reportAvailable) {
            err.printf("Reusing existing coverage data from %s.%n", reportPath);
            err.println("Warning: coverage may be stale; covered/uncovered site classification may be inaccurate.");
            return;
        }
        err.printf("Coverage reuse requested, but %s does not exist. Continuing without coverage filtering.%n", reportPath);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=988df9439192b7b4fa1c4394293442636c056b4b5fb88e06ecbd9fda55ac565e
scope.0.id=Y2xhc3M6QmFzZWxpbmVSdW5uZXIjQmFzZWxpbmVSdW5uZXI6MjY
scope.0.kind=class
scope.0.startLine=26
scope.0.endLine=76
scope.0.semanticHash=19a4dc801cb10bc411c2c5a2df666a7196fc39d64028c193351c0727cb2503b5
scope.1.id=ZmllbGQ6QmFzZWxpbmVSdW5uZXIjY292ZXJhZ2VSdW5uZXI6Mjg
scope.1.kind=field
scope.1.startLine=28
scope.1.endLine=28
scope.1.semanticHash=d92a1ba1476f655cf1babf2fbbc9b36f71fd57e400859cf09e46a1253a04c184
scope.2.id=ZmllbGQ6QmFzZWxpbmVSdW5uZXIjZXJyOjI5
scope.2.kind=field
scope.2.startLine=29
scope.2.endLine=29
scope.2.semanticHash=0f12a462a677e93faaa05787bc46db2cace278490b3a801f721c167aedea712a
scope.3.id=bWV0aG9kOkJhc2VsaW5lUnVubmVyI2N0b3IoMik6MzE
scope.3.kind=method
scope.3.startLine=31
scope.3.endLine=34
scope.3.semanticHash=d5d3d8a68c0409d2e78d7ce37a218417246e1f76196560fea8c506e86a988d3e
scope.4.id=bWV0aG9kOkJhc2VsaW5lUnVubmVyI2ZhaWwoMSk6NTg
scope.4.kind=method
scope.4.startLine=58
scope.4.endLine=65
scope.4.semanticHash=851c6d4cbbfcb15aa3682241c19bdad48d5f04d12e0bfe6a72f8c881d586f686
scope.5.id=bWV0aG9kOkJhc2VsaW5lUnVubmVyI3ByaW50UmV1c2VNZXNzYWdlKDIpOjY3
scope.5.kind=method
scope.5.startLine=67
scope.5.endLine=75
scope.5.semanticHash=f8c2d5278c4fb179cd759eb7b925b5da5528b508ac79ed1603b093da428fe0f0
scope.6.id=bWV0aG9kOkJhc2VsaW5lUnVubmVyI3J1big0KTozNg
scope.6.kind=method
scope.6.startLine=36
scope.6.endLine=56
scope.6.semanticHash=723e11ae21659608bd42294255d88720bee59eda553c5647e7cb7fa0d743a244
*/
