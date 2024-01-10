package userExtras;

import entity.Entity;
import main.GamePanel;
import main.Keyboard;
import java.awt.*;

/* used an object for the player */
public class Swing extends Entity {
    Keyboard keyB;
    public Swing(GamePanel gp, Keyboard keyB){ // used as a sprite
        super(gp);
        this.keyB = keyB;
        setValues();
        setImage();
        //System.out.println("Swing = new");
    }
    private void setValues(){
        name = "swing";
        type = 4;
        power = 2;
        alive = false;
        health = 5;
        maxHealth = 5;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void setImage(){
        up1 = imageHandler("/images/swingMarker_up1", gp.tileSize, gp.tileSize);
        up2 = imageHandler("/images/swingMarker_up2", gp.tileSize, gp.tileSize);
        down1 = imageHandler("/images/swingMarker_down1", gp.tileSize, gp.tileSize);
        down2 = imageHandler("/images/swingMarker_down2", gp.tileSize, gp.tileSize);
        left1 = imageHandler("/images/swingMarker_left1", gp.tileSize, gp.tileSize);
        left2 = imageHandler("/images/swingMarker_left2", gp.tileSize, gp.tileSize);
        right1 = imageHandler("/images/swingMarker_right1", gp.tileSize, gp.tileSize);
        right2 = imageHandler("/images/swingMarker_right2", gp.tileSize, gp.tileSize);
    }
    public void update(){

        // remove
        health--;
        if(health <= 0){
            System.out.println("Swing Marker .alive = false, set to null");
            alive = false;
        }

        // image buffer
        imageCounter++;
        if (imageCounter > 3) {
            if (spriteNum == 1) { spriteNum = 2; }
            else if (spriteNum == 2) { spriteNum = 1; }
            imageCounter = 0;
        }

        //gp.objInter.intersectTile(this);
        /* Sleep the amount of time swing can damaged enemy */
        minuteSleepCounter++;
        if(minuteSleepCounter > 2) { // todo: fix sleepCounter
            // entities
            gp.objInter.intersectEntity(this, gp.astronaut);
            gp.objInter.intersectEntity(this, gp.mushroom);
//            gp.objInter.intersectEntity(this, gp.tree); // requires more punches
            // removable tile
            if(keyB.chopping){ // only damage these if holding axe
                gp.objInter.intersectEntity(this, gp.tree);
                gp.objInter.intersectEntity(this, gp.ironRock);
            }
            minuteSleepCounter = 0;
        }
    }
}
