package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Entity.ChiTietKM;
import Utils.JDBC;
import Utils.XImage;

public class ChiTietKhuyenMai extends JFrame {

	private JPanel contentPane;
	private JTextField txtIDKhuyenMai;
	private JTextField txtID;
	private JTextField txtTiLeKM;
	private JLabel lblLoi;
	private JLabel lblLoiMa;

	public ChiTietKhuyenMai(String id) {
		setIconImage(XImage.APP_ICON);
		setForeground(Color.BLACK);
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 913, 659);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("CHI TIẾT KHUYẾN MÃI");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBackground(SystemColor.activeCaption);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(319, 10, 260, 42);
		contentPane.add(lblNewLabel_3);

		txtIDKhuyenMai = new JTextField(id);
		txtIDKhuyenMai.setEditable(false);
		txtIDKhuyenMai.setForeground(Color.BLACK);
		txtIDKhuyenMai.setBackground(Color.WHITE);
		txtIDKhuyenMai.setColumns(10);
		txtIDKhuyenMai.setBounds(251, 139, 394, 30);
		contentPane.add(txtIDKhuyenMai);

		txtID = new JTextField();
		txtID.setForeground(Color.BLACK);
		txtID.setBackground(Color.WHITE);
		txtID.setColumns(10);
		txtID.setBounds(251, 246, 394, 30);
		contentPane.add(txtID);

		txtTiLeKM = new JTextField();
		txtTiLeKM.setForeground(Color.BLACK);
		txtTiLeKM.setBackground(Color.WHITE);
		txtTiLeKM.setColumns(10);
		txtTiLeKM.setBounds(251, 358, 394, 30);
		contentPane.add(txtTiLeKM);

		JLabel lblNewLabel = new JLabel("Mã khuyến mãi:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(251, 78, 129, 30);
		contentPane.add(lblNewLabel);

		JLabel lblMHan = new JLabel("Mã sản phẩm:");
		lblMHan.setForeground(Color.BLACK);
		lblMHan.setBackground(SystemColor.activeCaption);
		lblMHan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMHan.setBounds(251, 188, 211, 30);
		contentPane.add(lblMHan);

		JLabel lblTiLe = new JLabel("Tỉ lệ giảm giá (%):");
		lblTiLe.setForeground(Color.BLACK);
		lblTiLe.setBackground(SystemColor.activeCaption);
		lblTiLe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTiLe.setBounds(251, 300, 150, 30);
		contentPane.add(lblTiLe);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.BLACK);
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setLayout(null);
		panel_2.setBounds(247, 432, 398, 57);
		contentPane.add(panel_2);

