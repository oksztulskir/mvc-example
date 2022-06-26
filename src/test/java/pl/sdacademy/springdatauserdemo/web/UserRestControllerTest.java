package pl.sdacademy.springdatauserdemo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.sdacademy.springdatauserdemo.BaseIntegrationTest;
import pl.sdacademy.springdatauserdemo.model.User;
import pl.sdacademy.springdatauserdemo.model.UserRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRestControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCrateUserUsingPostRequest() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/user")
                .content(objectMapper.writeValueAsString(prepareUser()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", equalTo("Jan")))
                .andExpect(jsonPath("$.lastName", equalTo("Kowalski")))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    void shouldSaveAndGetUserUsingHttpGetMethod() throws Exception {
        User savedUser = userRepository.save(prepareUser());

        mockMvc.perform(get("/user/{id}", savedUser.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", equalTo((int) savedUser.getId())))
                .andExpect(jsonPath("$.firstName", equalTo(savedUser.getFirstName())));
    }
}
