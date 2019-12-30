package si.rso.products.api.endpoints;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
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
//    @PermitAll
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GraphQLQuery
//    @RolesAllowed({"user", "admin"})
    public Product getProduct(@GraphQLArgument(name = "productId") String productId) {
        return productService.getProduct(productId);
    }

    //    @RolesAllowed("admin")
    @GraphQLMutation
    public Product createProduct(@GraphQLArgument(name = "product") Product product) {
        return productService.createProduct(product);
    }

    //    @RolesAllowed("admin")
    @GraphQLMutation
    public Product updateProduct(@GraphQLArgument(name = "product") Product product) {
        return productService.updateProduct(product);
    }

    //    @RolesAllowed("admin")
    @GraphQLMutation
    public Product deleteProduct(@GraphQLArgument(name = "productId") String productId) {
        return productService.deleteProduct(productId);
    }
}
