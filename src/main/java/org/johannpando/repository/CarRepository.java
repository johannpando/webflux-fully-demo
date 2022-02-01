package org.johannpando.repository;

import org.johannpando.entity.CarEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface CarRepository extends ReactiveSortingRepository<CarEntity, Long> {

}
