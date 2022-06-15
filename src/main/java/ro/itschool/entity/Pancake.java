package ro.itschool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pancake extends Dessert {

    private String flavour;

    private Integer weight;

    private Integer price;

    @OneToOne(cascade = CascadeType.ALL)
    private PancakeWrapper pancakeWrapper;

    @OneToMany(mappedBy = "pancake", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pancake_topping",
            joinColumns = @JoinColumn(name = "pancake_id"),
            inverseJoinColumns = @JoinColumn(name = "topping_id"))
    private Set<Topping> toppings = new HashSet<>();

    public void addIngredientToPancake(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setPancake(this);
    }

    public void addToppingToPancake(Topping topping) {
        toppings.add(topping);
        topping.addPancakeToTopping(this);
    }

}
