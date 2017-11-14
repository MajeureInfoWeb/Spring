package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.dao.RoomDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    */public List<RoomDto> list() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/On")
    public List<RoomDto> listWithOnLight() {
        return roomDao.findRoomsWithOnLight().stream().map(RoomDto::new).collect(Collectors.toList());
    }

}

/* Méthodes à ajouter :
    .get(roomid) sur api/rooms/{id} ou api/rooms/{id}/content

    .switchLight(roomid) sur api/room/{id]/switch.light

    .switchRinger(roomid) sur api/rooms/{id]/switch.ringer

    listWithOnLights() sur api/rooms/list-with-on-lights

    et du coup, il aura fallu aussi un RoomDaoImpl findRoomsWithOnLights()

 */