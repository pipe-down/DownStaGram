package downstagram.downstagram.service;

import downstagram.downstagram.domain.User;
import downstagram.downstagram.model.UserDto;
import downstagram.downstagram.model.UserRegistrationModel;
import downstagram.downstagram.repository.UserRepository;
import downstagram.downstagram.utils.EncryptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public User login(String userId, String password) {
        User user = userRepository.findUserByUserId(userId);

        if (user == null) return null;

        String pw = EncryptionUtils.encryptMD5(password);

        if (!user.getPassword().equals(pw)) return null;

        return user;
    }

    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new UserDto(user.get().getUserId());
        }
        return null;
    }

    public User findByUserId(String userId) {
        User user = userRepository.findUserByUserId(userId);
        return user;
    }

    @Transactional
    public void save(UserRegistrationModel userModel) {
        validateDuplicateMember(userModel);
        User user = User.createUser(userModel.getUserid(), userModel.getPasswd1(), userModel.getName(), null, userModel.getPhone(), null);

        userRepository.save(user);
    }

    @Transactional
    public void profile_update(String userId, String name, String website, String introduce) {
        User user = findByUserId(userId);
        user.updateUser(name, website, introduce);
        userRepository.save(user);
    }

    @Transactional
    public void profile_image(String userId, String profileImg) {
        User user = findByUserId(userId);
        user.setProfileImg(profileImg);
        userRepository.save(user);
    }

    private void validateDuplicateMember(UserRegistrationModel userModel) {
        List<User> user = userRepository.findByUserId(userModel.getUserid());
        if (user.size()>0) { // 유저 중복 체크
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
