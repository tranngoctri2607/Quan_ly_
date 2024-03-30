package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Entity.HoaDon;
import Utils.BatLoi;
import Utils.JDBC;
import Utils.TaoMa;

public class panelHoaDon extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtIDHoaDon;
	private JTextField txtHoTenKhach;
	private JTextField txtSDT;
	private JTable table;
	private JTextField txtNgay;
	private JTextField txtMaNV;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDateTime now = LocalDateTime.now();
	private JLabel lblLoiMaHd;
	private JLabel lblLoiSDT;
	private JLabel lblLoiMaNV;
	private JLabel lblLoiChuaChon;

	public panelHoaDon(String id) {
		setForeground(Color.BLACK);
		setBackground(new Color(176, 196, 222));
		setBounds(0, 0, 837, 754);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBounds(10, 11, 642, 684);
		add(panel);
		panel.setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(Color.BLACK);
		lblLogo.setBackground(SystemColor.activeCaption);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(panelHoaDon.class.getResource("/res/Logo3.png")));
		lblLogo.setBounds(0, 0, 176, 171);
		panel.add(lblLogo);

		JLabel lblTitle = new JLabel("HÓA ĐƠN BÁN HÀNG");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setBackground(SystemColor.activeCaption);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblTitle.setBounds(288, 12, 176, 34);
		panel.add(lblTitle);

		JLabel lblIDHoaDon = new JLabel("Mã hóa đơn:");
		lblIDHoaDon.setForeground(Color.BLACK);
		lblIDHoaDon.setBackground(SystemColor.activeCaption);
		lblIDHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIDHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIDHoaDon.setBounds(186, 58, 76, 25);
		panel.add(lblIDHoaDon);

		txtIDHoaDon = new JTextField();
		txtIDHoaDon.setEditable(false);
		txtIDHoaDon.setForeground(Color.BLACK);
		txtIDHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIDHoaDon.setBounds(269, 58, 237, 25);
		panel.add(txtIDHoaDon);
		txtIDHoaDon.setColumns(10);

		JLabel lblHoTenKhach = new JLabel("Khách hàng:");
		lblHoTenKhach.setForeground(Color.BLACK);
		lblHoTenKhach.setBackground(SystemColor.activeCaption);
		lblHoTenKhach.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHoTenKhach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHoTenKhach.setBounds(186, 95, 76, 25);
		panel.add(lblHoTenKhach);

		txtHoTenKhach = new JTextField();
		txtHoTenKhach.setForeground(Color.BLACK);
		txtHoTenKhach.setEditable(false);
		txtHoTenKhach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtHoTenKhach.setColumns(10);
		txtHoTenKhach.setBounds(269, 93, 237, 25);
		panel.add(txtHoTenKhach);

		JLabel lblSDT = new JLabel("SDT khách hàng:");
		lblSDT.setForeground(Color.BLACK);
		lblSDT.setBackground(SystemColor.activeCaption);
		lblSDT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSDT.setBounds(168, 132, 94, 25);
		panel.add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setForeground(Color.BLACK);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSDT.setColumns(10);
		txtSDT.setBounds(269, 130, 237, 25);
		panel.add(txtSDT);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 188, 587, 241);
		panel.add(scrollPane);

		table = new JTable();
		table.setForeground(Color.BLACK);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadForm();
			}
		});
		table.setBackground(new Color(255, 255, 255));
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n",
				"Ng\u00E0y l\u1EADp", "Th\u00E0nh ti\u1EC1n", "Nh\u00E2n vi\u00EAn l\u1EADp" }));
		scrollPane.setViewportView(table);

		JLabel lblNgayBan = new JLabel("Ngày bán:");
		lblNgayBan.setForeground(Color.BLACK);
		lblNgayBan.setBackground(SystemColor.activeCaption);
		lblNgayBan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNgayBan.setBounds(288, 440, 67, 25);
		panel.add(lblNgayBan);

		txtNgay = new JTextField();
		txtNgay.setForeground(Color.BLACK);
		txtNgay.setEditable(false);
		txtNgay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNgay.setBounds(347, 440, 185, 25);
		txtNgay.setText(dtf.format(now));
		panel.add(txtNgay);
		txtNgay.setColumns(10);

		JLabel lblNhanVine = new JLabel("Nhân viên bán hàng");
		lblNhanVine.setForeground(Color.BLACK);
		lblNhanVine.setBackground(SystemColor.activeCaption);
		lblNhanVine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhanVine.setBounds(370, 476, 136, 25);
		panel.add(lblNhanVine);

		txtMaNV = new JTextField(id);
		txtMaNV.setEditable(false);
		txtMaNV.setForeground(Color.BLACK);
		txtMaNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaNV.setBounds(347, 513, 185, 25);
		panel.add(txtMaNV);
		txtMaNV.setColumns(10);

		lblLoiMaHd = new JLabel("");
		lblLoiMaHd.setForeground(Color.BLACK);
		lblLoiMaHd.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiMaHd.setBounds(507, 58, 135, 25);
		panel.add(lblLoiMaHd);

		lblLoiSDT = new JLabel("");
		lblLoiSDT.setForeground(Color.BLACK);
		lblLoiSDT.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiSDT.setBounds(507, 132, 135, 20);
		panel.add(lblLoiSDT);

		lblLoiMaNV = new JLabel("");
		lblLoiMaNV.setForeground(Color.BLACK);
		lblLoiMaNV.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiMaNV.setBounds(532, 513, 110, 25);
		panel.add(lblLoiMaNV);

		JButton btnIn = new JButton("In");
		btnIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				XuatFile();
			}
		});
		btnIn.setBounds(52, 461, 89, 23);
		panel.add(btnIn);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(176, 196, 222));
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		btnThem.setIcon(new ImageIcon(panelHoaDon.class.getResource("/res/Basket.png")));
		btnThem.setForeground(Color.BLACK);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBounds(675, 95, 139, 35);
		add(btnThem);

		JButton btnMoi = new JButton("Mới");
		btnMoi.setBackground(new Color(176, 196, 222));
		btnMoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newHD();
			}
		});
		btnMoi.setIcon(new ImageIcon(panelHoaDon.class.getResource("/res/icon/new.png")));
		btnMoi.setForeground(Color.BLACK);
		btnMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMoi.setBounds(675, 39, 139, 35);
		add(btnMoi);

		JButton btnChiTiet = new JButton("Chi tiết");
		btnChiTiet.setBackground(new Color(176, 196, 222));
		btnChiTiet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChiTiet();
			}
		});
		btnChiTiet.setIcon(new ImageIcon(panelHoaDon.class.getResource("/res/info.png")));
		btnChiTiet.setForeground(Color.BLACK);
		btnChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChiTiet.setBounds(675, 155, 139, 35);
		add(btnChiTiet);

		lblLoiChuaChon = new JLabel("");
		lblLoiChuaChon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiChuaChon.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiChuaChon.setForeground(Color.BLACK);
		lblLoiChuaChon.setBounds(675, 191, 139, 19);
		add(lblLoiChuaChon);
		loadTable();
		setVisible(true);
		user = id;
	}

	ArrayList<HoaDon> arr = new ArrayList<>();
	String maCu = "";

	void loadTable() {
		String sql = "SELECT MaHD, NgayLap, HoaDon.MaNV, NhanVien.HoTen, sdtKH, KhachHang.HoTen, TongTien FROM HoaDon JOIN NhanVien ON HoaDon.MaNV = NhanVien.MaNV JOIN KhachHang ON HoaDon.sdtKH = KhachHang.SDT;";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setMaHD(rs.getString(1));
				hd.setNgayLap(rs.getString(2));
				hd.setMaNhanVien(rs.getString(3));
				hd.setTenNhanVien(rs.getString(4));
				hd.setSdtKhachHang(rs.getString(5));
				hd.setTenKhachHang(rs.getString(6));
				hd.setTongTien(rs.getInt(7));
				arr.add(hd);
				loadRow();
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void loadRow() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (HoaDon hd : arr) {
			model.addRow(new Object[] { hd.getMaHD(), hd.getNgayLap(), hd.getTongTien() / 1000 + "K vnd",
					hd.getTenNhanVien() });
		}
	}

	int viTri = -1;

	void loadForm() {
		viTri = table.getSelectedRow();
		txtHoTenKhach.setText(arr.get(viTri).getTenKhachHang());
		txtIDHoaDon.setText(arr.get(viTri).getMaHD());
		maCu = arr.get(viTri).getMaHD();
		txtNgay.setText(arr.get(viTri).getNgayLap());
		txtSDT.setText(arr.get(viTri).getSdtKhachHang());
		txtMaNV.setText(arr.get(viTri).getMaNhanVien());
	}

	void add() {
		boolean check = true;
		arr.clear();
		String maHD = TaoMa.create();
		String ngayLap = txtNgay.getText();
		String maNV = user;
		String sdtKH = txtSDT.getText();
		if (!BatLoi.Ma(maHD) || checkMaHD(maHD)) {
			check = false;
			lblLoiMaHd.setText("Mã hóa đơn không hợp lệ.");
		} else {
			lblLoiMaHd.setText("");
		}
		if (!BatLoi.SoDienThoai(sdtKH) || !checkSDTkh(sdtKH)) {
			check = false;
			lblLoiSDT.setText("Số điện thoại không hợp lệ.");
		} else {
			lblLoiSDT.setText("");
		}
		if (check) {
			String sql = "INSERT INTO HoaDon(MaHD, NgayLap, MaNV, sdtKH, TongTien) VALUES ('" + maHD + "', '" + ngayLap
					+ "', '" + maNV + "', '" + sdtKH + "', 0);";
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				con.close();
				loadTable();
				lblLoiChuaChon.setText("Thêm thành công.");
			} catch (Exception e) {
				lblLoiChuaChon.setText("");
				e.printStackTrace();
			}
		}
	}

	boolean checkMaHD(String input) {
		String sql = "SELECT MaHD FROM HoaDon;";
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

//	boolean checkMaNV(String input) {
//		String sql = "SELECT MaNV FROM NhanVien;";
//		try {
//			Connection con = DriverManager.getConnection(JDBC.url());
//			PreparedStatement ps = con.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				if (rs.getString(1).equalsIgnoreCase(input)) {
//					return true;
//				}
//			}
//			rs.close();
//			ps.close();
//			con.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	boolean checkSDTkh(String input) {
		String sql = "SELECT SDT FROM KhachHang;";
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

	String user;

	void newHD() {
		txtIDHoaDon.setText(TaoMa.create());
		txtHoTenKhach.setText("");
		txtMaNV.setText(user);
		txtNgay.setText(dtf.format(now));
		txtSDT.setText("");
		arr.clear();
		loadTable();
	}

	String maHoaDon = "";

	void ChiTiet() {
		try {
			maHoaDon = txtIDHoaDon.getText();
			if (maHoaDon.equals("")) {
				lblLoiChuaChon.setText("Chọn hóa đơn bên bảng.");
			} else if (checkMaHD(maHoaDon)) {
				lblLoiChuaChon.setText("");
				new ChiTietHoaDon(maHoaDon).setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Mã không tồn tại.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void XuatFile() {
		try (XSSFWorkbook wordbook = new XSSFWorkbook()) {
			XSSFSheet sheet = wordbook.createSheet(txtNgay.getText());
			XSSFRow row = null;
			Cell cell = null;
			row = sheet.createRow(3);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("MaHD");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("NgayLap");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("MaNV");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("sdtKH");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Tổng Tiền");

			for (int i = 0; i < arr.size(); i++) {
				row = sheet.createRow(4 + i);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(arr.get(i).getMaHD());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(arr.get(i).getNgayLap());
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(arr.get(i).getMaNhanVien());
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue(arr.get(i).getSdtKhachHang());
				cell = row.createCell(4, CellType.NUMERIC);
				cell.setCellValue(arr.get(i).getTongTien());

			}

			File f = new File("C:\\Users\\non\\Desktop\\HoaDon.xlsx");

			try {
				FileOutputStream File = new FileOutputStream(f);
				wordbook.write(File);
				File.close();
				JOptionPane.showMessageDialog(this, "In du lieu thanh cong");
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
