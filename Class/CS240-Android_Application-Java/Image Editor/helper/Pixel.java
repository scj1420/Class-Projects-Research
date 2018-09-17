
public class Pixel
{
	private int red;
	private int green;
	private int blue;
	
	public Pixel(int red_in, int green_in, int blue_in)
	{
		red = red_in;
		green = green_in;
		blue = blue_in;
	}
	
	public int getRed()
	{
		return red;
	}
	
	public int getGreen()
	{
		return green;
	}
	
	public int getBlue()
	{
		return blue;
	}
	
	public void setBlue(int set_val)
	{
		blue = set_val;
	}

	public void setGreen(int set_val)
	{
		green = set_val;
	}
	
	public void setRed(int set_val)
	{
		red = set_val;
	}
	
	public void invert()
	{
		red = 255 - red;
		green = 255 - green;
		blue = 255 - blue;
		//return new Pixel(max-red, max-green, max-blue);
	}
	
	public void grayscale()
	{
		int avg = (red + green + blue) / 3;
		red = avg;
		green = avg;
		blue = avg;
	}
	
}