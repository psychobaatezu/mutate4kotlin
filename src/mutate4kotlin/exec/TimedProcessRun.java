package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

final class TimedProcessRun {

    private TimedProcessRun() {
    }

    static TestRun finish(Process process, long timeoutMillis, long startNanos) throws InterruptedException {
        ProcessExecution execution = new ProcessRunnerSupport().run(process, timeoutMillis, startNanos);
        return new TestRun(execution.exitCode(), execution.output(), execution.durationMillis(), execution.timedOut());
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=33c3def200638c94785571ce3ca8e4c96290958629dbd4eb8b7fd27265bd6a44
scope.0.id=Y2xhc3M6VGltZWRQcm9jZXNzUnVuI1RpbWVkUHJvY2Vzc1J1bjoxMw
scope.0.kind=class
scope.0.startLine=13
scope.0.endLine=49
scope.0.semanticHash=c029b9c0fa7d2701f4f11ce07f82f69b3ddcdbd28f61cdc7be803696dcb62e83
scope.1.id=bWV0aG9kOlRpbWVkUHJvY2Vzc1J1biNjdG9yKDApOjE1
scope.1.kind=method
scope.1.startLine=15
scope.1.endLine=16
scope.1.semanticHash=049e73e88326e3180d0cec509e1ac139343ce3a657a3c315f8714f32c3dd5632
scope.2.id=bWV0aG9kOlRpbWVkUHJvY2Vzc1J1biNleGl0Q29kZSgyKTozMw
scope.2.kind=method
scope.2.startLine=33
scope.2.endLine=40
scope.2.semanticHash=4b4d53c3229714e01aee9ed7047a05c3e7eedc11328e8e4e6e54e0f7ebf4d0dc
scope.3.id=bWV0aG9kOlRpbWVkUHJvY2Vzc1J1biNmaW5pc2goMyk6MTg
scope.3.kind=method
scope.3.startLine=18
scope.3.endLine=23
scope.3.semanticHash=2cdb320a9262f484fde1146108e975dd74598b36c9989db7ee9a975fc611ea09
scope.4.id=bWV0aG9kOlRpbWVkUHJvY2Vzc1J1biNyZWFkT3V0cHV0KDEpOjQy
scope.4.kind=method
scope.4.startLine=42
scope.4.endLine=48
scope.4.semanticHash=22e416232865aa92407b0765fecab9691dab7724477d32849088d80dcee05c59
scope.5.id=bWV0aG9kOlRpbWVkUHJvY2Vzc1J1biN3YWl0Rm9yKDIpOjI1
scope.5.kind=method
scope.5.startLine=25
scope.5.endLine=31
scope.5.semanticHash=ebc667d3189d67b798b99277e5b5c4fc5010d61cb51def1f141f97e875f50a62
*/
