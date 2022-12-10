public class algo_k {
	public short x, y, z;
	private int classter;
	
	//contructor
	public algo_k(short x, short y, short z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		set_class(-1);
	}
	
	static void create(int n, algo_k arr[])
	{
		short x, y, z;
		for(int i = 0; i < n; i++)
		{
			x = (short) (Math.random()*100);
			y = (short) (Math.random()*100);
			z = (short) (Math.random()*100);
			arr[i] = new algo_k(x, y, z);
		}
	}
	
	public static boolean singleItteration(algo_k arr[], Centro cent[])
	{
		boolean key = false;//iteraciones
		int minIndex;
		double minOdleglosc;
		double temp;
		int ilosc[] = new int[cent.length];
		double x[] = new double[cent.length];
		double y[] = new double[cent.length];
		double z[] = new double[cent.length];
		for(int i = 0; i < cent.length; i++)
		{
			x[i] = 0;
			y[i] = 0;
			z[i] = 0;
			ilosc[i] = 0;
		}

		//funcion de pila para hacer xyz
		for(int i = 0; i < arr.length; i++)
		{
			minIndex = Integer.MAX_VALUE;
			minOdleglosc = Integer.MAX_VALUE;
			for(int j = 0; j < cent.length; j++)
			{
				temp = Math.sqrt(Math.pow(arr[i].x-cent[j].x,2) + Math.pow(arr[i].y-cent[j].y,2) + Math.pow(arr[i].z-cent[j].z,2));
				if(temp < minOdleglosc)
				{
					minOdleglosc = temp;
					minIndex = j;
				}
			}
			x[minIndex] += arr[i].x;
			y[minIndex] += arr[i].y;
			z[minIndex] += arr[i].z;
			ilosc[minIndex]++;
			if(arr[i].get_class() != minIndex)
			{
				arr[i].set_class(minIndex);
				key = true;
			}
		}

		for(int i = 0; i < cent.length; i++)
		{
			cent[i].x = x[i]/ilosc[i];
			cent[i].y = y[i]/ilosc[i];
			cent[i].z = z[i]/ilosc[i];
		}
		return key;
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}

	public int get_class() 
	{
		return classter;
	}

	public void set_class(int classter) 
	{
		this.classter = classter;
	}
}
