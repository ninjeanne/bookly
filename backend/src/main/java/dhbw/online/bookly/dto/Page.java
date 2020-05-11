package dhbw.online.bookly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dhbw.online.bookly.exception.PageException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.util.NoSuchElementException;
import java.util.Random;

@Data
@Entity
@Builder
@ApiModel(description = "page for a visitor")
public class Page {
    public Page(String uuid, PageImage pageImage, String name, String address, String telephone,
                String mobile, String school_class, String school, String size,
                String hair_color, String eye_color, String birthday, String star_sign,
                String favorite_subject, String favorite_pet, String how_to_please_me,
                String what_i_dont_like, String favorite_job, String my_hobbies,
                String fan_of, String favorite_movie, String favorite_sport, String favorite_book,
                String favorite_food, String nice_comment, String date, String leftOver) {
        if (uuid == null || uuid.equals("")) {
            this.uuid = generateRandomUUIDfromList();
        } else this.uuid = uuid;
        this.pageImage = pageImage;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.mobile = mobile;
        this.school_class = school_class;
        this.school = school;
        this.size = size;
        this.hair_color = hair_color;
        this.eye_color = eye_color;
        this.birthday = birthday;
        this.star_sign = star_sign;
        this.favorite_subject = favorite_subject;
        this.favorite_pet = favorite_pet;
        this.how_to_please_me = how_to_please_me;
        this.what_i_dont_like = what_i_dont_like;
        this.favorite_job = favorite_job;
        this.my_hobbies = my_hobbies;
        this.fan_of = fan_of;
        this.favorite_movie = favorite_movie;
        this.favorite_sport = favorite_sport;
        this.favorite_book = favorite_book;
        this.favorite_food = favorite_food;
        this.nice_comment = nice_comment;
        this.date = date;
        this.leftOver = leftOver;
    }

    public Page() {
        this.uuid = generateRandomUUIDfromList();
        this.pageImage = new PageImage();
        this.name = null;
        this.address = null;
        this.telephone = null;
        this.mobile = null;
        this.school_class = null;
        this.school = null;
        this.size = null;
        this.hair_color = null;
        this.eye_color = null;
        this.birthday = null;
        this.star_sign = null;
        this.favorite_subject = null;
        this.favorite_pet = null;
        this.how_to_please_me = null;
        this.what_i_dont_like = null;
        this.favorite_job = null;
        this.my_hobbies = null;
        this.fan_of = null;
        this.favorite_movie = null;
        this.favorite_sport = null;
        this.favorite_book = null;
        this.favorite_food = null;
        this.nice_comment = null;
        this.date = null;
        this.leftOver = null;
    }

    public Page(String pageUUID) {
        this.uuid = pageUUID;
        this.pageImage = new PageImage();
        this.name = null;
        this.address = null;
        this.telephone = null;
        this.mobile = null;
        this.school_class = null;
        this.school = null;
        this.size = null;
        this.hair_color = null;
        this.eye_color = null;
        this.birthday = null;
        this.star_sign = null;
        this.favorite_subject = null;
        this.favorite_pet = null;
        this.how_to_please_me = null;
        this.what_i_dont_like = null;
        this.favorite_job = null;
        this.my_hobbies = null;
        this.fan_of = null;
        this.favorite_movie = null;
        this.favorite_sport = null;
        this.favorite_book = null;
        this.favorite_food = null;
        this.nice_comment = null;
        this.date = null;
        this.leftOver = null;
    }

