package manage;

import entity.Entity;
import main.GamePanel;

public class ManageUsableItem extends Entity {
    Entity user;
    public ManageUsableItem(GamePanel gp){
        super(gp);
    }
    // reset as new object every time its used
    public void setEquipped(int worldX, int worldY, String direction, boolean equipped, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.equipped = equipped;
        this.user = user;
    }
    public void update(){

        direction = gp.player.direction; // for image counter
        worldX = user.worldX;
        worldY = user.worldY;

        // image buffer
        imageCounter++;
        if (imageCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            imageCounter = 0;
        }

        // attractArea object
        if(type == 5){
            gp.objInter.intersectEntity(this, gp.astronaut);
        }
    }
}