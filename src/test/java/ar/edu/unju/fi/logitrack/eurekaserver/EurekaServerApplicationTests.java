package ar.edu.unju.fi.logitrack.eurekaserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EurekaServerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("El contexto del servidor Eureka arranca")
    void contextoArranca() {
        // Si el contexto no arranca, falla antes de llegar acá.
    }

    @Test
    @DisplayName("/actuator/health responde UP")
    void healthResponde() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    @DisplayName("El panel de Eureka responde en la raíz")
    void panelResponde() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
