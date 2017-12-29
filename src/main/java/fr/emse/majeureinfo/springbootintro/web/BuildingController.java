package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.dao.BuildingDao;
import fr.emse.majeureinfo.springbootintro.dao.RoomDao;
import fr.emse.majeureinfo.springbootintro.model.Building;
import fr.emse.majeureinfo.springbootintro.model.Room;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/buildings")
@Transactional
public class BuildingController {

    private final BuildingDao buildingDao;


    public BuildingController(BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }

    @GetMapping
    public List<BuildingDto> list() {
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }


    @GetMapping(value = "/{buildingId}/content")
    public BuildingDto get(@PathVariable long buildingId) {
        return new BuildingDto(buildingDao.findOne(buildingId)); // On crée une nouvelle instance de BuildingDto avec l'id trouvé
    }

    @PostMapping(value = "/{buildingId}/switchAll") // On va tout éteindre ! (en post avec une liste)
    public List<BuildingDto> switchAll(@PathVariable Long buildingId) {
        Building myBuilding = buildingDao.findOne(buildingId) ; // On récupère le bon building
        myBuilding.switchAllOff(); // On switch
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList()); // On repasse tout le DTO en retour :)
    }
}

