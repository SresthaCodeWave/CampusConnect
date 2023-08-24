package com.example.ecommerce;

import com.example.ecommerce.controller.UserController;
import com.example.ecommerce.dto.SignInDto;
import com.example.ecommerce.dto.SignInResponseDto;
import com.example.ecommerce.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest
public class ControllerLevelTesting
{
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController signInController;

    @Test
    public void testSignIn() throws Exception {
        SignInDto signInDto = new SignInDto("testuser@example.com", "password");

        SignInResponseDto signInResponseDto = new SignInResponseDto("CORRECTUSER", "token");
        Mockito.when(userService.signIn(signInDto)).thenReturn(signInResponseDto);

        mockMvc.perform(post("/signIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(signInDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value("CORRECTUSER"))
                .andExpect(jsonPath("$.token").value("token"));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}





