package br.com.nucleos.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.nucleos.cursomc.services.DbService;
import br.com.nucleos.cursomc.services.EmailService;
import br.com.nucleos.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

   @Autowired
   private DbService dbService;

   @Bean
   public boolean instantiateDatabase() {
      dbService.instantiateTestDatabase();
      return true;
   }

   @Bean
   public EmailService emailService() {
      return new MockEmailService();
   }

}
