package dhbw.online.bookly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "page for a visitor")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "the unique identifier of a page of a book.", example = "3", required = true, position = 0)
    private int uuid;

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

}
