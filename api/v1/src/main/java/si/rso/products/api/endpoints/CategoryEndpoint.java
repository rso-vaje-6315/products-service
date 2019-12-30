package si.rso.products.api.endpoints;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import si.rso.products.lib.Category;
import si.rso.products.services.CategoryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
@GraphQLClass
public class CategoryEndpoint {

    @Inject
    private CategoryService categoryService;

    @GraphQLQuery
    public List<Category> getAllCategories() {
        return categoryService.getCategories();
    }

    @GraphQLQuery
    public Category getCategory(@GraphQLArgument(name = "categoryId") String categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @GraphQLMutation
    public Category createCategory(@GraphQLArgument(name = "category") Category category) {
        return categoryService.createCategory(category);
    }

    @GraphQLMutation
    public Category updateCategory(@GraphQLArgument(name = "category") Category category) {
        return categoryService.updateCategory(category);
    }

    @GraphQLMutation
    public Category deleteCategory(@GraphQLArgument(name = "categoryId") String categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
