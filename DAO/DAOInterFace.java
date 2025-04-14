package DAO;

import java.util.ArrayList;

public interface DAOInterFace<T> {
boolean insert(T t);
boolean delete(T t);
boolean update(T t);
ArrayList<T> selectAll();
ArrayList<T> selectCondition(String condition);
}

