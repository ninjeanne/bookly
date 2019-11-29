package dhbw.online.bookly.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Class representing the book cover of a friendship book")
public class FriendshipBookCover {
    @Id
    @ApiModelProperty(notes = "the unique identifier of the cover image",
            example = "c96b83f8-e840-4500-9537-f671661b472c",
            position = 0)
    private String uuid;

    @Lob
    @Column(length=100000)
    @ApiModelProperty(notes = "the binary data of the cover image",
            position = 1)
    private byte[] data;

    @ApiModelProperty(notes = "the size of the cover image",
            example = "985494823",
            position = 2)
    private long size;

    @ApiModelProperty(notes = "the media type of the cover image",
            example = "image/png",
            position = 0)
    private String mediaType;
}
