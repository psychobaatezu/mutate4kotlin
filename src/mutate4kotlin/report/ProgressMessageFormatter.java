package mutate4kotlin.report;

import mutate4kotlin.model.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import java.nio.file.Path;

final class ProgressMessageFormatter {

    String baselineStarting(Path moduleRoot) {
        return "Baseline starting for %s%n".formatted(moduleRoot);
    }

    String baselineFinished(TestRun baseline) {
        return "Baseline finished: exit=%d timedOut=%s duration=%d ms%n"
                .formatted(baseline.exitCode(), baseline.timedOut(), baseline.durationMillis());
    }

    String runStarting(int totalMutations, int workerCount) {
        return "Running %d mutations with %d workers.%n".formatted(totalMutations, workerCount);
    }

    String mutationStarting(int workerIndex, MutationJob job) {
        return "Worker %d starting %d/%d: %s:%d %s%n".formatted(
                workerIndex, job.order() + 1, job.totalJobs(), job.site().file(), job.site().lineNumber(), job.site().description());
    }

    String mutationFinished(int workerIndex, MutationResult result) {
        return "Worker %d finished %d/%d: %s %s:%d%n".formatted(
                workerIndex, result.order() + 1, result.totalJobs(),
                result.killed() ? "KILLED" : "SURVIVED", result.site().file(), result.site().lineNumber());
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=bcd32b9910207634b4c551caebb3aa8319a000e352e6482e197ffc9e2522a74b
scope.0.id=Y2xhc3M6UHJvZ3Jlc3NNZXNzYWdlRm9ybWF0dGVyI1Byb2dyZXNzTWVzc2FnZUZvcm1hdHRlcjoxNw
scope.0.kind=class
scope.0.startLine=17
scope.0.endLine=42
scope.0.semanticHash=d1a00416f77546caea60f80d98dad5abbffb70265d95d9e813258fc877fefc03
scope.1.id=bWV0aG9kOlByb2dyZXNzTWVzc2FnZUZvcm1hdHRlciNiYXNlbGluZUZpbmlzaGVkKDEpOjIz
scope.1.kind=method
scope.1.startLine=23
scope.1.endLine=26
scope.1.semanticHash=055f7bd20b2333b1eeb80bf4cee510b65bb5b4d2a4a53572bc06605a0ead9c17
scope.2.id=bWV0aG9kOlByb2dyZXNzTWVzc2FnZUZvcm1hdHRlciNiYXNlbGluZVN0YXJ0aW5nKDEpOjE5
scope.2.kind=method
scope.2.startLine=19
scope.2.endLine=21
scope.2.semanticHash=c6c1889158b6aa71d4fc920f62e815610dc9d690edccd1e586cd979333bbf937
scope.3.id=bWV0aG9kOlByb2dyZXNzTWVzc2FnZUZvcm1hdHRlciNjdG9yKDApOjE3
scope.3.kind=method
scope.3.startLine=1
scope.3.endLine=42
scope.3.semanticHash=a95afd3bb503478cb37dab678ea3692e1da506bf30da8dbdd9650f316d5c7f27
scope.4.id=bWV0aG9kOlByb2dyZXNzTWVzc2FnZUZvcm1hdHRlciNtdXRhdGlvbkZpbmlzaGVkKDIpOjM3
scope.4.kind=method
scope.4.startLine=37
scope.4.endLine=41
scope.4.semanticHash=8f38d46955eb53ff771aa264146708dfcdbb8dcc82c6b01c6eedee5b28d234ff
scope.5.id=bWV0aG9kOlByb2dyZXNzTWVzc2FnZUZvcm1hdHRlciNtdXRhdGlvblN0YXJ0aW5nKDIpOjMy
scope.5.kind=method
scope.5.startLine=32
scope.5.endLine=35
scope.5.semanticHash=74f29567c1a97ad8e14003930fdb8e64f155f1f3023bcd869a98c2ecb294e00e
scope.6.id=bWV0aG9kOlByb2dyZXNzTWVzc2FnZUZvcm1hdHRlciNydW5TdGFydGluZygyKToyOA
scope.6.kind=method
scope.6.startLine=28
scope.6.endLine=30
scope.6.semanticHash=1d3e2d2732cf931376024eaf9d0bb354faef6eea5e2f759ba8839c56d9b70e83
*/
