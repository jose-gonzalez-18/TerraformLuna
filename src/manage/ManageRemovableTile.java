package manage;

import entity.Entity;
import main.GamePanel;
import objects.IronOre;
import objects.Stick;

/* manage objects that extend this class */
public class ManageRemovableTile extends Entity {
    public ManageRemovableTile(GamePanel gp){
        super(gp);
    }
    public void update(){
        // damage enemy
        if(hitByPlayer){
            health--;
            hitByPlayer = false;
        }

        /* for texture purposes only, applies to objects
        with the damage textures.*/
        showDamage(this);
//        gp.objInter.disableLayerOverlapping(gp.tree, gp.ironRock);
//        gp.objInter.disableLayerOverlapping(gp.tree, gp.water);

        // kill enemy
        if (health <= 0) {
            //gp.playSoundEffect(4);
            alive = false;
            System.out.println(name + " = null");

            /* drop following items */
            if(name == "tree"){
                stick = new Stick(gp);
                stick.setItemDrop(worldX, worldY, false, this, stick);
                gp.itemDropList.add(stick);
            }
            if(name == "iron_rock"){
                ironOre = new IronOre(gp);
                ironOre.setItemDrop(worldX, worldY, false, this, ironOre);
                gp.itemDropList.add(ironOre);
            }
        }
    }
    // optional, all you have to do is add the 2 textures inside its image() method.
    public void showDamage(Entity user){
        if(user.health <= 4 && user.showDamage){ user.direction = "halfDamage"; }
//        if(user.health <= 1){ user.direction = "finalDamage"; }
    }
}
