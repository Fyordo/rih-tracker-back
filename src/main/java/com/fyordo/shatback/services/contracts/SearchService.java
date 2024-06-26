package com.fyordo.shatback.services.contracts;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface SearchService<T> {

    Optional<T> findById(Long id);
    Collection<T> findAll(Map<String, String> filter);
}
