package si.rso.products.api.endpoints;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.kumuluz.ee.graphql.classes.Filter;
import com.kumuluz.ee.logs.cdi.Log;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import si.rso.products.lib.Product;
import si.rso.products.services.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@Log
@RequestScoped
@GraphQLClass
public class ProductEndpoint {
    
    @Inject
    private ProductService productService;
    
    @GraphQLQuery
    public List<Product> getAllProducts(@GraphQLArgument(name = "filters") Filter filter) {
        return productService.getProducts(filter);
    }
    
    @GraphQLQuery
    public Product getProduct(@GraphQLArgument(name = "productId") String productId) {
        return productService.getProduct(productId);
    }
}
