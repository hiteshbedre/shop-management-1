package com.mettyoung.shopmanagement.shop;

import java.time.LocalDate;
import java.util.Set;

public interface ShopRepository {

    Shop save(Shop shop);
    Set<Shop> findActiveShops(LocalDate date);
    Set<Shop> findAll();
    void deleteAll();
}
