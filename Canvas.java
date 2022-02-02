package com.storygame.game;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Image;
import java.awt.Font;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Component;

public class Canvas {

    int PLAYER_H = 160;
    int PLAYER_W = 128;
    int PLAYER_SPEED = 3; 
    int TALKING_SPEED = 3;
    int bgLoopPosition;
    int nearNPCId;
    int currentAct;
    int screenOffSet;
    int talkingCounter;
    int textDisplayed;
    int tutorialTextDisplayed;
    int tutorialEventCounter;
    int characterTalking = -1;
    int currentPicrossSize;
    int fillCounter;
    int crossCounter;
    int picrossCounter;
    int finchWalkCounter;
    int finchStandCounter;
    int finchSitCounter;
    int finchFallingCounter;
    int swiftWalkCounter;
    int swiftStandCounter;
    int swiftFallingCounter;
    int owlWalkCounter;
    int owlStandCounter;
    int owlFallingCounter;
    int intoCarCounter;
    int npcCounter;
    int shelfCounter;
    int textBoxCounter;
    int ibisCounter;
    int currentPicrossImage;
    int boxSize;
    int xOffset;
    int yOffset;
    int cityLoop;
    int scenesCreated;
    int buildingLoop;
    float fallingCounter = -300;
    float vertScreenOffSet;
    float verticalSpeed;
    float actFadingCounter;
    float picrossResultCounter;
    String tutorialOutputText;
    Long picrossStartTime;
    String tutorialText;
    String currentText;
    StringBuffer sb;
    boolean isTalkedIndexSet;
    boolean isDoneTalking;
    boolean isTalking;
    boolean isTalkingNPC;
    boolean startNextLine;
    boolean talkingOption;
    boolean isPicrossStarting;
    boolean isPicrossCross;
    boolean isPicrossFill;
    boolean isAnimating;
    boolean isPicrossUpdate;
    boolean picrossCompleted;
    boolean picrossJustStarting;
    boolean changingToNextAct;
    boolean changingActFade;
    boolean swiftMoving;
    boolean actOneNextPhase;
    boolean owlIntroduceEvent;
    boolean isOwlfollowing;
    boolean isOwlMoving;
    boolean isOwlLeaving;
    boolean flipOwl;
    boolean isIbisDrawn;
    boolean canMeetCrane;
    boolean nuetralTextBox;
    boolean isEndingScene;
    int[] bgSize;
    int[] picrossPos;
    float[] pos;
    float[] swiftPos;
    float[] owlPos;
    boolean[] textBoxEmotion;
    boolean[] direction;
    boolean[] currentScreen;
    boolean[] houseCutScene;
    boolean[] finchHouseAnimations;
    boolean[] secretScene;
    // Finch : Swift : Owl : Secret 
    boolean[] insideCar;
    int[][] npcData;
    ArrayList<ArrayList<Integer>> picrossNumbers;
    ArrayList<int[]> fillSquare;
    ArrayList<int[]> crossSquare;
    Graphics2D gd;

    // These are the picrossResult names that appear
    String[] picrossNames = new String[]{
        "Remote",
        "Water Bottle",
        "Toy Sword",
        "Seed",
        "Bike",
        "Phone",
        "Umbrella",
        "Potion",
        "Back Scratcher",
        "Parachute",
        "Scissors",
        "Car Key",
        "Slime"
    };
    ImageIcon speechBuble = new ImageIcon(this.getClass().getResource("/speechbubble.png"));
    ImageIcon yesOrNo = new ImageIcon(this.getClass().getResource("/yesorno.png"));
    ImageIcon title = new ImageIcon(this.getClass().getResource("/title.png"));

    // ImageIcon[] finchSprite = new ImageIcon[] {
    //     new ImageIcon(this.getClass().getResource("/finchwalka.png")),
    //     new ImageIcon(this.getClass().getResource("/finchwalkb.png")),
    //     new ImageIcon(this.getClass().getResource("/finchwalkc.png")),
    //     new ImageIcon(this.getClass().getResource("/finchwalkd.png")),
    //     new ImageIcon(this.getClass().getResource("/finchwalke.png")),
    //     new ImageIcon(this.getClass().getResource("/finchwalkf.png")),
    //     new ImageIcon(this.getClass().getResource("/finchwalkg.png")),
    //     new ImageIcon(this.getClass().getResource("/finchwalkh.png")),
    //     new ImageIcon(this.getClass().getResource("/finchstilla.png")),
    //     new ImageIcon(this.getClass().getResource("/finchstillb.png")),
    //     new ImageIcon(this.getClass().getResource("/finchsita.png")),
    //     new ImageIcon(this.getClass().getResource("/finchsitb.png")),
    //     new ImageIcon(this.getClass().getResource("/finchsitc.png")),
    //     new ImageIcon(this.getClass().getResource("/finchsitd.png")),
    //     new ImageIcon(this.getClass().getResource("/finchsite.png")),
    //     new ImageIcon(this.getClass().getResource("/finchfalla.png")),
    //     new ImageIcon(this.getClass().getResource("/finchfallb.png")),
    //     new ImageIcon(this.getClass().getResource("/finchfallc.png")),
    //     new ImageIcon(this.getClass().getResource("/finchfalld.png")),
    //     new ImageIcon(this.getClass().getResource("/finchfalle.png")),
    //     new ImageIcon(this.getClass().getResource("/finchfallf.png")),
    // };

    // ImageIcon[] swiftSprite = new ImageIcon[] {
    //     new ImageIcon(this.getClass().getResource("/swiftwalka.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftwalkb.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftwalkc.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftwalkd.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftwalke.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftwalkf.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftwalkg.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftwalkh.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftstilla.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftstillb.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftsit.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftfalla.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftfallb.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftfallc.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftfalld.png")),
    //     new ImageIcon(this.getClass().getResource("/swiftfalle.png")),
    // };

    // ImageIcon[] owlSprite = new ImageIcon[] {
    //     new ImageIcon(this.getClass().getResource("/owlrolla.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollb.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollc.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrolld.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrolle.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollf.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollg.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollh.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrolli.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollj.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollk.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrolll.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollm.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrolln.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollo.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollp.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollq.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollr.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrolls.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollt.png")),
    //     new ImageIcon(this.getClass().getResource("/owlrollu.png")),
    //     new ImageIcon(this.getClass().getResource("/owldismounta.png")),
    //     new ImageIcon(this.getClass().getResource("/owldismountb.png")),
    //     new ImageIcon(this.getClass().getResource("/owldismountc.png")),
    //     new ImageIcon(this.getClass().getResource("/owla.png")),
    //     new ImageIcon(this.getClass().getResource("/owlb.png")),
    //     new ImageIcon(this.getClass().getResource("/owlfalla.png")),
    //     new ImageIcon(this.getClass().getResource("/owlfallb.png")),
    //     new ImageIcon(this.getClass().getResource("/owlfallc.png")),
    //     new ImageIcon(this.getClass().getResource("/owlfalld.png"))
    // };

    // ImageIcon[] objects = new ImageIcon[] {
    //     new ImageIcon(this.getClass().getResource("/cara.png")),
    //     new ImageIcon(this.getClass().getResource("/carb.png")),
    //     new ImageIcon(this.getClass().getResource("/gatea.png")),
    //     new ImageIcon(this.getClass().getResource("/gateb.png"))
    // };

    // ImageIcon[] house = new ImageIcon[]{
    //     new ImageIcon(this.getClass().getResource("/house.png")),
    //     new ImageIcon(this.getClass().getResource("/insidehouse.png")),
    //     new ImageIcon(this.getClass().getResource("/finchrooma.png")),
    //     new ImageIcon(this.getClass().getResource("/finchroomb.png")),
    //     new ImageIcon(this.getClass().getResource("/finchroomc.png")),
    //     new ImageIcon(this.getClass().getResource("/finchroomd.png"))
    // };

    // ImageIcon[] dad = new ImageIcon[] {
    //     new ImageIcon(this.getClass().getResource("/npcdadonea.png")),
    //     new ImageIcon(this.getClass().getResource("/npcdadoneb.png")),
    //     new ImageIcon(this.getClass().getResource("/npcdadonec.png")),
    //     new ImageIcon(this.getClass().getResource("/npcdadtwoa.png")),
    //     new ImageIcon(this.getClass().getResource("/npcdadtwob.png")),
    //     new ImageIcon(this.getClass().getResource("/npcdadtwoc.png"))
    // };

    // ImageIcon[] ibisSprite = new ImageIcon[] {
    //     new ImageIcon(this.getClass().getResource("/ibisa.png")),
    //     new ImageIcon(this.getClass().getResource("/ibisb.png"))
    // };

    // ImageIcon[] textBoxes = new ImageIcon[] {
    //     new ImageIcon(this.getClass().getResource("/angera.png")),
    //     new ImageIcon(this.getClass().getResource("/angerb.png")),
    //     new ImageIcon(this.getClass().getResource("/feara.png")),
    //     new ImageIcon(this.getClass().getResource("/fearb.png")),
    //     new ImageIcon(this.getClass().getResource("/happya.png")),
    //     new ImageIcon(this.getClass().getResource("/happyb.png")),
    //     new ImageIcon(this.getClass().getResource("/sada.png")),
    //     new ImageIcon(this.getClass().getResource("/sadb.png")),
    //     new ImageIcon(this.getClass().getResource("/neutral.png"))
    // };

    // ImageIcon[] background = new ImageIcon[]{
    //     new ImageIcon(this.getClass().getResource("/actonetown.png")),
    //     new ImageIcon(this.getClass().getResource("/mountain.png")),
    //     new ImageIcon(this.getClass().getResource("/city.png")),
    //     new ImageIcon(this.getClass().getResource("/villaincon.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossfive.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossten.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossfifteen.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossstartbackground.png"))
    // };

    // ImageIcon[] secretCity = new ImageIcon[] {
    //     new ImageIcon(this.getClass().getResource("/roof.png")),
    //     new ImageIcon(this.getClass().getResource("/building.png")),
    //     new ImageIcon(this.getClass().getResource("/room.png")),
    //     new ImageIcon(this.getClass().getResource("/car.png"))
    // };