    @Id
    @ApiModelProperty(notes = "the unique identifier of a page of a book.",
            example = "peachy:Peach:priest",
            required = true,
            position = 0)
    private String uuid;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "the image of the page", position = 1)
    private PageImage pageImage;

    @ApiModelProperty(notes = "name of the friend", position = 2)
    private String name;

    @ApiModelProperty(notes = "address of the friend", position = 3)
    private String address;

    @ApiModelProperty(notes = "telephone number of the friend", position = 4)
    private String telephone;

    @ApiModelProperty(notes = "mobile number of the friend", position = 5)
    private String mobile;

    @ApiModelProperty(notes = "school class where the friend is going to", position = 6)
    private String school_class;

    @ApiModelProperty(notes = "school where the friend is going to", position = 7)
    private String school;

    @ApiModelProperty(notes = "school where the friend is going to", position = 8)
    private String size;

    @ApiModelProperty(notes = "hair color of the friend", position = 9)
    private String hair_color;

    @ApiModelProperty(notes = "eye color of the friend", position = 10)
    private String eye_color;

    @ApiModelProperty(notes = "birth date of the friend", position = 11)
    private String birthday;

    @ApiModelProperty(notes = "star sign of the friend", position = 12)
    private String star_sign;

    @ApiModelProperty(notes = "favorite school subject of the friend", position = 13)
    private String favorite_subject;

    @ApiModelProperty(notes = "favorite pet of the friend", position = 14)
    private String favorite_pet;

    @ApiModelProperty(notes = "what the friends like or how to please the friend", position = 15)
    private String how_to_please_me;

    @ApiModelProperty(notes = "what the friend doesn't like", position = 16)
    private String what_i_dont_like;

    @ApiModelProperty(notes = "favorite job of the friend", position = 17)
    private String favorite_job;

    @ApiModelProperty(notes = "the hobbies of the friend", position = 18)
    private String my_hobbies;

    @ApiModelProperty(notes = "of what the friend is a fan of", position = 19)
    private String fan_of;

    @ApiModelProperty(notes = "favorite movie or series of the friend", position = 20)
    private String favorite_movie;

    @ApiModelProperty(notes = "favorite sport of the friend", position = 21)
    private String favorite_sport;

    @ApiModelProperty(notes = "favorite book of the friend", position = 22)
    private String favorite_book;

    @ApiModelProperty(notes = "favorite food of the friend", position = 23)
    private String favorite_food;

    @ApiModelProperty(notes = "nice comment to the friendship book owner", position = 24)
    private String nice_comment;

    @ApiModelProperty(notes = "creation date", position = 25)
    private String date;

    @ApiModelProperty(notes = "last words of the friend", position = 26)
    private String leftOver;

    private String generateRandomUUIDfromList() {
        final long numberOfLines = 370100L;
        long randomLineNumber1 = getRandomNumberLong(1, numberOfLines);
        long randomLineNumber2 = getRandomNumberLong(1, numberOfLines);
        long randomLineNumber3 = getRandomNumberLong(1, numberOfLines);
        StringBuilder newPageUUID=new StringBuilder();
        try {
            try (FileReader readfile = new FileReader("./backend/src/main/resources/words_alpha.txt")) {
                BufferedReader readbuffer = new BufferedReader(readfile);
                for (long lineNumber = 1; lineNumber < numberOfLines; lineNumber++) {
                    if (lineNumber == randomLineNumber1 ||
                            lineNumber == randomLineNumber2 ||
                            lineNumber == randomLineNumber3) {
                        newPageUUID.append(readbuffer.readLine()+":");
                    } else
                        readbuffer.readLine();
                }
                readbuffer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newPageUUID.toString();
    }

    private String generateRandomUUID() {
        String pageUUID = "no:pageUUID:Set";
        try {
            URL url = new URL("https://random-word-api.herokuapp.com/word?number=3");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                inputLine = inputLine.replaceAll("\\[\"", "");
                inputLine = inputLine.replaceAll("\",\"", ":");
                inputLine = inputLine.replaceAll("\"]", "");
                content.append(inputLine);
            }
            pageUUID = content.toString();
            in.close();
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (pageUUID.equals("no:UUID:Set")) {
            throw new PageException("No UUID Set");
        }
        return pageUUID;
    }

    public static long getRandomNumberLong(long min, long max) {
        Random random = new Random();
        return random.longs(min, (max + 1)).findFirst().getAsLong();
    }
}
