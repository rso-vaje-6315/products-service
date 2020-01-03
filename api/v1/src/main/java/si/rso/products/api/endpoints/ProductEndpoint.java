package si.rso.products.api.endpoints;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import si.rso.products.lib.Product;
import si.rso.products.services.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
@GraphQLClass
public class ProductEndpoint {

    @Inject
    private ProductService productService;

    @GraphQLQuery
    @Timeout
    @Retry
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GraphQLQuery
    @Timeout
    @Retry
    public Product getProduct(@GraphQLArgument(name = "productId") String productId) {
        return productService.getProduct(productId);
    }
}
