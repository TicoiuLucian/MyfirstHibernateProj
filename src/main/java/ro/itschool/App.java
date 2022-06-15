package ro.itschool;

import com.github.javafaker.Faker;
import ro.itschool.dao.*;
import ro.itschool.entity.*;
import ro.itschool.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDateTime;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static Faker faker = new Faker();

    public static void main(String[] args) {
        PancakeDAO pancakeDAO = new PancakeDAOImpl();
        BrownieDAO brownieDAO = new BrownieDAOImpl();

        pancakeDAO.createPancake(createPancake());
        pancakeDAO.createPancake(createPancake());
        pancakeDAO.createPancake(createPancake());


        pancakeDAO.deletePancake(27);

        brownieDAO.createBrownie(createBrownie());
        System.out.println(brownieDAO.getBrownieById(31));

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Dessert dessert = new Dessert();
        dessert.setName("desert generic");
        dessert.setBakedTime(LocalDateTime.now());
        session.save(dessert);
        transaction.commit();
        session.close();
        sessionFactory.close();

    }

    private static Pancake createPancake() {

        ToppingDAO toppingDAO = new ToppingDAOImpl();


        Pancake pancake = new Pancake();
        pancake.setFlavour(faker.food().fruit());
        pancake.setName(faker.funnyName().name());
//        pancake.set
        pancake.setPrice(faker.number().numberBetween(5, 50));
        pancake.setBakedTime(LocalDateTime.now().minusHours(faker.number().numberBetween(0, 2)));
        pancake.setWeight(faker.number().numberBetween(50, 200));


        PancakeWrapper pancakeWrapper = new PancakeWrapper();
        pancakeWrapper.setColour("Whhite");
        pancakeWrapper.setPrice(1);


        pancakeWrapper.setPancake(pancake);
        pancake.setPancakeWrapper(pancakeWrapper);
        pancake.addIngredientToPancake(new Ingredient(faker.food().ingredient()));
        pancake.addIngredientToPancake(new Ingredient(faker.food().ingredient()));

        pancake.addToppingToPancake(toppingDAO.createToppingAndReturnIt(new Topping(faker.food().fruit())));
        pancake.addToppingToPancake(toppingDAO.createToppingAndReturnIt(new Topping(faker.food().fruit())));
        pancake.addToppingToPancake(toppingDAO.createToppingAndReturnIt(new Topping(faker.food().fruit())));
        pancake.addToppingToPancake(toppingDAO.createToppingAndReturnIt(new Topping(faker.food().fruit())));
        pancake.addToppingToPancake(toppingDAO.createToppingAndReturnIt(new Topping(faker.food().fruit())));
        pancake.addToppingToPancake(toppingDAO.createToppingAndReturnIt(new Topping(faker.food().fruit())));

        return pancake;
    }

    private static Brownie createBrownie() {
        Brownie brownie = new Brownie();
        brownie.setName(faker.funnyName().name());
        brownie.setPrice(faker.number().numberBetween(5, 50));
        brownie.setBakedTime(LocalDateTime.now().minusHours(faker.number().numberBetween(0, 24)));

        brownie.setMilk(false);
        return brownie;
    }


//        Faker faker = new Faker();
//
//        Pancake pancake = new Pancake();
//        pancake.setFlavour(faker.food().fruit());
//        pancake.setName(faker.funnyName().name());
//        pancake.setPrice(faker.number().numberBetween(5, 50));
//        pancake.setWeight(faker.number().numberBetween(50, 200));
//
//
//        PancakeWrapper pancakeWrapper = new PancakeWrapper();
//        pancakeWrapper.setColour("Whhite");
//        pancakeWrapper.setPrice(1);
//
//        //------------------- one to one -------------------//
//
//        pancakeWrapper.setPancake(pancake);         //creare
//        pancake.setPancakeWrapper(pancakeWrapper);  //bidirectionalitate
//
//        PancakeDAO pancakeDAO = new PancakeDAOImpl();
//        //pancakeDAO.createPancake(pancake);
//
//
//        //------------------- one to many -------------------//
//
//        pancake.addIngredientToPancake(new Ingredient("Ingr. 1 pt clatita 1"));
//        pancake.addIngredientToPancake(new Ingredient("Ingr. 2 pt clatita 1"));
//        pancake.addIngredientToPancake(new Ingredient("Ingr. 3 pt clatita 1"));
//
//
//        //------------------- many to many -------------------//
//
//        Pancake pancake2 = new Pancake();
//        pancake2.setFlavour(faker.food().fruit());
//        pancake2.setName(faker.funnyName().name());
//        pancake2.setPrice(faker.number().numberBetween(5, 50));
//        pancake2.setWeight(faker.number().numberBetween(50, 200));
//
//
//        final Topping chocolate = new Topping("Chocolate");
//        final Topping vanilla = new Topping("Vanilla");
//        final Topping caramel = new Topping("Caramel");
//        final Topping strawberry = new Topping("Strawberry");
//
//
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        session.save(chocolate);
//        session.save(vanilla);
//        session.save(caramel);
//        session.save(strawberry);
//
//        transaction.commit();
//        session.close();
//
//        pancake.addToppingToPancake(chocolate);
//        pancake.addToppingToPancake(caramel);
//        pancake2.addToppingToPancake(vanilla);
//        pancake2.addToppingToPancake(strawberry);
//
//        pancakeDAO.createPancake(pancake);
//        pancakeDAO.createPancake(pancake2);
//
//


//    ~~ A doua varianta ~~
//             for (int i = 0; i < 10; i++)
//        pancakeDAO.createPancake(generatePancake());
//
//                LOGGER.info("{}", pancakeDAO.createPancakeAndReturnIt(generatePancake()));
//                LOGGER.info("{}", pancakeDAO.getAllPancakes());
//
//                try {
//                LOGGER.info("{}", pancakeDAO.getPancakeById(999));
//                }catch (Exception e){
//                LOGGER.error("GetById: Id not found");
//                }
//
//                pancakeDAO.deletePancake(999);
//
//    private static Pancake generatePancake() {
//        Faker faker = new Faker();
//
//        Pancake pancake = new Pancake();
//        pancake.setFlavour(faker.food().fruit());
//        pancake.setName(faker.funnyName().name());
//        pancake.setPrice(faker.number().numberBetween(5, 50));
//        pancake.setWeight(faker.number().numberBetween(50, 200));
//        return pancake;
//    }
//


//     ~~ Prima varianta ~~
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        Pancake pancake = new Pancake();
//        pancake.setFlavour("vanilie");
//        pancake.setName("Clatita Uriasa");
//        pancake.setPrice(15);
//        pancake.setWeight(200);
//
//        Pancake pancake2 = new Pancake();
//        pancake2.setFlavour("Ciocolata");
//        pancake2.setName("Clatita Minuscula");
//        pancake2.setPrice(25);
//        pancake2.setWeight(15);
//
//        Pancake pancake3 = new Pancake();
//        pancake3.setFlavour("Scortisoara");
//        pancake3.setName("Clatita normala");
//        pancake3.setPrice(25);
//        pancake3.setWeight(100);
//
//        Transaction transaction = session.beginTransaction();
//        session.save(pancake);
//        session.save(pancake2);
//        session.save(pancake3);
//        transaction.commit();
//
//        transaction = session.beginTransaction();
//        pancake.setName("Clatita Gigant");
//        session.update(pancake);
//        transaction.commit();
//
//        transaction = session.beginTransaction();
//        session.delete(pancake);
//        transaction.commit();
//
////        System.out.println(session.get(Pancake.class, 1));  //get pancake
//
//        session.close();
//        sessionFactory.close();


}