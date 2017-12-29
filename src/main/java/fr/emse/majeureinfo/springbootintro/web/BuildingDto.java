package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.model.Building;
import fr.emse.majeureinfo.springbootintro.model.Light;
import fr.emse.majeureinfo.springbootintro.model.Noise;
import fr.emse.majeureinfo.springbootintro.model.Room;

import java.util.List;

public class BuildingDto {

    private final Long buildingid;
    private final String nom;
    private final List<Room> rooms;
    // dans room on prenait les dto des light et tout
    // mais on n'en a pas besoin là
    // private RoomDto room;


    public BuildingDto(Building building) {
        this.buildingid = building.getBuildingId();
        this.nom = building.getNom();
        /*List<Room> maliste = building.getRooms();
        for (int i = 0; i < maliste.size(); i++){
            this.rooms.add(new RoomDto(maliste.get(i)));
        }
        J'ai pas besoin d'une liste de dto, c'est trop compliqué
        */
        this.rooms = building.getRooms();
    }

    public Long getBuildingId() {
        return buildingid;
    }
    public List<Room> getRooms() {
        return rooms;
    }
    /*public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
        il veut pas psk genre c'est final

        je crois que je n'ai pas tout à fait résolu mon dto ...
        */

}