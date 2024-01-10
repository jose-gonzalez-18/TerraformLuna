package objects;

import main.GamePanel;
import manage.ManageEntry;
import java.awt.*;

public class ReturnPortal extends ManageEntry {
    public ReturnPortal(GamePanel gp){
        super(gp);
        setValues();
        images();
    }
    private void setValues(){
        name = "ReturnPortal";
        alive = true;
        type = 0;    // look: Entity class
        entered = false; // until player touches it will set to true
        direction = "standingAnimation";
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void images(){
        standingAnimation1 = imageHandler("/images/returnPortal1", gp.tileSize, gp.tileSize);
        standingAnimation2 = imageHandler("/images/returnPortal2", gp.tileSize, gp.tileSize);
    }
}
