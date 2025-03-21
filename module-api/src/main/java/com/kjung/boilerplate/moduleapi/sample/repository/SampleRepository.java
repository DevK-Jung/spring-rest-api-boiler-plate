package com.kjung.boilerplate.moduleapi.sample.repository;

import com.kjung.boilerplate.moduleapi.sample.entity.SampleEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SampleRepository {

    private static final ConcurrentHashMap<Long, SampleEntity> sampleDb = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong(1);

    public SampleEntity save(SampleEntity entity) {
        if (entity.getId() == null) {
            entity.setId(sequence.getAndIncrement());
            entity.setRegDatetime(LocalDateTime.now());
            entity.setRegUserId(entity.getReqUserId());
        }
        entity.setUpdateDatetime(LocalDateTime.now());
        entity.setUpdaterId(entity.getReqUserId());

        sampleDb.put(entity.getId(), entity);

        return entity;
    }

    public Optional<SampleEntity> findById(Long id) {
        return Optional.ofNullable(sampleDb.get(id));
    }

    public List<SampleEntity> findAll() {
        return new ArrayList<>(sampleDb.values());
    }

    public void deleteById(Long id) {
        sampleDb.remove(id);
    }

    public void clear() {
        sampleDb.clear();
        sequence.set(1);
    }

}
