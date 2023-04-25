package com.sinelnikov.quest.deserializer;

import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class BinaryTreeReader {
    public BinaryTreeReader() {
    }

    public String read(ServletContext servletContext, String pathToTree){
        try (InputStream stream = servletContext.getResourceAsStream(pathToTree)) {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Could not read game tree configuration file");
        }
    }

}
