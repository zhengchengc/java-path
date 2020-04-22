package com.olichid.restfulapi.restfulapi;

import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public String postUser(@RequestBody User user) {
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
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

}
