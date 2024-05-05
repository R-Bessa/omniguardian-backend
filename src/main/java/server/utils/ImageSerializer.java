package server.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageSerializer {
    public static void main(String[] args) {
        byte[] imgBytes = getImageBytes("img.png");
        BufferedImage buf = deserializeImage(imgBytes);
        saveAsPNG(buf, "new.png");

    }

    public static BufferedImage deserializeImage(byte[] imageBytes) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            return ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveAsPNG(BufferedImage image, String filePath) {
        try {
            File output = new File(filePath);
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getImageBytes(String path) {
        try {
            File file = new File(path);
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
