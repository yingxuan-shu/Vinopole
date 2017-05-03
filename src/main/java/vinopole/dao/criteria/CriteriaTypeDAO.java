package vinopole.dao.criteria;

import org.hibernate.Session;
import vinopole.HibernateUtil;
import vinopole.dao.DAO;
import vinopole.model.criteria.CriteriaType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 24/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class CriteriaTypeDAO extends DAO<CriteriaType> {

    @Override
    public CriteriaType get(long id) {
        Session session = HibernateUtil.getSession();

        return session.load(CriteriaType.class, id);
    }

    @Override
    public List<CriteriaType> getAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<CriteriaType> query = session.getCriteriaBuilder().createQuery(CriteriaType.class);
        query.from(CriteriaType.class);

        return session.createQuery(query).getResultList();
    }

    /**
     * Get criteriaType from DB with name
     *
     * @param name to match
     * @return list of criteriaTYpe
     */
    public List<CriteriaType> getByName(String name) {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder qb = session.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<CriteriaType> q = qb.createQuery(CriteriaType.class);
        Root<CriteriaType> root = q.from(CriteriaType.class);
        q.where(qb.equal(root.get("name"), name));
        List<CriteriaType> criteriaTypes = null;
        if (session.createQuery(q) != null) {
            criteriaTypes = session.createQuery(q).getResultList();
        }

        return criteriaTypes;
    }
}
