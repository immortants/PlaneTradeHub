package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.Model;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> getModelsByManufacturerId(Long manufacturer);
}
