package com.challenge.longlife.resource;

public class ProductResource{
    private Long id;
    private String name;
    private Double price;
    private String description;

  public Long getId() {
        return id;
    }

    public ProductResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductResource setName(String name) {
        this.name = name;
        return this;
   }

    public Double getPrice() {
        return price;
    }

    public ProductResource setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
