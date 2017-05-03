import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import vinopole.HibernateUtil;
import vinopole.dao.FormDAO;
import vinopole.dao.JudgeDAO;
import vinopole.dao.ResponseFormDAO;
import vinopole.dao.SerieDAO;
import vinopole.dao.criteria.CriteriaDAO;
import vinopole.dao.criteria.CriteriaThemeDAO;
import vinopole.dao.criteria.CriteriaTypeDAO;
import vinopole.dao.wine.WineDAO;
import vinopole.dao.wine.WineTypeDAO;
import vinopole.model.Judge;
import vinopole.model.ResponseForm;
import vinopole.model.Serie;
import vinopole.model.criteria.Criteria;
import vinopole.model.criteria.CriteriaTheme;
import vinopole.model.criteria.CriteriaType;
import vinopole.model.form.Form;
import vinopole.model.wine.Wine;
import vinopole.model.wine.WineType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 11/11/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class DAOUnitTest {
    CriteriaTheme criteriaTheme;
    CriteriaType criteriaType;
    List<Criteria> criterias;
    WineType wineType;
    List<Wine> wines;
    Serie serie;
    Form form;
    Judge judge;
    ResponseForm responseForm;

    CriteriaThemeDAO criteriaThemeDAO;
    CriteriaTypeDAO criteriaTypeDAO;
    CriteriaDAO criteriaDAO;

    WineTypeDAO wineTypeDAO;
    WineDAO wineDAO;

    FormDAO formDAO;
    SerieDAO serieDAO;
    JudgeDAO judgeDAO;
    ResponseFormDAO responseFormDAO;


    @Before
    public void setUp() throws Exception {
        criteriaTheme = new CriteriaTheme("CriteriaTheme",0);
        criteriaType = new CriteriaType("CriteriaType");

        Criteria criteria = new Criteria("Criteria", criteriaTheme, criteriaType);
        criterias = new ArrayList<Criteria>();
        criterias.add(criteria);

        wineType = new WineType("WineType");
        Wine wine = new Wine("Wine", wineType);
        wines = new ArrayList<Wine>();
        wines.add(wine);

        form = new Form("Form", new Date(), criterias);
        serie = new Serie("Serie", form, wines);
        judge = new Judge();
        responseForm = new ResponseForm(form, judge, wine, criteria, serie, 10);

        criteriaThemeDAO = new CriteriaThemeDAO();
        criteriaTypeDAO = new CriteriaTypeDAO();
        criteriaDAO = new CriteriaDAO();

        wineTypeDAO = new WineTypeDAO();
        wineDAO = new WineDAO();

        formDAO = new FormDAO();
        serieDAO = new SerieDAO();
        judgeDAO = new JudgeDAO();
        responseFormDAO = new ResponseFormDAO();


        criteriaThemeDAO.save(criteriaTheme);
        criteriaTypeDAO.save(criteriaType);
        criteriaDAO.save(criteria);

        wineTypeDAO.save(wineType);
        wineDAO.save(wine);

        formDAO.save(form);
        serieDAO.save(serie);
        judgeDAO.save(judge);
        responseFormDAO.save(responseForm);
    }

    @Test
    public void get() throws Exception {
        assertNotEquals(0,criteriaDAO.getAll().size());
        assertNotEquals(0,criteriaTypeDAO.getAll().size());
        assertNotEquals(0,criteriaThemeDAO.getAll().size());
        assertNotEquals(0,wineDAO.getAll().size());
        assertNotEquals(0,wineTypeDAO.getAll().size());
        assertNotEquals(0,formDAO.getAll().size());
        assertNotEquals(0,judgeDAO.getAll().size());
        assertNotEquals(0,responseFormDAO.getAll().size());
        assertNotEquals(0,serieDAO.getAll().size());

    }

    @Test
    public void update() throws Exception {
        Criteria criteria = criteriaDAO.getAll().get(0);
        criteria.setName("test");
        criteriaDAO.update(criteria);
        Criteria criteria1 = criteriaDAO.getAll().get(0);
        assertEquals(criteria.getName(),criteria1.getName());
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void deleteCriteriaInForm(){
        Form f = new Form();
        Criteria c = new Criteria();
        criteriaDAO.save(c);
        f.addCriteria(c);
        formDAO.save(f);
        criteriaDAO.remove(c);
    }
    @Test(expected = javax.persistence.PersistenceException.class)
    public void deleteWineTypeInWine(){
        Wine w = new Wine();
        WineType t = new WineType();
        wineTypeDAO.save(t);
        w.setType(t);
        wineDAO.save(w);
        wineTypeDAO.remove(t);
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void deleteCriteriaTypeInCriteria(){
        CriteriaType t = new CriteriaType();
        criteriaTypeDAO.save(t);
        Criteria c = new Criteria();
        c.setType(t);
        criteriaDAO.save(c);
        criteriaTypeDAO.remove(t);
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void deleteCriteriaThemeInCriteria(){
        CriteriaTheme t = new CriteriaTheme();
        criteriaThemeDAO.save(t);
        Criteria c = new Criteria();
        c.setTheme(t);
        criteriaDAO.save(c);
        criteriaThemeDAO.remove(t);
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void deleteFormInSerie(){
        Form f = new Form();
        formDAO.save(f);
        Serie s = new Serie();
        s.setForm(f);
        serieDAO.save(s);
        formDAO.remove(f);
    }
    @Test(expected = javax.persistence.PersistenceException.class)
    public void deleteWineInSerie(){
        Wine w = new Wine();
        wineDAO.save(w);
        Serie s = new Serie();
        s.addWine(w);
        serieDAO.save(s);
        wineDAO.remove(w);
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void deleteCriteriaInResponseForm(){
        Criteria c = new Criteria();
        criteriaDAO.save(c);
        ResponseForm r = new ResponseForm();
        r.setCriteria(c);
        responseFormDAO.save(r);
        criteriaDAO.remove(c);
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void deleteWineInResponseForm(){
        Wine w = new Wine();
        wineDAO.save(w);
        ResponseForm r = new ResponseForm();
        r.setWine(w);
        responseFormDAO.save(r);
        wineDAO.remove(w);
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void deleteSerieInResponseForm(){
        Serie s = new Serie();
        serieDAO.save(s);
        ResponseForm r = new ResponseForm();
        r.setSerie(s);
        responseFormDAO.save(r);
        serieDAO.remove(s);
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void deleteJudgeInResponseForm(){
        Judge j = new Judge();
        judgeDAO.save(j);
        ResponseForm r = new ResponseForm();
        r.setJudge(j);
        responseFormDAO.save(r);
        judgeDAO.remove(j);
    }

    @After
    public void remove() throws Exception {
        responseFormDAO.remove(responseForm);
        judgeDAO.remove(judge);
        serieDAO.remove(serie);
        formDAO.remove(form);
        wineDAO.remove(wines.get(0));
        wineTypeDAO.remove(wineType);
        criteriaDAO.remove(criterias.get(0));
        criteriaTypeDAO.save(criteriaType);
        criteriaThemeDAO.save(criteriaTheme);
    }

    @AfterClass
    public static void close() {
        HibernateUtil.closeDb();
    }

}
