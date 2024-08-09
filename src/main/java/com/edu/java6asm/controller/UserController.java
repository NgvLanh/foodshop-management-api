package com.edu.java6asm.controller;

import com.edu.java6asm.model.User;
import com.edu.java6asm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // TODO: Tạo mới một người dùng
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        var createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // TODO: Cập nhật thông tin người dùng
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        var updated = userService.updateUser(id, updatedUser);
        return updated != null ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TODO: Xóa người dùng theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO: Tìm người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        var user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TODO: Tìm tất cả người dùng
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/my-info")
    public ResponseEntity<?> getMyInfo() {
        var user = userService.getMyInfo();
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}
