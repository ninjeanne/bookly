package dhbw.online.bookly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import dhbw.online.bookly.exception.PageException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Data
@Entity
@Builder
@AllArgsConstructor
@ApiModel(description = "page for a visitor")
public class Page {

    public Page() {
        setUuid(generateRandomUUID());
        setAddress(new PageEntry("Address"));
        setName(new PageEntry("Name"));
        setTelephone(new PageEntry("Telephone"));
        setMobile(new PageEntry("Mobile"));
        setSchool(new PageEntry("School"));
        setSchool_class(new PageEntry("School class"));
        setSize(new PageEntry("Size"));
        setHair_color(new PageEntry("Hair color"));
        setMy_hobbies(new PageEntry("My hobbies"));
        setEye_color(new PageEntry("Eye color"));
        setFavorite_book(new PageEntry("My favorite book"));
        setFavorite_movie(new PageEntry("Best Movie or TV show"));
        setFavorite_sport(new PageEntry("Favorite sport"));
        setFavorite_subject(new PageEntry("Favorite school subject"));
        setFavorite_job(new PageEntry("Dream profession"));
        setFavorite_pet(new PageEntry("My pet"));
        setFavorite_food(new PageEntry("Favorite food"));
        setNice_comment(new PageEntry("Here's something nice for you"));
        setDate(new PageEntry("Date"));
        setSign(new PageEntry("My autograph for you"));
        setHow_to_please_me(new PageEntry("So one can make me a joy"));
        setStar_sign(new PageEntry("sign of the zodiac"));
        setBirthday(new PageEntry("birthday"));
        setFan_of(new PageEntry("I am a fan of"));
        setWhat_i_dont_like(new PageEntry("What i don't like"));
    }

    private String generateRandomUUID() {
        String UUID = "no:UUID:Set";
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
            UUID = content.toString();
            in.close();
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(UUID.equals("no:UUID:Set")){
            throw new PageException("No UUID Set");
        }
        return UUID;
    }


    @Id
    @ApiModelProperty(notes = "the unique identifier of a page of a book.",
            example = "peachy:Peach:priest",
            required = true,
            position = 0)
    private String uuid;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "the image of the page",
            position = 1)
    private PageImage pageImage;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "name of the friend",
            position = 2)
    private PageEntry name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "address of the friend",
            position = 3)
    private PageEntry address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "telephone number of the friend",
            position = 4)
    private PageEntry telephone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "mobile number of the friend",
            position = 5)
    private PageEntry mobile;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "school class where the friend is going to",
            position = 6)
    private PageEntry school_class;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "school where the friend is going to",
            position = 7)
    private PageEntry school;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "school where the friend is going to",
            position = 8)
    private PageEntry size;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "hair color of the friend",
            position = 9)
    private PageEntry hair_color;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "eye color of the friend",
            position = 10)
    private PageEntry eye_color;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "birth date of the friend",
            position = 11)
    private PageEntry birthday;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "star sign of the friend",
            position = 12)
    private PageEntry star_sign;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "favorite school subject of the friend",
            position = 13)
    private PageEntry favorite_subject;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "favorite pet of the friend",
            position = 14)
    private PageEntry favorite_pet;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "what the friends like or how to please the friend",
            position = 15)
    private PageEntry how_to_please_me;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "what the friend doesn't like",
            position = 16)
    private PageEntry what_i_dont_like;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "favorite job of the friend",
            position = 17)
    private PageEntry favorite_job;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "the hobbies of the friend",
            position = 18)
    private PageEntry my_hobbies;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "of what the friend is a fan of",
            position = 19)
    private PageEntry fan_of;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "favorite movie or series of the friend",
            position = 20)
    private PageEntry favorite_movie;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "favorite sport of the friend",
            position = 21)
    private PageEntry favorite_sport;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "favorite book of the friend",
            position = 22)
    private PageEntry favorite_book;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "favorite food of the friend",
            position = 23)
    private PageEntry favorite_food;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "nice comment to the friendship book owner",
            position = 24)
    private PageEntry nice_comment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "creation date",
            position = 25)
    private PageEntry date;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "signing of the friend",
            position = 26)
    private PageEntry sign;

}
