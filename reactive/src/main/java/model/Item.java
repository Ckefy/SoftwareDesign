package model;

import org.bson.Document;

public class Item {
    public final long id;
    public final Currency currency;
    public final String name;
    public final double price;


    public Item(Document doc) {
        this(
                doc.getLong("id"),
                Currency.valueOf(doc.getString("currency")),
                doc.getString("name"),
                doc.getDouble("price")
        );
    }

    public Item(long id, Currency currency, String name, double price) {
        this.id = id;
        this.currency = currency;
        this.name = name;
        this.price = price;
    }

    public Item convert (Currency customerCurrency) {
        return new Item(this.id, customerCurrency, this.name,
                this.currency.multi(customerCurrency) * this.price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                "}\n";
    }

    public Document extractDocument() {
        return new Document()
                .append("id", this.id)
                .append("currency", currency.toString())
                .append("name", this.name)
                .append("price", this.price);
    }
}
