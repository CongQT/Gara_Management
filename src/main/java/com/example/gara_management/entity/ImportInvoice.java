package com.example.gara_management.entity;

import com.example.gara_management.enumtype.AppointmentStatus;
import com.example.gara_management.enumtype.ImportInvoiceStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "import_invoice")
@Entity
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class ImportInvoice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id", updatable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "note")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ImportInvoiceStatus status;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToMany()
    @NotAudited
    @JoinColumn(name = "import_invoice_id", referencedColumnName = "id")
    private List<ImportInvoiceAccessory> importInvoiceAccessories;
}
