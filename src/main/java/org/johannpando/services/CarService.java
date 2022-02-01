package org.johannpando.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.johannpando.entity.CarEntity;
import org.johannpando.repository.CarRepository;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class CarService {

    final private CarRepository carRepository;

    public Mono<CarEntity> findById(Long id) {
        return carRepository.findById(id).doOnNext(c -> log.info("Car with id: " + c.getId()));
    }

    public Mono<Void> deleteById(Long id) {
        return carRepository.deleteById(id).doOnNext(c -> log.info("Car with id{} deleted", id));
    }

    public Mono<CarEntity> save(CarEntity carEntity) {
        return carRepository.save(carEntity);
    }

    public Flux<CarEntity> findAll(Sort sort) {
        return carRepository.findAll(sort);
    }
}
