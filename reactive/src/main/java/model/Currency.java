package model;

public enum Currency {
    RUB,
    EUR,
    USD;

    public double multi(Currency customerCurrency) {
        return customerCurrency.multi() / this.multi();
    }

    private double multi() {
        if (this == USD) {
            return 1 / 100.0; // not quite true but I'm optimistic
        } else if (this == EUR) {
            return 1 / 120.0;
        }

        return 1;
    }
}
