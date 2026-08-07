package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Path;

final class WorkerCleanup {

    static final int DELETE_RETRIES = 5;
    static final long RETRY_DELAY_MILLIS = 50L;

    private WorkerCleanup() {
    }

    static IOException deleteWithRetries(Path runRoot, WorkerWorkspaces.DeleteAttempt deleteAttempt, WorkerWorkspaces.RetrySleeper retrySleeper) {
        IOException failure = null;
        for (int attempt = 1; attempt <= DELETE_RETRIES; attempt++) {
            failure = deleteAttempt.tryDelete(runRoot);
            if (failure == null) {
                return null;
            }
            if (!(failure instanceof DirectoryNotEmptyException)) {
                throw new IllegalStateException("Failed deleting worker workspace: " + runRoot, failure);
            }
            retrySleeper.sleep();
        }
        return failure;
    }

    static void sleepBeforeRetry() {
        try {
            Thread.sleep(RETRY_DELAY_MILLIS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while deleting worker workspace", ex);
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=916196e785fc5950644341c095048b2fc45c7fcbaf90b717552e957cf88f1bf0
scope.0.id=Y2xhc3M6V29ya2VyQ2xlYW51cCNXb3JrZXJDbGVhbnVwOjE0
scope.0.kind=class
scope.0.startLine=14
scope.0.endLine=45
scope.0.semanticHash=9a68a4c9c90e340eceb4bd594b3161e0d9d32473d90d7171a33c42bd7704c640
scope.1.id=ZmllbGQ6V29ya2VyQ2xlYW51cCNERUxFVEVfUkVUUklFUzoxNg
scope.1.kind=field
scope.1.startLine=16
scope.1.endLine=16
scope.1.semanticHash=1ae6cdef4e29c2a85a338a1d60c485dfb58e15b67b204ab84437adb401c946d8
scope.2.id=ZmllbGQ6V29ya2VyQ2xlYW51cCNSRVRSWV9ERUxBWV9NSUxMSVM6MTc
scope.2.kind=field
scope.2.startLine=17
scope.2.endLine=17
scope.2.semanticHash=ed74c3e96af540cb3bb9702831e366990a6073a9c3e0f68a7e7822aeb24de8e1
scope.3.id=bWV0aG9kOldvcmtlckNsZWFudXAjY3RvcigwKToxOQ
scope.3.kind=method
scope.3.startLine=19
scope.3.endLine=20
scope.3.semanticHash=cf7fcd1aface6f1393d99ea9ef383c25799930d27c2e832f307ff61261319e45
scope.4.id=bWV0aG9kOldvcmtlckNsZWFudXAjZGVsZXRlV2l0aFJldHJpZXMoMyk6MjI
scope.4.kind=method
scope.4.startLine=22
scope.4.endLine=35
scope.4.semanticHash=8f9b6ea479c743cfaa21737b16f08cfc519624cba40fbf8a744837dd0c195ef2
scope.5.id=bWV0aG9kOldvcmtlckNsZWFudXAjc2xlZXBCZWZvcmVSZXRyeSgwKTozNw
scope.5.kind=method
scope.5.startLine=37
scope.5.endLine=44
scope.5.semanticHash=accf26e2b8b71ac8afda5c956c4e0cf3dfbc622a27f17d2542cf8f6b52832e2e
*/
