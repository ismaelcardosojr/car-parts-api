package br.com.mildevs.carParts.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.mildevs.carParts.enumeration.Category;
import br.com.mildevs.carParts.model.Part;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PartReadingDTO {

    private long barCode;
    private String name;
    private String carModel;
    private String manufacturer;
    private double saleValue;
    private int inStockNum;
    private Category category;

    public PartReadingDTO toReadingDTO(Part thisPart) {
        PartReadingDTO newReadingDTO = new PartReadingDTO();
        BeanUtils.copyProperties(thisPart, newReadingDTO);
        
        return newReadingDTO;
    }
    
    public List<PartReadingDTO> toReadingDTOList(List<Part> fullPartList) {
        List<PartReadingDTO> readingDTOList = new ArrayList<>();

        for (Part thisPart : fullPartList) {
            readingDTOList.add(this.toReadingDTO(thisPart));
        }

        return readingDTOList;
    }

}