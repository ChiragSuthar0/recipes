package recipes.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "description")
    @NotBlank
    private String description;

    @ElementCollection
    @Column(name = "ingredient")
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "owner_id"))
    @Size(min = 1)
    private List<String> ingredients = new ArrayList<>();

    @ElementCollection
    @Column(name = "direction")
    @CollectionTable(name = "recipe_directions", joinColumns = @JoinColumn(name = "owner_id"))
    @Size(min = 1)
    private List<String> directions = new ArrayList<>();

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "category")
    @NotBlank
    private String category;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe(String name, String description, String category, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.ingredients = ingredients;
        this.directions = directions;
        this.date = LocalDateTime.now();
    }
}