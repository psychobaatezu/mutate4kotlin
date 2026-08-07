package mutate4kotlin.analysis;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.manifest.*;

import com.sun.source.tree.Tree;

record BinaryMutationOperator(String original, String replacement, boolean numericOnly) {

    static BinaryMutationOperator forKind(Tree.Kind kind) {
        return switch (kind) {
            case PLUS -> new BinaryMutationOperator("+", "-", true);
            case MINUS -> new BinaryMutationOperator("-", "+", false);
            case MULTIPLY -> new BinaryMutationOperator("*", "/", false);
            case DIVIDE -> new BinaryMutationOperator("/", "*", false);
            case CONDITIONAL_AND -> new BinaryMutationOperator("&&", "||", false);
            case CONDITIONAL_OR -> new BinaryMutationOperator("||", "&&", false);
            case EQUAL_TO -> new BinaryMutationOperator("==", "!=", false);
            case NOT_EQUAL_TO -> new BinaryMutationOperator("!=", "==", false);
            case GREATER_THAN -> new BinaryMutationOperator(">", ">=", false);
            case GREATER_THAN_EQUAL -> new BinaryMutationOperator(">=", ">", false);
            case LESS_THAN -> new BinaryMutationOperator("<", "<=", false);
            case LESS_THAN_EQUAL -> new BinaryMutationOperator("<=", "<", false);
            default -> null;
        };
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=9f9d05f26e52911e55441ddc71cf9836d37caa4b65c814fdb7ed201d536f7ee3
scope.0.id=Y2xhc3M6QmluYXJ5TXV0YXRpb25PcGVyYXRvciNCaW5hcnlNdXRhdGlvbk9wZXJhdG9yOjEz
scope.0.kind=class
scope.0.startLine=13
scope.0.endLine=32
scope.0.semanticHash=a5cdb79479465c6a2ce487540251e6561a35fcc793df195610cc87114e8cdb03
scope.1.id=ZmllbGQ6QmluYXJ5TXV0YXRpb25PcGVyYXRvciNudW1lcmljT25seToxMw
scope.1.kind=field
scope.1.startLine=13
scope.1.endLine=13
scope.1.semanticHash=567e9cb922b068d058d53f650ff53465500a6c337adfcd1193b120c12a510bcf
scope.2.id=ZmllbGQ6QmluYXJ5TXV0YXRpb25PcGVyYXRvciNvcmlnaW5hbDoxMw
scope.2.kind=field
scope.2.startLine=13
scope.2.endLine=13
scope.2.semanticHash=6d0259d47f6b774778039d81ab1b8653eeb1f14befc81ed73320a9cb25679cc1
scope.3.id=ZmllbGQ6QmluYXJ5TXV0YXRpb25PcGVyYXRvciNyZXBsYWNlbWVudDoxMw
scope.3.kind=field
scope.3.startLine=13
scope.3.endLine=13
scope.3.semanticHash=8368642ddeea9bf0ed36f612c90239029086d8b4895cdaaad06fe3b27ae7026a
scope.4.id=bWV0aG9kOkJpbmFyeU11dGF0aW9uT3BlcmF0b3IjY3RvcigzKToxMw
scope.4.kind=method
scope.4.startLine=1
scope.4.endLine=32
scope.4.semanticHash=17efacf066ba5f85e7bd12ca60252b33c60899af4157d63c0b56a7c34d81ad30
scope.5.id=bWV0aG9kOkJpbmFyeU11dGF0aW9uT3BlcmF0b3IjZm9yS2luZCgxKToxNQ
scope.5.kind=method
scope.5.startLine=15
scope.5.endLine=31
scope.5.semanticHash=eb58519acf159b52724e94e50b47e1aa5b53b9c88e422cefef87b7a14f06e65f
*/
