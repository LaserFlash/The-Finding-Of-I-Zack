package World.Rooms;

import java.awt.*;

/**
 * Created by fieldryan on 19/09/17.
 */
public class startRoom extends Room {



    public startRoom(){

    }


    public void update(){
        System.out.println("updating room");
    }

    @Override
    public void draw(Graphics g) {
    super.drawBase(50,50,Color.black,g);
        g.setColor(Color.black);
        g.fillOval(50,50,50,50);

    }
}
