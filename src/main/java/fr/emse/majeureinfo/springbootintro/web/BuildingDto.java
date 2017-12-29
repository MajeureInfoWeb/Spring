package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.model.Building;
import fr.emse.majeureinfo.springbootintro.model.Light;
import fr.emse.majeureinfo.springbootintro.model.Noise;
import fr.emse.majeureinfo.springbootintro.model.Room;

public class BuildingDto {

    //private final Long id;
    private final Long buildingid;
    private final RoomDto room;


    public BuildingDto(Building building) {
        //this.id = building.getId();
        this.buildingid = building.getBuildingId();
        this.room = new RoomDto(building.getRoom());
    }

    /*public Long getId() {
        return id;
    }*/
    public Long getBuildingId() {
        return buildingid;
    }

    public RoomDto getRoom() {
        return room;
    }


}