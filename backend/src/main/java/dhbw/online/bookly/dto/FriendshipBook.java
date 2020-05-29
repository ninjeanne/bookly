package dhbw.online.bookly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import dhbw.online.bookly.exception.FriendshipBookException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@ApiModel(description = "Class representing the friendship book of a user.")
public class FriendshipBook {

    public FriendshipBook(){
        stickers = new ArrayList<>();
        stickers.add(new DummyImage());
        stickers.add(new DummyImage());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Is extracted from the authentication and the user where it belongs to" + "the unique identifier of the book.", example = "3",
            required = true, position = 0)
    private int uuid;

    @ApiModelProperty(notes = "the title of the book", example = "My super fancy friendship book", position = 1)
    private String title;

    @ApiModelProperty(notes = "the subtitle title of the book", example = "My super fancy subtitle", position = 2)
    private String subtitle;

    @JsonIgnore
    @OneToOne(cascade = { CascadeType.ALL })
    @ApiModelProperty(notes = "the cover image of the book", position = 3)
    private Image cover = new DummyImage();

    @OneToOne
    @ApiModelProperty(notes = "the user of which the book belongs to. is extracted from the authentication", position = 4)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Page.class)
    @ApiModelProperty(notes = "list of all pages of the book", position = 5)
    private List<Page> pages = new ArrayList<>();

    @ApiModelProperty(notes = "ID of the Theme template", position = 6)
    private int theme;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Image.class)
    @ApiModelProperty(notes = "the first sticker of the book", position = 7)
    private List<Image> stickers;

    @JsonIgnore
    @OneToOne(cascade = { CascadeType.ALL })
    @ApiModelProperty(notes = "the second sticker of the book", position = 8)
    private Image sticker2 = new DummyImage();

    public void setTheme(int theme) {
        if (theme < 0) {
            throw new FriendshipBookException("The theme number has to be positive");
        } else {
            this.theme = theme;
        }
    }

    public void setPages(ArrayList<Page> pages) {
        if (pages == null) {
            this.pages = new ArrayList<>();
            return;
        }
        this.pages = pages;
    }

    public void setSticker(int stickerNumber, Image sticker) {
        if (stickerNumber < 0 || stickerNumber > 1) {
            throw new FriendshipBookException("Wrong sticker number " + stickerNumber);
        }

        stickers.remove(stickerNumber);
        stickers.add(stickerNumber, (sticker == null ? new DummyImage() : sticker));
    }

    public Image getSticker(int stickerNumber) {
        if (stickerNumber < 0 || stickerNumber > 1) {
            throw new FriendshipBookException("Wrong sticker number " + stickerNumber);
        }
        return stickers.get(stickerNumber);
    }

    public static List<Image> emptyStickers(){
        List<Image> stickers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            stickers.add(new DummyImage());
        }
        return stickers;
    }

}
