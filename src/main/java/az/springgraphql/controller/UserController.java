package az.springgraphql.controller;

import az.springgraphql.model.User;
import az.springgraphql.model.UserRequest;
import az.springgraphql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @QueryMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @QueryMapping
    public User getUserById(@Argument Integer id){
        return service.getUserById(id);
    }

    @MutationMapping
    public User createUser(@Argument UserRequest userRequest){
        return service.createUser(userRequest);
    }

    @MutationMapping
    public User updateUser(@Argument UserRequest userRequest){
        return service.updateUser(userRequest);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Integer id){
        service.deleteUser(id);
        return true;
    }
}
