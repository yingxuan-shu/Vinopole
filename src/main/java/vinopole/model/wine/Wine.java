package vinopole.model.wine;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 15/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
@Entity
@Table(name = "WINE")
public class Wine {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private String name;

    @ManyToOne
    private WineType type;

    public Wine() {
    }

    public Wine(String name, WineType type) {
        this.name = name;
        this.type = type;
    }

    public void setType(WineType type) {
        this.type = type;
    }


    public void setName(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }


    public long getId() {
        return id;
    }

    public WineType getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

}
