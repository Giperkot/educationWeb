package ru.orgunit.backend.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.orgunit.backend.dto.BaseDto;
import ru.orgunit.backend.dto.UserDto;
import ru.orgunit.backend.repositories.UserRepository;
import ru.orgunit.backend.services.UsersService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController()
public class UserCotroller {

    private final UsersService usersService;

    private final UserRepository userRepository;

    @Autowired
    public UserCotroller(UserRepository userRepository, UsersService usersService) {
        this.userRepository = userRepository;
        this.usersService = usersService;
    }


    @RequestMapping("/api/public/register")
    public BaseDto registerNewUser (@RequestBody UserDto newUser, HttpServletRequest request) {
        return usersService.registerNewUser(newUser, request);
    }

    @RequestMapping("/api/public/login")
    public BaseDto login (@RequestBody UserDto newUser, HttpServletRequest request) {
        return usersService.login(newUser, request);
    }

    @RequestMapping("/api/private/getProfile")
    public UserDto getProfile (HttpServletRequest request) {
        return usersService.getProfile(request);
    }
}
