package com.basketball.gateway_service.config;

import com.basketball.gateway_service.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final JwtAuthenticationFilter filter;

    // TODO remove filters from here and define security config separately for handling role-based auth OR handle roles inside the filter
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/v1/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://USER-SERVICE"))
                .route("workout-service", r -> r.path("/v1/workout-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WORKOUT-SERVICE"))
                .route("drill-service", r -> r.path("/v1/drill-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://DRILL-SERVICE"))
                .route("user-performance-service", r -> r.path("/v1/user-performance-service/**")
                        .uri("lb://USER-PERFORMANCE"))
                .route("auth-service", r -> r.path("/v1/auth/**")
                        .uri("lb://AUTH-SERVICE"))
                .build();
    }
}