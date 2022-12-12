package kea.dilemmaspilbackend.admin.controller;

import kea.dilemmaspilbackend.admin.security.JWTUtilToken;
import kea.dilemmaspilbackend.admin.security.JwtDetailsService;
import kea.dilemmaspilbackend.admin.service.AdminUserService;
import kea.dilemmaspilbackend.dilemmas.controller.AdminController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private JwtDetailsService jwtDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtilToken jwtUtilToken;
    private AdminUserService adminUserService;

    @BeforeEach
    public void setUp() {
        adminUserService = new AdminUserService(authenticationManager, jwtUtilToken, jwtDetailsService);
        jwtDetailsService = new JwtDetailsService();
        mockMvc = MockMvcBuilders.standaloneSetup(new AdminController(adminUserService)).build();
    }


    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                "username": "tom",
                                "password": "123"
                                }
                                """)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
