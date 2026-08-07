package mutate4kotlin.analysis;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.manifest.*;

import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.ReturnTree;
import com.sun.source.tree.UnaryTree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

final class AstMutationScanner extends TreePathScanner<Void, Void> {
    private final List<MutationSite> sites;
    private final AstScopeTracker scopeTracker;
    private final AstMutationSiteFactory siteFactory;

    AstMutationScanner(Path file, String source, CompilationUnitTree unit, Trees trees, List<MutationSite> sites) {
        this.sites = sites;
        LineNumberTable lineNumbers = new LineNumberTable(source);
        this.scopeTracker = new AstScopeTracker(file, source, unit, trees, lineNumbers);
        this.siteFactory = new AstMutationSiteFactory(file, source, unit, trees, lineNumbers, scopeTracker);
    }

    List<MutationScope> scopes() {
        return scopeTracker.scopes();
    }

    @Override
    public Void visitClass(ClassTree node, Void unused) {
        scopeTracker.enterClass(node);
        try {
            return super.visitClass(node, unused);
        } finally {
            scopeTracker.exitClass();
        }
    }

    @Override
    public Void visitMethod(com.sun.source.tree.MethodTree node, Void unused) {
        scopeTracker.visitMethod(node);
        return super.visitMethod(node, unused);
    }

    @Override
    public Void visitLiteral(LiteralTree node, Void unused) {
        MutationSite mutation = siteFactory.literal(getCurrentPath(), node);
        if (mutation != null) {
            sites.add(mutation);
        }
        return super.visitLiteral(node, unused);
    }

    @Override
    public Void visitBinary(BinaryTree node, Void unused) {
        MutationSite mutation = siteFactory.binary(getCurrentPath(), node);
        if (mutation != null) {
            sites.add(mutation);
        }
        return super.visitBinary(node, unused);
    }

    @Override
    public Void visitUnary(UnaryTree node, Void unused) {
        MutationSite mutation = siteFactory.unary(getCurrentPath(), node);
        if (mutation != null) {
            sites.add(mutation);
        }
        return super.visitUnary(node, unused);
    }

    @Override
    public Void visitReturn(ReturnTree node, Void unused) {
        addNullReplacement(node.getExpression());
        return super.visitReturn(node, unused);
    }

    @Override
    public Void visitVariable(VariableTree node, Void unused) {
        scopeTracker.visitVariable(getCurrentPath(), node);
        addNullReplacement(node.getInitializer());
        return super.visitVariable(node, unused);
    }

    @Override
    public Void visitAssignment(AssignmentTree node, Void unused) {
        addNullReplacement(node.getExpression());
        return super.visitAssignment(node, unused);
    }

