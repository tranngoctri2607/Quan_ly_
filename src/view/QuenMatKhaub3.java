package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Utils.JDBC;
import Utils.Mahoa;
import Utils.XImage;

public class QuenMatKhaub3 extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JPasswordField txtReenterPassword;
	private JButton btnContinue;
	private JRadioButton rdHide1;
	private JRadioButton rdHide2;
	private JLabel lblTitle;
	private JLabel lblBack;
	private JLabel lblBaoLoi;
	private JLabel lblExit;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	public QuenMatKhaub3(String email) {
		setIconImage(XImage.APP_ICON);
		setBackground(new Color(176, 196, 222));
		setForeground(Color.BLACK);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtPassword = new JPasswordField();
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setForeground(Color.BLACK);
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (!rdHide1.isSelected()) {
					txtPassword.setEchoChar('●');
				}
				if (new String(txtPassword.getPassword()).equals(" New password")) {
					txtPassword.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (new String(txtPassword.getPassword()).equals("")) {
					txtPassword.setText(" New password");
					txtPassword.setEchoChar((char) 0);
				}
			}
		});

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(176, 196, 222));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdHide2.isSelected()) {
					txtReenterPassword.setEchoChar((char) 0);
					lblNewLabel_1.setIcon(new ImageIcon(DangNhap.class.getResource("/res/Eye-pichon.png")));
					rdHide2.setSelected(false);
				} else {
					txtReenterPassword.setEchoChar('●');
					lblNewLabel_1.setIcon(new ImageIcon(DangNhap.class.getResource("/res/Hide-pichon.png")));
					rdHide2.setSelected(true);
				}
			}
		});

		lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(176, 196, 222));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdHide1.isSelected()) {
					txtPassword.setEchoChar((char) 0);
					lblNewLabel.setIcon(new ImageIcon(DangNhap.class.getResource("/res/Eye-pichon.png")));
					rdHide1.setSelected(false);
				} else {
					txtPassword.setEchoChar('●');
					lblNewLabel.setIcon(new ImageIcon(DangNhap.class.getResource("/res/Hide-pichon.png")));
					rdHide1.setSelected(true);
				}
			}
		});
		lblNewLabel.setIcon(new ImageIcon(QuenMatKhaub3.class.getResource("/res/Hide-pichon.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(517, 123, 49, 49);
		contentPane.add(lblNewLabel);
		lblNewLabel_1.setIcon(new ImageIcon(QuenMatKhaub3.class.getResource("/res/Hide-pichon.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(517, 180, 49, 49);
		contentPane.add(lblNewLabel_1);
		txtPassword.setText(" New password");
		txtPassword.setColumns(10);
		txtPassword.setBounds(159, 123, 358, 42);
		contentPane.add(txtPassword);

		txtReenterPassword = new JPasswordField();
		txtReenterPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnContinue.doClick();
				}
			}
		});
		txtReenterPassword.setBackground(Color.WHITE);
		txtReenterPassword.setForeground(Color.BLACK);
		txtReenterPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (!rdHide2.isSelected()) {
					txtReenterPassword.setEchoChar('●');
				}
				if (new String(txtReenterPassword.getPassword()).equals(" Re-enter password")) {
					txtReenterPassword.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (new String(txtReenterPassword.getPassword()).equals("")) {
					txtReenterPassword.setText(" Re-enter password");
					txtReenterPassword.setEchoChar((char) 0);
				}
			}
		});
		txtReenterPassword.setText(" Re-enter password");
		txtReenterPassword.setColumns(10);
		txtReenterPassword.setBounds(159, 180, 358, 42);
		contentPane.add(txtReenterPassword);

		btnContinue = new JButton("Continue");
		btnContinue.setBackground(new Color(176, 196, 222));
		btnContinue.setFont(new Font("Dialog", Font.BOLD, 14));
		btnContinue.setForeground(Color.BLACK);
		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change(email);
			}
		});
		btnContinue.setBounds(272, 269, 131, 42);
		contentPane.add(btnContinue);

		rdHide1 = new JRadioButton("");
		rdHide1.setBackground(new Color(176, 196, 222));
		rdHide1.setForeground(Color.BLACK);
		rdHide1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		rdHide1.setBounds(0, 0, 0, 0);
		contentPane.add(rdHide1);

		rdHide2 = new JRadioButton("");
		rdHide2.setBackground(new Color(176, 196, 222));
		rdHide2.setForeground(Color.BLACK);
		rdHide2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		rdHide2.setBounds(0, 0, 0, 0);
		contentPane.add(rdHide2);

		lblTitle = new JLabel("RESET PASSWORD");
		lblTitle.setBackground(new Color(176, 196, 222));
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitle.setBounds(219, 50, 260, 42);
		contentPane.add(lblTitle);

		lblBack = new JLabel(" Back to login");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblBack.setBackground(new Color(176, 196, 222));
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setForeground(Color.BLACK);
		lblBack.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblBack.setBounds(159, 220, 129, 32);
		contentPane.add(lblBack);

		lblBaoLoi = new JLabel("");
		lblBaoLoi.setBackground(new Color(176, 196, 222));
		lblBaoLoi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBaoLoi.setForeground(Color.BLACK);
		lblBaoLoi.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblBaoLoi.setBounds(219, 322, 298, 25);
		contentPane.add(lblBaoLoi);

		lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Exit?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
					QuenMatKhaub3.this.dispose();
				}
			}
		});
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Montserrat", Font.PLAIN, 20));
		lblExit.setBackground(new Color(176, 196, 222));
		lblExit.setBounds(670, 0, 30, 30);
		contentPane.add(lblExit);

	}

	void change(String email) {
		String newPassword = new String(txtPassword.getPassword());
		String rePassword = new String(txtReenterPassword.getPassword());
		if (newPassword.equalsIgnoreCase(rePassword)) {
			String sql = "UPDATE NhanVien SET MatKhau = '" + Mahoa.CC(newPassword) + "' WHERE Email ='" + email + "';";
			if (JOptionPane.showConfirmDialog(null, "Confirm?", "",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					Connection con = DriverManager.getConnection(JDBC.url());
					PreparedStatement ps = con.prepareStatement(sql);
					ps.executeUpdate();
					dispose();
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			lblBaoLoi.setText("Confirm password is incorrect.");
		}
	}
}