    // ImageIcon[] picrossResult = new ImageIcon[] {
    //     new ImageIcon(this.getClass().getResource("/fivea.png")),
    //     new ImageIcon(this.getClass().getResource("/fiveb.png")),
    //     new ImageIcon(this.getClass().getResource("/fivec.png")),
    //     new ImageIcon(this.getClass().getResource("/fived.png")),
    //     new ImageIcon(this.getClass().getResource("/tena.png")),
    //     new ImageIcon(this.getClass().getResource("/tenb.png")),
    //     new ImageIcon(this.getClass().getResource("/tenc.png")),
    //     new ImageIcon(this.getClass().getResource("/tend.png")),
    //     new ImageIcon(this.getClass().getResource("/tene.png")),
    //     new ImageIcon(this.getClass().getResource("/tenf.png")),
    //     new ImageIcon(this.getClass().getResource("/teng.png")),
    //     new ImageIcon(this.getClass().getResource("/tenh.png")),
    //     new ImageIcon(this.getClass().getResource("/fifteena.png"))
    // };

    // ImageIcon[] picrossBars = new ImageIcon[]{
    //     new ImageIcon(this.getClass().getResource("/horizontalfivebyfive.png")),
    //     new ImageIcon(this.getClass().getResource("/verticalfivebyfive.png")),
    //     new ImageIcon(this.getClass().getResource("/horizontaltenbyten.png")),
    //     new ImageIcon(this.getClass().getResource("/verticaltenbyten.png"))
    // };

    // ImageIcon[] symbols = new ImageIcon[]{
    //     new ImageIcon(this.getClass().getResource("/boxfivebyfivea.png")),
    //     new ImageIcon(this.getClass().getResource("/boxfivebyfiveb.png")),
    //     new ImageIcon(this.getClass().getResource("/boxfivebyfivec.png")),
    //     new ImageIcon(this.getClass().getResource("/boxfivebyfived.png")),
    //     new ImageIcon(this.getClass().getResource("/boxfivebyfivee.png")),
    //     new ImageIcon(this.getClass().getResource("/xfivebyfivea.png")),
    //     new ImageIcon(this.getClass().getResource("/xfivebyfiveb.png")),
    //     new ImageIcon(this.getClass().getResource("/xfivebyfivec.png")),
    //     new ImageIcon(this.getClass().getResource("/xfivebyfived.png")),
    //     new ImageIcon(this.getClass().getResource("/xfivebyfivee.png")),
    //     new ImageIcon(this.getClass().getResource("/boxtenbytena.png")),
    //     new ImageIcon(this.getClass().getResource("/boxtenbytenb.png")),
    //     new ImageIcon(this.getClass().getResource("/boxtenbytenc.png")),
    //     new ImageIcon(this.getClass().getResource("/boxtenbytend.png")),
    //     new ImageIcon(this.getClass().getResource("/boxtenbytene.png")),
    //     new ImageIcon(this.getClass().getResource("/xtenbytena.png")),
    //     new ImageIcon(this.getClass().getResource("/xtenbytenb.png")),
    //     new ImageIcon(this.getClass().getResource("/xtenbytenc.png")),
    //     new ImageIcon(this.getClass().getResource("/xtenbytend.png")),
    //     new ImageIcon(this.getClass().getResource("/xtenbytene.png")),
    // };

    // ImageIcon[] picrossStart = new ImageIcon[] {
    //     new ImageIcon(this.getClass().getResource("/picrossstart1.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossstart2.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossstart3.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossstart4.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossstart5.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossstart6.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossstart7.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossstart8.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossstart9.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossstart10.png")),
    //     new ImageIcon(this.getClass().getResource("/picrossstart11.png")),
    // };

    // ImageIcon[] npc = new ImageIcon[]{
    //     new ImageIcon(this.getClass().getResource("/npchickma.png")),
    //     new ImageIcon(this.getClass().getResource("/npchickmb.png")),
    //     new ImageIcon(this.getClass().getResource("/npchickfa.png")),
    //     new ImageIcon(this.getClass().getResource("/npchickfb.png")),
    //     new ImageIcon(this.getClass().getResource("/npchickma.png")),
    //     new ImageIcon(this.getClass().getResource("/npchickmb.png")),
    //     new ImageIcon(this.getClass().getResource("/npchickfa.png")),
    //     new ImageIcon(this.getClass().getResource("/npchickfb.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmechanica.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmechanicb.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonka.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonkb.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonka.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonkb.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonka.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonkb.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonka.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonkb.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonkgatekeepera.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonkgatekeeperb.png")),
    //     new ImageIcon(this.getClass().getResource("/cranea.png")),
    //     new ImageIcon(this.getClass().getResource("/craneb.png")),
    //     new ImageIcon(this.getClass().getResource("/npccitya.png")),
    //     new ImageIcon(this.getClass().getResource("/npccityb.png")),
    //     new ImageIcon(this.getClass().getResource("/npccitya.png")),
    //     new ImageIcon(this.getClass().getResource("/npccityb.png")),
    //     new ImageIcon(this.getClass().getResource("/npccitya.png")),
    //     new ImageIcon(this.getClass().getResource("/npccityb.png")),
    //     new ImageIcon(this.getClass().getResource("/npccitya.png")), // Maybe change this one to look slight different
    //     new ImageIcon(this.getClass().getResource("/npccityb.png")),
    //     new ImageIcon(this.getClass().getResource("/ibisa.png")),
    //     new ImageIcon(this.getClass().getResource("/ibisb.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonka.png")),
    //     new ImageIcon(this.getClass().getResource("/npcmonkb.png")),
    //     new ImageIcon(this.getClass().getResource("/npcstiltbirda.png")),
    //     new ImageIcon(this.getClass().getResource("/npcstiltbirdb.png")),
    //     new ImageIcon(this.getClass().getResource("/npcproducera.png")),
    //     new ImageIcon(this.getClass().getResource("/npcproducerb.png"))
    // };

    String[] finchSprite = new String[]{
        "/finchwalka.png",
        "/finchwalkb.png",
        "/finchwalkc.png",
        "/finchwalkd.png",
        "/finchwalke.png",
        "/finchwalkf.png",
        "/finchwalkg.png",
        "/finchwalkh.png",
        "/finchstilla.png",
        "/finchstillb.png",
        "/finchsita.png",
        "/finchsitb.png",
        "/finchsitc.png",
        "/finchsitd.png",
        "/finchsite.png",
        "/finchfalla.png",
        "/finchfallb.png",
        "/finchfallc.png",
        "/finchfalld.png",
        "/finchfalle.png",
        "/finchfallf.png",
    };

    String[] swiftSprite = new String[]{
        "/swiftwalka.png",
        "/swiftwalkb.png",
        "/swiftwalkc.png",
        "/swiftwalkd.png",
        "/swiftwalke.png",
        "/swiftwalkf.png",
        "/swiftwalkg.png",
        "/swiftwalkh.png",
        "/swiftstilla.png",
        "/swiftstillb.png",
        "/swiftsit.png",
        "/swiftfalla.png",
        "/swiftfallb.png",
        "/swiftfallc.png",
        "/swiftfalld.png",
        "/swiftfalle.png",
    };

    String[] owlSprite = new String[]{
        "/owlrolla.png",
        "/owlrollb.png",
        "/owlrollc.png",
        "/owlrolld.png",
        "/owlrolle.png",
        "/owlrollf.png",
        "/owlrollg.png",
        "/owlrollh.png",
        "/owlrolli.png",
        "/owlrollj.png",
        "/owlrollk.png",
        "/owlrolll.png",
        "/owlrollm.png",
        "/owlrolln.png",
        "/owlrollo.png",
        "/owlrollp.png",
        "/owlrollq.png",
        "/owlrollr.png",
        "/owlrolls.png",
        "/owlrollt.png",
        "/owlrollu.png",
        "/owldismounta.png",
        "/owldismountb.png",
        "/owldismountc.png",
        "/owla.png",
        "/owlb.png",
        "/owlfalla.png",
        "/owlfallb.png",
        "/owlfallc.png",
        "/owlfalld.png",
    };

    String[] objects = new String[]{
        "/cara.png",
        "/carb.png",
        "/gatea.png",
        "/gateb.png",
    };

    String[] house = new String[]{
        "/house.png",
        "/insidehouse.png",
        "/finchrooma.png",
        "/finchroomb.png",
        "/finchroomc.png",
        "/finchroomd.png",        
    };

    String[] dad = new String[]{
        "/npcdadonea.png",
        "/npcdadoneb.png",
        "/npcdadonec.png",
        "/npcdadtwoa.png",
        "/npcdadtwob.png",
        "/npcdadtwoc.png",
    };

    String[] ibisSprite = new String[]{
        "/ibisa.png",
        "/ibisb.png",
    };

    String[] textBoxes = new String[]{
        "/angera.png",
        "/angerb.png",
        "/feara.png",
        "/fearb.png",
        "/happya.png",
        "/happyb.png",
        "/sada.png",
        "/sadb.png",
        "/neutral.png",
    };

    String[] background = new String[]{
        "/actonetown.png",
        "/mountain.png",
        "/city.png",
        "/villaincon.png",
        "/picrossfive.png",
        "/picrossten.png",
        "/picrossfifteen.png",
        "/picrossstartbackground.png",
    };

    String[] secretCity = new String[]{
        "/roof.png",
        "/building.png",
        "/room.png",
        "/car.png",        
    };

    String[] picrossResult = new String[]{
        "/fivea.png",
        "/fiveb.png",
        "/fivec.png",
        "/fived.png",
        "/tena.png",
        "/tenb.png",
        "/tenc.png",
        "/tend.png",
        "/tene.png",
        "/tenf.png",
        "/teng.png",
        "/tenh.png",
        "/fifteena.png",
    };

    String[] picrossBars = new String[]{
        "/horizontalfivebyfive.png",
        "/verticalfivebyfive.png",
        "/horizontaltenbyten.png",
        "/verticaltenbyten.png",
    };

    String[] symbols = new String[]{
        "/boxfivebyfivea.png",
        "/boxfivebyfiveb.png",
        "/boxfivebyfivec.png",
        "/boxfivebyfived.png",
        "/boxfivebyfivee.png",
        "/xfivebyfivea.png",
        "/xfivebyfiveb.png",
        "/xfivebyfivec.png",
        "/xfivebyfived.png",
        "/xfivebyfivee.png",
        "/boxtenbytena.png",
        "/boxtenbytenb.png",
        "/boxtenbytenc.png",
        "/boxtenbytend.png",
        "/boxtenbytene.png",
        "/xtenbytena.png",
        "/xtenbytenb.png",
        "/xtenbytenc.png",
        "/xtenbytend.png",
        "/xtenbytene.png",
    };

