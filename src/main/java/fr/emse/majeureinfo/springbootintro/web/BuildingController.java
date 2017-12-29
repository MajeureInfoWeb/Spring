package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.dao.BuildingDao;
import fr.emse.majeureinfo.springbootintro.model.Building;
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

    /*On renvoie tout*/
    @GetMapping
    public List<BuildingDto> list() {
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    /*On renvoie 1 building*/
    @GetMapping(value = "/{buildingId}/content")
    public BuildingDto get(@PathVariable long buildingId) {
        return new BuildingDto(buildingDao.findOne(buildingId)); // On crée une nouvelle instance de BuildingDto avec l'id trouvé
    }

    /*On éteint toutes les lumières du building */
    @PostMapping(value = "/{buildingId}/switchLofBOff")
    public List<BuildingDto> switchLofBOff(@PathVariable Long buildingId) {
        Building myBuilding = buildingDao.findOne(buildingId) ;
        myBuilding.switchLOff();
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    /*On allume toutes les lumières du building */
    @PostMapping(value = "/{buildingId}/switchLofBOn")
    public List<BuildingDto> switchLofBOn(@PathVariable Long buildingId) {
        Building myBuilding = buildingDao.findOne(buildingId) ;
        myBuilding.switchLOn();
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    /*On éteint tous les ringers du building */
    @PostMapping(value = "/{buildingId}/switchRofBOff")
    public List<BuildingDto> switchRofBOff(@PathVariable Long buildingId) {
        Building myBuilding = buildingDao.findOne(buildingId) ;
        myBuilding.switchROff();
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    /*On allume tous les ringers du building */
    @PostMapping(value = "/{buildingId}/switchRofBOn")
    public List<BuildingDto> switchRofBOn(@PathVariable Long buildingId) {
        Building myBuilding = buildingDao.findOne(buildingId) ;
        myBuilding.switchROn();
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    /*On éteint tout le building*/
    @PostMapping(value = "/{buildingId}/switchAllofBOff") // On va tout éteindre ! (en post avec une liste)
    public List<BuildingDto> switchAllofBOff(@PathVariable Long buildingId) {
        switchLofBOff(buildingId);
        return switchRofBOff(buildingId);
        // Je le fais comme ça pour changer mais je ne suis pas sûr que ça soit mieux,
        // il y a des listes qui retournées qui ne servent à rien,
        // ceci dit, on n'appelle pas findOne ...
    }

    /*On allume tout le building*/
    @PostMapping(value = "/{buildingId}/switchAllofBOn") // Allumer le feu ! (en post avec une liste)
    public List<BuildingDto> switchAllofBOn(@PathVariable Long buildingId) {
        switchLofBOn(buildingId);
        return switchRofBOn(buildingId);
    }

    /*Pour l'exercice, on allume toutes les lumières */
    @PostMapping(value = "/switchAllLOn")
    public List<BuildingDto> switchAllLOn() {
        List<Building> myBuildings = buildingDao.findAll() ; // On récupère tout
        for (int i = 0 ; i < myBuildings.size() ; i++) {
            myBuildings.get(i).switchLOn(); // peut-être ici aussi pourrait-on le faire avec les Dto mais j'ai pas fait !
        }
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    /*Pour l'exercice, on allume tous les ringers */
    @PostMapping(value = "/switchAllROn")
    public List<BuildingDto> switchAllROn() {
        List<Building> myBuildings = buildingDao.findAll() ;
        for (Building myBuilding : myBuildings) {
            myBuilding.switchROn();
        }
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    /*Pour l'exercice, on éteint toutes les lumières */
    @PostMapping(value = "/switchAllLOff")
    public List<BuildingDto> switchAllLOff() {
        List<Building> myBuildings = buildingDao.findAll() ; // On récupère tout
        for (Building myBuilding : myBuildings) {
            myBuilding.switchLOff();
        }
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    /*Pour l'exercice, on éteint tous les ringers */
    @PostMapping(value = "/switchAllROff") // On va tout tout éteindre ! (en post avec une liste)
    public List<BuildingDto> switchAllROff() {
        List<Building> myBuildings = buildingDao.findAll() ; // On récupère tout
        for (Building myBuilding : myBuildings) {
            myBuilding.switchROff();
        }
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList()); // On repasse tout le DTO en retour :)
    }

    /*On éteint tout */
    @PostMapping(value = "/shutdownAll") // SAUVONS LA PLANETE
    public List<BuildingDto> shutdownAll() { //facile à faire, on a déjà tout décomposé
        switchAllLOff();
        return switchAllROff();
    }
}
