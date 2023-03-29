package com.example.demo.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@ReactiveFeignClient(name = "httpBinClient",
        url = "https://httpbin.org")
public interface HttpBinClient {

    @GetMapping(path = "/delay/{delayInSeconds}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<Map<String, Object>> getBinWithDelayInSeconds(@PathVariable int delayInSeconds);
}
