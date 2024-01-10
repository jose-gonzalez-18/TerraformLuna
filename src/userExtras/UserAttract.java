package userExtras;

import main.GamePanel;
import manage.ManageUsableItem;
import java.awt.*;

/* Players attract area used as an object*/
public class UserAttract extends ManageUsableItem {
    public UserAttract(GamePanel gp){
        super(gp);
        setValues();
        //System.out.println("UserAttract = new");
    }
    private void setValues(){
        name = "userAttractArea";
        type = 5;
        speed = 4;
        equipped = false;
        solidArea = new Rectangle(- (gp.tileSize*2) - 24, - (gp.tileSize*2) - 24, gp.tileSize*6, gp.tileSize*6);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
