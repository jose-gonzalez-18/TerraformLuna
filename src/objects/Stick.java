package objects;

import main.GamePanel;
import manage.ManageItemDrop;

import java.awt.*;

/* used as an object tile that player can interact with */
public class Stick extends ManageItemDrop {
    public Stick(GamePanel gp){
        super(gp);
        setValues();
        images();
    }
    private void setValues(){
        name = "stick";
        alive = true;
        speed = 1;
        attract = false;
        type = 8;    // look: Entity class
        health = 1;
        maxHealth = 1;
        picked = false;
        direction = "stationary";
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void images(){
        stationary = imageHandler("/images/stick", gp.tileSize, gp.tileSize);
    }
}
