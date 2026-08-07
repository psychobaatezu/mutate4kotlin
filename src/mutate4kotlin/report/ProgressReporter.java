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

public interface ProgressReporter {

    void baselineStarting(Path moduleRoot);

    void baselineFinished(TestRun baseline);

    void runStarting(int totalMutations, int workerCount);

    void mutationStarting(int workerIndex, MutationJob job);

    void mutationFinished(int workerIndex, MutationResult result);
}

/* mutate4kotlin-manifest
version=1
moduleHash=5d976d59de50c58060320395833506089f85b26d857a4f19865586e5020886e4
scope.0.id=Y2xhc3M6UHJvZ3Jlc3NSZXBvcnRlciNQcm9ncmVzc1JlcG9ydGVyOjE3
scope.0.kind=class
scope.0.startLine=17
scope.0.endLine=28
scope.0.semanticHash=b95921b34249834c7db712e0561107f31868e10dbd846842db6881ec9fec2684
scope.1.id=bWV0aG9kOlByb2dyZXNzUmVwb3J0ZXIjYmFzZWxpbmVGaW5pc2hlZCgxKToyMQ
scope.1.kind=method
scope.1.startLine=21
scope.1.endLine=21
scope.1.semanticHash=80560553836df885fcbbd9d8c1777b7b766d8011f248387c609b777d0643eae0
scope.2.id=bWV0aG9kOlByb2dyZXNzUmVwb3J0ZXIjYmFzZWxpbmVTdGFydGluZygxKToxOQ
scope.2.kind=method
scope.2.startLine=19
scope.2.endLine=19
scope.2.semanticHash=b936f0be4c958289892fb75fdcfc49f25e7857709de7a8630119e30a550415c2
scope.3.id=bWV0aG9kOlByb2dyZXNzUmVwb3J0ZXIjbXV0YXRpb25GaW5pc2hlZCgyKToyNw
scope.3.kind=method
scope.3.startLine=27
scope.3.endLine=27
scope.3.semanticHash=57e3bc02e4e25981200c63768791aefeb06237a8b649bf7eff1f9fc2a3601ade
scope.4.id=bWV0aG9kOlByb2dyZXNzUmVwb3J0ZXIjbXV0YXRpb25TdGFydGluZygyKToyNQ
scope.4.kind=method
scope.4.startLine=25
scope.4.endLine=25
scope.4.semanticHash=136eb90a01b33c98435a19f5249252654c495a2e9be3ade36fd1f7ba0e726368
scope.5.id=bWV0aG9kOlByb2dyZXNzUmVwb3J0ZXIjcnVuU3RhcnRpbmcoMik6MjM
scope.5.kind=method
scope.5.startLine=23
scope.5.endLine=23
scope.5.semanticHash=2548ee83185921368ba06dee5292f9fba0d2addb84148c94a7c2527e4700d787
*/
