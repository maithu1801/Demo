package com.example.NewWeb;

import com.example.NewWeb.user.User;
import com.example.NewWeb.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.SnapshotStateRepository;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;
    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("buithixuan@gmail.com");
        user.setPassword("xuan12389");
        user.setFirstName("Xuan");
        user.setLastName("Bui Thi");

        User savedUser = repo.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }


    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }
        @Test
    public void testUpdate() {
            Integer userId = 3;
            Optional<User> optionalUser = repo.findById(userId);
            User user = optionalUser.get();
            user.setFirstName("anh");
            repo.save(user);

            User updateUser = repo.findById(userId).get();
            Assertions.assertThat(updateUser.getPassword()).isEqualTo("anh");
        }

        @Test
        public void testGet() {
            Integer userId = 4;
            Optional<User> optionalUser = repo.findById(userId);
            Assertions.assertThat(optionalUser).isPresent();
            System.out.println(optionalUser.get());
        }


        @Test
    public void testDelete() {
            Integer userId = 4;
            repo.deleteById(userId);

            Optional<User> optionalUser = repo.findById(userId);
            Assertions.assertThat(optionalUser).isPresent();

        }




}

