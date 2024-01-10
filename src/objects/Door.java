package objects;

import manage.ManageEntry;
import main.GamePanel;
import java.awt.*;

public class Door extends ManageEntry {
    public Door(GamePanel gp, int randImage){
        super(gp);
        setValues();
        images(randImage);
    }
    private void setValues(){
        name = "door";
        alive = true;
        type = 0;    // look: Entity class
        entered = false; // until player touches it will set to true
        direction = "stationary";
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void images(int randImage){
        if(randImage == 0){
            stationary = imageHandler("/images/closedDoor_1", gp.tileSize, gp.tileSize);
        }else{
            stationary = imageHandler("/images/closedDoor_2", gp.tileSize, gp.tileSize);
        }
    }
}
