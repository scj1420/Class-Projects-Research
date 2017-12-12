
public class ImageEditor {
	
	int r; //height
	int c; //width

	public static void main(String[] args)
	{
		//Image im = new Image (args[0]);
		//im.writeToFile(args[1]);
		
		try
		{
			if(args.length>=3 && args.length<=4)
			{
				Image im = new Image (args[0]);
					if(args[2].equals("invert"))
						im.invert();
					else if(args[2].equals("grayscale"))
						im.grayscale();
					else if(args[2].equals("emboss"))
						im.emboss();
					else if(args[2].equals("motionblur") && args.length==4)
						im.motionBlur(Integer.parseInt(args[3]));
					else
					{
						System.err.println("Invalid arguments");
						return;
					}
					im.writeToFile(args[1]);
			}
			else
			{
				System.err.println("Invalid arguments");
				return;
			}
			return;
			
		}
		catch (Exception e)
		{
			System.err.println("Failed!");
		}
	}	
}
