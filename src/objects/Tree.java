package objects;

import main.GamePanel;
import manage.ManageRemovableTile;

import java.awt.*;

/* used as an object tile that player can interact with */
public class Tree extends ManageRemovableTile {
    public Tree(GamePanel gp, int randImage, int worldType){
        super(gp);
        setValues();

        // method selector
        if(worldType == 0){
            images(randImage);
        }
        else if(worldType == 1){
            voidImages(randImage);
        }
        //System.out.println("Tree = new");
    }
    private void setValues(){
        name = "tree";
        alive = true;
        showDamage = true;
        type = 7;       // look: Entity class
        health = 9;
        maxHealth = 9;
        direction = "stationary";
        solidArea = new Rectangle(0, gp.tileSize/2, gp.tileSize, gp.tileSize/2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    private void images(int randImage){
        if(randImage == 0){
            stationary = imageHandler("/images/mushroomTree_full", gp.tileSize, gp.tileSize);
        }
        else if(randImage == 1){
            stationary = imageHandler("/images/mushroomTreeOrange_full", gp.tileSize, gp.tileSize);
        }else{
            stationary = imageHandler("/images/mushroomTreeYellow_full", gp.tileSize, gp.tileSize);
        }

        // for damage to be shown if interacted with
        halfDamage = imageHandler("/images/mushroomTree_half", gp.tileSize, gp.tileSize);
//        finalDamage = imageHandler("", gp.tileSize, gp.tileSize);
    }
    private void voidImages(int randImage){
        if(randImage == 0){
            stationary = imageHandler("/images/mushroomTree_full_void", gp.tileSize, gp.tileSize);
        }else{
            stationary = imageHandler("/images/mushroomTree_full_void2", gp.tileSize, gp.tileSize);
        }
        halfDamage = imageHandler("/images/mushroomTree_half", gp.tileSize, gp.tileSize);
    }
}
