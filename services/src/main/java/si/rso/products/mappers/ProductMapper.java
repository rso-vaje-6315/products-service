package si.rso.products.mappers;

import si.rso.products.lib.Product;
import si.rso.products.persistence.ProductEntity;

public class ProductMapper {

    public static Product fromProductEntity(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setCode(entity.getCode());
        product.setTimestamp(entity.getTimestamp());
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setPrice(entity.getPrice());
        product.setVisible(entity.isVisible());
        product.setCategory(CategoryMapper.fromCategoryEntity(entity.getCategory()));

        return product;
    }

    public static ProductEntity toProductEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setCode(product.getCode());
        productEntity.setTimestamp(product.getTimestamp());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setVisible(product.isVisible());
        productEntity.setCategory(CategoryMapper.toCategoryEntity(product.getCategory()));

        return productEntity;
    }
}
