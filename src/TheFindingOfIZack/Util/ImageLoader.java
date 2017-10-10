package TheFindingOfIZack.Util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

public class ImageLoader {

    public static Icon loadIcon(String resource){
        return new ImageIcon((ImageLoader.class.getResource(resource)));
    }

    public static Image loadImage(String resource){
        Image img = new Image() {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        };

        try {
            img =  ImageIO.read(ImageLoader.class.getResource(("resource"))).getScaledInstance(GameSize.WINDOW_WIDTH,GameSize.WINDOW_HEIGHT,Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
