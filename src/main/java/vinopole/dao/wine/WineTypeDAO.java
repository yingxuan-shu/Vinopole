package vinopole.dao.wine;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;
import vinopole.HibernateUtil;
import vinopole.dao.DAO;
import vinopole.model.wine.WineType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 24/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class WineTypeDAO extends DAO<WineType> {

    @Override
    public WineType get(long id) {
        Session session = HibernateUtil.getSession();

        return session.load(WineType.class, id);
    }

    @Override
    public List<WineType> getAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<WineType> query = session.getCriteriaBuilder().createQuery(WineType.class);
        query.from(WineType.class);

        return session.createQuery(query).getResultList();
    }

    /**
     * Get wineType from DB with name
     * @param name to match
     * @return list of wineType
     */
    public List<WineType> getByName(String name) {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder qb = session.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<WineType> q = qb.createQuery(WineType.class);
        Root<WineType> root = q.from(WineType.class);
        q.where(qb.equal(root.get("name"),name));
        List<WineType> wineTypes = null;
        Query<WineType> query = session.createQuery(q);
        wineTypes = query.getResultList();

        return wineTypes;
    }
}
