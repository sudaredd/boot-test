package app.boot.boottest.controller;

import app.boot.boottest.model.User;
import app.boot.boottest.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(List.of(new User("sud", "sud@gmail.com")));
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("sud"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("sud@gmail.com"));
    }

    @Test
    public void shouldAllowCreationForUnauthenticatedUsers() throws Exception {
        this.mockMvc
            .perform(
                post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                        "userName": "duke",
                        "email":"duke@spring.io"
                        }
                        """)
                    .with(csrf())
            )
            .andExpect(status().isCreated())
            .andExpect(header().exists("Location"))
            .andExpect(header().string("Location", Matchers.containsString("duke")));

        verify(userService).storeNewUser(any(User.class));
    }
}