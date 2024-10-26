package com.dm.planetradehub.controller;

import com.dm.planetradehub.entity.*;
import com.dm.planetradehub.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProfileController {
    private final UserService userService;
    private final AdvertisementService advertisementService;
    private final TypeService typeService;
    private final ManufacturerService manufacturerService;
    private final ModelService modelService;
    private final AircraftService aircraftService;


    public ProfileController(UserService userService, AdvertisementService advertisementService, TypeService typeService, ManufacturerService manufacturerService, ModelService modelService, AircraftService aircraftService) {
        this.userService = userService;
        this.advertisementService = advertisementService;
        this.typeService = typeService;
        this.manufacturerService = manufacturerService;
        this.modelService = modelService;
        this.aircraftService = aircraftService;
    }

    @GetMapping("/profile")
    public String getProfile(Authentication authentication, Model model){

        model.addAttribute("user", userService.getUserByEmail(authentication.getName()));
        return "profile";
    }

    @PostMapping("/profile/edit")
    public String edit(@ModelAttribute User updatedUser, Authentication authentication){
        userService.updateUser(updatedUser, authentication);

        return "redirect:/profile";
    }

    @GetMapping("/myAdvertisements")
    public String myAdvertisements(@RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size,
                                   Model model, Authentication authentication){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(2);

        Page<Advertisement> advertisementPage = advertisementService.getMyAdvertisements(PageRequest.of(currentPage - 1, pageSize), authentication);

        int totalPages = advertisementPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("advertisements", advertisementPage);

        return "myAdvertisements";
    }

    @GetMapping("/myAdvertisement/{id}")
    public String updateMyAdvertisement(@PathVariable Long id, Model model){
        model.addAttribute("advertisement", advertisementService.getAdvertisementById(id));
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("aircraft", advertisementService.getAdvertisementById(id).getAircraft());

        return "updateAdvertisement";
    }

    @PostMapping("/myAdvertisement")
    public String updateMyAdvertisement(@ModelAttribute Advertisement advertisement,
                                        @RequestParam("imageFiles") List<MultipartFile> imageFiles,
                                        Authentication authentication) throws IOException {
        advertisementService.updateAdvertisement(advertisement, imageFiles, authentication);
        return "redirect:/myAdvertisements";
    }

    @GetMapping("/myAdvertisements/{id}")
    public String deleteMyAdvertisement(@PathVariable Long id){
        advertisementService.deleteAdvertisementById(id);

        return "redirect:/myAdvertisements";
    }

    @GetMapping("/advertisement")
    public String addAdvertisement(Model model) {
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("advertisement", new Advertisement());
        model.addAttribute("aircraft", new Aircraft());
        return "createAdvertisement";
    }

    @PostMapping("/advertisement")
    public String addProduct(@ModelAttribute Advertisement advertisement,
                             @ModelAttribute Aircraft aircraft,
                             @RequestParam("imageFiles") List<MultipartFile> imageFiles,
                             Authentication authentication)
            throws IOException {
        advertisementService.addAdvertisement(advertisement, aircraft, imageFiles, authentication);
        return "redirect:/myAdvertisements";
    }

    @GetMapping("/aircrafts")
    public String getAircrafts(Model model){
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("type", new Type());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("model", new com.dm.planetradehub.entity.Model());
        return "aircrafts";
    }

    @PostMapping("/aircrafts/type")
    public String addNewType(@ModelAttribute Type type){
        typeService.saveType(type);
        return "redirect:/aircrafts";
    }

    @PostMapping("/aircrafts/manufacturer")
    public String addNewManufacturer(@ModelAttribute Manufacturer manufacturer){
        manufacturerService.saveManufacturer(manufacturer);
        return "redirect:/aircrafts";
    }

    @PostMapping("/aircrafts/model")
    public String addNewModel(@ModelAttribute com.dm.planetradehub.entity.Model model){
        modelService.saveModel(model);
        return "redirect:/aircrafts";
    }

    @GetMapping("/aircrafts/type/{id}")
    public String deleteNewType(@PathVariable Long id){
        typeService.deleteTypeById(id);
        return "redirect:/aircrafts";
    }

    @GetMapping("/aircrafts/manufacturer/{id}")
    public String deleteNewManufacturer(@PathVariable Long id){
        manufacturerService.deleteManufacturerById(id);
        return "redirect:/aircrafts";
    }

    @GetMapping("/aircrafts/model/{id}")
    public String deleteNewModel(@PathVariable Long id){
        modelService.deleteModelById(id);
        return "redirect:/aircrafts";
    }
}
