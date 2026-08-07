package mutate4kotlin.analysis;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.manifest.*;

import com.sun.source.tree.ExpressionTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

final class TreeTypePredicates {

    private final Trees trees;

    TreeTypePredicates(Trees trees) {
        this.trees = trees;
    }

    boolean isNumeric(TreePath path) {
        TypeMirror type = trees.getTypeMirror(path);
        return type != null && type.getKind().isPrimitive() && type.getKind() != TypeKind.BOOLEAN;
    }

    boolean isReference(TreePath parentPath, ExpressionTree expression) {
        TypeMirror type = trees.getTypeMirror(new TreePath(parentPath, expression));
        return type != null
                && type.getKind() != TypeKind.ERROR
                && type.getKind() != TypeKind.VOID
                && !type.getKind().isPrimitive();
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=ebe80281e191ede3a0753bac0e17a649aab29a9e2a05df54acd4d5c220ccd48b
scope.0.id=Y2xhc3M6VHJlZVR5cGVQcmVkaWNhdGVzI1RyZWVUeXBlUHJlZGljYXRlczoxOA
scope.0.kind=class
scope.0.startLine=18
scope.0.endLine=38
scope.0.semanticHash=75fa92035c2b3de6bfffb5b0c035557e9a2d38209e9336acf1318cc5aabef944
scope.1.id=ZmllbGQ6VHJlZVR5cGVQcmVkaWNhdGVzI3RyZWVzOjIw
scope.1.kind=field
scope.1.startLine=20
scope.1.endLine=20
scope.1.semanticHash=9d745a8b082cf229bd6c9d2f33c79006a3ea76b6e039d0e7fce733607b392d9e
scope.2.id=bWV0aG9kOlRyZWVUeXBlUHJlZGljYXRlcyNjdG9yKDEpOjIy
scope.2.kind=method
scope.2.startLine=22
scope.2.endLine=24
scope.2.semanticHash=5aa2caafa9b27d1015d6dcec8f40a8730ecada245a271df9e96ec37400c10169
scope.3.id=bWV0aG9kOlRyZWVUeXBlUHJlZGljYXRlcyNpc051bWVyaWMoMSk6MjY
scope.3.kind=method
scope.3.startLine=26
scope.3.endLine=29
scope.3.semanticHash=cfe468f7e924be17404c280efe40b9686d150232d83b288d4d4d4e540d9d5650
scope.4.id=bWV0aG9kOlRyZWVUeXBlUHJlZGljYXRlcyNpc1JlZmVyZW5jZSgyKTozMQ
scope.4.kind=method
scope.4.startLine=31
scope.4.endLine=37
scope.4.semanticHash=2e5c0ee0c7c4cf698c8a0325e7f5d1f4bdf19e69956760faf3efa411789ca80f
*/
