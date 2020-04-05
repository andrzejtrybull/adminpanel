package bjosek.service;

import bjosek.data.model.User;
import bjosek.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUser(Long userid) {
        return userRepository.findById(userid);
    }

    public Page<User> getAllPaginated(Integer page, Integer pagesize) {
        return userRepository.findAll(PageRequest.of(page, pagesize));
    }
}
