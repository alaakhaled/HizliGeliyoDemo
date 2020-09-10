package com.aabusabra.hizligeliyodemo.comm.ResultLists;


import com.aabusabra.hizligeliyodemo.comm.ProductResult;
import com.aabusabra.hizligeliyodemo.comm.Result;
import java.util.List;


public class ProductResultList extends Result {
    private List<ProductResult> all_products;

    public List<ProductResult> getAllProducts() {
        return all_products;
    }

    public void setAllProducts(List<ProductResult> all_products) {
        this.all_products = all_products;
    }

}
