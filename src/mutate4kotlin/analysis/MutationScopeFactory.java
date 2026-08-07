package mutate4kotlin.analysis;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.manifest.*;

import com.sun.source.tree.Tree;
import com.sun.source.util.Trees;

final class MutationScopeFactory {

    private final String source;
    private final com.sun.source.tree.CompilationUnitTree unit;
    private final Trees trees;
    private final LineNumberTable lineNumbers;
    private final ManifestSupport manifestSupport = new ManifestSupport();

    MutationScopeFactory(String source,
                         com.sun.source.tree.CompilationUnitTree unit,
                         Trees trees,
                         LineNumberTable lineNumbers) {
        this.source = source;
        this.unit = unit;
        this.trees = trees;
        this.lineNumbers = lineNumbers;
    }

    MutationScope create(String id, String kind, Tree node) {
        int start = (int) trees.getSourcePositions().getStartPosition(unit, node);
        int end = (int) trees.getSourcePositions().getEndPosition(unit, node);
        if (start < 0 || end <= start) {
            start = 0;
            end = source.length();
        }
        return new MutationScope(id, kind, lineNumbers.lineNumber(start),
                lineNumbers.lineNumber(Math.max(start, end - 1)),
                manifestSupport.hash(source.substring(start, end)));
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=d79558189d1ff6eca00f4636261f6a3efd1e10133ee96a1c4aebe67e02f43212
scope.0.id=Y2xhc3M6TXV0YXRpb25TY29wZUZhY3RvcnkjTXV0YXRpb25TY29wZUZhY3Rvcnk6MTQ
scope.0.kind=class
scope.0.startLine=14
scope.0.endLine=43
scope.0.semanticHash=421061b29c57918782921aaf95217936b544f27c1710439323ef74ec47db5dc9
scope.1.id=ZmllbGQ6TXV0YXRpb25TY29wZUZhY3RvcnkjbGluZU51bWJlcnM6MTk
scope.1.kind=field
scope.1.startLine=19
scope.1.endLine=19
scope.1.semanticHash=ef6ee9bcfe04f326ca652abe258253055be2a73bcc39bc022bee59dece653a7a
scope.2.id=ZmllbGQ6TXV0YXRpb25TY29wZUZhY3RvcnkjbWFuaWZlc3RTdXBwb3J0OjIw
scope.2.kind=field
scope.2.startLine=20
scope.2.endLine=20
scope.2.semanticHash=eb7aed4f5d767d2c4dc362fe2a437cf6397eed6b1274f9817ce9aa8747f5e4e3
scope.3.id=ZmllbGQ6TXV0YXRpb25TY29wZUZhY3Rvcnkjc291cmNlOjE2
scope.3.kind=field
scope.3.startLine=16
scope.3.endLine=16
scope.3.semanticHash=97d0b5d76eb96c0b49baebabd2d8302b3f93dd5e9b34c3bf876e0156e47dafb5
scope.4.id=ZmllbGQ6TXV0YXRpb25TY29wZUZhY3RvcnkjdHJlZXM6MTg
scope.4.kind=field
scope.4.startLine=18
scope.4.endLine=18
scope.4.semanticHash=9d745a8b082cf229bd6c9d2f33c79006a3ea76b6e039d0e7fce733607b392d9e
scope.5.id=ZmllbGQ6TXV0YXRpb25TY29wZUZhY3RvcnkjdW5pdDoxNw
scope.5.kind=field
scope.5.startLine=17
scope.5.endLine=17
scope.5.semanticHash=ac4af8e7d584e0642703e6338e5dc722bdacc1c2fe084a79d4ab19090bf87ee8
scope.6.id=bWV0aG9kOk11dGF0aW9uU2NvcGVGYWN0b3J5I2NyZWF0ZSgzKTozMg
scope.6.kind=method
scope.6.startLine=32
scope.6.endLine=42
scope.6.semanticHash=ea8ade54ab5566fbc298b05f940a94df64e623931180f50e5828b0d74be0cad3
scope.7.id=bWV0aG9kOk11dGF0aW9uU2NvcGVGYWN0b3J5I2N0b3IoNCk6MjI
scope.7.kind=method
scope.7.startLine=22
scope.7.endLine=30
scope.7.semanticHash=1eeba8f33f03563661b8e064cb88bee3696c2a1ed8f2108e4bb6f34c87ac2bf8
*/
