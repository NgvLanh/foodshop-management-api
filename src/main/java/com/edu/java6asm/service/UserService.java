package com.edu.java6asm.service;

import com.edu.java6asm.enums.Roles;
import com.edu.java6asm.model.User;
import com.edu.java6asm.repository.UserRepository;
import com.edu.java6asm.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // TODO: Tạo mới một người dùng
    public User createUser(User user) {
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        var roles = new HashSet<String>();
        roles.add(Roles.USER.name());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    // TODO: Cập nhật thông tin người dùng
    public User updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setFullName(updatedUser.getFullName());
                    existingUser.setImage(updatedUser.getImage());
                    existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
                    // TODO: Cập nhật các trường khác nếu cần
                    return userRepository.save(existingUser);
                })
                .orElse(null); // TODO: Hoặc ném ngoại lệ tùy thuộc vào cách bạn muốn xử lý trường hợp không tìm thấy người dùng
    }

    // TODO: Xóa người dùng theo ID
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // TODO: Tìm người dùng theo ID
    @PostAuthorize("returnObject.id.toString() == authentication.name")
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // TODO: Tìm tất cả người dùng
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // TODO: Get my info
    public User getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String id = context.getAuthentication().getName();
        return userRepository.findById(Long.parseLong(id)).orElse(null);
    }
}
