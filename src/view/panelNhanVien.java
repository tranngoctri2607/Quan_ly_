package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Entity.NhanVien;
import Utils.BatLoi;
import Utils.JDBC;
import Utils.Mahoa;
import Utils.TaoMa;

public class panelNhanVien extends JPanel {

	private JTextField txtSDT;
	private JTextField txtName;
	private JTable tblNhanVien;
	private JTextField txtEmail;
	private JPasswordField txtPass;
	private JTextField txtTim;
	private JRadioButton rdNV;
	private JRadioButton rdQL;
	private JLabel lblLoiTen;
	private JLabel lblLoiMK;
	private JLabel lblLoiSDT;
	private JLabel lblLoiEmail;
	private JLabel lblLoiVaiTro;
	private JLabel lblLoiTT;
	private JRadioButton rdCon;
	private JRadioButton rdHet;

	ArrayList<NhanVien> list = new ArrayList<>();
	int vitri = -1;

	public panelNhanVien() {
		setForeground(Color.BLACK);
		setBackground(new Color(176, 196, 222));
		setBounds(0, 0, 837, 754);
		setLayout(null);
		setVisible(true);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(176, 196, 222));
		panel_2.setForeground(Color.BLACK);
		panel_2.setLayout(null);
		panel_2.setBounds(80, 674, 398, 57);
		add(panel_2);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(176, 196, 222));
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Them();
			}
		});
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setIcon(new ImageIcon(panelNhanVien.class.getResource("/res/add.png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setForeground(Color.BLACK);
		btnThem.setBounds(1, 11, 113, 35);
		panel_2.add(btnThem);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(176, 196, 222));
		btnCapNhat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CapNhat();
			}
		});
		btnCapNhat.setIcon(new ImageIcon(panelNhanVien.class.getResource("/res/Refresh.png")));
		btnCapNhat.setHorizontalAlignment(SwingConstants.LEFT);
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCapNhat.setForeground(Color.BLACK);
		btnCapNhat.setBounds(124, 11, 131, 35);
		panel_2.add(btnCapNhat);

		JButton btnMoi = new JButton("Mới");
		btnMoi.setBackground(new Color(176, 196, 222));
		btnMoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
				txtPass.setText("");
				txtSDT.setText("");
				txtEmail.setText("");
				load_data();
			}
		});
		btnMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMoi.setIcon(new ImageIcon(panelNhanVien.class.getResource("/res/icon/new.png")));
		btnMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMoi.setForeground(Color.BLACK);
		btnMoi.setBounds(265, 11, 108, 35);
		panel_2.add(btnMoi);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(176, 196, 222));
		panel_2_1.setForeground(Color.BLACK);
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(80, 606, 398, 57);
		add(panel_2_1);

		JButton btnFirst = new JButton("");
		btnFirst.setBackground(new Color(176, 196, 222));
		btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				First();
			}
		});
		btnFirst.setIcon(new ImageIcon(panelNhanVien.class.getResource("/res/skip_backward.png")));
		btnFirst.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFirst.setForeground(Color.BLACK);
		btnFirst.setBounds(1, 11, 79, 35);
		panel_2_1.add(btnFirst);

		JButton btnPrev = new JButton("");
		btnPrev.setBackground(new Color(176, 196, 222));
		btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Prev();
			}
		});
		btnPrev.setIcon(new ImageIcon(panelNhanVien.class.getResource("/res/rewind.png")));
		btnPrev.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrev.setForeground(Color.BLACK);
		btnPrev.setBounds(100, 11, 79, 35);
		panel_2_1.add(btnPrev);

		JButton btnNext = new JButton("");
		btnNext.setBackground(new Color(176, 196, 222));
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Next();
			}
		});
		btnNext.setIcon(new ImageIcon(panelNhanVien.class.getResource("/res/fast_forward.png")));
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNext.setForeground(Color.BLACK);
		btnNext.setBounds(199, 11, 79, 35);
		panel_2_1.add(btnNext);

		JButton btnLast = new JButton("");
		btnLast.setBackground(new Color(176, 196, 222));
		btnLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Last();
			}
		});
		btnLast.setIcon(new ImageIcon(panelNhanVien.class.getResource("/res/skip_forward.png")));
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLast.setForeground(Color.BLACK);
		btnLast.setBounds(298, 11, 79, 35);
		panel_2_1.add(btnLast);

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMatKhau.setForeground(Color.BLACK);
		lblMatKhau.setBounds(555, 301, 150, 30);
		add(lblMatKhau);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrangThai.setForeground(Color.BLACK);
		lblTrangThai.setBounds(555, 403, 150, 30);
		add(lblTrangThai);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSDT.setForeground(Color.BLACK);
		lblSDT.setBounds(555, 14, 150, 30);
		add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSDT.setForeground(Color.BLACK);
		txtSDT.setColumns(10);
		txtSDT.setBounds(555, 55, 272, 30);
		add(txtSDT);

		JLabel lblHoVaTen = new JLabel("Họ và tên:");
		lblHoVaTen.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHoVaTen.setForeground(Color.BLACK);
		lblHoVaTen.setBounds(555, 114, 150, 30);
		add(lblHoVaTen);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtName.setForeground(Color.BLACK);
		txtName.setColumns(10);
		txtName.setBounds(555, 155, 272, 30);
		add(txtName);

		JLabel lblVaiTro = new JLabel("Vai trò:");
		lblVaiTro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVaiTro.setForeground(Color.BLACK);
		lblVaiTro.setBounds(555, 216, 150, 30);
		add(lblVaiTro);

		rdNV = new JRadioButton("Nhân viên");
		rdNV.setBackground(new Color(176, 196, 222));
		rdNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdNV.setForeground(Color.BLACK);
		rdNV.setBounds(555, 251, 109, 23);
		add(rdNV);

		rdQL = new JRadioButton("Quản lý");
		rdQL.setBackground(new Color(176, 196, 222));
		rdQL.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdQL.setForeground(Color.BLACK);
		rdQL.setBounds(700, 251, 109, 23);
		add(rdQL);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 535, 523);
		add(scrollPane);

		tblNhanVien = new JTable();
		tblNhanVien.setForeground(Color.BLACK);
		tblNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vitri = tblNhanVien.getSelectedRow();
				LoadDataToControl(vitri);
			}
		});
		tblNhanVien.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 nh\u00E2n vi\u00EAn", "H\u1ECD t\u00EAn", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i",
						"Vai tr\u00F2", "Tr\u1EA1ng th\u00E1i", "Email" }));
		scrollPane.setViewportView(tblNhanVien);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setBounds(555, 504, 150, 30);
		add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setColumns(10);
		txtEmail.setBounds(555, 545, 272, 30);
		add(txtEmail);

		txtPass = new JPasswordField();
		txtPass.setForeground(Color.BLACK);
		txtPass.setEchoChar('*');
		txtPass.setBounds(555, 342, 272, 30);
		add(txtPass);

		txtTim = new JTextField();
		txtTim.setForeground(Color.BLACK);
		txtTim.setColumns(10);
		txtTim.setBounds(10, 23, 331, 30);
		add(txtTim);
		btngrVaiTro.add(rdNV);
		btngrVaiTro.add(rdQL);

		JButton btnTim = new JButton("");
		btnTim.setBackground(new Color(176, 196, 222));
		btnTim.setForeground(Color.BLACK);
		btnTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TimKiem();
			}
		});
		btnTim.setIcon(new ImageIcon(panelNhanVien.class.getResource("/res/Zoom.png")));
		btnTim.setBounds(351, 23, 57, 30);
		add(btnTim);

		lblLoiTen = new JLabel("");
		lblLoiTen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiTen.setForeground(Color.BLACK);
		lblLoiTen.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiTen.setBounds(555, 186, 272, 19);
		add(lblLoiTen);

		lblLoiMK = new JLabel("");
		lblLoiMK.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiMK.setForeground(Color.BLACK);
		lblLoiMK.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiMK.setBounds(555, 372, 272, 19);
		add(lblLoiMK);

		lblLoiTT = new JLabel("");
		lblLoiTT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiTT.setForeground(Color.BLACK);
		lblLoiTT.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiTT.setBounds(555, 474, 272, 19);
		add(lblLoiTT);

		lblLoiSDT = new JLabel("");
		lblLoiSDT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiSDT.setForeground(Color.BLACK);
		lblLoiSDT.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiSDT.setBounds(555, 84, 272, 19);
		add(lblLoiSDT);

		lblLoiEmail = new JLabel("");
		lblLoiEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiEmail.setForeground(Color.BLACK);
		lblLoiEmail.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiEmail.setBounds(555, 574, 272, 19);
		add(lblLoiEmail);

		lblLoiVaiTro = new JLabel("");
		lblLoiVaiTro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiVaiTro.setForeground(Color.BLACK);
		lblLoiVaiTro.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiVaiTro.setBounds(555, 280, 272, 19);
		add(lblLoiVaiTro);

		rdHet = new JRadioButton("Hết làm");
		btngrTrangThai.add(rdHet);
		rdHet.setForeground(Color.BLACK);
		rdHet.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdHet.setBackground(new Color(176, 196, 222));
		rdHet.setBounds(555, 440, 109, 23);
		add(rdHet);

		rdCon = new JRadioButton("Còn làm");
		btngrTrangThai.add(rdCon);
		rdCon.setForeground(Color.BLACK);
		rdCon.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdCon.setBackground(new Color(176, 196, 222));
		rdCon.setBounds(700, 440, 109, 23);
		add(rdCon);
		load_data();
	}

	private final ButtonGroup btngrVaiTro = new ButtonGroup();
	private final ButtonGroup btngrTrangThai = new ButtonGroup();

	String maCu = "";

	public void load_data() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			String sqlInsert = "select * from NhanVien WHERE TrangThai = 0;";
			ResultSet rs = st.executeQuery(sqlInsert);
			list.clear();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString("MaNV"));
				nv.setHoTen(rs.getString("HoTen"));
				nv.setMatKhau(rs.getString("MatKhau"));
				nv.setSdt(rs.getString("SDT"));
				nv.setVaiTro(rs.getBoolean("VaiTro"));
				nv.setTrangThai(rs.getBoolean("TrangThai"));
				nv.setEmail(rs.getString("Email"));
				list.add(nv);
			}
			rs.close();
			st.close();
			DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
			model.setRowCount(0);
			for (NhanVien nv : list) {
				Object[] row = new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSdt(),
						nv.isVaiTro() ? "Nhân viên" : "Quản lý", nv.getTrangThai() ? "Hết làm" : "Còn làm",
						nv.getEmail() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CapNhat() {
		String ma = maCu;
		String ten = txtName.getText();
		String matKhau = new String(txtPass.getPassword());
		String sdt = txtSDT.getText();
		boolean vaiTro = rdNV.isSelected() ? true : false;
		boolean trangThai = rdHet.isSelected() ? true : false;
		String email = txtEmail.getText();
		if (checkLoiUpdate(ma, ten, sdt, email, matKhau)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement(
						"UPDATE NhanVien SET  HoTen = ?, MatKhau = ?, SDT = ?, VaiTro = ?, TrangThai = ?, Email = ? where MaNV = ?");
				ps.setString(1, ten);
				ps.setString(2, Mahoa.CC(matKhau));
				ps.setString(3, sdt);
				ps.setBoolean(4, vaiTro);
				ps.setBoolean(5, trangThai);
				ps.setString(6, email);
				ps.setString(7, ma);
				int kq = ps.executeUpdate();
				if (kq == 1) {
					lblLoiEmail.setText("Cập nhật thành công.");
				} else {
					lblLoiEmail.setText("Cập nhật thất bại.");
				}
				ps.close();
				con.close();
				this.load_data();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void Them() {
		String ma = TaoMa.create();
		String ten = txtName.getText();
		String matKhau = new String(txtPass.getPassword());
		String sdt = txtSDT.getText();
		boolean vaiTro = rdNV.isSelected() ? true : false;
		boolean trangThai = rdHet.isSelected() ? true : false;
		String email = txtEmail.getText();
		if (checkLoi(ma, ten, sdt, email, matKhau)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement("insert into NhanVien values (?,?,?,?,?,?,?);");
				ps.setString(1, ma);
				ps.setString(2, ten);
				ps.setString(3, Mahoa.CC(matKhau));
				ps.setString(4, sdt);
				ps.setBoolean(5, vaiTro);
				ps.setBoolean(6, trangThai);
				ps.setString(7, email);
				int kq = ps.executeUpdate();
				if (kq == 1) {
					lblLoiEmail.setText("Thêm thành công.");
				} else {
					lblLoiEmail.setText("Thêm thất bại.");
				}
				ps.close();
				con.close();
				this.load_data();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean TimKiem() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con
					.prepareStatement("select * from NhanVien WHERE HoTen LIKE '%" + txtTim.getText() + "%';");
			ResultSet rs = ps.executeQuery();
			list.clear();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString("MaNV"));
				nv.setHoTen(rs.getString("HoTen"));
				nv.setMatKhau(rs.getString("MatKhau"));
				nv.setSdt(rs.getString("SDT"));
				nv.setVaiTro(rs.getBoolean("VaiTro"));
				nv.setTrangThai(rs.getBoolean("TrangThai"));
				nv.setEmail(rs.getString("Email"));
				list.add(nv);
			}
			DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
			model.setRowCount(0);
			for (NhanVien nv : list) {
				Object[] row = new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSdt(),
						nv.isVaiTro() ? "Nhân viên" : "Quản lý", nv.getTrangThai() ? "Hết làm" : "Còn làm",
						nv.getEmail() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void LoadDataToControl(int vitri) {
		maCu = list.get(vitri).getMaNV();
		txtName.setText(list.get(vitri).getHoTen());
		txtPass.setText(list.get(vitri).getMatKhau());
		txtSDT.setText(list.get(vitri).getSdt());
		if (list.get(vitri).isVaiTro()) {
			rdNV.setSelected(true);
		} else {
			rdQL.setSelected(true);
		}
		if (list.get(vitri).getTrangThai()) {
			rdHet.setSelected(true);
		} else {
			rdCon.setSelected(true);
		}
		txtEmail.setText(list.get(vitri).getEmail());
	}

	public void First() {
		vitri = 0;
		LoadDataToControl(vitri);
		tblNhanVien.setRowSelectionInterval(vitri, vitri);
	}

	public void Prev() {
		if (vitri > 0) {
			vitri--;
			LoadDataToControl(vitri);
			tblNhanVien.setRowSelectionInterval(vitri, vitri);
		}
	}

	public void Next() {
		if (vitri < list.size() - 1) {
			vitri++;
			LoadDataToControl(vitri);
			tblNhanVien.setRowSelectionInterval(vitri, vitri);
		}
	}

	public void Last() {
		vitri = list.size() - 1;
		LoadDataToControl(vitri);
		tblNhanVien.setRowSelectionInterval(vitri, vitri);
	}

	boolean checkMa(String input) {
		String sql = "SELECT MaNV FROM NhanVien;";
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

	boolean checkEmail(String input) {
		String sql = "SELECT Email FROM NhanVien;";
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

	boolean checkSDT(String input) {
		String sql = "SELECT SDT FROM NhanVien;";
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

	boolean checkLoi(String ma, String ten, String sdt, String email, String matKhau) {
		if (!BatLoi.SoDienThoai(sdt)) {
			lblLoiSDT.setText("Số điện thoại không hợp lệ.");
			return false;
		} else if (checkSDT(sdt)) {
			lblLoiSDT.setText("Số điện thoại bị trùng.");
		} else {
			lblLoiSDT.setText("");
		}
		if (!BatLoi.Ma(ma) || checkMa(ma)) {
			return false;
		}
		if (!BatLoi.TenTiengViet(ten)) {
			lblLoiTen.setText("Tên không hợp lệ.");
			return false;
		} else {
			lblLoiTen.setText("");
		}
		if (!rdNV.isSelected() && !rdQL.isSelected()) {
			lblLoiVaiTro.setText("Chưa chọn vai trò.");
			return false;
		} else {
			lblLoiVaiTro.setText("");
		}
		if (matKhau.equals("")) {
			lblLoiMK.setText("Mật khẩu trống.");
			return false;
		} else {
			lblLoiMK.setText("");
		}
		if (!rdHet.isSelected() && !rdCon.isSelected()) {
			lblLoiTT.setText("Trạng thái trống.");
			return false;
		} else {
			lblLoiTT.setText("");
		}
		if (!BatLoi.Email(email)) {
			lblLoiEmail.setText("Email không hợp lệ.");
			return false;
		}
		if (checkEmail(email)) {
			lblLoiEmail.setText("Email bị trùng.");
			return false;
		} else {
			lblLoiEmail.setText("");
		}
		return true;
	}

	boolean checkLoiUpdate(String ma, String ten, String sdt, String email, String matKhau) {
		if (!BatLoi.SoDienThoai(sdt)) {
			lblLoiSDT.setText("Số điện thoại không hợp lệ.");
			return false;
		} else if (!checkSDT(sdt)) {
			lblLoiSDT.setText("Số điện thoại không tồn tại");
		} else {
			lblLoiSDT.setText("");
		}
		if (!BatLoi.Ma(ma) || !checkMa(ma)) {
			return false;
		}
		if (!BatLoi.TenTiengViet(ten)) {
			lblLoiTen.setText("Tên không hợp lệ.");
			return false;
		} else {
			lblLoiTen.setText("");
		}
		if (!rdNV.isSelected() && !rdQL.isSelected()) {
			lblLoiVaiTro.setText("Chưa chọn vai trò.");
			return false;
		} else {
			lblLoiVaiTro.setText("");
		}
		if (matKhau.equals("")) {
			lblLoiMK.setText("Mật khẩu trống.");
			return false;
		} else {
			lblLoiMK.setText("");
		}
		if (!rdHet.isSelected() && !rdCon.isSelected()) {
			lblLoiTT.setText("Trạng thái trống.");
			return false;
		} else {
			lblLoiTT.setText("");
		}
		if (!BatLoi.Email(email)) {
			lblLoiEmail.setText("Email không hợp lệ.");
			return false;
		}
		return true;
	}
}
