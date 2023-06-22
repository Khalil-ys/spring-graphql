package az.springgraphql.service;

import az.springgraphql.exception.UserNotFoundException;
import az.springgraphql.model.User;
import az.springgraphql.model.UserRequest;
import az.springgraphql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Integer id){
        return repository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found"));
    }

    public User createUser(UserRequest request){
        User user= User.builder()
                .name(request.getName())
                .mail(request.getMail())
                .role(request.getRole())
                .build();
        return repository.save(user);
    }

    public User updateUser (UserRequest request){
        User exists=getUserById(request.getId());
        exists.setMail(request.getMail());
        exists.setName(request.getName());
        exists.setRole(request.getRole());
        return repository.save(exists);
    }

    public void deleteUser(Integer id){
        User user=getUserById(id);
        repository.delete(user);
    }
}
