package ru.progzona.annotron.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "status")
@AllArgsConstructor
@NoArgsConstructor
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private StatusName name;

    public enum StatusName {
        ASSIGNED,
        IN_PROGRESS,
        COMPLETED,
        REJECTED
    }
}