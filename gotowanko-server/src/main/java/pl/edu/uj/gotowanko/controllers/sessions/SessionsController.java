package pl.edu.uj.gotowanko.controllers.sessions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uj.gotowanko.entities.User;
import pl.edu.uj.gotowanko.repositories.UsersRepository;

import java.util.Calendar;

/**
 * Created by alanhawrot on 26.03.15.
 */
@RestController
@RequestMapping("/sessions")
public class SessionsController {

    @Autowired
    private UsersRepository usersRepository;

    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.POST)
    public void createSession() {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User authenticatedUser = usersRepository.findByEmail(authenticatedUserEmail);

        authenticatedUser.setLastLogged(Calendar.getInstance());

        usersRepository.save(authenticatedUser);
    }

    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteSession() {
        SecurityContextHolder.clearContext();
    }
}
