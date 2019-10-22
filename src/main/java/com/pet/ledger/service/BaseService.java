package com.pet.ledger.service;

import com.pet.ledger.model.BaseModel;
import com.pet.ledger.repository.ModelRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@org.springframework.stereotype.Service
public abstract class BaseService<T extends BaseModel> implements Service<T> {

    private ModelRepository<T> modelRepository;

    @Override
    public boolean insert(T entity) {
        System.out.println(entity.getClass().getName());
        modelRepository.save(entity);
        return true;
    }

    @Override
    public boolean updateById(String id, T entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        modelRepository.deleteById(id);
        return true;
    }

    @Override
    public T getEntityById(String id) {
        return modelRepository.findById(id).orElse(null);
    }
}
