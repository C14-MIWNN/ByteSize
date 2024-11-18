package nl.miwnn.se14.bytesize.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Yvonne.
 * A recipe can be made and read by the user.
 */

@Entity
public class Recipe {
    @Id @GeneratedValue
    private Long recipeId;

    @NotBlank
    private String recipeTitle;

    @Column(columnDefinition = "TEXT")
    private String recipeDescription;
    private String imageUrl;

    @ManyToOne
    private ByteSizeUser byteSizeUser;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
