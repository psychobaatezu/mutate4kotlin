package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

final class WorkerFutureCollector {

    List<MutationResult> collect(List<Future<List<MutationResult>>> futures, ExecutorService threadPool) throws Exception {
        List<MutationResult> results = new ArrayList<>();
        try {
            for (Future<List<MutationResult>> future : futures) {
                results.addAll(future.get());
            }
        } catch (ExecutionException ex) {
            threadPool.shutdownNow();
            Throwable cause = ex.getCause();
            if (cause instanceof Exception exception) {
                throw exception;
            }
            throw new IllegalStateException("Worker execution failed", cause);
        }
        results.sort(Comparator.comparingInt(MutationResult::order));
        return List.copyOf(results);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=28e4b5280a4f9c5ded3669e4dff44438cc3118786c1932ff2cd1692dcc5fda97
scope.0.id=Y2xhc3M6V29ya2VyRnV0dXJlQ29sbGVjdG9yI1dvcmtlckZ1dHVyZUNvbGxlY3RvcjoxNw
scope.0.kind=class
scope.0.startLine=17
scope.0.endLine=36
scope.0.semanticHash=7669a5af5ea85583ce5a46d42b27792042b3b7b455f484393c8303698049ee6d
scope.1.id=bWV0aG9kOldvcmtlckZ1dHVyZUNvbGxlY3RvciNjb2xsZWN0KDIpOjE5
scope.1.kind=method
scope.1.startLine=19
scope.1.endLine=35
scope.1.semanticHash=096af4c1c29c363b1e5f17d023aa8433cac90a006abcd815e2b053c5bca44583
scope.2.id=bWV0aG9kOldvcmtlckZ1dHVyZUNvbGxlY3RvciNjdG9yKDApOjE3
scope.2.kind=method
scope.2.startLine=1
scope.2.endLine=36
scope.2.semanticHash=eb3e6bb3130486813afd88d1df858a2a17c700df455637839243506931d69e6d
*/
