package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public final class ProcessTestCommandExecutor implements TestCommandExecutor {

    private static final List<String> DEFAULT_COMMAND = List.of("mvn", "test", "-DexcludeTags=no-mutate");

    private final ProcessLauncher launcher;
    private final String commandText;
    public ProcessTestCommandExecutor() {
        this(DEFAULT_COMMAND);
    }
    public ProcessTestCommandExecutor(List<String> command) {
        this(projectRoot -> ProcessTestCommandFactory.startProcess(projectRoot, command), null);
    }
    public ProcessTestCommandExecutor(String commandText) {
        this(projectRoot -> ProcessTestCommandFactory.startShellProcess(projectRoot, commandText), commandText);
    }
    public ProcessTestCommandExecutor(ProcessLauncher launcher) {
        this(launcher, null);
    }

    private ProcessTestCommandExecutor(ProcessLauncher launcher, String commandText) {
        this.launcher = launcher;
        this.commandText = commandText;
    }

    @Override
    public TestRun runTests(Path projectRoot, long timeoutMillis) throws IOException, InterruptedException {
        long start = System.nanoTime();
        Process process = launcher.start(projectRoot);
        return TimedProcessRun.finish(process, timeoutMillis, start);
    }

    public interface ProcessLauncher {
        Process start(Path projectRoot) throws IOException;
    }

    @Override
    public TestCommandExecutor withCommand(String command) {
        return new ProcessTestCommandExecutor(command);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=9fb47979aaf5272965ff1cb88f85dbc5bdc59ba818d7dbde85d65f4af06b688b
scope.0.id=Y2xhc3M6UHJvY2Vzc1Rlc3RDb21tYW5kRXhlY3V0b3IjUHJvY2Vzc1Rlc3RDb21tYW5kRXhlY3V0b3I6MTQ
scope.0.kind=class
scope.0.startLine=14
scope.0.endLine=53
scope.0.semanticHash=2e827d9aa5522c06c547b8ac13c4f3eaffe364ac0c4092f06a4574e8cc40e73a
scope.1.id=Y2xhc3M6UHJvY2Vzc1Rlc3RDb21tYW5kRXhlY3V0b3IuUHJvY2Vzc0xhdW5jaGVyI1Byb2Nlc3NMYXVuY2hlcjo0NQ
scope.1.kind=class
scope.1.startLine=45
scope.1.endLine=47
scope.1.semanticHash=10b42be2e20a8dec58e676a923181763137466cddcd33c1a57b8f3e5f574090d
scope.2.id=ZmllbGQ6UHJvY2Vzc1Rlc3RDb21tYW5kRXhlY3V0b3IjREVGQVVMVF9DT01NQU5EOjE2
scope.2.kind=field
scope.2.startLine=16
scope.2.endLine=16
scope.2.semanticHash=acb4325465590753b34bb9354c8c30a80ec883e1192af00b0a047d3fcfc1426b
scope.3.id=ZmllbGQ6UHJvY2Vzc1Rlc3RDb21tYW5kRXhlY3V0b3IjY29tbWFuZFRleHQ6MTk
scope.3.kind=field
scope.3.startLine=19
scope.3.endLine=19
scope.3.semanticHash=cf5e4621fdfcc9bed909053e9e5ec2636e46e8a96f8f85b50082189d1d0948e3
scope.4.id=ZmllbGQ6UHJvY2Vzc1Rlc3RDb21tYW5kRXhlY3V0b3IjbGF1bmNoZXI6MTg
scope.4.kind=field
scope.4.startLine=18
scope.4.endLine=18
scope.4.semanticHash=cb1e32596d4bac984ccc3f70b1331164a1c69a45806b76ade38854b88ad66acd
scope.5.id=bWV0aG9kOlByb2Nlc3NUZXN0Q29tbWFuZEV4ZWN1dG9yI2N0b3IoMCk6MjA
scope.5.kind=method
scope.5.startLine=20
scope.5.endLine=22
scope.5.semanticHash=a2af65d3d123548a17dbcd90196867ee5df8c08685e865de43d5da25515f15cc
scope.6.id=bWV0aG9kOlByb2Nlc3NUZXN0Q29tbWFuZEV4ZWN1dG9yI2N0b3IoMSk6MjM
scope.6.kind=method
scope.6.startLine=23
scope.6.endLine=25
scope.6.semanticHash=33ef9e8ef600da0ab618bab16d8d800dc8ed8f79ac6229c2c08205c4dee972fc
scope.7.id=bWV0aG9kOlByb2Nlc3NUZXN0Q29tbWFuZEV4ZWN1dG9yI2N0b3IoMSk6MjY
scope.7.kind=method
scope.7.startLine=26
scope.7.endLine=28
scope.7.semanticHash=cd7188e6145bc861e04b6a8db08a1fa83137e02e47dc744c434f4144ec4fbd38
scope.8.id=bWV0aG9kOlByb2Nlc3NUZXN0Q29tbWFuZEV4ZWN1dG9yI2N0b3IoMSk6Mjk
scope.8.kind=method
scope.8.startLine=29
scope.8.endLine=31
scope.8.semanticHash=00322a1344e959934f01aa1cc2ed89548580c204a612a3802f4947401cb884e8
scope.9.id=bWV0aG9kOlByb2Nlc3NUZXN0Q29tbWFuZEV4ZWN1dG9yI2N0b3IoMik6MzM
scope.9.kind=method
scope.9.startLine=33
scope.9.endLine=36
scope.9.semanticHash=ad8a801afaa2e0ec60950e9d81ba6175899176fe3d9431822e47f377a3c91723
scope.10.id=bWV0aG9kOlByb2Nlc3NUZXN0Q29tbWFuZEV4ZWN1dG9yI3J1blRlc3RzKDIpOjM4
scope.10.kind=method
scope.10.startLine=38
scope.10.endLine=43
scope.10.semanticHash=f41e7bf5ed441fbdeac2999b66bd5bb196c0cd33b156b23aaa32433fe411533f
scope.11.id=bWV0aG9kOlByb2Nlc3NUZXN0Q29tbWFuZEV4ZWN1dG9yI3dpdGhDb21tYW5kKDEpOjQ5
scope.11.kind=method
scope.11.startLine=49
scope.11.endLine=52
scope.11.semanticHash=5fadabf15406547d60a36fb44968ebb497ffe1ba6e7f50795e7081a730b81206
scope.12.id=bWV0aG9kOlByb2Nlc3NUZXN0Q29tbWFuZEV4ZWN1dG9yLlByb2Nlc3NMYXVuY2hlciNzdGFydCgxKTo0Ng
scope.12.kind=method
scope.12.startLine=46
scope.12.endLine=46
scope.12.semanticHash=79add1b1ed55d671688626bd454c14c832548b85092a1102a093501732d6e226
*/
