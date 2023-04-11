package br.com.parede;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.parede.dto.JwtResponse;
import br.com.parede.model.form.LoginForm;


@RunWith(SpringRunner.class)
@SpringBootTest
class ApiApplicationTests {
	
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    private MockMvc mockMvc;
    private String token;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        token = autenticar("rodrigo.parede@gmail.com", "123456");
    }
    
    private String autenticar(String email, String senha) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new LoginForm(email, senha));
            MvcResult result = mockMvc.perform(post("/auth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isOk())
                    .andReturn();
            String response = result.getResponse().getContentAsString();
            JwtResponse jwtResponse = objectMapper.readValue(response, JwtResponse.class);
            return jwtResponse.getToken();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Test
    public void testAutenticacaoComSucesso() {
        assertNotNull(token);
    }

}