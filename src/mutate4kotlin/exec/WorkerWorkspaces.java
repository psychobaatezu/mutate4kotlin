package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Path;
import java.util.List;

public record WorkerWorkspaces(Path runRoot, List<Path> workerRoots) implements AutoCloseable {

    @Override
    public void close() throws IOException {
        new WorkerWorkspaceCloser().close(runRoot);
    }
    public static IOException tryDelete(Path runRoot) {
        return tryDelete(runRoot, new WorkerDirectoryDelete()::delete);
    }

    public static IOException tryDelete(Path runRoot, DeleteTree deleteTree) {
        try {
            deleteTree.delete(runRoot);
            return null;
        } catch (IOException ex) {
            return ex;
        }
    }
    public static IOException deleteWithRetries(Path runRoot, DeleteAttempt deleteAttempt, RetrySleeper retrySleeper) {
        return WorkerCleanup.deleteWithRetries(runRoot, deleteAttempt::tryDelete, retrySleeper::sleep);
    }

    @FunctionalInterface
    public interface DeleteAttempt {
        IOException tryDelete(Path runRoot);
    }

    @FunctionalInterface
    public interface RetrySleeper {
        void sleep();
    }

    @FunctionalInterface
    public interface DeleteTree {
        void delete(Path runRoot) throws IOException;
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=b1992b2d1a1057c89d2b44a56fc307f92fc1c9bea8071af7ca4d17955c070da7
scope.0.id=Y2xhc3M6V29ya2VyV29ya3NwYWNlcyNXb3JrZXJXb3Jrc3BhY2VzOjE1
scope.0.kind=class
scope.0.startLine=15
scope.0.endLine=51
scope.0.semanticHash=8da839e9415e2b406a65e9677c1f06b2a59322932db15fd914cfbbb9f8d060c0
scope.1.id=Y2xhc3M6V29ya2VyV29ya3NwYWNlcy5EZWxldGVBdHRlbXB0I0RlbGV0ZUF0dGVtcHQ6Mzc
scope.1.kind=class
scope.1.startLine=37
scope.1.endLine=40
scope.1.semanticHash=afd43be2087a59cc7974c1f3b9458e2743834e758524c5965ba69f8408994514
scope.2.id=Y2xhc3M6V29ya2VyV29ya3NwYWNlcy5EZWxldGVUcmVlI0RlbGV0ZVRyZWU6NDc
scope.2.kind=class
scope.2.startLine=47
scope.2.endLine=50
scope.2.semanticHash=e7982efa79000686dc161c5c8c23e8e374c088be5161d8606fc6f2daba66b8de
scope.3.id=Y2xhc3M6V29ya2VyV29ya3NwYWNlcy5SZXRyeVNsZWVwZXIjUmV0cnlTbGVlcGVyOjQy
scope.3.kind=class
scope.3.startLine=42
scope.3.endLine=45
scope.3.semanticHash=06b0e20b3e890c659a3a9b80c13fa80cf1fb3149c4d916375ad095e14dde4ac1
scope.4.id=ZmllbGQ6V29ya2VyV29ya3NwYWNlcyNydW5Sb290OjE1
scope.4.kind=field
scope.4.startLine=15
scope.4.endLine=15
scope.4.semanticHash=772a6b7690de9807e89401768f987732d2f756f8d4d8b713a616f2f320d1bdbf
scope.5.id=ZmllbGQ6V29ya2VyV29ya3NwYWNlcyN3b3JrZXJSb290czoxNQ
scope.5.kind=field
scope.5.startLine=15
scope.5.endLine=15
scope.5.semanticHash=864f4dc6415331bf3df76e3b12034411554830e1c5bd475df6cd7d57236c2fc5
scope.6.id=bWV0aG9kOldvcmtlcldvcmtzcGFjZXMjY2xvc2UoMCk6MTc
scope.6.kind=method
scope.6.startLine=17
scope.6.endLine=20
scope.6.semanticHash=cc611da0b92c55d06f7e872983d8a4f3cfd5dec23ab129fc31889e979a9f692f
scope.7.id=bWV0aG9kOldvcmtlcldvcmtzcGFjZXMjY3RvcigyKToxNQ
scope.7.kind=method
scope.7.startLine=1
scope.7.endLine=51
scope.7.semanticHash=16f6dfab7dee4facfc73902f2e06f0b0b34e7718fe5350fcb9e8142165ea1559
scope.8.id=bWV0aG9kOldvcmtlcldvcmtzcGFjZXMjZGVsZXRlV2l0aFJldHJpZXMoMyk6MzM
scope.8.kind=method
scope.8.startLine=33
scope.8.endLine=35
scope.8.semanticHash=a1fa27433ee3752642eeeeae4a4d56233d2fb088b181c56e7db259a8f128696d
scope.9.id=bWV0aG9kOldvcmtlcldvcmtzcGFjZXMjdHJ5RGVsZXRlKDEpOjIx
scope.9.kind=method
scope.9.startLine=21
scope.9.endLine=23
scope.9.semanticHash=3c34839c28016a3390fb86142d240d1fd5df386d229824476cd4e70e375d8dab
scope.10.id=bWV0aG9kOldvcmtlcldvcmtzcGFjZXMjdHJ5RGVsZXRlKDIpOjI1
scope.10.kind=method
scope.10.startLine=25
scope.10.endLine=32
scope.10.semanticHash=5d0ac91ed788d3a72c8158f67287ae44cb1f5ed8913def70a97c878cbda6f5f5
scope.11.id=bWV0aG9kOldvcmtlcldvcmtzcGFjZXMuRGVsZXRlQXR0ZW1wdCN0cnlEZWxldGUoMSk6Mzk
scope.11.kind=method
scope.11.startLine=39
scope.11.endLine=39
scope.11.semanticHash=92f1ab5b3f8815b5d499d9af3fcd6190fad0b988da858da1786c6f1b04ee806c
scope.12.id=bWV0aG9kOldvcmtlcldvcmtzcGFjZXMuRGVsZXRlVHJlZSNkZWxldGUoMSk6NDk
scope.12.kind=method
scope.12.startLine=49
scope.12.endLine=49
scope.12.semanticHash=ed18c4210d31cf729700f62f81145ab93e3b9b23b80104567d2ae58eb51870be
scope.13.id=bWV0aG9kOldvcmtlcldvcmtzcGFjZXMuUmV0cnlTbGVlcGVyI3NsZWVwKDApOjQ0
scope.13.kind=method
scope.13.startLine=44
scope.13.endLine=44
scope.13.semanticHash=64aa96c23dc38d6626d29ef2a92b940468dc98b77ab2b7dd8376eeecca601700
*/
