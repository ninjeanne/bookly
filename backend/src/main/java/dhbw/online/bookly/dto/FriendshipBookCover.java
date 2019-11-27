package dhbw.online.bookly.dto;

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
public class FriendshipBookCover {
    @Id
    private String uuid;
    @Lob
    @Column(length=100000)
    private byte[] data;
    private long size;
    private String mediaType;
}
