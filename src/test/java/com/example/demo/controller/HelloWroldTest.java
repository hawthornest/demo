package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

/**
 * @Author yyhu3
 * @Date 2019-06-03 19:43
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWroldTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void divResult() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/yanxuan/test?divisor=132&dividend=1&errorMsg=test")).andExpect(MockMvcResultMatchers.status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/yanxuan/test?divisor=99999999&dividend=1&errorMsg=test")).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void add() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/yanxuan/test/add?add1=132&add2=1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

}