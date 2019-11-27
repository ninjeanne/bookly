package dhbw.online.bookly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipBook {
    @Id
    private String uuid;
    private String title;
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL})
    private FriendshipBookCover cover;
    @OneToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Page.class)
    private List<Page> pages;
}
