package mutate4kotlin.coverage;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.exec.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public final class JacocoLineCoverageParser {

    private JacocoLineCoverageParser() {
    }
    public static CoverageReport parse(Path jacocoXmlPath) {
        if (jacocoXmlPath == null || !Files.exists(jacocoXmlPath)) {
            return new CoverageReport(Set.of());
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);

            var builder = factory.newDocumentBuilder();
            builder.setEntityResolver((publicId, systemId) -> new InputSource(new StringReader("")));

            Document document = builder.parse(jacocoXmlPath.toFile());
            NodeList packages = document.getElementsByTagName("package");
            Set<CoverageSite> coveredLines = new HashSet<>();
            for (int i = 0; i < packages.getLength(); i++) {
                readPackage((Element) packages.item(i), coveredLines);
            }
            return new CoverageReport(Set.copyOf(coveredLines));
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to parse JaCoCo XML: " + jacocoXmlPath, ex);
        }
    }

    private static void readPackage(Element packageElement, Set<CoverageSite> coveredLines) {
        String packageName = packageElement.getAttribute("name");
        NodeList children = packageElement.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (!(node instanceof Element sourceFile) || !"sourcefile".equals(sourceFile.getTagName())) {
                continue;
            }
            String sourcePath = packageName.isBlank()
                    ? sourceFile.getAttribute("name")
                    : packageName + "/" + sourceFile.getAttribute("name");
            readCoveredLines(sourceFile, sourcePath, coveredLines);
        }
    }

    private static void readCoveredLines(Element sourceFile, String sourcePath, Set<CoverageSite> coveredLines) {
        NodeList children = sourceFile.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (!(node instanceof Element line) || !"line".equals(line.getTagName())) {
                continue;
            }
            int coveredInstructions = parseInt(line.getAttribute("ci"));
            if (coveredInstructions <= 0) {
                continue;
            }
            coveredLines.add(new CoverageSite(sourcePath, parseInt(line.getAttribute("nr"))));
        }
    }

    private static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=d5373651ce401f18c8b8e96e70eca8942fa8f344e873e95ab02930a0a8f9a93d
scope.0.id=Y2xhc3M6SmFjb2NvTGluZUNvdmVyYWdlUGFyc2VyI0phY29jb0xpbmVDb3ZlcmFnZVBhcnNlcjoyNQ
scope.0.kind=class
scope.0.startLine=25
scope.0.endLine=96
scope.0.semanticHash=bb0b2db0ce04a6079e577ae2ea3415ba9d52658d35131f1af60f76e2df2b3554
scope.1.id=bWV0aG9kOkphY29jb0xpbmVDb3ZlcmFnZVBhcnNlciNjdG9yKDApOjI3
scope.1.kind=method
scope.1.startLine=27
scope.1.endLine=28
scope.1.semanticHash=ab6a01bd386047020296d6eb0795e5aceb20da809f97f0b573e3ce535b851845
scope.2.id=bWV0aG9kOkphY29jb0xpbmVDb3ZlcmFnZVBhcnNlciNwYXJzZSgxKToyOQ
scope.2.kind=method
scope.2.startLine=29
scope.2.endLine=57
scope.2.semanticHash=43e7fcd51342192ead2f063fb23939880997d03386861a3dfc8a3bf54e6a49fe
scope.3.id=bWV0aG9kOkphY29jb0xpbmVDb3ZlcmFnZVBhcnNlciNwYXJzZUludCgxKTo4OQ
scope.3.kind=method
scope.3.startLine=89
scope.3.endLine=95
scope.3.semanticHash=4fb4b91404f9947d53aba51c47969c26e995c14a9f5bac278d8fff8e64566c4b
scope.4.id=bWV0aG9kOkphY29jb0xpbmVDb3ZlcmFnZVBhcnNlciNyZWFkQ292ZXJlZExpbmVzKDMpOjc0
scope.4.kind=method
scope.4.startLine=74
scope.4.endLine=87
scope.4.semanticHash=532d396a54432a071e7b67b8601dbfdd460ad6fc753fb52e6100adb53f92b312
scope.5.id=bWV0aG9kOkphY29jb0xpbmVDb3ZlcmFnZVBhcnNlciNyZWFkUGFja2FnZSgyKTo1OQ
scope.5.kind=method
scope.5.startLine=59
scope.5.endLine=72
scope.5.semanticHash=a11e2f236361dd35ec9765d0fa05cb87a6929d936d565c917c50f3dc28030842
*/
