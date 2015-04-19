package pl.edu.uj.gotowanko.entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by alanhawrot on 18.03.15.
 */
@Entity(name = "Recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @ColumnDefault(value = "'/images/recipe/noImage.png'")
    private String photoUrl;

    @Column
    private Integer cookingTimeInMinutes;

    @Column
    private Integer approximateCost;

    @Column
    private Integer numberOfLikes;

    @Column
    @Temporal(value = TemporalType.DATE)
    private Calendar dateAdded;

    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar lastEdited;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Collection<RecipeStep> recipeSteps = new ArrayList<>();

    @ManyToOne(optional = false)
    private User user;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Collection<Comment> comments = new HashSet<>();

    @ManyToMany(targetEntity = User.class, mappedBy = "recipeLikes")
    private Collection<User> userLikes = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getCookingTimeInMinutes() {
        return cookingTimeInMinutes;
    }

    public void setCookingTimeInMinutes(int cookingTimeInMinutes) {
        this.cookingTimeInMinutes = cookingTimeInMinutes;
    }

    public int getApproximateCost() {
        return approximateCost;
    }

    public void setApproximateCost(int approximateCost) {
        this.approximateCost = approximateCost;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Calendar getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Calendar lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Collection<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    private void setRecipeSteps(Collection<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    private void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public void addRecipeStep(RecipeStep recipeStep) {
        recipeSteps.add(recipeStep);
    }

    public Collection<User> getUserLikes() {
        return userLikes;
    }

    private void setUserLikes(Collection<User> userLikes) {
        this.userLikes = userLikes;
    }

    public int getNumberOfLikes() {
        return getUserLikes().size();
    }

    private void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public void addUserLike(User user) {
        getUserLikes().add(user);
    }

    public void removeUserLike(User user) {
        getUserLikes().remove(user);
    }

    public boolean containsUserLike(User user) {
        return getUserLikes().contains(user);
    }
}