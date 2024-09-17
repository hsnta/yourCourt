package com.basketball.gateway_service.config;

import com.basketball.gateway_service.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final JwtAuthenticationFilter filter;

    @Value("${gateway.basePath}")
    private String basePath;
    @Value("${gateway.userServicePort}")
    private String userServicePort;
    @Value("${gateway.workoutServicePort}")
    private String workoutServicePort;
    @Value("${gateway.drillServicePort}")
    private String drillServicePort;
    @Value("${gateway.userPerformanceServicePort}")
    private String userPerformanceServicePort;
    @Value("${gateway.authServicePort}")
    private String authServicePort;

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