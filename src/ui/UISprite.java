package ui;

import main.GamePanel;
import main.HandyTools;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UISprite {
    public GamePanel gp;
    public BufferedImage stickSprite, ironOreSprite;
    public BufferedImage atmosphereLevelSprite, emptyAtmosphereLevelSprite;
    public BufferedImage oxygenSprite, emptyOxygenSprite;
    public BufferedImage heartSprite, emptyHeartSprite;
    public BufferedImage inventoryArrowSprite;
    public UISprite(GamePanel gp){
        this.gp = gp;
        images();
    }
    public void images(){
        stickSprite = setup("/images/stick", gp.tileSize-10, gp.tileSize-10);
        atmosphereLevelSprite = setup("/images/atmosphereSprite_green1", gp.tileSize, gp.tileSize);
        emptyAtmosphereLevelSprite = setup("/images/atmosphereSprite_empty", gp.tileSize, gp.tileSize);
        oxygenSprite = setup("/images/oxygenSprite_full", gp.tileSize, gp.tileSize);
        emptyOxygenSprite = setup("/images/oxygenSprite_full", gp.tileSize, gp.tileSize);
        heartSprite = setup("/images/heartSprite_full", gp.tileSize, gp.tileSize);
        emptyHeartSprite = setup("/images/heartSprite_empty", gp.tileSize, gp.tileSize);
        ironOreSprite = setup("/images/ironOre", gp.tileSize - 10, gp.tileSize - 10);
        inventoryArrowSprite = setup("/images/inventoryArrowUI", gp.tileSize, gp.tileSize);
    }
    // helper - used for objects
    public BufferedImage setup(String imagePath, int width, int height){
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
