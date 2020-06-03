package lk.J_N_Super_Pvt_Ltd.asset.message.entity;


import com.sun.istack.NotNull;
import lk.J_N_Super_Pvt_Ltd.asset.employee.entity.Employee;
import lk.J_N_Super_Pvt_Ltd.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage extends AuditEntity {

    @Column( nullable = false, columnDefinition = "VARCHAR(20000) CHARACTER SET utf8 COLLATE utf8_bin NULL", length =
            20000 )
    @NotNull
    private String message;

    @Column( nullable = false, columnDefinition = "VARCHAR(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL", length =
            1000 )
    @NotNull
    private String subject;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "email_message_employee",
            joinColumns = @JoinColumn(name = "email_message_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;


}
