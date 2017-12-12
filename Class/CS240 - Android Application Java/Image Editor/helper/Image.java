import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class Image 
{

	private String MagicNumber;
	private int width;
	private int height;
	private int cur_col; //width
	private int cur_row; //height
	private int maxColorValue=255;
	private Pixel[][] pixels;
	
	public Image(int height, int width)
	{
		pixels = new Pixel[height][width];
		cur_row = 0;
		cur_col = 0;
		this.height = height;
		this.width = width;
	}
	//Math.abs();
	
	public void insertPixel(Pixel p)
	{
		if(cur_col < width && cur_row < height)
		{
			pixels[cur_row][cur_col] = p;
			if(cur_col == width-1)
			{
				cur_col = 0;
				cur_row++;
			}
			else
				cur_col++;
		}
	}
	/*public Image insertPixel(int h, int w, Pixel p) 
	{
		pixels[h][w] = p;
		return this;
	}*/
	
	public Pixel getPixel(int h, int w) 
	{
		return pixels[h][w];
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getMax()
	{
		return maxColorValue;
	}
	
	public void invert()
	{
		for(int h = 0; h < height; h++)
		{
			for(int w = 0; w < width; w++)
			{
				pixels[h][w].invert();
			}
		}
	}
	
	public void grayscale()
	{
		for(int h = 0; h < height; ++h)
		{
			for(int w = 0; w < width; ++w)
			{
				pixels[h][w].grayscale();
			}
		}
	}
	
	public void emboss()
	{
		Pixel[][] embossed = new Pixel[height][width];
		for(int h = 0; h < height; h++)
		{
			for(int w = 0; w < width; w++)
			{
				if(h-1 < 0 || w-1 < 0)
				{
					embossed[h][w] = new Pixel(128, 128, 128);
				}
				else
				{
					Pixel p = pixels[h][w];
					int redDiff = p.getRed() - pixels[h-1][w-1].getRed();
					int greenDiff = p.getGreen() - pixels[h-1][w-1].getGreen();
					int blueDiff = p.getBlue() - pixels[h-1][w-1].getBlue();
					
					int maxDiff = redDiff;
					if(Math.abs(maxDiff) < Math.abs(greenDiff))
						maxDiff = greenDiff;
					if(Math.abs(maxDiff) < Math.abs(blueDiff))
						maxDiff = blueDiff;
					
					int v = 128 + maxDiff;
					if(v < 0)
						v = 0;
					else if(v > 255)
						v = 255;
					
					embossed[h][w] = new Pixel(v, v, v);
				}
			}
		}
		pixels = embossed;
	}
	
	public void motionBlur(int n) // n must be greater than 0. n degree
	{
		Pixel[][] blurred = new Pixel[height][width];
		for(int h = 0; h < height; h++)
		{
			for(int w = 0; w < width; w++)
			{
				int redSum = 0;
				int greenSum = 0;
				int blueSum = 0;
				int col;
				for(col = 0; col < n && col+w < width; col++)
				{
					
					redSum += pixels[h][w+col].getRed();
					greenSum += pixels[h][w+col].getGreen();
					blueSum += pixels[h][w+col].getBlue();
				}
				if(col != 0)
					blurred[h][w] = new Pixel(redSum/col, greenSum/col, blueSum/col);
				else
					blurred[h][w] = new Pixel(pixels[h][w].getRed(), pixels[h][w].getGreen(), pixels[h][w].getBlue());
			}
		}
		pixels = blurred;
	}
	
	public Image (String inputFilename)
	{
		try{
		Scanner s = new Scanner(new BufferedInputStream(new FileInputStream (inputFilename)));
		
		s.useDelimiter("(\\s+)(#[^\\n]*\\n)?(\\s*)|(#[^\\n]*\\n)(\\s*)");
		
		MagicNumber = s.next(); //p3
		width = s.nextInt();
		height = s.nextInt();
		maxColorValue = s.nextInt(); //maxColorValue 255
		pixels = new Pixel[height][width];

		while (s.hasNext())
		{
			for(int h = 0; h < height; h++)
			{
				for(int w = 0; w < width; w++)
				{
					pixels[h][w] = new Pixel(s.nextInt(), s.nextInt(), s.nextInt());
					//pixels[h][w].setRed(s.nextInt());
					//pixels[h][w].setGreen(s.nextInt());
					//pixels[h][w].setBlue(s.nextInt());
				}
			}
		}
		s.close();
		}
		catch (Exception e)
		{
			System.err.println("Invalid arguments");
			e.printStackTrace();
		}
		
	}
	
	public void writeToFile(String outputFilename)
	{
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));
			
			pw.print(MagicNumber);
			pw.println();
			pw.print(width);
			pw.print(" ");
			pw.print(height);
			pw.println();
			pw.print(maxColorValue);
			pw.println();

			for(int h = 0; h < height; h++)
			{
				for(int w = 0; w < width; w++)
				{
					pw.print(pixels[h][w].getRed());
					pw.println();
					pw.print(pixels[h][w].getGreen());
					pw.println();
					pw.print(pixels[h][w].getBlue());
					pw.println();
					//pw.println(pixels[h][w]);
				}
				//pw.println("\n");
			}
			pw.close();
		}
		catch (Exception e)
		{
			System.err.println("Failed!");
			e.printStackTrace();
		}
	}
}
