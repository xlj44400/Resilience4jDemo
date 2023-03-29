package com.example.demo.client;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * ReactiveResilience4JCircuitBreakerConfig
 * </p>
 *
 * @author: lianjun
 * @since: 2023-03-29
 */
@Configuration
public class ReactiveResilience4JCircuitBreakerConfig {

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    private TimeLimiterRegistry timeLimiterRegistry;

    @Bean
    public ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory() {
        ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory =
                new ReactiveResilience4JCircuitBreakerFactory();
        reactiveResilience4JCircuitBreakerFactory.configureCircuitBreakerRegistry(circuitBreakerRegistry);
        reactiveResilience4JCircuitBreakerFactory.configureDefault(this::createResilience4JCircuitBreakerConfiguration);
        return reactiveResilience4JCircuitBreakerFactory;
    }

    private Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration createResilience4JCircuitBreakerConfiguration(String id) {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(id);
        CircuitBreakerConfig circuitBreakerConfig = circuitBreaker.getCircuitBreakerConfig();
        TimeLimiterConfig timeLimiterConfig = timeLimiterRegistry.timeLimiter(id)
                .getTimeLimiterConfig();
        circuitBreaker.getEventPublisher()
                .onEvent(event -> System.out.println("Circuit-breaker Event Publisher : " + event));
        return new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(circuitBreakerConfig)
                .timeLimiterConfig(timeLimiterConfig)
                .build();
    }
}
