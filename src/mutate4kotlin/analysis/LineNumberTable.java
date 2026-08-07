package mutate4kotlin.analysis;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.manifest.*;

final class LineNumberTable {

    private final String source;

    LineNumberTable(String source) {
        this.source = source;
    }

    int lineNumber(int index) {
        int line = 1;
        for (int i = 0; i < index; i++) {
            if (source.charAt(i) == '\n') {
                line++;
            }
        }
        return line;
    }

    int skipWhitespace(int index) {
        int current = index;
        while (current < source.length() && Character.isWhitespace(source.charAt(current))) {
            current++;
        }
        return current;
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=718e29cdd8ba44f7f27aa24ce060e3814eee2d2b31917237f4a2eb90c203b2b7
scope.0.id=Y2xhc3M6TGluZU51bWJlclRhYmxlI0xpbmVOdW1iZXJUYWJsZToxMQ
scope.0.kind=class
scope.0.startLine=11
scope.0.endLine=36
scope.0.semanticHash=415de4330d8eb32bdd008e30b2c70041d6a10b792137f38e2cafb25095c9ef9f
scope.1.id=ZmllbGQ6TGluZU51bWJlclRhYmxlI3NvdXJjZToxMw
scope.1.kind=field
scope.1.startLine=13
scope.1.endLine=13
scope.1.semanticHash=97d0b5d76eb96c0b49baebabd2d8302b3f93dd5e9b34c3bf876e0156e47dafb5
scope.2.id=bWV0aG9kOkxpbmVOdW1iZXJUYWJsZSNjdG9yKDEpOjE1
scope.2.kind=method
scope.2.startLine=15
scope.2.endLine=17
scope.2.semanticHash=1d35d023a1ca63e27614191b671746bf7030a75d04c65212c7b7d16c8492f2ab
scope.3.id=bWV0aG9kOkxpbmVOdW1iZXJUYWJsZSNsaW5lTnVtYmVyKDEpOjE5
scope.3.kind=method
scope.3.startLine=19
scope.3.endLine=27
scope.3.semanticHash=a506ade7bd4dc7e185dc61f210a528aec74b678c4d3875b8b4748d168d8bbd21
scope.4.id=bWV0aG9kOkxpbmVOdW1iZXJUYWJsZSNza2lwV2hpdGVzcGFjZSgxKToyOQ
scope.4.kind=method
scope.4.startLine=29
scope.4.endLine=35
scope.4.semanticHash=3224fa9074d7bd43a4a990ead1bfc2ad7d736e1cd879587a4057b3fd321649aa
*/
