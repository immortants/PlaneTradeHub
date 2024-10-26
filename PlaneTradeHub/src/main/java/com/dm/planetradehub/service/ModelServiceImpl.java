package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Model;
import com.dm.planetradehub.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService{
    private final ManufacturerService manufacturerService;
    private final ModelRepository modelRepository;

    public ModelServiceImpl(ManufacturerService manufacturerService, ModelRepository modelRepository) {
        this.manufacturerService = manufacturerService;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll() ;
    }

    @Override
    public Model getModelById(Long id) {
        return modelRepository.getReferenceById(id);
    }

    @Override
    public Model saveModel(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public List<Model> getModelsByManufacturer(String manufacturer) {
        if (manufacturer == ""){
            return getAllModels();
        }
        else
            return modelRepository.getModelsByManufacturerId(manufacturerService.getManufacturerByName(manufacturer).getId());
    }

    @Override
    public void deleteModelById(Long id) {
        modelRepository.deleteById(id);
    }
}
