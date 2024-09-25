package com.basketball.gateway_service.filter;

import com.basketball.gateway_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GatewayFilter {
    private final JwtUtil jwtUtil;
    private final WebClient authServiceWebClient;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        final List<String> apiEndpoints = List.of("/v1/auth/login", "/v1/auth/register", "/eureka");
        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));
        log.info("Requested endpoint: {}", request.getURI().getPath());
        if (!isApiSecured.test(request)) {
            return chain.filter(exchange);

        }
        if (authMissing(request)) {
            return onError(exchange);
        }

        String bearerToken = request.getHeaders().getOrEmpty("Authorization").get(0);

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return onError(exchange);
        }
        boolean isRefreshEndpoint = request.getURI().getPath().contains("/v1/auth/refresh");
        try {
            return authServiceWebClient.post()
                    .uri(uriBuilder -> uriBuilder.path("lb://AUTH-SERVICE/validate")
                            .queryParam("refresh", isRefreshEndpoint)
                            .build())
                    .header(HttpHeaders.AUTHORIZATION, bearerToken)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .then(chain.filter(exchange))
                    .onErrorResume(error -> onError(exchange));
        } catch (Exception e) {
            return onError(exchange);
        }
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
}
