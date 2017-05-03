package vinopole.dao.wine;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;
import vinopole.HibernateUtil;
import vinopole.dao.DAO;
import vinopole.model.wine.Wine;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 24/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class WineDAO extends DAO<Wine> {

    @Override
    public Wine get(long id) {
        Session session = HibernateUtil.getSession();

        return session.load(Wine.class, id);
    }

    @Override
    public List<Wine> getAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<Wine> query = session.getCriteriaBuilder().createQuery(Wine.class);
        query.from(Wine.class);

        return session.createQuery(query).getResultList();
    }


}
