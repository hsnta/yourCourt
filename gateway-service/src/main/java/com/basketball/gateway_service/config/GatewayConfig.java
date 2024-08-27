package com.basketball.gateway_service.config;

import com.basketball.gateway_service.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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

    public GatewayConfig(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/v1/user/**")
                        .filters(f -> f.filter(filter))
                        .uri(basePath + ":" + userServicePort))
                .route("workout-service", r -> r.path("/v1/workout-service/**")
                        .filters(f -> f.filter(filter))
                        .uri(basePath + ":" + workoutServicePort))
                .route("drill-service", r -> r.path("/v1/drill-service/**")
                        .filters(f -> f.filter(filter))
                        .uri(basePath + ":" + drillServicePort))
                .route("user-performance-service", r -> r.path("/v1/user-performance-service/**")
                        .uri(basePath + ":" + userPerformanceServicePort))
                .route("auth-service", r -> r.path("/v1/auth/**")
                        .uri(basePath + ":" + authServicePort))
                .build();
    }
}