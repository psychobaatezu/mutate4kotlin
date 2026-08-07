package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

final class ProcessRunnerSupport {

    boolean waitFor(Process process, long timeoutMillis) throws InterruptedException {
        if (timeoutMillis <= 0) {
            process.waitFor();
            return true;
        }
        return process.waitFor(timeoutMillis, TimeUnit.MILLISECONDS);
    }

    int exitCode(Process process, boolean timedOut) throws InterruptedException {
        if (!timedOut) {
            return process.exitValue();
        }
        process.destroyForcibly();
        process.waitFor();
        return 124;
    }

    String readOutput(Process process) {
        try {
            return new String(process.getInputStream().readAllBytes());
        } catch (IOException ex) {
            return "";
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=2247262d23e0849a6fc2f72d6d89268b9db2f0ae4174e9e4dbfbd28be79451ac
scope.0.id=Y2xhc3M6UHJvY2Vzc1J1bm5lclN1cHBvcnQjUHJvY2Vzc1J1bm5lclN1cHBvcnQ6MTM
scope.0.kind=class
scope.0.startLine=13
scope.0.endLine=39
scope.0.semanticHash=4dc1de07f8e59f186a60847c0f3cf515ccd57a0c9f8793b35f6fc14dc195fb27
scope.1.id=bWV0aG9kOlByb2Nlc3NSdW5uZXJTdXBwb3J0I2N0b3IoMCk6MTM
scope.1.kind=method
scope.1.startLine=1
scope.1.endLine=39
scope.1.semanticHash=67393fdc527191579fee4cc482bf9ddc7b4f9433e50b6811ac0362ad9a8f670c
scope.2.id=bWV0aG9kOlByb2Nlc3NSdW5uZXJTdXBwb3J0I2V4aXRDb2RlKDIpOjIz
scope.2.kind=method
scope.2.startLine=23
scope.2.endLine=30
scope.2.semanticHash=23ecc829c8ca3266527864e096ad51bc9757a6db6ed753d98e2553fca341003e
scope.3.id=bWV0aG9kOlByb2Nlc3NSdW5uZXJTdXBwb3J0I3JlYWRPdXRwdXQoMSk6MzI
scope.3.kind=method
scope.3.startLine=32
scope.3.endLine=38
scope.3.semanticHash=cfbfd1ff49c9583760680546d39271bc65619a702d0d5ee6373ead91309aee73
scope.4.id=bWV0aG9kOlByb2Nlc3NSdW5uZXJTdXBwb3J0I3dhaXRGb3IoMik6MTU
scope.4.kind=method
scope.4.startLine=15
scope.4.endLine=21
scope.4.semanticHash=990e0f072318963a297bdb79537f0383f51bf7ba1104c1887ffc7948aed3313d
*/
