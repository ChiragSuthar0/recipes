package recipes.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @JsonProperty("email")
    @Column(name = "username")
    @NotBlank
    @Email
    @Pattern(regexp = ".*\\..*")
    private String username;

    @Column(name = "password")
    @NotBlank
    @Size(min = 8)
    private String password;

    @Column(name = "role")
    private String role = "ROLE_USER";

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Recipe> recipes = new ArrayList<>();

    public void addRecipes(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
