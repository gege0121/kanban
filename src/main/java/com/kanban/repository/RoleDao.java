package com.kanban.repository;

import com.kanban.model.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> get();
    public Role getById(long id);
    public Role save(Role role);
    public int deleteById(long id);
    public int update(Role role);
}
