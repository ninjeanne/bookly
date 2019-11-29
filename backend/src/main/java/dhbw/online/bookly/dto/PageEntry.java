package dhbw.online.bookly.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "title and description of a simple entry of a page")
public class PageEntry {

    public PageEntry(String title) {
        setTitle(title);
    }

    @Id
    @ApiModelProperty(notes = "the unique identifier of a page entry of a book.",
            example = "27df8c72-83e8-4ef1-8f3b-b99a24d7f5d2",
            position = 0)
    private String uuid = UUID.randomUUID().toString();

    @ApiModelProperty(notes = "title of a page entry",
            example = "Titel",
            position = 1)
    private String title;

    @ApiModelProperty(notes = "individual description of a page entry",
            example = "Inhalt",
            position = 2)
    private String description;
}
