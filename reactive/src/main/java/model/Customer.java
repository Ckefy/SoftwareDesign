package model;

import org.bson.Document;

public class Customer {
    public final long id;
    public final Currency currency;
    public final String login;


    public Customer(Document doc) {
        this(
                doc.getLong("id"),
                Currency.valueOf(doc.getString("currency")),
                doc.getString("login")
        );
    }

    public Customer(long id, Currency currency, String login) {
        this.id = id;
        this.currency = currency;
        this.login = login;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", login='" + login + '\'' +
                "}\n";
    }

    public Document extractDocument() {
        return new Document()
                .append("id", this.id)
                .append("currency", currency.toString())
                .append("login", this.login);
    }
}
