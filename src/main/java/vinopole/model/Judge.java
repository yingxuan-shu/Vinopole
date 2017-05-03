package vinopole.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 15/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
@Entity
@Table(name = "JUDGE")
public class Judge {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    public long getId() {
        return id;
    }

    public Judge() {
    }
}
