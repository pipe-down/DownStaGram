package downstagram.downstagram.service;

import downstagram.downstagram.domain.User;
import downstagram.downstagram.model.UserDto;
import downstagram.downstagram.model.UserRegistrationModel;
import downstagram.downstagram.repository.UserRepository;
import downstagram.downstagram.utils.EncryptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.annotation.PostConstruct;
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

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new UserDto(user.get().getUserId());
        }
        return null;
    }

    public boolean hasErrors(UserRegistrationModel userModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return true;

        User user = userRepository.findByUserId(userModel.getUserid());
        if (user != null) { // 유저 중복 체크
            bindingResult.rejectValue("userid", null, "사용자 아이디가 중복됩니다.");
            return true;
        }
        return false;
    }

    public void save(UserRegistrationModel userModel) {
        User user = User.createUser(userModel.getUserid(), userModel.getPasswd1(), userModel.getName(), null, userModel.getPhone(), null);
        userRepository.save(user);
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            userRepository.save(new User("user"+i, EncryptionUtils.encryptMD5("test"+i)));
        }
    }
}
