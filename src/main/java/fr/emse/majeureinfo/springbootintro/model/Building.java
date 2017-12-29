package fr.emse.majeureinfo.springbootintro.model;

import javax.persistence.*;

@Entity
@SuppressWarnings("serial")
@Table(name="BUILDING")
public class Building {

    @Id
    @GeneratedValue
    private Long id;
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


    /**
     * The Room of a builduing
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Room room;

}