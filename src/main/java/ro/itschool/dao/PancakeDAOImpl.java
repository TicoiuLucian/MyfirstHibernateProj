package ro.itschool.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.itschool.entity.Pancake;
import ro.itschool.util.HibernateUtil;

import java.util.List;


public class PancakeDAOImpl implements PancakeDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PancakeDAOImpl.class);
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;
    private Transaction transaction;


    @Override
    public void createPancake(Pancake pancake) {
        LOGGER.info("Creating pancake: {}", pancake);
        openSessionAndTransaction();
        session.save(pancake);
        closeSessionAndTransaction();
        LOGGER.info("Pancake {} was created", pancake);
    }

    @Override
    public Pancake createPancakeAndReturnIt(Pancake pancake) {
        LOGGER.info("Creating pancake: {}", pancake);
        openSessionAndTransaction();
        int id = (int) session.save(pancake);
        closeSessionAndTransaction();
        pancake.setId(id);
        LOGGER.info("Pancake {} was created", pancake);
        return pancake;
    }

    @Override
    public List<Pancake> getAllPancakes() {
        LOGGER.info("Getting all pancakes: ");
        String hql = "from pancake";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        List<Pancake> pancakes = query.list();
        return pancakes;
    }

    @Override
    public Pancake getPancakeById(int id) throws Exception {
        LOGGER.info("Getting pancakes with id: {}", id);
        session = sessionFactory.openSession();
        Pancake p = session.get(Pancake.class, id);
        session.close();
        if (p == null) {
            throw new Exception("Pancake id not found");
        }
        LOGGER.info("Found pancake: {}", p);
        return p;
    }

    @Override
    public Pancake updatePancakeById(Pancake pancake) {
        LOGGER.info("Updating pancake: {}", pancake);
        openSessionAndTransaction();
        session.update(pancake);
        closeSessionAndTransaction();
        return pancake;
    }

    @Override
    public void deletePancake(int id) {
        LOGGER.info("Deleting pancake with id: {}", id);
        try {
            final Pancake pancakeById = getPancakeById(id);
            openSessionAndTransaction();
//            for (Topping t : pancakeById.getToppings()) {
//                    if (t.getPancakes().size()==1) {
//                        t.setPancakes(null);
//                        toppingDAO.deleteTopping(t.getId());
//                    }}
            session.delete(pancakeById);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Delete: Id not found! ");
        }
        closeSessionAndTransaction();
    }

    private void openSessionAndTransaction() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    private void closeSessionAndTransaction() {
        transaction.commit();
        session.close();
    }
}
