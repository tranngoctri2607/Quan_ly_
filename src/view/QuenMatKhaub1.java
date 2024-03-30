package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Utils.BatLoi;
import Utils.JDBC;
import Utils.XImage;

public class QuenMatKhaub1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JLabel lblSignUp;
	private JLabel lblBack;
	private JLabel lblBaoLoi;
	private JLabel lblExit;

	public QuenMatKhaub1() {
		setIconImage(XImage.APP_ICON);
		setForeground(Color.BLACK);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("RESET PASSWORD");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitle.setBounds(219, 50, 260, 42);
		contentPane.add(lblTitle);

		txtEmail = new JTextField();
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtEmail.getText().equals(" Email")) {
					txtEmail.setText("");
				}
			}
		});
		txtEmail.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				if (txtEmail.getText().equals("")) {
					txtEmail.setText(" Email");
				}
			}
		});
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						next();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		txtEmail.setText(" Email");
		txtEmail.setColumns(10);
		txtEmail.setBounds(157, 123, 358, 42);
		contentPane.add(txtEmail);

		lblSignUp = new JLabel("");
		lblSignUp.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSignUp.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblSignUp.setForeground(Color.BLUE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
//				new DangKy().setVisible(true);
				dispose();
			}
		});
		lblSignUp.setForeground(Color.BLACK);
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setBounds(422, 176, 93, 32);
		contentPane.add(lblSignUp);

		lblBack = new JLabel(" Back to login");
		lblBack.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBack.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblBack.setForeground(Color.BLUE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblBack.setForeground(Color.BLACK);
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setBounds(283, 176, 129, 32);
		contentPane.add(lblBack);

		lblBaoLoi = new JLabel("Báo lỗi");
		lblBaoLoi.setForeground(Color.BLACK);
		lblBaoLoi.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblBaoLoi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBaoLoi.setBounds(217, 219, 298, 25);
		contentPane.add(lblBaoLoi);
		lblBaoLoi.setText("");

		lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Exit?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
					QuenMatKhaub1.this.dispose();
				}
			}
		});
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Montserrat", Font.PLAIN, 20));
		lblExit.setBackground(Color.BLACK);
		lblExit.setBounds(670, 0, 30, 30);
		contentPane.add(lblExit);
		setLocationRelativeTo(null);
	}

	void next() {
		boolean check = false;
		String sql = "SELECT Email FROM NhanVien;";
		String email = txtEmail.getText();
		if (BatLoi.Email(email)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					if (rs.getString(1).equals(email)) {
						new QuenMatKhaub2(email).setVisible(true);
						dispose();
						check = true;
					}
				}
				if (!check) {
					lblBaoLoi.setText("Incorrect username email.");
				} else {
					lblBaoLoi.setText("");
				}
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
