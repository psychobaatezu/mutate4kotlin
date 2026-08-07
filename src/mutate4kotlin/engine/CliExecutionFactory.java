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

public final class CliExecutionFactory {

    public CliExecution create(Path workspaceRoot,
                               PrintStream out,
                               PrintStream err,
                               TestCommandExecutor executor,
                               CoverageRunner coverageRunner,
                               WorkspaceManager workspaceManager,
                               ProgressReporter verboseProgressReporter,
                               ProjectLayout layout) {
        ManifestSupport manifestSupport = new ManifestSupport();
        return new CliExecution(
                workspaceRoot,
                out,
                err,
                executor,
                coverageRunner,
                verboseProgressReporter,
                new MutationCatalog(),
                new ReportFormatter(),
                manifestSupport,
                layout,
                new DifferentialSelector(manifestSupport),
                new MutationCoverageFilter(layout),
                new MutationExecution(workspaceManager),
                new ScanReportFormatter(workspaceRoot)
        );
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=a684ab4c53e06fcf788cb560fbfe1515d976502ceec79ef920aefbda26560988
scope.0.id=Y2xhc3M6Q2xpRXhlY3V0aW9uRmFjdG9yeSNDbGlFeGVjdXRpb25GYWN0b3J5OjI2
scope.0.kind=class
scope.0.startLine=26
scope.0.endLine=54
scope.0.semanticHash=5391b843791cd472f60a6cae18d420def2a765ecdf0edcc5b7268817fab0c121
scope.1.id=bWV0aG9kOkNsaUV4ZWN1dGlvbkZhY3RvcnkjY3JlYXRlKDgpOjI4
scope.1.kind=method
scope.1.startLine=28
scope.1.endLine=53
scope.1.semanticHash=bb0d16eed00805386f17c84230fcb997b89d0838eee96075625ac51e40d38de6
scope.2.id=bWV0aG9kOkNsaUV4ZWN1dGlvbkZhY3RvcnkjY3RvcigwKToyNg
scope.2.kind=method
scope.2.startLine=1
scope.2.endLine=54
scope.2.semanticHash=d739422561aeddc06958c935783996e713f7125fb838151dfb63c3dbd46a230d
*/
