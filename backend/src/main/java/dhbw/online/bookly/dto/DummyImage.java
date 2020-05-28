package dhbw.online.bookly.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

@Data
@Entity
@ApiModel(description = "Class representing the dummy image of a page entry.")
public class DummyImage extends Image {
    @Override
    public void setUuid(int uuid) {
        super.setUuid(0);
    }

    public DummyImage() {
        try {
            setData(extractBytes("test_image.jpg"));
        } catch (IOException e) {
        }
        setSize(423867);
        setMediaType("image/jpeg");
    }

    private byte[] extractBytes(String imageName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(imageName);
        // open image
        assert resource != null;
        File imgPath = new File(resource.getFile());
        return Files.readAllBytes(imgPath.toPath());
    }
}
