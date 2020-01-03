package si.rso.products.api.endpoints;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.kumuluz.ee.security.annotations.Secure;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import si.rso.products.api.config.AuthRole;
import si.rso.products.lib.Product;
import si.rso.products.services.ProductService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@GraphQLClass
@Secure
public class ProductSecureEndpoint {

    @Inject
    private ProductService productService;

    @RolesAllowed({AuthRole.ADMIN, AuthRole.SELLER})
    @GraphQLMutation
    public Product createProduct(@GraphQLArgument(name = "product") Product product) {
        return productService.createProduct(product);
    }

    @RolesAllowed({AuthRole.ADMIN, AuthRole.SELLER})
    @GraphQLMutation
    public Product updateProduct(@GraphQLArgument(name = "product") Product product) {
        return productService.updateProduct(product);
    }

    @RolesAllowed({AuthRole.ADMIN, AuthRole.SELLER})
    @GraphQLMutation
    public Product deleteProduct(@GraphQLArgument(name = "productId") String productId) {
        return productService.deleteProduct(productId);
    }
}
