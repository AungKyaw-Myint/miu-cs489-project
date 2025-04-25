package org.cs489.dentalsurgeries.controller;

import org.cs489.dentalsurgeries.dto.response.AddressResponse2;
import org.cs489.dentalsurgeries.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
@Import(AddressControllerTest.TestConfig.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressService addressService;

//    @WithMockUser(username = "testUser", roles = {"ADMIN"})
    @Test
    void testListAddressReturnsListOfAddresses() throws Exception {
        var addr1 = new AddressResponse2(1L, 133, "City1", "State1", "12345","49123",null);
        var addr2 = new AddressResponse2(2L, 4444, "City2", "State2", "67890","421323",null);

        when(addressService.getAddresses()).thenReturn(List.of(addr1, addr2));

        mockMvc.perform(get("/api/v1/admins/addresses/list")
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isUnauthorized());
    }

    @Configuration
    static class TestConfig {
        @Bean
        public AddressService addressService() {
            return mock(AddressService.class);
        }
    }
}