package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.Manufacturer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Manufacturer getManufacturerByName(String name);

}
