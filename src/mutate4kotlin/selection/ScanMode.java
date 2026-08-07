package mutate4kotlin.selection;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.engine.*;

public final class ScanMode {

    private final DifferentialSelector selector;
    private final ScanReportFormatter formatter;
    private final LineFilter lineFilter;

    public ScanMode(DifferentialSelector selector, ScanReportFormatter formatter, LineFilter lineFilter) {
        this.selector = selector;
        this.formatter = formatter;
        this.lineFilter = lineFilter;
    }

    public String render(CliArguments parsed, ExecutionContext context) throws Exception {
        return formatter.format(
                context.sourceFile(),
                lineFilter.filter(context.analysis().sites(), parsed.lines()),
                selector.changedScopeIds(context.sourceFile(), context.analysis())
        );
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=9bec75c58a559644cb0193da1634f8f7717b30adc4a4c2083cabd7a384e910d6
scope.0.id=Y2xhc3M6U2Nhbk1vZGUjU2Nhbk1vZGU6MTE
scope.0.kind=class
scope.0.startLine=11
scope.0.endLine=30
scope.0.semanticHash=d54aef5530a97f2eaeb53fd3f480a5689e507fbafa2a70ca528abd4c5ebf9cfd
scope.1.id=ZmllbGQ6U2Nhbk1vZGUjZm9ybWF0dGVyOjE0
scope.1.kind=field
scope.1.startLine=14
scope.1.endLine=14
scope.1.semanticHash=6600d08feeb13b6394c0d4552a9345222d293c9707894932923c20f61c654257
scope.2.id=ZmllbGQ6U2Nhbk1vZGUjbGluZUZpbHRlcjoxNQ
scope.2.kind=field
scope.2.startLine=15
scope.2.endLine=15
scope.2.semanticHash=75af0640b8903e6fd41e753ad6d488dbdc1a9c0b7535651d6669d7d53c74c315
scope.3.id=ZmllbGQ6U2Nhbk1vZGUjc2VsZWN0b3I6MTM
scope.3.kind=field
scope.3.startLine=13
scope.3.endLine=13
scope.3.semanticHash=a436277abdb7a6ee715e4815ca019e5e16422373abca32b45079abcbb4095e05
scope.4.id=bWV0aG9kOlNjYW5Nb2RlI2N0b3IoMyk6MTc
scope.4.kind=method
scope.4.startLine=17
scope.4.endLine=21
scope.4.semanticHash=2af82f99ecd28873ccd3f382bd500e5a0bc650ad32da96936b3f9809345e3210
scope.5.id=bWV0aG9kOlNjYW5Nb2RlI3JlbmRlcigyKToyMw
scope.5.kind=method
scope.5.startLine=23
scope.5.endLine=29
scope.5.semanticHash=c23a7bae552eaab49c343983d5e5eb10604819582893ffe6700bf49e09c3ffbc
*/
