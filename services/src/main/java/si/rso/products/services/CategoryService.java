package si.rso.products.services;

import si.rso.products.lib.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    Category getCategory(String categoryId);

    Category createCategory(Category category);

    Category updateCategory(Category category);

    Category deleteCategory(String categoryId);
}
