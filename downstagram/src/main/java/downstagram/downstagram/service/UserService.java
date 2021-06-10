package downstagram.downstagram.service;

import downstagram.downstagram.domain.User;
import downstagram.downstagram.repository.UserRepository;
import downstagram.downstagram.utils.EncryptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

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

    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            userRepository.save(new User("user"+i, EncryptionUtils.encryptMD5("test"+i)));
        }
    }
}
