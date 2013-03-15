import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;


@SuppressWarnings("serial")
public class Display extends JComponent
					 implements MouseListener, ActionListener, KeyListener
{
	private JMenuBar menubar = new JMenuBar();
	private JMenu mnuFile = new JMenu("File");
	private JMenuItem mnuImport = new JMenuItem("Import");
	private JTextField input = new JTextField(20);
	private JPanel jp = new JPanel();
	private JButton btnGet = new JButton("Get random verb");
	private JButton btnCheck = new JButton("Check");
	
	private JLabel la = new JLabel("‡");
	private JLabel le = new JLabel("Ž");
	private JLabel li = new JLabel("’");
	private JLabel lo = new JLabel("—");
	private JLabel lu = new JLabel("œ");
	private JLabel ln = new JLabel("–");
	private JLabel lblSubject = new JLabel("Yo");
	private JLabel lblVerb = new JLabel(" ");
	private JLabel lblTense = new JLabel(" ");
	
	@SuppressWarnings("unused")
	private int currentVerb, lastVerb;
	@SuppressWarnings("unused")
	private int currentSub, lastSub;
	@SuppressWarnings("unused")
	private int currentTense, lastTense;
	
	private VerbData verbs[];
	
	public Display()
	{
		mnuFile.add(mnuImport);
		menubar.add(mnuFile);
		mnuImport.addActionListener(this);
		btnGet.addMouseListener(this);
		btnCheck.addMouseListener(this);
		la.addMouseListener(this);
		le.addMouseListener(this);
		li.addMouseListener(this);
		lo.addMouseListener(this);
		lu.addMouseListener(this);
		ln.addMouseListener(this);
		input.addKeyListener(this);
		
		
		jp.add(btnGet);
		jp.add(btnCheck);
		jp.add(lblSubject);
		jp.add(lblVerb);
		jp.add(lblTense);
		jp.add(input);
		jp.add(la);
		jp.add(le);
		jp.add(li);
		jp.add(lo);
		jp.add(lu);
		jp.add(ln);
		
		btnGet.setBounds(jp.getWidth() / 8, jp.getHeight() / 2 ,btnGet.getPreferredSize().width,btnGet.getPreferredSize().height);
		//btnCheck.setBounds(jp.getWidth() - jp.getWidth() / 8, jp.getHeight());
		//input.setBounds(jp.getWidth() / 2 - input.getWidth(), jp.getHeight());
		
	}

	public void importVerbs()
	{
		try
		{
			java.io.FileReader fr = new java.io.FileReader("verblist.txt");
			java.io.BufferedReader br = new java.io.BufferedReader(fr);
			int numOfVerbs = Integer.parseInt(br.readLine());
			verbs = new VerbData[numOfVerbs];
			for (int i = 0; i < numOfVerbs; i++)
			{
				String temp, s1, s2;
				boolean b1;
				temp = br.readLine();
				s1 = temp.substring(0, temp.indexOf(','));
				temp = temp.substring(temp.indexOf(','));
				
				b1 = temp.substring(0, temp.indexOf(',')) == "true" ? true : false;
				temp = temp.substring(temp.indexOf(','));
				
				s2 = temp;
				
				verbs[i] = new VerbData(s1,b1,s2);
			}
			br.close();
		} catch (java.io.IOException err)
		{
			System.err.println("Error opening file");
			err.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {}
	public void mouseClicked(MouseEvent e) 
	{
		if (e.getSource() == btnGet)
			getNewVerb();
		else if (e.getSource() == la)
			input.setText(input.getText() + "‡");
		else if (e.getSource() == le)
			input.setText(input.getText() + "Ž");
		else if (e.getSource() == li)
			input.setText(input.getText() + "’");
		else if (e.getSource() == lo)
			input.setText(input.getText() + "—");
		else if (e.getSource() == lu)
			input.setText(input.getText() + "œ");		
		else if (e.getSource() == ln)
				input.setText(input.getText() + "–");
		else if (e.getSource() == btnCheck)
			check();

	}
	public void keyPressed(KeyEvent e) 
	{
		if(e.getSource() == input)
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
				check();	
		}
	}

	public void check()
	{
		if (verbs[currentVerb].conjugate(currentTense, currentSub).equalsIgnoreCase(input.getText().trim()))
			{
				JOptionPane.showMessageDialog(null,"Correct!!");
				input.setText("");
				getNewVerb();
			}
		else
			JOptionPane.showMessageDialog(null,"Wrong");	
	}
	public void getNewVerb()
	{
		lastVerb = currentVerb;
		lastSub = currentSub;
		lastTense = currentTense;
		currentVerb = (int)(Math.random() * verbs.length - 1);
		currentSub = (int)(Math.random() * 5);
		currentTense = (int)(Math.random() * 3);
		lblSubject.setText(VerbData.Endings.subPronouns[currentSub]);
		lblVerb.setText(verbs[currentVerb].getInfinitive());
		lblTense.setText(VerbData.Endings.tenses[currentTense]);
	}
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	
	public static void main(String[] args) 
	{
	
		Display myDisplay = new Display();
		JFrame win = new JFrame("The Verbanator");
	
		myDisplay.importVerbs();
		
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(800, 175);
		//myDisplay.jp.setSize(win.getSize());
		win.add(myDisplay.menubar, BorderLayout.NORTH);
		win.add(myDisplay.jp);
		
		win.setVisible(true);	
	}
	
}