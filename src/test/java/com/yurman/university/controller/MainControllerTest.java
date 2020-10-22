package com.yurman.university.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
