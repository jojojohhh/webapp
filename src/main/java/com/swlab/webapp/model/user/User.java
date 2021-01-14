package com.swlab.webapp.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.swlab.webapp.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@DynamicInsert @DynamicUpdate
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1503466419962590641L;


    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 11, nullable = false)
    private String phoneNo;

    @Singular("userRoles")
    @JsonIgnoreProperties({"createTimestamp", "del"})
    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    @Where(clause = "del = false")
    private Set<UserRole> userRoles;

    @Builder
    public User(String email, String password, String name, String phoneNo) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNo = phoneNo;
    }
}
