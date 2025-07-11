package br.com.jeb.config;

import br.com.jeb.domain.Acessorio;
import br.com.jeb.domain.Brand;
import br.com.jeb.domain.Car;
import br.com.jeb.repository.AcessorioRepository;
import br.com.jeb.repository.BrandRepository;
import br.com.jeb.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    CommandLineRunner initDatabase(BrandRepository brandRepository, CarRepository carRepository,
            AcessorioRepository acessorioRepository) {
        return args -> {
            // Create brand
            Brand toyota = new Brand();
            toyota.setName("Toyota");
            brandRepository.save(toyota);

            // Create accessories
            Acessorio arCondicionado = new Acessorio();
            arCondicionado.setNome("Ar Condicionado");
            arCondicionado.setDescricao("Ar condicionado digital automático");
            acessorioRepository.save(arCondicionado);

            Acessorio multimidia = new Acessorio();
            multimidia.setNome("Central Multimídia");
            multimidia.setDescricao("Central multimídia com tela touch 8 polegadas");
            acessorioRepository.save(multimidia);

            // Create car with accessories
            Car corolla = new Car();
            corolla.setModel("Corolla");
            corolla.setYear(2023);
            corolla.setBrand(toyota);
            corolla.getAcessorios().add(arCondicionado);
            corolla.getAcessorios().add(multimidia);
            carRepository.save(corolla);

            System.out.println("Database initialized successfully!");
        };
    }
}
