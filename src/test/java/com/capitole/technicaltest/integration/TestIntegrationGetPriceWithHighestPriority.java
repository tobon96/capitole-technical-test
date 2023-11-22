package com.capitole.technicaltest.integration;

import com.capitole.technicaltest.integration.configuration.MigrationConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(MigrationConfiguration.class)
public class TestIntegrationGetPriceWithHighestPriority {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void givenRestCallToController_shouldReturnPriceThatApplies() throws Exception {
    var params = new LinkedMultiValueMap<String, String>();
    params.add("brand_id", "1");
    params.add("product_id", "35455");
    params.add("date", "2020-06-16T21:00:00");

    var result = mockMvc
        .perform(get("/price")
            .params(params))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priority").value(1))
        .andExpect(jsonPath("$.priceList").value(4));

    System.out.println(result);
  }

}
