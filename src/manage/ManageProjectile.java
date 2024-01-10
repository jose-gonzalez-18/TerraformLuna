package manage;

import entity.Entity;
import main.GamePanel;
import main.Keyboard;

/* Manage objects that extend ManageProjectile */
public class ManageProjectile extends Entity {

    Entity user;
    Keyboard keyB;
    public ManageProjectile(GamePanel gp, Keyboard keyB){
        super(gp);
        this.keyB = keyB;
    }
    // reset as new object every time its used
    public void setUse(int worldX, int worldY, String direction, boolean alive, double health, Entity user){
        this.worldX = worldX;
        this.worldY = worldY - 5; // - 5 so bullet can be right on gun XY
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.health = health;
    }
    public void update(){
        switch(direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        // remove
        health--;
        if(health <= 0){
            System.out.println( name + " .alive = false, set to null");
            alive = false;
        }

        // remove if touched by entity or tile, look at ObjectIntersect class
        if (hitByEntity) {
            alive = false;
            System.out.println(name + " .alive = false, set to null");
        }

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

        // always for tiles
        //gp.objInter.intersectTile(this);

        // object intersection
        /* Sleep the amount of time a player can get damaged by enemy */
        minuteSleepCounter++;
        if (minuteSleepCounter > 3) {
            // creatures
            gp.objInter.intersectEntity(this, gp.astronaut);

            // removable tile objects
//            gp.objInter.intersectEntity(this, gp.tree);
//            gp.objInter.intersectEntity(this, gp.mushroom);
//            gp.objInter.intersectEntity(this, gp.ironRock);
            minuteSleepCounter = 0;
        }
    }
}
