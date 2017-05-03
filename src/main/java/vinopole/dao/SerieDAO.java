package vinopole.dao;

import org.hibernate.Session;
import vinopole.HibernateUtil;
import vinopole.model.Serie;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 24/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class SerieDAO extends DAO<Serie> {

    @Override
    public Serie get(long id) {
        Session session = HibernateUtil.getSession();

        return session.load(Serie.class, id);
    }

    @Override
    public List<Serie> getAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<Serie> query = session.getCriteriaBuilder().createQuery(Serie.class);
        query.from(Serie.class);
        return session.createQuery(query).getResultList();
    }
}