    private void addNullReplacement(ExpressionTree expression) {
        MutationSite mutation = siteFactory.nullReplacement(getCurrentPath(), expression);
        if (mutation != null) {
            sites.add(mutation);
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=2f0bcf033301126a0d69b36e8ff014b3addba5a75f6b1be3ff9413a129a2a114
scope.0.id=Y2xhc3M6QXN0TXV0YXRpb25TY2FubmVyI0FzdE11dGF0aW9uU2Nhbm5lcjoyOA
scope.0.kind=class
scope.0.startLine=28
scope.0.endLine=112
scope.0.semanticHash=8599bdf245aec282c5f87787895229bf1c06f24ad180831a452382975b13345c
scope.1.id=ZmllbGQ6QXN0TXV0YXRpb25TY2FubmVyI3Njb3BlVHJhY2tlcjozMA
scope.1.kind=field
scope.1.startLine=30
scope.1.endLine=30
scope.1.semanticHash=981eb87cc60b755b1fb8c9b850a4a47ae987134bc0e67790140b222354df09a8
scope.2.id=ZmllbGQ6QXN0TXV0YXRpb25TY2FubmVyI3NpdGVGYWN0b3J5OjMx
scope.2.kind=field
scope.2.startLine=31
scope.2.endLine=31
scope.2.semanticHash=a12082247ba7aa0faba1b27f5926e4acaf0e65e5e94f9a9e4e4972397714d55f
scope.3.id=ZmllbGQ6QXN0TXV0YXRpb25TY2FubmVyI3NpdGVzOjI5
scope.3.kind=field
scope.3.startLine=29
scope.3.endLine=29
scope.3.semanticHash=4c37e4526fde01ed74f2cfe2ccd2614905126ab0ea693afdc95f8caa0f0036e3
scope.4.id=bWV0aG9kOkFzdE11dGF0aW9uU2Nhbm5lciNhZGROdWxsUmVwbGFjZW1lbnQoMSk6MTA2
scope.4.kind=method
scope.4.startLine=106
scope.4.endLine=111
scope.4.semanticHash=aba8b77527617e1d8838cb6693df1edaffdf35fc0388ee9fad8dba803b6b418b
scope.5.id=bWV0aG9kOkFzdE11dGF0aW9uU2Nhbm5lciNjdG9yKDUpOjMz
scope.5.kind=method
scope.5.startLine=33
scope.5.endLine=38
scope.5.semanticHash=62a27e08125912b3ac94afb68be13bee61bedecb6aa74335bae801d9868db82a
scope.6.id=bWV0aG9kOkFzdE11dGF0aW9uU2Nhbm5lciNzY29wZXMoMCk6NDA
scope.6.kind=method
scope.6.startLine=40
scope.6.endLine=42
scope.6.semanticHash=c54562779feb316e6001b69dee3f0d8b61fdbb11890f7e184b1a372aceb5ff3a
scope.7.id=bWV0aG9kOkFzdE11dGF0aW9uU2Nhbm5lciN2aXNpdEFzc2lnbm1lbnQoMik6MTAw
scope.7.kind=method
scope.7.startLine=100
scope.7.endLine=104
scope.7.semanticHash=9273a909f3c14e7ca98f82ec21ab8885d75a6ec24ff6956eea45c01c5fafb051
scope.8.id=bWV0aG9kOkFzdE11dGF0aW9uU2Nhbm5lciN2aXNpdEJpbmFyeSgyKTo2OQ
scope.8.kind=method
scope.8.startLine=69
scope.8.endLine=76
scope.8.semanticHash=69a014cfdce882b61d2f61ad01d95751e0784068b2ec5444ce934d6cf7668b74
scope.9.id=bWV0aG9kOkFzdE11dGF0aW9uU2Nhbm5lciN2aXNpdENsYXNzKDIpOjQ0
scope.9.kind=method
scope.9.startLine=44
scope.9.endLine=52
scope.9.semanticHash=f8f417e600b28d1d9b5a6ff806b43b840126fed0868f1c6d7f4cf545303fd342
scope.10.id=bWV0aG9kOkFzdE11dGF0aW9uU2Nhbm5lciN2aXNpdExpdGVyYWwoMik6NjA
scope.10.kind=method
scope.10.startLine=60
scope.10.endLine=67
scope.10.semanticHash=f80b315129f9ecc1af0e8ef1e86f75ef7991af2ce1fa8611f5efa802a69c6eb1
scope.11.id=bWV0aG9kOkFzdE11dGF0aW9uU2Nhbm5lciN2aXNpdE1ldGhvZCgyKTo1NA
scope.11.kind=method
scope.11.startLine=54
scope.11.endLine=58
scope.11.semanticHash=c8e0163737fe6dccd0a57933c8c6388c8fc265bac73ba33a92165f8a2965bfc1
scope.12.id=bWV0aG9kOkFzdE11dGF0aW9uU2Nhbm5lciN2aXNpdFJldHVybigyKTo4Nw
scope.12.kind=method
scope.12.startLine=87
scope.12.endLine=91
scope.12.semanticHash=aa6ca7e7f5b48ebc837a8f2fe01987fadcf04feebd63e9a901b82c4423df331d
scope.13.id=bWV0aG9kOkFzdE11dGF0aW9uU2Nhbm5lciN2aXNpdFVuYXJ5KDIpOjc4
scope.13.kind=method
scope.13.startLine=78
scope.13.endLine=85
scope.13.semanticHash=743cc4262021d584e8e0986b69f0356662680c7385899e8efe4e72153732195d
scope.14.id=bWV0aG9kOkFzdE11dGF0aW9uU2Nhbm5lciN2aXNpdFZhcmlhYmxlKDIpOjkz
scope.14.kind=method
scope.14.startLine=93
scope.14.endLine=98
scope.14.semanticHash=37a2d86d77f8cbe306c5fc45497f6c42b5a04fcc5a4ab7d1af0803794309ffe1
*/
