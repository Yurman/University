package com.foxminded.university.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.foxminded.university.config.TestDataConfiguration;
import com.foxminded.university.config.WebConfiguration;

@ContextConfiguration(classes = { WebConfiguration.class, TestDataConfiguration.class })
@WebAppConfiguration()
@ExtendWith(SpringExtension.class)

public class MainControllerTest {

    private MainController controller;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        controller = new MainController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldReturnMainView() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/");
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.view().name("main"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
