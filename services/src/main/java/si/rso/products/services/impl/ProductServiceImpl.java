package si.rso.products.services.impl;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Timeout;
import si.rso.products.lib.Category;
import si.rso.products.lib.Product;
import si.rso.products.mappers.CategoryMapper;
import si.rso.products.mappers.ProductMapper;
import si.rso.products.persistence.ProductEntity;
import si.rso.products.services.CategoryService;
import si.rso.products.services.ProductService;
import si.rso.products.services.StorageConnection;
import si.rso.rest.exceptions.NotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @PersistenceContext(unitName = "main-jpa-unit")
    private EntityManager em;

    @Inject
    private CategoryService categoryService;

    @Inject
    private StorageConnection storageConnection;

    @CircuitBreaker
    @Timeout
    @Override
    public List<Product> getProducts() {
        TypedQuery<ProductEntity> query = em.createNamedQuery(ProductEntity.FIND_ALL, ProductEntity.class);

        return query.getResultStream()
                .map(ProductMapper::fromProductEntity)
                .collect(Collectors.toList());
    }

    @CircuitBreaker
    @Timeout
    @Override
    public Product getProduct(String productId) {
        ProductEntity productEntity = em.find(ProductEntity.class, productId);

        if (productEntity == null) {
            throw new NotFoundException(ProductEntity.class, productId);
        }
        return ProductMapper.fromProductEntity(productEntity);
    }

    @CircuitBreaker
    @Timeout
    @Override
    @Transactional
    public Product createProduct(Product product) {
        Category categoryOfProduct = categoryService.getCategory(product.getCategory().getId());
        product.setCategory(categoryOfProduct);

        ProductEntity productEntity = ProductMapper.toProductEntity(product);

        em.persist(productEntity);

        return ProductMapper.fromProductEntity(productEntity);
    }

    @CircuitBreaker
    @Timeout
    @Override
    @Transactional
    public Product updateProduct(Product product) {
        ProductEntity old = em.find(ProductEntity.class, product.getId());
        if (old == null) {
            throw new NotFoundException(ProductEntity.class, product.getId());
        }
        old.setName(product.getName());
        old.setDescription(product.getDescription());
        old.setPrice(product.getPrice());
        old.setVisible(product.isVisible());
        old.setCategory(CategoryMapper.toCategoryEntity(categoryService.getCategory(product.getCategory().getId())));

        ProductEntity updated = em.merge(old);

        return ProductMapper.fromProductEntity(updated);
    }

    @CircuitBreaker
    @Timeout
    @Override
    @Transactional
    public Product deleteProduct(String productId) {
        ProductEntity productEntity = em.find(ProductEntity.class, productId);
        if (productEntity == null) {
            throw new NotFoundException(ProductEntity.class, productId);
        }
        em.remove(productEntity);

        return ProductMapper.fromProductEntity(productEntity);
    }

    @CircuitBreaker
    @Timeout
    @Override
    public void updateImage(String productId, byte[] imageBytes, String mimeType) throws IOException {
        storageConnection.uploadFile(productId, imageBytes, mimeType);
    }
}
