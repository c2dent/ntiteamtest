package com.example.test.controller;

import com.example.test.model.Overlord;
import com.example.test.model.Planet;
import com.example.test.repo.OverlordRepository;
import com.example.test.repo.PlanetRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQLDB)
@AutoConfigureMockMvc
@SpringBootTest
class OverloadControllerTest {

    @Resource
    private OverlordRepository overlordRepository;

    @Resource
    private PlanetRepository planetRepository;

    @Autowired
    private MockMvc mockMvc;

    Overlord oa, ob, oc, od, oe, of, og, oh, oi, oj, ok, ol;
    Planet pa, pb, pc, pd;


    @BeforeEach
    void setUp() {
        oa = new Overlord(1L, "a", 1, new ArrayList<>());
        ob = new Overlord(2L, "b", 2, new ArrayList<>());
        oc = new Overlord(3L, "c", 3, new ArrayList<>());
        od = new Overlord(4L, "d", 4, new ArrayList<>());
        oe = new Overlord(5L, "e", 5, new ArrayList<>());
        of = new Overlord(6L, "f", 6, new ArrayList<>());
        og = new Overlord(7L, "g", 44, new ArrayList<>());
        oh = new Overlord(8L, "h", 45, new ArrayList<>());
        oi = new Overlord(9L, "i", 9, new ArrayList<>());
        oj = new Overlord(10L, "j", 10, new ArrayList<>());
        ok = new Overlord(11L, "k", 11, new ArrayList<>());
        ol = new Overlord(12L, "l", 12, new ArrayList<>());
        pa = new Planet(1L, "a", null);
        pb = new Planet("b", null);
        pc = new Planet("c", null);
        pd = new Planet( "d", null);
        overlordRepository.save(oa);
        overlordRepository.save(ob);
        overlordRepository.save(oc);
        overlordRepository.save(od);
        overlordRepository.save(oe);
        overlordRepository.save(of);
        overlordRepository.save(og);
        overlordRepository.save(oh);
        overlordRepository.save(oi);
        overlordRepository.save(oj);
        overlordRepository.save(ok);
        planetRepository.save(pa);
        planetRepository.save(pb);
        planetRepository.save(pc);
        planetRepository.save(pd);
        Planet p1 = planetRepository.findFirstByName(pa.getName()).orElse(null);
        Planet p2 = planetRepository.findFirstByName(pb.getName()).orElse(null);
        p1.setOverlord(og);
        p2.setOverlord(oh);
        planetRepository.save(p1);
        planetRepository.save(p2);
        og.setPlanets(Collections.singletonList(planetRepository.findFirstByName(pa.getName()).orElse(null)));
        oh.setPlanets(Collections.singletonList(planetRepository.findFirstByName(pb.getName()).orElse(null)));
    }

    @Test
    void youngOverlords()  throws Exception{
        List<Overlord> overlordList = Arrays.asList(oa, ob, oc, od, oe, of, oi, oj, ok, ol);
        mockMvc.perform(get("/overlord/young"))
                .andExpect(status().isOk())
                .andExpect(view().name("overlord/young"))
                .andExpect(model().attributeExists("overlords"))
                .andExpect(model().attribute("overlords", overlordList));
    }

    @Test
    void freeOverlords()  throws Exception{

        List<Overlord> overlordList = Arrays.asList(oa, ob, oc, od, oe, of, oi, oj, ok, ol);
        mockMvc.perform(get("/overlord/free"))
                .andExpect(status().isOk())
                .andExpect(view().name("overlord/free"))
                .andExpect(model().attributeExists("overlords"))
                .andExpect(model().attribute("overlords", overlordList));
    }

    @Test
    void  create() throws Exception{
        MockHttpServletRequestBuilder createOverlord = post("/overlord")
                .param("name", ol.getName())
                .param("age", String.valueOf(ol.getAge()));
        mockMvc.perform(createOverlord)
                .andExpect(redirectedUrl("/"));
    }


}