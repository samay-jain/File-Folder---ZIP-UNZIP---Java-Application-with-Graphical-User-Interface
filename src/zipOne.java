import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.StreamSupport;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class zipOne {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					zipOne window = new zipOne();
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
	TOOLCODE tc = new TOOLCODE();
	public static String source,dest,fname;
	public zipOne() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 153, 255));
		frame.setResizable(false);
		frame.setBounds(100, 100, 867, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblChooseTheFilefolder_1 = new JLabel("Choose the file to ZIP");
		lblChooseTheFilefolder_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseTheFilefolder_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblChooseTheFilefolder_1.setBounds(134, 26, 584, 52);
		frame.getContentPane().add(lblChooseTheFilefolder_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		panel.setLayout(null);
		panel.setBounds(98, 88, 659, 303);
		frame.getContentPane().add(panel);
		
		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBackground(new Color(255, 204, 204));
		btnChooseFile.addActionListener(new ActionListener() {
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
						fname = "\\\\"+arr[arr.length-1].replace(".", "_")+".zip";
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					textField.setText(source);
				}
			}
		});
		btnChooseFile.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnChooseFile.setBounds(158, 29, 345, 50);
		panel.add(btnChooseFile);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setColumns(10);
		textField.setBounds(115, 100, 448, 36);
		panel.add(textField);
		
		JButton btnChooseExtractionPath = new JButton("Choose Location to Save");
		btnChooseExtractionPath.setBackground(new Color(255, 204, 204));
		btnChooseExtractionPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfile = new JFileChooser();
				jfile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfile.setFileHidingEnabled(false);
				jfile.addChoosableFileFilter(null);
				if(jfile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					File file = jfile.getSelectedFile();
					dest = file.getPath();
					String narr[] = dest.split(":");
					try {
						String arr[] = convert(dest);
						dest = String.join("\\\\", arr);
						dest = narr[0]+":\\\\"+dest;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					dest+=fname;
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
		
		JButton btnReturn = new JButton("return");
		btnReturn.setBackground(new Color(255, 204, 204));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Home.main(null);
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnReturn.setBounds(235, 413, 161, 35);
		frame.getContentPane().add(btnReturn);
		
		JButton btnZip = new JButton("Zip");
		btnZip.setBackground(new Color(255, 204, 204));
		btnZip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = tc.zipFile(source, dest, fname);
				if(result)
					JOptionPane.showMessageDialog(null, "File is zipped successfully!");
				else
					JOptionPane.showMessageDialog(null, "File can't zipped due to some technical issue!");
				textField.setText("");
				textField_1.setText("");
			}
		});
		btnZip.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnZip.setBounds(446, 413, 161, 35);
		frame.getContentPane().add(btnZip);
	}

}
