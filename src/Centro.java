import java.awt.Color;

public class Centro 
{
	double x, y, z;
	Color color;
	//generando color random
	Centro(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
	}
	
	static void create(int n, Centro arr[])
	{
		int x, y, z;
		for(int i = 0; i < n; i++)
		{
			x = (int) (Math.random()*100);
			y = (int) (Math.random()*100);
			z = (int) (Math.random()*100);
			arr[i] = new Centro(x, y, z);
		}
	}

}
