package r2.llc.communal.model.data;

import lombok.Data;

import java.util.Date;


@Data
public class UserModel {
    private Long id;
    private String name;
    private String photo;
    private String role;
    private String token;
    private String password;
    private Date createdAt;
    private Date updatedAt;
}
