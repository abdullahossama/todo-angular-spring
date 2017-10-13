package com.dev.ToDo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dev.ToDo.dao.TodoItemRepository;
import com.dev.ToDo.model.TodoItem;

@SpringBootApplication
public class ToDoApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ToDoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ToDoApplication.class, args);
	}
	
	/*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }*/
	
	@Bean
	public CommandLineRunner demo(TodoItemRepository repository) {
		return (args) -> {
			// save a couple of todoItems
			repository.save(new TodoItem("shop", false));
			repository.save(new TodoItem("read", true));
			repository.save(new TodoItem("eat", true));
			repository.save(new TodoItem("sleep", false));
			repository.save(new TodoItem("swim", false));

			// fetch all todoItems
			log.info("Todo Items found with findAll():");
			log.info("-------------------------------");
			for (TodoItem item : repository.findAll()) {
				log.info(item.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			TodoItem item = repository.findOne(1L);
			log.info("Item found with findOne(1L):");
			log.info("--------------------------------");
			log.info(item.toString());
			log.info("");

			// fetch Items by last name
			log.info("item found with findByTitle('read'):");
			log.info("--------------------------------------------");
			for (TodoItem read : repository.findByTitle("read")) {
				log.info(read.toString());
			}
			log.info("");
		};
	}
}
