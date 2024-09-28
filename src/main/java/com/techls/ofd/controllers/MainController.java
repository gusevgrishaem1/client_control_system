package com.techls.ofd.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class MainController {
    @SneakyThrows
    @GetMapping("/login-page")
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

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }
}
