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
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Entity.KhuyenMai;
import Utils.BatLoi;
import Utils.JDBC;
import Utils.Mahoa;
import Utils.TaoMa;
import com.toedter.calendar.JDateChooser;

public class panelKhuyenMai extends JPanel {

	ArrayList<KhuyenMai> list = new ArrayList<>();
	int current = 0;
	int vitri = -1;

	private JTextField txtIDKhuyenMai;
	private JTextField txtTenKM;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JTextField txtTim;
	private JLabel lblEnd;
	private JLabel lblStart;
	private JLabel lblLoiTen;
	private JLabel lblLoiMa;
	private JDateChooser ngayBatDau;
	private JDateChooser ngayKetThuc;

	public panelKhuyenMai() {
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
		btnThem.setIcon(new ImageIcon(panelKhuyenMai.class.getResource("/res/add.png")));
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
		btnCapNhat.setIcon(new ImageIcon(panelKhuyenMai.class.getResource("/res/Refresh.png")));
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
				txtIDKhuyenMai.setText(TaoMa.create());
				txtTenKM.setText("");
				ngayBatDau.setDate(null);
				ngayKetThuc.setDate(null);
				load_data();
			}
		});
		btnMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMoi.setIcon(new ImageIcon(panelKhuyenMai.class.getResource("/res/icon/new.png")));
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
				try {
					First();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFirst.setIcon(new ImageIcon(panelKhuyenMai.class.getResource("/res/skip_backward.png")));
		btnFirst.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFirst.setForeground(Color.BLACK);
		btnFirst.setBounds(1, 11, 79, 35);
		panel_2_1.add(btnFirst);

		JButton btnPrev = new JButton("");
		btnPrev.setBackground(new Color(176, 196, 222));
		btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Prev();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPrev.setIcon(new ImageIcon(panelKhuyenMai.class.getResource("/res/rewind.png")));
		btnPrev.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrev.setForeground(Color.BLACK);
		btnPrev.setBounds(100, 11, 79, 35);
		panel_2_1.add(btnPrev);

		JButton btnNext = new JButton("");
		btnNext.setBackground(new Color(176, 196, 222));
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Next();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNext.setIcon(new ImageIcon(panelKhuyenMai.class.getResource("/res/fast_forward.png")));
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNext.setForeground(Color.BLACK);
		btnNext.setBounds(199, 11, 79, 35);
		panel_2_1.add(btnNext);

		JButton btnLast = new JButton("");
		btnLast.setBackground(new Color(176, 196, 222));
		btnLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Last();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLast.setIcon(new ImageIcon(panelKhuyenMai.class.getResource("/res/skip_forward.png")));
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLast.setForeground(Color.BLACK);
		btnLast.setBounds(298, 11, 79, 35);
		panel_2_1.add(btnLast);

		txtIDKhuyenMai = new JTextField();
		txtIDKhuyenMai.setEditable(false);
		txtIDKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtIDKhuyenMai.setForeground(Color.BLACK);
		txtIDKhuyenMai.setColumns(10);
		txtIDKhuyenMai.setBounds(555, 72, 272, 30);
		add(txtIDKhuyenMai);

		JLabel lblIDKhuyenMai = new JLabel("Mã chương trình khuyến mãi:");
		lblIDKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIDKhuyenMai.setForeground(Color.BLACK);
		lblIDKhuyenMai.setBounds(555, 31, 225, 30);
		add(lblIDKhuyenMai);

		JLabel lblTenKM = new JLabel("Tên chương trình khuyến mãi:");
		lblTenKM.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenKM.setForeground(Color.BLACK);
		lblTenKM.setBounds(555, 133, 225, 30);
		add(lblTenKM);

		txtTenKM = new JTextField();
		txtTenKM.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTenKM.setForeground(Color.BLACK);
		txtTenKM.setColumns(10);
		txtTenKM.setBounds(555, 174, 272, 30);
		add(txtTenKM);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 535, 522);
		add(scrollPane);

		table = new JTable();
		table.setForeground(Color.BLACK);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				current = table.getSelectedRow();
				txtIDKhuyenMai.setText(table.getValueAt(current, 0).toString());
				maCu = table.getValueAt(current, 0).toString();
				txtTenKM.setText(table.getValueAt(current, 1).toString());
				ngayBatDau.setDate(Mahoa.stringToDate(table.getValueAt(current, 2).toString()));
				ngayKetThuc.setDate(Mahoa.stringToDate(table.getValueAt(current, 3).toString()));
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 khuy\u1EBFn m\u00E3i", "Ch\u01B0\u01A1ng tr\u00ECnh khuy\u1EBFn m\u00E3i",
						"Ng\u00E0y b\u1EAFt \u0111\u1EA7u", "Ng\u00E0y k\u1EBFt th\u00FAc" }));
		scrollPane.setViewportView(table);

		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu:");
		lblNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgayBatDau.setForeground(Color.BLACK);
		lblNgayBatDau.setBounds(555, 235, 150, 30);
		add(lblNgayBatDau);

		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc:");
		lblNgayKetThuc.setForeground(Color.BLACK);
		lblNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgayKetThuc.setBounds(555, 337, 150, 30);
		add(lblNgayKetThuc);

		txtTim = new JTextField();
		txtTim.setForeground(Color.BLACK);
		txtTim.setBounds(10, 23, 317, 30);
		add(txtTim);
		txtTim.setColumns(10);

		JButton btnTim = new JButton("");
		btnTim.setForeground(Color.BLACK);
		btnTim.setBackground(new Color(176, 196, 222));
		btnTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TimKiem();
			}
		});
		btnTim.setIcon(new ImageIcon(panelKhuyenMai.class.getResource("/res/Zoom.png")));
		btnTim.setBounds(337, 23, 57, 30);
		add(btnTim);

		JButton btnChiTit = new JButton("Chi tiết");
		btnChiTit.setBackground(new Color(176, 196, 222));
		btnChiTit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChiTiet();
			}
		});
		btnChiTit.setIcon(new ImageIcon(panelKhuyenMai.class.getResource("/res/info.png")));
		btnChiTit.setForeground(Color.BLACK);
		btnChiTit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChiTit.setBounds(404, 23, 139, 30);
		add(btnChiTit);

		lblLoiMa = new JLabel("");
		lblLoiMa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiMa.setForeground(Color.BLACK);
		lblLoiMa.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiMa.setBounds(555, 102, 272, 19);
		add(lblLoiMa);

		lblLoiTen = new JLabel("");
		lblLoiTen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiTen.setForeground(Color.BLACK);
		lblLoiTen.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiTen.setBounds(555, 204, 272, 19);
		add(lblLoiTen);

		lblStart = new JLabel("");
		lblStart.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStart.setForeground(Color.BLACK);
		lblStart.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblStart.setBounds(555, 307, 272, 19);
		add(lblStart);

		lblEnd = new JLabel("");
		lblEnd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnd.setForeground(Color.BLACK);
		lblEnd.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblEnd.setBounds(555, 408, 272, 19);
		add(lblEnd);

		ngayBatDau = new JDateChooser();
		ngayBatDau.setDateFormatString("yyyy-MM-dd");
		ngayBatDau.setBounds(555, 276, 272, 31);
		add(ngayBatDau);

		ngayKetThuc = new JDateChooser();
		ngayKetThuc.setDateFormatString("yyyy-MM-dd");
		ngayKetThuc.setBounds(555, 377, 272, 31);
		add(ngayKetThuc);
		load_data();
	}

	String maCu = "";

	public void load_data() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			String sqlInsert = "select * from KhuyenMai";
			ResultSet rs = st.executeQuery(sqlInsert);
			list.clear();
			while (rs.next()) {
				KhuyenMai km = new KhuyenMai();
				km.setMaKhuyenMai(rs.getString("MaKM"));
				km.setTenKhuyenMai(rs.getString("TenKM"));
				km.setNgayBatDau(rs.getString("NgayBatDau"));
				km.setNgayKetThuc(rs.getString("NgayKetThuc"));
				list.add(km);
			}
			rs.close();
			st.close();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (KhuyenMai km : list) {
				Object[] row = new Object[] { km.getMaKhuyenMai(), km.getTenKhuyenMai(), km.getNgayBatDau(),
						km.getNgayKetThuc() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	boolean checkMa(String input) {
		String sql = "SELECT MaKM FROM KhuyenMai;";
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

	boolean formatter(String start, String end) {
		try {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateStart = LocalDate.parse(start, dateFormatter);
			LocalDate dateEnd = LocalDate.parse(end, dateFormatter);
			int compareResult = dateStart.compareTo(dateEnd);
			if (compareResult < 0) {
				return true;
			} else if (compareResult > 0) {
				lblEnd.setText("Ngày kết thúc phải sau ngày bắt đầu.");
				return false;
			} else {
				lblEnd.setText("Lỗi định dạng.");
				return false;
			}
		} catch (Exception e) {
			lblEnd.setText("Lỗi định dạng.");
			return false;
		}
	}

	boolean checkLoi(String ma, String ten, String start, String end) {
		if (!BatLoi.Ma(ma) || checkMa(ma)) {
			lblLoiMa.setText("Mã khuyến mãi không hợp lệ.");
			return false;
		} else {
			lblLoiMa.setText("");
		}
		if (0 > ten.length() || ten.length() > 50 || ten.equals("")) {
			lblLoiTen.setText("Tên không hợp lệ.");
			return false;
		} else {
			lblLoiTen.setText("");
		}
//		if (!BatLoi.NamThangNgay(start) || start.equals("")) {
//			lblStart.setText("Không đúng định dạng năm-tháng-ngày.");
//			return false;
//		} else {
//			lblStart.setText("");
//		}
//		if (!BatLoi.NamThangNgay(end) || end.equals("")) {
//			lblEnd.setText("Không đúng định dạng năm-tháng-ngày.");
//			return false;
//		} else {
//			lblEnd.setText("");
//		}
		if (!formatter(start, end)) {
			return false;
		} else {
			lblEnd.setText("");
		}
		return true;
	}

	boolean checkLoiUpdate(String ma, String ten, String start, String end) {
		if (!BatLoi.Ma(ma) || !checkMa(ma)) {
			lblLoiMa.setText("Mã khuyến mãi trùng.");
			return false;
		} else {
			lblLoiMa.setText("");
		}
		if (0 > ten.length() || ten.length() > 50 || ten.equals("")) {
			lblLoiTen.setText("Tên không hợp lệ.");
			return false;
		} else {
			lblLoiTen.setText("");
		}
//		if (!BatLoi.NamThangNgay(start) || start.equals("")) {
//			lblStart.setText("Không đúng định dạng năm-tháng-ngày.");
//			return false;
//		} else {
//			lblStart.setText("");
//		}
//		if (!BatLoi.NamThangNgay(end) || end.equals("")) {
//			lblEnd.setText("Không đúng định dạng năm-tháng-ngày.");
//			return false;
//		} else {
//			lblEnd.setText("");
//		}
		if (!formatter(start, end)) {
			return false;
		} else {
			lblEnd.setText("");
		}
		return true;
	}

	public boolean Them() {
		String ma = TaoMa.create();
		String ten = txtTenKM.getText();
		String start = Mahoa.dateToString(ngayBatDau.getDate());
		String end = Mahoa.dateToString(ngayKetThuc.getDate());
		if (checkLoi(ma, ten, start, end)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement("insert into KhuyenMai values (?,?,?,?);");
				ps.setString(1, ma);
				ps.setString(2, ten);
				ps.setString(3, start);
				ps.setString(4, end);
				int kq = ps.executeUpdate();
				if (kq == 1) {
					lblEnd.setText("Thêm thành công.");
				} else {
					lblEnd.setText("Thêm thất bại.");
				}
				ps.close();
				con.close();
				load_data();
			} catch (Exception e) {
				lblEnd.setText("");
				e.printStackTrace();
			}
		}
		return true;
	}

	public void CapNhat() {
		String ma = maCu;
		String ten = txtTenKM.getText();
		String start = Mahoa.dateToString(ngayBatDau.getDate());
		String end = Mahoa.dateToString(ngayKetThuc.getDate());
		if (checkLoiUpdate(ma, ten, start, end)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement(
						"UPDATE KhuyenMai SET TenKM = ?, NgayBatDau = ?, NgayKetThuc = ? WHERE MaKM = ?");
				ps.setString(1, ten);
				ps.setString(2, start);
				ps.setString(3, end);
				ps.setString(4, ma);
				int kq = ps.executeUpdate();
				if (kq == 1) {
					lblEnd.setText("Cập nhật thành công.");
				} else {
					lblEnd.setText("Cập nhật thất bại.");
				}
				ps.close();
				con.close();
				load_data();
			} catch (Exception ex) {
				lblEnd.setText("");
				ex.printStackTrace();
			}
		}
	}

	public boolean TimKiem() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con
					.prepareStatement("select * from KhuyenMai WHERE TenKM LIKE '%" + txtTim.getText() + "%'");
			ResultSet rs = ps.executeQuery();
			list.clear();
			while (rs.next()) {
				KhuyenMai km = new KhuyenMai();
				km.setMaKhuyenMai(rs.getString("MaKM"));
				km.setTenKhuyenMai(rs.getString("TenKM"));
				km.setNgayBatDau(rs.getString("NgayBatDau"));
				km.setNgayKetThuc(rs.getString("NgayKetThuc"));
				list.add(km);
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (KhuyenMai km : list) {
				Object[] row = new Object[] { km.getMaKhuyenMai(), km.getTenKhuyenMai(), km.getNgayBatDau(),
						km.getNgayKetThuc() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	String id = "";

	void ChiTiet() {
		id = txtIDKhuyenMai.getText();
		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa chọn từ bảng.");
		} else if (checkMa(id)) {
			new ChiTietKhuyenMai(id).setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Mã không tồn tại.");
		}
	}

	public void LoadDataToControl(int vitri) throws ParseException {
		txtIDKhuyenMai.setText(list.get(vitri).getMaKhuyenMai());
		txtTenKM.setText(list.get(vitri).getTenKhuyenMai());
		ngayBatDau.setDate(Mahoa.stringToDate(list.get(vitri).getNgayBatDau()));
		ngayKetThuc.setDate(Mahoa.stringToDate(list.get(vitri).getNgayKetThuc()));
	}

	public void First() throws ParseException {
		vitri = 0;
		LoadDataToControl(vitri);
		table.setRowSelectionInterval(vitri, vitri);
	}

	public void Prev() throws ParseException {
		if (vitri > 0) {
			vitri--;
			LoadDataToControl(vitri);
			table.setRowSelectionInterval(vitri, vitri);
		}
	}

	public void Next() throws ParseException {
		if (vitri < list.size() - 1) {
			vitri++;
			LoadDataToControl(vitri);
			table.setRowSelectionInterval(vitri, vitri);
		}
	}

	public void Last() throws ParseException {
		vitri = list.size() - 1;
		LoadDataToControl(vitri);
		table.setRowSelectionInterval(vitri, vitri);
	}
}
