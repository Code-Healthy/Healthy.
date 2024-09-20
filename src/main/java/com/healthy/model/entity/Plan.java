package com.healthy.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthy.model.enums.PlanStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_plan_user"))
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_status", nullable = false)
    private PlanStatus planStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Goal> goals;
}
