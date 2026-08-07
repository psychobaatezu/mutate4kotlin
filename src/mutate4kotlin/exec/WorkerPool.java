package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.util.List;

public interface WorkerPool extends AutoCloseable {

    List<MutationResult> runAll(List<MutationJob> jobs) throws Exception;
}

/* mutate4kotlin-manifest
version=1
moduleHash=1da48140d6fd6ff9911c01af344741945ca55d76744f8b02694a10d2c7f5180a
scope.0.id=Y2xhc3M6V29ya2VyUG9vbCNXb3JrZXJQb29sOjEy
scope.0.kind=class
scope.0.startLine=12
scope.0.endLine=15
scope.0.semanticHash=075e4dc417f1c517c336382ec84f7babb21d429586f810f9a5ada2650f3a5817
scope.1.id=bWV0aG9kOldvcmtlclBvb2wjcnVuQWxsKDEpOjE0
scope.1.kind=method
scope.1.startLine=14
scope.1.endLine=14
scope.1.semanticHash=50d21d02a652681b64b10422fb63af3f7e0fee7cf050cb23a4a33562681c7cb8
*/
