package si.rso.products.mappers;

import si.rso.products.lib.Product;
import si.rso.products.persistence.ProductEntity;

public class ProductMapper {

    public static Product fromProductEntity(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setTimestamp(entity.getTimestamp());
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setPrice(entity.getPrice());
        product.setVisible(entity.isVisible());
        product.setCategory(CategoryMapper.fromCategoryEntity(entity.getCategory()));

        return product;
    }
}
