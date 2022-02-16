package recipes.businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import recipes.exceptions.ForbiddenRequestException;
import recipes.exceptions.RecipeNotFoundException;
import recipes.persistence.RecipeRepository;
import recipes.persistence.UserRepository;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class RecipeService {
    private RecipeRepository repository;
    private UserRepository userRepository;

    @Autowired
    public RecipeService(RecipeRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public void deleteRecipeById(UserDetails userDetails, long id) {
        Optional<Recipe> optionalRecipe = repository.findById(id);
        if (optionalRecipe.isEmpty()) {
            throw new RecipeNotFoundException("no such recipe");
        }
        String username = userDetails.getUsername();
        if (!optionalRecipe.get().getUser().getUsername().equals(username)) {
            throw new ForbiddenRequestException("forbidden user to delete");
        }
        repository.deleteById(id);
    }

    public Optional<Recipe> findRecipeById(long id) {
        return repository.findById(id);
    }

    public Long saveRecipe(UserDetails userDetails, Recipe recipe) {
        String username = userDetails.getUsername();
        Recipe newRecipe = new Recipe(recipe.getName(), recipe.getDescription(), recipe.getCategory(), recipe.getIngredients(), recipe.getDirections());
        User user = userRepository.findUserByUsername(username);
        user.addRecipes(recipe);
        newRecipe.setUser(user);
        Recipe savedRecipe = repository.save(newRecipe);
        return savedRecipe.getId();
    }

    public void updateRecipe(UserDetails userDetails, long id, Recipe recipe) {
        Optional<Recipe> recipeOptional = repository.findById(id);
        if (recipeOptional.isEmpty()) {
            throw new RecipeNotFoundException("no such recipe");
        }

        String username = userDetails.getUsername();
        if (!recipeOptional.get().getUser().getUsername().equals(username)) {
            throw new ForbiddenRequestException("forbidden user to update recipe");
        }
        recipeOptional.ifPresent(recipe1 -> {
            recipe1.setDate(LocalDateTime.now());
            recipe1.setName(recipe.getName());
            recipe1.setDescription(recipe.getDescription());
            recipe1.setCategory(recipe.getCategory());
            recipe1.setDirections(recipe.getDirections());
            recipe1.setIngredients(recipe.getIngredients());
            repository.save(recipe1);
        });
    }

    public List<Recipe> findRecipeByName(@NotBlank String name) {
        return repository.findAllByNameIgnoreCaseContainingOrderByDateDesc(name);
    }

    public List<Recipe> findRecipeByCategory(@NotBlank String category) {
        return repository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }
}
