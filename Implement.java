//Author: Stefano Barbier
//Last Modified: August 22rd, 2016

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class Implement {

	//sets up i/o
	static InputStreamReader x = new InputStreamReader(System.in) ;  //reads the input
	static BufferedReader comingIn = new BufferedReader(x) ;		//reads line of input
	static PrintWriter goingOut = new PrintWriter(System.out , true) ;
	
	static String picturename = "trip.jpg";
	static String filename = "trip";
	
	public static void main(String[] args) {
		Interpretor();
	}
	
	public static void Interpretor(){
		
	    File input= new File(picturename);
	  
	    try {
			BufferedImage hugeImage = ImageIO.read(input);
			Color[][] result = convertTo2DUsingGetRGB(hugeImage);
			
			filewriter(result);
			
			goingOut.println("red: " + result[0][0].getRed() + "," + "green: " + result[0][0].getGreen() + "," + "blue: " + result[0][0].getBlue());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			goingOut.println("could not find file");
		}
	    
		}
		
	private static Color[][] convertTo2DUsingGetRGB(BufferedImage image) {
		
			int width = image.getWidth();
			int height = image.getHeight();
			Color[][] result = new Color[height][width];

			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					
					Color color = new Color(image.getRGB(col,row));
					result[row][col] = color;
					//result[row][col] = image.getRGB(col, row);
				
			    }
			}

			return result;
	}
	
	public static void filewriter(Color [][]truearray) throws IOException{  //newdata represents the place to store the file
	
		File file = new File(filename);

				try{ //create new file if it doesn't exist
				    if(!file.exists()){
				        file.createNewFile();
				    }
				    //if it does exist append this new information to the end of the text already in that file
				    FileWriter fileWriter = new FileWriter(file, true);
				    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				    
				    int colorintarray[] = new int[truearray[0].length*truearray.length];
				    
				    for (int col = 0; col < truearray[0].length; col++) {
				         for (int row = 0; row < truearray.length; row++) {
				        	 
				        	int colorint = findnearest(truearray[row][col]);
				     		colorintarray[row + col*60] = colorint;
				     		
				         }
				      }
				   
				    for (int i = 0; i <colorintarray.length; i++){
				    	bufferedWriter.write(colorintarray[i] + ",");
				    }
				    bufferedWriter.close();
				    
				    
				} catch(IOException e) {
				    System.out.println("Could not write file.");
				}
	}
	
	public static int findnearest(Color colorimport){
		
		Colorrep black = new Colorrep(0,0,0);
		Colorrep red = new Colorrep(255,0,0);
		Colorrep green = new Colorrep(0,255,0);
		Colorrep blue = new Colorrep(0,0,255);
		Colorrep lightblue = new Colorrep(0,255,255);
		Colorrep purple = new Colorrep(255,0,255);
		Colorrep yellow = new Colorrep(255,255,0);
		Colorrep white = new Colorrep(255,255,255);
		
		Colorrep []pallete = {black, red, green, blue, lightblue, purple, yellow, white};
		Colorrep mincolor = black; 
		
		double redsumsquare = Math.pow((pallete[0].getred() -colorimport.getRed()),2);
		double greensumsquare = Math.pow((pallete[0].getgreen() - colorimport.getGreen()),2);
		double bluesumsquare = Math.pow((pallete[0].getblue() - colorimport.getBlue()),2);
		
		double mindistance1 = Math.pow(redsumsquare + greensumsquare + bluesumsquare, 0.5);
		
		
		
		double mindistance =  Math.pow((Math.pow((pallete[0].getred() -colorimport.getRed()),2) + 
			Math.pow((pallete[0].getgreen() - colorimport.getGreen()),2) + Math.pow((pallete[0].getblue() - colorimport.getBlue()),2)),0.5);
		
		for (int i = 0; i < pallete.length; i++){
			double tempdistance = Math.pow((Math.pow((pallete[i].getred() - colorimport.getRed()),2) + 
					Math.pow((pallete[i].getgreen() - colorimport.getGreen()),2) + Math.pow((pallete[i].getblue() - colorimport.getBlue()),2)),0.5);
			if (tempdistance < mindistance){
				mindistance = tempdistance;
				mincolor = pallete[i];
			}
		}//end for loop

		if (mincolor == white){return 0;}
		else if(mincolor == red){return 1; }
		else if(mincolor == green){return 2;}
		else if(mincolor == blue){return 3;}
		else if(mincolor == lightblue){return 4;}
		else if(mincolor == purple){return 5;}
		else if (mincolor == yellow){return 6;}
		else {return 7;} //black
	
	}
	
}//end file
	
	

