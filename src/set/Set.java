package set;

import entity.Astronaut;
import main.GamePanel;
import objects.*;

import java.awt.*;
import java.util.Random;

public class Set {
    GamePanel gp;
    public Set(GamePanel gp){
        this.gp = gp;
    }

    /*
        adjust from here
     */
    public void setEarth(){
        gp.setBackground(new Color(0, 130, 60));
        spawnTree();
        spawnMushroom();
        spawnRock();
        spawnWater();
        spawnAstronaut();
        setEnterPortal();
    }
    public void setVoid(){
        gp.setBackground(new Color(100, 0, 0));
        spawnVoidTree();
        spawnRock();
        spawnLava();
        spawnAstronaut(); // todo: lets spawn some monsters now
        setReturnPortal();
    }

    // non-void objects below
    private void spawnTree(){
        int imageSelection = 3; // 0, 1, 2
        for(int x=0; x<gp.tree.length; x++){
            Random rand = new Random();
            int randX = rand.nextInt(gp.maxWorldRow-5)+5;  // from pixel 5 to maxRow
            int randY = rand.nextInt(gp.maxWorldCol-5)+5; // from pixel 5 to maxCol
            int randImage = rand.nextInt(imageSelection);
            gp.tree[x] = new Tree(gp, randImage, 0);
            gp.tree[x].worldX = gp.tileSize*randX;// set to random
            gp.tree[x].worldY = gp.tileSize*randY; // set to random
        }
    }
    private void spawnMushroom(){
        int imageSelection = 2; // 0, 1
        for(int x=0; x<gp.mushroom.length; x++){
            Random rand = new Random();
            int randX = rand.nextInt(gp.maxWorldRow-5)+5;  // from pixel 5 to maxRow
            int randY = rand.nextInt(gp.maxWorldCol-5)+5; // from pixel 5 to maxCol
            int randImage = rand.nextInt(imageSelection);
            gp.mushroom[x] = new Mushroom(gp, randImage);
            gp.mushroom[x].worldX = gp.tileSize*randX;// set to random
            gp.mushroom[x].worldY = gp.tileSize*randY; // set to random
        }
    }
    private void spawnWater(){
        int imageSelection = 2; // 0, 1
        for(int x=0; x<gp.water.length; x++){
            Random rand = new Random();
            int randX = rand.nextInt(gp.maxWorldRow-5)+5;  // from pixel 5 to maxRow
            int randY = rand.nextInt(gp.maxWorldCol-5)+5; // from pixel 5 to maxCol
            int randColor = rand.nextInt(imageSelection);
            gp.water[x] = new Water(gp, randColor);
            gp.water[x].worldX = gp.tileSize*randX;// set to random
            gp.water[x].worldY = gp.tileSize*randY; // set to random
        }
    }

    // void objects below
    public void spawnAstronaut(){
        for(int x = 0; x<gp.astronaut.length; x++){
            Random rand = new Random();
            int randX = rand.nextInt(gp.maxWorldRow-5)+5;  // from pixel 5 to maxRow
            int randY = rand.nextInt(gp.maxWorldCol-5)+5; // from pixel 5 to maxCol
            gp.astronaut[x] = new Astronaut(gp);
            gp.astronaut[x].worldX = gp.tileSize*randX;// set to random
            gp.astronaut[x].worldY = gp.tileSize*randY; // set to random
            //System.out.println("Russian Astronaut set from SetEntity class");
        }
    }
    public void spawnVoidTree(){
        int imageSelection = 2; // 0, 1
        for(int x=0; x<gp.tree.length; x++){
            Random rand = new Random();
            int randX = rand.nextInt(gp.maxWorldRow-5)+5;  // from pixel 5 to maxRow
            int randY = rand.nextInt(gp.maxWorldCol-5)+5; // from pixel 5 to maxCol
            int randImage = rand.nextInt(imageSelection);
            gp.tree[x] = new Tree(gp, randImage, 1);
            gp.tree[x].worldX = gp.tileSize*randX;// set to random
            gp.tree[x].worldY = gp.tileSize*randY; // set to random
        }
    }
    public void spawnLava(){
    }

    // shared objects below
    public void setEnterPortal(){
        for(int x = 0; x<gp.enterPortal.length; x++){
            Random rand = new Random();
            int randX = rand.nextInt(gp.maxWorldRow-5)+5;  // from pixel 5 to maxRow
            int randY = rand.nextInt(gp.maxWorldCol-5)+5; // from pixel 5 to maxCol\
            gp.enterPortal[x] = new EnterPortal(gp);
            gp.enterPortal[x].worldX = gp.tileSize*randX;// set to random
            gp.enterPortal[x].worldY = gp.tileSize*randY; // set to random
            //System.out.println("EnterPortal set from Set class at " + randX + ":" + randY);
        }
    }
    public void setReturnPortal(){
        for(int x = 0; x<gp.returnPortal.length; x++){
            Random rand = new Random();
            int randX = rand.nextInt(gp.maxWorldRow-5)+5;  // from pixel 5 to maxRow
            int randY = rand.nextInt(gp.maxWorldCol-5)+5; // from pixel 5 to maxCol\
            gp.returnPortal[x] = new ReturnPortal(gp);
            gp.returnPortal[x].worldX = gp.tileSize*randX;// set to random
            gp.returnPortal[x].worldY = gp.tileSize*randY; // set to random
            //System.out.println("EnterPortal set from Set class at " + randX + ":" + randY);
        }
    }
    private void spawnRock(){
        int imageSelection = 2; // 0, 1
        for(int x=0; x<gp.ironRock.length; x++){
            Random rand = new Random();
            int randX = rand.nextInt(gp.maxWorldRow-5)+5;  // from pixel 5 to maxRow
            int randY = rand.nextInt(gp.maxWorldCol-5)+5; // from pixel 5 to maxCol
            int randImage = rand.nextInt(imageSelection);
            gp.ironRock[x] = new IronRock(gp, randImage);
            gp.ironRock[x].worldX = gp.tileSize*randX;// set to random
            gp.ironRock[x].worldY = gp.tileSize*randY; // set to random
        }
    }
    public void setDoorEntry(){
        for(int x=0; x<gp.door.length; x++){
            int imageSelection = 2;
            Random rand = new Random();
            int randX = rand.nextInt(gp.maxWorldRow-5)+5;  // from pixel 5 to maxRow
            int randY = rand.nextInt(gp.maxWorldCol-5)+5; // from pixel 5 to maxCol
            int randImage = rand.nextInt(imageSelection);
            gp.door[x] = new Door(gp, randImage);
            gp.door[x].worldX = gp.tileSize*randX;// set to random
            gp.door[x].worldY = gp.tileSize*randY; // set to random
            //System.out.println( "Door set from Set class at " + randX + ":" + randY);
        }
    }
}
