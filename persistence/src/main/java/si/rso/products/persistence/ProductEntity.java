package si.rso.products.persistence;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NamedQueries(value = {
        @NamedQuery(name = ProductEntity.FIND_ALL, query = "SELECT p FROM ProductEntity p")
        , @NamedQuery(name = ProductEntity.FIND_BY_CATEGORY, query = "SELECT p FROM ProductEntity p WHERE p.category = :category")
})
public class ProductEntity extends BaseEntity {

    public static final String FIND_ALL = "ProductEntity.findAll";
    public static final String FIND_BY_CATEGORY = "ProductEntity.findByCategory";

    private String name;

    private String description;

    private int price;

    private boolean visible;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private CategoryEntity category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
