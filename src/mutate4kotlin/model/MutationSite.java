package mutate4kotlin.model;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import java.nio.file.Path;

public record MutationSite(Path file,
                    int lineNumber,
                    int start,
                    int end,
                    String originalText,
                    String replacementText,
                    String description,
                    String scopeId,
                    String scopeKind,
                    int scopeStartLine,
                    int scopeEndLine) {

    public MutationSite(Path file,
                        int lineNumber,
                        int start,
                        int end,
                        String originalText,
                        String replacementText,
                        String description) {
        this(file, lineNumber, start, end, originalText, replacementText, description,
                "scope:" + lineNumber, "unknown", lineNumber, lineNumber);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=e1f70bdc071b73000faa3ceb8788b8a244ea02cfce796775703084c0c1bdbe8e
scope.0.id=Y2xhc3M6TXV0YXRpb25TaXRlI011dGF0aW9uU2l0ZToxOA
scope.0.kind=class
scope.0.startLine=18
scope.0.endLine=40
scope.0.semanticHash=0052112ae01e237a3bcf0d98a936567cd91e44e747749c0a459cd1718fd39f9e
scope.1.id=ZmllbGQ6TXV0YXRpb25TaXRlI2Rlc2NyaXB0aW9uOjI0
scope.1.kind=field
scope.1.startLine=24
scope.1.endLine=24
scope.1.semanticHash=059e51f9cb9ce993c16a8c1e89382e35eae3b3848aa44972c74aa1c55de02422
scope.2.id=ZmllbGQ6TXV0YXRpb25TaXRlI2VuZDoyMQ
scope.2.kind=field
scope.2.startLine=21
scope.2.endLine=21
scope.2.semanticHash=15fdf4797cd0b280b9cf2a8fbc50b7ed5fbfc821dcbca3fc387310a6ff9cbec0
scope.3.id=ZmllbGQ6TXV0YXRpb25TaXRlI2ZpbGU6MTg
scope.3.kind=field
scope.3.startLine=18
scope.3.endLine=18
scope.3.semanticHash=b3a48878344bcd4ddbd5a6532a185533c7ed6e82e81955ac557130584e5971be
scope.4.id=ZmllbGQ6TXV0YXRpb25TaXRlI2xpbmVOdW1iZXI6MTk
scope.4.kind=field
scope.4.startLine=19
scope.4.endLine=19
scope.4.semanticHash=e2d9a8752ba497a35f15467e67a045cce4ec8df064acbc3b45310cac37061a40
scope.5.id=ZmllbGQ6TXV0YXRpb25TaXRlI29yaWdpbmFsVGV4dDoyMg
scope.5.kind=field
scope.5.startLine=22
scope.5.endLine=22
scope.5.semanticHash=7d26ea113a880b918a979ae0c12445e48cdcf3e92c73faeca6689172741551a7
scope.6.id=ZmllbGQ6TXV0YXRpb25TaXRlI3JlcGxhY2VtZW50VGV4dDoyMw
scope.6.kind=field
scope.6.startLine=23
scope.6.endLine=23
scope.6.semanticHash=e1507e3c22deb8dfbb3e96379aea01c6e81cdd9117ac4db0aa69ed8d1138acf2
scope.7.id=ZmllbGQ6TXV0YXRpb25TaXRlI3Njb3BlRW5kTGluZToyOA
scope.7.kind=field
scope.7.startLine=28
scope.7.endLine=28
scope.7.semanticHash=00ff764351c5275d6aaa7d8f00498e1bcef07f814a4919070c1f8266c3a0c54f
scope.8.id=ZmllbGQ6TXV0YXRpb25TaXRlI3Njb3BlSWQ6MjU
scope.8.kind=field
scope.8.startLine=25
scope.8.endLine=25
scope.8.semanticHash=c424bf93a79f6ce8938cff8e702e04683c45e94f8d15f5f621f542c4e850a461
scope.9.id=ZmllbGQ6TXV0YXRpb25TaXRlI3Njb3BlS2luZDoyNg
scope.9.kind=field
scope.9.startLine=26
scope.9.endLine=26
scope.9.semanticHash=298c4ed6a9e0a0fe69551b8ec0007d1a375e4ea595b939b619f57115e09b7ada
scope.10.id=ZmllbGQ6TXV0YXRpb25TaXRlI3Njb3BlU3RhcnRMaW5lOjI3
scope.10.kind=field
scope.10.startLine=27
scope.10.endLine=27
scope.10.semanticHash=e08d84f0042fb6f89535bdebdd34f3b57cd9947ce46d4972129ca0240de582cc
scope.11.id=ZmllbGQ6TXV0YXRpb25TaXRlI3N0YXJ0OjIw
scope.11.kind=field
scope.11.startLine=20
scope.11.endLine=20
scope.11.semanticHash=87c29953993a687b6161aabde0342557be071bfeb0eff8c884084044992d0857
scope.12.id=bWV0aG9kOk11dGF0aW9uU2l0ZSNjdG9yKDExKToxOA
scope.12.kind=method
scope.12.startLine=1
scope.12.endLine=40
scope.12.semanticHash=be1119d49462131076e3139632a83d0606c9ee65a25dbde39dca5ed981993d7d
scope.13.id=bWV0aG9kOk11dGF0aW9uU2l0ZSNjdG9yKDcpOjMw
scope.13.kind=method
scope.13.startLine=30
scope.13.endLine=39
scope.13.semanticHash=a25c3ad27b38833b07b9a5b8370ca1109600bfcc0143bb1358d6ac221f6645e1
*/
