package objects;

import main.GamePanel;
import manage.ManageItemDrop;
import java.awt.*;

/* used as an object tile that player can interact with */
public class IronOre extends ManageItemDrop {
    public IronOre(GamePanel gp){
        super(gp);
        setValues();
        images();
        //System.out.println("IronOre = new");
    }
    private void setValues(){
        name = "iron_ore";
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
        stationary = imageHandler("/images/ironOre", gp.tileSize, gp.tileSize);
    }
}
