package objects;

import main.GamePanel;
import manage.ManageRemovableTile;
import java.awt.*;

/* used as an object tile that player can interact with */
public class Mushroom extends ManageRemovableTile {
    public Mushroom(GamePanel gp, int randImage){
        super(gp);
        setValues();
        images(randImage);
        //System.out.println("Mushroom = new");
    }
    private void setValues(){
        name = "mushroom";
        alive = true;
        type = 7;       // look: Entity class
        health = 5;
        maxHealth = 5;
        direction = "stationary";
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void images(int randImage){
        if(randImage == 0){
            stationary = imageHandler("/images/mushroom", gp.tileSize, gp.tileSize);
        }
        else{
            stationary = imageHandler("/images/mushroom_blue", gp.tileSize, gp.tileSize);
        }
    }
}
