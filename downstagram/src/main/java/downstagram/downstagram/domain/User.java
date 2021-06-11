package downstagram.downstagram.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "indi_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indi_user_id")
    private Long id;

    @Column(length = 20,nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    private String name;
    private String introduce;
    private String phone;
    private String website;
    private String userType;
    private int enable;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public User(Long id, String userId, String password, String name, String introduce, String phone, String website, String userType, int enable) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.introduce = introduce;
        this.phone = phone;
        this.website = website;
        this.userType = userType;
        this.enable = enable;
    }
}

