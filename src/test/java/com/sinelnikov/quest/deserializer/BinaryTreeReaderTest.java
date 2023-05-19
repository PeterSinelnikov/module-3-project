package com.sinelnikov.quest.deserializer;

import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BinaryTreeReaderTest {

    private BinaryTreeReader reader;
    private ServletContext servletContext;

    @BeforeEach
    void setUp() {
        servletContext = mock(ServletContext.class);
        reader = new BinaryTreeReader(servletContext);
    }

    @Test
    void initialize_passedValidPath_shouldReturnValidString() {
        String pathToTree = "src/main/resources/test.txt";
        String expectedContent = "Hello world!";
        ByteArrayInputStream stream = new ByteArrayInputStream(expectedContent.getBytes());
        when(servletContext.getResourceAsStream(pathToTree)).thenReturn(stream);
        String actual = reader.read(pathToTree);
        assertEquals("Hello world!", actual);
    }

    @Test
    void initialize_passedInvalidPath_shouldThrowException() {
        String expectedMessage = "Could not read game tree configuration file";
        String invalidPath = "invalid/path/to/file.txt";
        when(servletContext.getResourceAsStream(invalidPath))
                .then(invocationOnMock -> {
                    throw new IOException(expectedMessage);
                });
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> reader.read(invalidPath));
        assertEquals(expectedMessage, exception.getMessage());
    }
}