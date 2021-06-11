package downstagram.downstagram.model;

import downstagram.downstagram.domain.User;
import lombok.Data;

@Data
public class UserDto {

    private String userId;

    public UserDto(String userId) {
        this.userId = userId;
    }

    public UserDto(User user) {
        this.userId = user.getUserId();
    }
}
