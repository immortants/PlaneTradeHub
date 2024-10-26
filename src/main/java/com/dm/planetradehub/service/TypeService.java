package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Type;

import java.util.List;

public interface TypeService {
    List<Type> getAllTypes();

    Type getTypesById(Long id);

    Type saveType(Type type);

    void deleteTypeById(Long id);
}
