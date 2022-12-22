package br.com.mildevs.carParts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mildevs.carParts.dto.PartInputDTO;
import br.com.mildevs.carParts.dto.PartReadingDTO;
import br.com.mildevs.carParts.dto.PartUpdateDTO;
import br.com.mildevs.carParts.service.PartService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/parts")
public class PartController {

    @Autowired
    private PartService service;

    @GetMapping
    public List<PartReadingDTO> readAllParts() {
        return service.readAllParts();
    }

    @GetMapping("/{barCode}")
    public ResponseEntity<PartReadingDTO> readThisPart(@PathVariable long barCode) {
        return service.readThisPart(barCode);
    }

    @GetMapping("/{initialChar}/start")
    public List<PartReadingDTO> readAllPartsByFirstLetter(@PathVariable String initialChar) {
        return service.readAllPartsByFirstLetter(initialChar);
    }

    @GetMapping("/{carModel}/model")
    public List<PartReadingDTO> readAllPartsByCarModel(@PathVariable String carModel) {
        return service.readAllPartsByCarModel(carModel);
    }

    @GetMapping("/{category}/category")
    public List<PartReadingDTO> readAllPartsByCategory(@PathVariable String category) {
        return service.readAllPartsByCategory(category);
    }

    @PostMapping
    public ResponseEntity<PartReadingDTO> createPart(@Valid @RequestBody PartInputDTO inputDTO) {
        return service.createPart(inputDTO.toFullPart());
    }

    @PutMapping("/{barCode}")
    public ResponseEntity<PartReadingDTO> updatePart(@PathVariable long barCode, @Valid @RequestBody PartUpdateDTO updateDTO) {
        return service.updatePart(updateDTO, barCode);
    }

    @DeleteMapping("/{barCode}")
    public void deletePart(@PathVariable long barCode) {
        service.deletePart(barCode);
    }

}