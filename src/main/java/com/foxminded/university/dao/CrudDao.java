package com.foxminded.university.dao;

import java.util.List;

public interface CrudDao<T> {
    public T getById(int id);

    public List<T> getAll();

    public T add(T t);

    public boolean delete(int id);

    public T update(T t);

}
