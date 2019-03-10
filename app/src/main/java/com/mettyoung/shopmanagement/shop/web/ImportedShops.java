package com.mettyoung.shopmanagement.shop.web;

import com.mettyoung.shopmanagement.common.model.ListContainer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
public class ImportedShops implements ListContainer<ImportedShop> {

    public ImportedShops(List<ImportedShop> shops) {
        this.shops = shops;
    }

    @Valid
    @UniqueElements(message = "must only contain unique shop numbers")
    private List<ImportedShop> shops;

    @Override
    public void setItems(List<ImportedShop> shops) {
        this.shops = shops;
    }
}
