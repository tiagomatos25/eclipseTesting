
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;



public class FrameTest {
	private JFrame frame;
	private ELDraw el;
	private CLDraw cl;
	JLabel labelG1 = new JLabel("Game 1");
	JLabel labelG2 = new JLabel("Game 2");
	JLabel labelG3 = new JLabel("Game 3");
	JLabel labelG4 = new JLabel("Game 4");
	JLabel labelG5 = new JLabel("Game 5");
	JLabel labelG6 = new JLabel("Game 6");
	JLabel labelG7 = new JLabel("Game 7");
	JLabel labelG8 = new JLabel("Game 8");
	
	JButton button = new JButton("Draw");
	
	JLabel labelT1 = new JLabel("Team 1 from Pot 1");
	JLabel labelT2 = new JLabel("Team 2 from Pot 1");
	JLabel labelT3 = new JLabel("Team 3 from Pot 1");
	JLabel labelT4 = new JLabel("Team 4 from Pot 1");
	JLabel labelT5 = new JLabel("Team 5 from Pot 1");
	JLabel labelT6 = new JLabel("Team 6 from Pot 1");
	JLabel labelT7 = new JLabel("Team 7 from Pot 1");
	JLabel labelT8 = new JLabel("Team 8 from Pot 1");
	
	JLabel labelT1P2 = new JLabel("Team 1 from Pot 2");
	JLabel labelT2P2 = new JLabel("Team 2 from Pot 2");
	JLabel labelT3P2 = new JLabel("Team 3 from Pot 2");
	JLabel labelT4P2 = new JLabel("Team 4 from Pot 2");
	JLabel labelT5P2 = new JLabel("Team 5 from Pot 2");
	JLabel labelT6P2 = new JLabel("Team 6 from Pot 2");
	JLabel labelT7P2 = new JLabel("Team 7 from Pot 2");
	JLabel labelT8P2 = new JLabel("Team 8 from Pot 2");	
	JLabel BLANK = new JLabel(new ImageIcon("scp.png"));
	public FrameTest(String title, int width, int height) {
		frame = new JFrame(title);
		
		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		addFrameContent();
		
		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel
		//frame.pack();
	
	}

	public void open() {
		// para abrir a janela (torna-la visivel)
		frame.setVisible(true);
	}

	private void addFrameContent() {
			
		/* para organizar o conteudo em grelha (linhas x colunas)
		se um dos valores for zero, o numero de linhas ou colunas (respetivamente) fica indefinido,
		e estas sao acrescentadas automaticamente */
		frame.setLayout(new GridLayout(9,3));
		
		frame.add(labelG1); frame.add(labelT1);	frame.add(labelT1P2);		
		frame.add(labelG2); frame.add(labelT2);	frame.add(labelT2P2);	
		frame.add(labelG3);	frame.add(labelT3);	frame.add(labelT3P2);	
		frame.add(labelG4);	frame.add(labelT4);	frame.add(labelT4P2);
		frame.add(labelG5); frame.add(labelT5);	frame.add(labelT5P2);	
		frame.add(labelG6);	frame.add(labelT6);	frame.add(labelT6P2);	
		frame.add(labelG7); frame.add(labelT7);	frame.add(labelT7P2);
		frame.add(labelG8);	frame.add(labelT8);	frame.add(labelT8P2);
		
		frame.add(BLANK);
		
		JButton button = new JButton("Draw");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
	
				ELDraw.draw();
				CLDraw.draw();
			}
		});
		
		//frame.add(button);
		
		
		
		JButton button2 = new JButton("Draw One Team");
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
	
			//	ELDraw.drawOne();
				CLDraw.drawOne();
			}
		});
		
		frame.add(button2);
	}
	

	public static void main(String[] args) {
		FrameTest window = new FrameTest("Hello", 820, 600);
		window.open();
	}
}