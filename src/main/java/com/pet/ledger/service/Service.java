package com.pet.ledger.service;


import com.pet.ledger.model.BaseModel;

public interface Service<T extends BaseModel> {
    boolean insert(T entity);
    boolean updateById(String id, T entity);
    boolean deleteById(String id);
    T getEntityById(String id);

}
