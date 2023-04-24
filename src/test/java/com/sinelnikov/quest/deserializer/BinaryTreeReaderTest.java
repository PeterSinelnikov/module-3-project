package com.sinelnikov.quest.deserializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BinaryTreeReaderTest {

    BinaryTreeReader initializer;

    @Test
    void initialize_passedValidPath_shouldReturnValidString() throws IOException {
        initializer = new BinaryTreeReader();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).then(invocationOnMock -> servletContext);
        InputStream stream = Mockito.mock(InputStream.class);
        Mockito.when(servletContext.getResourceAsStream("/path/to/file.txt")).then(invocationOnMock -> stream);
        Mockito.when(stream.readAllBytes()).then(invocationOnMock -> "Hello world!".getBytes(StandardCharsets.UTF_8));
        String actual = initializer.read(request, "/path/to/file.txt");
        assertEquals("Hello world!", actual);
    }

    @Test
    void initialize_passedInvalidPath_shouldThrowException() throws IOException {
        initializer = new BinaryTreeReader();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).then(invocationOnMock -> servletContext);
        InputStream stream = Mockito.mock(InputStream.class);
        Mockito.when(servletContext.getResourceAsStream("invalid/path/to/file.txt"))
                .then(invocationOnMock -> stream);
        String expectedMessage = "Could not read game tree configuration file";
        Mockito.when(stream.readAllBytes()).then(invocationOnMock -> {
            throw new IOException(expectedMessage);
        });
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> initializer.read(request, "invalid/path/to/file.txt"));
        assertEquals(expectedMessage, exception.getMessage());
    }
}