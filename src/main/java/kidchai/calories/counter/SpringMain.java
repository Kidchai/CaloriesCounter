package kidchai.calories.counter;

import kidchai.calories.counter.model.Role;
import kidchai.calories.counter.model.User;
import kidchai.calories.counter.repository.UserRepository;
import kidchai.calories.counter.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

//        UserRepository userRepository = (UserRepository) appCtx.getBean("inmemoryUserRepository");
        UserRepository userRepository = appCtx.getBean(UserRepository.class);
        userRepository.getAll();

        UserService userService = appCtx.getBean(UserService.class);
        userService.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));

        appCtx.close();
    }
}
