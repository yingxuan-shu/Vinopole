package vinopole.dao.criteria;

import org.hibernate.Session;
import vinopole.HibernateUtil;
import vinopole.dao.DAO;
import vinopole.model.criteria.Criteria;
import vinopole.model.criteria.CriteriaTheme;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Metamodel;
import java.util.Collections;
import java.util.List;

import static antlr.build.ANTLR.root;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 24/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class CriteriaThemeDAO extends DAO<CriteriaTheme> {

    @Override
    public CriteriaTheme get(long id) {
        Session session = HibernateUtil.getSession();

        return session.load(CriteriaTheme.class, id);
    }

    @Override
    public List<CriteriaTheme> getAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<CriteriaTheme> query = session.getCriteriaBuilder().createQuery(CriteriaTheme.class);
        query.from(CriteriaTheme.class);
        List<CriteriaTheme> list = session.createQuery(query).getResultList();
        Collections.sort(list);
        return list;
    }

    /**
     * Get criteriaTheme from DB with name
     *
     * @param name to match
     * @return list of criteriaTheme
     */
    public List<CriteriaTheme> getByName(String name) {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder qb = session.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<CriteriaTheme> q = qb.createQuery(CriteriaTheme.class);
        Root<CriteriaTheme> root = q.from(CriteriaTheme.class);
        q.where(qb.equal(root.get("name"), name));
        List<CriteriaTheme> criteriaTheme = null;
        if (session.createQuery(q) != null) {
            criteriaTheme = session.createQuery(q).getResultList();
        }

        return criteriaTheme;
    }
}
