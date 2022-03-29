package simplon.pros.web.rest;

import simplon.pros.BriefGroupeJHipsterApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the FooResource REST controller.
 *
 * @see FooResource
 */
@SpringBootTest(classes = BriefGroupeJHipsterApp.class)
public class FooResourceIT {

    private MockMvc restMockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        FooResource fooResource = new FooResource();
        restMockMvc = MockMvcBuilders
            .standaloneSetup(fooResource)
            .build();
    }

    /**
     * Test defaultAction
     */
    @Test
    public void testDefaultAction() throws Exception {
        restMockMvc.perform(get("/api/foo/default-action"))
            .andExpect(status().isOk());
    }
}
