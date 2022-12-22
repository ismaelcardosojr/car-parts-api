package br.com.mildevs.carParts.dto;

import org.springframework.beans.BeanUtils;

import br.com.mildevs.carParts.model.Part;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PartUpdateDTO {

    @NotNull
    private double expenseValue;

    @NotNull
    private double saleValue;

    @NotNull
    private int inStockNum;

    public Part toFullPart(Part partBeingUpdated) {
        BeanUtils.copyProperties(this, partBeingUpdated);
        return partBeingUpdated;
    }

}