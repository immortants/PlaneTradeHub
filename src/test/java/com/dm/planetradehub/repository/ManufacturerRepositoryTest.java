package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManufacturerRepositoryTest {

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getManufacturerByName_WhenFound() {
        // Given
        String name = "Manufacturer";
        Manufacturer expectedManufacturer = new Manufacturer();

        when(manufacturerRepository.getManufacturerByName(name))
                .thenReturn(expectedManufacturer);

        // When
        Manufacturer manufacturer = manufacturerRepository.getManufacturerByName(name);

        // Then
        assertNotNull(manufacturer);
        assertEquals(expectedManufacturer, manufacturer);
        verify(manufacturerRepository, times(1)).getManufacturerByName(name);
    }

    @Test
    void getManufacturerByName_WhenNotFound() {
        // Given
        String name = "NonExistentManufacturer";

        when(manufacturerRepository.getManufacturerByName(name))
                .thenReturn(null);

        // When
        Manufacturer manufacturer = manufacturerRepository.getManufacturerByName(name);

        // Then
        assertNull(manufacturer);
        verify(manufacturerRepository, times(1)).getManufacturerByName(name);
    }
}