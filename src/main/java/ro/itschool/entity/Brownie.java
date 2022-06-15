package ro.itschool.entity;


import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Brownie extends Dessert {

    private boolean milk;

    private int price;
}