		JButton btnNewButton_2 = new JButton("Thêm");
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton_2.setBackground(SystemColor.activeCaption);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Them(id);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(ChiTietKhuyenMai.class.getResource("/res/Create.png")));
		btnNewButton_2.setBounds(62, 11, 116, 35);
		panel_2.add(btnNewButton_2);

		JButton btnNewButton = new JButton("Mới");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtID.setText("");
				txtTiLeKM.setText("");
				list.clear();
				loadArr(id);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ChiTietKhuyenMai.class.getResource("/res/icon/new.png")));
		btnNewButton.setBounds(208, 11, 99, 35);
		panel_2.add(btnNewButton);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setForeground(Color.BLACK);
		panel_2_1.setBackground(SystemColor.activeCaption);
		panel_2_1.setBounds(251, 515, 398, 57);
		contentPane.add(panel_2_1);

		JButton btnFirst = new JButton("");
		btnFirst.setBackground(SystemColor.activeCaption);
		btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				first();
			}
		});
		btnFirst.setIcon(new ImageIcon(ChiTietKhuyenMai.class.getResource("/res/skip_backward.png")));
		btnFirst.setForeground(Color.BLACK);
		btnFirst.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFirst.setBounds(1, 11, 79, 35);
		panel_2_1.add(btnFirst);

		JButton btnPrev = new JButton("");
		btnPrev.setBackground(SystemColor.activeCaption);
		btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});
		btnPrev.setIcon(new ImageIcon(ChiTietKhuyenMai.class.getResource("/res/rewind.png")));
		btnPrev.setForeground(Color.BLACK);
		btnPrev.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrev.setBounds(100, 11, 79, 35);
		panel_2_1.add(btnPrev);

		JButton btnNext = new JButton("");
		btnNext.setBackground(SystemColor.activeCaption);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		btnNext.setIcon(new ImageIcon(ChiTietKhuyenMai.class.getResource("/res/fast_forward.png")));
		btnNext.setForeground(Color.BLACK);
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNext.setBounds(199, 11, 79, 35);
		panel_2_1.add(btnNext);

		JButton btnLast = new JButton("");
		btnLast.setBackground(SystemColor.activeCaption);
		btnLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				last();
			}
		});
		btnLast.setIcon(new ImageIcon(ChiTietKhuyenMai.class.getResource("/res/skip_forward.png")));
		btnLast.setForeground(Color.BLACK);
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLast.setBounds(298, 11, 79, 35);
		panel_2_1.add(btnLast);

		lblLoi = new JLabel("");
		lblLoi.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblLoi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoi.setBounds(251, 388, 394, 19);
		contentPane.add(lblLoi);

		lblLoiMa = new JLabel("");
		lblLoiMa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiMa.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblLoiMa.setBounds(251, 275, 394, 19);
		contentPane.add(lblLoiMa);
		loadArr(id);
	}

	ArrayList<ChiTietKM> list = new ArrayList<>();

	void loadArr(String input) {
		String sql = "SELECT * FROM ChiTietKhuyenMai WHERE MaKM = '" + input + "';";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ChiTietKM km = new ChiTietKM();
				km.setMaKM(rs.getString(1));
				km.setMaSP(rs.getString(2));
				km.setTiLeKM(rs.getFloat(3));
				list.add(km);
			}
			rs.close();
			st.close();
			con.close();
			loadForm(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int index = 0;

	void loadForm(int viTri) {
		if (!list.isEmpty()) {
			txtIDKhuyenMai.setText(list.get(viTri).getMaKM());
			txtID.setText(list.get(viTri).getMaSP());
			txtTiLeKM.setText(String.valueOf(list.get(viTri).getTiLeKM()));

		}
	}

	public void first() {
		if (list.isEmpty()) {
		} else {
			index = 0;
			loadForm(index);
		}
	}

	public void last() {
		if (!list.isEmpty()) {
			index = list.size() - 1;
			loadForm(index);
		}
	}

	public void prev() {
		if (!list.isEmpty()) {
			if (index == 0) {
				last();
			} else {
				index--;
			}
			loadForm(index);
		}
	}

	public void next() {
		if (!list.isEmpty()) {
			if (index == list.size() - 1) {
				first();
			} else {
				index++;
			}
			loadForm(index);
		}
	}

	void Them(String input) {
		boolean check = true;
		list.clear();
		if (txtTiLeKM.getText().equals("")) {
			txtTiLeKM.setText("0");
		}
		double tileKM = Double.valueOf(txtTiLeKM.getText());
		String maKM = txtIDKhuyenMai.getText();
		String maSP = txtID.getText();
		if (tileKM > 100 || tileKM < 0) {
			check = false;
			lblLoi.setText("Tỉ lệ từ 0 - 100.");
		} else {
			lblLoi.setText("");
		}
		if (!checkMa(maSP)) {
			check = false;
			lblLoiMa.setText("Mã sản phẩm không tồn tại.");
		} else {
			lblLoiMa.setText("");
		}
		if (check) {
			tileKM = Float.parseFloat(txtTiLeKM.getText());
			String sql = "INSERT INTO ChiTietKhuyenMai(MaKM, MaSP, TiLeKM) VALUES('" + maKM + "', '" + maSP + "', '"
					+ tileKM + "');";
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				con.close();
				loadArr(input);
				lblLoi.setText("Thêm thành công.");
			} catch (Exception e) {
				lblLoi.setText("Thêm thất bại.");
				e.printStackTrace();
			}
		}
	}

	boolean checkMa(String input) {
		String sql = "SELECT MaSP FROM SanPham;";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equalsIgnoreCase(input)) {
					return true;
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
