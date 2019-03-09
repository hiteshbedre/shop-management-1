package com.mettyoung.shopmanagement.shop.web;

import com.mettyoung.shopmanagement.shop.Shop;
import com.mettyoung.shopmanagement.shop.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestController
public class ShopResource {

    private static final String IMPORT_SHOPS = "import-shops";
    private static final String FETCH_SHOPS = "fetch-shops";

    private ShopRepository shopRepository;

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @PostMapping(value = IMPORT_SHOPS, consumes = "text/csv")
    public Set<Shop> importShops(@RequestBody ImportedShopList importedShops) {
        // Replace all shops with the imported shop list.
        return shopRepository.saveAll(importedShops.stream().map(ImportedShop::toModel).collect(toSet()));
    }

    @GetMapping(value = FETCH_SHOPS)
    public Set<Shop> fetchShops(@RequestParam(value = "date", required = false)
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return date != null ? shopRepository.findActiveShops(date) : shopRepository.findAll();
    }
}
