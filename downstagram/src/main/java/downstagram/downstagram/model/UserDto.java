package downstagram.downstagram.model;

import lombok.Data;

@Data
public class UserDto {

    private String userId;

    public UserDto(String userId) {
        this.userId = userId;
    }
}
