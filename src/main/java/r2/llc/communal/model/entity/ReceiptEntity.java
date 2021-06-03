package r2.llc.communal.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "receipt_table")
public class ReceiptEntity {

    @Transient
    private final String seqName = "receipt_table_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = seqName)
    @SequenceGenerator(name = seqName, sequenceName = seqName, allocationSize = 1)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_expire")
    private String cardExpire;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "summa")
    private String summa;

    @Column(name = "communal_id")
    private Long communalId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
}
