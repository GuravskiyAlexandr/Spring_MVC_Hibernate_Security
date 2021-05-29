package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.model.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleServiceDao {
    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getRoles(List<String> roleNames) {
        return  new HashSet<>(roleDao.getRoles(roleNames));
    }
}
