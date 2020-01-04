package si.rso.products.services.impl;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Timeout;
import si.rso.products.lib.Category;
import si.rso.products.mappers.CategoryMapper;
import si.rso.products.persistence.CategoryEntity;
import si.rso.products.services.CategoryService;
import si.rso.rest.exceptions.NotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {

    @PersistenceContext(unitName = "main-jpa-unit")
    private EntityManager em;

    @CircuitBreaker
    @Timeout
    @Override
    public List<Category> getCategories() {
        TypedQuery<CategoryEntity> query = em.createNamedQuery(CategoryEntity.FIND_ALL, CategoryEntity.class);

        return query.getResultStream()
                .map(CategoryMapper::fromCategoryEntity)
                .collect(Collectors.toList());
    }

    @CircuitBreaker
    @Timeout
    @Override
    public Category getCategory(String categoryId) {
        CategoryEntity categoryEntity = em.find(CategoryEntity.class, categoryId);

        if (categoryEntity == null) {
            throw new NotFoundException(CategoryEntity.class, categoryId);
        }
        return CategoryMapper.fromCategoryEntity(categoryEntity);
    }

    @CircuitBreaker
    @Timeout
    @Override
    @Transactional
    public Category createCategory(Category category) {
        Category parentCategory = getCategory(category.getParentCategory().getId());
        category.setParentCategory(parentCategory);

        CategoryEntity categoryEntity = CategoryMapper.toCategoryEntity(category);

        em.persist(categoryEntity);

        return CategoryMapper.fromCategoryEntity(categoryEntity);
    }

    @CircuitBreaker
    @Timeout
    @Override
    @Transactional
    public Category updateCategory(Category category) {
        CategoryEntity old = em.find(CategoryEntity.class, category.getId());
        if (old == null) {
            throw new NotFoundException(CategoryEntity.class, category.getId());
        }
        old.setName(category.getName());
        old.setParentCategory(CategoryMapper.toCategoryEntity(getCategory(category.getParentCategory().getId())));

        CategoryEntity updated = em.merge(old);

        return CategoryMapper.fromCategoryEntity(updated);
    }

    @CircuitBreaker
    @Timeout
    @Override
    @Transactional
    public Category deleteCategory(String categoryId) {
        CategoryEntity categoryEntity = em.find(CategoryEntity.class, categoryId);
        if (categoryEntity == null) {
            throw new NotFoundException(CategoryEntity.class, categoryId);
        }

        TypedQuery<CategoryEntity> query = em.createNamedQuery(CategoryEntity.FIND_BY_CATEGORY, CategoryEntity.class);
        query.setParameter("category", categoryEntity);
        List<CategoryEntity> childrenEntities = query.getResultList();

        // remove children - recursion
        for (CategoryEntity childEntity : childrenEntities) {
            deleteCategory(childEntity.getId());
        }

        // remove parent
        em.remove(categoryEntity);

        return CategoryMapper.fromCategoryEntity(categoryEntity);
    }
}
