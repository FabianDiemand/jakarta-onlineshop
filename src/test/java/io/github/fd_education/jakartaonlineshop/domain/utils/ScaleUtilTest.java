package io.github.fd_education.jakartaonlineshop.domain.utils;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScaleUtilTest {

    @Test
    void scale_test() throws IOException {
        URL path = Objects.requireNonNull(getClass().getResource("/test-images/graka.png"));
        File file = new File(path.getPath());
        byte[] foto = Files.readAllBytes(file.toPath());

        int SCALE_TO_LENGTH = 400;
        byte[] scaledFoto = ScaleUtil.scale(foto, SCALE_TO_LENGTH);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(scaledFoto);
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

        assertEquals(SCALE_TO_LENGTH, Math.max(bufferedImage.getHeight(), bufferedImage.getWidth()));
    }
}