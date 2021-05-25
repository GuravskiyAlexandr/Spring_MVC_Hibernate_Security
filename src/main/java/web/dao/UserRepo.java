package web.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class UserRepo implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void userEdit(User user) {
        em.merge(user);
    }

    @Override
    public void userDelete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    public User findUserById(Long id) {
        return em.find(User.class, id);
    }
}
