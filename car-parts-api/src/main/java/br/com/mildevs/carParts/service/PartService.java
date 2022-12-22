package br.com.mildevs.carParts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mildevs.carParts.dto.PartReadingDTO;
import br.com.mildevs.carParts.dto.PartUpdateDTO;
import br.com.mildevs.carParts.enumeration.Category;
import br.com.mildevs.carParts.exception.PartNotFoundException;
import br.com.mildevs.carParts.model.Part;
import br.com.mildevs.carParts.repository.PartRepository;

@Service
public class PartService {
    
    @Autowired
    private PartRepository repository;

    public ResponseEntity<PartReadingDTO> readThisPart(long barCode) {
        if (!repository.existsByBarCode(barCode)) {
            throw new PartNotFoundException();
        }

        PartReadingDTO readingDTO = new PartReadingDTO();
        Part fullPart = repository.findByBarCode(barCode);

        return new ResponseEntity<PartReadingDTO>(readingDTO.toReadingDTO(fullPart), HttpStatus.FOUND);
    }

    public List<PartReadingDTO> readAllParts() {
        PartReadingDTO readingDTO = new PartReadingDTO();

        List<Part> fullPartList = repository.findAll();
        List<PartReadingDTO> readingDTOList = readingDTO.toReadingDTOList(fullPartList);

        return readingDTOList;
    }

    public List<PartReadingDTO> readAllPartsByFirstLetter(String initialChar) {
        PartReadingDTO readingDTO = new PartReadingDTO();

        List<Part> fullPartList = repository.findByNameLike(initialChar.toUpperCase() + "%");
        List<PartReadingDTO> readingDTOList = readingDTO.toReadingDTOList(fullPartList);

        return readingDTOList;
    }

    public List<PartReadingDTO> readAllPartsByCarModel(String carModel) {
        PartReadingDTO readingDTO = new PartReadingDTO();

        List<Part> fullPartList = repository.findByCarModel(carModel);
        List<PartReadingDTO> readingDTOList = readingDTO.toReadingDTOList(fullPartList);

        return readingDTOList;
    }

    public List<PartReadingDTO> readAllPartsByCategory(String categoryText) {
        PartReadingDTO readingDTO = new PartReadingDTO();

        String categoryStandard = categoryText.toUpperCase();
        Category thisCategory = Category.valueOf(categoryStandard);

        List<Part> fullPartList = repository.findByCategory(thisCategory);
        List<PartReadingDTO> readingDTOList = readingDTO.toReadingDTOList(fullPartList);

        return readingDTOList;
    }

    public ResponseEntity<PartReadingDTO> createPart(Part thisPart) {
        if (thisPart == null) {
            throw new PartNotFoundException();
        }

        PartReadingDTO readingDTO = new PartReadingDTO();
        Part fullPart = repository.save(thisPart);

        return new ResponseEntity<PartReadingDTO>(readingDTO.toReadingDTO(fullPart), HttpStatus.CREATED);
    }

    public ResponseEntity<PartReadingDTO> updatePart(PartUpdateDTO updateDTO, long barCode) {
        if (updateDTO == null) {
            throw new PartNotFoundException();
        }

        PartReadingDTO readingDTO = new PartReadingDTO();

        Part partBeingUpdated = repository.findByBarCode(barCode);
        Part fullPart = updateDTO.toFullPart(partBeingUpdated);
        Part updatedPart = repository.save(fullPart);

        return new ResponseEntity<PartReadingDTO>(readingDTO.toReadingDTO(updatedPart), HttpStatus.ACCEPTED);
    }

    public void deletePart(long barCode) {
        Part thisPart = repository.findByBarCode(barCode);

        if (thisPart == null) {
            throw new PartNotFoundException();
        }

        repository.delete(thisPart);
    }

}