package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Building;
import fr.emse.majeureinfo.springbootintro.model.Light;
import fr.emse.majeureinfo.springbootintro.model.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


public class BuildingDaoImpl implements BuildingDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Building> findBuildingId(long a) {
        String jpql = "select lt from Building lt where lt.buildingid = :value";
        TypedQuery<Building> query = em.createQuery(jpql, Building.class);
        return query.setParameter("value", a)
                .getResultList();
    }
}