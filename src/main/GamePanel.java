package main;

import atmosphere.ManageAtmosphere;
import entity.Entity;
import entity.Player;
import intersects.ObjectIntersect;
import manage.ManageTile;
import objects.*;
import set.Set;
import ui.ManageUI;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int finalTileSize = 16; // 16x16
    final int scale = 3;
    public final int tileSize = finalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // Game
    Thread gameThread;
    public int gameState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int menuState = 3;
    public final int itemManagerState = 4;
    public final int dialogueState = 5;
    public final int playerDeathState = 6;
    public final int aboutState = 7;
    public final int optionState = 8;

    // handle game object generator
    public boolean buildingTerrain = true;

    public String writePath = "src/main/map1.txt"; // used to generate terrain
    public String path = "/main/map1.txt";         // used to read terrain

    // game handler objects
    public ManageUI ui = new ManageUI(this);
    public ManageTile manageTile = new ManageTile(this, path);
    public Set set = new Set(this);
    public Keyboard keyboard = new Keyboard(this);
    public ObjectIntersect objInter = new ObjectIntersect(this);
    public ManageAtmosphere atmosphere = new ManageAtmosphere(this);

    // entity objects
    public Player player = new Player(this, keyboard);
    public Entity[] astronaut = new Entity[15];

    // removable tile objects
    public Tree[] tree = new Tree[100];
    public Water[] water = new Water[10];
    public Mushroom[] mushroom = new Mushroom[50];
    public IronRock[] ironRock = new IronRock[50];
    public EnterPortal[] enterPortal = new EnterPortal[10];
    public ReturnPortal[] returnPortal = new ReturnPortal[10];
    public Door[] door = new Door[1];

    // lists
    public ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> userAttractList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> swingList = new ArrayList<>();
    public ArrayList<Entity> equippedItemList = new ArrayList<>();
    public ArrayList<Entity> itemDropList = new ArrayList<>();
    /* water should not get sorted so do not add to entity list */
    public ArrayList<Entity> waterTileList = new ArrayList<>(); // EnterPortal will be added to this

    // used inside main
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0, 130, 60));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboard);
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }                   // instantiate thread
    public void levelSetup(){
        gameState = menuState;
    }   // pick starting (gameState)
    public void setNull(){
        // player
        if(player != null){
            if (player.alive){ player.update();}
            else{ player = null; }
        }
        Arrays.fill(astronaut, null);
        Arrays.fill(tree, null);
        Arrays.fill(water, null);
        Arrays.fill(mushroom, null);
        Arrays.fill(ironRock, null);
        Arrays.fill(door, null);
        Arrays.fill(enterPortal, null);
        Arrays.fill(returnPortal, null);
    }                            // remove/null
    public void updateEach(){
        // player
        if(player != null){
            if (player.alive){ player.update();}
            else{ player = null; }
        }

        // russian astronaut
        for (int x = 0; x< astronaut.length; x++) {
            if (astronaut[x] != null) {
                if(astronaut[x].alive){
                    astronaut[x].update();
                }
                else{ astronaut[x] = null; }
            }
        }

        // tree
        for (int x=0; x<tree.length; x++) {
            if (tree[x] != null) {
                if(tree[x].alive){
                    tree[x].update();
                }else{ tree[x] = null; }
            }
        }

        // water
        for (int x=0; x<water.length; x++) {
            if (water[x] != null) {
                if(water[x].alive){
                    water[x].update();
                }else{ water[x] = null; }
            }
        }

        // mushroom
        for (int x=0; x<mushroom.length; x++) {
            if (mushroom[x] != null) {
                if(mushroom[x].alive){
                    mushroom[x].update();
                }else{ mushroom[x] = null; }
            }
        }

        // iron rock
        for (int x=0; x<ironRock.length; x++) {
            if (ironRock[x] != null) {
                if(ironRock[x].alive){
                    ironRock[x].update();
                }else{ ironRock[x] = null; }
            }
        }

        // door
        for (int x=0; x<door.length; x++) {
            if (door[x] != null) {
                if(door[x].alive){
                    door[x].update();
                }else{ door[x] = null; }
            }
        }

        // EnterPortal
        for (int x = 0; x< enterPortal.length; x++) {
            if (enterPortal[x] != null) {
                if(enterPortal[x].alive){
                    enterPortal[x].update();
                }else{ enterPortal[x] = null; }
            }
        }

        // ReturnPortal
        for (int x = 0; x< returnPortal.length; x++) {
            if (returnPortal[x] != null) {
                if(returnPortal[x].alive){
                    returnPortal[x].update();
                }else{ returnPortal[x] = null; }
            }
        }

        // swing
        for(int i=0; i<swingList.size(); i++){
            if(swingList.get(i) != null){
                if(swingList.get(i).alive){
                    swingList.get(i).update();
                }
                if(!swingList.get(i).alive){
                    swingList.remove(i);
                }
            }
        }

        // gunshot
        for(int i=0; i<projectileList.size(); i++){
            if(projectileList.get(i) != null){
                if(projectileList.get(i).alive){ // if (true) update
                    projectileList.get(i).update();
                }
                if(!projectileList.get(i).alive){
                    projectileList.remove(i);
                }
            }
        }

//            // portals and doors: todo check id this is needed
//            for(int i=0; i<manageEntryList.size(); i++){
//                if(manageEntryList.get(i) != null){
//                    if(manageEntryList.get(i).alive){ // if (true) update
//                        manageEntryList.get(i).update();
//                    }
//                    if(!manageEntryList.get(i).alive){
//                        manageEntryList.remove(i);
//                    }
//                }
//            }

        // any player item
        for(int i=0; i<equippedItemList.size(); i++){
            if(equippedItemList.get(i) != null){
                if(equippedItemList.get(i).equipped){ // if (true) update
                    equippedItemList.get(i).update();
                }
                if(!equippedItemList.get(i).equipped){
                    equippedItemList.remove(i);
                }
            }
        }

        // item drop list
        for(int i=0; i<itemDropList.size(); i++){
            if(itemDropList.get(i) != null){
                if(!itemDropList.get(i).picked){ // if (false) update
                    itemDropList.get(i).update();
                }
                if(itemDropList.get(i).picked){
                    itemDropList.remove(i);
                }
            }
        }
    }                         // manage each objects update() method
    public void handleLevels(){
        if(buildingTerrain){
            set.setEarth();
            buildingTerrain = false;
        }
        if(player.enterEntry.equals("void")){
            setNull();
            set.setVoid();
            player.enterEntry = "default";
        }
        if(player.enterEntry.equals("earth")){
            setNull();
            set.setEarth();
            player.enterEntry = "default";
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / 60;
        int frames = 0;
        int ticks = 0;
        long lastTimer1 = System.currentTimeMillis();

        while (gameThread != null) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldContinue = true;
            while (unprocessed >= 1) {
                ticks++;
                //tick();
                unprocessed -= 1;
            }

            try {
                Thread.sleep(13);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //if (shouldContinue) {
            frames++;
            update();
            repaint();
            //}

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                System.out.println(ticks + " ticks, " + frames + " fps");
                frames = 0;
                ticks = 0;
            }
        }

//        double drawInterval = 1000000000/FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//        long timer = 0;
//        int drawCount = 0;
//
//        while (gameThread != null){
//            currentTime = System.nanoTime();
//            delta += (currentTime - lastTime) / drawInterval;
//            timer += (currentTime - lastTime);
//            lastTime = currentTime;
//            if(delta >= 1){
//                update();
//                repaint();
//                delta--;
//                drawCount++;
//            }
//            if(timer >= 1000000000){
//                System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
//        }
    }                               // game loop
    public void update(){
        // play state only
        if(gameState == playState){
            handleLevels();
            updateEach();
        }
        // MENU STATE
        if(gameState == menuState){
            setNull();
        }
    }                             // used inside run() method for game loop (important)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(gameState == menuState){
            ui.draw(g2);
        }else{
            //set.generateMoonTile(maxWorldRow, maxWorldCol, 2, writePath);
            manageTile.generateEarthTile(maxWorldRow, maxWorldCol, 0, writePath);
            //manageTile.draw(g2);

            // swing marker
            for(int i=0; i<swingList.size(); i++){
                if(swingList.get(i) != null){
                    entityList.add(swingList.get(i));
                }
            }

            // projectile
            for(int i=0; i<projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }

            // equipped item List
            for(int i=0; i<equippedItemList.size(); i++){
                if(equippedItemList.get(i) != null){
                    entityList.add(equippedItemList.get(i));
                }
            }

            // door
            for (Entity entity : door) {
                if (entity != null) {entityList.add(entity);}
            }

            // EnterPortal
            for (Entity entity : enterPortal) {
                if (entity != null) {waterTileList.add(entity);}
            }

            // EnterPortal
            for (Entity entity : returnPortal) {
                if (entity != null) {waterTileList.add(entity);}
            }

            // iron rock
            for (Entity entity : ironRock) {
                if (entity != null) {entityList.add(entity);}
            }

            // tree
            for (Entity entity : tree) {
                if (entity != null) {entityList.add(entity);}
            }

            // door
            for (Entity entity : door) {
                if (entity != null) {entityList.add(entity);}
            }

            // water
            for (Entity entity : water) {
                if (entity != null) {waterTileList.add(entity);}
            }

            // mushroom
            for (Entity entity : mushroom) {
                if (entity != null) {entityList.add(entity);}
            }


            // russian astronauts
            for (Entity entity : astronaut) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            // item drop list
            for(int i=0; i<itemDropList.size(); i++){
                if(itemDropList.get(i) != null){
                    entityList.add(itemDropList.get(i));
                }
            }

            entityList.add(player);

            entityList.sort((e1, e2) -> Integer.compare(e1.worldY, e2.worldY));

            // draw objects in lists to screen
            for (Entity entity : waterTileList) { entity.draw(g2); }
            for (Entity entity : entityList) { entity.draw(g2); }

            // empty lists
            entityList.clear();
            waterTileList.clear();
            ui.draw(g2);
        }
        g2.dispose();
    }          // draw to screen (g2)
}
