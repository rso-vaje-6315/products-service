package si.rso.products.api.endpoints;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
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

//    @GraphQLMutation
////    @RolesAllowed("admin")
//    public Customer addNewCustomer(@GraphQLArgument(name="customer") Customer customer) {
//        customerBean.saveCustomer(customer);
//        return customer;
//    }
//
//    @GraphQLMutation
////    @DenyAll
//    public void deleteCustomer(@GraphQLArgument(name="customerId") String customerId) {
//        customerBean.deleteCustomer(customerId);
//    }
}
