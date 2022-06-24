package cinema.app.config;

import cinema.app.model.Role;
import cinema.app.model.User;
import cinema.app.service.RoleService;
import cinema.app.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("admin@google.com");
        admin.setPassword("admin2002");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);
        User user = new User();
        user.setEmail("vova@google.com");
        user.setPassword("vova1234");
        user.setRoles(Set.of(adminRole));
        userService.add(user);
    }
}
