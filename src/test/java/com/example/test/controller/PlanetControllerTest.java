package com.example.test.controller;

import com.example.test.model.Overlord;
import com.example.test.model.Planet;
import com.example.test.repo.OverlordRepository;
import com.example.test.repo.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQLDB)
class PlanetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private OverlordRepository overlordRepository;

    @Test
    void create() throws Exception {
        MockHttpServletRequestBuilder createPlanet = post("/planet")
                .param("name", "a");
        mockMvc.perform(createPlanet)
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void deletePlanet() throws Exception {
        mockMvc.perform(delete("/planet/1"))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void appointOverlord() throws Exception {
        Overlord o = new Overlord("a", 333);
        overlordRepository.save(o);
        Planet p = new Planet("p", null);
        planetRepository.save(p);
        MockHttpServletRequestBuilder updatePlanet = patch("/planet/3")
                .param("overlord_id", "1");
        mockMvc.perform(updatePlanet)
                .andExpect(redirectedUrl("/"));
    }
}