package dhbw.online.bookly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "the unique identifier of a page entry of a book.",
            example = "3",
            position = 0)
    private int uuid;

    @ApiModelProperty(notes = "title of a page entry",
            example = "Titel",
            position = 1)
    private String title;

    @ApiModelProperty(notes = "individual description of a page entry",
            example = "Inhalt",
            position = 2)
    private String description;
}
