package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Type;
import com.dm.planetradehub.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @Override
    public Type getTypesById(Long id) {
        return typeRepository.getReferenceById(id);
    }

    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void deleteTypeById(Long id) {
        typeRepository.deleteById(id);
    }
}
