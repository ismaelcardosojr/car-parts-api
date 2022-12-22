package br.com.mildevs.carParts.dto;

import org.springframework.beans.BeanUtils;

import br.com.mildevs.carParts.enumeration.Category;
import br.com.mildevs.carParts.model.Part;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PartInputDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String carModel;

    @NotBlank
    private String manufacturer;

    @NotNull
    private double expenseValue;

    @NotNull
    private double saleValue;

    @NotNull
    private int inStockNum;

    @NotNull
    private String category;

    public Part toFullPart() {
        Part newPart = new Part();

        newPart = this.standardizingCategory(newPart);
        BeanUtils.copyProperties(this, newPart);

        return newPart;
    }

    private Part standardizingCategory(Part newPart) {
        String categoryStandard = this.category.toUpperCase();
        Category newCategory = Category.valueOf(categoryStandard);
        newPart.setCategory(newCategory);

        return newPart;
    }

}