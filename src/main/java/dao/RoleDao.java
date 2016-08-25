package dao;

import entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by swatch on 8/21/16.
 */
@Repository("role")
@Transactional
public class RoleDao extends AbstractDao<Integer, Role> {
    public Role getRoleById(Integer id){
        return getByKey(id);
    }
}
