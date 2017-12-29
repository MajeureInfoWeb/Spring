package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.model.Building;
import fr.emse.majeureinfo.springbootintro.model.Room;

import java.util.ArrayList;
import java.util.List;

public class BuildingDto {

    private final Long buildingid;
    private final String nom;
    private final List<RoomDto> rooms;



    public BuildingDto(Building building) {
        this.buildingid = building.getBuildingId();
        this.nom = building.getNom();
        List<Room> maliste = building.getRooms();
        rooms = new ArrayList<>();
        for (int i = 0; i < maliste.size(); i++){
            this.rooms.add(new RoomDto(maliste.get(i)));
        }
        // C'est une liste de Dto
    }

    public Long getBuildingId() {
        return buildingid;
    }
    public List<RoomDto> getRooms() {
        return rooms;
    }
    public String getNom() {
        return nom;
    }

    /*On ne fait pas de set sur des objet final */

}