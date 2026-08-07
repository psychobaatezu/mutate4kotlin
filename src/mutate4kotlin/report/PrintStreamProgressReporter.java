package mutate4kotlin.report;

import mutate4kotlin.model.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import java.io.PrintStream;
import java.nio.file.Path;

public final class PrintStreamProgressReporter implements ProgressReporter {

    private final PrintStream out;
    private final ProgressMessageFormatter formatter = new ProgressMessageFormatter();

    public PrintStreamProgressReporter(PrintStream out) {
        this.out = out;
    }

    @Override
    public synchronized void baselineStarting(Path moduleRoot) {
        out.print(formatter.baselineStarting(moduleRoot));
    }

    @Override
    public synchronized void baselineFinished(TestRun baseline) {
        out.print(formatter.baselineFinished(baseline));
    }

    @Override
    public synchronized void runStarting(int totalMutations, int workerCount) {
        out.print(formatter.runStarting(totalMutations, workerCount));
    }

    @Override
    public synchronized void mutationStarting(int workerIndex, MutationJob job) {
        out.print(formatter.mutationStarting(workerIndex, job));
    }

    @Override
    public synchronized void mutationFinished(int workerIndex, MutationResult result) {
        out.print(formatter.mutationFinished(workerIndex, result));
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=08ed26c91f6337740d6a8a9390bd329be32d1d649964e45710c5cbf241e4f5b2
scope.0.id=Y2xhc3M6UHJpbnRTdHJlYW1Qcm9ncmVzc1JlcG9ydGVyI1ByaW50U3RyZWFtUHJvZ3Jlc3NSZXBvcnRlcjoxOA
scope.0.kind=class
scope.0.startLine=18
scope.0.endLine=51
scope.0.semanticHash=1f0bc7edeb00b01208b9f4520ee4935204cd29fe30bd9cdb406059d03a6af66b
scope.1.id=ZmllbGQ6UHJpbnRTdHJlYW1Qcm9ncmVzc1JlcG9ydGVyI2Zvcm1hdHRlcjoyMQ
scope.1.kind=field
scope.1.startLine=21
scope.1.endLine=21
scope.1.semanticHash=255196c2d788a8317c639f6ae46730c54568cb11907d4761b4626df2915bba2d
scope.2.id=ZmllbGQ6UHJpbnRTdHJlYW1Qcm9ncmVzc1JlcG9ydGVyI291dDoyMA
scope.2.kind=field
scope.2.startLine=20
scope.2.endLine=20
scope.2.semanticHash=b98df4fbf291f7cd01ba32f6b30c169fc64c08011a73e48a271561a4fcdd0a52
scope.3.id=bWV0aG9kOlByaW50U3RyZWFtUHJvZ3Jlc3NSZXBvcnRlciNiYXNlbGluZUZpbmlzaGVkKDEpOjMy
scope.3.kind=method
scope.3.startLine=32
scope.3.endLine=35
scope.3.semanticHash=66d7160c6619e899c0428364e75b3dd02326a72946d4aef2a8545ee5d4603a09
scope.4.id=bWV0aG9kOlByaW50U3RyZWFtUHJvZ3Jlc3NSZXBvcnRlciNiYXNlbGluZVN0YXJ0aW5nKDEpOjI3
scope.4.kind=method
scope.4.startLine=27
scope.4.endLine=30
scope.4.semanticHash=3d9e879a8c79b7a47800d6a73f474e6511cc752149f2040c16cfe53c0a105f70
scope.5.id=bWV0aG9kOlByaW50U3RyZWFtUHJvZ3Jlc3NSZXBvcnRlciNjdG9yKDEpOjIz
scope.5.kind=method
scope.5.startLine=23
scope.5.endLine=25
scope.5.semanticHash=0522d2383a4a3af2e7d0721d8f5cc31174d019a21a8c7db66dd30b149373e47b
scope.6.id=bWV0aG9kOlByaW50U3RyZWFtUHJvZ3Jlc3NSZXBvcnRlciNtdXRhdGlvbkZpbmlzaGVkKDIpOjQ3
scope.6.kind=method
scope.6.startLine=47
scope.6.endLine=50
scope.6.semanticHash=8ed08a589dc0c96f9785cf67513c27170ce882ff43845611794cf021797a724f
scope.7.id=bWV0aG9kOlByaW50U3RyZWFtUHJvZ3Jlc3NSZXBvcnRlciNtdXRhdGlvblN0YXJ0aW5nKDIpOjQy
scope.7.kind=method
scope.7.startLine=42
scope.7.endLine=45
scope.7.semanticHash=1373c1d499c78b37911e9074a18c1bddb5a9c89f75ad8ca64148f52f5ce99985
scope.8.id=bWV0aG9kOlByaW50U3RyZWFtUHJvZ3Jlc3NSZXBvcnRlciNydW5TdGFydGluZygyKTozNw
scope.8.kind=method
scope.8.startLine=37
scope.8.endLine=40
scope.8.semanticHash=d8192ad57702572c64480525f849b05225935c16cdfbd9eec59f1c9f6dc9654e
*/
