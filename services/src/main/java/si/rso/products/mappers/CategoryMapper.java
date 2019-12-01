package si.rso.products.mappers;

import si.rso.products.lib.Category;
import si.rso.products.persistence.CategoryEntity;

public class CategoryMapper {
    public static Category fromCategoryEntity(CategoryEntity entity) {
        Category category = new Category();
        category.setName(entity.getName());
        if (entity.getParentCategory() != null) {
            category.setParentCategory(CategoryMapper.fromCategoryEntity(entity.getParentCategory()));
        }
        return category;
    }
}
