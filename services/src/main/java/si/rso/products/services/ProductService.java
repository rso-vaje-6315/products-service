package si.rso.products.services;

import com.kumuluz.ee.graphql.classes.Filter;
import si.rso.products.lib.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> getProducts(Filter filter);

    Product getProduct(String productId);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    Product deleteProduct(String productId);

    void updateImage(String productId, byte[] imageBytes, String mimeType) throws IOException;
}
