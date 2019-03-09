package com.mettyoung.shopmanagement.shop.web;

import java.util.ArrayList;

// Had to do this to circumvent Java's limitation of getting the parameterized type due to type erasure.
public class ImportedShopList extends ArrayList<ImportedShop> {
}
