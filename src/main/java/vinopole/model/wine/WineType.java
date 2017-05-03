package vinopole.model.wine;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 15/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
@Entity
@Table(name = "WINETYPE")
public class WineType {
    /**
     * STATIC
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(unique = true)
    private String name;

    public WineType(String name) {
        this.name = name;
    }

    public WineType() {
    }

    @Override
    public String toString() {
        return name;
    }
}
