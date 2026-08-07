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

public record MutationResult(MutationSite site, boolean killed, long durationMillis, boolean timedOut, int order, int totalJobs) {
}

/* mutate4kotlin-manifest
version=1
moduleHash=69e88b866f1d5a0ae6fdb3623f83c5f671324419259e6d0cddf8989cf9e3cda1
scope.0.id=Y2xhc3M6TXV0YXRpb25SZXN1bHQjTXV0YXRpb25SZXN1bHQ6MTY
scope.0.kind=class
scope.0.startLine=16
scope.0.endLine=17
scope.0.semanticHash=07e595f0479bdcad7ebc0f56d04d60ecb549b895f846326db552e16616d8d25d
scope.1.id=ZmllbGQ6TXV0YXRpb25SZXN1bHQjZHVyYXRpb25NaWxsaXM6MTY
scope.1.kind=field
scope.1.startLine=16
scope.1.endLine=16
scope.1.semanticHash=aa9aa63ad847ef3f927bafd418f3e392b391e4c0075d48a91d10d27edf303bc3
scope.2.id=ZmllbGQ6TXV0YXRpb25SZXN1bHQja2lsbGVkOjE2
scope.2.kind=field
scope.2.startLine=16
scope.2.endLine=16
scope.2.semanticHash=378bea803d2499c3151d941bd7367ec27f0e04aed7985a67f8625b697313df0f
scope.3.id=ZmllbGQ6TXV0YXRpb25SZXN1bHQjb3JkZXI6MTY
scope.3.kind=field
scope.3.startLine=16
scope.3.endLine=16
scope.3.semanticHash=0e66a2bfa108b680f4ec099d041d39db3e21c11b40e76b1a9be9aca42d545750
scope.4.id=ZmllbGQ6TXV0YXRpb25SZXN1bHQjc2l0ZToxNg
scope.4.kind=field
scope.4.startLine=16
scope.4.endLine=16
scope.4.semanticHash=a7a51416b7204844b86a01153232b04c037e2a021da84f00eb4cc86c66df7045
scope.5.id=ZmllbGQ6TXV0YXRpb25SZXN1bHQjdGltZWRPdXQ6MTY
scope.5.kind=field
scope.5.startLine=16
scope.5.endLine=16
scope.5.semanticHash=15d6b8ef15a135d7a7402858996de81a1a61f74338bfc1158a2c3dd58a852ac8
scope.6.id=ZmllbGQ6TXV0YXRpb25SZXN1bHQjdG90YWxKb2JzOjE2
scope.6.kind=field
scope.6.startLine=16
scope.6.endLine=16
scope.6.semanticHash=27442f1a1fe76d92c88a3bda8dc10176f8ed6c2c9e9ecf37e792865c262ce60a
scope.7.id=bWV0aG9kOk11dGF0aW9uUmVzdWx0I2N0b3IoNik6MTY
scope.7.kind=method
scope.7.startLine=1
scope.7.endLine=17
scope.7.semanticHash=5aa297419a8e7ee5cc068185e7a2d2673b16c5373814c5d27a97c3eb3de8494a
*/
