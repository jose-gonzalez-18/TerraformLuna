package intersects;

import entity.Entity;
import main.GamePanel;
import manage.ManageEntry;
import manage.ManageRemovableTile;

public class ObjectIntersect {
    GamePanel gp;
    public ObjectIntersect(GamePanel gp) {
        this.gp = gp;
    }
    /* very dynamic - works with any item drop */
    public void intersectItemDrop(Entity user, Entity itemDrop){
        if(itemDrop != null){
            // get object1 solid area position
            user.solidArea.x = user.worldX + user.solidArea.x;
            user.solidArea.y = user.worldY + user.solidArea.y;
            // get itemDrop solid area position
            itemDrop.solidArea.x = itemDrop.worldX + itemDrop.solidArea.x;
            itemDrop.solidArea.y = itemDrop.worldY + itemDrop.solidArea.y;

            if (user.solidArea.intersects(itemDrop.solidArea)) {
                // heart
                itemDrop.hitByPlayer = true;
                System.out.println( user.name + " picked up: " + itemDrop.name );
            }

            // check if user is userAttractArea, so item can follow player
            if(user.type == 5){
                itemDrop.attract = true;
            }

            // reset position to objects own defaultXY
            user.solidArea.x = user.solidAreaDefaultX;
            user.solidArea.y = user.solidAreaDefaultY;
            itemDrop.solidArea.x = itemDrop.solidAreaDefaultX;
            itemDrop.solidArea.y = itemDrop.solidAreaDefaultY;
        }
    }
    // removable tile intersects removable tile
//    public void disableLayerOverlapping(Entity[] tile, Entity[] tile2){
//        int maxLength;
//        if(tile.length > tile2.length){
//            maxLength = tile.length;
//        }else{
//            maxLength = tile2.length;
//        }
//        for (int x = 0; x < maxLength; x++) {
//            if(tile[x] != null){
//                // get object2 solid area position
//                tile[x].solidArea.x = tile[x].worldX + tile[x].solidArea.x;
//                tile[x].solidArea.y = tile[x].worldY + tile[x].solidArea.y;
//                // get object2 solid area position
//                tile2[x].solidArea.x = tile2[x].worldX + tile2[x].solidArea.x;
//                tile2[x].solidArea.y = tile2[x].worldY + tile2[x].solidArea.y;
//
//                if (tile[x].solidArea.intersects(tile2[x].solidArea)) {
//                    tile2[x].alive = false;
//                    System.out.println(tile2[x] + "set to null");
//                }
//
//                // reset position to objects own defaultXY
//                tile[x].solidArea.x = tile[x].solidAreaDefaultX;
//                tile[x].solidArea.y = tile[x].solidAreaDefaultY;
//                tile2[x].solidArea.x = tile2[x].solidAreaDefaultX;
//                tile2[x].solidArea.y = tile2[x].solidAreaDefaultY;
//            }
//        }
//    }
    // player with Entity
    public void intersectEntity(Entity user, Entity[] target) {
        for (int x = 0; x < target.length; x++) {
            if (target[x] != null) {
                // get player solid area position
                user.solidArea.x = user.worldX + user.solidArea.x;
                user.solidArea.y = user.worldY + user.solidArea.y;
                // get Entity solid area position
                target[x].solidArea.x = target[x].worldX + target[x].solidArea.x;
                target[x].solidArea.y = target[x].worldY + target[x].solidArea.y;

                // user intersects target
                if (user.solidArea.intersects(target[x].solidArea)) {
                    //System.out.println(user.name + " touched " + target[x].name);
                    if (target[x].type == 2 || target[x].type == 3) { // if user intersects with type 2 or 3: hostile
                        //System.out.println(user.name + " is near " + target[x].name);
                        user.hitByEntity = true; // damage user
                    }

                    // check user is the swing marker, so it can damage and remove target[x].type
                    if (user.type == 4 || user.type == 6 && user.alive) { // 4: swing marker, 6: projectile
                        if (target[x].type == 2) { // moving hostile
                            target[x].hitByPlayer = true;
                        }
                        if (target[x].type == 3) { // non-moving hostile
                            target[x].hitByPlayer = true;
                        }
                        if (target[x].type == 7) { // objects
                            target[x].hitByPlayer = true;
                            user.hitByEntity = true; // remove gunshot after impact
                        }
                    }

                    // check if user is playerAttractArea, so enemy can follow
                    if(user.type == 5){
                        if (target[x].type == 2) {
                            target[x].attract = true;
                        }
                        if (target[x].type == 3) {
                            target[x].attract = true;
                        }
                    }
                }

                // reset position to objects own defaultXY
                user.solidArea.x = user.solidAreaDefaultX;
                user.solidArea.y = user.solidAreaDefaultY;
                target[x].solidArea.x = target[x].solidAreaDefaultX;
                target[x].solidArea.y = target[x].solidAreaDefaultY;
            }
        }
    }
    public void intersectRemovableTile(Entity user, ManageRemovableTile[] tile){
        for (int x = 0; x < tile.length; x++) {
            if(tile[x] != null){
                // get object1 solid area position
                user.solidArea.x = user.worldX + user.solidArea.x;
                user.solidArea.y = user.worldY + user.solidArea.y;
                // get object2 solid area position
                tile[x].solidArea.x = tile[x].worldX + tile[x].solidArea.x;
                tile[x].solidArea.y = tile[x].worldY + tile[x].solidArea.y;

                if (user.solidArea.intersects(tile[x].solidArea)) {
                    //System.out.println("User collided with " + tile[x].name);
                    user.collisionOn = true;
                    if(user.direction == "up"){
                        user.worldY = tile[x].worldY + (gp.tileSize - 10);
                    }
                    if(user.direction == "down"){
                        user.worldY = tile[x].worldY - (gp.tileSize/2);
                    }
                    if(user.direction == "left"){
                        user.worldX = tile[x].worldX + (gp.tileSize - 2);
                    }
                    if(user.direction == "right"){
                        user.worldX = tile[x].worldX - (gp.tileSize - 10);
                    }
                }

                // reset position to objects own defaultXY
                user.solidArea.x = user.solidAreaDefaultX;
                user.solidArea.y = user.solidAreaDefaultY;
                tile[x].solidArea.x = tile[x].solidAreaDefaultX;
                tile[x].solidArea.y = tile[x].solidAreaDefaultY;
            }
        }
    }
    public void intersectWater(Entity user, ManageRemovableTile[] tile){
        for (int x = 0; x < tile.length; x++) {
            if(tile[x] != null){
                // get object1 solid area position
                user.solidArea.x = user.worldX + user.solidArea.x;
                user.solidArea.y = user.worldY + user.solidArea.y;
                // get object2 solid area position
                tile[x].solidArea.x = tile[x].worldX + tile[x].solidArea.x;
                tile[x].solidArea.y = tile[x].worldY + tile[x].solidArea.y;

                if (user.solidArea.intersects(tile[x].solidArea)) {
                    user.swim = true;
                }
                // reset position to objects own defaultXY
                user.solidArea.x = user.solidAreaDefaultX;
                user.solidArea.y = user.solidAreaDefaultY;
                tile[x].solidArea.x = tile[x].solidAreaDefaultX;
                tile[x].solidArea.y = tile[x].solidAreaDefaultY;
            }
        }
    }
    public void intersectEntry(Entity user, ManageEntry[] entry){
        for (int x = 0; x < entry.length; x++) {
            if(entry[x] != null){
                // get object1 solid area position
                user.solidArea.x = user.worldX + user.solidArea.x;
                user.solidArea.y = user.worldY + user.solidArea.y;
                // get object2 solid area position
                entry[x].solidArea.x = entry[x].worldX + entry[x].solidArea.x;
                entry[x].solidArea.y = entry[x].worldY + entry[x].solidArea.y;

                if (user.solidArea.intersects(entry[x].solidArea)) {
                    switch (entry[x].name){
                        case "door":
                            break;
                        case "EnterPortal":
                            gp.player.enterEntry = "void";
                            break;
                        case "ReturnPortal":
                            gp.player.enterEntry = "earth";
                            break;
                    }
                    user.swim = true;
                }
                // reset position to objects own defaultXY
                user.solidArea.x = user.solidAreaDefaultX;
                user.solidArea.y = user.solidAreaDefaultY;
                entry[x].solidArea.x = entry[x].solidAreaDefaultX;
                entry[x].solidArea.y = entry[x].solidAreaDefaultY;
            }
        }
    }
}