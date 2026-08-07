package mutate4kotlin.manifest;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public final class ManifestSupport {

    private final ManifestBoundary boundary = new ManifestBoundary();
    private final ManifestCodec codec = new ManifestCodec();
    private final ManifestHashing hashing = new ManifestHashing();
    public Optional<DifferentialManifest> read(Path sourceFile) throws Exception {
        String raw = Files.readString(sourceFile);
        int start = boundary.startIndex(raw);
        if (start < 0) {
            return Optional.empty();
        }
        int end = raw.indexOf(ManifestBoundary.END, start);
        if (end < 0) {
            return Optional.empty();
        }
        String body = raw.substring(start + ManifestBoundary.START.length(), end).trim();
        return Optional.of(codec.parse(body));
    }
    public String stripManifest(String rawSource) {
        int start = boundary.startIndex(rawSource);
        if (start < 0) {
            return rawSource;
        }
        return rawSource.substring(0, start).stripTrailing() + "\n";
    }
    public void write(Path sourceFile, String sourceWithoutManifest, DifferentialManifest manifest) throws Exception {
        String updated = sourceWithoutManifest.stripTrailing() + "\n\n"
                + codec.serialize(manifest, ManifestBoundary.START, ManifestBoundary.END);
        Files.writeString(sourceFile, updated);
    }
    public String hashScopes(List<MutationScope> scopes) {
        return hashing.hashScopes(scopes);
    }
    public String hash(String text) {
        return hashing.hash(text);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=d7e203c047500c0a872488c21161457400b5500721954f1cffd1153635883a1e
scope.0.id=Y2xhc3M6TWFuaWZlc3RTdXBwb3J0I01hbmlmZXN0U3VwcG9ydDoxNQ
scope.0.kind=class
scope.0.startLine=15
scope.0.endLine=51
scope.0.semanticHash=d463909fe6bef70cf6031646f4b417b415d760948d6c4b0516a4ea93e0bc3879
scope.1.id=ZmllbGQ6TWFuaWZlc3RTdXBwb3J0I2JvdW5kYXJ5OjE3
scope.1.kind=field
scope.1.startLine=17
scope.1.endLine=17
scope.1.semanticHash=69a92f5d0290833d99eaa6d46e401be707f53d45f254c188c940dabaa120871a
scope.2.id=ZmllbGQ6TWFuaWZlc3RTdXBwb3J0I2NvZGVjOjE4
scope.2.kind=field
scope.2.startLine=18
scope.2.endLine=18
scope.2.semanticHash=1ad8888122c70f709f904a4de72341e7333163df8bfebb1860e8beb0538e37ad
scope.3.id=ZmllbGQ6TWFuaWZlc3RTdXBwb3J0I2hhc2hpbmc6MTk
scope.3.kind=field
scope.3.startLine=19
scope.3.endLine=19
scope.3.semanticHash=07f8191220f8363c819c701d211afa75214cc651d7b500bea887a226a63a65d0
scope.4.id=bWV0aG9kOk1hbmlmZXN0U3VwcG9ydCNjdG9yKDApOjE1
scope.4.kind=method
scope.4.startLine=1
scope.4.endLine=51
scope.4.semanticHash=e6224bfa9f032220a16d7330e7b62741bef04efee3cea31187cbc6424317efe6
scope.5.id=bWV0aG9kOk1hbmlmZXN0U3VwcG9ydCNoYXNoKDEpOjQ4
scope.5.kind=method
scope.5.startLine=48
scope.5.endLine=50
scope.5.semanticHash=bb3d0dd953462247f6b97e1c1f0a0a6625367352516531915a3054f2d98ac1d7
scope.6.id=bWV0aG9kOk1hbmlmZXN0U3VwcG9ydCNoYXNoU2NvcGVzKDEpOjQ1
scope.6.kind=method
scope.6.startLine=45
scope.6.endLine=47
scope.6.semanticHash=5685174148ad12fdd79bf84b294c6e9d85205b011f07786f1abb2eb42fbb0f5e
scope.7.id=bWV0aG9kOk1hbmlmZXN0U3VwcG9ydCNyZWFkKDEpOjIw
scope.7.kind=method
scope.7.startLine=20
scope.7.endLine=32
scope.7.semanticHash=0d84f3c572c19934e75664c9e69d65fbfbf49c931a6d463996f6701260fe7189
scope.8.id=bWV0aG9kOk1hbmlmZXN0U3VwcG9ydCNzdHJpcE1hbmlmZXN0KDEpOjMz
scope.8.kind=method
scope.8.startLine=33
scope.8.endLine=39
scope.8.semanticHash=51e3b72a8ffdc31cc6535d6b9ee7b8099ff1da456df7e5a69c48426f6112df6f
scope.9.id=bWV0aG9kOk1hbmlmZXN0U3VwcG9ydCN3cml0ZSgzKTo0MA
scope.9.kind=method
scope.9.startLine=40
scope.9.endLine=44
scope.9.semanticHash=fe5356a86c7262b087798b50da89278db155a4a235c627789573f7461cb8c2ae
*/
