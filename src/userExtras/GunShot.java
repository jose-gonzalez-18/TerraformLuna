package userExtras;

import main.GamePanel;
import main.Keyboard;
import manage.ManageProjectile;

import java.awt.*;

/* used an object for the player to use as shot */
public class GunShot extends ManageProjectile {
    public GunShot(GamePanel gp, Keyboard keyB){
        super(gp, keyB);
        setValues();
        images();
        //System.out.println("GunShot = new");
    }
    private void setValues(){
        name = "gunshot";
        alive = false;
        health = 50;
        speed = 20;
        type = 6;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void images(){
        up1 = imageHandler("/images/gunshot", gp.tileSize, gp.tileSize);
        up2 = imageHandler("/images/gunshot", gp.tileSize, gp.tileSize);
        down1 = imageHandler("/images/gunshot", gp.tileSize, gp.tileSize);
        down2 = imageHandler("/images/gunshot", gp.tileSize, gp.tileSize);
        left1 = imageHandler("/images/gunshot", gp.tileSize, gp.tileSize);
        left2 = imageHandler("/images/gunshot", gp.tileSize, gp.tileSize);
        right1 = imageHandler("/images/gunshot", gp.tileSize, gp.tileSize);
        right2 = imageHandler("/images/gunshot", gp.tileSize, gp.tileSize);
    }
}
