package Object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Chest extends SuperObject {
	
	public Chest()
	{
		name = "Chest";
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/Objects/chest.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
