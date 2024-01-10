package ui;

import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ManageUI {
    GamePanel gp;
    Graphics2D g2;
    Font purisaBfont;

    private final BufferedImage heart, emptyHeart,
            oxygen, emptyOxygen,
            atmosphereLevel, emptyAtmosphereLevel,
            stick, ironOre, inventoryArrow;

    public int selectArrow = 0; // used here and accessed in keyboard class

    public ManageUI(GamePanel gp){
        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/fonts/PurisaBold.ttf");
        try{
            purisaBfont = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch(FontFormatException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        UISprite uiHeart = new UISprite(gp);
        heart = uiHeart.heartSprite;
        emptyHeart = uiHeart.emptyHeartSprite;

        UISprite uiOxygen = new UISprite(gp);
        oxygen = uiOxygen.oxygenSprite;
        emptyOxygen = uiOxygen.emptyOxygenSprite;

        UISprite uiAtmosphere = new UISprite(gp);
        atmosphereLevel = uiAtmosphere.atmosphereLevelSprite;
        emptyAtmosphereLevel = uiAtmosphere.emptyAtmosphereLevelSprite;

        UISprite uiSTick = new UISprite(gp);
        stick = uiSTick.stickSprite;

        UISprite uiIronOre = new UISprite(gp);
        ironOre = uiIronOre.ironOreSprite;

        UISprite uiInventoryArrow = new UISprite(gp);
        inventoryArrow = uiInventoryArrow.inventoryArrowSprite;

    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(purisaBfont);

        // menu state
        if(gp.gameState == gp.menuState){
            drawMainMenu();
        }
        // play state
        if(gp.gameState == gp.playState){
            drawAtmosphereLevel();
            drawHeartBar();
            drawOxygenBar();
            drawPlayerCoordinate();
            drawInventoryOption();
            drawHotBarSlots();
        }
        // item manager state
        if(gp.gameState == gp.itemManagerState){
            drawItemManager();
            // redraw or else it disappears in this state
            drawAtmosphereLevel();
            drawHeartBar();
            drawOxygenBar();
            drawPlayerCoordinate();
            drawInventoryOption();
            drawHotBarSlots();
        }
        // pause state
        if(gp.gameState == gp.pauseState){
            drawPausedScreen();
            // redraw or else it disappears in this state
            drawAtmosphereLevel();
            drawHeartBar();
            drawOxygenBar();
            drawPlayerCoordinate();
            drawInventoryOption();
            drawHotBarSlots();
        }
        // dialogue state
        if(gp.gameState == gp.dialogueState){
            // draw any dialogue methods
        }
        // player death state
        if(gp.gameState == gp.playerDeathState){
            drawPlayerDeath();
            // redraw or else it disappears in this state
            drawAtmosphereLevel();
            drawHeartBar();
            drawOxygenBar();
            drawPlayerCoordinate();
            drawInventoryOption();
            drawHotBarSlots();
        }
    }

    // ui
    private void drawItemManager(){
        Color c = new Color(0,0,50, 150);
        int xPos = gp.tileSize*8;
        int yPos = gp.tileSize;
        g2.setColor(c);
        g2.fillRoundRect(xPos,yPos, gp.tileSize*5, gp.tileSize*10, 3, 3);
        String text = "> Item Manager <";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15F));
        g2.setColor(Color.GREEN);
        g2.drawString(text, xPos + 45, yPos + 20);

        //----------Equipment-------
        text = "Equipment";
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,15F));
        g2.drawString(text, xPos + 20, yPos + 50);

        xPos = xPos + 40;
        yPos = yPos + 80;
        text = "un-equip current item";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,10F));
        g2.drawString(text, xPos, yPos);
        if(selectArrow == 0){
            g2.drawString(">", xPos - 10, yPos);
        }

        text = "item_Axe";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,10F));
        g2.drawString(text, xPos, yPos + 20);
        if(selectArrow == 1){
            g2.drawString(">", xPos - 10, yPos + 20);
        }

        text = "item_RayGun";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,10F));
        g2.drawString(text, xPos, yPos + 40);
        if(selectArrow == 2){
            g2.drawString(">", xPos - 10, yPos + 40);
        }

        //-----------CRAFT--------
        xPos = gp.tileSize*8;
        text = "Craft";
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,15F));
        g2.drawString(text, xPos + 15, yPos + 200);

    }
    private void drawInventoryOption(){
        int slotXPos = gp.screenWidth - (gp.tileSize*3) - 30;
        int slotYPos = gp.screenHeight - (gp.tileSize);
        Color c = new Color(150,150,150, 100);
        //g2.drawImage(inventoryArrow, xPos, yPos, null);
        g2.setColor(c);
        g2.fillRoundRect(slotXPos,
                slotYPos, (gp.tileSize/2)+5,
                (gp.tileSize/2)+5, 180, 180);
        c = new Color(0,200,200);
        g2.setColor(c);
        g2.drawString("I", slotXPos + 10, slotYPos + 20);
    }
    private void drawMainMenu(){
        // do stuff
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 56F));
        String text = "Terraform Luna";
        int x = centerTextXAxis(text);
        int y = gp.tileSize*2;

        // game title
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // Play game
        x = x + gp.tileSize*2;
        y = gp.tileSize*5;
        text = "Play";
        g2.setColor(Color.GREEN);
        g2.drawString(text, x, y);
        if(selectArrow == 0){
            g2.setColor(Color.WHITE);
            g2.drawString(">", x - (gp.tileSize*2), y);
        }

        // Options
        y = gp.tileSize*7;
        text = "Options";
        g2.setColor(Color.GREEN);
        g2.drawString(text, x, y);
        if(selectArrow == 1){
            g2.setColor(Color.WHITE);
            g2.drawString("+", x - (gp.tileSize*2), y);
        }

        // About
        y = gp.tileSize*9;
        text = "About";
        g2.setColor(Color.GREEN);
        g2.drawString(text, x, y);
        if(selectArrow == 2){
            g2.setColor(Color.WHITE);
            g2.drawString("-", x - (gp.tileSize*2), y);
        }
    }
    private void drawPausedScreen(){
        Color c = new Color(0,0,50, 150);
        int xPos = gp.tileSize*5;
        int yPos = gp.tileSize;
        g2.setColor(c);
        g2.fillRoundRect(xPos,yPos, gp.tileSize*5, gp.tileSize*4, 3, 3);
        String text = "> Paused <";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15F));
        g2.setColor(Color.GREEN);
        g2.drawString(text, xPos + 45, yPos + 20);

        xPos = xPos + 40;
        yPos = yPos + 60;
        text = "Keep Playing";
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,15F));
        g2.drawString(text, xPos, yPos);
        if(selectArrow == 0){
            g2.drawString(">", xPos - 15, yPos);
        }

        text = "Options";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,15F));
        g2.drawString(text, xPos, yPos + 30);
        if(selectArrow == 1){
            g2.drawString("-", xPos - 20, yPos + 30);
        }

        text = "Exit";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,15F));
        g2.drawString(text, xPos, yPos + 60);
        if(selectArrow == 2){
            g2.drawString("<", xPos - 15, yPos + 60);
        }
    }
    private void drawPlayerDeath(){
        Color c = new Color(0,0,50, 150);
        int xPos = gp.tileSize*5;
        int yPos = gp.tileSize;
        g2.setColor(c);
        g2.fillRoundRect(xPos,yPos, gp.tileSize*5, gp.tileSize*4, 3, 3);
        String text = "> You Died <";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15F));
        g2.setColor(Color.GREEN);
        g2.drawString(text, xPos + 45, yPos + 20);

        xPos = xPos + 40;
        yPos = yPos + 60;
        text = "Start Over";
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,15F));
        g2.drawString(text, xPos, yPos);
        if(selectArrow == 0){
            g2.drawString(">", xPos - 15, yPos);
        }

        text = "Options";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,15F));
        g2.drawString(text, xPos, yPos + 30);
        if(selectArrow == 1){
            g2.drawString("-", xPos - 20, yPos + 30);
        }

        text = "Exit";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,15F));
        g2.drawString(text, xPos, yPos + 60);
        if(selectArrow == 2){
            g2.drawString("<", xPos - 15, yPos + 60);
        }
    }

    // player extras
    private void drawHeartBar(){
        int xPos = gp.tileSize;
        int yPos = gp.tileSize * 11;

        // draw empty hearts
        for(int x=0; x<gp.player.maxHealth; x++){
            g2.drawImage(emptyHeart, xPos, yPos, null);
            xPos += gp.tileSize - 30;
        }

        xPos = gp.tileSize; // reset - so hearts can load above empty ones
        // draw full hearts
        for(int x=0; x<gp.player.health; x++){
            g2.drawImage(heart, xPos, yPos, null);
            xPos += gp.tileSize - 30;
        }
    }
    private void drawOxygenBar(){
        int xPos = gp.tileSize;
        int yPos = gp.tileSize * 10 + 20;

        // draw empty energy
        for(int x=0; x<gp.player.maxOxygenLevel; x++){
            g2.drawImage(emptyOxygen, xPos, yPos, null);
            xPos += 15;
        }

        xPos = gp.tileSize; // reset - so energy can load above empty ones
        for(int x=0; x<gp.player.oxygenLevel; x++){
            g2.drawImage(oxygen, xPos, yPos, null);
            xPos += 15;
        }
    }

    // player inventory count
    private void drawHotBarSlots(){
        Color c = new Color(150,150,150, 100);
        int xPos = gp.tileSize*4;
        int yPos = gp.screenHeight - (gp.tileSize);
        g2.setColor(c);
        for(int x=0; x<gp.player.playerSlots; x++){
            g2.fillRect(xPos, yPos, gp.tileSize-10, gp.tileSize-10);
            xPos = xPos + 40;
        }

        drawHotBarItems();
    }

    // helper, used inside drawHotBar() method
    private void drawHotBarItems(){
        int slotXPos = gp.tileSize*4;
        int slotYPos = gp.screenHeight - (gp.tileSize);
        int spriteXPos = gp.tileSize*4;
        int spriteYPos = gp.screenHeight - (gp.tileSize - 10);
        Color c = new Color(0, 200, 0);
        g2.setColor(c);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15F));

        // items
        g2.drawImage(stick, slotXPos, slotYPos, null);
        g2.drawString("" + gp.player.stickCount, spriteXPos, spriteYPos);
        g2.drawImage(ironOre, spriteXPos + 40, slotYPos - 10, null);
        g2.drawString("" + gp.player.ironOreCount, spriteXPos + 40, spriteYPos);

