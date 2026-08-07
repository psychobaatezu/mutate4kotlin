package mutate4kotlin.engine;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.analysis.*;
import mutate4kotlin.cli.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;
import mutate4kotlin.selection.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

final class MutationExecution {

    private final WorkspaceManager workspaceManager;

    MutationExecution(WorkspaceManager workspaceManager) {
        this.workspaceManager = workspaceManager;
    }

    List<MutationResult> run(Path moduleRoot,
                             List<MutationSite> sites,
                             long timeoutMillis,
                             int maxWorkers,
                             ProgressReporter progressReporter,
                             TestCommandExecutor testExecutor) throws Exception {
        List<MutationJob> jobs = new ArrayList<>();
        for (int i = 0; i < sites.size(); i++) {
            MutationSite site = sites.get(i);
            jobs.add(new MutationJob(site, moduleRoot.relativize(site.file()), timeoutMillis, i, sites.size()));
        }
        int workerCount = Math.max(1, Math.min(jobs.size(), maxWorkers));
        try (WorkerWorkspaces workspaces = workspaceManager.createWorkerWorkspaces(moduleRoot, workerCount);
             WorkerPool pool = new ParallelWorkerPool(workspaces.workerRoots(), testExecutor, progressReporter)) {
            return pool.runAll(jobs);
        }
    }

    long timeoutMillis(long baselineDurationMillis, int timeoutFactor) {
        long baseline = Math.max(1L, baselineDurationMillis);
        return Math.max(1_000L, baseline * timeoutFactor);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=e7250489cc3c36793f387ac2964143ee5e99a6de06ab795b637a83b6c26311e0
scope.0.id=Y2xhc3M6TXV0YXRpb25FeGVjdXRpb24jTXV0YXRpb25FeGVjdXRpb246Mjc
scope.0.kind=class
scope.0.startLine=27
scope.0.endLine=57
scope.0.semanticHash=22427490eed92be6ffb568e5cb86cd8b7378d05884c5bea3ed5c375b9b3f83d6
scope.1.id=ZmllbGQ6TXV0YXRpb25FeGVjdXRpb24jd29ya3NwYWNlTWFuYWdlcjoyOQ
scope.1.kind=field
scope.1.startLine=29
scope.1.endLine=29
scope.1.semanticHash=60bbb4e898126c43385879883da3a5544b10f8098c4a451c74ad9ec157359d55
scope.2.id=bWV0aG9kOk11dGF0aW9uRXhlY3V0aW9uI2N0b3IoMSk6MzE
scope.2.kind=method
scope.2.startLine=31
scope.2.endLine=33
scope.2.semanticHash=3378101ccd3f8f5d76943d28c61d0c54b2157a25837b3e9f1ca1db55b891023a
scope.3.id=bWV0aG9kOk11dGF0aW9uRXhlY3V0aW9uI3J1big2KTozNQ
scope.3.kind=method
scope.3.startLine=35
scope.3.endLine=51
scope.3.semanticHash=920160cbf90690918a38260f2ab55573d35c46dc92842875cc19a748634cf524
scope.4.id=bWV0aG9kOk11dGF0aW9uRXhlY3V0aW9uI3RpbWVvdXRNaWxsaXMoMik6NTM
scope.4.kind=method
scope.4.startLine=53
scope.4.endLine=56
scope.4.semanticHash=433fc4bf876ce7237434a1b10d07920f962ea0fdcfed04daf3e30266efadd9d0
*/
