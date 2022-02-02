package com.storygame.game;

import java.util.ArrayList;

public class Picross {

	ArrayList<int[][]> picrossResult;
	int boxSize;
	int yOffset;
	int xOffset;


    public Picross() {
        picrossResult = new ArrayList<>();
        this.setPicrossResults();
	}

    public void setPicrossResults() {
		picrossResult.add(new int[][]{{0, 0, 1, 0, 0},
                                    {0, 0, 1, 0, 0}, 
                                    {0, 1, 1, 1, 0}, 
                                    {0, 1, 0, 1, 0}, 
									{0, 1, 1, 1, 0}});
      picrossResult.add(new int[][]{{0, 1, 0, 1, 0},
                                    {1, 0, 0, 0, 1}, 
                                    {1, 0, 0, 0, 1}, 
                                    {1, 0, 0, 0, 1}, 
									{0, 1, 1, 1, 0}});
      picrossResult.add(new int[][]{{0, 0, 0, 0, 1},
                                    {0, 0, 0, 1, 0}, 
                                    {1, 0, 1, 0, 0}, 
                                    {0, 1, 0, 0, 0}, 
									{1, 0, 1, 0, 0}});
      picrossResult.add(new int[][]{{0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 1}, 
                                    {0, 1, 0, 1, 0}, 
                                    {0, 1, 1, 1, 0}, 
									{0, 1, 1, 1, 0}}); 
      picrossResult.add(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                                    {0, 0, 0, 0, 0, 0, 0, 1, 1, 0}, 
                                    {1, 0, 0, 0, 0, 0, 1, 0, 1, 0}, 
									{0, 1, 1, 1, 1, 1, 0, 0, 1, 0},
                                    {0, 0, 0, 0, 1, 1, 1, 1, 1, 0},
                                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 0, 0, 1, 0, 0, 1, 0},
                                    {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
                                    {0, 1, 0, 0, 0, 0, 0, 0, 1, 0}}); 
      picrossResult.add(new int[][]{{0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, 
                                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, 
                                    {0, 0, 0, 1, 1, 1, 1, 1, 0, 0}, 
									{0, 0, 0, 1, 1, 0, 1, 1, 0, 0},
                                    {0, 0, 0, 1, 0, 1, 0, 1, 0, 0},
                                    {0, 0, 0, 1, 1, 0, 1, 1, 0, 0},
                                    {0, 0, 0, 1, 0, 1, 0, 1, 0, 0},
                                    {0, 0, 0, 1, 1, 0, 1, 1, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 1, 1, 0, 0}});
      picrossResult.add(new int[][]{{0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                                    {0, 0, 1, 1, 1, 1, 1, 1, 0, 0}, 
                                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1}, 
                                    {1, 1, 0, 0, 1, 0, 0, 0, 1, 1}, 
									{0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}}); 
      picrossResult.add(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0, 0, 0, 1, 0, 0}, 
                                    {0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, 
                                    {0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, 
									{0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                                    {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                                    {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                                    {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                                    {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0}});
      picrossResult.add(new int[][]{{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 1, 0, 0, 1, 0}, 
                                    {0, 0, 0, 0, 1, 1, 0, 1, 0, 0}, 
                                    {0, 0, 0, 0, 1, 1, 1, 0, 0, 1}, 
									{0, 0, 0, 0, 1, 1, 1, 1, 1, 0},
                                    {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                                    {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0}}); 
      picrossResult.add(new int[][]{{0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                                    {0, 0, 1, 1, 1, 1, 1, 1, 0, 0}, 
                                    {0, 1, 1, 1, 0, 0, 1, 1, 1, 0}, 
                                    {1, 1, 1, 0, 0, 0, 0, 1, 1, 1}, 
									{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                                    {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                                    {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});
      picrossResult.add(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
                                    {0, 1, 1, 0, 0, 0, 0, 1, 0, 1}, 
                                    {1, 0, 0, 1, 0, 0, 1, 0, 1, 0}, 
									{1, 1, 1, 1, 1, 1, 0, 1, 0, 0},
                                    {0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                                    {1, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                                    {1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}); 
      picrossResult.add(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0}, 
                                    {0, 0, 1, 1, 1, 1, 1, 1, 0, 0}, 
                                    {0, 0, 1, 1, 0, 0, 1, 1, 0, 0}, 
									{0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0}});   
      picrossResult.add(new int[][]{{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0}, 
                                    {0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0}, 
                                    {0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0}, 
                                    {0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0},
                                    {1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1},
                                    {0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1},
                                    {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0},
                                    {0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0}});   
	}

	// public void printArray(int[][] array) {
	// 	for(int i = 0; i < 10; i++) {
	// 		System.out.println("------");
	// 		for(int k = 0; k < 10; k++) {
	// 			System.out.print(array[i][k] + " ");
	// 		}
	// 	}
	// }

	// This is the same method from Canvas. I decieded to copy it instead of sending over a bunch of variables
	public void setPicrossSizes(int currentPicrossSize) {
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

	// Returns -1, -1 if there is nothing wrong. Or number where the problem is.
	public int[] checkPicrossAccuracy(ArrayList<int[]> fill, ArrayList<int[]> cross, int currentAct, int npcId) {
		int actIndex = this.getActIndex(currentAct, npcId);
		int picrossSize = 0;
		if(picrossResult.get(actIndex)[0].length == 10) {
			picrossSize = 1;
		} else if(picrossResult.get(actIndex)[0].length == 15) {
			picrossSize = 2;
		}
		this.setPicrossSizes(picrossSize);
		for(int i = 0; i < fill.size(); i++) {
			int x = (fill.get(i)[0]-xOffset)/boxSize;
			int y = (fill.get(i)[1]-yOffset)/boxSize;
			if(picrossResult.get(actIndex)[y][x] != 1) { // Tried to put a fill in the wrong spot.
				return new int[]{x, y, 1};
			}
		}	
		for(int i = 0; i < cross.size(); i++) {
			int x = (cross.get(i)[0]-xOffset)/boxSize;
			int y = (cross.get(i)[1]-yOffset)/boxSize;
			if(picrossResult.get(actIndex)[y][x] != 0) { // Tried to put a cross in the wrong spot.
				return new int[]{x, y, 0};
			}
		}
		if(fill.size() == this.totalFillsInPicross(actIndex)) { // Completed the picross
			return new int[1];
		}
		return new int[2];
	}

	public int totalFillsInPicross(int actIndex) {
		int totalFill = 0;
		for(int i = 0; i < picrossResult.get(actIndex).length; i++) {
			for(int k = 0; k < picrossResult.get(actIndex)[i].length; k++) {
				if(picrossResult.get(actIndex)[i][k] == 1) {
					totalFill += 1;
				}
			}
		}
		return totalFill;
	}

	// Scanning picrossResults makes the top numbers flipped.
	// This is to reverse those numbers and it was placed here instead of in canvas.
	public ArrayList<Integer> reverse(ArrayList<Integer> list) {
		ArrayList<Integer> output = new ArrayList<>();
		for(int i = list.size()-1; i >= 0; i--) {
			output.add(list.get(i));
		}
		return output;
	}

	// There might be a better way of doing this with math and patterns.
	public int getActIndex(int currentAct, int npcId) {
		int actIndex = 0;
		if(npcId == -1) {
			actIndex = 0;
		}
		switch(currentAct) {
			case 0:
				switch(npcId) {
					case 1:
						actIndex = 1;
						break;
					case 2:
						actIndex = 2;
						break;	
					case 3:
						actIndex = 3;
						break;	
					case 5: // Ibis
						actIndex = 4;
						break;	
				}
				break;	
			case 1:
				switch(npcId) {
					case 1:
						actIndex = 5;
						break;
					case 2:
						actIndex = 6;	
						break;
					case 3:
						actIndex = 7;	
						break;
					case 5:
						actIndex = 8;	
						break;
				}	
				break;
			case 2:
				switch(npcId) {
					case 0:
						actIndex = 9;
						break;
					case 1:
						actIndex = 10;
						break;
					case 2:
						actIndex = 11;
						break;
				}
				break;
			case 3:
				actIndex = 12;
				break;
		}
		return actIndex;
	}

	// We go through each number left to right and count how many 1 appear and add it to blackX. 
	// If a 0 occures and blackX is >1 then it gets added to temp. Once the entire line is complete it is added to output
	// After each line is done we essientally flip the array and do it over again to get the other dimension.
    public ArrayList<ArrayList<Integer>> getPicrossNumbers(int currentAct, int npcId) {
		int actIndex = this.getActIndex(currentAct, npcId);
		ArrayList<ArrayList<Integer>> output = new ArrayList<>();
		int x = picrossResult.get(actIndex)[0].length; 
		int y = picrossResult.get(actIndex).length;
		boolean flipped = false;
		// Goes through the picross vertically then horizontally.
		for(int t = 0; t < 2; t++) {
			// This is to flip the X and Y to another orientation
			int carry = x;
			x = y;
			y = carry;
			for(int i = 0; i < y; i++) { // I is the y value
				int blackX = 0;
				ArrayList<Integer> temp = new ArrayList<>(); 
				// We have to go through it backwards because of the way we draw it in the canvas.
				for(int j = x-1; j >= 0; j--) { // J is the x value
					// The J and I are swapped
					int picResult = (flipped) ? picrossResult.get(actIndex)[i][j] : picrossResult.get(actIndex)[j][i];
					if(picResult == 1) {
						blackX++;
						continue;
					} else if(blackX != 0) {
						temp.add(blackX);
						blackX = 0;	
					}
				}
				if(blackX != 0) {
					temp.add(blackX);
					blackX = 0;
				}
				if(temp.isEmpty()) {
					temp.add(0);
				}
				output.add(temp);
			}
			flipped = true;
		}
        return output;
    }
}    