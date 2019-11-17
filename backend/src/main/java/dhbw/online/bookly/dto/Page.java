package dhbw.online.bookly.dto;

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
public class Page {
    @Id
    private String uuid;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = PageEntry.class)
    private List<PageEntry> entries;
}
