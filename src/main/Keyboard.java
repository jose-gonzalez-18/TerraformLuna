package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed,leftPressed, rightPressed; //WASD
    public boolean enteredPressed, ePressed, iPressed, tPressed;
    public boolean punching = false;
    public boolean shooting = false;
    public boolean chopping = false;

    // constructor
    public Keyboard(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e){
    }
    @Override
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();

        // --------------------------------- MAIN MENU STATE ----------------------------
        if(gp.gameState == gp.menuState){
            // move through the menu
            if(keyCode == KeyEvent.VK_W){
                gp.ui.selectArrow--;
                if(gp.ui.selectArrow < 0){ // prevent arrow from leaving screen
                    gp.ui.selectArrow = 2;
                }
            }
            if(keyCode == KeyEvent.VK_S){
                gp.ui.selectArrow++;
                if(gp.ui.selectArrow > 2){ // prevent arrow from leaving screen
                    gp.ui.selectArrow = 0;
                }
            }
            if(keyCode == KeyEvent.VK_ENTER){
                // play game
                if(gp.ui.selectArrow == 0){
                    //gp.playMusic(1); todo
                    gp.gameState = gp.playState;
                }
                // options
                if(gp.ui.selectArrow == 1){
                    gp.gameState = gp.playState; // temp
                    //gp.gameState = gp.optionSate; todo: add
                }
                // about
                if(gp.ui.selectArrow == 2){
                    gp.gameState = gp.aboutState;
                }
            }
        }

        // ---------------------------------- PAUSED STATE ----------------------------
        if(gp.gameState == gp.pauseState){
            // move through the menu
            if(keyCode == KeyEvent.VK_W){
                gp.ui.selectArrow--;
                if(gp.ui.selectArrow < 0){ // prevent arrow from leaving screen
                    gp.ui.selectArrow = 2;
                }
            }
            if(keyCode == KeyEvent.VK_S){
                gp.ui.selectArrow++;
                if(gp.ui.selectArrow > 2){ // prevent arrow from leaving screen
                    gp.ui.selectArrow = 0;
                }
            }
            if(keyCode == KeyEvent.VK_ENTER){
                // play game
                if(gp.ui.selectArrow == 0){
                    //gp.playMusic(1); todo
                    gp.gameState = gp.playState;
                }
                // options
                if(gp.ui.selectArrow == 1){
                    gp.gameState = gp.playState; // temp
                    //gp.gameState = gp.optionSate; // todo: add
                }
                // exit
                if(gp.ui.selectArrow == 2){
                    gp.gameState = gp.menuState;
                }
            }
        }

        // ----------------------------------- GAME RUNNING STATE ----------------------
        if(gp.gameState == gp.playState){
            // player movement
            if(keyCode == KeyEvent.VK_W){ upPressed = true; }
            if(keyCode == KeyEvent.VK_S){ downPressed = true; }
            if(keyCode == KeyEvent.VK_D){ rightPressed = true; }
            if(keyCode == KeyEvent.VK_A){ leftPressed = true; }
            if(keyCode == KeyEvent.VK_ENTER){
                enteredPressed = true;
            }
            // equip items
            if(keyCode == KeyEvent.VK_E){
                ePressed = true;
            }
            // item manager
            if(keyCode == KeyEvent.VK_I){
                iPressed = true;
            }
            // for testing/debugging only
            if(keyCode == KeyEvent.VK_T){
                tPressed = true;
            }
            // attacking
            if(keyCode == KeyEvent.VK_SPACE){
                if(gp.player.equipAxe){
                    chopping = true;
                }
                else if(gp.player.equipRayGun){
                    shooting = true;
                }
                else if(gp.player.punch){
                    punching = true;
                }
            }
            if(keyCode == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if(keyCode == KeyEvent.VK_I){
                gp.gameState = gp.itemManagerState;
            }
            if(keyCode == KeyEvent.VK_T){
                // optional
            }
//            // check fps while game running
//            if(keyCode == KeyEvent.VK_T){
//                if(!checkFPS){ checkFPS = true; }
//                else if(checkFPS){ checkFPS = false; }
//            }
        }

        // --------------------------------------- ITEM MANAGER KEYBOARD ---------------------
        if(gp.gameState == gp.itemManagerState){
            if(keyCode == KeyEvent.VK_ENTER){ // EXIT small window
                gp.gameState = gp.playState;
            }
            // move through the menu
            if(keyCode == KeyEvent.VK_W){
                gp.ui.selectArrow--;
                if(gp.ui.selectArrow < 0){ // prevent arrow from leaving screen
                    gp.ui.selectArrow = 2;
                }
            }
            if(keyCode == KeyEvent.VK_S){
                gp.ui.selectArrow++;
                if(gp.ui.selectArrow > 2){ // prevent arrow from leaving screen
                    gp.ui.selectArrow = 0;
                }
            }
            if(keyCode == KeyEvent.VK_ENTER){
                // un-equip item
                if(gp.ui.selectArrow == 0){
                    gp.player.punch = true;
                    gp.player.equipRayGun = false;
                    gp.player.equipAxe = false;
                }
                // axe
                if(gp.ui.selectArrow == 1){
                    gp.player.equipAxe = true;
                    gp.player.equipRayGun = false;
                }
                // ray gun
                if(gp.ui.selectArrow == 2){
                    gp.player.equipRayGun = true;
                    gp.player.equipAxe = false;
                }
            }
        }

        // --------------------------------------- DIALOGUE STATE KEYBOARD ---------------------
        if(gp.gameState == gp.dialogueState){

        }

        // ---------------------------------------- PLAYERS DEATH STATE -----------------------
        if(gp.gameState == gp.playerDeathState){
            // move through the menu
            if(keyCode == KeyEvent.VK_W){
                gp.ui.selectArrow--;
                if(gp.ui.selectArrow < 0){ // prevent arrow from leaving screen
                    gp.ui.selectArrow = 2;
                }
            }
            if(keyCode == KeyEvent.VK_S){
                gp.ui.selectArrow++;
                if(gp.ui.selectArrow > 2){ // prevent arrow from leaving screen
                    gp.ui.selectArrow = 0;
                }
            }
            if(keyCode == KeyEvent.VK_ENTER){
                // play again
                if(gp.ui.selectArrow == 0){
                    //gp.playMusic(1); todo
                    gp.gameState = gp.playState;
                }
                // options
                if(gp.ui.selectArrow == 1){
                    gp.gameState = gp.optionState;
                }
                // exit game
                if(gp.ui.selectArrow == 2){
                    gp.gameState = gp.menuState;
                }
            }
        }
    }
    // ------------------------------------------ KEY RELEASE --------------------------------
    @Override
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode  == KeyEvent.VK_W){ upPressed = false; }
        if(keyCode == KeyEvent.VK_S){ downPressed = false; }
        if(keyCode == KeyEvent.VK_A){ leftPressed = false; }
        if(keyCode == KeyEvent.VK_D){ rightPressed = false; }
        if(keyCode == KeyEvent.VK_SPACE){
            punching = false;
            chopping = false;
            shooting = false;
        }
        if(keyCode == KeyEvent.VK_E){
            ePressed = false;
        }
        if(keyCode == KeyEvent.VK_I){
            iPressed = false;
        }
        if(keyCode == KeyEvent.VK_T){
            tPressed = false;
        }
    }
}
