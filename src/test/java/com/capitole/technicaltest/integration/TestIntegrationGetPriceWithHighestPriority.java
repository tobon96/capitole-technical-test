package com.capitole.technicaltest.integration;

import com.capitole.technicaltest.integration.configuration.MigrationConfiguration;
import org.junit.jupiter.api.Tag;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(MigrationConfiguration.class)
class TestIntegrationGetPriceWithHighestPriority {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void givenRestCallToControllerAndWrongBrandIdParameter_shouldReturnError() throws Exception {
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
  void givenRestCallToControllerAndNonExistentProduct_shouldReturnError() throws Exception {
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
        .andExpect(jsonPath("$.error.code").value("resourceNotAvailable"))
        .andExpect(jsonPath("$.error.error").value("PriceNotAvailableException"))
        .andExpect(jsonPath("$.error.cause").value("Incorrect parameters: Entity not available"));
  }

  @Test
  @Tag("First Scenario: 10:00 of day 14 for product 35455 and brand 1 (ZARA)")
  void givenRestCallToControllerForFistRequestedScenario_shouldReturnPriceThatApplies() throws Exception {
    var params = new LinkedMultiValueMap<String, String>();
    params.add("brand_id", "1");
    params.add("product_id", "35455");
    params.add("date", "2020-06-14T10:00:00");

    mockMvc
        .perform(get("/price")
            .params(params))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.productId").value(35455))
        .andExpect(jsonPath("$.data.brandId").value(1))
        .andExpect(jsonPath("$.data.priceList").value(1))
        .andExpect(jsonPath("$.data.startDate").value("2020-06-14T00:00:00"))
        .andExpect(jsonPath("$.data.endDate").value("2020-12-31T23:59:59"))
        .andExpect(jsonPath("$.data.amount").value(35.5))
        .andExpect(jsonPath("$.error").isEmpty());
  }

  @Test
  @Tag("First Scenario: 16:00 of day 14 for product 35455 and brand 1 (ZARA)")
  void givenRestCallToControllerForSecondRequestedScenario_shouldReturnPriceThatApplies() throws Exception {
    var params = new LinkedMultiValueMap<String, String>();
    params.add("brand_id", "1");
    params.add("product_id", "35455");
    params.add("date", "2020-06-14T16:00:00");

    mockMvc
        .perform(get("/price")
            .params(params))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.productId").value(35455))
        .andExpect(jsonPath("$.data.brandId").value(1))
        .andExpect(jsonPath("$.data.priceList").value(2))
        .andExpect(jsonPath("$.data.startDate").value("2020-06-14T15:00:00"))
        .andExpect(jsonPath("$.data.endDate").value("2020-06-14T18:30:00"))
        .andExpect(jsonPath("$.data.amount").value(25.45))
        .andExpect(jsonPath("$.error").isEmpty());
  }

  @Test
  @Tag("First Scenario: 21:00 of day 14 for product 35455 and brand 1 (ZARA)")
  void givenRestCallToControllerForThirdRequestedScenario_shouldReturnPriceThatApplies() throws Exception {
    var params = new LinkedMultiValueMap<String, String>();
    params.add("brand_id", "1");
    params.add("product_id", "35455");
    params.add("date", "2020-06-14T21:00:00");

    mockMvc
        .perform(get("/price")
            .params(params))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.productId").value(35455))
        .andExpect(jsonPath("$.data.brandId").value(1))
        .andExpect(jsonPath("$.data.priceList").value(1))
        .andExpect(jsonPath("$.data.startDate").value("2020-06-14T00:00:00"))
        .andExpect(jsonPath("$.data.endDate").value("2020-12-31T23:59:59"))
        .andExpect(jsonPath("$.data.amount").value(35.5))
        .andExpect(jsonPath("$.error").isEmpty());
  }

  @Test
  @Tag("First Scenario: 10:00 of day 15 for product 35455 and brand 1 (ZARA)")
  void givenRestCallToControllerForFourthRequestedScenario_shouldReturnPriceThatApplies() throws Exception {
    var params = new LinkedMultiValueMap<String, String>();
    params.add("brand_id", "1");
    params.add("product_id", "35455");
    params.add("date", "2020-06-15T10:00:00");

    mockMvc
        .perform(get("/price")
            .params(params))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.productId").value(35455))
        .andExpect(jsonPath("$.data.brandId").value(1))
        .andExpect(jsonPath("$.data.priceList").value(3))
        .andExpect(jsonPath("$.data.startDate").value("2020-06-15T00:00:00"))
        .andExpect(jsonPath("$.data.endDate").value("2020-06-15T11:00:00"))
        .andExpect(jsonPath("$.data.amount").value(30.5))
        .andExpect(jsonPath("$.error").isEmpty());
  }

  @Test
  @Tag("First Scenario: 21:00 of day 16 for product 35455 and brand 1 (ZARA)")
  void givenRestCallToControllerForFifthRequestedScenario_shouldReturnPriceThatApplies() throws Exception {
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

}
