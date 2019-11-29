package dhbw.online.bookly;

import dhbw.online.bookly.configuration.SpringSecurityWebAuxTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureMockMvc
public class WebApplicationSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("user")
    public void givenManagerUser_whenGetFooSalute_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user")
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }
}