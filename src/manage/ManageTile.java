package manage;

import main.GamePanel;
import main.HandyTools;
import set.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Random;

/* manage how the terrain is generated */
public class ManageTile{

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public ManageTile(GamePanel gp, String path){
        this.gp = gp;
        tile = new Tile[30];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        readMap(path);
    }

    private void images(){
        imageHandler(0, "grassTile_1", false);
    }

    /* generate numbers in a matrix style -> wRow = size of map height, wCol size of map width,
    tiles = amount of different type of tiles eg... sand, tree*/
    public void generateTile(int wRow, int wCol, int tiles, String path){
        images();
        try {
            FileWriter theFile = new FileWriter(path);
            PrintWriter out = new PrintWriter(theFile);
//            // top border
//            for(int tb = 0; tb<wCol; tb++){
//                out.print(0);
//            }
            for(int row=0; row<wRow; row++){
                for(int col=0; col<wCol; col++){
                    int randXY = generateTree(tiles);
                    out.print(randXY);
                }
                out.print("\n");
            }
            //System.out.print("end matrix \n");
            out.close();
        } catch (IOException e) {
            System.out.println("An error occurred writing to file");
            e.printStackTrace();
        }
    }

    // helper method - used inside generateTerrain() todo: will no longer generate removable objects from this class
    private int generateTree(int tiles){
        int result1;
        Random rand = new Random();
        result1 = rand.nextInt(tiles); // generate 0-tiles -> each number represents a tile type...soil, crater ect..
        // filter out some zeros
        if(result1 == 0){
            rand = new Random();
            result1 = rand.nextInt(tiles);
        }
        // filter out more zeros
        if(result1 == 0){
            rand = new Random();
            result1 = rand.nextInt(tiles);
        }
        return result1;
    }

//    public void generateTerrainBorder(int wRow, int wCol, int tile, String path){
//        try{
//            FileWriter theFile = new FileWriter(path);
//            PrintWriter out = new PrintWriter(theFile);
//            // top border
//            for(int tb = 0; tb<wCol - 1; tb++){
//                out.print(tile);
//            }
//            // left border
//            for(int lb = 0; lb<wRow - 1; lb++){
//                out.println(tile);
//
////                for(int space = 0; space<wCol-2; wCol++){
////                    out.print(1);
////                }
//
//                // right border
//                out.println(0);
//            }
//            // bottom border
//            for(int rb = wCol; rb<wRow*2; rb++){
//                out.print(0);
//            }
//            out.close();
//        } catch(IOException e){
//            System.out.println("An error occurred writing to file");
//            e.printStackTrace();
//        }
//    }

    /* generate numbers in a matrix style -> wRow = size of map height, wCol size of map width,
    tiles = amount of different type of tiles eg... sand, tree*/
    public void generateEarthTile(int wRow, int wCol, int tileNum, String path){
        images(); // set images
        try {
            FileWriter theFile = new FileWriter(path);
            PrintWriter out = new PrintWriter(theFile);
//            // top border
//            for(int tb = 0; tb<wCol; tb++){
//                out.print(0);
//            }
            for(int row=0; row<wRow; row++){
                for(int col=0; col<wCol; col++){
                    out.print(tileNum);
                }
                out.print("\n");
            }
            //System.out.print("end matrix \n");
            out.close();
        } catch (IOException e) {
            System.out.println("An error occurred writing to file");
            e.printStackTrace();
        }
    }

    // todo: make it read the generateTerrain() so you can then load to the draw()
    private void readMap(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split("");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("File not found or corrupted unable to read");
        }
    }

    // todo: maybe make it draw from the txt file and every tile is read like unexplored or created as a rectfill
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.playerLockedX;
            int screenY = worldY - gp.player.worldY + gp.player.playerLockedY;
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.playerLockedX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.playerLockedX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.playerLockedY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.playerLockedY){
//                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//                g2.setColor(Color.GRAY);
                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }
            worldCol++;
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }

    // helper for getTileImage()
    public void imageHandler(int index, String imageName, boolean collision){
        HandyTools uTool = new HandyTools();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/images/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
