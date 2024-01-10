package objects;

import main.GamePanel;
import manage.ManageRemovableTile;
import java.awt.*;

/* used as an object tile that player can interact with */
public class Water extends ManageRemovableTile {
    public Water(GamePanel gp, int color){
        super(gp);
        setValues();
        images(color);
        //System.out.println("Water = new ");
    }
    private void setValues(){
        name = "water";
        alive = true;
        type = 7;       // look: Entity class
        health = 5;
        maxHealth = 5;
        direction = "stationary";
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void images(int color){
        if(color == 0){
            stationary = imageHandler("/images/water_1", gp.tileSize, gp.tileSize);
        }else{
            stationary = imageHandler("/images/water_2", gp.tileSize, gp.tileSize);
        }
    }
}
