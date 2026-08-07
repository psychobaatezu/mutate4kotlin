package mutate4kotlin.report;

import mutate4kotlin.model.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

public final class NoOpProgressReporter implements ProgressReporter {

    public NoOpProgressReporter() {
    }

    @Override
    public void baselineStarting(java.nio.file.Path moduleRoot) {
    }

    @Override
    public void baselineFinished(TestRun baseline) {
    }

    @Override
    public void runStarting(int totalMutations, int workerCount) {
    }

    @Override
    public void mutationStarting(int workerIndex, MutationJob job) {
    }

    @Override
    public void mutationFinished(int workerIndex, MutationResult result) {
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=6a8157a6198125c4a1a19a270a51d9320c8fbb8b5985a80240a552e5ef238e60
scope.0.id=Y2xhc3M6Tm9PcFByb2dyZXNzUmVwb3J0ZXIjTm9PcFByb2dyZXNzUmVwb3J0ZXI6MTU
scope.0.kind=class
scope.0.startLine=15
scope.0.endLine=39
scope.0.semanticHash=06eda9d41803d5a04ffb5d74cc719daa996bcd043c9f26590d3b0d09caa6ab66
scope.1.id=bWV0aG9kOk5vT3BQcm9ncmVzc1JlcG9ydGVyI2Jhc2VsaW5lRmluaXNoZWQoMSk6MjQ
scope.1.kind=method
scope.1.startLine=24
scope.1.endLine=26
scope.1.semanticHash=806f1f20006bb2d2e1ec96b33db1713e72e01f4ff531c685442a44a15a86950c
scope.2.id=bWV0aG9kOk5vT3BQcm9ncmVzc1JlcG9ydGVyI2Jhc2VsaW5lU3RhcnRpbmcoMSk6MjA
scope.2.kind=method
scope.2.startLine=20
scope.2.endLine=22
scope.2.semanticHash=2e2bcad7d22b810ee23334995c2960a5e654188090283558affcfd5f29c34d02
scope.3.id=bWV0aG9kOk5vT3BQcm9ncmVzc1JlcG9ydGVyI2N0b3IoMCk6MTc
scope.3.kind=method
scope.3.startLine=17
scope.3.endLine=18
scope.3.semanticHash=8076cf1c1557059c64fceac48697b7f7f5d5bbd2a576134471acf5df32427a78
scope.4.id=bWV0aG9kOk5vT3BQcm9ncmVzc1JlcG9ydGVyI211dGF0aW9uRmluaXNoZWQoMik6MzY
scope.4.kind=method
scope.4.startLine=36
scope.4.endLine=38
scope.4.semanticHash=3ea79bf3faaee6d22e39336d8f5f3724beabc344c75e0064a85eb38caa526196
scope.5.id=bWV0aG9kOk5vT3BQcm9ncmVzc1JlcG9ydGVyI211dGF0aW9uU3RhcnRpbmcoMik6MzI
scope.5.kind=method
scope.5.startLine=32
scope.5.endLine=34
scope.5.semanticHash=da0af9fb13dcf8011bf7a6aea0fb0c5095d395ce638e176f697a42e9d2715f14
scope.6.id=bWV0aG9kOk5vT3BQcm9ncmVzc1JlcG9ydGVyI3J1blN0YXJ0aW5nKDIpOjI4
scope.6.kind=method
scope.6.startLine=28
scope.6.endLine=30
scope.6.semanticHash=fef575637efd762424803c4008e33e59fa114e7084decb07801b64370af12fe0
*/
