package com.development.app.BasketballService;

import com.development.app.BasketballService.Models.UserEntity;
import com.development.app.BasketballService.Models.UserPerformanceEntity;
import com.development.app.BasketballService.Repositories.UserPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasketballServiceApplication implements CommandLineRunner {

	@Autowired
	private UserPerformanceRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(BasketballServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		repository.deleteAll();
		// save a couple of customers
//		repository.save(new UserPerformanceEntity(0, 0));
//		repository.save(new UserPerformanceEntity(7, 8));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (UserPerformanceEntity userPerformanceEntity : repository.findAll()) {
			System.out.println(userPerformanceEntity);
		}
		System.out.println();
	}
}
