package com.sparklecow.curso;

import com.sparklecow.curso.entities.user.Role;
import com.sparklecow.curso.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class CursoApplication {
	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			RoleRepository roleRepository = ctx.getBean(RoleRepository.class);
			Optional<Role> role = roleRepository.findByName("USER");
			if(role.isEmpty()){
				roleRepository.save(Role.builder()
						.name("USER")
						.createdDate(LocalDateTime.now())
						.build());
			}
		};
	}
}
