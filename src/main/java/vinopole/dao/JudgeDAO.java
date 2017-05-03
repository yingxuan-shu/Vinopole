package vinopole.dao;

import org.hibernate.Session;
import vinopole.HibernateUtil;
import vinopole.model.Judge;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 24/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class JudgeDAO extends DAO<Judge> {

    @Override
    public Judge get(long id) {
        Session session = HibernateUtil.getSession();

        return session.load(Judge.class, id);
    }

    @Override
    public List<Judge> getAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<Judge> query = session.getCriteriaBuilder().createQuery(Judge.class);
        query.from(Judge.class);

        return session.createQuery(query).getResultList();
    }
}
