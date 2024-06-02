package ru.devolw.tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.devolw.tacos.repository.IngredientRepository;
import ru.devolw.tacos.model.Ingredient;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) {
		return args -> {
			repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
			repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
			repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
			repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
			repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
			repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
			repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
			repo.save(new Ingredient("JACK", "Monterey Jack", Ingredient.Type.CHEESE));
			repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
			repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
		};
	}
}