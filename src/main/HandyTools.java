package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HandyTools {
    // helper scale forest_images used inside manage.ManageTile{setup();}
    public BufferedImage scaleImage(BufferedImage originalImage, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(originalImage, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
