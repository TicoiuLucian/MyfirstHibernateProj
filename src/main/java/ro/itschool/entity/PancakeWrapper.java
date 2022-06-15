package ro.itschool.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PancakeWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String colour;

    private int price;

    @ToString.Exclude
    @OneToOne(mappedBy = "pancakeWrapper", cascade = CascadeType.ALL)
    private Pancake pancake;


}