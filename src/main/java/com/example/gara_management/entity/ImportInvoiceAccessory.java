package com.example.gara_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDateTime;

@Table(name = "import_invoice_accessory")
@Entity
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class ImportInvoiceAccessory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "import_invoice_id")
    private Integer importInvoiceId;

    @Column(name = "accessory_id")
    private Integer accessoryId;

    @Column(name = "import_price")
    private Double import_price;

    @Column(name = "quantity")
    private Integer quantity;
}
