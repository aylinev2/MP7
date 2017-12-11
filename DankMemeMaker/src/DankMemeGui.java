import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DankMemeGui extends JFrame implements ActionListener{

	private static final String TITLE = "DANKEST MEME GENERATOR 9000";
	private static final int DEFAULT_WIDTH = 640;
	private static final int DEFAULT_HEIGHT = 480;
	private static final int TEXT_BOX_WIDTH = 160;
	
	private GridBagConstraints gbc;
	
	private ImageDisplay memePanel;
	private JPanel controlPanel;
	private JButton load, generate, save;
	private JFileChooser fileDialog;
	private JTextField textBox;
	
	private BufferedImage loadedImage;
	private BufferedImage memedImage;
	
	private Meme meme;
	
	public DankMemeGui() {
		super(TITLE); 
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridBagLayout());
		memePanel = new ImageDisplay();
		
		
		fileDialog = new JFileChooser();
		textBox = new JTextField(TEXT_BOX_WIDTH);
		
		load = new JButton("Load");
		
		generate = new JButton("MEME!");
		generate.setEnabled(false);
		
		save = new JButton("Save");
		save.setEnabled(false);
		

		
		
		gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(10, 10, 10, 10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		controlPanel.add(textBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		
		controlPanel.add(load, gbc);
		
		gbc.gridx = 1;
		
		controlPanel.add(generate, gbc);
		
		gbc.gridx = 2;
		
		controlPanel.add(save, gbc);
		
		//controlPanel.setVisible(true);
		
		add(controlPanel, BorderLayout.SOUTH);
		add(memePanel, BorderLayout.CENTER);
		
		
		load.addActionListener(this);
		generate.addActionListener(this);
		save.addActionListener(this);
		
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source.equals(load)) {
			try {
				load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(source.equals(save)) {
			try {
				save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(source.equals(generate)) {
			generate();
		}
		
	}
	
	private void load() throws IOException {
		if(!(fileDialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)) return;
		
		loadedImage = ImageIO.read(fileDialog.getSelectedFile());
		dispImage(loadedImage);
		
		generate.setEnabled(true);
	}

	private void save() throws IOException {
		
		if(fileDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			System.out.println(fileDialog.getSelectedFile().getName());
			ImageIO.write(memedImage, "jpg", fileDialog.getSelectedFile());
		}
		
	}

	private void generate() {
		
		System.out.println(textBox.getText());
		meme = new DankMeme(loadedImage, textBox.getText());
		
		memedImage = meme.getMeme();

		dispImage(memedImage);

		save.setEnabled(true);
	}
	
	private void dispImage(BufferedImage img) {
		memePanel.setImage(img);
	}
	
	
	public static void main(String... args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new DankMemeGui();
	}
}
