package ru.progzona.annotron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.progzona.annotron.model.entity.StatusEntity;
import ru.progzona.annotron.model.entity.TaskAssignEntity;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TaskAssignRepository extends JpaRepository<TaskAssignEntity, Long> {
    boolean existsByTaskId(Long taskId);
    long countByUser_IdAndStatus_NameIn(Long userId, Collection<StatusEntity.StatusName> statuses);
    boolean existsByUser_IdAndTask_Id(Long userId, Long taskId);
}

