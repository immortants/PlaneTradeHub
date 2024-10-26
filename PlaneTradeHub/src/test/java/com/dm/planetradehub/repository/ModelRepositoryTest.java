package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModelRepositoryTest {

    @Mock
    private ModelRepository modelRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getModelsByManufacturerId_WhenFound() {
        // Given
        Long manufacturerId = 1L;
        List<Model> expectedModels = new ArrayList<>();
        expectedModels.add(new Model());
        expectedModels.add(new Model());

        when(modelRepository.getModelsByManufacturerId(manufacturerId))
                .thenReturn(expectedModels);

        // When
        List<Model> models = modelRepository.getModelsByManufacturerId(manufacturerId);

        // Then
        assertNotNull(models);
        assertEquals(expectedModels.size(), models.size());
        assertEquals(expectedModels, models);
        verify(modelRepository, times(1)).getModelsByManufacturerId(manufacturerId);
    }

    @Test
    void getModelsByManufacturerId_WhenNotFound() {
        // Given
        Long manufacturerId = 1L;

        when(modelRepository.getModelsByManufacturerId(manufacturerId))
                .thenReturn(new ArrayList<>());

        // When
        List<Model> models = modelRepository.getModelsByManufacturerId(manufacturerId);

        // Then
        assertNotNull(models);
        assertEquals(0, models.size());
        verify(modelRepository, times(1)).getModelsByManufacturerId(manufacturerId);
    }
}