//        g2.drawImage(stick, xPos, yPos, null);
//        g2.drawString("" + gp.player.stickCount, xPos, yPos);
//        g2.drawImage(ironOre, gp.screenWidth - (gp.tileSize*3), gp.tileSize, null);
//        g2.drawString("" + gp.player.ironOreCount, gp.screenWidth - (gp.tileSize*2), gp.tileSize*2 - 10);
    }
    // game running stuff
    private void drawAtmosphereLevel(){
        int xPos = gp.screenWidth / 2 - (gp.tileSize / 2);
        int yPos = gp.tileSize - 30;
        Color  c  = new Color(0, 150, 0);
        String text = "atmosphere";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,10F));
        g2.setColor(c);
        g2.drawString(text, gp.screenWidth / 2 - (gp.tileSize / 5), gp.tileSize / 2);

        // draw empty energy
        for(int x=0; x<gp.atmosphere.atmosphereMaxLevel; x++){
            g2.drawImage(emptyAtmosphereLevel, xPos, yPos, null);
            xPos += 15;
        }

        xPos = xPos - 75; // reset - so energy can load above empty ones
        for(int x=0; x<gp.atmosphere.atmosphereLevel; x++){
            g2.drawImage(atmosphereLevel, xPos, yPos, null);
            xPos += 15;
        }
    }
    private void drawPlayerCoordinate(){
        int x = (gp.player.worldX)/42; // x & y get updated relative to player position
        int y = (gp.player.worldY)/42;
        Color  c  = new Color(180, 150, 0);
        g2.setColor(c);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        g2.drawString("X:" + x, 15, gp.tileSize);
        g2.drawString("Y:" + y, gp.tileSize + 20, gp.tileSize);
    }

    // helper methods
    public int centerTextXAxis(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

}
