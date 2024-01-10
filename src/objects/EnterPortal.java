package objects;

import main.GamePanel;
import manage.ManageEntry;
import java.awt.*;

public class EnterPortal extends ManageEntry {
    public EnterPortal(GamePanel gp){
        super(gp);
        setValues();
        images();
    }
    private void setValues(){
        name = "EnterPortal";
        alive = true;
        type = 0;    // look: Entity class
        entered = false; // until player touches it will set to true
        direction = "standingAnimation";
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void images(){
        standingAnimation1 = imageHandler("/images/portal1", gp.tileSize, gp.tileSize);
        standingAnimation2 = imageHandler("/images/portal2", gp.tileSize, gp.tileSize);
    }
}
