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
    private String name;

    @CsvBindByName(column = "start_date")
    @CsvDate("yyyy-MM-dd")
    private Date startDate;

    @CsvBindByName(column = "end_date")
    @CsvDate("yyyy-MM-dd")
    private Date endDate;

    public Shop toModel() {
        Shop shop = new Shop();
        shop.setName(name);
        shop.setStartDate(startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        shop.setEndDate(endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        return shop;
    }
}
