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
public class CriteriaTheme implements Comparable<CriteriaTheme> {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public long id;

    @Column(name = "priority")
    private int priority;

    @Column(unique = true)
    private String name;

    public CriteriaTheme() {
    }

    public CriteriaTheme(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(CriteriaTheme criteriaTheme) {
        if (this.priority < criteriaTheme.priority) return -1;
        if (this.priority > criteriaTheme.priority) return 1;
        return 0;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setName(String name) {
        this.name = name;
    }


}