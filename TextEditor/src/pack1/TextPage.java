package pack1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextPage extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JComboBox fsize;
	private JComboBox fontfamily;
	private JComboBox fstyle;
	private JLabel nchars;
	private JLabel nwords;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextPage frame = new TextPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TextPage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\HP\\Downloads\\icons8-text-editor-64.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 25, 700, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 380, 597);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) 
			{
				int chars=textArea.getText().length();
				String[]split=textArea.getText().split(" ");
				
				int words=split.length;
				
				
				nchars.setText("characters :"+chars);
				nwords.setText("words :"+words);
				
			}
		});
		textArea.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 139, 139)), "ENTER YOUR TEXT HERE....", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		scrollPane.setViewportView(textArea);

		JButton btnNewButton = new JButton("Open");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dt = textArea.getText();
				if (!dt.isEmpty()) {
					int opt = JOptionPane.showConfirmDialog(getParent(), "Do You Want To Save this File?");
					if (opt == JOptionPane.YES_OPTION) {
						saveFile(dt);
					}
				}
				String data = openFile();
				textArea.setText(data);

			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(420, 11, 243, 49);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = textArea.getText();

				if (data.isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "Nothing to save");

				} else {
					saveFile(data);
				}
			}

		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBackground(Color.BLUE);
		btnNewButton_1.setBounds(420, 71, 243, 49);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Merge");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 int n= Integer.parseInt(JOptionPane.showInputDialog("Enter number of files you want to merge."));
					 String dt="";
					 while(n>0) {
						 String data=openFile()+"\n-----------------------------------------------\n";
						 dt+=data;
						 n--;
					 }
					 textArea.setText(dt);
				}
				catch(NumberFormatException ne){
					JOptionPane.showMessageDialog(getParent(),"Please provide number only!!");
				}
				
				 
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_2.setBackground(Color.BLUE);
		btnNewButton_2.setBounds(420, 131, 243, 49);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Set Background");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color bgcolor = JColorChooser.showDialog(getParent(), "select background color", Color.black);
				textArea.setBackground(bgcolor);
			}
		});
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_3.setBackground(Color.BLUE);
		btnNewButton_3.setBounds(420, 191, 243, 49);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_3_1 = new JButton("Set TextColor");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color textclr = JColorChooser.showDialog(getParent(), "Select text color", Color.black);
				textArea.setForeground(textclr);
			}
		});
		btnNewButton_3_1.setForeground(Color.WHITE);
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_3_1.setBackground(Color.BLUE);
		btnNewButton_3_1.setBounds(420, 251, 243, 49);
		contentPane.add(btnNewButton_3_1);

		JLabel lblNewLabel = new JLabel("Font Options");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(420, 330, 243, 43);
		contentPane.add(lblNewLabel);

		JLabel lblSize = new JLabel("Size");
		lblSize.setBackground(new Color(255, 255, 255));
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setForeground(Color.BLUE);
		lblSize.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSize.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		lblSize.setBounds(420, 384, 107, 43);
		contentPane.add(lblSize);

		JLabel lblFont = new JLabel("Font");
		lblFont.setBackground(new Color(255, 255, 255));
		lblFont.setHorizontalAlignment(SwingConstants.CENTER);
		lblFont.setForeground(Color.BLUE);
		lblFont.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFont.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		lblFont.setBounds(420, 441, 107, 43);
		contentPane.add(lblFont);

		JLabel lblStyle = new JLabel("Style");
		lblStyle.setBackground(new Color(255, 255, 255));
		lblStyle.setHorizontalAlignment(SwingConstants.CENTER);
		lblStyle.setForeground(Color.BLUE);
		lblStyle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStyle.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		lblStyle.setBounds(420, 502, 107, 43);
		contentPane.add(lblStyle);

		String fontsize[]= new String[100];
		for(int i=0;i<fontsize.length;i++) {
			
			fontsize[i]=String.valueOf(i+5);  
		}
		fsize = new JComboBox(fontsize);
		fsize.setBackground(new Color(255, 255, 255));
		fsize.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) 
			{
			    int size=Integer.parseInt(fsize.getSelectedItem().toString()) ;
		        String string=fontfamily.getSelectedItem().toString();
		        int style=fstyle.getSelectedIndex();
				//Font ff= new Font(name,style,size);
		        textArea.setFont(new Font(string,style,size));
		        textArea.updateUI(); 
			}
		});
		fsize.setFont(new Font("Tahoma", Font.BOLD, 15));
		fsize.setBounds(537, 388, 126, 39);
		contentPane.add(fsize);
		AutoCompleteDecorator.decorate(fsize);
		
	    Font[] allFonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
  String fonts[]= new String[allFonts.length];
  for(int i=0;i<fonts.length;i++) {
	  fonts[i]=allFonts[i].getFontName();
  }
		fontfamily = new JComboBox(fonts);
		fontfamily.setBackground(new Color(255, 255, 255));
		fontfamily.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) 
			{
				 int size=Integer.parseInt(fsize.getSelectedItem().toString()) ;
			        String string=fontfamily.getSelectedItem().toString();
			        int style=fstyle.getSelectedIndex();
					//Font ff= new Font(name,style,size);
			        textArea.setFont(new Font(string,style,size));
			        textArea.updateUI(); 
			}
		});
		fontfamily.setFont(new Font("Tahoma", Font.BOLD, 12));
		fontfamily.setBounds(537, 441, 126, 39);
		contentPane.add(fontfamily);
		AutoCompleteDecorator.decorate(fontfamily);
		
		//type Font select and press fn+f3 to get font class thn u will be able to see all indexes
		 

		fstyle = new JComboBox();
		fstyle.setBackground(new Color(255, 255, 255));
		fstyle.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) 
			{
				 int size=Integer.parseInt(fsize.getSelectedItem().toString()) ;
			        String string=fontfamily.getSelectedItem().toString();
			        int style=fstyle.getSelectedIndex();
					//Font ff= new Font(name,style,size);
			        textArea.setFont(new Font(string,style,size));
			        textArea.updateUI(); 
			}
		});
		fstyle.setModel(new DefaultComboBoxModel(new String[] {"Plain", "Bold", "Italic"}));
		fstyle.setFont(new Font("Tahoma", Font.BOLD, 15));
		fstyle.setBounds(537, 502, 126, 39);
		contentPane.add(fstyle);
		
		JCheckBox wordwrap = new JCheckBox("WordWrap");
		wordwrap.setBackground(new Color(255, 255, 255));
		wordwrap.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				boolean selected= wordwrap.isSelected();
				if(selected) {
					textArea.setLineWrap(true);
					scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				}
				else {
					textArea.setLineWrap(false);
					scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				}
			 
			}
		});
		wordwrap.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		wordwrap.setForeground(new Color(0, 0, 255));
		wordwrap.setFont(new Font("Tahoma", Font.BOLD, 16));
		wordwrap.setBounds(486, 569, 119, 39);
		contentPane.add(wordwrap);
		
		nwords = new JLabel("Words: 0");
		nwords.setFont(new Font("Tahoma", Font.BOLD, 18));
		nwords.setBounds(20, 619, 142, 39);
		contentPane.add(nwords);
		
		nchars = new JLabel("Characters: 0");
		nchars.setFont(new Font("Tahoma", Font.BOLD, 18));
		nchars.setBounds(172, 619, 142, 39);
		contentPane.add(nchars);
	}

	public String openFile() {
		JFileChooser jfc = new JFileChooser();
		int option = jfc.showOpenDialog(getParent());
		String data = "";

		if (option == JFileChooser.APPROVE_OPTION) {

			// get file name and path

			File file = jfc.getSelectedFile();
			String path = file.getAbsolutePath();
			String name = file.getName();

			setTitle(name + " -Text Editor");

			// get data from file
			try {
				FileInputStream fis = new FileInputStream(path);
				data = new String(fis.readAllBytes());

				fis.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return data;
	}

	public void saveFile(String data) {
		JFileChooser jfc = new JFileChooser();
		int option = jfc.showSaveDialog(getParent());

		if (option == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			File dir = jfc.getCurrentDirectory();
			String name = file.getName();
			setTitle(name + " -Text Editor");

			// check if file already exists or not

			File[] files = dir.listFiles();

			boolean status = false;
			for (File f : files)

				if (name.equalsIgnoreCase(f.getName())) {
					int opt = JOptionPane.showConfirmDialog(getParent(),
							"File Already Exists ! Want to over write the file?");
					if (opt == JOptionPane.YES_OPTION) {

						break;
					} else if (opt == JOptionPane.NO_OPTION) {
						saveFile(data);
						status = true;
						break;
					}

				}
			if (!status) {
				// save file
				FileWriter fw;
				try {

					fw = new FileWriter(file);
					fw.write(data);
					fw.close();
					JOptionPane.showMessageDialog(getParent(), "File Saved Successfully!!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
