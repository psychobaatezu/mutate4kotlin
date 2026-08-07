package mutate4kotlin.cli;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.engine.*;
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
import java.util.Set;

public final class CliApplication {

    private final ProjectLayout layout;
    private final CliExecution execution;
    private final CliRequestParser requestParser;

    public CliApplication(Path workspaceRoot, PrintStream out, PrintStream err, TestCommandExecutor executor) {
        this(workspaceRoot,
                out,
                err,
                executor,
                new CoverageRunner(new ProcessCommandExecutor()),
                new CopiedWorkspaceManager(),
                new PrintStreamProgressReporter(out));
    }

    public CliApplication(Path workspaceRoot,
                          PrintStream out,
                          PrintStream err,
                          TestCommandExecutor executor,
                          CoverageRunner coverageRunner) {
        this(workspaceRoot,
                out,
                err,
                executor,
                coverageRunner,
                new CopiedWorkspaceManager(),
                new PrintStreamProgressReporter(out));
    }

    public CliApplication(Path workspaceRoot,
                          PrintStream out,
                          PrintStream err,
                          TestCommandExecutor executor,
                          CoverageRunner coverageRunner,
                          WorkspaceManager workspaceManager) {
        this(workspaceRoot, out, err, executor, coverageRunner, workspaceManager, new PrintStreamProgressReporter(out));
    }

    public CliApplication(Path workspaceRoot,
                          PrintStream out,
                          PrintStream err,
                          TestCommandExecutor executor,
                          CoverageRunner coverageRunner,
                          WorkspaceManager workspaceManager,
                          ProgressReporter verboseProgressReporter) {
        this.layout = new ProjectLayout(workspaceRoot);
        this.requestParser = new CliRequestParser(out, err);
        this.execution = new CliExecutionFactory().create(
                workspaceRoot, out, err, executor, coverageRunner, workspaceManager, verboseProgressReporter, layout);
    }

    public int execute(String[] args) throws Exception {
        ParseOutcome parse = requestParser.parse(args);
        if (parse.exitCode() >= 0) {
            return parse.exitCode();
        }
        return execution.execute(parse.arguments());
    }

    public String sourceSuffix(Path moduleRoot, Path file) {
        return layout.sourceSuffix(moduleRoot, file);
    }

    public Path moduleRootFor(List<Path> files) {
        return layout.moduleRootFor(files);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=3c2fe913651b9eab225fd67b180f3d818d0db87c334f4b9295a0a04fe0637943
scope.0.id=Y2xhc3M6Q2xpQXBwbGljYXRpb24jQ2xpQXBwbGljYXRpb246Mjg
scope.0.kind=class
scope.0.startLine=28
scope.0.endLine=95
scope.0.semanticHash=85001550d62f4a4f99ebaa6392ecca3245886eb5d5663007696eca11027a652e
scope.1.id=ZmllbGQ6Q2xpQXBwbGljYXRpb24jZXhlY3V0aW9uOjMx
scope.1.kind=field
scope.1.startLine=31
scope.1.endLine=31
scope.1.semanticHash=0fac5c4abbf73c9b7109597fddecb570e00731fa74b8f3d2329dbd874c1b47c0
scope.2.id=ZmllbGQ6Q2xpQXBwbGljYXRpb24jbGF5b3V0OjMw
scope.2.kind=field
scope.2.startLine=30
scope.2.endLine=30
scope.2.semanticHash=16ed98fa39282b5fde699cc6cd9ba90e34dced0e14519c4201e4d5401638a53e
scope.3.id=ZmllbGQ6Q2xpQXBwbGljYXRpb24jcmVxdWVzdFBhcnNlcjozMg
scope.3.kind=field
scope.3.startLine=32
scope.3.endLine=32
scope.3.semanticHash=fb5c498538cef295c60d146d6983b630d1b4c9a5dd2724beb1d2971c48e98dfd
scope.4.id=bWV0aG9kOkNsaUFwcGxpY2F0aW9uI2N0b3IoNCk6MzQ
scope.4.kind=method
scope.4.startLine=34
scope.4.endLine=42
scope.4.semanticHash=35e6b39416f71ce799b052f864a862cf117f629c8abbd216fc9f8bedba64a734
scope.5.id=bWV0aG9kOkNsaUFwcGxpY2F0aW9uI2N0b3IoNSk6NDQ
scope.5.kind=method
scope.5.startLine=44
scope.5.endLine=56
scope.5.semanticHash=3d1a21b3ec22f56d8dcac207332c2e1ac7ec6c100aa1c84638fcbdf8c3f33eb4
scope.6.id=bWV0aG9kOkNsaUFwcGxpY2F0aW9uI2N0b3IoNik6NTg
scope.6.kind=method
scope.6.startLine=58
scope.6.endLine=65
scope.6.semanticHash=ba93ef44caaebb394ca66fe0703070b8b4335d760c5ba6b6461d19642324bb7e
scope.7.id=bWV0aG9kOkNsaUFwcGxpY2F0aW9uI2N0b3IoNyk6Njc
scope.7.kind=method
scope.7.startLine=67
scope.7.endLine=78
scope.7.semanticHash=14a24fec3f7ff9f0475922d83c977b785aaafd2510670ea4a897fe15789c5be6
scope.8.id=bWV0aG9kOkNsaUFwcGxpY2F0aW9uI2V4ZWN1dGUoMSk6ODA
scope.8.kind=method
scope.8.startLine=80
scope.8.endLine=86
scope.8.semanticHash=09cb66bcbfdb82d350d852b7c205eb51238c8a2c18dcf5e96b0bbcfcecda75b2
scope.9.id=bWV0aG9kOkNsaUFwcGxpY2F0aW9uI21vZHVsZVJvb3RGb3IoMSk6OTI
scope.9.kind=method
scope.9.startLine=92
scope.9.endLine=94
scope.9.semanticHash=d1b80c4945e54986c893e7215f2a1b9f10db8583bf58ebe12c31d0c37a633798
scope.10.id=bWV0aG9kOkNsaUFwcGxpY2F0aW9uI3NvdXJjZVN1ZmZpeCgyKTo4OA
scope.10.kind=method
scope.10.startLine=88
scope.10.endLine=90
scope.10.semanticHash=dda6a7a64e5fc5616e3280272b7c4506acda0d97510d85ea4d355a8983cc2632
*/
