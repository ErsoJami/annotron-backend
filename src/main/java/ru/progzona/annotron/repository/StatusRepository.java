package ru.progzona.annotron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.progzona.annotron.model.entity.StatusEntity;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
    Optional<StatusEntity> findByName(StatusEntity.StatusName name);
}
