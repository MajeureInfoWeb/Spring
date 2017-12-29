package fr.emse.majeureinfo.springbootintro.model;

import javax.persistence.*;

@Entity
@SuppressWarnings("serial")
@Table(name="ROOM")
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    //@Column(nullable = false)

    @SuppressWarnings("unused")
    public Room() {
    }

    public Room(Light light, Noise noise) {
        this.light = light;
        this.noise = noise;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public Noise getNoise() {
        return noise;
    }

    public void setNoise(Noise noise) {
        this.noise = noise;
    }

    public void switchRinger() {
        if (noise.getStatus() == Status.ON){
            noise.setStatus(Status.OFF);
        }
        else {
            noise.setStatus(Status.ON);
        }
    }
  
  public void switchLight() {
        if (light.getStatus() == Status.ON){
            light.setStatus(Status.OFF) ;
        }
        else {
            light.setStatus(Status.ON) ;
        }
    }

    public void switchOn() {
        light.setStatus(Status.ON);
        noise.setStatus(Status.ON);
    }

    public void switchOff() {
        light.setStatus(Status.OFF);
        noise.setStatus(Status.OFF);
    }

    /**
     * The Light of a room
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Light light;

    /**
     * The Noise of a room
     */

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Noise noise;
}