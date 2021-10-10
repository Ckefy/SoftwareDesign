package ru.akirakozov.sd.refactoring.entities.product.dto;

import java.util.Objects;

public class ProductDTO {
    private final String name;
    private final int price;

    public ProductDTO(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        ProductDTO item = (ProductDTO) obj;
        return item.price == this.price && Objects.equals(item.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.price);
    }
}
