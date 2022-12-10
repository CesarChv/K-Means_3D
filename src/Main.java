import java.awt.EventQueue;

public class Main 
{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
	        public void run() {
	            try 
				{
	                Ventana_prin window = new Ventana_prin("Metodo k-means 3D");
	                window.getFrame().setVisible(true);
	            }
	            catch (Exception e) 
				{
	                e.printStackTrace();
	            }
	        }
	    });
		
	}
}
