package r2.llc.communal.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Communal {

    private Long id;
    private String name;
    private String photo;
    private Long max;
    private Long min;
    private Date createdAt;
    private Date updatedAt;
}
