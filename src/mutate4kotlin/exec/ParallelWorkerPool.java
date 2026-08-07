package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.nio.file.Path;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class ParallelWorkerPool implements WorkerPool {

    private final List<Path> workerRoots;
    private final TestCommandExecutor executor;
    private final ProgressReporter progressReporter;
    private final ExecutorService threadPool;
    private final WorkerFutureCollector futureCollector = new WorkerFutureCollector();
    public ParallelWorkerPool(List<Path> workerRoots,
                       TestCommandExecutor executor,
                       ProgressReporter progressReporter) {
        this.workerRoots = workerRoots;
        this.executor = executor;
        this.progressReporter = progressReporter;
        this.threadPool = Executors.newFixedThreadPool(workerRoots.size());
    }

    @Override
    public List<MutationResult> runAll(List<MutationJob> jobs) throws Exception {
        progressReporter.runStarting(jobs.size(), workerRoots.size());
        Queue<MutationJob> queue = new ConcurrentLinkedQueue<>(jobs);
        List<Future<List<MutationResult>>> futures = new ArrayList<>();
        for (int i = 0; i < workerRoots.size(); i++) {
            int workerIndex = i + 1;
            Path workerRoot = workerRoots.get(i);
            futures.add(threadPool.submit(() -> runWorker(workerRoot, workerIndex, queue)));
        }
        return futureCollector.collect(futures, threadPool);
    }

    private List<MutationResult> runWorker(Path workerRoot, int workerIndex, Queue<MutationJob> queue) throws Exception {
        List<MutationResult> results = new ArrayList<>();
        try (MutationWorker worker = new IsolatedMutationWorker(workerRoot, executor, progressReporter, workerIndex)) {
            for (MutationJob job = queue.poll(); job != null; job = queue.poll()) {
                results.add(worker.run(job));
            }
        }
        return results;
    }

    @Override
    public void close() {
        threadPool.shutdownNow();
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=437673c69c143c95d1bb2396490ffd20d599a387d6fc4b9f3c50e9dc2be644f9
scope.0.id=Y2xhc3M6UGFyYWxsZWxXb3JrZXJQb29sI1BhcmFsbGVsV29ya2VyUG9vbDoyMQ
scope.0.kind=class
scope.0.startLine=21
scope.0.endLine=64
scope.0.semanticHash=f3c7397a12e313840eab40ccccf37c0735c23b1f1d9757d9afbbeb3055504765
scope.1.id=ZmllbGQ6UGFyYWxsZWxXb3JrZXJQb29sI2V4ZWN1dG9yOjI0
scope.1.kind=field
scope.1.startLine=24
scope.1.endLine=24
scope.1.semanticHash=586732a0ecabc62826375cedaf16f617af3c1efc69001d666cf589b9ab7c733d
scope.2.id=ZmllbGQ6UGFyYWxsZWxXb3JrZXJQb29sI2Z1dHVyZUNvbGxlY3RvcjoyNw
scope.2.kind=field
scope.2.startLine=27
scope.2.endLine=27
scope.2.semanticHash=b1cad4c1bafe9fe89d05cf16d3e613dc44989efb45093357f4f8c0a9f03f8d43
scope.3.id=ZmllbGQ6UGFyYWxsZWxXb3JrZXJQb29sI3Byb2dyZXNzUmVwb3J0ZXI6MjU
scope.3.kind=field
scope.3.startLine=25
scope.3.endLine=25
scope.3.semanticHash=822d43f8e79547024c33846d22f60703a14c1effeb5ff03690e54c82475a4cec
scope.4.id=ZmllbGQ6UGFyYWxsZWxXb3JrZXJQb29sI3RocmVhZFBvb2w6MjY
scope.4.kind=field
scope.4.startLine=26
scope.4.endLine=26
scope.4.semanticHash=dbeee8006c4e51282ccb22437bc0f63652e441d0e03812465bf779d9870ad7fc
scope.5.id=ZmllbGQ6UGFyYWxsZWxXb3JrZXJQb29sI3dvcmtlclJvb3RzOjIz
scope.5.kind=field
scope.5.startLine=23
scope.5.endLine=23
scope.5.semanticHash=2d7605598709a2a552b4e6043b89758c71bddbee49f9b208349f804d77a953ff
scope.6.id=bWV0aG9kOlBhcmFsbGVsV29ya2VyUG9vbCNjbG9zZSgwKTo2MA
scope.6.kind=method
scope.6.startLine=60
scope.6.endLine=63
scope.6.semanticHash=fcf222b70804259609092793567d978f099a2a78bc58bcd1cbcf764cc8a7e86a
scope.7.id=bWV0aG9kOlBhcmFsbGVsV29ya2VyUG9vbCNjdG9yKDMpOjI4
scope.7.kind=method
scope.7.startLine=28
scope.7.endLine=35
scope.7.semanticHash=3c609d3f902fb54ed5ffa02e11db9d86f04f3898bbdb4645bc0f1cfd2ce72349
scope.8.id=bWV0aG9kOlBhcmFsbGVsV29ya2VyUG9vbCNydW5BbGwoMSk6Mzc
scope.8.kind=method
scope.8.startLine=37
scope.8.endLine=48
scope.8.semanticHash=838f8ea1334ff394368ffc74ffbc9604f038591256ffe4e6bd94364c1070915b
scope.9.id=bWV0aG9kOlBhcmFsbGVsV29ya2VyUG9vbCNydW5Xb3JrZXIoMyk6NTA
scope.9.kind=method
scope.9.startLine=50
scope.9.endLine=58
scope.9.semanticHash=c4c04279d6a47f8629959638c2ca33b780d474d86b53fb601aabfaa2e5faad44
*/
