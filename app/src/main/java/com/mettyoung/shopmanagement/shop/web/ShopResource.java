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

    private ShopRepository shopRepository;

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @PostMapping(value = IMPORT_SHOPS, consumes = "text/csv")
    public Set<Shop> importShops(@RequestBody ImportedShopList importedShops) {
        // Clear all shops on upload CSV.
        shopRepository.deleteAll();
        // Save all shops to in-memory repository.
        importedShops.stream().map(ImportedShop::toModel).forEach(shopRepository::save);
        // Return all shops from in-memory repository.
        return shopRepository.findAll();
    }
}
