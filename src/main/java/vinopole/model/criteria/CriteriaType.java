package vinopole.model.criteria;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 15/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
@Entity
@Table
public class CriteriaType {
    /**
     * STATIC
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public long id;

    @Column(unique = true)
    private String name;

    public CriteriaType() {
    }

    public CriteriaType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
