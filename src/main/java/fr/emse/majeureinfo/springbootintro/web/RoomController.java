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
    public List<RoomDto> list() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/On")
    public List<RoomDto> listWithOnLight() {
        return roomDao.findRoomsWithOnLight().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/{roomId}/switchRinger") //On travaille en post pour pouvoir changer dans la BDD
    public RoomDto switchRinger(@PathVariable Long roomId) {
        Room myRoom = roomDao.findOne(roomId) ; // On récupère la room
        myRoom.switchRinger(); // On switch
        return new RoomDto(myRoom) ; // On repasse le DTO en retour :)
    }

    @PostMapping(value = "/{roomId}/switchRingerl") //On travaille en post et cette fois on fait une liste
    public List<RoomDto> switchRingerl(@PathVariable Long roomId) {
        Room myRoom = roomDao.findOne(roomId) ; // On récupère la room à changer
        myRoom.switchRinger(); // On switch
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList()); // On repasse tout le DTO en retour :)
    }


    @GetMapping(value = "/{roomId}/content")
    public RoomDto get(@PathVariable long roomId) {
        return new RoomDto(roomDao.findOne(roomId)); // On crée une nouvelle instance de RoomDto avec l'id trouvé
    }

    @PostMapping(value = "/{roomId}/switchLight")
    public RoomDto switchLight(@PathVariable Long roomId) {
        Room myRoom = roomDao.findOne(roomId) ; // On récupère la room
        myRoom.switchLight(); // On switch
        return new RoomDto(myRoom) ; // On repasse le DTO en retour :)

    }

    @PostMapping(value = "/{roomId}/switchLightl") //On travaille en post et cette fois on fait une liste
    public List<RoomDto> switchLightl(@PathVariable Long roomId) {
        Room myRoom = roomDao.findOne(roomId) ; // On récupère la room a changer
        myRoom.switchLight(); // On switch
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList()); // On repasse tout le DTO en retour :)
    }

    @PostMapping(value = "/{roomId}/setLight/{level}")
    public List<RoomDto> setLight(@PathVariable Long roomId, @PathVariable int level) {
        Room myRoom = roomDao.findOne(roomId) ; // On récupère la room a changer
        myRoom.getLight().setLevel(level); // On set la light avec le nouveau paramètre
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList()); // On repasse tout le DTO en retour :)
    }

    @PostMapping(value = "/{roomId}/setNoise/{level}")
    public List<RoomDto> setNoise(@PathVariable Long roomId, @PathVariable int level) {
        Room myRoom = roomDao.findOne(roomId) ; // On récupère la room a changer
        myRoom.getNoise().setLevel(level); // On set le Noise avec le nouveau paramètre
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList()); // On repasse tout le DTO en retour :)
    }
}
