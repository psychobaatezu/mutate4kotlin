package mutate4kotlin.manifest;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class ManifestParser {

    DifferentialManifest parse(String body) {
        Map<Integer, Map<String, String>> scopes = new LinkedHashMap<>();
        int version = 1;
        String moduleHash = "";
        for (String line : body.split("\n")) {
            if (!line.isBlank()) {
                readScopeLine(scopes, line);
                if (line.startsWith("version=")) {
                    version = Integer.parseInt(line.substring("version=".length()));
                } else if (line.startsWith("moduleHash=")) {
                    moduleHash = line.substring("moduleHash=".length());
                }
            }
        }
        List<MutationScope> parsedScopes = scopes.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> toScope(entry.getValue()))
                .toList();
        return new DifferentialManifest(version, moduleHash, parsedScopes);
    }

    private void readScopeLine(Map<Integer, Map<String, String>> scopes, String line) {
        int separator = line.indexOf('=');
        if (separator < 0) {
            return;
        }
        String key = line.substring(0, separator);
        if (!key.startsWith("scope.")) {
            return;
        }
        String[] parts = key.split("\\.");
        if (parts.length != 3) {
            return;
        }
        int index = Integer.parseInt(parts[1]);
        scopes.computeIfAbsent(index, ignored -> new LinkedHashMap<>()).put(parts[2], line.substring(separator + 1));
    }

    private MutationScope toScope(Map<String, String> values) {
        return new MutationScope(
                ManifestValueCodec.decode(values.get("id")),
                values.get("kind"),
                Integer.parseInt(values.get("startLine")),
                Integer.parseInt(values.get("endLine")),
                values.get("semanticHash")
        );
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=a0f44aaf46e054f4b3d68a69b12b0aec8f04227bf3a1ac4746ee678cf3162cbb
scope.0.id=Y2xhc3M6TWFuaWZlc3RQYXJzZXIjTWFuaWZlc3RQYXJzZXI6MTQ
scope.0.kind=class
scope.0.startLine=14
scope.0.endLine=63
scope.0.semanticHash=89e0ba409245286d618773797b9f34aa1b6fc210eb3ee3572c39fa8c8a23a443
scope.1.id=bWV0aG9kOk1hbmlmZXN0UGFyc2VyI2N0b3IoMCk6MTQ
scope.1.kind=method
scope.1.startLine=1
scope.1.endLine=63
scope.1.semanticHash=464a12db3ca72bb5c9025094ff5634b87301e474748d8f20c60fdcad5716cd1a
scope.2.id=bWV0aG9kOk1hbmlmZXN0UGFyc2VyI3BhcnNlKDEpOjE2
scope.2.kind=method
scope.2.startLine=16
scope.2.endLine=35
scope.2.semanticHash=705bb027de0aceec8f8996ace3c9b1b32a53a32f1c87e92776efba4b31925f2a
scope.3.id=bWV0aG9kOk1hbmlmZXN0UGFyc2VyI3JlYWRTY29wZUxpbmUoMik6Mzc
scope.3.kind=method
scope.3.startLine=37
scope.3.endLine=52
scope.3.semanticHash=9aedbd0887a115dc0bdb80efa93c6ffa52c8868c184182ab9c3e1922a41f8bd4
scope.4.id=bWV0aG9kOk1hbmlmZXN0UGFyc2VyI3RvU2NvcGUoMSk6NTQ
scope.4.kind=method
scope.4.startLine=54
scope.4.endLine=62
scope.4.semanticHash=45d21ab3c9b05e94703fc8a02f5d6b223637b09b1970789c04f93117218b0dd3
*/
