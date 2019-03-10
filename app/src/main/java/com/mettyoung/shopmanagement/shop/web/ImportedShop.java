package com.mettyoung.shopmanagement.shop.web;

import com.mettyoung.shopmanagement.common.model.DateRange;
import com.mettyoung.shopmanagement.common.validator.StartDateNoLaterThanEndDate;
import com.mettyoung.shopmanagement.shop.Shop;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.ZoneId;
import java.util.Date;

@StartDateNoLaterThanEndDate
@Data
@EqualsAndHashCode(of = "number")
public class ImportedShop implements DateRange {

    @NotNull
    @CsvBindByName(column = "shop")
    private Long number;

    @NotNull
    @CsvBindByName(column = "start_date")
    @CsvDate("yyyyMMdd")
    private Date startDate;

    @NotNull
    @Future
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
