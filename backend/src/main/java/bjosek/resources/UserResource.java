package bjosek.resources;

import bjosek.dto.UserJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import bjosek.data.model.User;
import bjosek.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/api/user")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserJson> getAllUsers() {
        return userService.getAllUsers().stream().map(UserResource::toJson).collect(toList());
    }

    @RequestMapping(path = "/{userid}", method = RequestMethod.GET)
    public ResponseEntity<UserJson> getUser(@PathVariable("userid") Long userid) {
        UserJson userJson = toJson(userService.findUser(userid).orElseThrow(EntityNotFoundException::new));
        return new ResponseEntity(userJson, HttpStatus.OK);
    }

    public static UserJson toJson(User user) {
        return new UserJson(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname());
    }
}
