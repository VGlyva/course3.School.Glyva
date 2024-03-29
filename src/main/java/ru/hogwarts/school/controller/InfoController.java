package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class InfoController {
    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/port")
    public ResponseEntity<Integer> getPort() {
        return ResponseEntity.ok(serverPort);
    }

}
