import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import vinopole.HibernateUtil;
import vinopole.dao.ResponseFormDAO;
import vinopole.model.Judge;
import vinopole.model.ResponseForm;
import vinopole.model.Serie;
import vinopole.model.criteria.Criteria;
import vinopole.model.criteria.CriteriaTheme;
import vinopole.model.criteria.CriteriaType;
import vinopole.model.form.Form;
import vinopole.model.wine.Wine;
import vinopole.model.wine.WineType;
import vinopole.tool.GeneratorCsv;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 15/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class DbUnitTest {

    static Session session;

    @BeforeClass
    public static void setUp() throws Exception {
        session = HibernateUtil.getSession();
    }

    @Test
    public void creationJudge() throws Exception {
        session.beginTransaction();
        session.save(new Judge());
        session.getTransaction().commit();
        CriteriaQuery<Judge> queryJudge = session.getCriteriaBuilder().createQuery(Judge.class);
        queryJudge.from(Judge.class);
        List<Judge> resultListJudge = session.createQuery(queryJudge).getResultList();
        assertEquals(resultListJudge.size(), 4);
    }

    @Test
    public void wineType() throws Exception {
        session.beginTransaction();
        session.save(new WineType());
        session.getTransaction().commit();
        CriteriaQuery<WineType> queryWineType = session.getCriteriaBuilder().createQuery(WineType.class);
        queryWineType.from(WineType.class);
        List<WineType> resultListWineType = session.createQuery(queryWineType).getResultList();
        assertEquals(resultListWineType.size(), 1);

    }

    @Test
    public void wine() throws Exception {
        WineType wineType = new WineType("type");
        session.beginTransaction();
        session.save(wineType);
        session.getTransaction().commit();

        session.beginTransaction();
        session.save(new Wine("test", wineType));
        session.getTransaction().commit();
        CriteriaQuery<Wine> queryWine = session.getCriteriaBuilder().createQuery(Wine.class);
        queryWine.from(Wine.class);
        List<Wine> resultListWine = session.createQuery(queryWine).getResultList();
        assertEquals(resultListWine.size(), 1);

    }

    @Test
    public void serie() throws Exception {
        session.beginTransaction();
        session.save(new Serie());
        session.getTransaction().commit();
        CriteriaQuery<Serie> querySerie = session.getCriteriaBuilder().createQuery(Serie.class);
        querySerie.from(Serie.class);
        List<Serie> resultListSerie = session.createQuery(querySerie).getResultList();
        assertEquals(resultListSerie.size(), 1);

    }

    @Test
    public void response() throws Exception {
        session.beginTransaction();
        session.save(new ResponseForm());
        session.getTransaction().commit();
        CriteriaQuery<ResponseForm> queryResponseForm = session.getCriteriaBuilder().createQuery(ResponseForm.class);
        queryResponseForm.from(ResponseForm.class);
        List<ResponseForm> resultListResponseForm = session.createQuery(queryResponseForm).getResultList();
        assertEquals(resultListResponseForm.size(), 1);

    }

    @Test
    public void form() throws Exception {
        session.beginTransaction();
        session.save(new Form());
        session.getTransaction().commit();
        CriteriaQuery<Form> queryForm = session.getCriteriaBuilder().createQuery(Form.class);
        queryForm.from(Form.class);
        List<Form> resultListForm = session.createQuery(queryForm).getResultList();
        assertEquals(resultListForm.size(), 1);

    }


    @Test
    public void criteria() throws Exception {
        CriteriaType criteriaType = new CriteriaType("Type");
        CriteriaTheme criteriaTheme = new CriteriaTheme("theme",0);

        session.beginTransaction();
        session.save(criteriaType);
        session.getTransaction().commit();

        session.beginTransaction();
        session.save(criteriaTheme);
        session.getTransaction().commit();


        session.beginTransaction();
        session.save(new Criteria("criteria", criteriaTheme, criteriaType));
        session.getTransaction().commit();
        CriteriaQuery<Criteria> queryCriteria = session.getCriteriaBuilder().createQuery(Criteria.class);
        queryCriteria.from(Criteria.class);
        List<Criteria> resultListCriteria = session.createQuery(queryCriteria).getResultList();
        assertEquals(resultListCriteria.size(), 10);

    }

    @Test
    public void criteriaTheme() throws Exception {
        session.beginTransaction();
        session.save(new CriteriaTheme());
        session.getTransaction().commit();
        CriteriaQuery<CriteriaTheme> queryCriteriaTheme = session.getCriteriaBuilder().createQuery(CriteriaTheme.class);
        queryCriteriaTheme.from(CriteriaTheme.class);
        List<CriteriaTheme> resultListCriteriaTheme = session.createQuery(queryCriteriaTheme).getResultList();
        assertEquals(resultListCriteriaTheme.size(), 1);
    }

    @Test
    public void criteriaType() throws Exception {
        session.beginTransaction();
        session.save(new CriteriaType());
        session.getTransaction().commit();
        CriteriaQuery<CriteriaType> queryCriteriaType = session.getCriteriaBuilder().createQuery(CriteriaType.class);
        queryCriteriaType.from(CriteriaType.class);
        List<CriteriaType> resultListCriteriaType = session.createQuery(queryCriteriaType).getResultList();
        assertEquals(resultListCriteriaType.size(), 1);

    }

    @Test
    public void sortedResponses() throws Exception {
        session.beginTransaction();
        CriteriaTheme gout = new CriteriaTheme();
        gout.setName("gout");
        gout.setPriority(2);
        session.save(gout);
        CriteriaTheme odeur = new CriteriaTheme();
        odeur.setName("odeur");
        odeur.setPriority(1);
        session.save(odeur);
        CriteriaTheme vue = new CriteriaTheme();
        vue.setName("vue");
        vue.setPriority(3);
        session.save(vue);

        Criteria vue1 = new Criteria("vue1");
        vue1.setTheme(vue);
        session.save(vue1);
        Criteria gout1 = new Criteria("gout1");
        gout1.setTheme(gout);
        session.save(gout1);
        Criteria odeur1 = new Criteria("odeur1");
        odeur1.setTheme(odeur);
        session.save(odeur1);

        Criteria gout2 = new Criteria("gout2");
        gout2.setTheme(gout);
        session.save(gout2);
        Criteria vue2 = new Criteria("vue2");
        vue2.setTheme(vue);
        session.save(vue2);
        Criteria odeur2 = new Criteria("odeur2");
        odeur2.setTheme(odeur);
        session.save(odeur2);

        Criteria odeur3 = new Criteria("odeur3");
        odeur3.setTheme(odeur);
        session.save(odeur3);
        Criteria gout3 = new Criteria("gout3");
        gout3.setTheme(gout);
        session.save(gout3);
        Criteria vue3 = new Criteria("vue3");
        vue3.setTheme(vue);
        session.save(vue3);

        Form form = new Form();
        form.addCriteria(vue1);
        form.addCriteria(gout1);
        form.addCriteria(odeur1);
        form.addCriteria(vue2);
        form.addCriteria(gout2);
        form.addCriteria(odeur2);
        form.addCriteria(gout3);
        form.addCriteria(odeur3);
        form.addCriteria(vue3);
        session.save(form);

        List<Wine> vins = new ArrayList<>();
        Wine v1 = new Wine();
        v1.setName("vin1");
        session.save(v1);
        vins.add(v1);
        Wine v2 = new Wine();
        v2.setName("vin2");
        session.save(v2);
        vins.add(v2);
        Wine v3 = new Wine();
        v3.setName("vin3");
        session.save(v3);
        vins.add(v3);

        Serie s = new Serie("serie",form,vins);
        session.save(s);

        Judge juge1 = new Judge();
        session.save(juge1);
        Judge juge2 = new Judge();
        session.save(juge2);
        Judge juge3 = new Judge();
        session.save(juge3);
        
        //juge 1 v1 [1 - 9]
        ResponseForm responseForm1 = new ResponseForm(form,juge1,v1,gout1,s,4);
        ResponseForm responseForm2 = new ResponseForm(form,juge1,v1,gout2,s,5);
        ResponseForm responseForm3 = new ResponseForm(form,juge1,v1,gout3,s,6);
        ResponseForm responseForm4 = new ResponseForm(form,juge1,v1,vue1,s,7);
        ResponseForm responseForm5 = new ResponseForm(form,juge1,v1,odeur1,s,1);
        ResponseForm responseForm6 = new ResponseForm(form,juge1,v1,odeur2,s,2);
        ResponseForm responseForm7 = new ResponseForm(form,juge1,v1,vue2,s,8);
        ResponseForm responseForm8 = new ResponseForm(form,juge1,v1,odeur3,s,3);
        ResponseForm responseForm9 = new ResponseForm(form,juge1,v1,vue3,s,9);
        
        //juge 2 v1 [28 - 36]
        ResponseForm responseForm12 = new ResponseForm(form,juge2,v1,gout1,s,31);
        ResponseForm responseForm22 = new ResponseForm(form,juge2,v1,gout2,s,32);
        ResponseForm responseForm32 = new ResponseForm(form,juge2,v1,gout3,s,33);
        ResponseForm responseForm42 = new ResponseForm(form,juge2,v1,vue1,s,34);
        ResponseForm responseForm52 = new ResponseForm(form,juge2,v1,odeur1,s,28);
        ResponseForm responseForm62 = new ResponseForm(form,juge2,v1,odeur2,s,29);
        ResponseForm responseForm72 = new ResponseForm(form,juge2,v1,vue2,s,35);
        ResponseForm responseForm82 = new ResponseForm(form,juge2,v1,odeur3,s,30);
        ResponseForm responseForm92 = new ResponseForm(form,juge2,v1,vue3,s,36);

        //juge3 v1 [55 - 63]
        ResponseForm responseForm13 =new ResponseForm(form,juge3,v1,gout1,s,58);
        ResponseForm responseForm23 =new ResponseForm(form,juge3,v1,gout2,s,59);
        ResponseForm responseForm33 =new ResponseForm(form,juge3,v1,gout3,s,60);
        ResponseForm responseForm43 =new ResponseForm(form,juge3,v1,vue1,s,61);
        ResponseForm responseForm53 =new ResponseForm(form,juge3,v1,odeur1,s,55);
        ResponseForm responseForm63 =new ResponseForm(form,juge3,v1,odeur2,s,56);
        ResponseForm responseForm73 =new ResponseForm(form,juge3,v1,vue2,s,62);
        ResponseForm responseForm83 =new ResponseForm(form,juge3,v1,odeur3,s,57);
        ResponseForm responseForm93 =new ResponseForm(form,juge3,v1,vue3,s,63);
        session.save(responseForm1);
        session.save(responseForm2);
        session.save(responseForm3);
        session.save(responseForm4);
        session.save(responseForm5);
        session.save(responseForm6);
        session.save(responseForm7);
        session.save(responseForm8);
        session.save(responseForm9);
        session.save(responseForm12);
        session.save(responseForm22);
        session.save(responseForm32);
        session.save(responseForm42);
        session.save(responseForm52);
        session.save(responseForm62);
        session.save(responseForm72);
        session.save(responseForm82);
        session.save(responseForm92);
        session.save(responseForm13);
        session.save(responseForm23);
        session.save(responseForm33);
        session.save(responseForm43);
        session.save(responseForm53);
        session.save(responseForm63);
        session.save(responseForm73);
        session.save(responseForm83);
        session.save(responseForm93);
        //juge 1 v2 [10 - 18]
        ResponseForm responseForm1v2 = new ResponseForm(form,juge1,v2,gout1,s,13);
        ResponseForm responseForm2v2 = new ResponseForm(form,juge1,v2,gout2,s,14);
        ResponseForm responseForm3v2 = new ResponseForm(form,juge1,v2,gout3,s,15);
        ResponseForm responseForm4v2 = new ResponseForm(form,juge1,v2,vue1,s,16);
        ResponseForm responseForm5v2 = new ResponseForm(form,juge1,v2,odeur1,s,10);
        ResponseForm responseForm6v2 = new ResponseForm(form,juge1,v2,odeur2,s,11);
        ResponseForm responseForm7v2 = new ResponseForm(form,juge1,v2,vue2,s,17);
        ResponseForm responseForm8v2 = new ResponseForm(form,juge1,v2,odeur3,s,12);
        ResponseForm responseForm9v2 = new ResponseForm(form,juge1,v2,vue3,s,18);
        //juge 2 v2 [37 - 45]
        ResponseForm responseForm12v2 = new ResponseForm(form,juge2,v2,gout1,s,40);
        ResponseForm responseForm22v2 = new ResponseForm(form,juge2,v2,gout2,s,41);
        ResponseForm responseForm32v2 = new ResponseForm(form,juge2,v2,gout3,s,42);
        ResponseForm responseForm42v2 = new ResponseForm(form,juge2,v2,vue1,s,43);
        ResponseForm responseForm52v2 = new ResponseForm(form,juge2,v2,odeur1,s,37);
        ResponseForm responseForm62v2 = new ResponseForm(form,juge2,v2,odeur2,s,38);
        ResponseForm responseForm72v2 = new ResponseForm(form,juge2,v2,vue2,s,44);
        ResponseForm responseForm82v2 = new ResponseForm(form,juge2,v2,odeur3,s,39);
        ResponseForm responseForm92v2 = new ResponseForm(form,juge2,v2,vue3,s,45);
        //juge3 v2 [64 - 72]
        ResponseForm responseForm13v2 =new ResponseForm(form,juge3,v2,gout1,s,67);
        ResponseForm responseForm23v2 =new ResponseForm(form,juge3,v2,gout2,s,68);
        ResponseForm responseForm33v2 =new ResponseForm(form,juge3,v2,gout3,s,69);
        ResponseForm responseForm43v2 =new ResponseForm(form,juge3,v2,vue1,s,70);
        ResponseForm responseForm53v2 =new ResponseForm(form,juge3,v2,odeur1,s,64);
        ResponseForm responseForm63v2 =new ResponseForm(form,juge3,v2,odeur2,s,65);
        ResponseForm responseForm73v2 =new ResponseForm(form,juge3,v2,vue2,s,71);
        ResponseForm responseForm83v2 =new ResponseForm(form,juge3,v2,odeur3,s,66);
        ResponseForm responseForm93v2 =new ResponseForm(form,juge3,v2,vue3,s,72);
        session.save(responseForm1v2);
        session.save(responseForm2v2);
        session.save(responseForm3v2);
        session.save(responseForm4v2);
        session.save(responseForm5v2);
        session.save(responseForm6v2);
        session.save(responseForm7v2);
        session.save(responseForm8v2);
        session.save(responseForm9v2);
        session.save(responseForm12v2);
        session.save(responseForm22v2);
        session.save(responseForm32v2);
        session.save(responseForm42v2);
        session.save(responseForm52v2);
        session.save(responseForm62v2);
        session.save(responseForm72v2);
        session.save(responseForm82v2);
        session.save(responseForm92v2);
        session.save(responseForm13v2);
        session.save(responseForm23v2);
        session.save(responseForm33v2);
        session.save(responseForm43v2);
        session.save(responseForm53v2);
        session.save(responseForm63v2);
        session.save(responseForm73v2);
        session.save(responseForm83v2);
        session.save(responseForm93v2);
        //juge 1 v3 [19 - 27]
        ResponseForm responseForm1v3= new ResponseForm(form,juge1,v3,gout1,s,22);
        ResponseForm responseForm2v3= new ResponseForm(form,juge1,v3,gout2,s,23);
        ResponseForm responseForm3v3= new ResponseForm(form,juge1,v3,gout3,s,24);
        ResponseForm responseForm4v3= new ResponseForm(form,juge1,v3,vue1,s,25);
        ResponseForm responseForm5v3= new ResponseForm(form,juge1,v3,odeur1,s,19);
        ResponseForm responseForm6v3= new ResponseForm(form,juge1,v3,odeur2,s,20);
        ResponseForm responseForm7v3= new ResponseForm(form,juge1,v3,vue2,s,26);
        ResponseForm responseForm8v3= new ResponseForm(form,juge1,v3,odeur3,s,21);
        ResponseForm responseForm9v3= new ResponseForm(form,juge1,v3,vue3,s,27);
        //juge 2 v3 [46 - 54]
        ResponseForm responseForm12v3= new ResponseForm(form,juge2,v3,gout1,s,49);
        ResponseForm responseForm22v3= new ResponseForm(form,juge2,v3,gout2,s,50);
        ResponseForm responseForm32v3= new ResponseForm(form,juge2,v3,gout3,s,51);
        ResponseForm responseForm42v3= new ResponseForm(form,juge2,v3,vue1,s,52);
        ResponseForm responseForm52v3= new ResponseForm(form,juge2,v3,odeur1,s,46);
        ResponseForm responseForm62v3= new ResponseForm(form,juge2,v3,odeur2,s,47);
        ResponseForm responseForm72v3= new ResponseForm(form,juge2,v3,vue2,s,53);
        ResponseForm responseForm82v3= new ResponseForm(form,juge2,v3,odeur3,s,48);
        ResponseForm responseForm92v3= new ResponseForm(form,juge2,v3,vue3,s,54);
        //juge3 v3 [73 - 81]
        ResponseForm responseForm13v3=new ResponseForm(form,juge3,v3,gout1,s,76);
        ResponseForm responseForm23v3=new ResponseForm(form,juge3,v3,gout2,s,77);
        ResponseForm responseForm33v3=new ResponseForm(form,juge3,v3,gout3,s,78);
        ResponseForm responseForm43v3=new ResponseForm(form,juge3,v3,vue1,s,79);
        ResponseForm responseForm53v3=new ResponseForm(form,juge3,v3,odeur1,s,73);
        ResponseForm responseForm63v3=new ResponseForm(form,juge3,v3,odeur2,s,74);
        ResponseForm responseForm73v3=new ResponseForm(form,juge3,v3,vue2,s,80);
        ResponseForm responseForm83v3=new ResponseForm(form,juge3,v3,odeur3,s,75);
        ResponseForm responseForm93v3=new ResponseForm(form,juge3,v3,vue3,s,81);
        session.save(responseForm1v3);
        session.save(responseForm2v3);
        session.save(responseForm3v3);
        session.save(responseForm4v3);
        session.save(responseForm5v3);
        session.save(responseForm6v3);
        session.save(responseForm7v3);
        session.save(responseForm8v3);
        session.save(responseForm9v3);
        session.save(responseForm12v3);
        session.save(responseForm22v3);
        session.save(responseForm32v3);
        session.save(responseForm42v3);
        session.save(responseForm52v3);
        session.save(responseForm62v3);
        session.save(responseForm72v3);
        session.save(responseForm82v3);
        session.save(responseForm92v3);
        session.save(responseForm13v3);
        session.save(responseForm23v3);
        session.save(responseForm33v3);
        session.save(responseForm43v3);
        session.save(responseForm53v3);
        session.save(responseForm63v3);
        session.save(responseForm73v3);
        session.save(responseForm83v3);
        session.save(responseForm93v3);
        session.getTransaction().commit();
        ResponseFormDAO daoResponse = new ResponseFormDAO();
        List<ResponseForm> responses = daoResponse.getBySerie(s.getId());
        for(int i = 1; i <= 81; i++){
            double result = responses.get(i-1).getResult();
            assertEquals(i,result,0);
        }
        GeneratorCsv.GenerateCsv(s,".\\");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        HibernateUtil.closeDb();
    }
}
