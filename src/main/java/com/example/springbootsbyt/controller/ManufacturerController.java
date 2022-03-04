package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.Manufacturers;
import com.example.springbootsbyt.service.impl.ManufacturerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManufacturerController {
    private final ManufacturerServiceImpl manufacturerServiceImpl;

    public ManufacturerController(ManufacturerServiceImpl manufacturerServiceImpl) {
        this.manufacturerServiceImpl = manufacturerServiceImpl;
    }

    @GetMapping("/create-model")
    public String createModelsForm(Manufacturers manufacturers) {
        return "printers-list";
    }

    @PostMapping("/create-model")
    public String createModel(Manufacturers manufacturers) {
        manufacturerServiceImpl.saveModels(manufacturers);
        return "redirect:/printers";
    }

    @GetMapping("/models-delete/{idModels}")
    public String deleteModels(@PathVariable("idModels") long id) {
        manufacturerServiceImpl.deleteById(id);
        return "redirect:/printers";
    }
}

