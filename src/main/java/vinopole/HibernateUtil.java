package vinopole;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import vinopole.dao.criteria.CriteriaThemeDAO;
import vinopole.dao.criteria.CriteriaTypeDAO;
import vinopole.dao.wine.WineTypeDAO;
import vinopole.model.criteria.CriteriaTheme;
import vinopole.model.criteria.CriteriaType;
import vinopole.model.wine.WineType;

import java.util.List;


/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 16/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    private static Session session = null;

    /**
     * Give an open session
     *
     * @return session
     */
    public static Session getSession() {
        if (session != null && session.isOpen()) {
            return session;
        }
        setUp();
        session = sessionFactory.openSession();
        return session;
    }

    /**
     * Close session and sessionfactory
     */
    public static void closeDb() {
        if (session != null) {
            session.close();
        }
        if (sessionFactory != null) {
            sessionFactory.close();
        }

    }

    /**
     * Initialise mapping of database
     */
    private static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("/xml/hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }


    /**
     * Initialise DB with static values if not already exist
     */
    public static void initDB() {
        Session sessionAdd = HibernateUtil.getSession();
        sessionAdd.beginTransaction();


        WineTypeDAO wineTypeDAO = new WineTypeDAO();
        List<WineType> wineTypes = wineTypeDAO.getByName("blanc");
        if (wineTypes == null || wineTypes.size() == 0) {
            sessionAdd.save(new WineType("blanc"));
        }

        wineTypes = wineTypeDAO.getByName("rouge");
        if (wineTypes == null || wineTypes.size() == 0) {
            sessionAdd.save(new WineType("rouge"));
        }

        wineTypes = wineTypeDAO.getByName("rosé");
        if (wineTypes == null || wineTypes.size() == 0) {
            sessionAdd.save(new WineType("rosé"));
        }

        CriteriaThemeDAO criteriaThemeDAO = new CriteriaThemeDAO();
        List<CriteriaTheme> criteriaTheme = criteriaThemeDAO.getByName("Odeur");
        if (criteriaTheme == null || criteriaTheme.size() == 0) {
            sessionAdd.save(new CriteriaTheme("Odeur", 0));
        }
        criteriaTheme = criteriaThemeDAO.getByName("Goût");
        if (criteriaTheme == null || criteriaTheme.size() == 0) {
            sessionAdd.save(new CriteriaTheme("Goût", 1));
        }
        criteriaTheme = criteriaThemeDAO.getByName("Vue");
        if (criteriaTheme == null || criteriaTheme.size() == 0) {
            sessionAdd.save(new CriteriaTheme("Vue", 2));
        }
        criteriaTheme = criteriaThemeDAO.getByName("Qualité globale");
        if (criteriaTheme == null || criteriaTheme.size() == 0) {
            sessionAdd.save(new CriteriaTheme("Qualité globale", 3));
        }

        CriteriaTypeDAO criteriaTypeDAO = new CriteriaTypeDAO();
        List<CriteriaType> criteriaTypes = criteriaTypeDAO.getByName("Commentaire");
        if (criteriaTypes == null || criteriaTypes.size() == 0) {
            sessionAdd.save(new CriteriaType("Commentaire"));
        }

        criteriaTypes = criteriaTypeDAO.getByName("Commentaire");
        if (criteriaTypes == null || criteriaTypes.size() == 0) {
            sessionAdd.save(new CriteriaType("Commentaire"));
        }

        criteriaTypes = criteriaTypeDAO.getByName("Echelle continue");
        if (criteriaTypes == null || criteriaTypes.size() == 0) {
            sessionAdd.save(new CriteriaType("Echelle continue"));
        }

        criteriaTypes = criteriaTypeDAO.getByName("5 étoiles");
        if (criteriaTypes == null || criteriaTypes.size() == 0) {
            sessionAdd.save(new CriteriaType("5 étoiles"));
        }

        criteriaTypes = criteriaTypeDAO.getByName("oui/non");
        if (criteriaTypes == null || criteriaTypes.size() == 0) {
            sessionAdd.save(new CriteriaType("oui/non"));
        }

        criteriaTypes = criteriaTypeDAO.getByName("Trois smileys");
        if (criteriaTypes == null || criteriaTypes.size() == 0) {
            sessionAdd.save(new CriteriaType("Trois smileys"));
        }

        criteriaTypes = criteriaTypeDAO.getByName("Pas du tout / un peu / moyen / assez / beaucoup");
        if (criteriaTypes == null || criteriaTypes.size() == 0) {
            sessionAdd.save(new CriteriaType("Pas du tout / un peu / moyen / assez / beaucoup"));
        }

        sessionAdd.getTransaction().commit();
    }
}
