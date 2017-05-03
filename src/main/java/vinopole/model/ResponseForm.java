package vinopole.model;

import org.hibernate.annotations.GenericGenerator;
import vinopole.model.criteria.Criteria;
import vinopole.model.form.Form;
import vinopole.model.wine.Wine;

import javax.persistence.*;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 15/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
@Entity
@Table(name = "RESPONSEFORM")
public class ResponseForm implements Comparable<ResponseForm> {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    public ResponseForm() {
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public ResponseForm(Form form, Judge judge, Wine wine, Criteria criteria, Serie serie, double result) {
        this.form = form;
        this.judge = judge;
        this.wine = wine;
        this.criteria = criteria;
        this.result = result;
        this.serie = serie;
    }

    @OneToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @OneToOne
    @JoinColumn(name = "judge_id")
    private Judge judge;

    @OneToOne
    @JoinColumn(name = "wine_id")
    private Wine wine;

    @OneToOne
    @JoinColumn(name = "criteria_id")
    private Criteria criteria;

    @OneToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;

    private double result;

    @Override
    public int compareTo(ResponseForm response) {
        //si juge avant
        if (this.judge.getId() < response.judge.getId()) return -1;
        //si juge après
        if (this.judge.getId() > response.judge.getId()) return 1;
        //si même juge --> on compare les vins
        //si vin avant
        if (this.wine.getId() < response.wine.getId()) return -1;
        //si vin après
        if (this.wine.getId() > response.wine.getId()) return 1;
        //si même vin : on trie par critère
        return this.criteria.compareTo(response.criteria);
    }

    public double getResult() {
        return result;
    }

    public long getId() {
        return id;
    }

    public Form getForm() {
        return form;
    }

    public Judge getJudge() {
        return judge;
    }

    public Wine getWine() {
        return wine;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public Serie getSerie() {
        return serie;
    }
}
