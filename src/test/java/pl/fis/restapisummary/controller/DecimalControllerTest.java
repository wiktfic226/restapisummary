package pl.fis.restapisummary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.fis.restapisummary.service.MessageService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DecimalControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    MessageService messageService;

    @BeforeEach
    public void setDefaultMessageServiceValues() {
        messageService.setDecimalPlaces(2);
        messageService.setMultiplier(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 4, 5, 1, 0})
    public void setDecimalTest(Integer decimal) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/decimal")
                .content(objectMapper.writeValueAsString(decimal)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(decimal, messageService.getDecimalPlaces());
    }

    @Test
    public void getDecimalTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/decimal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$", Is.is(2)));
    }
}
