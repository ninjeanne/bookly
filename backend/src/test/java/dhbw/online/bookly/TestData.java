package dhbw.online.bookly;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.FriendshipBookCover;
import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.repository.FriendshipBookRepository;
import dhbw.online.bookly.repository.UserRepository;
import dhbw.online.bookly.service.FriendshipBookService;
import dhbw.online.bookly.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;

@Component
@Slf4j
public class TestData {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendshipBookRepository friendshipBookRepository;
    @Autowired
    private FriendshipBookService friendshipBookService;
    @Autowired
    private PageService pageService;

    @PostConstruct
    private void initialize() {
        User user = User.builder().mail("user@bookly.online").first_name("Max").last_name("Mustermann").username("test-user").build();

        Page page = initPage();
        Page secondPage = initSecondPage();
        FriendshipBook friendshipBook = FriendshipBook.builder().title("Unser super tolles Buch").user(user).pages(Arrays.asList(page, secondPage)).build();

        if (!userRepository.existsByUsername(user.getUsername())) {
            userRepository.save(user);
            if (!friendshipBookRepository.existsByUser(user)) {
                try {
                    FriendshipBookCover cover = FriendshipBookCover.builder().data(extractBytes("test_image.jpg")).size(423867).mediaType("image/jpeg").build();
                    friendshipBook.setCover(cover);
                    friendshipBookRepository.save(friendshipBook);
                    pageService.saveImageForPage(page, extractBytes("test_image.jpg"), 423867, "image/jpeg");
                } catch (IOException e) {
                    log.debug("Could not read test image in resources folder");
                }
            }
        }
    }

    public byte[] extractBytes(String imageName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(imageName);
        // open image
        assert resource != null;
        File imgPath = new File(resource.getFile());
        return Files.readAllBytes(imgPath.toPath());
    }

    private Page initPage() {
        Page page = new Page("test:page:UUID");
        page.setAddress("Teststraße 12");
        page.setName("Max Mustermann");
        page.setTelephone("555555");
        page.setMobile("123456");
        page.setSchool("Driving School");
        page.setSchool_class("1A");
        page.setSize("1.8m");
        page.setHair_color("blond");
        page.setMy_hobbies("Jogging, Dancing, Programming");
        page.setEye_color("green");
        page.setFavorite_movie("Star Trek, Lilo and Stitch");
        page.setFavorite_food("Spaghetti");
        page.setWhat_i_dont_like("learning");
        page.setLeftOver("My last words are CHOCOLATE");
        return page;
    }

    private Page initSecondPage() {
        Page page = new Page("new:page:UUID");
        page.setAddress("Teststraße 12");
        page.setName("Maximila Musterfrau");
        page.setTelephone("012345");
        page.setMobile("23412313");
        page.setSchool("MIT");
        page.setSchool_class("1A");
        page.setSize("1.8m");
        page.setHair_color("blond");
        page.setMy_hobbies("Jogging, Dancing, Programming");
        page.setEye_color("green");
        page.setFavorite_job("Chillen");
        page.setFavorite_book("1984");
        page.setWhat_i_dont_like("learning");
        page.setLeftOver("My last words are CHOCOLATE");
        page.setFan_of("Doing nothing");
        page.setNice_comment("Hi Neeeko :) Du kannst mich lesen");
        return page;
    }

}
