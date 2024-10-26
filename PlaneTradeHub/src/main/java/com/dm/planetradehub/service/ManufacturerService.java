package com.dm.planetradehub.service;


import com.dm.planetradehub.entity.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> getAllManufacturers();

    Manufacturer getManufacturerById(Long id);

    Manufacturer getManufacturerByName(String name);

    Manufacturer saveManufacturer(Manufacturer manufacturer);

    void deleteManufacturerById(Long id);
}
