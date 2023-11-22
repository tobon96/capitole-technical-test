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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

;


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

    mockMvc
        .perform(get("/price")
            .params(params))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.productId").value(35455))
        .andExpect(jsonPath("$.data.brandId").value(1))
        .andExpect(jsonPath("$.data.priceList").value(4))
        .andExpect(jsonPath("$.data.startDate").value("2020-06-15T16:00:00"))
        .andExpect(jsonPath("$.data.endDate").value("2020-12-31T23:59:59"))
        .andExpect(jsonPath("$.data.amount").value(38.95))
        .andExpect(jsonPath("$.error").isEmpty());
  }

  @Test
  public void givenRestCallToControllerAndWrongBrandIdParameter_shouldReturnError() throws Exception {
    var params = new LinkedMultiValueMap<String, String>();
    params.add("brand_id", "a");
    params.add("product_id", "35455");
    params.add("date", "2020-06-16T21:00:00");

    mockMvc
        .perform(get("/price")
            .params(params))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.data").isEmpty())
        .andExpect(jsonPath("$.error.code").value("typeMismatch"))
        .andExpect(jsonPath("$.error.error").value("MethodArgumentTypeMismatchException"))
        .andExpect(jsonPath("$.error.cause").value("Invalid argument type: brand_id should be Long"));
  }

  @Test
  public void givenRestCallToControllerAndNonExistentProduct_shouldReturnError() throws Exception {
    var params = new LinkedMultiValueMap<String, String>();
    params.add("brand_id", "1");
    params.add("product_id", "35456");
    params.add("date", "2020-06-16T21:00:00");

    mockMvc
        .perform(get("/price")
            .params(params))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.data").isEmpty())
        .andExpect(jsonPath("$.error.code").value("priceNotFound"))
        .andExpect(jsonPath("$.error.error").value("PriceNotFoundException"))
        .andExpect(jsonPath("$.error.cause").value("Incorrect parameters: Entity Not found"));
  }

}
