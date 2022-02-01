package org.johannpando.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CarRoutes {

    @Bean
    public RouterFunction<ServerResponse> routes(CarHandler carHandler) {

        return RouterFunctions.route()
                .path(
                "/cars", builder -> builder
                        .GET("", carHandler::getAll)
                        .GET("/{id}", carHandler::findById)
                        .DELETE("/{id}", carHandler::deleteById)
                        .POST("", carHandler::save)
                ).build();
    }
}
