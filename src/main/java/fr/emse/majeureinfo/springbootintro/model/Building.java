package fr.emse.majeureinfo.springbootintro.model;

import org.springframework.data.annotation.Persistent;

import javax.persistence.*;
import java.util.List;

@Entity
@SuppressWarnings("serial")
@Table(name="BUILDING")
public class Building {

    @Id
    @GeneratedValue
    private Long buildingid;
    //un seul id est nécessaire
    // En revanche on est obligé d'avoir une table avec le nom des buildings
    // et une autre table de join où on dit lesquels ont quels rooms

    // Crée la colonne dans la BDD (mais ça marche même si je ne le mets pas)
    @Column(nullable = false)
    private String nom;

    /**
     * One of the Room of a building
     */
    // je sais pas trop ce que fait le mappedBy mais j'avais noté ça ...
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