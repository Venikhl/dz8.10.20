package org.step.linked.step.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.step.linked.step.entity.Planet;
import org.step.linked.step.entity.Profile;
import org.step.linked.step.entity.User;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
@Sql(value = {"classpath:db/test/planets/init_planet_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:db/test/planets/delete_planet_test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Test
    public void shouldReturnAllPlanets() {
        List<Planet> planets = planetRepository.findAll();

        Assertions.assertNotNull(planets);
        Assertions.assertFalse(planets.isEmpty());
    }

    @Test
    public void shouldReturnPlanetById() {
        Optional<Planet> planetById = planetRepository.findById(1L);

        Assertions.assertTrue(planetById.isPresent());
        Assertions.assertEquals(1L, planetById.get().getId());
    }

    @Test
    public void shouldSavePlanet() {
        final long id = 99L;
        final Planet planet = Planet.builder()
                .id(id)
                .name("ninety ninth")
                .weight(9900000L)
                .radius(990000L)
                .build();

        planetRepository.saveAndFlush(planet);

        Optional<Planet> byId = planetRepository.findById(id);

        Assertions.assertTrue(byId.isPresent());
        Assertions.assertEquals(id, byId.get().getId());
    }

    @Test
    public void shouldDeletePlanetById() {
        planetRepository.deleteById(99L);

        planetRepository.flush();

        Optional<Planet> byId = planetRepository.findById(99L);

        Assertions.assertFalse(byId.isPresent());
    }
}
