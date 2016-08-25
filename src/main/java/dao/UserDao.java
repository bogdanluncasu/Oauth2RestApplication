package dao;

import entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by swatch on 8/20/16.
 */
@Repository("user")
@Transactional
public class UserDao extends AbstractDao<Integer, User> {
    public void saveUser(User user){
        getSession().saveOrUpdate(user);
    }

    public User getUserById(Integer id) {
        return getByKey(id);
    }

    public User getUserByUsername(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        List<User> results = criteria.list();
        return (results.size()>0?results.get(0):null);
    }
}
