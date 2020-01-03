package si.rso.products.api.endpoints;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
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
    @Timeout
    @Retry
    public List<Category> getAllCategories() {
        return categoryService.getCategories();
    }

    @GraphQLQuery
    @Timeout
    @Retry
    public Category getCategory(@GraphQLArgument(name = "categoryId") String categoryId) {
        return categoryService.getCategory(categoryId);
    }
}
