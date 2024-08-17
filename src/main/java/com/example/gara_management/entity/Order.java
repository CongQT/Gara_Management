package com.example.gara_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at is null")
@Entity
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id", updatable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private User staff;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToMany()
    @NotAudited
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderAccessory> orderAccessories;

    @OneToMany()
    @NotAudited
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderServices> orderServices;

}
