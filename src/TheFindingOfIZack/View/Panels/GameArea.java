package TheFindingOfIZack.View.Panels;

import TheFindingOfIZack.World.Model;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel{
    private Model model;

    public GameArea(Model m){
        super();
        this.model = m;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawRoom(g);
        drawPlayer(g);
        drawEntities(g);
    }


    private void drawRoom(Graphics g){
        model.getPlayer().getRoom().draw(g);
    }

    private void drawEntities(Graphics g){
        model.getPlayer();
    }
    private void drawPlayer(Graphics g){
        model.getPlayer().draw(g);
    }
}
