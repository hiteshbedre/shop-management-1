package com.mettyoung.shopmanagement.shop.web;

import com.mettyoung.shopmanagement.shop.Shop;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.time.ZoneId;
import java.util.Date;

@Data
public class ImportedShop {

    @CsvBindByName(column = "shop")
    private long number;

    @CsvBindByName(column = "start_date")
    @CsvDate("yyyyMMdd")
    private Date startDate;

    @CsvBindByName(column = "end_date")
    @CsvDate("yyyyMMdd")
    private Date endDate;

    public Shop toModel() {
        Shop shop = new Shop();
        shop.setNumber(number);
        shop.setStartDate(startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        shop.setEndDate(endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        return shop;
    }
}
