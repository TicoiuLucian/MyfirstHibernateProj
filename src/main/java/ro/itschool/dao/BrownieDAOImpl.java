package ro.itschool.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.itschool.entity.Brownie;
import ro.itschool.util.HibernateUtil;

import java.util.List;

public class BrownieDAOImpl implements BrownieDAO {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;
    private Transaction transaction;

    @Override
    public void createBrownie(Brownie brownie) {
        openSessionAndTransaction();
        session.save(brownie);
        closeSessionAndTransaction();
    }

    @Override
    public Brownie createBrownieAndReturnIt(Brownie brownie) {
        return null;
    }

    @Override
    public List<Brownie> getAllBrownie() {
        return null;
    }

    @Override
    public Brownie getBrownieById(int id) {
        session = sessionFactory.openSession();
        Brownie brownie = session.get(Brownie.class, id);
        session.close();
        return brownie;
    }

    @Override
    public Brownie updateBrownieById(Brownie brownie) {
        return null;
    }

    @Override
    public void deleteBrownie(int id) {

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
