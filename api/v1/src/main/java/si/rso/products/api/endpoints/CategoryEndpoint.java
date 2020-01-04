package si.rso.products.api.endpoints;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.kumuluz.ee.logs.cdi.Log;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import si.rso.products.lib.Category;
import si.rso.products.services.CategoryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@Log
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
}
