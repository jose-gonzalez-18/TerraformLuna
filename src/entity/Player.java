package entity;

import main.GamePanel;
import main.Keyboard;
import userExtras.GunShot;
import userExtras.UserAttract;
import userExtras.Swing;
import java.awt.*;
import java.awt.image.BufferedImage;

/* Main character */
public class Player extends Entity {
    Keyboard keyB;
    // lock player to screen
    public final int playerLockedX;
    public final int playerLockedY;

    public Player(GamePanel gp, Keyboard keyB){
        super(gp);
        this.keyB = keyB;
        // player position relative to screen borders
        playerLockedX = gp.screenWidth / 2 - (gp.tileSize / 2);
        playerLockedY = gp.screenHeight / 2 - (gp.tileSize / 2);
        setValues();
        images();
        attackImages();
        shootImages();
        swimmingImages();
        holdingAxeImages();
    }
    private void setValues(){
        name = "astro";
        alive = true;
        type = 1;
        speed = 2;
        swim = false;
        oxygenLevel = 5;
        maxOxygenLevel = 5;
        health = 5;
        maxHealth = 5;
        vulnerable = true;    // always vulnerable unless waiting for timer
        direction = "down";
        punch = true;
        equipAxe = false;
        equipRayGun = false;
        enterEntry = "default"; // look at entity class for options

        // inventory
        playerSlots = 10;
        stickCount = 0;
        ironOreCount = 0;

        // placement
        solidArea = new Rectangle(5, 10, 30, 35);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        worldX = (gp.maxWorldCol * gp.tileSize) / 2;
        worldY = (gp.maxWorldRow * gp.tileSize) / 2;

        // extras
        gunShot = new GunShot(gp, keyB);
        swing = new Swing(gp, keyB);
        userAttractArea = new UserAttract(gp);
    }
    private void images(){
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
    private void attackImages(){
        attackUp = imageHandler("/images/astronaut_shootUp1", gp.tileSize, gp.tileSize);
        attackDown = imageHandler("/images/astronaut_attackingDown1", gp.tileSize, gp.tileSize);
        attackLeft = imageHandler("/images/astronaut_attackingLeft1", gp.tileSize, gp.tileSize);
        attackRight = imageHandler("/images/astronaut_attackingRight1", gp.tileSize, gp.tileSize);
    }
    private void shootImages(){
        shootingUp = imageHandler("/images/astronaut_shootUp1", gp.tileSize, gp.tileSize);
        shootingDown = imageHandler("/images/astronaut_shootDown1", gp.tileSize, gp.tileSize);
        shootingLeft = imageHandler("/images/astronaut_shootLeft1", gp.tileSize, gp.tileSize);
        shootingRight = imageHandler("/images/astronaut_shootRight1", gp.tileSize, gp.tileSize);
    }
    private void holdingAxeImages(){
        choppingAxeLeft = imageHandler("/images/astronaut_chopLeft1", gp.tileSize, gp.tileSize);
        choppingAxeRight = imageHandler("/images/astronaut_chopRight1", gp.tileSize, gp.tileSize);
        choppingAxeDown = imageHandler("/images/astronaut_chopDown1", gp.tileSize, gp.tileSize);
        choppingAxeUp = imageHandler("/images/astronaut_chopUp1", gp.tileSize, gp.tileSize);
    }
    private void swimmingImages(){
        //
        swimming = imageHandler("/images/astronaut_swimming", gp.tileSize, gp.tileSize);
    }
    private void damageObjects(int inputPower){
        // create
        swing = new Swing(gp, gp.keyboard);
        swing.alive = true;
        swing.power = inputPower;
        swing.direction = direction;
        // location
        if(gp.player.direction == "up"){
            int xPos = gp.player.worldX;
            int yPos = gp.player.worldY - gp.tileSize;
            swing.worldX = xPos;
            swing.worldY = yPos;
        }
        if(gp.player.direction == "down"){
            int xPos = gp.player.worldX;
            int yPos = gp.player.worldY + gp.tileSize;
            swing.worldX = xPos;
            swing.worldY = yPos;
        }
        if(gp.player.direction == "left"){
            int xPos = gp.player.worldX - gp.tileSize;
            int yPos = gp.player.worldY;
            swing.worldX = xPos;
            swing.worldY = yPos;
        }
        if(gp.player.direction == "right"){
            int xPos = gp.player.worldX + gp.tileSize;
            int yPos = gp.player.worldY;
            swing.worldX = xPos;
            swing.worldY = yPos;
        }
        // add & draw
        gp.swingList.add(swing);
    }
    public void update(){
        // check direction only
        if (keyB.upPressed || keyB.downPressed || keyB.leftPressed || keyB.rightPressed) {
            if (keyB.upPressed) {
                direction = "up";
            } else if (keyB.downPressed) {
                direction = "down";
            } else if (keyB.leftPressed) {
                direction = "left";
            } else if (keyB.rightPressed) {
                direction = "right";
            }
            // manage collision
            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            // change image buffer speed here
            imageCounter++;
            if (imageCounter > 8) {
                if (spriteNum == 1) { spriteNum = 2;
                } else if (spriteNum == 2) { spriteNum = 3;
                } else if (spriteNum == 3){ spriteNum = 1; }
                imageCounter = 0;
            }
        }
        collisionOn = false;
        swim = false;

        // in the future maybe combine these all together for memory saving: at least try
        //gp.objInter.intersectTile(this);
        gp.objInter.intersectRemovableTile(this, gp.tree);
        gp.objInter.intersectRemovableTile(this, gp.ironRock);
        gp.objInter.intersectWater(this, gp.water); // so player can swim
        gp.objInter.intersectEntry(this, gp.enterPortal);
        gp.objInter.intersectEntry(this, gp.returnPortal);
        /* Sleep the amount of time a player can get damaged by enemy */
        minuteSleepCounter++;
        if (minuteSleepCounter > 40) {
            // entity
            gp.objInter.intersectEntity(this, gp.astronaut);
            minuteSleepCounter = 0;
        }

        // player damaged if touched by object2
        if (hitByEntity) { // look at ObjectIntersect class
            health--;
            //gp.playSoundEffect(5);
            hitByEntity = false;
        }

        // do not allow more than maxHealth
        if(health >= 5){
            health = 5;
        }
        if(health < 1){ // for testing purposes only
            health = 5;
        }

//        // remove
//        if(health <= 0){
//            alive = false;
//        }

        // ON ALWAYS / attract area
        if(alive){
            // create
            userAttractArea = new UserAttract(gp);
            userAttractArea.setEquipped(worldX, worldY, direction, true, this);
            // add & draw
            gp.userAttractList.add(userAttractArea);
        }

        // allow only one shot at a time
        if(keyB.shooting && !gunShot.alive){
            gunShot = new GunShot(gp, keyB);
            gunShot.setUse(worldX, worldY, direction, true, gunShot.health,this);
            gp.projectileList.add(gunShot);
        }

        // player chopping attacking: object uses swing to damage
        if(keyB.chopping && !swing.alive && !keyB.shooting){
            System.out.println(" player chopped ");
            damageObjects(3);
        }
        // player swing attacking: object uses swing to damage
        if(keyB.punching && !swing.alive && !keyB.shooting){
            System.out.println(" player attacked ");
            damageObjects(1);
        }
    }
    public void draw(Graphics2D g2){
        // update player images
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.playerLockedX;
        int screenY = worldY - gp.player.worldY + gp.player.playerLockedY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.playerLockedX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.playerLockedX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.playerLockedY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.playerLockedY){

            // player move
            switch (direction){
                case "up":
                    if(keyB.punching){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = attackUp; }
                    }
                    else if(keyB.shooting){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = shootingUp; }
                    }
                    else if(keyB.chopping){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = choppingAxeUp; }
                    }
                    else if(swim){ // object intersect changes this to true
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = swimming; }
                    }
                    // not attacking
                    else {
                        if (spriteNum == 1) { image = up1; }
                        if (spriteNum == 2) { image = up2; }
                        if (spriteNum == 3) { image = up3; }
                    }
                    break;
                case "down":
                    if(keyB.punching){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = attackDown; }
                    }
                    else if(keyB.shooting){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = shootingDown; }
                    }
                    else if(keyB.chopping){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = choppingAxeDown; }
                    }
                    else if(swim){ // object intersect changes this to true
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = swimming; }
                    }
                    // not attacking
                    else {
                        if (spriteNum == 1) { image = down1; }
                        if (spriteNum == 2) { image = down2; }
                        if (spriteNum == 3) { image = down3; }
                    }
                    break;
                case "left":
                    if(keyB.punching){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = attackLeft; }
                    }
                    else if(keyB.shooting){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = shootingLeft; }
                    }
                    else if(keyB.chopping){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = choppingAxeLeft; }
                    }
                    else if(swim){ // object intersect changes this to true
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = swimming; }
                    }
                    // not attacking
                    else {
                        if (spriteNum == 1) { image = left1; }
                        if (spriteNum == 2) { image = left2; }
                        if (spriteNum == 3) { image = left3; }
                    }
                    break;
                case "right":
                    if(keyB.punching){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = attackRight; }
                    }
                    else if(keyB.shooting){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = shootingRight; }
                    }
                    else if(keyB.chopping){
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = choppingAxeRight; }
                    }
                    else if(swim){ // object intersect changes this to true
                        if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) { image = swimming; }
                    }
                    // not attacking
                    else{
                        if (spriteNum == 1) { image = right1; }
                        if (spriteNum == 2) { image = right2; }
                        if (spriteNum == 3) { image = right3; }
                    }
                    break;
            }
            // dim if damaged
            if(hitByEntity){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            // reset color
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}
