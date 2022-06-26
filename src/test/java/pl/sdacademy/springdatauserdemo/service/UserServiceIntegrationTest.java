package pl.sdacademy.springdatauserdemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sdacademy.springdatauserdemo.BaseIntegrationTest;
import pl.sdacademy.springdatauserdemo.model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private UserService userService;

    @Test
    void userShouldBeCreated() {
        // given
        // when
        User saved = userService.create(prepareUser());

        // then
        assertNotNull(saved);
        assertEquals("Jan", saved.getFirstName());
        assertEquals("Kowalski", saved.getLastName());
        User foundSavedUser = userService.findById(saved.getId());
        assertNotNull(foundSavedUser);
        assertEquals(saved.getId(), foundSavedUser.getId());
    }
}