package com.swlab.webapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 7967710375248314678L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "INT(10)")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date creatTimeStamp;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0", length = 1)
    private boolean del;

    @PrePersist
    protected void onCreate() {
        creatTimeStamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public abstract Object getSimple();
}
