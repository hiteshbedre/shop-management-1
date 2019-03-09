package com.mettyoung.shopmanagement.shop;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ShopRepositoryImpl implements ShopRepository {

    private Set<Shop> shops;

    public ShopRepositoryImpl() {
        shops = new HashSet<>();
    }

    @Override
    public Shop save(Shop shop) {
        shops.add(shop);
        return shop;
    }

    @Override
    public Set<Shop> findActiveShops(LocalDate date) {
        return shops.stream().filter(shop -> shop.isActive(date)).collect(toSet());
    }

    @Override
    public Set<Shop> findAll() {
        return shops;
    }
}
