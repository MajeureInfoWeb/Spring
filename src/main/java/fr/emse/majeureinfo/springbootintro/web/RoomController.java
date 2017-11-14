package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.dao.RoomDao;
import fr.emse.majeureinfo.springbootintro.model.Room;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/rooms")
@Transactional
public class RoomController {

    private final RoomDao roomDao;


    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @GetMapping
    /*public String list() {
        return "coucou";
    */
    public List<RoomDto> list() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{roomId}/content")
    public RoomDto get(@PathVariable long roomId) {
        return new RoomDto(roomDao.findOne(roomId)); // On crée une nouvelle instance de RoomDto avec l'id trouvé
    }

    @GetMapping(value = "/{roomId}/switch")
    public RoomDto switchLight(@PathVariable Long roomId) {
        Room myRoom = roomDao.findOne(roomId) ; // On récupère la room
        myRoom.switchLight(); // On switch
        return new RoomDto(myRoom) ; // On repasse le DTO en retour :)

    }
}

/* Méthodes à ajouter :

    .switchLight(roomid) sur api/room/{id]/switch.light

    .switchRinger(roomid) sur api/rooms/{id]/switch.ringer

    listWithOnLights() sur api/rooms/list-with-on-lights

    et du coup, il aura fallu aussi un RoomDaoImpl findRoomsWithOnLights()

 */