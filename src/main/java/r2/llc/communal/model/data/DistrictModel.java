package r2.llc.communal.model.data;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class DistrictModel {

    private Long id;
    private Long region_id;
    private String titleUz;
    private String titleEn;
    private String titleRu;
    private Date createdAt;
    private Date updatedAt;
}
