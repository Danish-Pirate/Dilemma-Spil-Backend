package kea.dilemmaspilbackend.admin.repository;

import kea.dilemmaspilbackend.admin.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findAdminUserByUsername(String username);
}
