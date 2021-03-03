//You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  
//Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

//The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, 
//so "a" is considered a different type of stone from "A".

class Solution {
    public int[][] foolFill(int[][] image, int sr, int sc, int newColor) {
       
        if(image[sr][sc] == newColor {
            return image;
        }
        
		fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
	
	public void fill(int[][] image, int i, int j, int color, int newColor){
		if(i<0 || i>= image.length || j<0 || j>= image[i].length || image[i][j] != color){
			return;
		}
		
		image[i][j] = newColor;
		fill(image,i+1,j,color,newColor);
		fill(image,i-1,j,color,newColor);
		fill(image,i,j+1,color,newColor);
		fill(image,i,j-1,color,newColor);
		
	}
	
	
}
