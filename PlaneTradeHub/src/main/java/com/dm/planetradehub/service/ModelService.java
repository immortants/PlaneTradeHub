package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Model;

import java.util.List;

public interface ModelService {
    List<Model> getAllModels();

    Model getModelById(Long id);

    Model saveModel(Model model);

    List<Model> getModelsByManufacturer(String manufacturer);

    void deleteModelById(Long id);
}
