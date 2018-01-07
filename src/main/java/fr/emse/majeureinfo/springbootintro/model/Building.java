package fr.emse.majeureinfo.springbootintro.model;

import javax.persistence.*;
import java.util.List;

@Entity
@SuppressWarnings("serial")
@Table(name="BUILDING")
public class Building {

    @Id
    @GeneratedValue
    private Long buildingid;
    // un seul id est nécessaire
    // En revanche on est obligé d'avoir une table avec le nom des buildings
    // et une autre table de join où on dit lesquels ont quelles rooms

    // Crée la colonne dans la BDD (mais ça marche même si je ne le mets pas)
    @Column(nullable = false)
    private String nom;

    /*
     * One of The RoomS of A building
     */
    // je sais pas trop ce que fait le mappedBy et ça marche sans
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;


    @SuppressWarnings("unused")
    public Building() {
    }

    public Building(List<Room> rooms, String nom) {
        // Un building, c'est DES rooms et un nom
        this.rooms = rooms;
        this.nom = nom;
    }

    // Il m'a proposé de condenser mes boucles, j'ai dit oui ! c'est joli aussi :)
    public void switchAllOn() {
        for (Room room : this.rooms) room.switchOn();
    }
    public void switchAllOff() {
        for (Room room : this.rooms) room.switchOff();
    }

    // Le détail de ma pensée est ici
    public void switchLOff() {
        for (int i = 0 ; i < this.rooms.size() ; i ++) this.rooms.get(i).switchLOff();
    }

    public void switchROff() {
        for (int i = 0 ; i < this.rooms.size() ; i ++){
            this.rooms.get(i).switchROff();
        }
    }

    public void switchLOn() {
        for (Room room : this.rooms) room.switchLOn();
    }
    public void switchROn() {
        for (Room room : this.rooms) room.switchROn();
    }


    public Long getBuildingId() {
        return this.buildingid;
    }
    public void setBuildingId(Long id) {
        this.buildingid = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Room> getRooms() {
        return rooms;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}