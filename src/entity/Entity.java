package entity;

import main.GamePanel;
import main.HandyTools;
import manage.ManageItemDrop;
import manage.ManageProjectile;
import manage.ManageUsableItem;
import objects.HeartDrop;
import userExtras.Swing;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    public GamePanel gp;
    public Entity(GamePanel gp){
        this.gp = gp;
    }

    // solid area
    public Rectangle solidArea = new Rectangle();
    public ManageProjectile gunShot;
    public ManageUsableItem userAttractArea;
    public ManageItemDrop stick;
    public ManageItemDrop ironOre;
    public ManageItemDrop heartDrop;
    public Swing swing;

    // buffered images
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3,
            standingAnimation1, standingAnimation2, stationary, halfDamage, finalDamage,
            attackUp, attackDown, attackLeft, attackRight,
            shootingUp, shootingDown, shootingLeft, shootingRight,
            choppingAxeLeft, choppingAxeRight, choppingAxeDown, choppingAxeUp,
            swimming;

    public boolean collisionOn = false;
    public int spriteNum = 1;

    // default properties
    public boolean outOfIndex;      // any entity out of drawn screen will not be updated: memory saver
    public String name;
    public boolean alive;
    public int speed;
    public int oxygenLevel;
    public int maxOxygenLevel;
    public double health;
    public int maxHealth;
    public boolean showDamage;       // damage texture will change if true
    public int power;                // used for player damageObject()
    public boolean vulnerable;       // user can be damaged if (true)
    public boolean punch;            // user can damage enemy if (true)
    public boolean attract;          // user will attract enemy if (true)
    public boolean equipAxe;         // user sprite will change if (true)
    public boolean swim;             // player images change if (true || false)
    public boolean equipRayGun;
    public String direction;
    public int solidAreaDefaultX, solidAreaDefaultY; // placement
    public int worldX, worldY;
    public boolean equipped;         // for user to equip items
    public boolean picked;           // item can be picked by user
    public int stickCount = 0;
    public int ironOreCount = 0;
    public boolean entered;         // for portals/doors
    public int playerSlots;
    public String enterEntry;       /* default(not entered anything yet), void(entered void), earth(left void)*/
    public int type;
                    /*  TYPES
                        0: doors / portals
                        1: player
                        2: moving hostile
                        3: stationary hostile
                        4: swing
                        5: user.userAttractArea
                        6: projectile
                        7: removable object tile
                        8: item drops
                     */

    // player health
    public boolean hitByEntity = false;
    public boolean hitByPlayer = false;
    //public boolean hitByItemDrop = false;

    // counters
    public int movementTimer = 0;
    public int imageCounter = 0;
    public int minuteSleepCounter = 0;
    public int attractTimer = 0;

    public void attack(){ }
    public void setMovements(){ }
    public void update(){
        setMovements();
        if(!collisionOn){
            switch(direction){
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
        collisionOn = false;
        // change image buffer speed here
        imageCounter++;
        if (imageCounter > 8) {
            if (spriteNum == 1) { spriteNum = 2; }
            else if (spriteNum == 2) { spriteNum = 3; }
            else if (spriteNum == 3){ spriteNum = 1; }
            imageCounter = 0;
        }

        // damage enemy
        if(hitByPlayer){
            health--;
            hitByPlayer = false;
        }

//        // out of index: memory saver
//        if(worldX > gp.screenWidth || worldX < 0 || worldY > gp.screenHeight || worldY < 0){
//            outOfIndex = true;
//        }

        // kill enemy
        if (health <= 0) {
            //gp.playSoundEffect(4);
            alive = false;
            heartDrop = new HeartDrop(gp);
            heartDrop.setItemDrop(worldX, worldY, false, this, heartDrop);
            gp.itemDropList.add(heartDrop);
            System.out.println(name + " = null");
        }

        // reset object[x].attract after player walks away
        attractTimer++;
        if(attractTimer > 10) {
            if (attract) { attract = false; }
            attractTimer = 0;
        }

        // do not allow entity to leave worldXY
        //gp.objInter.intersectTile(this);
        gp.objInter.intersectRemovableTile(this, gp.tree);
        gp.objInter.intersectRemovableTile(this, gp.ironRock);
        gp.objInter.intersectWater(this, gp.water); // so player can swim
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.playerLockedX;
        int screenY = worldY - gp.player.worldY + gp.player.playerLockedY;
        // draw within world size
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.playerLockedX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.playerLockedX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.playerLockedY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.playerLockedY) {
            // move
            switch (direction) {
                case "up":
                    if (spriteNum == 1) { image = up1; }
                    if (spriteNum == 2) { image = up2; }
                    if (spriteNum == 3) { image = up3; }
                    break;
                case "down":
                    if (spriteNum == 1) { image = down1; }
                    if (spriteNum == 2) { image = down2; }
                    if (spriteNum == 3) { image = down3; }
                    break;
                case "left":
                    if (spriteNum == 1) { image = left1; }
                    if (spriteNum == 2) { image = left2; }
                    if (spriteNum == 3) { image = left3; }
                    break;
                case "right":
                    if (spriteNum == 1) { image = right1; }
                    if (spriteNum == 2) { image = right2; }
                    if (spriteNum == 3) { image = right3; }
                    break;
                case "standingAnimation": // animate between 2 images
                    if (spriteNum == 1) { image = standingAnimation1; }
                    if (spriteNum == 2) { image = standingAnimation2; }
                    break;
                case "stationary": // no animation
                    if(spriteNum == 1) { image = stationary; } // optional for non-moving items
                    break;
                case "halfDamage":
                    if(spriteNum == 1) { image = halfDamage; } // optional for damage to be shown
                    break;
                case "finalDamage":
                    if(spriteNum == 1) { image = finalDamage; } // optional for damage to be shown
                    break;
            }
            // dim if damaged
            if(hitByPlayer){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            // reset color
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            //attract area
//            if(type == 5){
//                g2.setColor(Color.WHITE);
//                g2.drawRect(screenX - (gp.tileSize*2) - 24, screenY - (gp.tileSize*2) - 24, gp.tileSize*6, gp.tileSize*6);
//            }
        } // end draw
    }
    // helper - used for objects
    public BufferedImage imageHandler(String imagePath, int width, int height){
        HandyTools uTool = new HandyTools();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
