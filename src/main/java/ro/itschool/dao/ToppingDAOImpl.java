package ro.itschool.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.itschool.entity.Topping;
import ro.itschool.util.HibernateUtil;

import java.util.List;

public class ToppingDAOImpl implements ToppingDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(PancakeDAOImpl.class);

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;
    private Transaction transaction;


    private void openSessionAndTransaction() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    private void closeSessionAndTransaction() {
        transaction.commit();
        session.close();
    }

    @Override
    public void createTopping(Topping topping) {
        LOGGER.info("Creating topping: {}", topping);
        openSessionAndTransaction();
        session.save(topping);
        closeSessionAndTransaction();
        LOGGER.info("Topping {} was created", topping);

    }

    @Override
    public Topping createToppingAndReturnIt(Topping topping) {
        openSessionAndTransaction();
        int id = (int) session.save(topping);
        closeSessionAndTransaction();
        topping.setId(id);
        return topping;
    }

    @Override
    public List<Topping> getAllTopping() {
        return null;
    }

    @Override
    public Topping getToppingById(int id) {
       session = sessionFactory.openSession();

       Topping t = session.get(Topping.class, id);
       session.close();
        return t;
    }

    @Override
    public Topping updateToppingById(Topping topping) {
        return null;
    }

    @Override
    public void deleteTopping(int id) {
        openSessionAndTransaction();
        session.delete(getToppingById(id));
        closeSessionAndTransaction();

    }
}
