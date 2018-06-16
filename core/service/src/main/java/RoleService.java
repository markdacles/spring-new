import java.util.List;
import java.util.Set;

public interface RoleService {
    
    public List<Roles> listRoles();

    public Roles findById(Long id, String object);

    public void addRole(Roles r);

    public void updateRole(Roles r);

    public void deleteRole(Long id, String object);

}