package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class WorkerWorkspaceCloser {

    void close(Path runRoot) throws IOException {
        if (!Files.exists(runRoot)) {
            return;
        }
        IOException failure = WorkerCleanup.deleteWithRetries(runRoot, WorkerWorkspaces::tryDelete, WorkerCleanup::sleepBeforeRetry);
        if (failure != null) {
            throw new IllegalStateException("Failed deleting worker workspace: " + runRoot, failure);
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=d97850cd62824d312a703b77e6379cf942d59e6c01da73acf4b26f204e67e4e2
scope.0.id=Y2xhc3M6V29ya2VyV29ya3NwYWNlQ2xvc2VyI1dvcmtlcldvcmtzcGFjZUNsb3NlcjoxNA
scope.0.kind=class
scope.0.startLine=14
scope.0.endLine=25
scope.0.semanticHash=b71dc186000f722b6441c8aacacf02eb4b6fc2d40f336ccc53e173cfd9665379
scope.1.id=bWV0aG9kOldvcmtlcldvcmtzcGFjZUNsb3NlciNjbG9zZSgxKToxNg
scope.1.kind=method
scope.1.startLine=16
scope.1.endLine=24
scope.1.semanticHash=0274b087c6b40ca1e5d873c8d24301f280ecb21a0e2ae432a7c5cbd45c559780
scope.2.id=bWV0aG9kOldvcmtlcldvcmtzcGFjZUNsb3NlciNjdG9yKDApOjE0
scope.2.kind=method
scope.2.startLine=1
scope.2.endLine=25
scope.2.semanticHash=3b1310540fe50c7b8a547b304aa461b07f6800490fba284fedbffd88beb5c572
*/
