package manage;

import entity.Entity;
import main.GamePanel;

/* Manage objects that extend ManageItemDrop */
public class ManageItemDrop extends Entity {
    Entity user;
    public ManageItemDrop(GamePanel gp){
        super(gp);
    }
    // reset as new object every time its used
    public void setItemDrop(int worldX, int worldY, boolean picked, Entity user, ManageItemDrop itemDrop){
        this.worldX = worldX;
        this.worldY = worldY;
        /*automatically grabs the direction/animation from (, itemDrop) so it never fails*/
        itemDrop.direction = direction;
        this.picked = picked;
        this.user = user;
    }
    public void update() {
        // item drops where user sets null
        worldX = user.worldX;
        worldY = user.worldY;

        // without timer the heart drop will heal more than 1 health
        minuteSleepCounter++;
        if (minuteSleepCounter > 5) {
            gp.objInter.intersectItemDrop(gp.player, this);
            minuteSleepCounter = 0;
        }

        // remove
        if(hitByPlayer){
            picked = true;
            switch(name){
                case "stick": gp.player.stickCount++; break;
                case "iron_ore": gp.player.ironOreCount++; break;
                case "heart_drop": gp.player.health++; break;
            }
            //hitByItemDrop = false;
            System.out.println(name + " = null");
        }

        if(direction == "standingAnimation"){
            imageCounter++;
            if (imageCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                imageCounter = 0;
            }
        }else{
            spriteNum = 1;
        }

    }
}