package mutate4kotlin.model;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

public record CoverageRun(TestRun baseline, CoverageReport report, boolean reused, boolean reportAvailable) {
}

/* mutate4kotlin-manifest
version=1
moduleHash=d36e19c9af224a2a8d79a3a1d9e7bf44bb50a50044f6d54ca052aa25eaed8045
scope.0.id=Y2xhc3M6Q292ZXJhZ2VSdW4jQ292ZXJhZ2VSdW46MTY
scope.0.kind=class
scope.0.startLine=16
scope.0.endLine=17
scope.0.semanticHash=8d09be5a1b5fc94f40b8abb7fca868905fed6e352c7fe2021e017648c2998d50
scope.1.id=ZmllbGQ6Q292ZXJhZ2VSdW4jYmFzZWxpbmU6MTY
scope.1.kind=field
scope.1.startLine=16
scope.1.endLine=16
scope.1.semanticHash=fa42908ea8a39560f4d3e2a71f3b9616ac12a2a50160f0d9ffc623c132930a6e
scope.2.id=ZmllbGQ6Q292ZXJhZ2VSdW4jcmVwb3J0OjE2
scope.2.kind=field
scope.2.startLine=16
scope.2.endLine=16
scope.2.semanticHash=dc2336483aa1f963fe252a42af3c42ba79e3ce39ea8d06f14aa7261f3c7463f6
scope.3.id=ZmllbGQ6Q292ZXJhZ2VSdW4jcmVwb3J0QXZhaWxhYmxlOjE2
scope.3.kind=field
scope.3.startLine=16
scope.3.endLine=16
scope.3.semanticHash=26a135c82441962c092b66ed34521c65689ea72c5f449ad4820b465693ef3260
scope.4.id=ZmllbGQ6Q292ZXJhZ2VSdW4jcmV1c2VkOjE2
scope.4.kind=field
scope.4.startLine=16
scope.4.endLine=16
scope.4.semanticHash=9ecfbeb812c31623d320d22f8922ddd0c424aaa3e5caafff0ab1f1fa781624a5
scope.5.id=bWV0aG9kOkNvdmVyYWdlUnVuI2N0b3IoNCk6MTY
scope.5.kind=method
scope.5.startLine=1
scope.5.endLine=17
scope.5.semanticHash=366b94c5e22e81aed4cb75dc046583d08ce09156ce3788cae799cb8b760ccd50
*/
