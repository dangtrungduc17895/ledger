package com.pet.ledger.service;

import com.pet.ledger.model.BaseModel;
import com.pet.ledger.model.type.Session;
import com.pet.ledger.repository.ModelRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@NoArgsConstructor
@Transactional
public abstract class BaseService<T extends BaseModel> implements Service<T> {

    @Autowired
    protected ModelRepository<T> modelRepository;

    @Override
    public boolean insert(T entity) {
        modelRepository.save(entity);
        return true;
    }

    @Override
    public void updateById(String id, T entity) {}

    @Override
    public boolean deleteById(String id) {
        modelRepository.deleteById(id);
        return true;
    }

    @Override
    public T getEntityById(String id) {
        BaseModel session = modelRepository.findById(id).orElse(null);
        int b = 3;
        return modelRepository.findById(id).orElse(null);
    }
}
