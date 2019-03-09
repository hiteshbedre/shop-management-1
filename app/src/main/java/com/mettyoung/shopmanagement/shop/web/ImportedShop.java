package com.mettyoung.shopmanagement.shop.web;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

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
}