    String[] picrossStart = new String[]{
        "/picrossstart1.png",
        "/picrossstart2.png",
        "/picrossstart3.png",
        "/picrossstart4.png",
        "/picrossstart5.png",
        "/picrossstart6.png",
        "/picrossstart7.png",
        "/picrossstart8.png",
        "/picrossstart9.png",
        "/picrossstart10.png",
        "/picrossstart11.png",
    };

    String[] npc = new String[]{
        "/npchickma.png",
        "/npchickmb.png",
        "/npchickfa.png",
        "/npchickfb.png",
        "/npchickma.png",
        "/npchickmb.png",
        "/npchickfa.png",
        "/npchickfb.png",
        "/npcmechanica.png",
        "/npcmechanicb.png",
        "/npcmonka.png",
        "/npcmonkb.png",
        "/npcmonka.png",
        "/npcmonkb.png",
        "/npcmonka.png",
        "/npcmonkb.png",
        "/npcmonka.png",
        "/npcmonkb.png",
        "/npcmonkgatekeepera.png",
        "/npcmonkgatekeeperb.png",
        "/cranea.png",
        "/craneb.png",
        "/npccitya.png",
        "/npccityb.png",
        "/npccitya.png",
        "/npccityb.png",
        "/npccitya.png",
        "/npccityb.png",
        "/npccitya.png",
        "/npccityb.png",
        "/ibisa.png",
        "/ibisb.png",
        "/npcmonka.png",
        "/npcmonkb.png",
        "/npcstiltbirda.png",
        "/npcstiltbirdb.png",
        "/npcproducera.png",
        "/npcproducerb.png"
    };

    public ImageIcon getII(String fileName) {
        return new ImageIcon(this.getClass().getResource(fileName));
    }

