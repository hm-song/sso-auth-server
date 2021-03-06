package hm.song.practice.web;

import hm.song.practice.entity.User;
import hm.song.practice.service.authentication.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private CustomUserDetailsService userService;

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Page<User> getAllUsers(int page, int size) {
        return userService.getUsers(page, size);
    }
}
