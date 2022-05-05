package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {

        List<User> list = entityManager.createQuery("SELECT u FROM User u").getResultList();
        return list;
    }

    @Override
    public void add(User user) {

        entityManager.persist(user);
    }

    @Override
    public void remove(Long id) {

        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void update(Long id, User user) {

        User updatedUser = entityManager.find(User.class, id);

        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());

        entityManager.merge(updatedUser);
    }

    public User show(Long id) {

        return entityManager.find(User.class, id);
    }
}
