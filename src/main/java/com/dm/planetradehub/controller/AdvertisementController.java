package com.dm.planetradehub.controller;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.entity.Gallery;
import com.dm.planetradehub.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AdvertisementController {
    private final AdvertisementService advertisementService;
    private final TypeService typeService;
    private final ManufacturerService manufacturerService;
    private final ModelService modelService;

    public AdvertisementController(AdvertisementService advertisementService, TypeService typeService, ManufacturerService manufacturerService, ModelService modelService) {
        this.advertisementService = advertisementService;
        this.typeService = typeService;
        this.manufacturerService = manufacturerService;
        this.modelService = modelService;
    }

    @GetMapping("/")
    public String homePage(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(2);

        Page<Advertisement> advertisementPage = advertisementService.getAllAdvertisements(PageRequest.of(currentPage - 1, pageSize));

        int totalPages = advertisementPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("advertisements", advertisementPage);

        return "index";
    }

    @GetMapping("/{advertisementId}/image/{imageId}")
    public ResponseEntity<byte[]> getAdvertisementImage(@PathVariable Long advertisementId,
                                                        @PathVariable Long imageId) {
        Optional<Gallery> advertisementImageOptional = advertisementService.getAdvertisementImage(advertisementId, imageId);

        if (advertisementImageOptional.isPresent()) {
            Gallery advertisementImage = advertisementImageOptional.get();
            byte[] imageBytes = Base64.getDecoder().decode(advertisementImage.getImage());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new byte[0], HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/advertisements")
    public String advertisements(@RequestParam(value = "type", required = false) String type,
                                 @RequestParam(value = "manufacturer", required = false) String manufacturer,
                                 @RequestParam(value = "model", required = false) String modelOfAircraft,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size,
                                 Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(2);

        Page<Advertisement> advertisementPage = advertisementService.findAdvertisementsBy(type, manufacturer, modelOfAircraft, 0, PageRequest.of(currentPage - 1, pageSize));

        int totalPages = advertisementPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("type", type);
        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("model", modelOfAircraft);
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        model.addAttribute("models", modelService.getModelsByManufacturer(manufacturer));
        model.addAttribute("advertisements", advertisementPage);

        return "advertisements";
    }

    @GetMapping("/advertisements/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        model.addAttribute("advertisement", advertisementService.getAdvertisementById(id));

        return "details";
    }
}
