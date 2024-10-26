package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.Gallery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GalleryRepositoryTest {

    @Mock
    private GalleryRepository galleryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByAdvertisement_IdAndId_WhenFound() {
        // Given
        Long advertisementId = 1L;
        Long imageId = 1L;
        Gallery expectedGallery = new Gallery();

        when(galleryRepository.findByAdvertisement_IdAndId(advertisementId, imageId))
                .thenReturn(Optional.of(expectedGallery));

        // When
        Optional<Gallery> gallery = galleryRepository.findByAdvertisement_IdAndId(advertisementId, imageId);

        // Then
        assertTrue(gallery.isPresent());
        assertEquals(expectedGallery, gallery.get());
        verify(galleryRepository, times(1)).findByAdvertisement_IdAndId(advertisementId, imageId);
    }

    @Test
    void findByAdvertisement_IdAndId_WhenNotFound() {
        // Given
        Long advertisementId = 1L;
        Long imageId = 1L;

        when(galleryRepository.findByAdvertisement_IdAndId(advertisementId, imageId))
                .thenReturn(Optional.empty());

        // When
        Optional<Gallery> gallery = galleryRepository.findByAdvertisement_IdAndId(advertisementId, imageId);

        // Then
        assertFalse(gallery.isPresent());
        verify(galleryRepository, times(1)).findByAdvertisement_IdAndId(advertisementId, imageId);
    }
}