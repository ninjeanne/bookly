package dhbw.online.bookly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

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

    @ApiModelProperty(notes = "the cover title of the book",
            example = "My super fancy friendship book",
            position = 1)
    private String title;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL})
    @ApiModelProperty(notes = "the cover image of the book",
            position = 2)
    private FriendshipBookCover cover;

    @OneToOne
    @ApiModelProperty(notes = "the user of which the book belongs to. is extracted from the authentication",
            position = 3)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Page.class)
    @ApiModelProperty(notes = "list of all pages of the book",
            position = 4)
    private List<Page> pages;
}
