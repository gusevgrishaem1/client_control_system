package com.techls.ofd.controllers;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class MainController {
    @SneakyThrows
    @GetMapping("/login")
    @ResponseBody
    public String showLoginPage() {
        ClassPathResource resource = new ClassPathResource("static/login.html");
        Path filePath = resource.getFile().toPath();
        return Files.readString(filePath);
    }

    @SneakyThrows
    @GetMapping
    @ResponseBody
    public String showIndexPage() {
        ClassPathResource resource = new ClassPathResource("static/index.html");
        Path filePath = resource.getFile().toPath();
        return Files.readString(filePath);
    }
}
