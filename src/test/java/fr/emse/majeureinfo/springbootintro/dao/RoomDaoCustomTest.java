package fr.emse.majeureinfo.springbootintro.dao;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.DeleteAll;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import fr.emse.majeureinfo.springbootintro.model.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/test.properties")

public class RoomDaoCustomTest {

    @Autowired
    private RoomDao roomDao;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    protected static final DbSetupTracker TRACKER = new DbSetupTracker();

    private static final Operation DELETE_ALL = DeleteAll.from("room", "light", "noise");

    // On va faire trois opérations d'entrée dans la BDD
    protected void dbSetup(Operation operation, Operation operation2, Operation operation3) {
        DbSetup setup = new DbSetup(new DataSourceDestination(dataSource),
                Operations.sequenceOf(DELETE_ALL, operation, operation2, operation3));
        TRACKER.launchIfNecessary(setup);
    }

    @Before
    public void prepare() {
        Operation light =
                Insert.into("LIGHT")
                        .withDefaultValue("status", Status.OFF)//on peut mettre ON ici et du coup il faudra avoir une taille de 1 plus loin mais tout marche !!
                        .columns("id", "level")
                        .values(1L, 2)
                        .build();

        Operation noise =
                Insert.into("NOISE")
                        .withDefaultValue("status", Status.ON)
                        .columns("id", "level")
                        .values(2L, 3)
                        .build();

        Operation room =
                Insert.into("ROOM")
                        //.withDefaultValue("status", Status.ON)
                        .columns("id", "light_id", "noise_id")
                        .values(3L, 1L, 2L) //On fait la BDD avec les bonnes clés primaires
                        .build();
        dbSetup(light, noise, room);
    }

    @Test
    public void shouldfindRoomsWithOnLight() {
        TRACKER.skipNextLaunch();
        assertThat(roomDao.findRoomsWithOnLight()).hasSize(0);//ici on peut modifier ce qu'on veut que le test renvoie
    }

}