package vinopole.model;

import org.hibernate.annotations.GenericGenerator;
import vinopole.model.form.Form;
import vinopole.model.wine.Wine;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 15/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 *          Kévin MICHAUX - ajout génération .csv
 */
@Entity
@Table(name = "SERIE")
public class Serie {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Wine> wines = new ArrayList<Wine>();

    public Serie() {
    }

    public Serie(String name, Form form, List<Wine> wines) {
        this.name = name;
        this.form = form;
        this.wines = wines;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setWines(List<Wine> wines) {
        this.wines = wines;
    }

    public void addWine(Wine w) {
        wines.add(w);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Wine> getWines() {
        return wines;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    @Override
    public String toString() {
        return name;
    }
}