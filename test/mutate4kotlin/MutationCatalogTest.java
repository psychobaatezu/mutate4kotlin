package mutate4kotlin;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MutationCatalogTest {

    @TempDir
    Path tempDir;

    @Test
    void discoversBooleanEqualityAndComparisonMutations() throws Exception {
        Path file = tempDir.resolve("src/main/kotlin/demo/Sample.kt");
        Files.createDirectories(file.getParent());
        Files.writeString(file, """
                package demo

                class Sample {
                    fun truthy(): Boolean {
                        return true
                    }

                    fun same(left: Int, right: Int): Boolean {
                        return left == right
                    }

                    fun larger(left: Int, right: Int): Boolean {
                        return left > right
                    }

                    fun smaller(left: Int, right: Int): Boolean {
                        return left <= right
                    }
                }
                """);

        List<MutationSite> sites = new MutationCatalog().discover(List.of(file));

        List<String> descriptions = sites.stream().map(MutationSite::description).toList();

        assertEquals(true, descriptions.contains("replace true with false"));
        assertEquals(true, descriptions.contains("replace == with !="));
        assertEquals(true, descriptions.contains("replace > with >="));
        assertEquals(true, descriptions.contains("replace <= with <"));
    }

    @Test
    void ignoresOperatorsInsideStringsCharsAndComments() throws Exception {
        Path file = tempDir.resolve("src/main/kotlin/demo/Literals.kt");
        Files.createDirectories(file.getParent());
        Files.writeString(file, """
                package demo

                class Literals {
                    fun text(): String {
                        return "true == false > <"
                    }

                    fun angle(): Char {
                        return '>'
                    }

                    fun same(left: Int, right: Int): Boolean {
                        // left == right > 0
                        /* false != true <= >= */
                        return left == right
                    }
                }
                """);

        List<MutationSite> sites = new MutationCatalog().discover(List.of(file));

        List<String> descriptions = sites.stream().map(MutationSite::description).toList();
        assertEquals(List.of("replace == with !="), descriptions);
    }

    @Test
    void ignoresGenericTypeAngleBrackets() throws Exception {
        Path file = tempDir.resolve("src/main/kotlin/demo/GenericSample.kt");
        Files.createDirectories(file.getParent());
        Files.writeString(file, """
                package demo

                class GenericSample {
                    fun names(): List<String> {
                        return listOf("a")
                    }

                    fun larger(left: Int, right: Int): Boolean {
                        return left > right
                    }
                }
                """);

        List<MutationSite> sites = new MutationCatalog().discover(List.of(file));

        List<String> descriptions = sites.stream().map(MutationSite::description).toList();
        assertEquals(true, descriptions.contains("replace > with >="));
        assertEquals(false, descriptions.contains("replace < with <="));
        assertEquals(false, descriptions.contains("replace <= with <"));
    }

    @Test
    void discoversArithmeticLogicalAndNullReplacementMutations() throws Exception {
        Path file = tempDir.resolve("src/main/kotlin/demo/Expanded.kt");
        Files.createDirectories(file.getParent());
        Files.writeString(file, """
                package demo

                class Expanded {
                    fun add(left: Int, right: Int): Int {
                        return left + right
                    }

                    fun divide(left: Int, right: Int): Int {
                        return left / right
                    }

                    fun both(left: Boolean, right: Boolean): Boolean {
                        return left && right
                    }

                    fun message(): String {
                        return "hello"
                    }

                    fun assign(): String {
                        var value = helper()
                        value = helper()
                        return value
                    }

                    fun helper(): String {
                        return "x"
                    }
                }
                """);

        List<MutationSite> sites = new MutationCatalog().discover(List.of(file));

        List<String> descriptions = sites.stream().map(MutationSite::description).toList();
        assertEquals(true, descriptions.contains("replace + with -"));
        assertEquals(true, descriptions.contains("replace / with *"));
        assertEquals(true, descriptions.contains("replace && with ||"));
        assertEquals(true, descriptions.contains("replace value with null"));
    }

    @Test
    void discoversUnaryAndConstantMutations() throws Exception {
        Path file = tempDir.resolve("src/main/kotlin/demo/UnarySample.kt");
        Files.createDirectories(file.getParent());
        Files.writeString(file, """
                package demo

                class UnarySample {
                    fun invert(value: Boolean): Boolean {
                        return !value
                    }

                    fun negative(value: Int): Int {
                        return -value
                    }

                    fun zero(): Int {
                        return 0
                    }

                    fun one(): Int {
                        return 1
                    }
                }
                """);

        List<MutationSite> sites = new MutationCatalog().discover(List.of(file));

        List<String> descriptions = sites.stream().map(MutationSite::description).toList();
        assertEquals(true, descriptions.contains("replace ! with removed !"));
        assertEquals(true, descriptions.contains("replace - with +"));
        assertEquals(true, descriptions.contains("replace - with removed -"));
        assertEquals(true, descriptions.contains("replace 0 with 1"));
        assertEquals(true, descriptions.contains("replace 1 with 0"));

        MutationSite unaryNot = sites.get(0);
        assertEquals("", unaryNot.replacementText());

        MutationSite unaryMinus = sites.stream()
                .filter(site -> "replace - with removed -".equals(site.description()))
                .findFirst()
                .orElseThrow();
        assertEquals("", unaryMinus.replacementText());
    }
}
