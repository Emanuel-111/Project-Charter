package com.storygame.game;

import java.util.ArrayList;
import java.lang.Runtime;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;

import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class maingame extends JPanel implements ActionListener, KeyListener, MouseListener {

    // Added because of a warning
    private static final long serialVersionUID = 1L;
    Timer time;
    int GAME_SPEED = 10;
    int PLAYER_SPEED = 3;
    int nearNPC = -1;
    int currentSong = 1;
    boolean picrossStarted;
    boolean talkedToMechanic;
    boolean startGame;
    boolean isPicrossMusicOn;
    int[] picrossPos = new int[2];
    float[] pos = new float[]{850, 414};
    float[] swiftPos = new float[]{0, 402};
    float[] owlPos = new float[]{705, 354};
    // Left : Right
    boolean[] direction = new boolean[]{false, false};
    // Title : Game : Picross : Cutscene 
    boolean[] currentScreen = new boolean[]{true, false, false, false};
    // hickForest :
    boolean[] talkingPhase = new boolean[9];
    // Swift to house : living Room talk : Finch's room
    boolean[] houseCutScene = new boolean[]{true, false, false};
    // 0Title : 1Home : 2Hick : 3Mountain : 4City : 5VillainCon : 6Puzzle : 
    Clip[] music = new Clip[7];
    String[] musicFile = new String[7];

    ArrayList<ArrayList<Integer>> picrossNumbers = new ArrayList<>();
    Canvas canvas;
    ActCreator act;
    Dialogue dialogue;
    Picross picross;

    public maingame() {
        picross = new Picross();
        dialogue = new Dialogue();
        act = new ActCreator();
        canvas = new Canvas(pos, swiftPos, owlPos, currentScreen, direction, picrossPos, houseCutScene);
        musicFile[0] = String.valueOf(this.getClass().getResource("/title.wav"));
        musicFile[1] = String.valueOf(this.getClass().getResource("/home.wav"));
        musicFile[2] = String.valueOf(this.getClass().getResource("/hick.wav"));
        musicFile[3] = String.valueOf(this.getClass().getResource("/mountain.wav"));
        musicFile[4] = String.valueOf(this.getClass().getResource("/bigcity.wav"));
        musicFile[5] = String.valueOf(this.getClass().getResource("/villaincon.wav"));
        musicFile[6] = String.valueOf(this.getClass().getResource("/puzzle.wav")); 
        time = new Timer(GAME_SPEED, this);
        time.start();
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void playMusic(int clipIndex, boolean stop, String file) {
        try {
            if(music[clipIndex] == null) {
                URL audioFile = new URL(file);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                music[clipIndex] = (Clip) AudioSystem.getLine(info);
                music[clipIndex].open(audioStream);
            }
            if(stop) {
                music[clipIndex].stop();
            } else {
                music[clipIndex].loop(Clip.LOOP_CONTINUOUSLY);
                music[clipIndex].start();
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println("x: " + x + " y: " + y);
        if(currentScreen[0]) { // If you start the screen
            if(x > 600 && x < 800 && y > 740 && y < 800) {
                currentScreen[0] = false;
                currentScreen[3] = true;
                playMusic(0, true, musicFile[0]);
            }
        } else if(currentScreen[2]) { // Picross
            if(canvas.getPicrossSize() == 5) {
                if(x > 380 && x < 560 && y < 110) {
                    canvas.skipPicross();
                } else if(x > 174 && x < 380 && y < 110) {
                    // Open up options menu
                }
            } else if(canvas.getPicrossSize() == 10) {
                if(x > 350 && x < 530 && y < 110) {
                    canvas.skipPicross();
                } else if(x > 145 && x < 350 && y < 110) {
                    // Open up options menu
                }
            } else if(canvas.getPicrossSize() == 15) {
                if(x > 220 && x < 400 && y < 110) {
                    canvas.skipPicross();
                } else if(x > 0 && x < 220 && y < 110) {
                    // Open up options menu
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(currentScreen[1]) { // Game
            switch(key) {
                case KeyEvent.VK_LEFT:
                    if(!canvas.getTalkingNPC() && !canvas.getTalking()) {
                        direction[0] = true;
                        direction[1] = false;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(!canvas.getTalkingNPC() && !canvas.getTalking()) {
                        direction[1] = true;
                        direction[0] = false;
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    // Initiate text
                    if(canvas.getTalkingNPC()) {
                        canvas.nextLine();
                    } else if(canvas.getTalking()) {
                        canvas.nextLine();
                    } else if(nearNPC != -1) {
                        canvas.talkingToNPC(dialogue.getNPCText(nearNPC));
                    }
                    break;
                case KeyEvent.VK_UP:
                    canvas.setOption(true); // True is yes
                    break;
                case KeyEvent.VK_DOWN:
                    canvas.setOption(false); // False is no
                    break;    
            }
        } else if(currentScreen[2]) { // Picross
            int pSize = canvas.getPicrossSize()-1;
            // If we are animating then it stops keyboard controls
            if(canvas.getAnimating()) {
                // This is for the tutorial. It is here because we animate during the tutorial
                if(key == KeyEvent.VK_SPACE) {
                    canvas.nextLine();
                }
                return;
            }
            switch(key) {
                case KeyEvent.VK_LEFT:
                    if(picrossPos[0] > 0) {
                        picrossPos[0] -= 1;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(picrossPos[0] < pSize) {
                        picrossPos[0] += 1;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(picrossPos[1] > 0) {
                        picrossPos[1] -= 1;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(picrossPos[1] < pSize && pSize <= 10) { 
                            picrossPos[1] += 1;
                    } else if(picrossPos[1] < 9 && pSize > 10) {
                        picrossPos[1] += 1;
                    }
                    break;
                case KeyEvent.VK_Z: // Fill in square
                    canvas.picrossClick(1);
                    break;
                case KeyEvent.VK_X: // Cross in square
                    canvas.picrossClick(0);
                    break;    
            }
        } else if(currentScreen[3]) {
            if(key == KeyEvent.VK_SPACE) {
                if(canvas.getTalking()) {
                    canvas.nextLine();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_LEFT:
                direction[0] = false;
                break;
            case KeyEvent.VK_RIGHT:
                direction[1] = false;
                break; 
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(currentScreen[0]) {
            this.playMusic(0, false, musicFile[0]);
        } else if(currentScreen[1]) { // Game 
            picrossStarted = false;
            if(isPicrossMusicOn) {
                isPicrossMusicOn = false;
                this.playMusic(6, true, musicFile[6]);
                this.playMusic(currentSong, false, musicFile[currentSong]);
            }
            if(currentSong == 1) { // This switches from houseCutScene to game
                this.playMusic(1, true, musicFile[1]);
                currentSong++;
                this.playMusic(currentSong, false, musicFile[currentSong]);
            }
            // Here we check if we are moving to a new act.
            if(canvas.isChangeingToNextAct()) {
                this.playMusic(currentSong, true, musicFile[currentSong]);
                int nextAct = act.getCurrentAct()+1;
                act.setCurrentAct(nextAct);
                dialogue.setCurrentAct(nextAct);
                canvas.setCurrentAct(nextAct);
                int y = 414;
                if(nextAct == 1) {
                    y = 3295;
                }
                pos[0] = 200;
                pos[1] = y;
                swiftPos[0] = 0;
                int heightAdd = 12;
                if(nextAct == 1) {
                    heightAdd = 6;
                }
                swiftPos[1] = y-heightAdd;
                if(nextAct == 3) {
                    owlPos[0] = 350;
                }
                currentSong++;
                this.playMusic(currentSong, false, musicFile[currentSong]);
                canvas.setChangingToNextAct(false);
                return;
            }

            // Player movement
            // If player is in act two we account for vertical movement as well
            // And to move the background at the right speed we need to store that value
            if(direction[0]) {
                pos[0] += act.playerMove(pos, 0);
                if(act.getCurrentAct() == 1) {
                    float verticalSpeed = act.playerMoveVertical(0);
                    canvas.setVerticalSpeed(verticalSpeed);
                    pos[1] += verticalSpeed;
                }
            } else if(direction[1]) {
                // This is only for act one.
                if(act.canMoveForward(pos[0])) {
                    return;
                }
                pos[0] += act.playerMove(pos, 1);
                if(act.getCurrentAct() == 1) {
                    float verticalSpeed = act.playerMoveVertical(1);
                    canvas.setVerticalSpeed(verticalSpeed);
                    pos[1] += verticalSpeed;
                }
            }
            // Here we move swift based on Pos location
            float swiftHorizontalMove = act.followMove(pos, swiftPos, 0);
            swiftPos[0] += swiftHorizontalMove;
            if(swiftHorizontalMove != 0) { // Without this swift will fall or fly randomly
                swiftPos[1] += act.followMove(pos, swiftPos, 1);
            }
            canvas.setSwiftMoving(swiftHorizontalMove != 0);

            if(act.getCurrentAct() == 2) {
                boolean shouldOwlMove = act.getCurrentAct() == 2 && !canvas.getTalking() && canvas.getIsOwlFollowing();
                // We have this for one special scene in the car in act two.
                if(shouldOwlMove || canvas.getSecretScene()) {
                    float owlMovement = act.followMove(swiftPos, owlPos, 0);
                    owlPos[0] += owlMovement;
                    canvas.setOwlMoving(owlMovement != 0);
                } else { // This is needed for a mainDialogue scene where we plays a moving animation.
                    canvas.setOwlMoving(false);
                }
            }

            // Is player is in a talking phase
            // All talking spots have to be in a range because of player speed
            switch(act.getCurrentAct()) {
                case 0:
                    // This is when we first enter the scene.
                    if(!talkingPhase[0] && pos[0] >= 845 && pos[0] <= 855) {
                        canvas.setCurrentText(dialogue.getMainDialogueText(3));
                        canvas.setTalking(true); 
                        talkingPhase[0] = true;
                        // This stops the player from moving during the dialogue
                        direction[0] = false;
                        direction[1] = false;
                    }
                    //This changes the car to a broken car
                    if(talkedToMechanic && pos[0] >= 2000 && pos[0] <= 2010) {
                        canvas.setNextActOnePhase(true);
                    } else if(talkedToMechanic && !talkingPhase[1] && pos[0] >= 540 && pos[0] <= 550) { // This is the talking once the car breaks
                        canvas.setCurrentText(dialogue.getMainDialogueText(4));
                        canvas.setTalking(true); 
                        talkingPhase[1] = true;
                        // This stops the player from moving during the dialogue
                        direction[0] = false;
                        direction[1] = false;
                    } else if(talkingPhase[1] && !talkingPhase[2] && pos[0] >= 6800 && pos[0] <= 6810) { // This is the talking when they see ibis
                        canvas.setCurrentText(dialogue.getMainDialogueText(5));
                        canvas.setTalking(true);
                        talkingPhase[2] = true;
                        // This stops the player from moving during the dialogue
                        direction[0] = false;
                        direction[1] = false;
                    }
                    break;
                case 1:
                    // This is when we first enter the scene.
                    if(!talkingPhase[3] && pos[0] >= 195 && pos[0] <= 205) {
                        canvas.setCurrentText(dialogue.getMainDialogueText(6));
                        canvas.setTalking(true);
                        canvas.setNextActOnePhase(false);
                        talkingPhase[3] = true;
                        direction[0] = false;
                        direction[1] = false;
                    }
                    //The first value is to see if we haved talked to Crane.
                    if(act.getCurrentActNPC()[5][4] == 1 && !talkingPhase[4] && pos[0] > 9800) {
                        canvas.setCurrentText(dialogue.getMainDialogueText(7));
                        canvas.setTalking(true);
                        talkingPhase[4] = true;
                        direction[0] = false;
                        direction[1] = false;
                    }
                    break;
                case 2:
                    if(!talkingPhase[5] && pos[0] >= 540 && pos[0] <= 550) { 
                        canvas.setCurrentText(dialogue.getMainDialogueText(8));
                        canvas.setTalking(true);
                        talkingPhase[5] = true;
                        direction[0] = false;
                        direction[1] = false;
                    } else if(!talkingPhase[6] && pos[0] >= 7900 && pos[0] <= 7910) {
                        canvas.setCurrentText(dialogue.getMainDialogueText(9));
                        canvas.setTalking(true);
                        talkingPhase[6] = true;
                        direction[0] = false;
                        direction[1] = false;
                    }
                    break;
                case 3:
                    // This is when we first enter the scene.
                    if(!talkingPhase[7] && pos[0] >= 195 && pos[0] <= 205) {
                        canvas.setCurrentText(dialogue.getMainDialogueText(10));
                        canvas.setTalking(true);
                        talkingPhase[7] = true;
                        direction[0] = false;
                        direction[1] = false;
                    }
                    // This is for the ending scene when finch is talking
                    if(!talkingPhase[8] && canvas.getEndingScene()) {
                        canvas.setCurrentText(dialogue.getMainDialogueText(11));
                        canvas.setTalking(true);
                        talkingPhase[8] = true;
                    }
                    break;    
            }


            // Is player near npc
            int secretSceneId = -1;
            if(act.getCurrentAct() == 2) {
                secretSceneId = canvas.currentSecretSceneId();
            }
            nearNPC = act.nearNPC(pos[0], canvas.getBgLoopPosition(), secretSceneId);
            if(canvas.isDoneTalking() && nearNPC != -1) {
                canvas.setDoneTalking(false);
                act.nearNpcTalked(nearNPC);
                // This is for the one tire scene
                if(act.getCurrentAct() == 0 && nearNPC == 4) {
                    talkedToMechanic = true;
                }
            }
             // Here we let the canvas know if we are near an NPC
            canvas.nearNPC(nearNPC);
            // Here we give canvas all npc and act information.
            canvas.update(act.getCurrentActNPC());
            // This is only used for act two when meeting crane
            act.setCrane(canvas.getMeetCrane());

        } else if(currentScreen[2]) { // Picross
            isPicrossMusicOn = true;
            // We only assign picross numbers when we first start to not have it go each iteration
            if(!picrossStarted) {
                picrossPos[0] = 0;
                picrossPos[1] = 0;
                if(nearNPC == -1 && talkingPhase[1]) {
                    nearNPC = 5; // This is to get the Ibis picross
                }
                this.playMusic(currentSong, true, musicFile[currentSong]);
                this.playMusic(6, false, musicFile[6]);
                picrossNumbers = picross.getPicrossNumbers(act.getCurrentAct(), nearNPC);
                canvas.setPicrossNumber(picrossNumbers);
                canvas.setCurrentPicrossImage(picross.getActIndex(act.getCurrentAct(), nearNPC));
                picrossStarted = true;
            }
            // I tried to have these methods only call as little as possible because they are slow.
            if(canvas.checkPicross()) {
                int[] check = picross.checkPicrossAccuracy(canvas.getFill(), canvas.getCross(), act.getCurrentAct(), nearNPC);
                if(check.length == 1) { // Completed the picross
                    isPicrossMusicOn = true;
                    // This is because picrossMusicOn never gets used inside of houseScene
                    canvas.completedPicross(true);
                } else if(check.length == 3) { // Got a square wrong
                    canvas.wrongSquare(check[0], check[1], check[2]);
                }
                canvas.setCheckPicross(false);
            }
        } else if(currentScreen[3]) { // Cutscene
            if(isPicrossMusicOn) {
                isPicrossMusicOn = false;
                this.playMusic(6, true, musicFile[6]);
            }
            playMusic(1, false, musicFile[1]);
            if(houseCutScene[0] && !canvas.getAnimating()) {
                canvas.setCurrentText(dialogue.getMainDialogueText(0));
                canvas.setTalking(true);
            } else if(houseCutScene[1] && !canvas.getAnimating()) {
                canvas.setCurrentText(dialogue.getMainDialogueText(1));
                canvas.setTalking(true);
            } else if(houseCutScene[2] && !canvas.getAnimating()) {
                canvas.setTutorialText(dialogue.getTutorialText());
                canvas.setCurrentText(dialogue.getMainDialogueText(2));
                canvas.setTalking(true);
            }
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        canvas.paint(this, g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        maingame obj = new maingame();
        
        frame.add(obj);
        frame.setSize(1460, 980);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}