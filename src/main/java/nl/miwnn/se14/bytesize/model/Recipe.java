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
    private String recipeTitle;

    private String recipeDescription;

    private String imageUrl;

//    @ManyToOne
//    private User user;

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
