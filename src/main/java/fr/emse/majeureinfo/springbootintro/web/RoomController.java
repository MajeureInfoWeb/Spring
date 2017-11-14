package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.dao.RoomDao;
import fr.emse.majeureinfo.springbootintro.model.Noise;
import fr.emse.majeureinfo.springbootintro.model.Status;
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
    */public List<RoomDto> list() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

}

    @PostMapping(path = "/switch/ringer", consumes = "application/json")
    public RoomDto switchRinger(@RequestBody String roomIdString){

        long roomId = Long.parseLong(roomIdString);
        Noise noise = roomDao.findOne(roomId).getNoise();
        if (noise.getStatus().equals(Status.OFF))
            noise.setStatus(Status.ON);
        else
            noise.setStatus(Status.OFF);
        return get(roomId);
    }

    @GetMapping(value = "/light/on")
    public List<RoomDto> listWithOnLight(){
        return roomDao.findRoomsWithLightsOn().stream().map(RoomDto::new).collect(Collectors.toList());
    }