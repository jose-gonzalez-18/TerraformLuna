package manage;

import entity.Entity;
import main.GamePanel;

public class ManageEntry extends Entity {
    public ManageEntry(GamePanel gp){ super(gp);}
    public void update(){
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
