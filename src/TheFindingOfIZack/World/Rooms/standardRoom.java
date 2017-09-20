public class standardRoom extends Room extends Drawable{

    private ArrayList<Enemy> enemiesInRoom;

    public standardRoom(){
        this.enemiesInRoom = new ArrayList<Enemy>();

    }


public void draw(Graphics g){
        for(Enemy e: enemiesInRoom){
            e.draw(g);
        }

}




}