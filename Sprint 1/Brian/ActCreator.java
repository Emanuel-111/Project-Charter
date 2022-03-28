package com.storygame.game;

import java.util.ArrayList;

public class ActCreator {

    private static int PLAYER_W = 240; 
    private static int PLAYER_SPEED = 3;
    private static int NPC_W = 50; 
    private static int RANGE = 200;
    int currentAct = 0;
    int screenMove;
    int playerGroundSpot;
    int swiftGroundSpot;
    int swiftDirection;
    boolean meetCrane;
    int[] bgSize;
    ArrayList<float[][]> groundLine;
    // X : Y : ActId : NPCId : talkedToo
    ArrayList<int[][]> npc;

    public void nearNpcTalked(int nearNpc) {
        npc.get(currentAct)[nearNpc][4] = 1;
    }

    public void moveBackground(int direction) {
        if(direction == 0) { // left
            screenMove += 5;
        } else { // right
            screenMove -= 5;
        }
    }

    public int[][] getCurrentActNPC() {
        return npc.get(currentAct);
    }

    public int getCurrentAct() {
        return currentAct;
    }

    public void setCurrentAct(int act) {
        currentAct = act;
    }

    public void setCrane(boolean meet) {
        meetCrane = meet;
    }

    public boolean canMoveForward(float x) {
        if(currentAct == 1 && !meetCrane && x >= 8500) {
            return true;
        }
        return false;
    }

    // I think you can take out the first If statement because I dont think it does anything
    public int nearNPC(float x, int loopPosition, int secrectId) {
        if(secrectId != -1) { // If we are in a secret scene we set the Id to the secret.
            return secrectId;
        }
        boolean currentNPC = false;
        int[][] data = npc.get(currentAct);
        for(int i = 0; i < npc.get(currentAct).length; i++) {
            // Here we check to make sure we are in the correct Act List
            if(data[i][2] != currentAct) {
                if(currentNPC) {
                    return -1;
                }
                continue;
            }
            currentNPC = true;
            // Here we see if we are near the NPC
            if(x > (data[i][0]+loopPosition)-RANGE && x < (data[i][0]+loopPosition)+(RANGE+NPC_W)) {
                return data[i][3];
            }
        }
        return -1;
    }

    // Swift has an area around her. If finch leaves it then she will follow if not then she does nothing.
    // refPos is the reference Position which what we want the folPos, follow position, to follow.
    public float followMove(float[] refPos, float[] folPos, int axis) {
        if(axis == 0) { // X axis
            // Here we update swiftPos current ground location during act 1
            if(currentAct == 1) {
                if(folPos[0] > groundLine.get(swiftGroundSpot)[1][0]) {
                    swiftGroundSpot++;
                } else if(folPos[0] < groundLine.get(swiftGroundSpot)[0][0]) {
                    swiftGroundSpot--;
                }
            }

            float disFromFinch = refPos[0] - folPos[0];
            if(disFromFinch > 250) {
                swiftDirection = 1;
                return PLAYER_SPEED;
            } else if(disFromFinch < -250) {
                swiftDirection = 0;
                return -PLAYER_SPEED;
            }
        } else if(currentAct == 1) { // Y axis
            // Because the y values are changing I have swift calculate its own y values and direction
            // Seperately from finch. There must be a better way though.
            float[][] line = groundLine.get(swiftGroundSpot);
            float slope = (line[1][1] - line[0][1]) / (line[1][0] - line[0][0]);
            float yMovement = slope * PLAYER_SPEED;
            return (swiftDirection == 0) ? yMovement : -yMovement;
        }
        return 0;
    }

    // To calculate the vertical movement needed. We find the slope of the groundLine.
    // Then multiply it by PLAYER_SPEED to get the needed y movement.
    public float playerMoveVertical(int direction) {
        float[][] line = groundLine.get(playerGroundSpot);
        float slope = (line[1][1] - line[0][1]) / (line[1][0] - line[0][0]);
        float yMovement = slope * PLAYER_SPEED;
        return (direction == 0) ? yMovement : -yMovement;
    }

    public int playerMove(float[] pos, int direction) {
        float x = pos[0];
        switch(currentAct) {
            case 3:
            case 0:
                if(direction == 0) { // Left
                    return (x >= 0) ? -PLAYER_SPEED : 0;
                } else { // Right
                    return (x+PLAYER_W <= bgSize[0]) ? PLAYER_SPEED : 0;
                }
            case 1:
                // Updates the groundLine if the player moves out of it.
                if(pos[0] > groundLine.get(playerGroundSpot)[1][0]) {
                    playerGroundSpot++;
                } else if(pos[0] < groundLine.get(playerGroundSpot)[0][0]) {
                    playerGroundSpot--;
                }
                if(direction == 0) { // Left
                    return (x >= 0) ? -PLAYER_SPEED : 0;
                } else { // Right
                    return (x+PLAYER_W <= bgSize[1]) ? PLAYER_SPEED : 0;
                }
            case 2:
                if(direction == 0) { // Left
                    return -PLAYER_SPEED;
                } else { // Right
                    return PLAYER_SPEED;
                }    
        }
        return 0;
    }

    public ActCreator() {
        groundLine = new ArrayList<>();
        npc = new ArrayList<>();
        bgSize = new int[]{10080, 10080, 10080, 5760};
        this.addNPCData();
        this.setGroundLine();
    }

    // This is used only for the second act in Spirt Moutain due to the changing elevation.
    public void setGroundLine() {
        // Line1: Point1{x, y}, Point2{x, y} 
        // Make the ground lines longer than the actual ground because it can causes an error
        groundLine.add(new float[][]{{-10, 295}, {1445, 295}});
        groundLine.add(new float[][]{{1445, 295}, {2874, 1255}});
        groundLine.add(new float[][]{{2874, 1255}, {4325, 1255}});
        groundLine.add(new float[][]{{4325, 1255}, {5754, 2215}});
        groundLine.add(new float[][]{{5754, 2215}, {7205, 2215}});
        groundLine.add(new float[][]{{7205, 2215}, {8634, 3175}});
        groundLine.add(new float[][]{{8634, 3175}, {10090, 3175}});
    }

    public void addNPCData() {
        // Act One
        npc.add(new int[][]{
            {2400, 420, 0, 0, 0},
            {3700, 420, 0, 1, 0},
            {4300, 420, 0, 2, 0},
            {5500, 420, 0, 3, 0},
            {9000, 420, 0, 4, 0}
        });
        // Act two
        npc.add(new int[][]{
            {1200, 3302, 1, 0, 0},
            {3210, 2340, 1, 1, 0},
            {4000, 2340, 1, 2, 0},
            {6150, 1382, 1, 3, 0},
            {8670, 424, 1, 4, 0},
            {9450, 434, 1, 5, 0}                
        });
        // Act three
        npc.add(new int[][]{
            {2650, 426, 2, 0, 0},
            {4700, 426, 2, 1, 0},
            {6700, 426, 2, 2, 0},
            {8200, 426, 2, 3, 0}
        });
        // Act Four
        npc.add(new int[][]{
            {850, 426, 3, 0, 0},
            {2260, 420, 3, 1, 0},
            {3750, 426, 3, 2, 0},
            {4975, 426, 3, 3, 0}
        });
    }
}