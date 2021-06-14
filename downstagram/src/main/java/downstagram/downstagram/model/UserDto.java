package downstagram.downstagram.model;

import downstagram.downstagram.domain.User;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String userId;
    private String name;
    private String phone;

    public UserDto(String userId) {
        this.userId = userId;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.name = user.getName();
        this.phone = user.getPhone();
    }
}
