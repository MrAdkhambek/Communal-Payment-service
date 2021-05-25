package r2.llc.communal.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import r2.llc.communal.model.entity.UserEntity;
import r2.llc.communal.model.repository.UserRepository;


@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("RUN service");

        UserEntity admin = new UserEntity();
        admin.setId(1L);
        admin.setName("ADMIN");
        admin.setPhoto("ADMIN");
        admin.setRole("ADMIN");
        admin.setPassword(passwordEncoder.encode("ADMIN"));

        UserEntity user = new UserEntity();
        user.setId(2L);
        user.setName("USER");
        user.setPhoto("USER");
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode("USER"));

        userRepository.save(admin);
        userRepository.save(user);
    }
}
