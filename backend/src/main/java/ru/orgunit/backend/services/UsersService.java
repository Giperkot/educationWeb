package ru.orgunit.backend.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orgunit.backend.dto.BaseDto;
import ru.orgunit.backend.dto.UserDto;
import ru.orgunit.backend.entities.EntityStatus;
import ru.orgunit.backend.entities.UsersEntity;
import ru.orgunit.backend.exceptions.SirErrorStatus;
import ru.orgunit.backend.exceptions.SirException;
import ru.orgunit.backend.repositories.SpringSessionRepository;
import ru.orgunit.backend.repositories.UserRepository;
import ru.orgunit.backend.repositories.UserTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UsersService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class.getName());

    private final UserRepository userRepository;

    private final UserTokenRepository userTokenRepository;

    private final SpringSessionRepository springSessionRepository;

    private static final String NO_USER_EXISTS = "NO_USER_EXISTS";
    private static final String USER_LOGIN_FIELD = "login";
    private static final String USER_ID_FIELD = "userId";
    private static final String AUTENTIFICATION = "autentification";

    private static final String COOCIE_SESSION_NAME = "SESSION";
    private static final int ONE_YEAR = 31536000;

    @Autowired
    public UsersService(SpringSessionRepository springSessionRepository, UserRepository userRepository, UserTokenRepository userTokenRepository) {
        this.springSessionRepository = springSessionRepository;
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
    }


    public BaseDto registerNewUser (@NotNull UserDto newUser, HttpServletRequest req) {
        String login = clearPhone(newUser.getLogin());
        String password = newUser.getPassword().trim();

        if (login.length() < 10) {
            throw new SirException(SirErrorStatus.VALIDATION_ERROR, "Ошибка при заполнении телефона");
        }

        // Проверить нет ли такого.
        UsersEntity oldUser = userRepository.findByLoginAndStatus(login, EntityStatus.ACTIVE);

        if (oldUser != null) {
            throw new SirException("Пользователь с таким телефоном уже зарегистрирован");
        }

        UsersEntity usersEntity = new UsersEntity(login);

        generatePassword (usersEntity, password);

        // Добавить в базу.
        UsersEntity user = userRepository.save(usersEntity);
        req.getSession().setAttribute(USER_LOGIN_FIELD, login);
        req.getSession().setAttribute(USER_ID_FIELD, user.getId());

        // Send confirm code
        /*String telephoneCode = generateTelephoneCode();
        userTokenRepository.save(new UserTokenEntity(telephoneCode, UUID.fromString(req.getSession().getId())));
        smsService.sendSMS("Код подтверждения: " + telephoneCode, login);*/

        return new BaseDto();
    }

/*    public BaseDto checkConfirmCode (ConfirmCodeDto confirmCode, HttpServletRequest req, HttpServletResponse response) {
        UUID sessionId = UUID.fromString(req.getSession().getId());

        UserTokenEntity userTokenEntity = userTokenRepository.getBySessionIdAndStatus(sessionId, EntityStatus.ACTIVE);

        // Проверка на совпадение.
        if (userTokenEntity == null || !userTokenEntity.getTelephoneCode().equals(confirmCode.getConfirmCode().trim())) {
            throw new SirException(SirErrorStatus.VALIDATION_ERROR,"Код введён не верно");
        }

        req.getSession().setAttribute(AUTENTIFICATION, true);

        // Ставим сессию на 1 год.
        req.getSession().setMaxInactiveInterval(ONE_YEAR);

        SpringSessionEntity springSessionEntity = springSessionRepository.getFirstBySessionId(req.getSession().getId());
        springSessionEntity.setUserId(UUID.fromString(req.getSession().getAttribute(USER_ID_FIELD).toString()));
        springSessionRepository.save(springSessionEntity);

        Cookie[] httpSession = req.getCookies();
        if (httpSession != null)
        {
            for(int i=0; i<httpSession.length; i++)
            {
                Cookie cookie = httpSession[i];
                if (COOCIE_SESSION_NAME.equals(cookie.getName()))
                {
                    cookie.setMaxAge(ONE_YEAR);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }

        *//**
         * todo Нужно решить проблему с удалением из user_token
         *//*

        return new BaseDto();
    }*/

    public BaseDto login (UserDto newUser, HttpServletRequest req) {
        String login = clearPhone(newUser.getLogin());
        String password = newUser.getPassword().trim();

        if (login.length() < 10) {
            throw new SirException(SirErrorStatus.VALIDATION_ERROR, "Ошибка при заполнении телефона");
        }

        // Проверить нет ли такого.
        UsersEntity oldUser = userRepository.findByLoginAndStatus(login, EntityStatus.ACTIVE);

        if (oldUser == null) {
            throw new SirException(SirErrorStatus.WRONG_LOGIN_OR_PASSWORD);
        }

        boolean correctPass = checkPassword (oldUser, password);

        if (!correctPass) {
            throw new SirException(SirErrorStatus.WRONG_LOGIN_OR_PASSWORD);
        }

        req.getSession().setAttribute(USER_LOGIN_FIELD, oldUser.getLogin());
        req.getSession().setAttribute(USER_ID_FIELD, oldUser.getId());

        return new BaseDto();
    }

    public UserDto getProfile (HttpServletRequest req) {

        Boolean auth = (Boolean) req.getSession().getAttribute(AUTENTIFICATION);

        // можно смело убрать.
        if (auth == null || !auth) {
            throw new SirException("пользователь не авторизован");
        }

        UsersEntity user = userRepository.findByLoginAndStatus(req.getSession().getAttribute(USER_LOGIN_FIELD).toString(), EntityStatus.ACTIVE);
        return new UserDto(user);
    }



    public List<UsersEntity> findAllUsers() {
        List<UsersEntity> result = new ArrayList<>();
        Iterable<UsersEntity> all = userRepository.findAll();
        all.forEach(result::add);
        return result;
    }

    private String clearPhone(String phone) {
        return phone.replaceAll("[^0-9]", "");
    }

    private String generateTelephoneCode () {
        return ((Long)Math.round(Math.random() * 90000 + 10000)).toString();
    }


    private void generatePassword (UsersEntity usersEntity, String password) {
        String salt = BCrypt.gensalt(10);
        String hashpw = BCrypt.hashpw(password, salt);

        usersEntity.setSalt(salt);
        usersEntity.setPassword(hashpw);
    }

    private boolean checkPassword (UsersEntity usersEntity, String password) {

        String salt = usersEntity.getSalt();
        String hashpw = BCrypt.hashpw(password, salt);

        return hashpw.equals(usersEntity.getPassword());
    }
}
