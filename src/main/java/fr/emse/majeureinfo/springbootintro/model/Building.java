package fr.emse.majeureinfo.springbootintro.model;

import org.springframework.data.annotation.Persistent;

import javax.persistence.*;

@Entity
@SuppressWarnings("serial")
@Table(name="BUILDING")
public class Building {

    @Id
    @GeneratedValue
    private Long id;

    // Bon ça marche même si je ne mets pas d'arobase... (Je me suis pris la tête alors qu'il me manquait juste une virgule dans ma requête)
    private String nom;
    private Long buildingid;


    //@Column(nullable = false)

    @SuppressWarnings("unused")
    public Building() {
    }

    public Building(Room room) {
        this.room = room;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildingId() {
        return this.buildingid;
    }
    public void setBuildingId(Long id) {
        this.buildingid = buildingid;
    }

    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * The Room of a building
     */
    // je sais pas encore bien ce que ça fait vu que ça marchait avec le onetoone
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Room room;

}