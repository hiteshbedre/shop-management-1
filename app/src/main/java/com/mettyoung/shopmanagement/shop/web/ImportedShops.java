package com.mettyoung.shopmanagement.shop.web;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.Valid;
import java.util.List;

@Data
public class ImportedShops {

    public ImportedShops(List<ImportedShop> shops) {
        this.shops = shops;
    }

    @Valid
    @UniqueElements(message = "must only contain unique shop numbers")
    private List<ImportedShop> shops;
}
