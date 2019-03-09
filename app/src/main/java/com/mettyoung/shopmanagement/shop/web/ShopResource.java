package com.mettyoung.shopmanagement.shop.web;

import com.mettyoung.shopmanagement.shop.Shop;
import com.mettyoung.shopmanagement.shop.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ShopResource {

    private static final String IMPORT_SHOPS = "import-shops";

    @Autowired
    private ShopRepository shopRepository;

    @PostMapping(value = IMPORT_SHOPS, consumes = "text/csv")
    public Set<Shop> importShops(@RequestBody ImportedShopList importedShops) {
        importedShops.stream().map(ImportedShop::toModel).forEach(shopRepository::save);
        return shopRepository.findAll();
    }
}
