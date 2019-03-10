package com.mettyoung.shopmanagement.common.model;

import java.util.List;

public interface ListContainer<T> {
    void setItems(List<T> items);
}
