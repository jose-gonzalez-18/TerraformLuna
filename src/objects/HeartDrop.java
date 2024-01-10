package objects;

import main.GamePanel;
import manage.ManageItemDrop;
import java.awt.*;

/* user will drop this as an object item */
public class HeartDrop extends ManageItemDrop {
    public HeartDrop(GamePanel gp){
        super(gp);
        setValues();
        images();
        System.out.println("Item Drop Heart = new");
    }
    private void setValues(){
        name = "heart_drop";
        type = 8;
        speed = 0;
        picked = false;
        health = 1;
        maxHealth = 1;
        direction = "standingAnimation";
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void images(){
        standingAnimation1 = imageHandler("/images/heartHealth1", gp.tileSize, gp.tileSize);
        standingAnimation2 = imageHandler("/images/heartHealth2", gp.tileSize, gp.tileSize);
    }
}
