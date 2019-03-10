package com.mettyoung.shopmanagement;

import com.mettyoung.shopmanagement.shop.ShopRepository;
import com.mettyoung.shopmanagement.shop.ShopRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DataStoreConfig {

    @Bean
    ShopRepository shopRepository() {
        return new ShopRepositoryImpl();
    }
}
