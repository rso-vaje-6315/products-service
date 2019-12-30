package si.rso.products.mappers;

import si.rso.products.lib.Category;
import si.rso.products.persistence.CategoryEntity;

public class CategoryMapper {
    public static Category fromCategoryEntity(CategoryEntity entity) {
        Category category = new Category();
        category.setId(entity.getId());
        category.setTimestamp(entity.getTimestamp());
        category.setName(entity.getName());

        if (entity.getParentCategory() != null) {
            category.setParentCategory(CategoryMapper.fromCategoryEntity(entity.getParentCategory()));
        }

        return category;
    }

    public static CategoryEntity toCategoryEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setTimestamp(category.getTimestamp());
        categoryEntity.setName(category.getName());

        if (category.getParentCategory() != null) {
            categoryEntity.setParentCategory(CategoryMapper.toCategoryEntity(category.getParentCategory()));
        }

        return categoryEntity;
    }
}
