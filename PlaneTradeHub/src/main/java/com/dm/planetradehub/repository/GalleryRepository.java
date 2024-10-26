package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    Optional<Gallery> findByAdvertisement_IdAndId(Long advertisementId, Long imageId);
}
