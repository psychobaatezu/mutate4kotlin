package mutate4kotlin.manifest;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.List;

final class ManifestHashing {

    String hashScopes(List<MutationScope> scopes) {
        StringBuilder out = new StringBuilder();
        scopes.stream().sorted(Comparator.comparing(MutationScope::id))
                .forEach(scope -> out.append(scope.id()).append('|').append(scope.semanticHash()).append('\n'));
        return hash(out.toString());
    }

    String hash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte value : bytes) {
                hex.append(String.format("%02x", value));
            }
            return hex.toString();
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to hash manifest content", ex);
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=5d28ea50d712b4689fd1cadc5a3cd1006048e2f7d8c2d806efb0597f85a0d556
scope.0.id=Y2xhc3M6TWFuaWZlc3RIYXNoaW5nI01hbmlmZXN0SGFzaGluZzoxNQ
scope.0.kind=class
scope.0.startLine=15
scope.0.endLine=37
scope.0.semanticHash=832d577ae4f19250f0315b9920bf395520ac2002a66bef1bc37395cb6c6cfc3b
scope.1.id=bWV0aG9kOk1hbmlmZXN0SGFzaGluZyNjdG9yKDApOjE1
scope.1.kind=method
scope.1.startLine=1
scope.1.endLine=37
scope.1.semanticHash=985b84539ef7b1e4b20b5866fa3096da1206e12637602f09541300b4223d03b6
scope.2.id=bWV0aG9kOk1hbmlmZXN0SGFzaGluZyNoYXNoKDEpOjI0
scope.2.kind=method
scope.2.startLine=24
scope.2.endLine=36
scope.2.semanticHash=50ca1c72fb62f3e1fc4a2116f6ffcf302217e947800a7c665e564579336289f3
scope.3.id=bWV0aG9kOk1hbmlmZXN0SGFzaGluZyNoYXNoU2NvcGVzKDEpOjE3
scope.3.kind=method
scope.3.startLine=17
scope.3.endLine=22
scope.3.semanticHash=337b4c5a0ca5399ac61f602008edbc223c3ced494888ee596b9767414aaf9907
*/
