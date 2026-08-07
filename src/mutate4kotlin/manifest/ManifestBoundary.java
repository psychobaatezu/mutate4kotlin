package mutate4kotlin.manifest;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

final class ManifestBoundary {

    static final String START = "/* mutate4kotlin-manifest\n";
    static final String END = "*/";

    int startIndex(String raw) {
        int start = raw.lastIndexOf(START);
        if (start < 0) {
            return -1;
        }
        String tail = raw.substring(start);
        return tail.trim().endsWith(END) ? start : -1;
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=7ae43338aefcf420979cd546316c545aaa2edcfbdb66123a37ab3fa88a20bb2d
scope.0.id=Y2xhc3M6TWFuaWZlc3RCb3VuZGFyeSNNYW5pZmVzdEJvdW5kYXJ5OjEw
scope.0.kind=class
scope.0.startLine=10
scope.0.endLine=23
scope.0.semanticHash=4e17cb68c10060c90c75f7eabc9144f0498bb1b18e8cea333927a046c625f8fa
scope.1.id=ZmllbGQ6TWFuaWZlc3RCb3VuZGFyeSNFTkQ6MTM
scope.1.kind=field
scope.1.startLine=13
scope.1.endLine=13
scope.1.semanticHash=db26a960e69ded69c9ee459b513743790f9a63facc216e2718de3dc89cca2312
scope.2.id=ZmllbGQ6TWFuaWZlc3RCb3VuZGFyeSNTVEFSVDoxMg
scope.2.kind=field
scope.2.startLine=12
scope.2.endLine=12
scope.2.semanticHash=f017ccc48553a7937b9971620ecdc588b20ef44aa9fa9816bcebd004049e4441
scope.3.id=bWV0aG9kOk1hbmlmZXN0Qm91bmRhcnkjY3RvcigwKToxMA
scope.3.kind=method
scope.3.startLine=1
scope.3.endLine=23
scope.3.semanticHash=a2d95a0238edf0486a7a252cf27cca5906fc4fb262f66b97600e50e0e4932f15
scope.4.id=bWV0aG9kOk1hbmlmZXN0Qm91bmRhcnkjc3RhcnRJbmRleCgxKToxNQ
scope.4.kind=method
scope.4.startLine=15
scope.4.endLine=22
scope.4.semanticHash=cfc123afae969780fd00da4bd715c1702dc8ef328ab5979c6744ef6ddda65bdd
*/
