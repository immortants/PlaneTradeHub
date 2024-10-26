package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdvertisementRepositoryTest {

    @Mock
    private AdvertisementRepository advertisementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAdvertisementsByParameters() {
        // Given
        String type = "Type";
        String manufacturer = "Manufacturer";
        String model = "Model";
        int year = 2022;
        Pageable pageable = Pageable.unpaged();
        Page<Advertisement> expectedAdvertisements = new PageImpl<>(List.of(new Advertisement()));

        when(advertisementRepository.findAdvertisementsByParameters(type, manufacturer, model, year, pageable))
                .thenReturn(expectedAdvertisements);

        // When
        Page<Advertisement> advertisements = advertisementRepository.findAdvertisementsByParameters(type, manufacturer, model, year, pageable);

        // Then
        assertNotNull(advertisements);
        assertEquals(expectedAdvertisements, advertisements);
        verify(advertisementRepository, times(1)).findAdvertisementsByParameters(type, manufacturer, model, year, pageable);
    }

    @Test
    void findAdvertisementById() {
        // Given
        Long id = 1L;
        Advertisement expectedAdvertisement = new Advertisement();

        when(advertisementRepository.findAdvertisementById(id))
                .thenReturn(expectedAdvertisement);

        // When
        Advertisement advertisement = advertisementRepository.findAdvertisementById(id);

        // Then
        assertNotNull(advertisement);
        assertEquals(expectedAdvertisement, advertisement);
        verify(advertisementRepository, times(1)).findAdvertisementById(id);
    }

    @Test
    void findAdvertisementsByUser() {
        // Given
        User user = new User();
        Pageable pageable = Pageable.unpaged();
        Page<Advertisement> expectedAdvertisements = new PageImpl<>(List.of(new Advertisement()));

        when(advertisementRepository.findAdvertisementsByUser(user, pageable))
                .thenReturn(expectedAdvertisements);

        // When
        Page<Advertisement> advertisements = advertisementRepository.findAdvertisementsByUser(user, pageable);

        // Then
        assertNotNull(advertisements);
        assertEquals(expectedAdvertisements, advertisements);
        verify(advertisementRepository, times(1)).findAdvertisementsByUser(user, pageable);
    }

    @Test
    void findAll() {
        // Given
        Pageable pageable = Pageable.unpaged();
        Page<Advertisement> expectedAdvertisements = new PageImpl<>(List.of(new Advertisement()));

        when(advertisementRepository.findAll(pageable))
                .thenReturn(expectedAdvertisements);

        // When
        Page<Advertisement> advertisements = advertisementRepository.findAll(pageable);

        // Then
        assertNotNull(advertisements);
        assertEquals(expectedAdvertisements, advertisements);
        verify(advertisementRepository, times(1)).findAll(pageable);
    }
}