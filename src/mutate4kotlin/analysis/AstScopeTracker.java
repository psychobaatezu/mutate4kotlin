package mutate4kotlin.analysis;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.manifest.*;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

final class AstScopeTracker {

    private final Path file;
    private final String source;
    private final com.sun.source.tree.CompilationUnitTree unit;
    private final Trees trees;
    private final LineNumberTable lineNumbers;
    private final MutationScopeFactory scopeFactory;
    private final List<MutationScope> scopes = new ArrayList<>();
    private final Deque<String> classNames = new ArrayDeque<>();

    AstScopeTracker(Path file, String source, com.sun.source.tree.CompilationUnitTree unit, Trees trees, LineNumberTable lineNumbers) {
        this.file = file;
        this.source = source;
        this.unit = unit;
        this.trees = trees;
        this.lineNumbers = lineNumbers;
        this.scopeFactory = new MutationScopeFactory(source, unit, trees, lineNumbers);
    }

    List<MutationScope> scopes() {
        return List.copyOf(scopes);
    }

    void enterClass(ClassTree node) {
        classNames.push(node.getSimpleName().toString());
        addScope(classScope(node));
    }

    void exitClass() {
        classNames.pop();
    }

    void visitMethod(MethodTree node) {
        addScope(methodScope(node));
    }

    void visitVariable(TreePath path, VariableTree node) {
        if (isField(path)) {
            addScope(fieldScope(node));
        }
    }

    ScopeRef currentScope(TreePath path) {
        while (path != null) {
            Tree leaf = path.getLeaf();
            if (leaf instanceof MethodTree method) {
                MutationScope scope = methodScope(method);
                addScope(scope);
                return ScopeRef.from(scope);
            }
            if (leaf instanceof VariableTree variable && isField(path)) {
                MutationScope scope = fieldScope(variable);
                addScope(scope);
                return ScopeRef.from(scope);
            }
            if (leaf instanceof ClassTree type) {
                MutationScope scope = classScope(type);
                addScope(scope);
                return ScopeRef.from(scope);
            }
            path = path.getParentPath();
        }
        return new ScopeRef("file:" + file.getFileName(), "file", 1, lineNumbers.lineNumber(source.length()));
    }

    private void addScope(MutationScope scope) {
        if (scopes.stream().noneMatch(existing -> existing.id().equals(scope.id()))) {
            scopes.add(scope);
        }
    }

    private MutationScope classScope(ClassTree node) {
        return scope(id("class", node.getSimpleName().toString(), node), "class", node);
    }

    private MutationScope methodScope(MethodTree node) {
        String name = node.getName().contentEquals("<init>") ? "ctor" : node.getName().toString();
        return scope(id("method", name + "(" + node.getParameters().size() + ")", node), "method", node);
    }

    private MutationScope fieldScope(VariableTree node) {
        return scope(id("field", node.getName().toString(), node), "field", node);
    }

    private MutationScope scope(String id, String kind, Tree node) {
        int start = (int) trees.getSourcePositions().getStartPosition(unit, node);
        return scopeFactory.create(id, kind, node);
    }

    private String id(String kind, String detail, Tree node) {
        int start = (int) trees.getSourcePositions().getStartPosition(unit, node);
        List<String> names = new ArrayList<>(classNames);
        java.util.Collections.reverse(names);
        String prefix = String.join(".", names);
        if (!prefix.isBlank()) {
            prefix += "#";
        }
        return kind + ":" + prefix + detail + ":" + lineNumbers.lineNumber(Math.max(start, 0));
    }

