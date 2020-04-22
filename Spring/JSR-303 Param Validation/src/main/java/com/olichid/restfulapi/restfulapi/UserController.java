package com.olichid.restfulapi.restfulapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Api(tags = "User Management")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    // Create thread safe Map, simulate storing users information
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    /**
     * Process "/users/" GET request, acquire users list
     *
     * @return
     */
    @GetMapping("/")
    @ApiOperation(value = "Acquire user list")
    public List<User> getUserList() {
        List<User> userList = new ArrayList<User>(users.values());

        return userList;
    }

    /**
     * "/users/" POST request, create a user
     *
     * @param user
     * @return
     */
    @PostMapping("/")
    @ApiOperation(value = "Create user", notes = "Create user with user object")
    public String postUser(@Valid @RequestBody User user) {
        // Annotation @RequestBody used to bind uploaded application/json data in http request
        users.put(user.getId(), user);
        return "success";
    }

    /**
     * /users/ GET request, acquire the user that id in the url
     *
     * @param id
     * @return user
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "Acquire user information", notes = "Acquire user information with id in url")
    public User getUser(@PathVariable long id) {
        // id in url could bind as param in method via @PathVariable
        return users.get(id);
    }

    /**
     * /users/{id} PUT request, update user information
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "User ID", required = true, example = "1")
    @ApiOperation(value = "Update user information", notes = "Get user need to be updated from id in url，and update the information")
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User updateUser = users.get(id);
        updateUser.setName(user.getName());
        updateUser.setAge(user.getAge());
        users.put(id, updateUser);
        return "success";
    }

    /**
     * /user/{id} DELETE request, delete user
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user", notes = "Delete user with id in url")
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

}
