package r2.llc.communal.model.data;

import lombok.Builder;
import lombok.Data;
import r2.llc.communal.model.entity.DistrictEntity;

import java.util.Date;
import java.util.Set;


@Data
@Builder
public class RegionModel {

    private Long id;
    private String titleUz;
    private String titleEn;
    private String titleRu;
    private Set<DistrictEntity> districts;
    private Date createdAt;
    private Date updatedAt;
}
