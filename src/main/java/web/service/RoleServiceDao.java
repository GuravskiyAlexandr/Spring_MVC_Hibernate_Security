package web.service;

import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleServiceDao {
    Set<Role> getRoles(List<String> roleNames);
}
