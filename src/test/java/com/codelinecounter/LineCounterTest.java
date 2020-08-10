package com.codelinecounter;

import org.junit.Test;

import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.assertSame;

public class LineCounterTest {

    private LineCounter lineCounter = new LineCounter();

    @Test
    public void testCounterGeneric() {
        assertSame(
                5,
                lineCounter.countLines(createReader("TestGeneric1.java"))
        );
        assertSame(
                3,
                lineCounter.countLines(createReader("TestGeneric2.java"))
        );
    }

    @Test
    public void testCounterEmpty() {
        assertSame(
                0,
                lineCounter.countLines(createReader("TestEmpty.java"))
        );
    }

    @Test(expected = RuntimeException.class)
    public void testCounterNull() {
        lineCounter.countLines(null);
    }

    private Reader createReader(String s) {
        return new InputStreamReader(
                this.getClass().getClassLoader().getResourceAsStream(s)
        );
    }
}