    private boolean isField(TreePath path) {
        TreePath parent = path.getParentPath();
        return parent != null && parent.getLeaf() instanceof ClassTree;
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=666cf71fe3cc680df203aa4a9eb19c4618a2542f076e2ec35ae175a90513a5bf
scope.0.id=Y2xhc3M6QXN0U2NvcGVUcmFja2VyI0FzdFNjb3BlVHJhY2tlcjoyNA
scope.0.kind=class
scope.0.startLine=24
scope.0.endLine=129
scope.0.semanticHash=632a020578c3defdf5aa5c034bbec88c85422630369c20ba9ea3ef6c38023394
scope.1.id=ZmllbGQ6QXN0U2NvcGVUcmFja2VyI2NsYXNzTmFtZXM6MzM
scope.1.kind=field
scope.1.startLine=33
scope.1.endLine=33
scope.1.semanticHash=daff98cd6fa5327542e39a39c2f99b26c95ebbcb3df54b14b8b4c6d0eef08abe
scope.2.id=ZmllbGQ6QXN0U2NvcGVUcmFja2VyI2ZpbGU6MjY
scope.2.kind=field
scope.2.startLine=26
scope.2.endLine=26
scope.2.semanticHash=9042a4679166911f8012023a394a64d0f9c2541fc9520ae0147f655250891eb5
scope.3.id=ZmllbGQ6QXN0U2NvcGVUcmFja2VyI2xpbmVOdW1iZXJzOjMw
scope.3.kind=field
scope.3.startLine=30
scope.3.endLine=30
scope.3.semanticHash=ef6ee9bcfe04f326ca652abe258253055be2a73bcc39bc022bee59dece653a7a
scope.4.id=ZmllbGQ6QXN0U2NvcGVUcmFja2VyI3Njb3BlRmFjdG9yeTozMQ
scope.4.kind=field
scope.4.startLine=31
scope.4.endLine=31
scope.4.semanticHash=4563be7b224edbadacedddc51825c3842c741026133a3f1e1b9f3bbb17c68f56
scope.5.id=ZmllbGQ6QXN0U2NvcGVUcmFja2VyI3Njb3BlczozMg
scope.5.kind=field
scope.5.startLine=32
scope.5.endLine=32
scope.5.semanticHash=abad8a7bfec753598533e3a6890ab8d8e55c457f360a34acb37f2786c106b4df
scope.6.id=ZmllbGQ6QXN0U2NvcGVUcmFja2VyI3NvdXJjZToyNw
scope.6.kind=field
scope.6.startLine=27
scope.6.endLine=27
scope.6.semanticHash=97d0b5d76eb96c0b49baebabd2d8302b3f93dd5e9b34c3bf876e0156e47dafb5
scope.7.id=ZmllbGQ6QXN0U2NvcGVUcmFja2VyI3RyZWVzOjI5
scope.7.kind=field
scope.7.startLine=29
scope.7.endLine=29
scope.7.semanticHash=9d745a8b082cf229bd6c9d2f33c79006a3ea76b6e039d0e7fce733607b392d9e
scope.8.id=ZmllbGQ6QXN0U2NvcGVUcmFja2VyI3VuaXQ6Mjg
scope.8.kind=field
scope.8.startLine=28
scope.8.endLine=28
scope.8.semanticHash=ac4af8e7d584e0642703e6338e5dc722bdacc1c2fe084a79d4ab19090bf87ee8
scope.9.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNhZGRTY29wZSgxKTo5MA
scope.9.kind=method
scope.9.startLine=90
scope.9.endLine=94
scope.9.semanticHash=205023f7558b4d73e6b0293024bdbff016f1324312f5cdfa3a88a2cca0264e5c
scope.10.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNjbGFzc1Njb3BlKDEpOjk2
scope.10.kind=method
scope.10.startLine=96
scope.10.endLine=98
scope.10.semanticHash=930636004243a570c651a76033516c5698f0492c8e153405c48d72e1b1aa7814
scope.11.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNjdG9yKDUpOjM1
scope.11.kind=method
scope.11.startLine=35
scope.11.endLine=42
scope.11.semanticHash=1660482daeb48d9246e95cf792bd149341e21a159dcc850b6ee74ebcbd7f89d1
scope.12.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNjdXJyZW50U2NvcGUoMSk6Njc
scope.12.kind=method
scope.12.startLine=67
scope.12.endLine=88
scope.12.semanticHash=f25adbc6de4b3b111c3abedd0f3620f69c9dadae14626af7afb0997a87910e70
scope.13.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNlbnRlckNsYXNzKDEpOjQ4
scope.13.kind=method
scope.13.startLine=48
scope.13.endLine=51
scope.13.semanticHash=43facb6d0d757dbc1f7fdec3ac99b457c45cb3c617a2c104fdbb00ab3f1ae781
scope.14.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNleGl0Q2xhc3MoMCk6NTM
scope.14.kind=method
scope.14.startLine=53
scope.14.endLine=55
scope.14.semanticHash=9c4cb456ef6c651befba21309c821138c47e54d75dd25276e1a9ebb306ee844d
scope.15.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNmaWVsZFNjb3BlKDEpOjEwNQ
scope.15.kind=method
scope.15.startLine=105
scope.15.endLine=107
scope.15.semanticHash=413f28751d622565630bba648f2b439ad3f0a3d700f0ebd6d3a9bd8af914580d
scope.16.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNpZCgzKToxMTQ
scope.16.kind=method
scope.16.startLine=114
scope.16.endLine=123
scope.16.semanticHash=7c5f7dfa8093e051a6fa20cc6061d274965436bdd5b33da401624b22d743e624
scope.17.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNpc0ZpZWxkKDEpOjEyNQ
scope.17.kind=method
scope.17.startLine=125
scope.17.endLine=128
scope.17.semanticHash=e5e9a590760eb1a2f11d7df8c221734f47ab06823355f773b4b51f22e815fcfc
scope.18.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNtZXRob2RTY29wZSgxKToxMDA
scope.18.kind=method
scope.18.startLine=100
scope.18.endLine=103
scope.18.semanticHash=9c09b2946b89baa476720e36e87e1f44c761241c2ce6423f5ee05ab7e7d20745
scope.19.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNzY29wZSgzKToxMDk
scope.19.kind=method
scope.19.startLine=109
scope.19.endLine=112
scope.19.semanticHash=01c8f95c98c08f09010d65b779e4a079f0d036f172fc478773acdf41851fc2e2
scope.20.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciNzY29wZXMoMCk6NDQ
scope.20.kind=method
scope.20.startLine=44
scope.20.endLine=46
scope.20.semanticHash=36d42761d2d5e2d22934a3c8a5577640d1b24e0af5a69125ed0b1c37717c8871
scope.21.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciN2aXNpdE1ldGhvZCgxKTo1Nw
scope.21.kind=method
scope.21.startLine=57
scope.21.endLine=59
scope.21.semanticHash=da9027a5d3fbdb212a0acc6f07f68873fbddd53d47fa0c496ce42208b34a2e89
scope.22.id=bWV0aG9kOkFzdFNjb3BlVHJhY2tlciN2aXNpdFZhcmlhYmxlKDIpOjYx
scope.22.kind=method
scope.22.startLine=61
scope.22.endLine=65
scope.22.semanticHash=4a5ad524a511f2444419960e237b1cdf12af1adc14261ab9634ec19a54c95b6f
*/
