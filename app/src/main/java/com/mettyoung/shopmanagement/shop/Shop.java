package com.mettyoung.shopmanagement.shop;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "number")
public class Shop {
    private long number;
    private LocalDate startDate;
    private LocalDate endDate;

    public boolean isActive(LocalDate date) {
        return (startDate.isBefore(date) || startDate.equals(date)) && (date.equals(endDate) || date.isBefore(endDate));
    }
}
