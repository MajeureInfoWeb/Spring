package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Noise;
import fr.emse.majeureinfo.springbootintro.model.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class NoiseDaoImpl implements NoiseDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Noise> findOnRingers() {
        String jpql = "select rg from Noise rg where rg.status = :value";
        TypedQuery<Noise> query = em.createQuery(jpql, Noise.class);
        return query.setParameter("value", Status.ON)
                .getResultList();
    }
}