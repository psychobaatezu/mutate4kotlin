package mutate4kotlin.selection;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.util.List;
import java.util.Set;

public final class LineFilter {

    public List<MutationSite> filter(List<MutationSite> sites, Set<Integer> lines) {
        if (lines.isEmpty()) {
            return sites;
        }
        return sites.stream().filter(site -> lines.contains(site.lineNumber())).toList();
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=095e78f5e46d506c876ad7379e04c2da7afea78f69ad6dc5b6e7f9aba06bfe72
scope.0.id=Y2xhc3M6TGluZUZpbHRlciNMaW5lRmlsdGVyOjEz
scope.0.kind=class
scope.0.startLine=13
scope.0.endLine=21
scope.0.semanticHash=294efa6419796a71c9ddb4806edb63256a684cb913cf0fed9363dabb90ea1623
scope.1.id=bWV0aG9kOkxpbmVGaWx0ZXIjY3RvcigwKToxMw
scope.1.kind=method
scope.1.startLine=1
scope.1.endLine=21
scope.1.semanticHash=4ea19c6ebdcbfaf1b75563160ebdd15c19bbf4fdf84d702581b4780082048d22
scope.2.id=bWV0aG9kOkxpbmVGaWx0ZXIjZmlsdGVyKDIpOjE1
scope.2.kind=method
scope.2.startLine=15
scope.2.endLine=20
scope.2.semanticHash=bde18f3a2853a78d965839437e754ecc708d91239ee231b1d53e945ef50746f8
*/
