package vinopole.model.form;

import org.hibernate.annotations.GenericGenerator;
import vinopole.model.criteria.Criteria;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 15/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
@Entity
@Table(name = "FORM")
public class Form {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    private Date creationDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Criteria> criterias = new ArrayList<Criteria>();

    public Form() {
    }

    public Form(String name, Date creationDate, List<Criteria> criterias) {
        this.name = name;
        this.creationDate = creationDate;
        this.criterias = criterias;
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }


    public void addCriteria(Criteria c) {
        criterias.add(c);
    }

    @Override
    public String toString() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
    }

}
