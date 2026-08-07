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

public record CoverageSite(String sourcePath, int lineNumber) {
}

/* mutate4kotlin-manifest
version=1
moduleHash=0f0226afd385752fd8301bba5494ab1c8de02a60c4a06c2c4589ce6769a5d733
scope.0.id=Y2xhc3M6Q292ZXJhZ2VTaXRlI0NvdmVyYWdlU2l0ZToxNg
scope.0.kind=class
scope.0.startLine=16
scope.0.endLine=17
scope.0.semanticHash=13af1fd4450f5db949b4654177fb1b930131013ad39b988c045aba9c7945855c
scope.1.id=ZmllbGQ6Q292ZXJhZ2VTaXRlI2xpbmVOdW1iZXI6MTY
scope.1.kind=field
scope.1.startLine=16
scope.1.endLine=16
scope.1.semanticHash=e2d9a8752ba497a35f15467e67a045cce4ec8df064acbc3b45310cac37061a40
scope.2.id=ZmllbGQ6Q292ZXJhZ2VTaXRlI3NvdXJjZVBhdGg6MTY
scope.2.kind=field
scope.2.startLine=16
scope.2.endLine=16
scope.2.semanticHash=326fe9d351d6e94a53ddb1ba86d31c0ac9f0a6d1399ec138880f37f7ff748078
scope.3.id=bWV0aG9kOkNvdmVyYWdlU2l0ZSNjdG9yKDIpOjE2
scope.3.kind=method
scope.3.startLine=1
scope.3.endLine=17
scope.3.semanticHash=06d53a675c88a8d2209b0eb228c97dda044c6e171892b96401440ca086fc7be7
*/
