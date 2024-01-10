package entity;

import main.GamePanel;
import java.awt.*;
import java.util.Random;

/* hostile entity */
public class Astronaut extends Entity{
    public Astronaut(GamePanel gp){
        super(gp);
        setValues();
        images();
        //System.out.println("Russian Astronaut = new");
    }
    private void setValues(){
        name = "russian_astronaut";
        outOfIndex = false;
        type = 2; // types found at Entity class
        speed = 1;
        health = 5;
        maxHealth = 5;
        attract = false;
        alive = true;
        direction = "down"; // by default, changes during game.
        // placement
        solidArea = new Rectangle(5, 10, 30, 35);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void images(){
        // 16X16 SAVED AS PNG BACKGROUND TRANSPARENT
        up1 = imageHandler("/images/astronaut_up1", gp.tileSize, gp.tileSize);
        up2 = imageHandler("/images/astronaut_up2", gp.tileSize, gp.tileSize);
        up3 = imageHandler("/images/astronaut_up1", gp.tileSize, gp.tileSize);
        down1 = imageHandler("/images/astronaut_down1", gp.tileSize, gp.tileSize);
        down2 = imageHandler("/images/astronaut_down2", gp.tileSize, gp.tileSize);
        down3 = imageHandler("/images/astronaut_down1", gp.tileSize, gp.tileSize);
        left1 = imageHandler("/images/astronaut_left1", gp.tileSize, gp.tileSize);
        left2 = imageHandler("/images/astronaut_left2", gp.tileSize, gp.tileSize);
        left3 = imageHandler("/images/astronaut_left3", gp.tileSize, gp.tileSize);
        right1 = imageHandler("/images/astronaut_right1", gp.tileSize, gp.tileSize);
        right2 = imageHandler("/images/astronaut_right2", gp.tileSize, gp.tileSize);
        right3 = imageHandler("/images/astronaut_right3", gp.tileSize, gp.tileSize);
    }
    public void setMovements(){
//        movementTimer++;
//        if(movementTimer == 120){
//            Random random = new Random();
//            int i = random.nextInt(100)+1; // pick any number 1 to 100
//            if (i <= 25) {direction = "up";}
//            if(i > 25) {direction = "down";}
//            if(i > 50 && i<= 75) {direction = "left";}
//            if(i > 75 && i <= 100) {direction = "right";}
//            movementTimer = 0; // reset counter
//        }
        movementTimer++;
        if(attract){
            // if player touches attract area only
            if(gp.player.worldX > worldX){ direction = "right"; }
            if(gp.player.worldX < worldX){ direction = "left"; }
            if(gp.player.worldY > worldY){ direction = "down"; }
            if(gp.player.worldY < worldY){ direction = "up"; }
        }else{
            if (movementTimer > 20) {
                Random random = new Random();
                int i = random.nextInt(100) + 1; // 1 to 100 random number
                if (i <= 25) { direction = "up"; }
                if (i > 25) { direction = "down"; }
                if (i > 50 && i <= 75) { direction = "left"; }
                if (i > 75 && i <= 100) { direction = "right"; }
                movementTimer = 0;
            }
        }
    }
    public void attack(){ }
}
