package com.capitole.technicaltest.integration.configuration;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestConfiguration
public class MigrationConfiguration extends SpringExtension {

  @Bean
  public FlywayMigrationStrategy clean() {
    return flyway -> {
      flyway.clean();
      flyway.migrate();
    };
  }
}
