package org.johannpando.routes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.johannpando.entity.CarEntity;
import org.johannpando.services.CarService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class CarHandler {

    private final CarService carService;

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        var all = carService.findAll(Sort.by("model", "brand"));
        return ServerResponse.ok().body(BodyInserters.fromPublisher(all, CarEntity.class));
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        var one = carService.findById(Long.valueOf(serverRequest.pathVariable("id")))
                .switchIfEmpty(Mono.error(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return ServerResponse.ok().body(BodyInserters.fromPublisher(one, CarEntity.class));
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        final Mono<CarEntity> car = serverRequest.bodyToMono(CarEntity.class);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(car.flatMap(carService::save), CarEntity.class));
    }

    public Mono deleteById(ServerRequest serverRequest) {
        var id = Long.valueOf(serverRequest.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(carService.deleteById(id), Void.class);
    }
}
