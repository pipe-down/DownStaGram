package downstagram.downstagram.domain;

import downstagram.downstagram.utils.EncryptionUtils;
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

    @Enumerated(EnumType.STRING)
    private IndiUserType userType; // GUEST, USER, ADMIN
    private int enable;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public static User createUser(String userId, String password, String name, String introduce, String phone, String website) {
        return new User(userId, EncryptionUtils.encryptMD5(password), name, introduce, phone, website, IndiUserType.USER, 1);
    }

    public void updateUser(String name, String website, String introduce) {
        this.name = name;
        this.website = website;
        this.introduce = introduce;
    }

    public User(String userId, String password, String name, String introduce, String phone, String website, IndiUserType userType, int enable) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.introduce = introduce;
        this.phone = phone;
        this.website = website;
        this.userType = userType;
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", userType=" + userType +
                ", enable=" + enable +
                '}';
    }
}

