package TheFindingOfIZack.Util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

public class ImageLoader {

    public static final Icon normalButton;
    public static final Icon hoverButton;
    public static final Icon disabledButton;

    public static final Image controls;
    public static final Image startBG;

    static {
        normalButton = new ImageIcon(ImageLoader.class.getResource(("/normalButton.jpg")));
        hoverButton = new ImageIcon(ImageLoader.class.getResource(("/hoverButton.jpg")));
        disabledButton = new ImageIcon(ImageLoader.class.getResource(("/disabledButton.jpg")));

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
            img =  ImageIO.read(ImageLoader.class.getResource(("/startBG.jpg"))).getScaledInstance(GameSize.WINDOW_WIDTH,GameSize.WINDOW_HEIGHT,Image.SCALE_DEFAULT);
        } catch (IOException e) {

        }finally {
            startBG = img;
        }

        img = new Image() {
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
            img =  ImageIO.read(ImageLoader.class.getResource(("/keys.png"))).getScaledInstance(330,150,Image.SCALE_DEFAULT);
        } catch (IOException e) {

        }finally {
            controls = img;
        }
    }

}
