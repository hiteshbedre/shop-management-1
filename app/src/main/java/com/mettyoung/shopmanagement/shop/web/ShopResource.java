package com.mettyoung.shopmanagement.shop.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopResource {

    private static final String IMPORT_SHOPS = "import-shops";

    @PostMapping(value = IMPORT_SHOPS, consumes = "text/csv")
    public ImportedShopList importShops(@RequestBody ImportedShopList importedShops) {
        return importedShops;
    }
}
