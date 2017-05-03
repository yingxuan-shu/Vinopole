package vinopole.dao.criteria;

import org.hibernate.Session;
import vinopole.HibernateUtil;
import vinopole.dao.DAO;
import vinopole.model.criteria.Criteria;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 24/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class CriteriaDAO extends DAO<Criteria> {

    @Override
    public Criteria get(long id) {
        Session session = HibernateUtil.getSession();
        return session.load(Criteria.class, id);
    }

    @Override
    public List<Criteria> getAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<Criteria> query = session.getCriteriaBuilder().createQuery(Criteria.class);
        query.from(Criteria.class);

        return session.createQuery(query).getResultList();
    }
}
