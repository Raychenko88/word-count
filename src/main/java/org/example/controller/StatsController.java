package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "stats")
public class StatsController {

    private final StatsService statsService;

    @PostMapping
    public ResponseEntity<List> save(@RequestBody String sentence) {
        try {
            return new ResponseEntity<>(statsService.saveAll(sentence), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
