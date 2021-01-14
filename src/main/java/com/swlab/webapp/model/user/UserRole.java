package com.swlab.webapp.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swlab.webapp.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
@Table(name = "user_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_name"})})
@DynamicUpdate
public class UserRole extends BaseEntity implements GrantedAuthority {


    private static final long serialVersionUID = 7712340584126173791L;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_ROLE_USER"))
    private User user;

    @Column(name = "role_name", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private RoleType roleName;

    @Override
    public Object getSimple() {
        return roleName.name();
    }

    public enum RoleType {
        ROLE_ADMIN, ROLE_VIEW
    }

    @Builder
    public UserRole(User user, RoleType roleName) {
        this.user = user;
        this.roleName = roleName;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return this.roleName.name();
    }


}
