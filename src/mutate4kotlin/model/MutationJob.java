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

import java.nio.file.Path;

public record MutationJob(MutationSite site,
                   Path sourceRelativePath,
                   long timeoutMillis,
                   int order,
                   int totalJobs) {
}

/* mutate4kotlin-manifest
version=1
moduleHash=4b86c0ac851643bcbec6d31ec144ad72ab99db5a7e819d0081f1d16122f046c5
scope.0.id=Y2xhc3M6TXV0YXRpb25Kb2IjTXV0YXRpb25Kb2I6MTg
scope.0.kind=class
scope.0.startLine=18
scope.0.endLine=23
scope.0.semanticHash=a7cab737fbbd37747edfc24fc7e6c727004bea0e92e7e32c4fd4d5051678febe
scope.1.id=ZmllbGQ6TXV0YXRpb25Kb2Ijb3JkZXI6MjE
scope.1.kind=field
scope.1.startLine=21
scope.1.endLine=21
scope.1.semanticHash=0e66a2bfa108b680f4ec099d041d39db3e21c11b40e76b1a9be9aca42d545750
scope.2.id=ZmllbGQ6TXV0YXRpb25Kb2Ijc2l0ZToxOA
scope.2.kind=field
scope.2.startLine=18
scope.2.endLine=18
scope.2.semanticHash=a7a51416b7204844b86a01153232b04c037e2a021da84f00eb4cc86c66df7045
scope.3.id=ZmllbGQ6TXV0YXRpb25Kb2Ijc291cmNlUmVsYXRpdmVQYXRoOjE5
scope.3.kind=field
scope.3.startLine=19
scope.3.endLine=19
scope.3.semanticHash=4893807f9d2c101c2f6403b3c59419372ec00951180da66f745315c5ef96dd07
scope.4.id=ZmllbGQ6TXV0YXRpb25Kb2IjdGltZW91dE1pbGxpczoyMA
scope.4.kind=field
scope.4.startLine=20
scope.4.endLine=20
scope.4.semanticHash=4906b8273ee28192719a11a58331b1df5d9c570f74a1f7da7a71e702d9de6c53
scope.5.id=ZmllbGQ6TXV0YXRpb25Kb2IjdG90YWxKb2JzOjIy
scope.5.kind=field
scope.5.startLine=22
scope.5.endLine=22
scope.5.semanticHash=27442f1a1fe76d92c88a3bda8dc10176f8ed6c2c9e9ecf37e792865c262ce60a
scope.6.id=bWV0aG9kOk11dGF0aW9uSm9iI2N0b3IoNSk6MTg
scope.6.kind=method
scope.6.startLine=1
scope.6.endLine=23
scope.6.semanticHash=48883e0530701a0669020d2e3631cd31fce2fc1f569a67218d757839721f25bf
*/
