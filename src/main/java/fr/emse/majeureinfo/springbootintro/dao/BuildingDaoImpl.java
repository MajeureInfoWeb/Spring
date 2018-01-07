package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Building;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


public class BuildingDaoImpl implements BuildingDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    // Fonction pour récupérer un Building avec son Id.
    // La requête fonctionne comme pour les lights sauf que cette fois, il y a un paramètre, l'Id du Building
    // Il se trouve qu'on ne s'en sert jamais, on utilise plutôt findOne
    public List<Building> findBuildingId(long a) {
        String jpql = "select lt from Building lt where lt.buildingid = :value";
        TypedQuery<Building> query = em.createQuery(jpql, Building.class);
        return query.setParameter("value", a)
                .getResultList();
    }

    // Attention, si on veut rajouter des choses ici, il ne faut pas oublier de le déclarer aussi dans le DaoCustom
}