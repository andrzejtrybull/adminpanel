package bjosek.resources;

import bjosek.data.model.User;
import bjosek.dto.UserJson;
import bjosek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.function.Function;
import java.util.function.Predicate;

@RestController
@RequestMapping(path = "/api/user")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(params = { "page", "pagesize" })
    public Page<UserJson> getAllUsers(@RequestParam Integer page, @RequestParam Integer pagesize) {
        return userService.getAllPaginated(page, pagesize).map(UserResource::toJson);
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
