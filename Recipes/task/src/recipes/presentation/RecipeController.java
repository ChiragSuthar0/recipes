package recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import recipes.businesslayer.Recipe;
import recipes.businesslayer.RecipeService;
import recipes.businesslayer.User;
import recipes.persistence.UserRepository;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    final RecipeService recipeService;

    @Autowired
    UserRepository userRepository;

    public RecipeController(@Autowired RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/new")
    public ResponseEntity<Map<String, Object>> storeRecipe(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody Recipe recipe) {
        Long id = recipeService.saveRecipe(userDetails, recipe);
        return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        Optional<Recipe> optionalRecipe = recipeService.findRecipeById(id);
        return optionalRecipe.map(recipe -> new ResponseEntity<>(recipe, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRecipe(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        recipeService.deleteRecipeById(userDetails, id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateRecipe(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @Valid @RequestBody Recipe recipe) {
        recipeService.updateRecipe(userDetails, id, recipe);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/")
    public ResponseEntity<List<Recipe>> searchRecipe(@RequestParam Optional<String> category, @RequestParam Optional<String> name) {
        if (category.isEmpty() && name.isEmpty() || category.isPresent() && name.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        List<Recipe> recipeList = new ArrayList<>();
        try {
            if (name.isPresent()) {
                recipeList = recipeService.findRecipeByName(name.get());
            }
            if (category.isPresent()) {
                recipeList = recipeService.findRecipeByCategory(category.get());
            }
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

}
