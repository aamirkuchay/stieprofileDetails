package com.cards.Cards;

import com.cards.Cards.entity.Role;
import com.cards.Cards.entity.User;
import com.cards.Cards.respository.RoleRepository;
import com.cards.Cards.respository.UserRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "STIE CARDS",
				description = "STIE Cards Application",
				version = "v1",
				contact = @Contact(name = "MR.stie cards", email = "K@gmail.com",url = "https://stiecards.com")

		),
		externalDocs = @ExternalDocumentation(
				description = "STIE CARDS Api Documents",
				url = "https://stie_cards.com"
		)
)
public class CardsApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<Role> adminRoleOpt = roleRepository.findByName("ROLE_ADMIN");
		if (adminRoleOpt.isPresent()) {
			Role adminRole = adminRoleOpt.get();
			Optional<User> adminAccountOpt = userRepository.findByRole(adminRole);
			if (adminAccountOpt.isEmpty()) {
				User user = new User();
				user.setName("CARDS");
				user.setPhoneNumber("1234567543");
				user.setAddress("Srinager");
				user.setUsername("admin");
				user.setPassword(new BCryptPasswordEncoder().encode("admin"));
				user.setRole(adminRole);
				userRepository.save(user);
			}
		} else {
			throw new RuntimeException("ROLE_ADMIN not found in the database.");
		}
	}

}
