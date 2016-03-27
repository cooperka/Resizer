import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.thebuzzmedia.imgscalr.Scalr;

public class ListFiles
{
	static int size = 950, progress = 0;
	static double total = .001;

	public static void main(String[] args)
	{
		String path;

		if (args.length > 2 || !args[1].matches("[0-9]*"))
		{
			//Shrinks all .jpg or .png within DIRECTORY to fit in box of size PIXELS
			System.out.println("\nERROR! Usage: java -jar RESIZE.jar DIRECTORY PIXELS");
			if (!args[1].matches("[0-9]*"))
				System.out.println("\n*** PIXELS MUST BE A NUMBER! ***\n");
			if (args.length > 2)
				System.out.println("\n*** ROOT DIRECTORY MUST NOT HAVE SPACES IN THE NAME! ***\n");
			return;
		}
		else
		{
			path = args[0].replace("\"", ""); // "C:\\Users\\Kevin\\Desktop\\TEST\\";
			if (path.charAt(path.length() - 1) != '\\')
			{
				//path = path.substring(0, path.length() - 1);
				path += "\\";
			}
			size = Integer.parseInt(args[1]);
		}

		System.out.println("\nResizer v1.1 - Copyright 2011 Kevin Cooper\nInitializing...\n");
		getPrelim(path);

		System.out.print("Found " + (int)total + " pictures, press ENTER to begin (or Q to quit) ... ");
		char ch = '\u0000';
		while(true)
		{
			try
			{
				ch = (char) System.in.read();
				if (ch == 'q' || ch == 'Q')
					return;
				else if (ch != '\u0000')
					break;
			}
			catch(IOException e) { }
		}

		System.out.println("");
		getFiles(path);

		System.out.println("\nFinished.");
	}

	public static void getPrelim(String path)
	{
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++)
		{
			if (listOfFiles[i].isFile())
			{
				if (listOfFiles[i].getName().toLowerCase().contains(".jpg"))
				{
					total++;
				}
				else if (listOfFiles[i].getName().toLowerCase().contains(".png"))
				{
					total++;
				}
			}

			else if (listOfFiles[i].isDirectory())
			{
				getPrelim(path + listOfFiles[i].getName() + "\\");
			}
		}
	}

	public static void getFiles(String path)
	{
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++)
		{
			if (listOfFiles[i].isFile())
			{
				if (listOfFiles[i].getName().toLowerCase().contains(".jpg"))
				{
					System.out.print("Converting " + listOfFiles[i].getName() + " ...");
					convert(path + listOfFiles[i].getName(), "jpg");
					progress++;
					System.out.print(" done. ");
					for (int k = ("Converting " + listOfFiles[i].getName() + " ... done. ").length(); k < 60; k++)
						System.out.print(" ");
					System.out.println("(" + Math.ceil(progress/total*1000.0)/10.0 + "%)");
				}
				else if (listOfFiles[i].getName().toLowerCase().contains(".png"))
				{
					System.out.print("Converting " + listOfFiles[i].getName() + " ...");
					convert(path + listOfFiles[i].getName(), "png");
					progress++;
					System.out.print(" done. ");
					for (int k = ("Converting " + listOfFiles[i].getName() + " ... done. ").length(); k < 60; k++)
						System.out.print(" ");
					System.out.println("(" + Math.ceil(progress/total*1000.0)/10.0 + "%)");
				}
			}

			else if (listOfFiles[i].isDirectory())
			{
				getFiles(path + listOfFiles[i].getName() + "\\");
			}
		}
	}

	public static void convert(String path, String type)
	{
		BufferedImage srcImage = null;
		try
		{
			srcImage = ImageIO.read(new File(path));
		}
		catch (IOException e) { }

		BufferedImage scaledImage = Scalr.resize(srcImage, size); // ppt slide is around 950x720

		File imageFile = new File(path);
		try
		{
			ImageIO.write(scaledImage, type, imageFile);
		} catch (IOException e) { }
	}
}
