package com.employeemanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "performance_reviews", indexes = {
    @Index(name = "idx_emp_id", columnList = "emp_id"),
    @Index(name = "idx_review_date", columnList = "review_date")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;
    
    @Column(name = "review_period_start", nullable = false)
    private LocalDate reviewPeriodStart;
    
    @Column(name = "review_period_end", nullable = false)
    private LocalDate reviewPeriodEnd;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_by", nullable = false)
    private Employee reviewedBy;
    
    @Column(name = "rating")
    private Integer rating;
    
    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;
    
    @Column(name = "strengths", columnDefinition = "TEXT")
    private String strengths;
    
    @Column(name = "areas_for_improvement", columnDefinition = "TEXT")
    private String areasForImprovement;
    
    @Column(name = "review_date", nullable = false)
    private LocalDate reviewDate;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
