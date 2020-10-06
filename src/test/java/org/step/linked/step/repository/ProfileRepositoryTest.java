package org.step.linked.step.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.step.linked.step.entity.Profile;
import org.step.linked.step.entity.User;

import java.util.List;
import java.util.Optional;

/*
@DataJpaTest - мы загружаем только слой базы данных
а так же конфигурируем InMemory DB
Используется H2 так как зависимость находится в classpath
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
@Sql(value = {"classpath:db/test/users/init_user_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:db/test/users/delete_user_test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProfileRepositoryTest {

    private static final Long PROFILE_ID_TEST = 1L;
    private static final String PROFILE_DESCRIPTION_TEST = "second";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void shouldReturnAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();

        Assertions.assertNotNull(profiles);
        Assertions.assertFalse(profiles.isEmpty());
    }

    @Test
    public void shouldReturnProfileById() {
        Optional<Profile> profileById = profileRepository.findById(PROFILE_ID_TEST);

        Assertions.assertTrue(profileById.isPresent());
        Assertions.assertEquals(PROFILE_ID_TEST, profileById.get().getId());
    }

    @Test
    public void shouldSaveProfile() {
        final long id = 105L;
        final Profile profile = new Profile(id, PROFILE_DESCRIPTION_TEST);

        profileRepository.save(profile);

        Optional<Profile> byId = profileRepository.findById(id);

        Assertions.assertTrue(byId.isPresent());
        Assertions.assertEquals(id, byId.get().getId());
    }

    @Test
    public void shouldDeleteProfileById() {
        profileRepository.deleteById(PROFILE_ID_TEST);

        Optional<Profile> byId = profileRepository.findById(PROFILE_ID_TEST);

        Assertions.assertFalse(byId.isPresent());
    }

    @Test
    public void shouldDeleteProfile() {
        final long id = 2L;

        final Profile profile = new Profile(id, PROFILE_DESCRIPTION_TEST);

        profileRepository.delete(profile);

        Optional<Profile> byId = profileRepository.findById(id);

        Assertions.assertFalse(byId.isPresent());
    }
}
