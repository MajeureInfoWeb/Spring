package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Building;

import java.util.List;

public interface BuildingDaoCustom {

    List<Building> findBuildingId(long a);
}