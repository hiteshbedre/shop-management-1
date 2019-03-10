package com.mettyoung.shopmanagement.shop;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toSet;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {

    @Transactional
    default Set<Shop> replaceAll(Set<Shop> shops) {
        deleteAll();
        return StreamSupport.stream(saveAll(shops).spliterator(), false).collect(toSet());
    }

    @Query("SELECT s from Shop s WHERE s.startDate <= ?1 AND ?1 <= s.endDate")
    Set<Shop> findActiveShops(LocalDate date);

    default Set<Shop> findAll() {
        return StreamSupport.stream(findAll().spliterator(), false).collect(toSet());
    }
}
