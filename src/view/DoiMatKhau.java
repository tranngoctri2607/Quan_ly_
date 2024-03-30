package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import Utils.JDBC;
import Utils.Mahoa;

public class DoiMatKhau extends JPanel {
	private JPasswordField txtMatKhau1;
	private JPasswordField txtMatKhau2;
	private JPasswordField txtMatKhau3;
	private JLabel lblLoi3;
	private JLabel lblLoi1;
	private JLabel lblLoi2;

	public DoiMatKhau(String id) {
		setForeground(Color.BLACK);
		setBackground(SystemColor.activeCaption);
		setBounds(0, 0, 837, 754);
		setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("ĐỔI MẬT KHẨU");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBackground(SystemColor.activeCaption);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(268, 88, 260, 42);
		add(lblNewLabel_3);

		txtMatKhau1 = new JPasswordField();
		txtMatKhau1.setForeground(Color.BLACK);
		txtMatKhau1.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtMatKhau1.setText(" Mật khẩu hiện tại");
		txtMatKhau1.setColumns(10);
		txtMatKhau1.setBounds(208, 201, 358, 42);
		add(txtMatKhau1);

		txtMatKhau2 = new JPasswordField();
		txtMatKhau2.setForeground(Color.BLACK);
		txtMatKhau2.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtMatKhau2.setText(" Mật khẩu mới");
		txtMatKhau2.setColumns(10);
		txtMatKhau2.setBounds(208, 298, 358, 42);
		add(txtMatKhau2);

		txtMatKhau3 = new JPasswordField();
		txtMatKhau3.setForeground(Color.BLACK);
		txtMatKhau3.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtMatKhau3.setText(" Xác nhận mật khẩu mới");
		txtMatKhau3.setColumns(10);
		txtMatKhau3.setBounds(208, 399, 358, 42);
		add(txtMatKhau3);

		JButton btnXacNhan = new JButton(" Xác nhận");
		btnXacNhan.setForeground(Color.BLACK);
		btnXacNhan.setBackground(Color.WHITE);
		btnXacNhan.setFont(new Font("Dialog", Font.BOLD, 14));
		btnXacNhan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Xác nhận đổi mật khẩu?", "Xác nhận",
						JOptionPane.YES_NO_OPTION) == 0)
					XacNhan(id);
			}
		});
		btnXacNhan.setBounds(326, 505, 131, 42);
		add(btnXacNhan);

		lblLoi3 = new JLabel("");
		lblLoi3.setForeground(Color.BLACK);
		lblLoi3.setBackground(SystemColor.activeCaption);
		lblLoi3.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblLoi3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoi3.setBounds(268, 441, 298, 25);
		add(lblLoi3);

		lblLoi1 = new JLabel("");
		lblLoi1.setForeground(Color.BLACK);
		lblLoi1.setBackground(SystemColor.activeCaption);
		lblLoi1.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblLoi1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoi1.setBounds(268, 243, 298, 25);
		add(lblLoi1);

		JRadioButton rdHide1 = new JRadioButton("");
		rdHide1.setForeground(Color.BLACK);
		rdHide1.setBackground(SystemColor.activeCaption);
		rdHide1.setFont(new Font("Dialog", Font.PLAIN, 14));
		rdHide1.setBounds(0, 0, 0, 0);
		add(rdHide1);

		JRadioButton rdHide2 = new JRadioButton("");
		rdHide2.setForeground(Color.BLACK);
		rdHide2.setBackground(SystemColor.activeCaption);
		rdHide2.setFont(new Font("Dialog", Font.PLAIN, 14));
		rdHide2.setBounds(0, 0, 0, 0);
		add(rdHide2);

		JRadioButton rdHide3 = new JRadioButton("");
		rdHide3.setForeground(Color.BLACK);
		rdHide3.setBackground(SystemColor.activeCaption);
		rdHide3.setFont(new Font("Dialog", Font.PLAIN, 14));
		rdHide3.setBounds(0, 0, 0, 0);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBackground(SystemColor.activeCaption);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdHide1.isSelected()) {
					txtMatKhau1.setEchoChar((char) 0);
					lblNewLabel_1.setIcon(new ImageIcon(DangNhap.class.getResource("/res/Eye-pichon.png")));
					rdHide1.setSelected(false);
				} else {
					txtMatKhau1.setEchoChar('●');
					lblNewLabel_1.setIcon(new ImageIcon(DangNhap.class.getResource("/res/Hide-pichon.png")));
					rdHide1.setSelected(true);
				}
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(DoiMatKhau.class.getResource("/res/Hide-pichon.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(568, 201, 49, 49);
		add(lblNewLabel_1);
		add(rdHide3);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setBackground(SystemColor.activeCaption);
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdHide2.isSelected()) {
					txtMatKhau2.setEchoChar((char) 0);
					lblNewLabel_1_1.setIcon(new ImageIcon(DangNhap.class.getResource("/res/Eye-pichon.png")));
					rdHide2.setSelected(false);
				} else {
					txtMatKhau2.setEchoChar('●');
					lblNewLabel_1_1.setIcon(new ImageIcon(DangNhap.class.getResource("/res/Hide-pichon.png")));
					rdHide2.setSelected(true);
				}
			}
		});
		lblNewLabel_1_1.setIcon(new ImageIcon(DoiMatKhau.class.getResource("/res/Hide-pichon.png")));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(568, 298, 49, 49);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setBackground(SystemColor.activeCaption);
		lblNewLabel_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdHide3.isSelected()) {
					txtMatKhau3.setEchoChar((char) 0);
					lblNewLabel_1_2.setIcon(new ImageIcon(DangNhap.class.getResource("/res/Eye-pichon.png")));
					rdHide3.setSelected(false);
				} else {
					txtMatKhau3.setEchoChar('●');
					lblNewLabel_1_2.setIcon(new ImageIcon(DangNhap.class.getResource("/res/Hide-pichon.png")));
					rdHide3.setSelected(true);
				}
			}
		});
		lblNewLabel_1_2.setIcon(new ImageIcon(DoiMatKhau.class.getResource("/res/Hide-pichon.png")));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(568, 399, 49, 49);
		add(lblNewLabel_1_2);

		lblLoi2 = new JLabel("");
		lblLoi2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoi2.setForeground(Color.BLACK);
		lblLoi2.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblLoi2.setBackground(SystemColor.activeCaption);
		lblLoi2.setBounds(268, 340, 298, 25);
		add(lblLoi2);

		txtMatKhau1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (!rdHide1.isSelected()) {
					txtMatKhau1.setEchoChar('●');
				}
				if (new String(txtMatKhau1.getPassword()).equals(" Mật khẩu hiện tại")) {
					txtMatKhau1.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (new String(txtMatKhau1.getPassword()).equals("")) {
					txtMatKhau1.setText(" Mật khẩu hiện tại");
					txtMatKhau1.setEchoChar((char) 0);
				}
			}
		});

		txtMatKhau2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (!rdHide2.isSelected()) {
					txtMatKhau2.setEchoChar('●');
				}
				if (new String(txtMatKhau2.getPassword()).equals(" Mật khẩu mới")) {
					txtMatKhau2.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (new String(txtMatKhau2.getPassword()).equals("")) {
					txtMatKhau2.setText(" Mật khẩu mới");
					txtMatKhau2.setEchoChar((char) 0);
				}
			}
		});

		txtMatKhau3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (!rdHide3.isSelected()) {
					txtMatKhau3.setEchoChar('●');
				}
				if (new String(txtMatKhau3.getPassword()).equals(" Xác nhận mật khẩu mới")) {
					txtMatKhau3.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (new String(txtMatKhau3.getPassword()).equals("")) {
					txtMatKhau3.setText(" Xác nhận mật khẩu mới");
					txtMatKhau3.setEchoChar((char) 0);
				}
			}
		});

		rdHide1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		rdHide2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		rdHide3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		setVisible(true);
	}

	void XacNhan(String id) {
		String mkHienTai = String.valueOf(txtMatKhau1.getPassword());
		if (KiemTraMatKhau(id, mkHienTai)) {
			String mkMoi = String.valueOf(txtMatKhau2.getPassword());
			String mkXacNhan = String.valueOf(txtMatKhau3.getPassword());
			if (mkMoi.equals(" Mật khẩu mới")) {
				lblLoi2.setText("Mật khẩu trống.");
			} else if (mkXacNhan.equals(" Xác nhận mật khẩu mới")) {
				lblLoi3.setText("Xác nhận mật khẩu trống.");
			} else {
				lblLoi2.setText("");
				if (mkMoi.equalsIgnoreCase(mkXacNhan)) {
					String sql = "UPDATE NhanVien SET MatKhau = '" + Mahoa.CC(mkMoi) + "' WHERE SDT ='" + id + "';";
					try {
						Connection con = DriverManager.getConnection(JDBC.url());
						PreparedStatement ps = con.prepareStatement(sql);
						ps.executeUpdate();
						ps.close();
						con.close();
						lblLoi3.setText("Đổi mật khẩu thành công.");
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					lblLoi3.setText("Xác nhận mật khẩu không chính xác.");
				}
			}
		}
	}

	boolean KiemTraMatKhau(String id, String input) {
		String sql = "SELECT MatKhau FROM NhanVien WHERE SDT = '" + id + "';";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equalsIgnoreCase(Mahoa.CC(input))) {
					lblLoi1.setText("");
					return true;
				} else {
					lblLoi1.setText("Mật khẩu hiện tại không chính xác.");
				}
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
