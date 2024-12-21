package com.batu.common.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private BigDecimal amount;

    private static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money(Double amount){
        this.amount = BigDecimal.valueOf(amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isGreaterThenZero(){
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThen(Money money){
        return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
    }

    public Money add(Money money){
        return new Money(setScale(this.amount.add(money.getAmount())));
    }

    public Money subtract(Money money){
        return new Money(this.amount.subtract(money.getAmount()));
    }

    public Money multiply(Money money){
        return new Money(this.amount.multiply(money.getAmount()));
    }

    public Money divide(Money money){
        return new Money(this.amount.divide(money.getAmount()));
    }

    // Is diversion suitable for this class ?  Money : divide(Money val)

    private BigDecimal setScale(BigDecimal val){
        return val.setScale(2, RoundingMode.HALF_EVEN);
    }
}
