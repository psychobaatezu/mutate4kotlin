package mutate4kotlin.manifest;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

final class ManifestCodec {

    private final ManifestParser parser = new ManifestParser();
    private final ManifestSerializer serializer = new ManifestSerializer();

    DifferentialManifest parse(String body) {
        return parser.parse(body);
    }

    String serialize(DifferentialManifest manifest, String start, String end) {
        return serializer.serialize(manifest, start, end);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=f9440d0066dbaab5e6a035a1e640fc11379e6d23a3700af8f2e137a2b14e0fef
scope.0.id=Y2xhc3M6TWFuaWZlc3RDb2RlYyNNYW5pZmVzdENvZGVjOjEw
scope.0.kind=class
scope.0.startLine=10
scope.0.endLine=22
scope.0.semanticHash=ac28863418fc502e5d74f7343ab559220310e5d5dbd710ae530afcf84525b1bc
scope.1.id=ZmllbGQ6TWFuaWZlc3RDb2RlYyNwYXJzZXI6MTI
scope.1.kind=field
scope.1.startLine=12
scope.1.endLine=12
scope.1.semanticHash=03ccfcc9d6ac03c4dab98792232a006ab4af1f6ac773fcb4410fcc48916908d4
scope.2.id=ZmllbGQ6TWFuaWZlc3RDb2RlYyNzZXJpYWxpemVyOjEz
scope.2.kind=field
scope.2.startLine=13
scope.2.endLine=13
scope.2.semanticHash=3a2e869def9416706ad22421d73fca80f49c7f72fd40bc232d1b68073abe83a5
scope.3.id=bWV0aG9kOk1hbmlmZXN0Q29kZWMjY3RvcigwKToxMA
scope.3.kind=method
scope.3.startLine=1
scope.3.endLine=22
scope.3.semanticHash=6e9dd9ed5adb4a56c26dd5db4f9c02fcb345fac0a77f4b6c50b3d6bdbb9b33f6
scope.4.id=bWV0aG9kOk1hbmlmZXN0Q29kZWMjcGFyc2UoMSk6MTU
scope.4.kind=method
scope.4.startLine=15
scope.4.endLine=17
scope.4.semanticHash=6c9de769cf444ed7e0d1cb743449256207e090a106bb49efc2e289e70ee986c6
scope.5.id=bWV0aG9kOk1hbmlmZXN0Q29kZWMjc2VyaWFsaXplKDMpOjE5
scope.5.kind=method
scope.5.startLine=19
scope.5.endLine=21
scope.5.semanticHash=00c506fc603b98636f88406b99bc2c49095602cb25a680ba98447f1fa4df89b5
*/
