package Object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Key extends SuperObject {
	
	public Key()
	{
		name = "Key";
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/Objects/key.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

}
