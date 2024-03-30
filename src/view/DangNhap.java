package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Utils.JDBC;
import Utils.Mahoa;
import Utils.XImage;

public class DangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JLabel lblBaoLoi;

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DangNhap().setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DangNhap() {
		setIconImage(XImage.APP_ICON);
		setForeground(Color.BLACK);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 589, 531);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHinh = new JLabel("");
		lblHinh.setForeground(Color.BLACK);
		lblHinh.setBounds(180, 12, 236, 236);
		lblHinh.setBackground(new Color(112, 128, 144));
		lblHinh.setHorizontalAlignment(SwingConstants.CENTER);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src\\res\\Logo3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setIcon(new ImageIcon(DangNhap.class.getResource("/res/eye2.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(449, 312, 42, 42);
		contentPane.add(lblNewLabel_1);
		lblHinh.setIcon(imageIcon);
		contentPane.add(lblHinh);

		JLabel lblQuenMK = new JLabel("Fogot password");
		Font font = lblQuenMK.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblQuenMK.setFont(new Font("Dialog", Font.ITALIC, 14));
		lblQuenMK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblQuenMK.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblQuenMK.setForeground(Color.BLUE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new QuenMatKhaub1().setVisible(true);
			}
		});
		lblQuenMK.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuenMK.setBounds(298, 391, 149, 32);
		contentPane.add(lblQuenMK);

		JLabel lblDangKy = new JLabel("Register customer");
		lblQuenMK.setForeground(Color.BLACK);
		lblDangKy.setForeground(Color.BLACK);
		lblDangKy.setFont(new Font("Dialog", Font.ITALIC, 14));
		lblDangKy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblDangKy.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblDangKy.setForeground(Color.BLUE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new DangKy().setVisible(true);
			}
		});
		lblDangKy.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangKy.setBounds(105, 391, 167, 32);
		contentPane.add(lblDangKy);
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setIcon(new ImageIcon(DangNhap.class.getResource("/res/Key.png")));
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 20));
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loggin();
			}
		});
		btnLogin.setBounds(200, 436, 188, 42);
		contentPane.add(btnLogin);

		lblBaoLoi = new JLabel("Báo lỗi");
		lblBaoLoi.setFont(new Font("Dialog", Font.ITALIC, 14));
		lblBaoLoi.setForeground(Color.RED);

		lblBaoLoi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBaoLoi.setBounds(136, 355, 281, 25);
		contentPane.add(lblBaoLoi);
		lblBaoLoi.setText("");

		JRadioButton rdHidePassword = new JRadioButton("");
		rdHidePassword.setFont(new Font("Dialog", Font.ITALIC, 14));
		rdHidePassword.setBackground(new Color(255, 255, 255));
		rdHidePassword.setForeground(Color.BLACK);
		rdHidePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdHidePassword.isSelected()) {

				} else {

				}
			}
		});

		rdHidePassword.setBounds(0, 0, 0, 0);
		contentPane.add(rdHidePassword);

		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdHidePassword.isSelected()) {
					txtPassword.setEchoChar((char) 0);
					lblNewLabel_1.setIcon(new ImageIcon(DangNhap.class.getResource("/res/eye.png")));
					rdHidePassword.setSelected(false);
				} else {
					txtPassword.setEchoChar('*');
					lblNewLabel_1.setIcon(new ImageIcon(DangNhap.class.getResource("/res/eye2.png")));
					rdHidePassword.setSelected(true);
				}
			}
		});

		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Exit?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
					DangNhap.this.dispose();
				}
			}
		});
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Montserrat", Font.PLAIN, 20));
		lblExit.setBackground(Color.BLACK);
		lblExit.setBounds(559, 0, 30, 30);
		contentPane.add(lblExit);

		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});
		txtPassword.setForeground(Color.BLACK);
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (!rdHidePassword.isSelected()) {
					txtPassword.setEchoChar('*');
				}
				if (new String(txtPassword.getPassword()).equals(" Password")) {
					txtPassword.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (new String(txtPassword.getPassword()).equals("")) {
					txtPassword.setText(" Password");
					txtPassword.setEchoChar((char) 0);
				}
			}
		});
		txtPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPassword.setEchoChar('*');
				if (new String(txtPassword.getPassword()).equals(" Password")) {
					txtPassword.setText("");
				}
				if (rdHidePassword.isSelected()) {
					txtPassword.setEchoChar((char) 0);
				} else {
					txtPassword.setEchoChar('*');
				}
			}
		});
		txtPassword.setText(" Password");
		txtPassword.setEchoChar((char) 0);
		txtPassword.setBounds(158, 312, 281, 42);
		contentPane.add(txtPassword);

		txtUserName = new JTextField();
		txtUserName.setForeground(Color.BLACK);
		txtUserName.setBackground(Color.WHITE);
		txtUserName.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtUserName.getText().equals(" Phone number")) {
					txtUserName.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtUserName.getText().equals("")) {
					txtUserName.setText(" Phone number");
				}
			}
		});
		txtUserName.setText(" Phone number");
		txtUserName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtUserName.getText().equals(" Phone number")) {
					txtUserName.setText("");
				}
			}
		});
		txtUserName.setBounds(158, 259, 281, 42);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		setLocationRelativeTo(null);
	}

	void loggin() {
		boolean check = false;
		String userName = txtUserName.getText();
		String password = Mahoa.CC(new String(txtPassword.getPassword()));
		String maNV = "";
		String sql = "SELECT SDT, MatKhau, MaNV FROM NhanVien;";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equalsIgnoreCase(userName)) {
					if (rs.getString(2).equalsIgnoreCase(password)) {
						check = true;
						maNV = rs.getString(3);
					}
				}
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (check) {
			lblBaoLoi.setText("");
			new Main(maNV).setVisible(true);
			dispose();
		} else {
			lblBaoLoi.setText("Incorrect username or password.");
		}
	}
}
