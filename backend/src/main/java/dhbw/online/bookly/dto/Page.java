package dhbw.online.bookly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dhbw.online.bookly.exception.PageException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "page for a visitor")
public class Page {
    public Page(String uuid, Image pageImage, String name, String address, String telephone, String mobile, String schoolClass, String school, String size,
            String hairColor, String eyeColor, String birthday, String starSign, String favoriteSubject, String favoritePet, String howToPleaseMe,
            String whatIDontLike, String favoriteJob, String myHobbies, String fanOf, String favoriteMovie, String favoriteSport, String favoriteBook,
            String favoriteFood, String niceComment, String date, String leftOver, Image pageStickerOne, Image pageStickerTwo, Image pageStickerThree,
            Image pageStickerFour) {
        if (uuid == null || uuid.equals("")) {
            this.uuid = generateRandomUUID();
        } else {
            this.uuid = uuid;
        }
        this.pageImage = pageImage;
        this.pageStickerOne = pageStickerOne;
        this.pageStickerTwo = pageStickerTwo;
        this.pageStickerThree = pageStickerThree;
        this.pageStickerFour = pageStickerFour;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.mobile = mobile;
        this.schoolClass = schoolClass;
        this.school = school;
        this.size = size;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.birthday = birthday;
        this.starSign = starSign;
        this.favoriteSubject = favoriteSubject;
        this.favoritePet = favoritePet;
        this.howToPleaseMe = howToPleaseMe;
        this.whatIDontLike = whatIDontLike;
        this.favoriteJob = favoriteJob;
        this.myHobbies = myHobbies;
        this.fanOf = fanOf;
        this.favoriteMovie = favoriteMovie;
        this.favoriteSport = favoriteSport;
        this.favoriteBook = favoriteBook;
        this.favoriteFood = favoriteFood;
        this.niceComment = niceComment;
        this.date = date;
        this.leftOver = leftOver;
    }

    public Page() {
        this.uuid = generateRandomUUID();
    }

    public Page(String pageUUID) {
        this.uuid = pageUUID;
    }

    @Id
    @ApiModelProperty(notes = "the unique identifier of a page of a book.", example = "peachy:Peach:priest", required = true, position = 0)
    private String uuid;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "the image of the page", position = 1)
    private Image pageImage = new DummyImage();

    @ApiModelProperty(notes = "name of the friend", position = 2)
    private String name;

    @ApiModelProperty(notes = "address of the friend", position = 3)
    private String address;

    @ApiModelProperty(notes = "telephone number of the friend", position = 4)
    private String telephone;

    @ApiModelProperty(notes = "mobile number of the friend", position = 5)
    private String mobile;

    @ApiModelProperty(notes = "school class where the friend is going to", position = 6)
    private String schoolClass;

    @ApiModelProperty(notes = "school where the friend is going to", position = 7)
    private String school;

    @ApiModelProperty(notes = "school where the friend is going to", position = 8)
    private String size;

    @ApiModelProperty(notes = "hair color of the friend", position = 9)
    private String hairColor;

    @ApiModelProperty(notes = "eye color of the friend", position = 10)
    private String eyeColor;

    @ApiModelProperty(notes = "birth date of the friend", position = 11)
    private String birthday;

    @ApiModelProperty(notes = "star sign of the friend", position = 12)
    private String starSign;

    @ApiModelProperty(notes = "favorite school subject of the friend", position = 13)
    private String favoriteSubject;

    @ApiModelProperty(notes = "favorite pet of the friend", position = 14)
    private String favoritePet;

    @ApiModelProperty(notes = "what the friends like or how to please the friend", position = 15)
    private String howToPleaseMe;

    @ApiModelProperty(notes = "what the friend doesn't like", position = 16)
    private String whatIDontLike;

    @ApiModelProperty(notes = "favorite job of the friend", position = 17)
    private String favoriteJob;

    @ApiModelProperty(notes = "the hobbies of the friend", position = 18)
    private String myHobbies;

    @ApiModelProperty(notes = "of what the friend is a fan of", position = 19)
    private String fanOf;

    @ApiModelProperty(notes = "favorite movie or series of the friend", position = 20)
    private String favoriteMovie;

    @ApiModelProperty(notes = "favorite sport of the friend", position = 21)
    private String favoriteSport;

    @ApiModelProperty(notes = "favorite book of the friend", position = 22)
    private String favoriteBook;

    @ApiModelProperty(notes = "favorite food of the friend", position = 23)
    private String favoriteFood;

    @ApiModelProperty(notes = "nice comment to the friendship book owner", position = 24)
    private String niceComment;

    @ApiModelProperty(notes = "creation date", position = 25)
    private String date;

    @ApiModelProperty(notes = "last words of the friend", position = 26)
    private String leftOver;

    @JsonIgnore
    @OneToOne(cascade = { CascadeType.ALL })
    @ApiModelProperty(notes = "Page Sticker", position = 27)
    private Image pageStickerOne = new DummyImage();

    @JsonIgnore
    @OneToOne(cascade = { CascadeType.ALL })
    @ApiModelProperty(notes = "Page Sticker", position = 28)
    private Image pageStickerTwo = new DummyImage();

    @JsonIgnore
    @OneToOne(cascade = { CascadeType.ALL })
    @ApiModelProperty(notes = "Page Sticker", position = 29)
    private Image pageStickerThree = new DummyImage();

    @JsonIgnore
    @OneToOne(cascade = { CascadeType.ALL })
    @ApiModelProperty(notes = "Page Sticker", position = 30)
    private Image pageStickerFour = new DummyImage();

    private String generateRandomUUID() {
        String pageUUID = "no:pageUUID:Set";
        try {
            URL url = new URL("https://random-word-api.herokuapp.com/word?number=3");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
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

    public void setPageStickerOne(Image pageStickerOne) {
        if (pageStickerOne == null) {
            this.pageStickerOne = new DummyImage();
            return;
        }
        this.pageStickerOne = pageStickerOne;
    }

    public void setPageImage(Image pageImage) {
        if (pageImage == null) {
            this.pageImage = new DummyImage();
            return;
        }
        this.pageImage = pageImage;
    }

    public void setPageStickerTwo(Image pageStickerTwo) {
        if (pageStickerTwo == null) {
            this.pageStickerTwo = new DummyImage();
            return;
        }
        this.pageStickerTwo = pageStickerTwo;
    }

    public void setPageStickerThree(Image pageStickerThree) {
        if (pageStickerThree == null) {
            this.pageStickerThree = new DummyImage();
            return;
        }
        this.pageStickerThree = pageStickerThree;
    }

    public void setPageStickerFour(Image pageStickerFour) {
        if (pageStickerFour == null) {
            this.pageStickerFour = new DummyImage();
            return;
        }
        this.pageStickerFour = pageStickerFour;
    }

}
