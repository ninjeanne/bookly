package dhbw.online.bookly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@ApiModel(description = "Class representing the friendship book of a user.")
public class FriendshipBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Is extracted from the authentication and the user where it belongs to" +
            "the unique identifier of the book.",
            example = "3",
            required = true,
            position = 0)
    private int uuid;

    @ApiModelProperty(notes = "the title of the book",
            example = "My super fancy friendship book",
            position = 1)
    private String title;

    @ApiModelProperty(notes = "the subtitle title of the book",
            example = "My super fancy subtitle",
            position = 2)
    private String subtitle;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL})
    @ApiModelProperty(notes = "the cover image of the book",
            position = 3)
    private FriendshipBookCover cover;

    @OneToOne
    @ApiModelProperty(notes = "the user of which the book belongs to. is extracted from the authentication",
            position = 4)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Page.class)
    @ApiModelProperty(notes = "list of all pages of the book",
            position = 5)
    private List<Page> pages = new ArrayList<>();

    @ApiModelProperty(notes = "ID of the Theme template",
            position = 6)
    private int theme;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL})
    @ApiModelProperty(notes = "the first sticker of the book",
            position = 7)
    private FriendshipBookSticker sticker1;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL})
    @ApiModelProperty(notes = "the second sticker of the book",
            position = 8)
    private FriendshipBookSticker sticker2;

    public void setTheme(int theme){
        if(theme < 0){
            throw new FriendshipBookException("The theme number has to be positive");
        } else {
            this.theme = theme;
        }
    }

    public void setPages(ArrayList<Page> pages){
        if(pages == null){
            this.pages = new ArrayList<>();
            return;
        }
        this.pages = pages;
    }
}
