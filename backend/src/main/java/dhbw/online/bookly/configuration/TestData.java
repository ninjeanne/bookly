package dhbw.online.bookly.configuration;

import dhbw.online.bookly.dto.DummyImage;
import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.repository.FriendshipBookRepository;
import dhbw.online.bookly.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@Slf4j
@Profile("local")
public class TestData {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendshipBookRepository friendshipBookRepository;

    @PostConstruct
    private void initialize() {
        User user = User.builder().mail("user@bookly.online").first_name("Max").last_name("Mustermann").username("test-user").build();

        Page page = initPage();
        Page secondPage = initSecondPage();
        FriendshipBook friendshipBook = FriendshipBook.builder().cover(new DummyImage()).title("Unser super tolles Buch").user(user)
                .pages(Arrays.asList(page, secondPage)).build();
        System.out.println(friendshipBook.getCover().getSize());
        if (!userRepository.existsByUsername(user.getUsername())) {
            userRepository.save(user);
            if (!friendshipBookRepository.existsByUser(user)) {
                friendshipBookRepository.save(friendshipBook);
            }
        }
    }

    private Page initPage() {
        Page page = new Page();
        page.setAddress("Teststraße 12");
        page.setName("Max Mustermann");
        page.setTelephone("555555");
        page.setMobile("123456");
        page.setSchool("Driving School");
        page.setSchoolClass("1A");
        page.setSize("1.8m");
        page.setHairColor("blond");
        page.setMyHobbies("Jogging, Dancing, Programming");
        page.setEyeColor("green");
        page.setFavoriteMovie("Star Trek, Lilo and Stitch");
        page.setFavoriteFood("Spaghetti");
        page.setWhatIDontLike("learning");
        page.setLeftOver("My last words are CHOCOLATE");
        return page;
    }

    private Page initSecondPage() {
        Page page = new Page();
        page.setAddress("Teststraße 12");
        page.setName("Maximila Musterfrau");
        page.setTelephone("012345");
        page.setMobile("23412313");
        page.setSchool("MIT");
        page.setSchoolClass("1A");
        page.setSize("1.8m");
        page.setHairColor("blond");
        page.setMyHobbies("Jogging, Dancing, Programming");
        page.setEyeColor("green");
        page.setFavoriteJob("Chillen");
        page.setFavoriteBook("1984");
        page.setWhatIDontLike("learning");
        page.setLeftOver("My last words are CHOCOLATE");
        page.setFanOf("Doing nothing");
        page.setNiceComment("Hi Neeeko :) Du kannst mich lesen");
        return page;
    }

}
