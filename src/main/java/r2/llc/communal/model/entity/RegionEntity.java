package r2.llc.communal.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Data
@Entity
@Table(name = "region_table")
public class RegionEntity {

    @Transient
    private final String seqName = "region_table_id_seq";

    @Transient
    @JsonIgnore
    private final String regionFkey = "region_fkey";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = seqName)
    @SequenceGenerator(name = seqName, sequenceName = seqName, allocationSize = 1)
    private Long id;

    @Column(name = "title_uz", nullable = false)
    private String titleUz;

    @Column(name = "title_en", nullable = false)
    private String titleEn;

    @Column(name = "title_ru", nullable = false)
    private String titleRu;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id", foreignKey = @ForeignKey(name = regionFkey))
    private Set<DistrictEntity> districts;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
}
