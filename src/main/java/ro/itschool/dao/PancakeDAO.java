package ro.itschool.dao;

import ro.itschool.entity.Pancake;

import java.util.List;

public interface PancakeDAO {


    //Create
    void createPancake(Pancake pancake);

    Pancake createPancakeAndReturnIt(Pancake pancake);


    //Read
    List<Pancake> getAllPancakes();

    Pancake getPancakeById(int id) throws Exception;


    //Update
    Pancake updatePancakeById(Pancake pancake);


    //Delete
    void deletePancake(int id);

}
