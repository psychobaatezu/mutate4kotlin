package mutate4kotlin.selection;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.coverage.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class MutationCoverageFilter {

    private final ProjectLayout layout;

    public MutationCoverageFilter(ProjectLayout layout) {
        this.layout = layout;
    }

    public CoverageSelection filter(Path moduleRoot, List<MutationSite> sites, CoverageReport coverage) {
        List<MutationSite> covered = new ArrayList<>();
        List<MutationSite> uncovered = new ArrayList<>();
        for (MutationSite site : sites) {
            if (coverage.covers(layout.sourceSuffix(moduleRoot, site.file()), site.lineNumber())) {
                covered.add(site);
            } else {
                uncovered.add(site);
            }
        }
        return new CoverageSelection(List.copyOf(covered), List.copyOf(uncovered));
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=4afe70bdce10d50d1a6ee21ba85284ea9835310cc9ac1f08356db8eee6792b88
scope.0.id=Y2xhc3M6TXV0YXRpb25Db3ZlcmFnZUZpbHRlciNNdXRhdGlvbkNvdmVyYWdlRmlsdGVyOjE1
scope.0.kind=class
scope.0.startLine=15
scope.0.endLine=35
scope.0.semanticHash=2af11ad49ec895069da094399e3fd726c75464df967f029287352befbd4fc619
scope.1.id=ZmllbGQ6TXV0YXRpb25Db3ZlcmFnZUZpbHRlciNsYXlvdXQ6MTc
scope.1.kind=field
scope.1.startLine=17
scope.1.endLine=17
scope.1.semanticHash=16ed98fa39282b5fde699cc6cd9ba90e34dced0e14519c4201e4d5401638a53e
scope.2.id=bWV0aG9kOk11dGF0aW9uQ292ZXJhZ2VGaWx0ZXIjY3RvcigxKToxOQ
scope.2.kind=method
scope.2.startLine=19
scope.2.endLine=21
scope.2.semanticHash=08b7dd244c1c32ad0c726b1f21cf0c797c3776ba9aecc92734aff2f72b5ff0af
scope.3.id=bWV0aG9kOk11dGF0aW9uQ292ZXJhZ2VGaWx0ZXIjZmlsdGVyKDMpOjIz
scope.3.kind=method
scope.3.startLine=23
scope.3.endLine=34
scope.3.semanticHash=3dfb84812d7f4fbf1be3c020891b4ddad03da27e100317e490d94540f0ec572f
*/
