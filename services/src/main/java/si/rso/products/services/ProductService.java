package si.rso.products.services;

import si.rso.products.lib.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProduct(String productId);
}
