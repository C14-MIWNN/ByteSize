package nl.miwnn.se14.bytesize.model;

import jakarta.persistence.*;

/**
 * @author Yvonne.
 * A recipe can be made and read by the user.
 */

@Entity
public class Recipe {
    @Id @GeneratedValue
    private Long recipeId;

//    @NotBlank
    @Column(unique = true)
    private String title;

    private String description;

    private String imageUrl;

//    @ManyToOne
//    private User user;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
