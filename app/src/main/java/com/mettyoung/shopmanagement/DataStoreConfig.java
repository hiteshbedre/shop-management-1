package com.mettyoung.shopmanagement;

import com.mettyoung.shopmanagement.shop.ShopRepository;
import com.mettyoung.shopmanagement.shop.ShopRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataStoreConfig {

    @Bean
    public ShopRepository shopRepository() {
        return new ShopRepositoryImpl();
    }
}
