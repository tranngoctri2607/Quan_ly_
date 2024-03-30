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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Utils.BatLoi;
import Utils.JDBC;
import Utils.XImage;

public class DangKy extends JFrame {

	private JPanel contentPane;
	private JTextField txtSDT;
	private JTextField txtName;
	private JTextArea txtAddress;
	private JLabel lblPhoneNumber;
	private JTextField txtEmail;
	private JLabel lblEnterFullName;
	private JLabel lblEmail;
	private JLabel lblAddress;

	public DangKy() {
		setIconImage(XImage.APP_ICON);
		setTitle("Đăng ký");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 811, 683);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("REGISTRATION");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBackground(SystemColor.activeCaption);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(259, 11, 260, 42);
		contentPane.add(lblNewLabel_3);

		txtSDT = new JTextField();
		txtSDT.setForeground(Color.BLACK);
		txtSDT.setBackground(Color.WHITE);
		txtSDT.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtSDT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSDT.getText().equals(" Phone number")) {
					txtSDT.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtSDT.getText().equals("")) {
					txtSDT.setText(" Phone number");
				}
			}
		});
		txtSDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtSDT.getText().equals(" Phone number")) {
					txtSDT.setText("");
				}
			}
		});
		txtSDT.setText(" Phone number");
		txtSDT.setColumns(10);
		txtSDT.setBounds(210, 95, 358, 42);
		contentPane.add(txtSDT);

		lblPhoneNumber = new JLabel("");
		lblPhoneNumber.setForeground(Color.BLACK);
		lblPhoneNumber.setBackground(SystemColor.activeCaption);
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNumber.setBounds(270, 137, 298, 25);
		contentPane.add(lblPhoneNumber);

		txtName = new JTextField();
		txtName.setForeground(Color.BLACK);
		txtName.setBackground(Color.WHITE);
		txtName.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtName.getText().equals(" Name")) {
					txtName.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtName.getText().equals("")) {
					txtName.setText(" Name");
				}
			}
		});
		txtName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtName.getText().equals(" Name")) {
					txtName.setText("");
				}
			}
		});
		txtName.setText(" Name");
		txtName.setColumns(10);
		txtName.setBounds(210, 172, 358, 42);
		contentPane.add(txtName);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(210, 240, 358, 125);
		contentPane.add(scrollPane);

		txtAddress = new JTextArea();
		txtAddress.setForeground(Color.BLACK);
		txtAddress.setBackground(Color.WHITE);
		txtAddress.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtAddress.getText().equals(" Address")) {
					txtAddress.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtAddress.getText().equals("")) {
					txtAddress.setText(" Address");
				}
			}
		});
		txtAddress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtAddress.getText().equals(" Address")) {
					txtAddress.setText("");
				}
			}
		});
		txtAddress.setText(" Address");
		scrollPane.setViewportView(txtAddress);

		JButton btnRegiser = new JButton("Register now");
		btnRegiser.setForeground(Color.BLACK);
		btnRegiser.setBackground(SystemColor.activeCaption);
		btnRegiser.setFont(new Font("Dialog", Font.BOLD, 14));
		btnRegiser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		btnRegiser.setBounds(329, 532, 131, 42);
		contentPane.add(btnRegiser);

		txtEmail = new JTextField();
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtEmail.getText().equals(" Email")) {
					txtEmail.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtEmail.getText().equals("")) {
					txtEmail.setText(" Email");
				}
			}
		});
		txtEmail.setText(" Email");
		txtEmail.setColumns(10);
		txtEmail.setBounds(210, 395, 358, 42);
		contentPane.add(txtEmail);

		lblEmail = new JLabel("");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setBackground(SystemColor.activeCaption);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(270, 435, 298, 25);
		contentPane.add(lblEmail);

		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Exit?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
					DangKy.this.dispose();
				}
			}
		});
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Montserrat", Font.PLAIN, 20));
		lblExit.setBackground(SystemColor.activeCaption);
		lblExit.setBounds(781, 0, 30, 30);
		contentPane.add(lblExit);

		lblEnterFullName = new JLabel("");
		lblEnterFullName.setForeground(Color.BLACK);
		lblEnterFullName.setBackground(SystemColor.activeCaption);
		lblEnterFullName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterFullName.setBounds(270, 213, 298, 25);
		contentPane.add(lblEnterFullName);

		lblAddress = new JLabel("");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setBackground(SystemColor.activeCaption);
		lblAddress.setBounds(270, 363, 298, 25);
		contentPane.add(lblAddress);
		setLocationRelativeTo(null);
	}

	void register() {
		boolean check = true;
		String phone = txtSDT.getText();
		String fullName = txtName.getText();
		String address = txtAddress.getText();
		String email = txtEmail.getText();
		if (email.equals(" Email")) {
			check = false;
			lblEmail.setText("Email null.");
		} else if (!BatLoi.Email(email)) {
			check = false;
			lblEmail.setText("Email invalid.");
		} else {
			lblEmail.setText("");
		}
		if (!BatLoi.TenTiengViet(fullName)) {
			check = false;
			lblEnterFullName.setText("Name invalid.");
		} else if (fullName.equals(" Name")) {
			check = false;
			lblEnterFullName.setText("Name null.");
		} else {
			lblEnterFullName.setText("");
		}
		if (!BatLoi.SoDienThoai(phone)) {
			check = false;
			lblPhoneNumber.setText("Phone number invalid.");
		} else {
			lblPhoneNumber.setText("");
		}
		if (address.equals(" Address")) {
			check = false;
			lblAddress.setText("Address null.");
		} else {
			lblAddress.setText("");
		}
		if (check) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO KhachHang(SDT, HoTen, DiaChi, Email) VALUES (?, ?, ?, ?)");
				ps.setString(1, phone);
				ps.setString(2, fullName);
				ps.setString(3, address);
				ps.setString(4, email);
				ps.executeUpdate();
				lblEmail.setText("Register successfully.");
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
