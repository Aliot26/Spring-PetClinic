package com.petclinic.spring.services.springdatajpa;

import com.petclinic.spring.dto.SignupRequest;
import com.petclinic.spring.model.ERole;
import com.petclinic.spring.model.Role;
import com.petclinic.spring.model.Status;
import com.petclinic.spring.model.User;
import com.petclinic.spring.repositories.RoleRepository;
import com.petclinic.spring.repositories.UserRepository;
import com.petclinic.spring.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 *Created by olga on 03.09.2020
 */
@Service
@Profile("springdatajpa")
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(SignupRequest user) {
        User newUser = new User(user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                user.getEmail()
                );
        newUser.setUsername(user.getUsername());

        Role roleUser = roleRepository.findByName(ERole.ROLE_USER);
        ArrayList<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        newUser.setRoles(userRoles);
        newUser.setStatus(Status.ACTIVE);

        User registerUser = userRepository.save(newUser);

        log.info("IN register - user: {} successfully registered", registerUser);
        return registerUser;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = (List<User>) userRepository.findAll();
        log.info("In getAll {} users were found", userList.size());
        return userList;
    }

    @Override
    public User  findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        if (result == null) {
            log.warn("In findByUsername - no user found by username {}", username);
            return null;
        }
        log.info("In findByUsername - user: {} found", result);
        return result;

    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("In findById - no user found by id {}", id);
            return null;
        }
        log.info("In findById - user: {} found", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("In delete - user with id ; {} successfully deleted", id);
    }

    @Override
    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email);
        if (result == null) {
            log.warn("In findByEmail - no user found by email {}", email);
            return null;
        }
        log.info("In findByEmail - email: {} found", result);
        return result;
    }

    @Override
    public Boolean existsByUsername(String username) {
        boolean existsData = true;
        if (userRepository.findByUsername(username) == null) {
            existsData = false;
        }
        return existsData;
    }

    @Override
    public Boolean existsByEmail(String email) {
        boolean existsData = true;
        if (userRepository.findByEmail(email) == null) {
            existsData = false;
        }
        return existsData;
    }
}
