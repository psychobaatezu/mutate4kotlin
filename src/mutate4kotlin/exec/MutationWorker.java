package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

interface MutationWorker extends AutoCloseable {

    MutationResult run(MutationJob job) throws Exception;

    @Override
    default void close() throws Exception {
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=c8f2ca95529ef657d9f74eb6300de7b6b650d2c45196efc5456b853482384a87
scope.0.id=Y2xhc3M6TXV0YXRpb25Xb3JrZXIjTXV0YXRpb25Xb3JrZXI6MTA
scope.0.kind=class
scope.0.startLine=10
scope.0.endLine=17
scope.0.semanticHash=e9bdce06c4981962e9a68b9c8ce06462017650c29325ca67795e80c37cc74922
scope.1.id=bWV0aG9kOk11dGF0aW9uV29ya2VyI2Nsb3NlKDApOjE0
scope.1.kind=method
scope.1.startLine=14
scope.1.endLine=16
scope.1.semanticHash=f16971c6b62a62e0a62adada661b9885be5f88969c14cbe5d8e82bf96c812f31
scope.2.id=bWV0aG9kOk11dGF0aW9uV29ya2VyI3J1bigxKToxMg
scope.2.kind=method
scope.2.startLine=12
scope.2.endLine=12
scope.2.semanticHash=4d16c4144a234c2e8983c7c41421005e034688b229ab2f566e1e8ad56aa9a758
*/
