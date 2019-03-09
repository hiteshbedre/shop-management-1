package com.mettyoung.shopmanagement.shop;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ShopRepositoryImpl implements ShopRepository {

    private Set<Shop> shops;

    public ShopRepositoryImpl() {
        shops = new LinkedHashSet<>();
    }

    @Override
    public Set<Shop> saveAll(Set<Shop> shops) {
        this.shops.clear();
        this.shops.addAll(shops);
        return this.shops;
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
