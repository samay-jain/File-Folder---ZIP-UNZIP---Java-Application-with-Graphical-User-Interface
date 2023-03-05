import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public static int option=-1;
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 153, 255));
		frame.setResizable(false);
		frame.setBounds(100, 100, 940, 642);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ZIP or UNZIP your documents");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(184, 40, 584, 52);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(152, 125, 624, 408);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("ZIP Single File");
		btnNewButton.setBackground(new Color(255, 204, 204));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				option = 1;
				zipOne.main(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(171, 46, 293, 50);
		panel.add(btnNewButton);
		
		JButton btnZipFolder = new JButton("ZIP Folder");
		btnZipFolder.setBackground(new Color(255, 204, 204));
		btnZipFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				option=2;
				zipper.main(null);
			}
		});
		btnZipFolder.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnZipFolder.setBounds(171, 175, 293, 50);
		panel.add(btnZipFolder);
		
		JButton btnUnzipFileOr = new JButton("UNZIP File or Folder");
		btnUnzipFileOr.setBackground(new Color(255, 204, 204));
		btnUnzipFileOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option=3;
				frame.setVisible(false);
				unzipUI.main(null);
			}
		});
		btnUnzipFileOr.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnUnzipFileOr.setBounds(171, 310, 293, 50);
		panel.add(btnUnzipFileOr);
	}
}
