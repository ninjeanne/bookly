package dhbw.online.bookly.dto;

import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.persistence.Entity;
import java.io.IOException;
import java.io.InputStream;

@Entity
@Slf4j
@ApiModel(description = "Class representing any dummy image.")
public class DummyImage extends Image {

    @Override
    public byte[] getData() {
        try {
            return extractBytes("test_image.jpg");
        } catch (IOException e) {
            System.err.println(e.getMessage());
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

        InputStream stream = classLoader.getResourceAsStream(imageName);
        assert stream != null;
        return IOUtils.toByteArray(stream);
    }
}
