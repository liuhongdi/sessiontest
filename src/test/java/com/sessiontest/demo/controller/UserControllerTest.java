package com.sessiontest.demo.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import com.jayway.jsonpath.JsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    private static MockHttpSession sessionPub;
    /**
     * 测试方法开始之前执行   设置模拟Mvc
     */
    @BeforeAll
    public static void setupMockMvc() {
        sessionPub = new MockHttpSession();
        sessionPub.setAttribute("username", "liuhongdi");
    }
    @Test
    @DisplayName("测试get用户名,有session")
    void getTest() throws Exception {
        //MockHttpSession session = new MockHttpSession();
        //session.setAttribute("username", "liuhongdi");
        MvcResult mvcResult = mockMvc.perform(get("/user/get")
                .session(sessionPub)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content, equalTo("liuhongdi"));
    }

    @Test
    @DisplayName("测试get用户名,无session")
    void getTestFail() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user/get")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content, equalTo(""));
    }
}