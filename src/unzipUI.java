import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.util.*;
import java.util.stream.StreamSupport;
import java.awt.Color;
;public class unzipUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					unzipUI window = new unzipUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public static String source="";
	public static String dest="";
	public static String fname="";
	public unzipUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static String[] convert(String pathstr)throws Exception
	{
		try {
		Path path = Paths.get(pathstr);
		return StreamSupport.stream(path.spliterator(), true).map(Path::toString).toArray(String[]::new);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new String[] {};
		}
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 153, 255));
		frame.setResizable(false);
		frame.setBounds(100, 100, 977, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblChooseTheFilefolder = new JLabel("Choose the file/folder to UNZIP");
		lblChooseTheFilefolder.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseTheFilefolder.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblChooseTheFilefolder.setBounds(196, 34, 584, 52);
		frame.getContentPane().add(lblChooseTheFilefolder);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(154, 96, 659, 303);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnChoose = new JButton("Choose  Zip File");
		btnChoose.setBackground(new Color(255, 204, 204));
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfile = new JFileChooser();
				jfile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfile.setFileHidingEnabled(false);
				if(jfile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					File file = jfile.getSelectedFile();
					source = file.getPath();
					String narr[] = source.split(":");
					
					try {
						String arr[] = convert(source);
						source = String.join("\\\\", arr);
						source = narr[0]+":\\\\"+source;
						fname = arr[arr.length-1];
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					textField.setText(source);
				}
			}
		});
		btnChoose.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnChoose.setBounds(158, 29, 345, 50);
		panel.add(btnChoose);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setBounds(115, 100, 448, 36);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnChooseExtractionPath = new JButton("Choose Location to Save");
		btnChooseExtractionPath.setBackground(new Color(255, 204, 204));
		btnChooseExtractionPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfile = new JFileChooser();
				jfile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfile.setFileHidingEnabled(false);
				if(jfile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					File file = jfile.getSelectedFile();
					String path = file.getPath();
					String narr[] = path.split(":");
				
					try {
						String arr[] = convert(path);
						dest = String.join("\\\\", arr);
						dest = narr[0]+":\\\\"+dest;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					textField_1.setText(dest);
				}
			}
		});
		btnChooseExtractionPath.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnChooseExtractionPath.setBounds(158, 159, 345, 50);
		panel.add(btnChooseExtractionPath);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("default save to desktop");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField_1.setColumns(10);
		textField_1.setBounds(115, 233, 448, 36);
		panel.add(textField_1);
		
		JButton btnExtract = new JButton("Extract");
		btnExtract.setBackground(new Color(255, 204, 204));
		btnExtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TOOLCODE code = new TOOLCODE();
				boolean b = code.unzipAny(source, dest);
				if(b==true)
				{
					JOptionPane.showMessageDialog(null, "File is Extracted successfully!");
					textField_1.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "File can't be extracted!");
					textField_1.setText("");
				}
				textField.setText("");
			}
		});
		btnExtract.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnExtract.setBounds(504, 431, 160, 50);
		frame.getContentPane().add(btnExtract);
		
		JButton btnReturn = new JButton("return");
		btnReturn.setBackground(new Color(255, 204, 204));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Home.main(null);
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnReturn.setBounds(309, 431, 160, 50);
		frame.getContentPane().add(btnReturn);
	}
}
