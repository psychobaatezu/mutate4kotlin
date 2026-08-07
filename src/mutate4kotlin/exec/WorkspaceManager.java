package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.io.IOException;
import java.nio.file.Path;

public interface WorkspaceManager {

    WorkerWorkspaces createWorkerWorkspaces(Path moduleRoot, int workerCount) throws IOException;
}

/* mutate4kotlin-manifest
version=1
moduleHash=a77ae9a1ff0772134c57f24b51ec5badf6673a3b204dfabaf32d300ae21f7137
scope.0.id=Y2xhc3M6V29ya3NwYWNlTWFuYWdlciNXb3Jrc3BhY2VNYW5hZ2VyOjEz
scope.0.kind=class
scope.0.startLine=13
scope.0.endLine=16
scope.0.semanticHash=607e508a19d6c52d26ed323f93de9984b6a6d0f9a3b07940f061e603e4733911
scope.1.id=bWV0aG9kOldvcmtzcGFjZU1hbmFnZXIjY3JlYXRlV29ya2VyV29ya3NwYWNlcygyKToxNQ
scope.1.kind=method
scope.1.startLine=15
scope.1.endLine=15
scope.1.semanticHash=0e338e0beb0d182070d62ca74e9a03c13f555be04d7a8213c87519fbc8d61df5
*/
