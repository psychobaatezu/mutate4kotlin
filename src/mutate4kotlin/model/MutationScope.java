package mutate4kotlin.model;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

public record MutationScope(String id, String kind, int startLine, int endLine, String semanticHash) {
}

/* mutate4kotlin-manifest
version=1
moduleHash=9a4a945c249aa4d84007779f759612598097c5244657b390dd737ffff1e813ff
scope.0.id=Y2xhc3M6TXV0YXRpb25TY29wZSNNdXRhdGlvblNjb3BlOjE2
scope.0.kind=class
scope.0.startLine=16
scope.0.endLine=17
scope.0.semanticHash=da7d0f8eda9e5cf590c06142aef3521d46196f778c277862765a332ede775376
scope.1.id=ZmllbGQ6TXV0YXRpb25TY29wZSNlbmRMaW5lOjE2
scope.1.kind=field
scope.1.startLine=16
scope.1.endLine=16
scope.1.semanticHash=6a317d712b10858871edee8b33ee58b8a682069bbab781a96d93cf4abe131abe
scope.2.id=ZmllbGQ6TXV0YXRpb25TY29wZSNpZDoxNg
scope.2.kind=field
scope.2.startLine=16
scope.2.endLine=16
scope.2.semanticHash=e8fa4cb36294bbb4bd5d903d949ffd11d1cecb67601bc0971c86bcf7b9f75249
scope.3.id=ZmllbGQ6TXV0YXRpb25TY29wZSNraW5kOjE2
scope.3.kind=field
scope.3.startLine=16
scope.3.endLine=16
scope.3.semanticHash=409014eefe90e3ecd3219c367bb4cae049ec0a555945b9fcb4539bc28c2cd25e
scope.4.id=ZmllbGQ6TXV0YXRpb25TY29wZSNzZW1hbnRpY0hhc2g6MTY
scope.4.kind=field
scope.4.startLine=16
scope.4.endLine=16
scope.4.semanticHash=6bb7c0e2cf9e1d01ef9fd621e26fd0930c8e6fd57c678f3360d4dc78607ffee9
scope.5.id=ZmllbGQ6TXV0YXRpb25TY29wZSNzdGFydExpbmU6MTY
scope.5.kind=field
scope.5.startLine=16
scope.5.endLine=16
scope.5.semanticHash=1c3439a97a0bcc4e6560a484ca45e0482b4b1010943d82fa235aa4a63d21ffb1
scope.6.id=bWV0aG9kOk11dGF0aW9uU2NvcGUjY3Rvcig1KToxNg
scope.6.kind=method
scope.6.startLine=1
scope.6.endLine=17
scope.6.semanticHash=4e03a12227ed9090f3c8b8e16323c180cc74c3e0ba010d6c0e11b12c12bb7b82
*/
