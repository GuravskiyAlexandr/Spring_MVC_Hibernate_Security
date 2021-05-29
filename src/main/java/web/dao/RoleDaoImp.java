package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getRoles(List<String> roleNames) {
        return em.createQuery("select r from Role r where r.role in :roles", Role.class)
                .setParameter("roles", roleNames)
                .getResultList();
    }
}
