package recipes.businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.exceptions.BadRequestException;
import recipes.persistence.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder getEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder getEncoder) {
        this.userRepository = userRepository;
        this.getEncoder = getEncoder;
    }

    public void registerUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            throw new BadRequestException("already user registered");
        }
        user.setPassword(getEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
