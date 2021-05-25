package r2.llc.communal.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Set;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Category {

    private Long id;
    private String name;
    private String photo;
    private Set<Communal> items;
    private Date createdAt;
    private Date updatedAt;
}
