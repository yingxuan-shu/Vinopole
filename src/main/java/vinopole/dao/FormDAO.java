package vinopole.dao;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import vinopole.HibernateUtil;
import vinopole.model.form.Form;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 24/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class FormDAO extends DAO<Form> {

    @Override
    public Form get(long id) {
        Session session = HibernateUtil.getSession();

        return session.load(Form.class, id);
    }

    @Override
    public List<Form> getAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<Form> query = session.getCriteriaBuilder().createQuery(Form.class);
        query.from(Form.class);

        return session.createQuery(query).getResultList();
    }

}
