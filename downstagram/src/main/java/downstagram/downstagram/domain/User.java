package downstagram.downstagram.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import downstagram.downstagram.utils.EncryptionUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private String profileImg;

    @Enumerated(EnumType.STRING)
    private IndiUserType userType; // GUEST, USER, ADMIN

    @Enumerated(EnumType.STRING)
    private TableStatus enable;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "following", cascade = CascadeType.REMOVE)
    private List<Follow> followings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "follower", cascade = CascadeType.REMOVE)
    private List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public static User createUser(String userId, String password, String name, String introduce, String phone, String website) {
        return new User(userId, EncryptionUtils.encryptMD5(password), name, introduce, phone, website, IndiUserType.USER, TableStatus.N);
    }

    public void enableUser(int enableValue) {
        if (enableValue == 0) {
            this.enable = TableStatus.N;
        } else {
            this.enable = TableStatus.Y;
        }
    }

    public void updateUser(String name, String website, String introduce) {
        this.name = name;
        this.website = website;
        this.introduce = introduce;
    }

    public User(String userId, String password, String name, String introduce, String phone, String website, IndiUserType userType, TableStatus enable) {
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

