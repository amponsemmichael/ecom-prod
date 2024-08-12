package com.amponsem.ecom_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    private int id;
    private String name;
    private String desc;
    private String brand;
    private BigDecimal price;
    private String category;

    private Date releaseDate;
    private int quantity;
    private Boolean available;

}
