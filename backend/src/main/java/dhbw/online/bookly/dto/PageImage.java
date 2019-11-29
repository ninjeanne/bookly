package dhbw.online.bookly.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Class representing the image of a page entry.")
public class PageImage {
    @Id
    @ApiModelProperty(notes = "the unique identifier of the page image",
            example = "c96b83f8-e840-4500-9537-f671661b472c",
            position = 0)
    private String uuid;

    @Lob
    @Column(length=100000)
    @ApiModelProperty(notes = "the binary data of the page image",
            position = 1)
    private byte[] data;

    @ApiModelProperty(notes = "the size of the page image",
            example = "985494823",
            position = 2)
    private long size;

    @ApiModelProperty(notes = "the media type of the page image",
            example = "image/png",
            position = 0)
    private String mediaType;
}
