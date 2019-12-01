package si.rso.products.services.impl;

import si.rso.products.lib.Product;
import si.rso.products.mappers.ProductMapper;
import si.rso.products.persistence.ProductEntity;
import si.rso.products.services.ProductService;
import si.rso.rest.exceptions.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    @PersistenceContext(unitName = "main-jpa-unit")
    private EntityManager em;

    @Override
    public List<Product> getProducts() {
        TypedQuery<ProductEntity> query = em.createNamedQuery(ProductEntity.FIND_ALL, ProductEntity.class);

        return query.getResultStream()
                .map(ProductMapper::fromProductEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProduct(String productId) {
        ProductEntity productEntity = em.find(ProductEntity.class, productId);

        if (productEntity == null) {
            throw new NotFoundException(ProductEntity.class, productId);
        }
        return ProductMapper.fromProductEntity(productEntity);
    }
}
