package com.sinelnikov.quest.deserializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class BinaryTreeReader {
    public BinaryTreeReader() {
    }

    public String read(HttpServletRequest req, String pathToTree){
        ServletContext context = req.getServletContext();
        try (InputStream stream = context.getResourceAsStream(pathToTree)) {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Could not read game tree configuration file");
        }
    }

}
