package mutate4kotlin.manifest;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

final class ManifestSerializer {

    String serialize(DifferentialManifest manifest, String start, String end) {
        StringBuilder out = new StringBuilder();
        out.append(start);
        out.append("version=").append(manifest.version()).append('\n');
        out.append("moduleHash=").append(manifest.moduleHash()).append('\n');
        for (int i = 0; i < manifest.scopes().size(); i++) {
            appendScope(out, i, manifest.scopes().get(i));
        }
        out.append(end).append('\n');
        return out.toString();
    }

    private void appendScope(StringBuilder out, int index, MutationScope scope) {
        out.append("scope.").append(index).append(".id=").append(ManifestValueCodec.encode(scope.id())).append('\n');
        out.append("scope.").append(index).append(".kind=").append(scope.kind()).append('\n');
        out.append("scope.").append(index).append(".startLine=").append(scope.startLine()).append('\n');
        out.append("scope.").append(index).append(".endLine=").append(scope.endLine()).append('\n');
        out.append("scope.").append(index).append(".semanticHash=").append(scope.semanticHash()).append('\n');
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=06c0811e022c2c876c8680f4821a5124182c6723616dffa785096546e341590d
scope.0.id=Y2xhc3M6TWFuaWZlc3RTZXJpYWxpemVyI01hbmlmZXN0U2VyaWFsaXplcjoxMA
scope.0.kind=class
scope.0.startLine=10
scope.0.endLine=31
scope.0.semanticHash=76e98d70f11a2b122a3087a3952fbd64296366f18fb63603100f0602db209f08
scope.1.id=bWV0aG9kOk1hbmlmZXN0U2VyaWFsaXplciNhcHBlbmRTY29wZSgzKToyNA
scope.1.kind=method
scope.1.startLine=24
scope.1.endLine=30
scope.1.semanticHash=64edc1f15c60da77f6e2b14bbd5651417468b4e8420654098dbe6f2ce9b52594
scope.2.id=bWV0aG9kOk1hbmlmZXN0U2VyaWFsaXplciNjdG9yKDApOjEw
scope.2.kind=method
scope.2.startLine=1
scope.2.endLine=31
scope.2.semanticHash=3a2994a395cf665c2d722ad37c3be0459c1126933e5cfc7294b4f7b9c230d9ef
scope.3.id=bWV0aG9kOk1hbmlmZXN0U2VyaWFsaXplciNzZXJpYWxpemUoMyk6MTI
scope.3.kind=method
scope.3.startLine=12
scope.3.endLine=22
scope.3.semanticHash=d295a17751a6ca8f4c96b74212970acf8a08abb945cf13678497f4225a3a3c96
*/
