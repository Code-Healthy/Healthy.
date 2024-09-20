package com.healthy.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthy.model.enums.GoalStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_goal_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "habit_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_goal_habit"))
    private Habit habit;

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_goal_plan"))
    private Plan plan;

    @Column(name = "target_value")
    private Float targetValue;

    @Column(name = "current_value")
    private Float currentValue;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal_status")
    private GoalStatus goalStatus;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<TrackingRecord> trackingRecords;
}
