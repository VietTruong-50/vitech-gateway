package com.example.apigateway.config;

//import org.springdoc.core.SwaggerUiConfigParameters;
//import org.springdoc.core.SwaggerUiConfigProperties;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionLocator;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
public class AppConfig {
    //    @Bean
//    public SwaggerUiConfigParameters swaggerUiConfigParameters() {
//        return new SwaggerUiConfigParameters(new SwaggerUiConfigProperties());
//    }
//    @Bean
//    public CommandLineRunner openApiGroups(
//            RouteDefinitionLocator locator,
//            SwaggerUiConfigParameters swaggerUiParameters) {
//        return args -> Objects.requireNonNull(locator
//                        .getRouteDefinitions().collectList().block())
//                .stream()
//                .map(RouteDefinition::getId)
//                .filter(id -> id.matches(".*-service"))
//                .map(id -> id.replace("-service", ""))
//                .forEach(swaggerUiParameters::addGroup);
//    }
    @Autowired
    RouteDefinitionLocator locator;

    @Bean
    public List<GroupedOpenApi> apis() {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("-service", "");
            GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
        });
        return groups;
    }
//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("shopping-service", r -> r.path("/v1/shopping/**")
//                        .uri("http://localhost:9001/shopping-service"))
//                .route("management-service", r -> r.path("/v1/**")
//                        .uri("http://localhost:9002/management-service"))
//                .route("order-service", r -> r.path("/api/v1/order/**")
//                        .uri("http://localhost:9003/"))
//                .route("inventory-service", r -> r.path("/api/v1/inventory/**")
//                        .uri("http://localhost:9004/"))
//                .route("user-service", r -> r.path("/api/v1/user/**")
//                        .uri("http://localhost:9005/"))
//                .build();
//    }
}