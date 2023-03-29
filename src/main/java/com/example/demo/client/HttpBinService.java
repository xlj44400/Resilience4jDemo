package com.example.demo.client;

import com.example.demo.client.HttpBinClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class HttpBinService {

    @Autowired
    private HttpBinClient httpBinClient;

    public Mono<Map<String, Object>> getBin(int delayInSeconds) {
        return httpBinClient.getBinWithDelayInSeconds(delayInSeconds)
                .onErrorMap(Throwable::getCause);
    }
}