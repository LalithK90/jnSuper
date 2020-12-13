package j_n_super_pvt_ltd.asset.user_management.role.entity;

import j_n_super_pvt_ltd.asset.user_management.user.entity.User;
import j_n_super_pvt_ltd.util.audit.AuditEntity;
import j_n_super_pvt_ltd.asset.common_asset.model.enums.LiveOrDead;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AuditEntity {

    @NotNull
    @Column( unique = true )
    private String roleName;

    @Enumerated(EnumType.STRING)
    private LiveOrDead liveOrDead;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
