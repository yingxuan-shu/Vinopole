package vinopole.dao;

import org.hibernate.Session;
import vinopole.HibernateUtil;
import vinopole.model.ResponseForm;
import vinopole.model.Serie;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.Collections;
import java.util.List;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 24/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public class ResponseFormDAO extends DAO<ResponseForm> {

    @Override
    public ResponseForm get(long id) {
        Session session = HibernateUtil.getSession();

        return session.load(ResponseForm.class, id);
    }

    @Override
    public List<ResponseForm> getAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<ResponseForm> query = session.getCriteriaBuilder().createQuery(ResponseForm.class);
        query.from(ResponseForm.class);

        return session.createQuery(query).getResultList();
    }

    /**
     * Get responses from DB with serie ID
     *
     * @param serieId
     * @return list of response
     */
    public List<ResponseForm> getBySerie(long serieId) {
        Session session = HibernateUtil.getSession();
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        Metamodel m = em.getMetamodel();

        EntityType<ResponseForm> ResponseForm_ = m.entity(ResponseForm.class);
        EntityType<Serie> Serie_ = m.entity(Serie.class);


        CriteriaQuery<ResponseForm> query = cb.createQuery(ResponseForm.class);
        Root<ResponseForm> responseForm = query.from(ResponseForm_);

        Join serie = responseForm.join(ResponseForm_.getSingularAttribute("serie"));
        serie.on(cb.equal(serie.get(Serie_.getSingularAttribute("id")), serieId));
        List<ResponseForm> responses = session.createQuery(query).getResultList();
        Collections.sort(responses);
        return responses;
    }
}
