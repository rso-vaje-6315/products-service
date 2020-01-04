package si.rso.products.api.endpoints;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.security.annotations.Secure;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import si.rso.products.api.config.AuthRole;
import si.rso.products.lib.Category;
import si.rso.products.services.CategoryService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Log
@RequestScoped
@GraphQLClass
@Secure
public class CategorySecureEndpoint {

    @Inject
    private CategoryService categoryService;

    @GraphQLMutation
    @RolesAllowed({AuthRole.ADMIN, AuthRole.SELLER})
    public Category createCategory(@GraphQLArgument(name = "category") Category category) {
        return categoryService.createCategory(category);
    }

    @GraphQLMutation
    @RolesAllowed({AuthRole.ADMIN, AuthRole.SELLER})
    public Category updateCategory(@GraphQLArgument(name = "category") Category category) {
        return categoryService.updateCategory(category);
    }

    @GraphQLMutation
    @RolesAllowed({AuthRole.ADMIN, AuthRole.SELLER})
    public Category deleteCategory(@GraphQLArgument(name = "categoryId") String categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
