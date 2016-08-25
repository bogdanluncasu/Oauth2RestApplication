package service;

import dao.RoleDao;
import dao.UserDao;
import entity.User;
import exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by swatch on 8/22/16.
 */
@org.springframework.stereotype.Service
public class UserService implements Service{
    private final int USERROLE=1;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public void addUser(User user) throws UserException {
        if(user.getUsername()==null||user.getUsername().contains(" ")||user.getUsername().isEmpty()
                ||user.getPassword()==null||user.getPassword().isEmpty()){
            throw  new UserException("Wrong User Format");
        }else if(userDao.getUserByUsername(user.getUsername())!=null){
            throw new UserException("Username is taken");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(roleDao.getRoleById(USERROLE));
        userDao.saveUser(user);
    }

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }
}
