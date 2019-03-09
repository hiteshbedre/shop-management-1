package com.mettyoung.shopmanagement.shop.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopResource {

    private static final String IMPORT_SHOPS = "import-shops";

    @PostMapping(IMPORT_SHOPS)
    public String importShops() {
        return "hello";
    }
}
