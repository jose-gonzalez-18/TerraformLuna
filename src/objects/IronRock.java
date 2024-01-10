package objects;

import main.GamePanel;
import manage.ManageRemovableTile;
import java.awt.*;

/* used as an object tile that player can interact with */
public class IronRock extends ManageRemovableTile {
    public IronRock(GamePanel gp, int randImage){
        super(gp);
        setValues();
        images(randImage);
        //System.out.println("IronRock = new");
    }
    private void setValues(){
        name = "iron_rock";
        alive = true;
        showDamage = false;
        type = 7;    // look: Entity class
        health = 10;
        maxHealth = 10;
        direction = "stationary";
        solidArea = new Rectangle(0, gp.tileSize/2, gp.tileSize, gp.tileSize/2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void images(int randImage){
        if(randImage == 0){
            stationary = imageHandler("/images/ironRock_full", gp.tileSize, gp.tileSize);
        }
        else{
            stationary = imageHandler("/images/ironRock2_full", gp.tileSize, gp.tileSize);
        }
    }
}
