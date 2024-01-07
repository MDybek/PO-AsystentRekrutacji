package com.example.poasystentrekrutacji.utils.validator;

import java.util.List;
import java.util.stream.Collectors;

public class CollectionValidationUtils {

    public static <T> boolean isCollectionNonUnique(List<T> collection) {
        return collection.size() != collection.stream().collect(Collectors.toSet()).size();
    }
}
