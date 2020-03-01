package bjosek;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.jayway.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class HelloControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        RestAssured.port = port;
    }

    @Test
    public void testinfo() throws Exception {
        mvc.perform(get("/api/appinfo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Spring Boot Application")));

    }

    @Test
    public void testinfoassured() {
        when()
                .get("/api/appinfo")
                .then()
                .statusCode(SC_OK)
                .body(is(containsString("Spring Boot Application")));
    }
}