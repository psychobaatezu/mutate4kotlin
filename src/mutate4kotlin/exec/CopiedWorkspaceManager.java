package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class CopiedWorkspaceManager implements WorkspaceManager {

    private final ModuleTreeCopier copier = new ModuleTreeCopier();

    @Override
    public WorkerWorkspaces createWorkerWorkspaces(Path moduleRoot, int workerCount) throws IOException {
        Path workersBase = moduleRoot.resolve("target/mutation-workers");
        Files.createDirectories(workersBase);
        Path runRoot = workersBase.resolve("run-" + UUID.randomUUID());
        Files.createDirectories(runRoot);

        List<Path> workerRoots = new ArrayList<>();
        for (int worker = 1; worker <= workerCount; worker++) {
            Path workerRoot = runRoot.resolve("worker-" + worker);
            copier.copy(moduleRoot, workerRoot);
            workerRoots.add(workerRoot);
        }
        return new WorkerWorkspaces(runRoot, List.copyOf(workerRoots));
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=b35f1036c99b694ee2cc30358b9af3947b787f16fdd2ce1493ddac5de08dde5e
scope.0.id=Y2xhc3M6Q29waWVkV29ya3NwYWNlTWFuYWdlciNDb3BpZWRXb3Jrc3BhY2VNYW5hZ2VyOjIw
scope.0.kind=class
scope.0.startLine=20
scope.0.endLine=39
scope.0.semanticHash=4bd32e329bc7ef398e92faaded71cbec09921febbc524199802b076d89bd72a0
scope.1.id=ZmllbGQ6Q29waWVkV29ya3NwYWNlTWFuYWdlciNjb3BpZXI6MjI
scope.1.kind=field
scope.1.startLine=22
scope.1.endLine=22
scope.1.semanticHash=69f013451833c2d496acf5f2bb782996ab13cb05fb79c479ab31b4f4aec93107
scope.2.id=bWV0aG9kOkNvcGllZFdvcmtzcGFjZU1hbmFnZXIjY3JlYXRlV29ya2VyV29ya3NwYWNlcygyKToyNA
scope.2.kind=method
scope.2.startLine=24
scope.2.endLine=38
scope.2.semanticHash=752fc3c8068cfba74037d585e6012a1aee44f8dc135a49a6ad555cab2cc43d82
scope.3.id=bWV0aG9kOkNvcGllZFdvcmtzcGFjZU1hbmFnZXIjY3RvcigwKToyMA
scope.3.kind=method
scope.3.startLine=1
scope.3.endLine=39
scope.3.semanticHash=8e3b355e9c8584296546bd51511bf052c52bec14c5e401a76f331c3834b5d881
*/
