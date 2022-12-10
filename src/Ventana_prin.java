import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.*;
//import java.util.ArrayList;
//para el panel
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.Timer;

public class Ventana_prin extends JFrame
{ //creando ventana principal metods


	Timer t;
	private JFrame frame;
	private JPanel panel;
	int k = 20; //inicializando 
	int num_centros = 2;	//inicializando numero de centros en le plano
	int num_puntos = 100;	//inicializando numero de puntos en el plano
	algo_k arr[];
	Centro arrCent[];
	
	public Ventana_prin(String title) 
	{
		//cambios de tiempo de actualizacion
		t = new Timer(5000, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				getFrame().repaint();
			}
		});
		inicializa(title);
	}

	private void inicializa(String title) 
	{
		if(num_puntos >= 1000)
		{
			System.exit(1);
		}
		arr = new algo_k[num_puntos];
		algo_k.create(num_puntos, arr);
		arrCent = new Centro[num_centros];
		Centro.create(num_centros, arrCent);
		
		frame = new JFrame(title) {
			@Override
			public void paint(Graphics g) 
			{
				super.paint(g);
				t.start();
				g.setColor(new Color(0,0,0)); //cambio de color del cubo
				double arc = Math.toRadians(k);
				double x =  Math.cos(arc);
				double y =  Math.sin(arc);
				int inicia_x = 400 + (int)(404*x);
				int inicia_y = 700 + (int)(96*y);
				int dx1 = (int)(404*x) + (int)(404*y);
				int dy1 = (int)(96*y) - (int)(96*x);
				int dx2 = (int)(404*x) - (int)(404*y);
				int dy2 = (int)(96*y) + (int)(96*x);
				
				for(int i = 0; i < 2; i++)
				{ //creando plano 33d
					g.drawLine(400 - (int)(404*x), 700 - (int)(96*y) - i*500,400 - (int)(404*y), 700 + (int)(96*x) - i*500);
						g.drawLine(400 - (int)(404*x), 700 - (int)(96*y) - i*500,400 + (int)(404*y), 700 - (int)(96*x) - i*500);
						g.drawLine(400 + (int)(404*x), 700 + (int)(96*y) - i*500,400 - (int)(404*y), 700 + (int)(96*x) - i*500);
					g.drawLine(400 + (int)(404*x), 700 + (int)(96*y) - i*500,400 + (int)(404*y), 700 - (int)(96*x) - i*500);
				}
	
					g.drawLine(400 - (int)(404*x), 700 - (int)(96*y), 400 - (int)(404*x), 200 - (int)(96*y));
					g.drawLine(400 + (int)(404*x), 700 + (int)(96*y), 400 + (int)(404*x), 200 + (int)(96*y));
				g.drawLine(400 - (int)(404*y), 700 + (int)(96*x), 400 - (int)(404*y), 200 + (int)(96*x));
				g.drawLine(400 + (int)(404*y), 700 - (int)(96*x), 400 + (int)(404*y), 200 - (int)(96*x));

				


				//asignando colores puntos y clases
				g.setColor(Color.BLACK);//RED
				if(!algo_k.singleItteration(arr, arrCent)) t.stop();
				for(int i = 0; i < num_puntos; i++)
				{
					g.setColor(arrCent[arr[i].get_class()].color);
					g.fillOval(inicia_x  - dx1*arr[i].x/100 - dx2*arr[i].y/100, inicia_y - dy1*arr[i].x/100 - dy2*arr[i].y/100 - 500*arr[i].z/100, 4, 4); //puntos aleatorios asignar tamañó
					//g.drawOval(inicia_x  - dx1*arr[i].x/100 - dx2*arr[i].y/100, inicia_y - dy1*arr[i].x/100 - dy2*arr[i].y/100 - 500*arr[i].z/100, 3, 1); //puntos aleatorios asignar tamañó
				}
				for(int i = 0; i < num_centros; i++)
				{
					g.setColor(arrCent[i].color);
					g.fillOval(inicia_x  - (int)(dx1*arrCent[i].x/100) - (int)(dx2*arrCent[i].y/100), inicia_y - (int)(dy1*arrCent[i].x/100) - (int)(dy2*arrCent[i].y/100) - (int)(500*arrCent[i].z/100), 10, 10);//clases puntos asignados color
					//fillOval
				}
			}
		};

		if(num_puntos >= 100000)
		{
			System.exit(0);
		}


		getFrame().getContentPane().setBackground(new Color(155,155,155)); //color del panel donde esta cubo
	    getFrame().setBounds(10, 10, 950, 900); //tamaño de marco panel
	    getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getFrame().getContentPane().setLayout(null);
	    getFrame().setResizable(false);
	    
	    
	    panel = new JPanel();
	    panel.setBackground(new Color(155,155,155));//color del panel de opciones
	    panel.setBounds(750, 0, 200, 200); //tamanño de asignacion de opciones
	    getFrame().getContentPane().add(panel);
	    
		//boton de salir;
		JButton salir = new JButton("Salir");
		salir.setBounds(890,820,63,18); //boton salir
		panel.add(salir);
		//System.exit(0);

		//boton de numeros de puntos
	    JLabel texto_num_puntos = new JLabel("Numero de puntos");
	    TextField leer_num_puntos = new TextField();
	    leer_num_puntos.setPreferredSize(new Dimension(50,20));//tamaño de la caja puntos
	    leer_num_puntos.setText("" + num_puntos);
		if(num_puntos >= 1000)
		{
			System.exit(0);
		}

	    //boton de numeros de centros
	    JLabel texto_num_centros = new JLabel("Numero de centros");
	    TextField leer_num_centros = new TextField();
	    leer_num_centros.setPreferredSize(new Dimension(50,20));//tamaño de la caja centross
		leer_num_centros.setText("" + num_centros);
		// if(num_centros <= 100){
			
		// }

	    //generar plano 
	    JButton gen_plan = new JButton("Generar plano");
	    gen_plan.setPreferredSize(new Dimension(150, 30));
	    gen_plan.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(num_puntos >= 900000)
				{
					System.exit(0);
				}
				num_centros = Integer.parseInt(leer_num_centros.getText());
				num_puntos = Integer.parseInt(leer_num_puntos.getText());
				arr = new algo_k[num_puntos];
				algo_k.create(num_puntos, arr);
				
				arrCent = new Centro[num_centros];
				Centro.create(num_centros, arrCent);
				getFrame().repaint();
			}
	    });
	    
	    panel.add(texto_num_puntos);
	    panel.add(leer_num_puntos);
	    panel.add(texto_num_centros);
	    panel.add(leer_num_centros);
		panel.add(salir);
	    panel.add(gen_plan);
	    
	    getFrame().addMouseWheelListener(new MouseWheelListener()
		{
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) 
			{
				k += e.getWheelRotation();
				getFrame().repaint();	
			}
		});
	}

	public JFrame getFrame() 
	{
		return frame;
	}

	public void setFrame(JFrame frame) 
	{
		this.frame = frame;
	}

	public void run() 
	{
		while (true) 
		{
			try
			{
				Thread.sleep(200);
			}catch(InterruptedException ex)
			{
				//break;
				System.exit(0);
			}
		}
	}
}
