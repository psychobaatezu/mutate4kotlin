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

import java.util.List;

public record DifferentialManifest(int version, String moduleHash, List<MutationScope> scopes) {
}

/* mutate4kotlin-manifest
version=1
moduleHash=ac6caa4250f9cee6b34c15a753259c118a3c96768c2b33e6eac745192419c2d1
scope.0.id=Y2xhc3M6RGlmZmVyZW50aWFsTWFuaWZlc3QjRGlmZmVyZW50aWFsTWFuaWZlc3Q6MTg
scope.0.kind=class
scope.0.startLine=18
scope.0.endLine=19
scope.0.semanticHash=cfb10659ae5b472ba333764ea53c81691741142279001676cf66410a5522ef11
scope.1.id=ZmllbGQ6RGlmZmVyZW50aWFsTWFuaWZlc3QjbW9kdWxlSGFzaDoxOA
scope.1.kind=field
scope.1.startLine=18
scope.1.endLine=18
scope.1.semanticHash=053274f7b8645a3d89c728e10be06932de6b8480352e50cfec8e178920e832d9
scope.2.id=ZmllbGQ6RGlmZmVyZW50aWFsTWFuaWZlc3Qjc2NvcGVzOjE4
scope.2.kind=field
scope.2.startLine=18
scope.2.endLine=18
scope.2.semanticHash=4a9e24cf881dede690f21f7e430f391a556e5ec7db5855c8bd55305b01e6a8ef
scope.3.id=ZmllbGQ6RGlmZmVyZW50aWFsTWFuaWZlc3QjdmVyc2lvbjoxOA
scope.3.kind=field
scope.3.startLine=18
scope.3.endLine=18
scope.3.semanticHash=a5a96aebf5c73e829dc56332b3d8426008ab1d500f8e7ebc188dd1da3dd7a444
scope.4.id=bWV0aG9kOkRpZmZlcmVudGlhbE1hbmlmZXN0I2N0b3IoMyk6MTg
scope.4.kind=method
scope.4.startLine=1
scope.4.endLine=19
scope.4.semanticHash=b9e65d1a19a10ca8ae8ef1a5295bf0c2827132d9eadad9e20e187d6b51027f57
*/
