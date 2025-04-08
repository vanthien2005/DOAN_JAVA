package DAO;

import java.util.ArrayList;

public interface DAOInterFace<T> {
void insert(T t);
void delete(T t);
void update(T t);
void selectAll(ArrayList<T> ds);
void selectCondition(ArrayList<T> ds,String condition);
}

