package com.mettyoung.shopmanagement.shop.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImportedShopsTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void should_limit_the_end_date_of_each_imported_shop_to_future_dates() {
        ImportedShop validImportedShop = new ImportedShop();
        validImportedShop.setNumber(1L);
        validImportedShop.setStartDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        validImportedShop.setEndDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        ImportedShop invalidImportedShop = new ImportedShop();
        invalidImportedShop.setNumber(2L);
        invalidImportedShop.setStartDate(Date.from(LocalDate.now().minusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        invalidImportedShop.setEndDate(Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        ImportedShops importedShops = new ImportedShops(Arrays.asList(validImportedShop, invalidImportedShop));

        Set<String> violations = getInvalidPropertyPaths(validator.validate(importedShops));
        assertEquals(1, violations.size());
        assertThat(violations).containsExactlyInAnyOrder("shops[1].endDate");
    }

    @Test
    public void should_validate_all_fields_of_each_imported_shop_be_not_empty() {
        ImportedShop validImportedShop = new ImportedShop();
        validImportedShop.setNumber(1L);
        validImportedShop.setStartDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        validImportedShop.setEndDate(Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        ImportedShop invalidImportedShop = new ImportedShop();

        ImportedShops importedShops = new ImportedShops(Arrays.asList(validImportedShop, invalidImportedShop));

        Set<String> violations = getInvalidPropertyPaths(validator.validate(importedShops));
        assertEquals(3, violations.size());
        assertThat(violations).containsExactlyInAnyOrder("shops[1].number", "shops[1].startDate", "shops[1].endDate");
    }

    @Test
    public void should_restrict_the_start_date_of_each_imported_shop_be_no_later_than_end_date() {
        ImportedShop validImportedShop = new ImportedShop();
        validImportedShop.setNumber(1L);
        validImportedShop.setStartDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        validImportedShop.setEndDate(Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        ImportedShop invalidImportedShop = new ImportedShop();
        invalidImportedShop.setNumber(2L);
        invalidImportedShop.setStartDate(Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        invalidImportedShop.setEndDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        ImportedShops importedShops = new ImportedShops(Arrays.asList(validImportedShop, invalidImportedShop));

        Set<String> violations = getInvalidPropertyPaths(validator.validate(importedShops));
        assertEquals(1, violations.size());
        assertThat(violations).containsExactlyInAnyOrder("shops[1]");
    }

    @Test
    public void should_allow_duplicate_date_range() {
        ImportedShop importedShop_1 = new ImportedShop();
        importedShop_1.setNumber(1L);
        importedShop_1.setStartDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        importedShop_1.setEndDate(Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        ImportedShop importedShop_2 = new ImportedShop();
        importedShop_2.setNumber(2L);
        importedShop_2.setStartDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        importedShop_2.setEndDate(Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        ImportedShops importedShops = new ImportedShops(Arrays.asList(importedShop_1, importedShop_2));
        assertEquals(0, validator.validate(importedShops).size());
    }

    @Test
    public void should_not_allow_duplicate_shop_numbers() {
        ImportedShop importedShop_1 = new ImportedShop();
        importedShop_1.setNumber(1L);
        importedShop_1.setStartDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        importedShop_1.setEndDate(Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        ImportedShop importedShop_2 = new ImportedShop();
        importedShop_2.setNumber(1L);
        importedShop_2.setStartDate(Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        importedShop_2.setEndDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        ImportedShops importedShops = new ImportedShops(Arrays.asList(importedShop_1, importedShop_2));

        Set<ConstraintViolation<ImportedShops>> violations = validator.validate(importedShops);
        assertEquals(1, violations.size());
        assertEquals("must only contain unique shop numbers", violations.iterator().next().getMessage());
    }

    private Set<String> getInvalidPropertyPaths(Set<ConstraintViolation<ImportedShops>> violations) {
        return violations.stream().map(t -> t.getPropertyPath().toString()).collect(toSet());
    }
}