    public ImageIcon resizeImage(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public BufferedImage flipImage(ImageIcon image) {
        // Here we flip the image
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getImage().getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        // Here we translate the image to a bufferedImage
        BufferedImage newImage = new BufferedImage(
            image.getImage().getWidth(null), image.getImage().getHeight(null),
            BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image.getImage(), 0, 0, null);
        g.dispose();
        return op.filter(newImage, null);
    }

    // Here are all of the setters, getters, etc.

    public boolean getEndingScene() {
        return isEndingScene;
    }

    public boolean getSecretScene() {
        return secretScene[5];
    }

    public int currentSecretSceneId() {
        // These Id correspond to the Id in actCreator
        if(secretScene[1]) {
            return 0;
        } else if(secretScene[3]) {
            return 1;
        } else if(secretScene[5]) {
            return 2;
        }
        return -1;
    }

    public boolean getMeetCrane() {
        return canMeetCrane;
    }

    public void setNextActOnePhase(boolean phase) {
        actOneNextPhase = phase;
    }

    public boolean getIsOwlFollowing() {
        return isOwlfollowing;
    }

    public void setSwiftMoving(boolean move) {
        swiftMoving = move;
    }

    public void setOwlMoving(boolean move) {
        flipOwl = false;
        isOwlMoving = move;
    }

    public void setVerticalSpeed(float speed) { 
        verticalSpeed = speed;
    }

    public int getBgLoopPosition() {
        return bgLoopPosition;
    }

    public void talkingToNPC(String text) {
        isTalkingNPC = true;
        setCurrentText(text);
    }

    public void setCurrentText(String text) {
        currentText = text;
    }

    public void setCurrentAct(int act) {
        currentAct = act;
    }

    public boolean isChangeingToNextAct() {
        return changingToNextAct;
    } 

    public void setChangingToNextAct(boolean act) {
        changingToNextAct = act;
    }

    public void nearNPC(int npcId) {
        nearNPCId = npcId;
    }

    public void update(int[][] npc) {
        npcData = npc;
    }

    public void setDoneTalking(boolean talk) {
        isDoneTalking = talk;
    }

    public boolean isDoneTalking() {
        return isDoneTalking;
    }

    public boolean getTalkingNPC() {
        return isTalkingNPC;
    } 

    public boolean getTalking() {
        return isTalking;
    }

    public void setTutorialText(String text) {
        tutorialText = text;
    }

    public void setTalking(boolean talk) {
        isTalking = talk;
    }

    public void nextLine() {
        startNextLine = true;
    }

    public void setOption(boolean option) {
        talkingOption = option;
    }

    public boolean getAnimating() {
        return isAnimating;
    }

    public ArrayList<int[]> getFill() {
        return fillSquare;
    }

    public ArrayList<int[]> getCross() {
        return crossSquare;
    }

    public void setCurrentPicrossImage(int image) {
        currentPicrossImage = image;
    }

    public boolean checkPicross() {
        return isPicrossUpdate;
    }

    public void setCheckPicross(boolean check) {
        isPicrossUpdate = check;
    }

    public void picrossClick(int button) {
        if(button == 1) { // Fill
            isPicrossFill = true;
        } else { // Cross
            isPicrossCross = true;
        }
    }

    public int getPicrossSize() {
        return (currentPicrossSize+1)*5;
    }

    public void setPicrossNumber(ArrayList<ArrayList<Integer>> number) {
        picrossNumbers = number;
    }

    public void completedPicross(boolean completed) {
        picrossCompleted = completed;
    }

    public void skipPicross() {
        picrossCompleted = true;
    }

    public void changePicrossSizes() {
        // Box sizes
        switch(currentPicrossSize) {
            case 0: // 5x5
                boxSize = 96;
                break;
            case 1: // 10x10
                boxSize = 60;
                break;
            case 2: // 15x10
                boxSize = 60;
                break;    

        }
        // starting offset
        switch(currentPicrossSize) {
            case 0: // 5x5
                xOffset = 578;
                yOffset = 236;
                break;
            case 1: // 10x10
                xOffset = 548;
                yOffset = 236;
                break;
            case 2: // 15x10
                xOffset = 416;
                yOffset = 236;
                break;    

        }
    }

    // top refers to the numbers on the top
    public int getPicrossNumberLocation(int axis, boolean top) {
        if(axis == 0) { // X
            switch(currentPicrossSize) {
                case 0: // 5x5
                    return (top) ? 600 : 550;
                case 1: // 10x10
                    return (top) ? 570 : 500;
                case 2: // 15x10
                    return (top) ? 435 : 380;
            }
        } else { // Y
            switch(currentPicrossSize) {
                case 0: // 5x5
                    return (top) ? 225 : 285;
                case 1: // 10x10
                    return (top) ? 225 : 280;
                case 2: // 15x10
                    return (top) ? 225 : 280;
            }
        }
        return 0;
    }

    public int hasFilled() {
        for(int[] data : fillSquare) {
            if(xOffset + (picrossPos[0]*boxSize) == data[0] && yOffset + (picrossPos[1]*boxSize) == data[1]){
                return 1;
            }
        }
        for(int[] data : crossSquare) {
            if(xOffset + (picrossPos[0]*boxSize) == data[0] && yOffset + (picrossPos[1]*boxSize) == data[1]){
                return 0;
            }
        }
        return -1;
    }

    public void wrongSquare(int x, int y, int type) {
        this.removeSquare(type);
    }

    public void removeSquare(int type) {
        if(type == 1) { // Fill
            for(int i = 0; i < fillSquare.size(); i++) {
                if(xOffset + picrossPos[0] * boxSize == fillSquare.get(i)[0] && yOffset + (picrossPos[1]*boxSize) == fillSquare.get(i)[1]) {
                    fillSquare.remove(i);
                    return;
                }
            }
        } else { // Cross
            for(int i = 0; i < crossSquare.size(); i++) {
                if(xOffset + picrossPos[0] * boxSize == crossSquare.get(i)[0] && yOffset + (picrossPos[1]*boxSize) == crossSquare.get(i)[1]) {
                    crossSquare.remove(i);
                    return;
                }
            }
        }
    }
    public void drawPicrossNumbers(Graphics g) {
        int size = (currentPicrossSize+1) * 5;
        int x = getPicrossNumberLocation(0, true);
        int y = getPicrossNumberLocation(1, true);
        int addition = 95;
        if(currentPicrossSize >= 1) {
            addition = 60;
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        for(int i = 0; i < picrossNumbers.size(); i++) {
            if(i == size) { // Flip to the other side
                addition = 0;
                x = getPicrossNumberLocation(0, false);
                y = getPicrossNumberLocation(1, false);;
            }
            for(int j = 0; j < picrossNumbers.get(i).size(); j++) {
                int offset = j * 50;
                int divide = 600;
                if(currentPicrossSize == 1) { // 10x10
                    divide = 525;
                } else if(currentPicrossSize == 2) { // 15x10
                    divide = 410;
                }
                String number = String.valueOf(picrossNumbers.get(i).get(j));
                if(x < divide) { // Horizontal numbers
                    g.drawString(number, x-offset, y);
                } else { // Vertical numbers
                    int longNumber = 0;
                    if(number.length() >= 2) { // If it is a two digit number we center it
                        longNumber = 15;
                    }
                    g.drawString(number, x-longNumber, y-offset);
                }
            }
            x += addition;
            if(addition == 0) {
                // Changes based on picross Size
                y += (currentPicrossSize >= 1) ? 60 : 95;
            }
        }
    }

    public void picrossAnimation(boolean ending, Component c, Graphics g) {
        isAnimating = true;
        picrossCounter++;
        String text = (ending) ? "SOLUTION FOUND" : "THINKING TIME";
        g.fillRect(0, 0, 1440, 960);
        if(picrossCounter >= 165) { // Everything is over
            picrossCounter = 0;
            isAnimating = false;
            if(ending) {
                isPicrossCross = false;
                isPicrossFill = false;
                picrossCompleted = false;
                isPicrossUpdate = false;
                fillSquare.clear();
                crossSquare.clear();
                currentScreen[2] = false;
                if(!houseCutScene[2]) { // Game Play
                    currentScreen[1] = true;
                } else { // Cut scene
                    currentScreen[3] = true; 
                }
            } else {
                picrossJustStarting = false;
            }
        } else if(picrossCounter >= 110) { // Animation is over.
            g.setColor(Color.CYAN);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 65));
            g.drawString(text, 500, 490);
        } else if(picrossCounter >= 80) {
            animation(picrossStart, 8, 3, picrossCounter, 10, false).paintIcon(c, g, 0, 0);
            g.setColor(Color.CYAN);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 65));
            g.drawString(text, 500, 490);
        } else if(picrossCounter >= 70) {
            this.getII(background[7]).paintIcon(c, g, 0, 0);
            this.getII(picrossStart[7]).paintIcon(c, g, 0, 0);
        } else {
            animation(picrossStart, 0, 7, picrossCounter, 10, false).paintIcon(c, g, 0, 0);
        }
    }

    // I put this here to make it more readable than having it placed a bunch in DisplayText
    public void placeDialogue(Graphics g) {
        g.setColor(Color.BLACK);
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        // You might be able to take the increase variable and have it skip over the first iteration instead.
        int add = 0;
        int increase = 0;
        int top = 50;
        if(textBoxEmotion[2] || textBoxEmotion[1]) {
            top = 80;
        }
        for(int i = 0; i < (sb.length()/95)+1; i++) {
            int y = top+((i+1)*35);
            int end = 0;
            if(sb.length() <= 95+(95*i)+add) {
                end = sb.length();
            } else {
                end = 95+(95*i)+add;
                while(sb.charAt(end) != ' ') {
                    end++;
                    add++;
                    if(end >= sb.length()-1) {
                        break;
                    }
                }
            }
            if((95*i)+add > end) {
                g.drawString(sb.substring((95*i), end), 75, y);
            } else {
                g.drawString(sb.substring((95*i)+increase, end), 75, y);
            }
            increase = add;
        }
    }

    public void displayOption(Component c, Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
        yesOrNo.paintIcon(c, g, 0, 0);
        g.setColor(Color.BLACK);
        if(talkingOption) {
            g.drawString("No", 150, 570);
            g.setColor(Color.RED);
            g.drawString("Yes", 150, 440);
        } else {
            g.drawString("Yes", 150, 440);
            g.setColor(Color.RED);
            g.drawString("No", 150, 570);
        }
    }

    // Maybe make it so that it outputs the nuetral text box normally instead of having to check the other ones everytime.
    public ImageIcon getBackgroundTextBox() {
        ImageIcon output = new ImageIcon();
        if(textBoxEmotion[0]) {
            output = (textBoxCounter >= 15) ? this.getII(textBoxes[0]) : this.getII(textBoxes[1]);
        } else if(textBoxEmotion[1]) {
            output = (textBoxCounter >= 15) ? this.getII(textBoxes[2]) : this.getII(textBoxes[3]);
        } else if(textBoxEmotion[2]) {
            output = (textBoxCounter >= 15) ? this.getII(textBoxes[4]) : this.getII(textBoxes[5]);
        } else if(textBoxEmotion[3]) {
            output = (textBoxCounter >= 15) ? this.getII(textBoxes[6]) : this.getII(textBoxes[7]);
        } else {
            nuetralTextBox = true;
            output = this.getII(textBoxes[8]);
        }
        textBoxCounter++;
        if(textBoxCounter >= 30) {
            textBoxCounter = 0;
        }
        return output;
    }

    // This is contained inside of canvas because it has to get a lot of information locally
    // which makes more sense to have it here instead of in Dialogue. 
    public void displayText(Component c, Graphics g, int x, int y, int talked) {
        boolean changedTextBox = false;
        nuetralTextBox = false;
        this.getBackgroundTextBox().paintIcon(c, g, 0, 0);
        int mountainHeight = 0;
        if(currentAct == 1) {
            mountainHeight = 2880;
        }

        // This changes the color of the triangle in the dream world.
        if(finchSitCounter != -1 && !isEndingScene) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }
        // Maybe try to make these polygon drawings wayyyy smaller it is so long
        // This could be handled better through backgroundTextBox or somewhere else.
        if(nuetralTextBox && !insideCar[0] && !secretScene[1]) { // If we are in a secret Scene we do not draw them
            // We add a additional value to each position to make the triangle appear above them
            if(characterTalking == 1) { // Finch
                g.fillPolygon(new int[]{100, 200, (int)pos[0]-screenOffSet+90}, new int[]{251, 251, (int)(pos[1]-vertScreenOffSet-mountainHeight)}, 3);
            } else if(characterTalking == 0) { // Swift
                g.fillPolygon(new int[]{100, 200, (int)swiftPos[0]-screenOffSet+90}, new int[]{251, 251, (int)(swiftPos[1]-vertScreenOffSet-mountainHeight)}, 3);
            } else if(characterTalking == 3) { // Owl
                g.fillPolygon(new int[]{100, 200, (int)owlPos[0]-screenOffSet+90}, new int[]{251, 251, (int)(owlPos[1])}, 3);
            } else if(x != 0 && y != 0) { // This is so it does not draw a triangle in a random direction.
                g.fillPolygon(new int[]{100, 200, x}, new int[]{240, 240, y}, 3);
            }
        } 
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));

        if(talked == 1 && !isTalkedIndexSet) { // We have already talked to this character so we skip it
            for(int i = 0; i < currentText.length(); i++) {
                if(currentText.charAt(i) == '*') {
                    isTalkedIndexSet = true;
                    textDisplayed = i+1;
                    break;
                }
            }
        }
        // If text is empty
        if(textDisplayed >= currentText.length() || currentText.charAt(textDisplayed) == '*') {
            this.placeDialogue(g);
            if(startNextLine) {
                textDisplayed = 0;
                startNextLine = false;
                isTalkingNPC = false;
                isTalking = false;
                isTalkedIndexSet = false;
                isDoneTalking = true;
                sb.replace(0, sb.length(), "");
                textBoxEmotion = new boolean[4];
                characterTalking = -1;
            }
            return;
        }
        // We check to see if a (#)Picross, (%)Question, (^)New Act (&)special event, ($) textemotion, is coming 
        // You should try to change the position of most used to least used to make more efficent.
        // Also maybe try to move this.placeDialogue to a better spot so you dont have to keep calling it after
        if(currentText.charAt(textDisplayed) == '$' ) {
            if(startNextLine) {
                changedTextBox = true;
                // You might be able to make these into numbers instead of letters to make it shorter
                switch(currentText.charAt(textDisplayed+1)) {
                    case 'a':
                        textBoxEmotion[0] = true;
                        break;
                    case 'f':
                        textBoxEmotion[1] = true;
                        break;
                    case 'h':
                        textBoxEmotion[2] = true;
                        break;
                    case 's':
                        textBoxEmotion[3] = true;
                        break;            
                }
                textDisplayed += 2;
            } else {
                this.placeDialogue(g);
                return;
            }
        } else if(currentText.charAt(textDisplayed) == '&') { // Special event
            // The first part is during the cutscenes
            if(houseCutScene[2]) {
                if(startNextLine) {
                    // I have these in if statements because they have to work in order.
                    if(!finchHouseAnimations[0]) {
                        finchHouseAnimations[0] = true;
                    } else if(!finchHouseAnimations[1]) {
                        finchHouseAnimations[1] = true;
                        textDisplayed++;
                    }
                } else {
                    this.placeDialogue(g);
                }
                return;
            } 
            // Here we check these during the game/Acts
            switch(currentAct) {
                case 2:
                    // This is when they first meet owl
                    // We use owlFollowing instead of owlIntroduce because Introduce returns to false later
                    if(!isOwlfollowing) {
                        if(startNextLine) {
                            owlIntroduceEvent = true;
                        } else {
                            this.placeDialogue(g);
                        }
                        return;
                    } else if(scenesCreated < 7) {
                        // Here are the secret tasks. You have to complete them in order or else it will be all weird.
                        // This is waiting for the players to move into the car
                        // I use isAnimating because it isnt used here and I did not want another variable.
                        if(secretScene[5] && isAnimating) {
                            return;
                        }
                        // This loops through the tasks and if one is not true it makes it true.
                        if(startNextLine) {
                            for(int i = scenesCreated; i < 7; i++) {
                                if(!secretScene[i]) {
                                    secretScene[i]= true;
                                    break;
                                } else {
                                    secretScene[i] = false;
                                    scenesCreated++;   
                                }
                            }
                            textDisplayed++;
                        } else {
                            this.placeDialogue(g);
                        }
                        return;
                    }
                case 1:
                    // This is if we are talking to crane
                    if(nearNPCId == 5 && canMeetCrane) {
                        if(startNextLine) {
                            // This is finch sitting
                            ImageIcon finch = animation(finchSprite, 10, 5, finchSitCounter, 5, false);
                            finch.paintIcon(c, g, (int)pos[0]-screenOffSet, (int)(pos[1]-vertScreenOffSet-mountainHeight));
                            // Here is the background fading out
                            // I use finchSitCounter * 0.04 because I do not want to use a new variable to keep track of fading.
                            gd = (Graphics2D) g;
                            float fadeCounter = ((float)finchSitCounter) * 0.04f; 
                            ImageIcon bg = fadeAnimation(background, 1, fadeCounter, true);
                            bg.paintIcon(c, gd, -screenOffSet, (int)-vertScreenOffSet-mountainHeight);
                            finchSitCounter++;
                            if(finchSitCounter >= 25) {
                                finchSitCounter = -1;
                                textDisplayed++;
                            }
                        } else {
                            this.placeDialogue(g);
                        }
                        return;
                    }
                    // This is when we are talking to the gatekeeper
                case 0:
                    // This is for when we go and draw Ibis.
                    if(actOneNextPhase) {
                        if(startNextLine) {
                            isIbisDrawn = true;
                            textDisplayed++;
                        } else {
                            this.placeDialogue(g);
                        }
                        return;
                    }
                    // This is for the mechanic to check we talked to everyone
                    if(startNextLine) {
                        // If we have not talked to an npc it goes through the text and puts textDisplayed at a seperate text
                        int decrease = 1;
                        if(currentAct == 1) {
                            decrease = 2;
                        }
                        for(int i = 0; i < npcData.length-decrease; i++) {
                            if(npcData[i][4] == 0) {
                                textDisplayed += 2;
                                while(textDisplayed < currentText.length()) {
                                    if(currentText.charAt(textDisplayed) == '2') {
                                        break;
                                    }
                                    textDisplayed++;
                                }
                                return;
                            }
                        }
                        // If we talked to everyone then we cut off the end text that says we did a good job.
                        int index = textDisplayed + 2;
                        while(index < currentText.length()) {
                            if(currentText.charAt(index) == '2') {
                                break;
                            }
                            index++;
                        }
                        if(currentAct == 1) {
                            canMeetCrane = true;
                        }
                        currentText = currentText.substring(0, index);
                        textDisplayed++;
                    } else {
                        this.placeDialogue(g);
                    }
                    return;
                case 3: 
                    // This is when you go the producer and we set the screen to move out.
                    if(nearNPCId == 3) {
                        if(startNextLine) {
                            pos[0] = -192;
                            isEndingScene = true;
                            textDisplayed = 0;
                        } else {
                            this.placeDialogue(g);
                        }
                        return;
                    } else {
                        // This is when owl is leaving;
                        if(startNextLine && !isOwlfollowing) {
                            textDisplayed++;
                        } else {
                            isOwlLeaving = true;
                            owlPos[0] += 5;
                            owlStandCounter = 0;
                            animation(owlSprite, 0, 21, owlWalkCounter, 5, false).paintIcon(c, g, (int)owlPos[0], (int)owlPos[1]);;
                            owlWalkCounter++;
                            if(owlWalkCounter >= 105) {
                                owlWalkCounter = 0;
                            }
                            if(owlPos[0] >= 1440) {
                                isOwlfollowing = false;
                            }
                        }
                        this.placeDialogue(g);
                        return;
                    }
            }
        } else if(currentText.charAt(textDisplayed) == '#') { // picross
            if(!startNextLine) {
                this.placeDialogue(g);
                return;
            }
            if(!isPicrossStarting) {
                finchSitCounter = 0; // This is for act 1.
                isPicrossStarting = true;
                picrossJustStarting = true;
                picrossStartTime = System.currentTimeMillis();
                currentScreen[2] = true;
                if(currentScreen[1]) { // Game play
                    currentScreen[1] = false;
                } else { // Cut scene
                    currentScreen[3] = false;
                }
                currentPicrossSize = Character.getNumericValue(currentText.charAt(textDisplayed+1));
                this.changePicrossSizes();
                return;
            } else {
                isPicrossStarting = false;
                textDisplayed += 2;
            }
        } else if(currentText.charAt(textDisplayed) == '%') { // question
            displayOption(c, g);
            this.placeDialogue(g);
            if(startNextLine) {
                String output = "";
                // This used to be yes first and no second but was changed. So there is a !
                if(!talkingOption) { // Said no
                    boolean start = false;
                    // Takes all text displayed after the first @ and makes it the new currenText
                    for(int i = textDisplayed; i < currentText.length(); i++) {
                        if(currentText.charAt(i) == '@') {
                            if(start) {
                                break;
                            }
                            start = true;
                            continue;
                        }
                        if(start) {
                            output += currentText.charAt(i);
                        }
                    }
                } else { // Said yes
                    // Takes all text displayed after the second @ and makes it the new currenText
                    int start = 0;
                    for(int i = textDisplayed; i < currentText.length(); i++) {
                        if(start == 2) {
                            output += currentText.charAt(i);
                        }
                        if(currentText.charAt(i) == '@') {
                            start++;
                            if(start > 2) {
                                break;
                            }
                        }
                    }
                }
                sb.replace(0, currentText.length(), "");
                textBoxEmotion = new boolean[4];
                currentText = output;
                textDisplayed = 0;
                characterTalking = -1;
                startNextLine = false;
            } else {
                return;
            }
        } else if(currentText.charAt(textDisplayed) == '^') { // Next act
            if(startNextLine) {
                changingActFade = true;
                changingToNextAct = true;
                textDisplayed = 0;
                startNextLine = false;
                isTalkingNPC = false;
                isTalking = false;
                screenOffSet = 0;
                vertScreenOffSet = 0;
                sb.replace(0, sb.length(), "");
                textBoxEmotion = new boolean[4];
                characterTalking = -1;
            } else {
                this.placeDialogue(g);
            }
            return;
        }
        // If char is a number we stop to let the player read it then continue to the next talking character
        if(Character.isDigit(currentText.charAt(textDisplayed)) && characterTalking != -1) {
            if(startNextLine) {
                startNextLine = false;
                characterTalking = Character.getNumericValue(currentText.charAt(textDisplayed));
                sb.replace(0, sb.length(), "");
                if(!changedTextBox) {
                    textBoxEmotion = new boolean[4];
                }
                textDisplayed++;
            }
            this.placeDialogue(g);
            return;
        }
        if(characterTalking == -1) {
            characterTalking = Character.getNumericValue(currentText.charAt(textDisplayed));
            textDisplayed++;
        }
        sb.append(currentText.charAt(textDisplayed));
        textDisplayed++;
        this.placeDialogue(g);
    }

    // This sets the gd value and gives back the imageIcon. The Graphics needs to be gd when painting
    // If reverse is true then it fades out if false then fades in.
    // Change the fact that gd is not set until right before you fade.
    public ImageIcon fadeAnimation(String[] imageSet, int imageIndex, float counter, boolean reverse) {
        isAnimating = true;
        if(reverse) {
            gd.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1-counter));
        } else {
            gd.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, counter));
        }
        return this.getII(imageSet[imageIndex]);
    }

    public boolean fadingOutHouse(Component c, Graphics g, int scene) {
        gd = (Graphics2D) g;
        int increase = 1;
        if(scene == 2) {
            increase = 3;
        }
        if(actFadingCounter >= 1) {
            fadeAnimation(house, scene+increase, actFadingCounter-1, false).paintIcon(c, gd, 0, 0);
            actFadingCounter += 0.01;
            // This is to have it skip to the next scene instead of it fading in.
            if(increase == 3) {
                actFadingCounter++;
            }
            // This is in here so the screen does not flash white. There is always something drawn
            if(actFadingCounter >= 2) {
                isAnimating = false;
                actFadingCounter = 0;
                return false;
            }
        } else {
            fadeAnimation(house, scene, actFadingCounter, true).paintIcon(c, gd, 0, 0);;
            actFadingCounter += 0.01;
        }
        return true;
    }

    public ImageIcon animation(String[] imageSet, int imageOffset, int animateCount, int counter, int speed, boolean reverse) {
        int addition = 0;
        int duration = speed;
        for(int i = 0; i < animateCount; i++) {
            if(counter <= duration) {
                addition = i;
                break;
            }
            duration += speed;
        }
        if(reverse) {
            return this.getII(imageSet[(imageOffset + animateCount-1) - addition]);
        } else {
            return this.getII(imageSet[imageOffset + addition]);
        }
    }

    public void paint(Component c, Graphics g) {

        if(currentScreen[0]) {
            title.paintIcon(c, g, 0, 0);
            g.setColor(Color.BLACK);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 80));
            g.drawString("Start", 610, 800);
            // This is the bottom text
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            String controls = "Programmed by: Adam Kollgaard | Art by: Brian Kollgaard | Music by: Travis Hahn";
            g.drawString(controls, 0, 925);
        } else if(currentScreen[1]) {
            // This plays the ending animation so we don't have to go to the other code
            if(isEndingScene) { 
                c.setBackground(Color.WHITE);
                isAnimating = true;
                // When the game ends we just loop the thank you for playing.
                if(pos[0] >= 1440) {
                    gd = (Graphics2D) g;
                    gd.setColor(Color.BLACK);
                    gd.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 75));
                    if(actFadingCounter < 1) {
                        // I use fadeAnimation to have the gd value set for fading. The background, 0 is used as a place holder.
                        fadeAnimation(background, 0, actFadingCounter, false);
                        gd.drawString("Thank You For Playing", 365, 250);
                        gd.drawString("Programmed by: Adam Kollgaard", 200, 350);
                        gd.drawString("Art by: Brian Kollgaard", 325, 450);
                        gd.drawString("Music by: Travis Hahn", 325, 550);
                        actFadingCounter += 0.01;
                    } else {
                        gd.drawString("Thank You For Playing", 365, 250);
                        gd.drawString("Programmed by: Adam Kollgaard", 200, 350);
                        gd.drawString("Art by: Brian Kollgaard", 325, 450);
                        gd.drawString("Music by: Travis Hahn", 325, 550);
                    }
                    
                    return;        
                }
                // First we fade out the background
                if(actFadingCounter < 1 && pos[0] < 518) {
                    gd = (Graphics2D) g;
                    fadeAnimation(background, 3, actFadingCounter, true).paintIcon(c, gd, -screenOffSet, 0);
                    actFadingCounter += 0.01;
                } else {
                    // Next we have finch move across the screen
                    if(pos[0] <= 518) {
                        pos[0] += PLAYER_SPEED;
                        animation(finchSprite, 0, 8, finchWalkCounter, 5, false).paintIcon(c, g, (int)pos[0], (int)pos[1]);
                    } else {
                        actFadingCounter = 0;
                        if(isTalking) {
                            screenOffSet = 0; // This is for the arrow.
                            this.getII(finchSprite[8]).paintIcon(c, g, (int)pos[0], (int)pos[1]);
                            this.displayText(c, g, 0, 0, 0);
                        }
                        // These have to be seperate or else it will loop
                        if(!isTalking) {
                            pos[0] += PLAYER_SPEED;
                            animation(finchSprite, 0, 8, finchWalkCounter, 5, false).paintIcon(c, g, (int)pos[0], (int)pos[1]);
                        }
                    }
                    finchWalkCounter++;
                    if(finchWalkCounter >= 40) {
                        finchWalkCounter = 0;
                    }
                }
                return;
            }
            boolean isBackgroundSet = false;
            int isNpcSet = -1;
            // This is for act 2 when they enter the city. And are in a secret Scene
            if(currentAct == 2) {
                c.setBackground(Color.BLACK);
                if(secretScene[0]) { // On top of roof
                    isBackgroundSet = true;
                    isNpcSet = 0;
                    this.getII(secretCity[0]).paintIcon(c, g, 0, 0);
                    // We set the positions for the player
                    // screenOffsets are used to cancel them out when they are drawn later
                    pos[0] = 650 + screenOffSet;
                    pos[1] = 273 + vertScreenOffSet;
                    swiftPos[0] = 550 + screenOffSet;
                    swiftPos[1] = 261 + vertScreenOffSet;
                    owlPos[0] = 450 + screenOffSet;
                    owlPos[1] = 213 + vertScreenOffSet;
                    ImageIcon npcImage = new ImageIcon(this.getClass().getResource(npc[25]));
                    new ImageIcon(flipImage(npcImage)).paintIcon(c, g, 850, 285);
                } else if(secretScene[1]) { // Falling off roof
                    c.setBackground(Color.decode("#191012"));
                    isBackgroundSet = true;
                    isNpcSet = 0;
                    // Here we make the building move up to make them seem like they are falling
                    buildingLoop -= 15;
                    if(buildingLoop <= 0) {
                        this.getII(secretCity[1]).paintIcon(c, g, 0, buildingLoop);
                        // This is the one that appears on the bottom.
                        this.getII(secretCity[1]).paintIcon(c, g, 0, buildingLoop+960);
                    }
                    if(buildingLoop <= -960) {
                        buildingLoop = 0;
                    }

                    if(fallingCounter <= 400) {
                        fallingCounter += 1.25;
                    }
                    // screenOffsets are used to cancel them out when they are drawn later
                    pos[0] = 100 + screenOffSet;
                    pos[1] = fallingCounter + vertScreenOffSet;
                    swiftPos[0] = 300 + screenOffSet;
                    swiftPos[1] = fallingCounter + vertScreenOffSet;
                    owlPos[0] = 600 + screenOffSet;
                    owlPos[1] = fallingCounter + vertScreenOffSet;
                } else if(secretScene[2]) {
                    secretScene[2] = false;
                    scenesCreated++;
                    pos[0] = 2500;
                    pos[1] = 414;
                    swiftPos[0] = 2300;
                    swiftPos[1] = 402;
                    owlPos[0] = 2100;
                    owlPos[1] = 354;
                } else if(secretScene[3]) {
                    isBackgroundSet = true;
                    isNpcSet = 1;
                    this.getII(secretCity[2]).paintIcon(c, g, 0, 0);
                    pos[0] = 450 + screenOffSet;
                    swiftPos[0] = 200 + screenOffSet;
                    owlPos[0] = screenOffSet;
                    ImageIcon npcImage = new ImageIcon(this.getClass().getResource(npc[25]));
                    new ImageIcon(flipImage(npcImage)).paintIcon(c, g, 1000, 420);
                } else if(secretScene[4]) {
                    secretScene[4] = false;
                    scenesCreated++;
                    pos[0] = 4550;
                    swiftPos[0] = 4350;
                    owlPos[0] = 4150;
                } else if(secretScene[5]) {
                    // This is for moving the npc
                    isNpcSet = 2;
                    if(intoCarCounter <= 250) {
                        intoCarCounter += 2;
                    } else {
                        insideCar[3] = true;
                    }
                    // This is for moving the player/followers
                    if(owlPos[0] <= 6950) {
                        direction[1] = true;
                        isAnimating = true;
                    } else {
                        insideCar[2] = true;
                        direction[1] = false;
                        isAnimating = false;
                    }
                    insideCar[0] = pos[0] >= 6950;
                    insideCar[1] = swiftPos[0] >= 6950;
                } else if(secretScene[6]) {
                    insideCar = new boolean[4];
                    secretScene[6] = false;
                    scenesCreated++;
                    intoCarCounter = 0;
                    pos[0] = 6550;
                    swiftPos[0] = 6350;
                    owlPos[0] = 6150;
                }
            }
            // Here we draw the background and ground
            if(changingActFade) {
                gd = (Graphics2D) g;
                c.setBackground(Color.BLACK);
                // Return statements and counter are inside because screen would flash if return was outside.
                if(actFadingCounter >= 2) {
                    changingActFade = false;
                    actFadingCounter = 0;
                } else if(actFadingCounter >= 1) { // The first part fades in the new/current background
                    fadeAnimation(background, currentAct, actFadingCounter-1, false).paintIcon(c, gd, -screenOffSet, (int)-vertScreenOffSet);
                    actFadingCounter += 0.01;
                    return;
                } else { // The first part fades out the last background
                    fadeAnimation(background, currentAct-1, actFadingCounter, true).paintIcon(c, gd, -screenOffSet, (int)-vertScreenOffSet);
                    actFadingCounter += 0.01;
                    return;
                }
            }

            int mountainHeight = 0;
            if(currentAct == 1) {
                mountainHeight = 2880;
            }

            // This is to have the background loop
            if(currentAct == 2 && !isBackgroundSet) {
                bgLoopPosition = (bgSize[currentAct] * cityLoop);
                if(pos[0] > bgSize[currentAct] + bgLoopPosition) { // Right side
                    cityLoop++;
                } else if(pos[0] < bgLoopPosition) {
                    cityLoop--;
                }
                // This is if you are at the end of a section it draws two depeding on wether you are on the left or right side.
                // 1440 is the size of the screen which is used to have it draw before the character gets there.
                // We also dont move camera when finch is inside the car. This centers the car
                if(pos[0] > (bgSize[currentAct] + bgLoopPosition) - 1440) {
                    this.getII(background[currentAct]).paintIcon(c, g, -screenOffSet+bgLoopPosition+bgSize[currentAct], 0);
                } else if(pos[0] < bgLoopPosition + 1440) {
                    this.getII(background[currentAct]).paintIcon(c, g, -screenOffSet+bgLoopPosition-bgSize[currentAct], 0);
                }
                // We do not add vertScreenOff/moutainHeight because we are in act 2 so it is irrelavent
                this.getII(background[currentAct]).paintIcon(c, g, -screenOffSet+bgLoopPosition, 0);
            } else if(finchSitCounter == 0 && !isBackgroundSet) { // This is for act 1 when they talk to crane.
                this.getII(background[currentAct]).paintIcon(c, g, -screenOffSet, (int)-vertScreenOffSet-mountainHeight);
            }
            if(finchSitCounter != 0) { // This is to make the background white during act 1 scene
                c.setBackground(Color.WHITE);
            }

            if(currentAct == 0) {
                // Here we add the gate/car for the first act so it is hard coded
                if(actOneNextPhase) {
                    this.getII(objects[1]).paintIcon(c, g, 100-screenOffSet, 450);
                    this.getII(objects[3]).paintIcon(c, g, 6993-screenOffSet, 516);
                } else {
                    this.getII(objects[0]).paintIcon(c, g, 100-screenOffSet, 450);
                    this.getII(objects[2]).paintIcon(c, g, 6993-screenOffSet, 516);
                }
                // Here we draw ibis once activated
                if(isIbisDrawn) {
                    new ImageIcon(flipImage(this.animation(ibisSprite, 0, 2, ibisCounter, 30, false))).paintIcon(c, g, 7080-screenOffSet, 426);
                    ibisCounter++;
                    if(ibisCounter >= 60) {
                        ibisCounter = 0;
                    }
                }
            } else if(currentAct == 2) {
                // This is the car
                this.getII(secretCity[3]).paintIcon(c, g, 6850-screenOffSet+bgLoopPosition, 450);
            }

            // All npc are drawn here
            g.setColor(Color.RED);
            for(int i = 0; i < npcData.length; i++) {
                int talked = npcData[i][4];
                int id = npcData[i][3];
                int x = npcData[i][0];
                int y = npcData[i][1];
                // This is for act 2 when they are in the building
                if(isNpcSet != -1) { 
                    if(i != isNpcSet) {
                        continue;
                    } else if(isNpcSet == 0) {
                        x = 850+screenOffSet-bgLoopPosition;
                        y = 285+(int)vertScreenOffSet;
                    } else if(isNpcSet == 1) {
                        x = 1000+screenOffSet-bgLoopPosition;
                    } else if(isNpcSet == 2) {
                        x += intoCarCounter;
                    }
                }
                // This is if we are in the dream world we exit
                if(finchSitCounter != 0 && talked != 0) {
                    continue;
                }
                // Here we do the animation to draw the npc
                int addition = 0;
                if(currentAct == 1) {
                    addition = 10;
                } else if(currentAct == 2) {
                    addition = 22;
                } else if(currentAct == 3) {
                    addition = 30;
                }
                // This is for act 2 when we are falling off of the roof. And in the car
                if(!secretScene[1] && !insideCar[3]) {
                    ImageIcon npcImage = new ImageIcon();
                    npcImage = animation(npc, addition + (id*2), 2, npcCounter, 30, false);
                    new ImageIcon(flipImage(npcImage)).paintIcon(c, g, x-screenOffSet+bgLoopPosition, (int)(y-vertScreenOffSet-mountainHeight));
                }
                if(nearNPCId == id || i == isNpcSet) { // isNpcSet is for act 2 when they are on the building
                    // Highligt NPC talking and around isNpcSet for act 2 falling
                    if(i != isNpcSet && !insideCar[3]) {
                        speechBuble.paintIcon(c, g, x-screenOffSet+bgLoopPosition, (int)(y-vertScreenOffSet-mountainHeight));
                    }
                    // Check if they are talking 
                    if(isTalkingNPC) {
                        this.displayText(c, g, x-screenOffSet+bgLoopPosition, (int)(y-vertScreenOffSet-mountainHeight), talked);
                    }
                }
            }
            npcCounter++;
            if(npcCounter >= 60) {
                npcCounter = 0;
            }

            // These is to change the camera 
            // Please move this in a better location its so bad being in the middle
            // We dont move the camera when finch is in the car. This centers the car during the secretScene
            if(direction[0]) { // Left
                if(screenOffSet > 0 && (pos[0]-screenOffSet) < 540) {
                    screenOffSet -= PLAYER_SPEED; 
                } else if(currentAct == 2 && (pos[0]-screenOffSet) < 540) { // To have act 2 loop
                    screenOffSet -= PLAYER_SPEED;
                }
            } else if(direction[1] && !insideCar[0]) { // Right 
                // We subtract 1440 or else the screen will cut off.
                if(screenOffSet < bgSize[currentAct]-1440 && (pos[0]+PLAYER_W-screenOffSet) > 900) { 
                    screenOffSet += PLAYER_SPEED;
                } else if(currentAct == 2 && (pos[0]+PLAYER_W-screenOffSet) > 900) {// To have act 2 loop
                    screenOffSet += PLAYER_SPEED;
                }
            }
            // This is for vertical camera movement
            if(currentAct == 1 && (direction[0] || direction[1])) {
                if(pos[1]-mountainHeight < 360) {
                    vertScreenOffSet += verticalSpeed;
                }

            }

            // First we check for special events and then if not do main dialogue events
            if(owlIntroduceEvent) {
                // We flip the animated image and paint it.
                new ImageIcon(flipImage(animation(owlSprite, 0, 21, owlWalkCounter, 5, false))).paintIcon(c, g, 1440-(owlWalkCounter*7), 354);
                owlWalkCounter++;
                if(owlWalkCounter >= 105) {
                    owlIntroduceEvent = false;
                    isOwlfollowing = true;
                    flipOwl = true;
                    owlWalkCounter = 0;
                    textDisplayed++;
                }
            } else if(isTalking) {
                this.displayText(c, g, 0, 0, 0);
            }

            // Here we add owl and insideCar is during the 5th scene
            if(isOwlfollowing && !insideCar[2] && !isOwlLeaving) {
                ImageIcon owl = new ImageIcon();
                if(secretScene[1]) {
                    owl = animation(owlSprite, 26, 4, owlFallingCounter, 5, false);
                    owlFallingCounter++;
                    if(owlFallingCounter >= 20) {
                        owlFallingCounter = 0;
                    }
                } else if(isOwlMoving) {
                    owlStandCounter = 0;
                    owl = animation(owlSprite, 0, 21, owlWalkCounter, 5, false);
                    owlWalkCounter++;
                    if(owlWalkCounter >= 105) {
                        owlWalkCounter = 0;
                    }
                } else {
                    owlWalkCounter = 0;
                    if(owlStandCounter >= 15) {
                        // We subtract 15 because it causes owl to flicker which is from only using one variable for two animations
                        owl = animation(owlSprite, 24, 2, owlStandCounter-15, 25, false);
                    } else {
                        owl = animation(owlSprite, 21, 3, owlStandCounter, 5, true);
                    }
                    owlStandCounter++;
                    if(owlStandCounter >= 65) {
                        owlStandCounter = 15;
                    }
                }
                // We make flip owl to make him always face finch
                if(owlPos[0] > pos[0] || flipOwl) {
                    owl = new ImageIcon(flipImage(owl));
                }
                owl.paintIcon(c, g, (int)owlPos[0]-screenOffSet, (int)owlPos[1]);
            }

            // Swift is here to have main character in front.
            ImageIcon swift = new ImageIcon();
            if(secretScene[1]) {
                swift = animation(swiftSprite, 11, 5, swiftFallingCounter, 5, false);
                swiftFallingCounter++;
                if(swiftFallingCounter >= 25) {
                    swiftFallingCounter = 0;
                }
            } else if(swiftMoving) {
                swift = animation(swiftSprite, 0, 8, swiftWalkCounter, 5, false);
                swiftWalkCounter++;
                if(swiftWalkCounter >= 40) {
                    swiftWalkCounter = 0;
                }
            } else {
                swiftWalkCounter = 0;
                swift = animation(swiftSprite, 8, 2, swiftStandCounter, 25, false);
                swiftStandCounter++;
                if(swiftStandCounter >= 50) {
                    swiftStandCounter = 0;
                }
            }
            if(direction[0]) { // Flip image if going left
                swift = new ImageIcon(flipImage(swift));
            }
            if(finchSitCounter == 0 && !insideCar[1]) { // This is for act 2 in the dream world
                swift.paintIcon(c, g, (int)swiftPos[0]-screenOffSet, (int)(swiftPos[1]-vertScreenOffSet-mountainHeight));
            }

            // Here we do the animation for finch
            ImageIcon finch = new ImageIcon();
            if(secretScene[1]) {
                finch = animation(finchSprite, 15, 6, finchFallingCounter, 5, false);
                finchFallingCounter++;
                if(finchFallingCounter >= 30) {
                    finchFallingCounter = 0;
                }
            } else if(direction[0] || direction[1]) { // Character moving
                finch = animation(finchSprite, 0, 8, finchWalkCounter, 5, false);
                finchWalkCounter++;
                if(finchWalkCounter >= 40) {
                    finchWalkCounter = 0;
                }
            } else { // This is to reset finchWalkCounter
                finchWalkCounter = 0;
                finch = animation(finchSprite, 8, 2, finchStandCounter, 25, false);
                finchStandCounter++;
                if(finchStandCounter >= 50) {
                    finchStandCounter = 0;
                }
            }
            if(direction[0]) { // Flip image if going left
                finch = new ImageIcon(flipImage(finch));
            }
            // This is for act 1 when he needs to sit down
            if(finchSitCounter == 0 && !insideCar[0]) {
                finch.paintIcon(c, g, (int)pos[0]-screenOffSet, (int)(pos[1]-vertScreenOffSet-mountainHeight));
            } else if(finchSitCounter == -1) {
                this.getII(finchSprite[14]).paintIcon(c, g, (int)pos[0]-screenOffSet, (int)(pos[1]-vertScreenOffSet-mountainHeight));
            }
        } else if(currentScreen[2]) { // Picross screen
            // picrossResultCounter has to be considered because we have the image appear outside of picrossAnimation to have it include the background
            if(picrossCompleted && picrossResultCounter >= 2.5) {
                picrossAnimation(true, c, g);
                return;
            }
            if(picrossJustStarting) {
                picrossResultCounter = 0;
                picrossAnimation(false, c, g);
                return;
            }

            // Here is the background and info text
            // We add some to offset the actual backgrounds to keep it in frame
            this.getII(background[currentPicrossSize+4]).paintIcon(c, g, 0, 0);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 55));
            int stringX = 410; // 5x5
            if(currentPicrossSize == 1) { // 10x10
                stringX = 380;
            } else if(currentPicrossSize == 2) { // 15x10
                stringX = 245;
            }
            g.drawString("Skip", stringX, 80);
            g.drawString("Option", stringX-230, 80);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            g.drawString("Not available", stringX-245, 110);

        
            // Here is the timer
            // Maybe try to make it stop when they complete the picross with a if statement to picrossComplete
            String time = "MM:SS";
            int seconds = (int)(System.currentTimeMillis() - picrossStartTime) / 1000;
            if(seconds >= 60) {
                int minutes = seconds / 60;
                seconds -= minutes*60;
                String second = (seconds < 10) ? "0" + String.valueOf(seconds) : String.valueOf(seconds);
                String minute = (minutes < 10) ? "0" + String.valueOf(minutes) : String.valueOf(minutes);
                time = minute + ":" + second; 
            } else {
                String second = (seconds < 10) ? "0" + String.valueOf(seconds) : String.valueOf(seconds);
                time = "00:" + second;
            }
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 75));
            int timeX = 270; // 5x5
            if(currentPicrossSize == 1) { // 10x10
                timeX = 235;
            } else if(currentPicrossSize == 2) { // 15x10
                timeX = 100;
            }
            g.drawString(time, timeX, 200);

            // Here is the text that appears at the bottom
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            String controls = "KeyBoard: Arrow Keys to move around - Z to fill - X to crossout | Mouse: Not available";
            g.drawString(controls, 0, 925);

            // Here we do the highlighted box and the rectangles indicating the position
            g.setColor(Color.RED);
            int leftHorizX = 342;
            int picrossBarIndex = 0;
            if(currentPicrossSize == 1) { // 10x10
                leftHorizX = 312;
                picrossBarIndex = 2;
            } else if(currentPicrossSize == 2) { // 15x10
                leftHorizX = 180;
                picrossBarIndex = 2;
            } 

            // Changed to pX and pY because X and Y is defined later on.
            int pX = xOffset + (picrossPos[0]*boxSize);
            int pY = yOffset + (picrossPos[1]*boxSize);
            g.drawRect(pX, pY, boxSize+2, boxSize+2);
            pX = xOffset-3 + (picrossPos[0]*boxSize);
            this.getII(picrossBars[picrossBarIndex+1]).paintIcon(c, g, pX, 0); // Vertical
            pY = yOffset-2 + (picrossPos[1]*boxSize);
            this.getII(picrossBars[picrossBarIndex]).paintIcon(c, g, leftHorizX, pY); // Horizontal

            // Here we draw the numbers that appear over the picross screen
            this.drawPicrossNumbers(g);

            // Here we do the picross filling animation
            int filled = this.hasFilled();
            if(filled == 1 && isPicrossCross) { // This was added because of a bug.
                isPicrossCross = false;
            } else if(filled == 0 && isPicrossFill) {
                isPicrossFill = false;
            }
            // This changes the offsets of x and y depeding on the picross size
            if(isPicrossFill && filled != 0) {
                isAnimating = true;
                int x = xOffset + picrossPos[0]*boxSize;
                int y = yOffset + picrossPos[1]*boxSize;
                boolean reverse = (filled == 1) ? false : true;
                if(currentPicrossSize == 0) {
                    animation(symbols, 0, 5, fillCounter, 2, reverse).paintIcon(c, g, x+4, y+4);
                } else { // 10x10 and 15x10
                    animation(symbols, 10, 5, fillCounter, 2, reverse).paintIcon(c, g, x+4, y+4);
                }
                fillCounter++;
                if(fillCounter >= 10) {
                    isAnimating = false;
                    fillCounter = 0;
                    isPicrossFill = false;
                    isPicrossUpdate = true;
                    if(filled == 1) { // Remove
                        this.removeSquare(1);
                    } else { // Keep
                        fillSquare.add(new int[]{x, y});
                    }
                }
            } else if(isPicrossCross && filled != 1) {
                isAnimating = true;
                int x = xOffset + picrossPos[0]*boxSize;
                int y = yOffset + picrossPos[1]*boxSize;
                boolean reverse = (filled == 0) ? false : true;
                if(currentPicrossSize == 0) {
                    animation(symbols, 5, 5, crossCounter, 2, reverse).paintIcon(c, g, x+4, y+4);
                } else {
                    animation(symbols, 15, 5, crossCounter, 2, reverse).paintIcon(c, g, x+4, y+4);
                }
                crossCounter++;
                if(crossCounter >= 10) {
                    isAnimating = false;
                    crossCounter = 0;
                    isPicrossCross = false;
                    isPicrossUpdate = true;
                    if(filled == 0) {
                        this.removeSquare(0);
                    } else {
                        crossSquare.add(new int[]{x, y});
                    }
                }
            }
            // Here we permanentaly draw the picrossStuff
            // We add 4 as a margin to have it in place
            int index = 0;
            if(currentPicrossSize >= 1) {
                index = 10;
            }
            for(int[] data : fillSquare) {
                this.getII(symbols[index]).paintIcon(c, g, data[0]+4, data[1]+4);
            } 
            index += 5; // This switches to the X section
            for(int[] data : crossSquare) {
                this.getII(symbols[index]).paintIcon(c, g, data[0]+4, data[1]+4);
            }

            // Here is the tutorial code
            // We do our own Diplay Text section because we are in another conversation in display text
            // There probably is a better way, but for now have duplicate code is fine.
            if(houseCutScene[2] && tutorialTextDisplayed < tutorialText.length()) {
                isAnimating = true;
                // Here we draw the box and text. This is done first because of special events
                new ImageIcon(flipImage(this.getII(swiftSprite[8]))).paintIcon(c, g, 1200, 300);
                g.setColor(Color.WHITE);
                g.fillRect(600, 0, 800, 130);
                g.setColor(Color.BLACK);
                g.drawRect(600, 0, 800, 130);
                g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
                if(tutorialOutputText.length() >= 50) {
                    g.drawString(tutorialOutputText.substring(0, 50) + "-", 650, 35);
                    g.drawString(tutorialOutputText.substring(50, tutorialOutputText.length()), 650, 70);
                } else {
                    g.drawString(tutorialOutputText, 650, 35);
                }

                // Here we draw things depending on the event.
                // I double every value to make the border thicker
                g.setColor(Color.RED);
                switch(tutorialEventCounter) {
                    case 1:
                        g.drawRect(160, 0, 410, 235);
                        g.drawRect(161, 1, 408, 233);
                        break;
                    case 2:
                        g.drawRect(170, 0, 190, 110);
                        g.drawRect(171, 1, 188, 108);
                        break;
                    case 3:
                        g.drawRect(360, 0, 200, 110);
                        g.drawRect(361, 1, 198, 108);
                        break;        
                    case 4:
                        g.drawRect(590, 130, 460, 100);
                        g.drawRect(589, 131, 458, 98);
                        g.drawRect(480, 250, 90, 440);
                        g.drawRect(481, 251, 88, 438);
                        break;    
                    case 5:
                        g.drawRect(580, 240, 490, 470);
                        g.drawRect(581, 241, 488, 468);
                        break;   
                    case 6:
                        g.drawRect(590, 130, 460, 100);
                        g.drawRect(591, 131, 458, 98);
                        g.drawRect(480, 250, 90, 440);
                        g.drawRect(481, 251, 88, 438);
                        break;   
                    case 7:
                        g.drawRect(525, 340, 50, 50);
                        g.drawRect(526, 341, 48, 48);
                        g.drawRect(775, 340, 96, 96);
                        g.drawRect(771, 341, 94, 94);
                        if(fillSquare.isEmpty()) {
                            fillSquare.add(new int[]{xOffset + (2 * boxSize), yOffset + (boxSize)});
                        }
                        break;   
                    case 8:
                        g.drawRect(532, 435, 50, 50);
                        g.drawRect(533, 436, 48, 48);
                        g.drawRect(680, 430, 280, 96);
                        g.drawRect(681, 431, 278, 94);
                        if(fillSquare.size() == 1) {
                            fillSquare.add(new int[]{xOffset + (1 * boxSize), yOffset + (2 *boxSize)});
                            fillSquare.add(new int[]{xOffset + (2 * boxSize), yOffset + (2 *boxSize)});
                            fillSquare.add(new int[]{xOffset + (3 * boxSize), yOffset + (2 *boxSize)});
                        }
                        break;   
                    case 9:
                        g.drawRect(340, 530, 710, 96);
                        g.drawRect(341, 531, 708, 94);
                        break;   
                    case 10:
                        g.drawRect(680, 180, 90, 550);
                        g.drawRect(681, 181, 88, 548);
                        break;
                    case 11:
                        g.drawRect(475, 530, 105, 90);
                        g.drawRect(476, 531, 103, 88);
                        break; 
                    case 12:
                        g.drawRect(490, 530, 40, 50);
                        g.drawRect(491, 531, 38, 48);
                        g.drawRect(680, 530, 96, 96);
                        g.drawRect(681, 531, 94, 94);
                        g.setColor(Color.BLUE);
                        g.drawRect(550, 540, 20, 35);
                        g.drawRect(551, 541, 18, 33);
                        g.drawRect(870, 530, 96, 96);
                        g.drawRect(871, 531, 94, 94);
                        if(fillSquare.size() == 4) {
                            fillSquare.add(new int[]{xOffset + (1 * boxSize), yOffset + (3 *boxSize)});
                            fillSquare.add(new int[]{xOffset + (3 * boxSize), yOffset + (3 *boxSize)});
                        }
                        break; 
                    case 13:
                        g.drawRect(580, 240, 90, 470);
                        g.drawRect(581, 241, 88, 468);
                        break; 
                    case 14:
                        g.drawRect(600, 180, 30, 50);
                        g.drawRect(601, 181, 28, 48);
                        if(crossSquare.isEmpty()) {
                            crossSquare.add(new int[]{xOffset , yOffset});
                            crossSquare.add(new int[]{xOffset , yOffset + (boxSize)});
                            crossSquare.add(new int[]{xOffset , yOffset + (2 *boxSize)});
                            crossSquare.add(new int[]{xOffset , yOffset + (3 *boxSize)});
                            crossSquare.add(new int[]{xOffset , yOffset + (4 *boxSize)});
                        }
                        break;
                    case 16:
                        fillSquare.clear();
                        crossSquare.clear();
                        isAnimating = false;
                        break;    
                }

                if(tutorialText.charAt(tutorialTextDisplayed) == '&') { 
                    // This is where we increase the special event counter
                    startNextLine = false;
                    tutorialEventCounter++;
                    tutorialTextDisplayed++;

                    // Here we reset the text when a new text line should be made.
                } else if(Character.isDigit(tutorialText.charAt(tutorialTextDisplayed))) {
                    if(startNextLine) {
                        startNextLine = false;
                        tutorialOutputText = "";
                        tutorialTextDisplayed++;
                    }
                } else {
                    tutorialOutputText += tutorialText.charAt(tutorialTextDisplayed);
                    tutorialTextDisplayed++;
                }
            }

            // We draw the completed image here
            if(picrossCompleted) {
                isAnimating = true;
                gd = (Graphics2D) g;
                // These are seperate to have the image show for a little bit after it fades in.
                if(picrossResultCounter <= 1) {
                    fadeAnimation(picrossResult, currentPicrossImage, picrossResultCounter, false).paintIcon(c, gd, xOffset, yOffset);
                } else {
                    this.getII(picrossResult[currentPicrossImage]).paintIcon(c, g, xOffset, yOffset);
                }
                picrossResultCounter += 0.01;
                // Here we draw the text box for the picross
                String name = picrossNames[currentPicrossImage];
                // We change the size of the box depending on the name of the picross
                int boxLength = 300;
                if(name.length() >= 6) {
                    boxLength = 400;
                } else if(name.length() >= 8) {
                    boxLength = 600;
                } else if(name.length() >= 12) {
                    boxLength = 900;
                }
                g.setColor(Color.WHITE);
                g.fillRect(600, 50, boxLength, 150);
                g.setColor(Color.RED);
                g.drawRect(600, 50, boxLength, 150);
                g.setColor(Color.RED);
                g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 60));
                g.drawString(name, 650, 150);
            }

        } else if(currentScreen[3]) { // Cutscene
            if(houseCutScene[0]){
                if(!isAnimating) { // This is because of the fade
                    this.getII(house[0]).paintIcon(c, g, 0, 0);
                }
                if(swiftPos[0] >= 700) {
                    if(isTalking) {
                        this.displayText(c, g, 850, 320, 0);
                    }
                    // These have to be seperate statements or else it will loop
                    if(!isTalking) {
                        if(this.fadingOutHouse(c, g, 0)) {
                            return;
                        }
                        swiftPos[0] = 300;
                        swiftPos[1] = 400;
                        houseCutScene[0] = false;
                        houseCutScene[1] = true;
                    }
                    // I paint this here so swift does not suddenly flash away.
                    this.getII(swiftSprite[8]).paintIcon(c, g, (int)swiftPos[0], (int)swiftPos[1]);
                } else {
                    animation(swiftSprite, 0, 8, swiftWalkCounter, 5, false).paintIcon(c, g, (int)swiftPos[0], (int)swiftPos[1]);
                    swiftPos[0] += 3;
                    swiftWalkCounter++;
                    if(swiftWalkCounter >= 40) {
                        swiftWalkCounter = 0;
                    }
                }
            } else if(houseCutScene[1]) {
                if(!isAnimating) { // If we are not fading.
                    this.getII(house[1]).paintIcon(c, g, 0, 0);
                }
                if(isTalking) {
                    this.displayText(c, g, 1050, 380, 0);
                }
                // These have to be seperate statements or else it will loop
                if(!isTalking) { 
                    if(this.fadingOutHouse(c, g, 1)) {
                        return;
                    }
                    swiftPos[0] = 200;
                    houseCutScene[1] = false;
                    houseCutScene[2] = true;
                    return;
                }
                new ImageIcon(flipImage(this.getII(dad[2]))).paintIcon(c, g, 965, 390);
                new ImageIcon(flipImage(this.getII(dad[5]))).paintIcon(c, g, 1150, 390);
                this.getII(swiftSprite[10]).paintIcon(c, g, (int)swiftPos[0], (int)swiftPos[1]);
            } else if(houseCutScene[2]) {
                // The fadingHouseAnimation code is so weird. Maybe change it.
                if(finchHouseAnimations[0] && shelfCounter >= 0) {
                    shelfCounter++;
                    if(shelfCounter >= 60) {
                        textDisplayed++;
                        shelfCounter = -1;
                    } else {
                        animation(house, 2, 4, shelfCounter, 15, false).paintIcon(c, g, 0, 0);;
                    }
                } else if(!isAnimating && shelfCounter >= 0) { // This is because of the animation
                    this.getII(house[2]).paintIcon(c, g, 0, 0);
                }
                // If we finished the animation we show the new background
                // It is seperate or else it will flash
                if(!isAnimating && shelfCounter == -1) {
                    this.getII(house[5]).paintIcon(c, g, 0, 0);
                }

                if(isTalking && shelfCounter <= 0) {
                    this.displayText(c, g, 0, 0, 0);
                }
                // These have to be seperate statements or else it will loop
                if(!isTalking) {
                    if(this.fadingOutHouse(c, g, 2)) {
                        return;
                    }
                    pos[0] = 200;
                    swiftPos[0] = 0;
                    houseCutScene[2] = false;
                    currentScreen[3] = false;
                    currentScreen[1] = true;
                }

                this.getII(swiftSprite[8]).paintIcon(c, g, (int)swiftPos[0], (int)swiftPos[1]); 
                // This is for when finch disappears
                if(!finchHouseAnimations[1]) {
                    new ImageIcon(flipImage(this.getII(finchSprite[8]))).paintIcon(c, g, (int)pos[0], (int)pos[1]);
                }
            }
        }

    }

    public Canvas(float[] position, float[] swiftPosition, float[] owlPosition, boolean[] currentS, boolean[] direct, int[] picross, boolean[] houseCS) {
        pos = position;
        swiftPos = swiftPosition;
        owlPos = owlPosition;
        currentScreen = currentS;
        direction = direct;
        picrossPos = picross;
        houseCutScene = houseCS;
        picrossNumbers = new ArrayList<ArrayList<Integer>>();
        fillSquare = new ArrayList<>();
        crossSquare = new ArrayList<>();
        finchHouseAnimations = new boolean[2];
        textBoxEmotion = new boolean[4];
        secretScene = new boolean[7];
        insideCar = new boolean[4];
        tutorialOutputText = "";
        // These are the number of background/other changes in the house scene
        bgSize = new int[]{10080, 10080, 10080, 5760};
        sb = new StringBuffer("");
    }
}