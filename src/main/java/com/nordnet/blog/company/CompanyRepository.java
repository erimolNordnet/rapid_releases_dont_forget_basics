package com.nordnet.blog.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nordnet.blog.company.type.Company;
import com.nordnet.blog.company.type.CompanyId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CompanyRepository {

    private final ReactiveValueOperations<String, String> hash;
    private final ObjectMapper mapper;

    Mono<Company> read(CompanyId id) {
        return hash.get(id.getValue())
                .mapNotNull(v -> {
                    try {
                        return mapper.readValue(v, Company.class);
                    } catch (JsonProcessingException e) {
                        log.error("Couldn't map json value", e);
                    }
                    return null;
                });
    }
}
