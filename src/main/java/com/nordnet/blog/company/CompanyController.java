package com.nordnet.blog.company;

import com.nordnet.blog.company.type.Company;
import com.nordnet.blog.company.type.CompanyId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository repository;

    @GetMapping("/companies/{id}")
    public Mono<ResponseEntity<Company>> get(@PathVariable CompanyId id) {
        return repository.read(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.defer(() -> Mono.just(ResponseEntity.noContent().build())));
    }
}
