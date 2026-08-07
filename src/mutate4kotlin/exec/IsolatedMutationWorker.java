package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.nio.file.Files;
import java.nio.file.Path;

final class IsolatedMutationWorker implements MutationWorker {

    private final Path workerModuleRoot;
    private final TestCommandExecutor executor;
    private final ProgressReporter progressReporter;
    private final int workerIndex;

    IsolatedMutationWorker(Path workerModuleRoot,
                           TestCommandExecutor executor,
                           ProgressReporter progressReporter,
                           int workerIndex) {
        this.workerModuleRoot = workerModuleRoot;
        this.executor = executor;
        this.progressReporter = progressReporter;
        this.workerIndex = workerIndex;
    }

    @Override
    public MutationResult run(MutationJob job) throws Exception {
        progressReporter.mutationStarting(workerIndex, job);
        Path workerFile = workerModuleRoot.resolve(job.sourceRelativePath());
        String original = Files.readString(workerFile);
        Files.writeString(workerFile, mutatedSource(original, job.site()));
        try {
            TestRun run = executor.runTests(workerModuleRoot, job.timeoutMillis());
            MutationResult result = new MutationResult(
                    job.site(),
                    !run.passed(),
                    run.durationMillis(),
                    run.timedOut(),
                    job.order(),
                    job.totalJobs()
            );
            progressReporter.mutationFinished(workerIndex, result);
            return result;
        } finally {
            Files.writeString(workerFile, original);
        }
    }

    private String mutatedSource(String source, MutationSite site) {
        return source.substring(0, site.start())
                + site.replacementText()
                + source.substring(site.end());
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=245964c0ef9f339f69a0e7a5634e7b68cb6cb533d4ba64791354f57fa582b23a
scope.0.id=Y2xhc3M6SXNvbGF0ZWRNdXRhdGlvbldvcmtlciNJc29sYXRlZE11dGF0aW9uV29ya2VyOjEz
scope.0.kind=class
scope.0.startLine=13
scope.0.endLine=58
scope.0.semanticHash=8f1d16c876662c3e36928368266cbe6c428343d69a4412b82f9e775909d1b936
scope.1.id=ZmllbGQ6SXNvbGF0ZWRNdXRhdGlvbldvcmtlciNleGVjdXRvcjoxNg
scope.1.kind=field
scope.1.startLine=16
scope.1.endLine=16
scope.1.semanticHash=586732a0ecabc62826375cedaf16f617af3c1efc69001d666cf589b9ab7c733d
scope.2.id=ZmllbGQ6SXNvbGF0ZWRNdXRhdGlvbldvcmtlciNwcm9ncmVzc1JlcG9ydGVyOjE3
scope.2.kind=field
scope.2.startLine=17
scope.2.endLine=17
scope.2.semanticHash=822d43f8e79547024c33846d22f60703a14c1effeb5ff03690e54c82475a4cec
scope.3.id=ZmllbGQ6SXNvbGF0ZWRNdXRhdGlvbldvcmtlciN3b3JrZXJJbmRleDoxOA
scope.3.kind=field
scope.3.startLine=18
scope.3.endLine=18
scope.3.semanticHash=fac3aeaa5c0390938c149a9458ed52f2c60552c9cbee6d3c815e89e844a74b7b
scope.4.id=ZmllbGQ6SXNvbGF0ZWRNdXRhdGlvbldvcmtlciN3b3JrZXJNb2R1bGVSb290OjE1
scope.4.kind=field
scope.4.startLine=15
scope.4.endLine=15
scope.4.semanticHash=a2661a729d17d17776f340d8a9f737bca4a9d491408c8971292e96bb435a6291
scope.5.id=bWV0aG9kOklzb2xhdGVkTXV0YXRpb25Xb3JrZXIjY3Rvcig0KToyMA
scope.5.kind=method
scope.5.startLine=20
scope.5.endLine=28
scope.5.semanticHash=a78c027770c4b11553ea0eceee772149867cf7cbd085969f6709e861e0cc692c
scope.6.id=bWV0aG9kOklzb2xhdGVkTXV0YXRpb25Xb3JrZXIjbXV0YXRlZFNvdXJjZSgyKTo1Mw
scope.6.kind=method
scope.6.startLine=53
scope.6.endLine=57
scope.6.semanticHash=05c32d0f2f9e57b3f691d6084304f6e28cd1edc8743f51214b1755c14f0080cb
scope.7.id=bWV0aG9kOklzb2xhdGVkTXV0YXRpb25Xb3JrZXIjcnVuKDEpOjMw
scope.7.kind=method
scope.7.startLine=30
scope.7.endLine=51
scope.7.semanticHash=2d003ded089dcc27ce1c6d73ff8cb065fa6415fe4d678256ee64b8a8889de2c1
*/
