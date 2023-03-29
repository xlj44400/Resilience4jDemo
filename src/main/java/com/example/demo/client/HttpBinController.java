package com.example.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class HttpBinController {

    @Autowired
    private HttpBinService httpBinService;

    @GetMapping(path = "/get",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Object>> getBin(
            @RequestParam(required = false, defaultValue = "1") int delayInSeconds) {
        return httpBinService.getBin(delayInSeconds);
    }
}
