package hibernate.shop;

        import java.math.BigDecimal;

public class Price {
    BigDecimal nettoPrice;
    BigDecimal grossPrice;

    public BigDecimal getNettoPrice() {
        return nettoPrice;
    }

    public void setNettoPrice(BigDecimal nettoPrice) {
        this.nettoPrice = nettoPrice;
    }

    public BigDecimal getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(BigDecimal grossPrice) {
        this.grossPrice = grossPrice;
    }
}

// tworzy instancje z bezargumentowego konstruktora i wypelnia pola 0 lub null
// potem zaciagajac wartosci z baz danych ustawia pola za pomoca seterow