package downstagram.downstagram.model;

import downstagram.downstagram.domain.User;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String userId;
    private String name;
    private String phone;
    private String website;
    private String introduce;
    private String profileImg;

    public UserDto(String userId) {
        this.userId = userId;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.website = user.getWebsite();
        this.introduce = user.getIntroduce();
        this.profileImg = user.getProfileImg();
    }
}
