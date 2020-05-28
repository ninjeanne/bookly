package dhbw.online.bookly.dto;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

@Entity
@ApiModel(description = "Class representing any dummy image.")
public class DummyImage extends Image {

    @Override
    public byte[] getData() {
        try {
            return extractBytes("test_image.jpg");
        } catch (IOException e) {
        }
        return null;
    }

    @Override
    public long getSize() {
        return 423867;
    }

    @Override
    public String getMediaType() {
        return "image/jpeg";
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
