package fr.emse.majeureinfo.springbootintro.dao;

import fr.emse.majeureinfo.springbootintro.model.Noise;

import java.util.List;

public interface NoiseDaoCustom {
    public List<Noise> findOnRingers();

}