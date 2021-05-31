package r2.llc.communal.model.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import r2.llc.communal.model.data.EntityMapper;
import r2.llc.communal.model.data.RegModel;
import r2.llc.communal.model.data.SingleMapper;
import r2.llc.communal.model.data.UserModel;
import r2.llc.communal.model.entity.UserEntity;
import r2.llc.communal.model.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService implements UserDetailsService {

    private final EntityMapper<UserModel, UserEntity> userMapper;
    private final SingleMapper<RegModel, UserEntity> regMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public List<UserModel> getAll() {
        return repository
                .findAll()
                .stream()
                .map(userMapper::mapRT)
                .collect(Collectors.toList());
    }

    public UserModel save(RegModel regModel) {
        UserEntity user = regMapper.map(regModel);
        UserEntity result = repository.save(user);
        return userMapper.mapRT(result);
    }

    public UserModel getById(Long id) {
        UserEntity entity = repository.findById(id).orElseThrow();
        return userMapper.mapRT(entity);
    }

    public UserModel auth(String username, String pass) throws NoSuchElementException {
        return repository
                .findByName(username)
                .filter(userEntity -> passwordEncoder.matches(pass, userEntity.getPassword()))
                .map(userEntity -> {
                    userEntity.setToken(generateToken());
                    return repository.save(userEntity);
                })
                .map(userMapper::mapRT)
                .orElseThrow();
    }

    public UserModel deleteById(Long id) {
        UserEntity result = repository.getById(id);
        repository.deleteById(id);
        return userMapper.mapRT(result);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository
                .findByName(username)
                .map(UserEntity::toUserDetails)
                .orElseThrow();
    }

    public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
        return repository
                .findByToken(token)
                .map(UserEntity::toUserDetails)
                .orElseThrow();
    }

    public UserModel loadUserModelByToken(String token) throws UsernameNotFoundException {
        return repository
                .findByToken(token)
                .map(userMapper::mapRT)
                .orElseThrow();
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
