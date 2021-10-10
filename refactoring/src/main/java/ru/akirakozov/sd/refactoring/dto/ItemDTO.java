package ru.akirakozov.sd.refactoring.dto;

import java.util.Objects;

public class ItemDTO {
    private final String name;
    private final int price;

    public ItemDTO(String name, int price) {
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
        ItemDTO item = (ItemDTO) obj;
        return item.price == this.price && Objects.equals(item.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.price);
    }
}
