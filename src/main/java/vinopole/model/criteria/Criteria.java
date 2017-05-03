package vinopole.model.criteria;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 15/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
@Entity
@Table(name = "CRITERIA")
public class Criteria implements Comparable<Criteria> {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private String name;

    @ManyToOne
    private CriteriaTheme theme;

    @ManyToOne
    private CriteriaType type;


    public void setType(CriteriaType type) {
        this.type = type;
    }

    public Criteria() {
    }


    public Criteria(String name) {
        this.name = name;
    }

    public Criteria(String name, CriteriaTheme theme, CriteriaType type) {
        this.name = name;
        this.theme = theme;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CriteriaTheme getTheme() {
        return theme;
    }

    public CriteriaType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Criteria criteria) {
        //on tri par priorité de thème
        int themePriority = this.theme.compareTo(criteria.theme);
        if (themePriority != 0) return themePriority;
        //si c'est le même thème on tri par l'id du critère
        if (this.id < criteria.id) return -1;
        if (this.id > criteria.id) return 1;
        //sinon c'est les mêmes critères
        return 0;
    }

    public void setTheme(CriteriaTheme theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return name;
    }
}
