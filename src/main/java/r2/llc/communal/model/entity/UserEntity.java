package r2.llc.communal.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_table")
public class UserEntity {

    @Transient
    private final String seqName = "user_table_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = seqName)
    @SequenceGenerator(name = seqName, sequenceName = seqName, allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "role")
    private String role;

    @Column(name = "token")
    private String token;

    @Column(name = "password")
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    public UserDetails toUserDetails() {
        return User
                .withUsername(name)
                .password(password)
                .roles(role.toUpperCase())
                .build();
    }
}
