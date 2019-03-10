package com.mettyoung.shopmanagement.shop;

import java.time.LocalDate;
import java.util.Set;

public interface ShopRepository {

    Set<Shop> saveAll(Set<Shop> shops);

    Set<Shop> findActiveShops(LocalDate date);

    Set<Shop> findAll();
}
