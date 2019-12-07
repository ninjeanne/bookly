package dhbw.online.bookly.configuration;

import dhbw.online.bookly.dto.FriendshipBook;
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
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;

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
        User user = User.builder()
                .mail("user@bookly.online")
                .first_name("Max")
                .last_name("Mustermann")
                .username("user")
                .password("password")
                .build();

        Page page = initPage();
        FriendshipBook friendshipBook = FriendshipBook.builder()
                .title("Unser super tolles Buch")
                .user(user)
                .pages(Collections.singletonList(page))
                .build();

        userRepository.save(user);
        friendshipBookRepository.save(friendshipBook);

        //read image
        try {
            friendshipBookService.saveImageForBook(friendshipBook, extractBytes("test_image.jpg"), 0, "image/jpeg");
            pageService.saveImageForPage(page, extractBytes("test_image.jpg"), 0, "image/jpeg");
        } catch (IOException e) {
            log.debug("Could not read test image in resources folder");
        }

    }

    public byte[] extractBytes(String imageName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(imageName);
        // open image
        assert resource != null;
        File imgPath = new File(resource.getFile());
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

        return (data.getData());
    }

    private Page initPage() {
        Page page = new Page();
        page.getAddress().setDescription("Teststraße 12");
        page.getName().setDescription("Max Mustermann");
        page.getTelephone().setDescription("012345");
        page.getMobile().setDescription("23412313");
        page.getSchool().setDescription("MIT");
        page.getSchool_class().setDescription("1A");
        page.getSize().setDescription("1.8m");
        page.getHair_color().setDescription("blond");
        page.getMy_hobbies().setDescription("Jogging, Dancing, Programming");
        page.getEye_color().setDescription("green");
        page.getFavorite_movie().setDescription("Star Trek, Lilo and Stitch");
        page.getFavorite_book().setDescription("1984");
        page.getFavorite_food().setDescription("Spaghetti");
        page.getWhat_i_dont_like().setDescription("learning");
        return page;
    }

}
