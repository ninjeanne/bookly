package dhbw.online.bookly.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Class representing a sticker of a page")
public class PageSticker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "the unique identifier of the page sticker",
            example = "1",
            position = 0)
    private int uuid;

    @Lob
    @Column(length=100000)
    @Type(type = "org.hibernate.type.ImageType")
    @ApiModelProperty(notes = "the binary data of the page sticker",
            position = 1)
    private byte[] data;

    @ApiModelProperty(notes = "the size of the page sticker",
            example = "33423429",
            position = 2)
    private long size;

    @ApiModelProperty(notes = "the media type of the page sticker",
            example = "image/png",
            position = 3)
    private String mediaType;
}
