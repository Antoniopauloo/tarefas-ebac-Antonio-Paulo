package br.com.jeb;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExampleClassTest {
    private final ExampleClass example = new ExampleClass();

    @Test
    void testProcessText() {
        assertEquals("Hello", example.processText("hello"));
        assertEquals("World", example.processText("world"));
        assertEquals("", example.processText(""));
    }
}
