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

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void doesNotExitWhenCodeIsZero() {
        AtomicInteger exit = new AtomicInteger(-1);

        Main.exitIfNeeded(0, exit::set);

        assertEquals(-1, exit.get());
    }

    @Test
    void exitsWhenCodeIsNonZero() {
        AtomicInteger exit = new AtomicInteger(-1);

        Main.exitIfNeeded(3, exit::set);

        assertEquals(3, exit.get());
    }
}
