package com.foxminded.university.dao;

import java.util.List;

public interface CrudDao<T> {
    public T getById(int id);

    public List<T> getAll();

    public boolean add(T t);

    public boolean delete(int id);

    public boolean update(T t);

}
