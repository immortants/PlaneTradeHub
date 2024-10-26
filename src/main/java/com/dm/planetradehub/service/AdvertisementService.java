package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.entity.Aircraft;
import com.dm.planetradehub.entity.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AdvertisementService {
    Page<Advertisement> getAllAdvertisements(Pageable pageable);

    Page<Advertisement> getMyAdvertisements(Pageable pageable, Authentication authentication);

    Page<Advertisement> findAdvertisementsByParameters(Advertisement advertisement, Pageable pageable);

    Page<Advertisement> findAdvertisementsBy(String type, String manufacturer, String model, int year, Pageable pageable);

    Advertisement getAdvertisementById(Long id);

    Advertisement addAdvertisement(Advertisement advertisement, Aircraft aircraft, List<MultipartFile> imageFiles, Authentication authentication) throws IOException;

    Advertisement updateAdvertisement(Advertisement advertisement, List<MultipartFile> imageFiles, Authentication authentication) throws IOException;

    Advertisement saveAdvertisement(Advertisement advertisement);

    void deleteAdvertisementById(Long id);

    Optional<Gallery> getAdvertisementImage(Long advertisementId, Long imageId);
}
