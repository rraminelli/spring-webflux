package com.example.springwebflux.repository.pagination;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
@RequiredArgsConstructor
public class PaginationRepository {

    final ReactiveMongoTemplate mongoTemplate;

    public <T> PageFluxDto<T> pagination(FilterCustomDto filterCustomDto, Class<T> clazz) {

        Pageable pageable = PageRequest.of(filterCustomDto.getPage(), filterCustomDto.getSize());

        Query queryCount = new Query();
        Query query = new Query().with(pageable);
        filterCustomDto.getValues()
                .forEach((key, value) -> {
                    queryCount.addCriteria(where(key).is(value));
                    query.addCriteria(where(key).is(value));
                });

        Mono<Long> countMono = mongoTemplate.count(queryCount, clazz);

        Flux<T> contentFlux = mongoTemplate.find(query, clazz);

        return new PageFluxDto<>(
                contentFlux,
                countMono
        );
    }

}
