package br.com.mildevs.carParts.model;

import br.com.mildevs.carParts.enumeration.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Part {
    
    @Id @GeneratedValue
    @Column(name = "bar_code")
    private long barCode;

    @Column(nullable = false)
    private String name;

    @Column(name = "car_model", nullable = false)
    private String carModel;

    @Column(nullable = false)
    private String manufacturer;

    @Column(name = "expense_value", nullable = false)
    private double expenseValue;

    @Column(name = "sale_value", nullable = false)
    private double saleValue;

    @Column(name = "in_stock_num", nullable = false)
    private int inStockNum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

}