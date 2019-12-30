package si.rso.products.persistence;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@NamedQueries(value = {
        @NamedQuery(name = CategoryEntity.FIND_ALL, query = "SELECT c FROM CategoryEntity c")
        , @NamedQuery(name = CategoryEntity.FIND_BY_CATEGORY, query = "SELECT c FROM CategoryEntity c WHERE c.parentCategory = :category")
})
public class CategoryEntity extends BaseEntity {

    public static final String FIND_ALL = "CategoryEntity.findAll";
    public static final String FIND_BY_CATEGORY = "CategoryEntity.findByCategory";

    private String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "parent_category", referencedColumnName = "id")
    private CategoryEntity parentCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryEntity parentCategory) {
        this.parentCategory = parentCategory;
    }
}
