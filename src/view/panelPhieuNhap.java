package view;

import java.awt.Color;
import java.awt.Font;
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
import java.util.ArrayList;

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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Entity.PhieuNhap;
import Utils.BatLoi;
import Utils.JDBC;
import Utils.TaoMa;

public class panelPhieuNhap extends JPanel {
	private JTextField txtNCC;
	private JTextField txtIDPhieu;
	private JTextField txtIDNhanVien;
	private JTable table;
	private JTextField txtNgayNhap;
	private JTextField txtTim;
	private JLabel lblLoiMaPhieu;
	private JLabel lblLoiMaNV;
	private JLabel lblLoiNgay;
	private JLabel lblLoiMaNCC;

	public panelPhieuNhap() {
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
				add();
			}
		});
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setIcon(new ImageIcon(panelPhieuNhap.class.getResource("/res/add.png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setForeground(Color.BLACK);
		btnThem.setBounds(1, 11, 113, 35);
		panel_2.add(btnThem);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(176, 196, 222));
		btnCapNhat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnCapNhat.setIcon(new ImageIcon(panelPhieuNhap.class.getResource("/res/Refresh.png")));
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
				txtIDPhieu.setText(TaoMa.create());
				txtIDNhanVien.setText("");
				txtNCC.setText("");
				txtNgayNhap.setText("");
				loadTable();
			}
		});
		btnMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMoi.setIcon(new ImageIcon(panelPhieuNhap.class.getResource("/res/icon/new.png")));
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
		btnFirst.setIcon(new ImageIcon(panelPhieuNhap.class.getResource("/res/skip_backward.png")));
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
		btnPrev.setIcon(new ImageIcon(panelPhieuNhap.class.getResource("/res/rewind.png")));
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
		btnNext.setIcon(new ImageIcon(panelPhieuNhap.class.getResource("/res/fast_forward.png")));
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
		btnLast.setIcon(new ImageIcon(panelPhieuNhap.class.getResource("/res/skip_forward.png")));
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLast.setForeground(Color.BLACK);
		btnLast.setBounds(298, 11, 79, 35);
		panel_2_1.add(btnLast);

		JLabel lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNCC.setForeground(Color.BLACK);
		lblNCC.setBounds(555, 235, 150, 30);
		add(lblNCC);

		txtNCC = new JTextField();
		txtNCC.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNCC.setForeground(Color.BLACK);
		txtNCC.setColumns(10);
		txtNCC.setBounds(555, 276, 272, 30);
		add(txtNCC);

		txtIDPhieu = new JTextField();
		txtIDPhieu.setEditable(false);
		txtIDPhieu.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtIDPhieu.setForeground(Color.BLACK);
		txtIDPhieu.setColumns(10);
		txtIDPhieu.setBounds(555, 72, 272, 30);
		add(txtIDPhieu);

		JLabel lblIDPhieu = new JLabel("Mã phiếu nhập:");
		lblIDPhieu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIDPhieu.setForeground(Color.BLACK);
		lblIDPhieu.setBounds(555, 31, 150, 30);
		add(lblIDPhieu);

		JLabel lblIDNhanVien = new JLabel("Mã nhân viên:");
		lblIDNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIDNhanVien.setForeground(Color.BLACK);
		lblIDNhanVien.setBounds(555, 133, 150, 30);
		add(lblIDNhanVien);

		txtIDNhanVien = new JTextField();
		txtIDNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtIDNhanVien.setForeground(Color.BLACK);
		txtIDNhanVien.setColumns(10);
		txtIDNhanVien.setBounds(555, 174, 272, 30);
		add(txtIDNhanVien);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 535, 522);
		add(scrollPane);

		table = new JTable();
		table.setForeground(Color.BLACK);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vitri = table.getSelectedRow();
				loadForm(vitri);
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 Phi\u1EBFu",
				"Nh\u00E0 cung c\u1EA5p", "Ng\u00E0y nh\u1EADp", "T\u00EAn nh\u00E2n vi\u00EAn" }));
		scrollPane.setViewportView(table);

		JLabel lblNgayNhap = new JLabel("Ngày nhập hàng");
		lblNgayNhap.setForeground(Color.BLACK);
		lblNgayNhap.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgayNhap.setBounds(555, 337, 150, 30);
		add(lblNgayNhap);

		txtNgayNhap = new JTextField();
		txtNgayNhap.setForeground(Color.BLACK);
		txtNgayNhap.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNgayNhap.setColumns(10);
		txtNgayNhap.setBounds(555, 378, 272, 30);
		add(txtNgayNhap);

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
				find();
			}
		});
		btnTim.setIcon(new ImageIcon(panelPhieuNhap.class.getResource("/res/Zoom.png")));
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
		btnChiTit.setIcon(new ImageIcon(panelPhieuNhap.class.getResource("/res/info.png")));
		btnChiTit.setForeground(Color.BLACK);
		btnChiTit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChiTit.setBounds(406, 24, 139, 35);
		add(btnChiTit);

		lblLoiMaPhieu = new JLabel("");
		lblLoiMaPhieu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiMaPhieu.setForeground(Color.BLACK);
		lblLoiMaPhieu.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiMaPhieu.setBounds(555, 102, 272, 19);
		add(lblLoiMaPhieu);

		lblLoiMaNV = new JLabel("");
		lblLoiMaNV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiMaNV.setForeground(Color.BLACK);
		lblLoiMaNV.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiMaNV.setBounds(555, 204, 272, 19);
		add(lblLoiMaNV);

		lblLoiMaNCC = new JLabel("");
		lblLoiMaNCC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiMaNCC.setForeground(Color.BLACK);
		lblLoiMaNCC.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiMaNCC.setBounds(555, 306, 272, 19);
		add(lblLoiMaNCC);

		lblLoiNgay = new JLabel("");
		lblLoiNgay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiNgay.setForeground(Color.BLACK);
		lblLoiNgay.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiNgay.setBounds(555, 408, 272, 19);
		add(lblLoiNgay);

		JButton btnIn = new JButton("In");
		btnIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				XuatFile();
			}
		});
		btnIn.setBounds(488, 606, 89, 23);
		add(btnIn);
		loadTable();
	}

	ArrayList<PhieuNhap> arr = new ArrayList<>();
	int current = 0;

	void loadTable() {
		arr.clear();
		String sql = "SELECT MaPhieu, NgayNhap, pn.MaNCC, pn.MaNV, nv.HoTen, ncc.TenNCC FROM PhieuNhap AS pn JOIN NhaCungCap AS ncc ON pn.MaNCC = ncc.MaNCC JOIN NhanVien AS nv ON pn.MaNV = nv.MaNV";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();
				pn.setMaPhieuNhap(rs.getString(1));
				pn.setNgayNhapHang(rs.getString(2));
				pn.setMaNCC(rs.getString(3));
				pn.setMaNhanVien(rs.getString(4));
				pn.setTenNhanVien(rs.getString(5));
				pn.setNhaCungCap(rs.getString(6));
				arr.add(pn);
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
		for (PhieuNhap pn : arr) {
			model.addRow(new Object[] { pn.getMaPhieuNhap(), pn.getNhaCungCap(), pn.getNgayNhapHang(),
					pn.getTenNhanVien() });
		}
	}

	void loadForm(int vitri) {
		txtIDPhieu.setText(arr.get(vitri).getMaPhieuNhap());
		txtNCC.setText(arr.get(vitri).getMaNCC());
		txtNgayNhap.setText(arr.get(vitri).getNgayNhapHang());
		txtIDNhanVien.setText(arr.get(vitri).getMaNhanVien());
	}

	void add() {
		arr.clear();
		String maPhieu = TaoMa.create();
		String ngayNhap = txtNgayNhap.getText();
		String nCC = txtNCC.getText();
		String maNV = txtIDNhanVien.getText();
		if (checkLoi(maPhieu, ngayNhap, nCC, maNV)) {
			String sql = "INSERT INTO PhieuNhap(MaPhieu, NgayNhap, MaNCC, MaNV) VALUES('" + maPhieu + "', '" + ngayNhap
					+ "', '" + nCC + "', '" + maNV + "');";
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement(sql);
				int kq = ps.executeUpdate();
				if (kq == 1) {
					JOptionPane.showMessageDialog(this, "Thêm thành công");
				} else {
					JOptionPane.showMessageDialog(this, "Thêm thất bại.");
				}
				ps.close();
				con.close();
				loadTable();
				lblLoiNgay.setText("Thêm thành công.");
			} catch (Exception e) {
				lblLoiNgay.setText("Thêm thất bại.");
				e.printStackTrace();
			}
		}
	}

	public void update() {
		String maPhieu = txtIDPhieu.getText();
		String ngayNhap = txtNgayNhap.getText();
		String nCC = txtNCC.getText();
		String maNV = txtIDNhanVien.getText();
		if (checkLoiUpdate(maPhieu, ngayNhap, nCC, maNV)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con
						.prepareStatement("UPDATE PhieuNhap SET NgayNhap = ?, MaNCC = ?, MaNV = ? Where  MaPhieu = ?");
				ps.setString(1, ngayNhap);
				ps.setString(2, nCC);
				ps.setString(3, maNV);
				ps.setString(4, maPhieu);
				ps.executeUpdate();
				ps.close();
				con.close();
				loadTable();
				lblLoiNgay.setText("Cập nhật thành công.");
			} catch (Exception ex) {
				lblLoiNgay.setText("Cập nhật thất bại.");
				ex.printStackTrace();
			}
		}
	}

	boolean checkLoi(String maPhieu, String ngayNhap, String ncc, String maNV) {
		if (!BatLoi.Ma(maPhieu) || checkMaPhieuNhap(maPhieu)) {
			lblLoiMaPhieu.setText("Mã phiếu không hợp lệ.");
			return false;
		} else {
			lblLoiMaPhieu.setText("");
		}
		if (!BatLoi.Ma(maNV) || !checkMaNV(maNV)) {
			lblLoiMaNV.setText("Mã nhân viên không hợp lệ");
			return false;
		} else {
			lblLoiMaNV.setText("");
		}
		if (!BatLoi.Ma(ncc)) {
			lblLoiMaNCC.setText("Mã nhà cung cấp không hợp lệ.");
			return false;
		}
		if (!BatLoi.NamThangNgay(ngayNhap)) {
			lblLoiNgay.setText("Sai định dạng năm-tháng-ngày.");
			return false;
		} else {
			lblLoiNgay.setText("");
		}
		return true;
	}

	boolean checkLoiUpdate(String maPhieu, String ngayNhap, String ncc, String maNV) {
		if (!BatLoi.Ma(maPhieu) || !checkMaPhieuNhap(maPhieu)) {
			lblLoiMaPhieu.setText("Mã phiếu không hợp lệ.");
			return false;
		} else {
			lblLoiMaPhieu.setText("");
		}
		if (!BatLoi.Ma(maNV) || !checkMaNV(maNV)) {
			lblLoiMaNV.setText("Mã nhân viên không hợp lệ");
			return false;
		} else {
			lblLoiMaNV.setText("");
		}
		if (!BatLoi.Ma(ncc)) {
			lblLoiMaNCC.setText("Mã nhà cung cấp không hợp lệ.");
			return false;
		}
		if (!BatLoi.NamThangNgay(ngayNhap)) {
			lblLoiNgay.setText("Sai định dạng năm-tháng-ngày.");
			return false;
		} else {
			lblLoiNgay.setText("");
		}
		return true;
	}

	boolean checkMaNV(String input) {
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

	boolean checkMaPhieuNhap(String input) {
		String sql = "SELECT MaPhieu FROM PhieuNhap;";
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

	String id = "";

	void ChiTiet() {
		id = txtIDPhieu.getText();
		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa chọn phiếu nhập từ bảng.");
		} else if (checkMaPhieuNhap(id)) {
			new ChiTietPhieuNhap(id).setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Mã không tồn tại.");
		}
	}

	int vitri = -1;

	public void First() {
		vitri = 0;
		loadForm(vitri);
		table.setRowSelectionInterval(vitri, vitri);
	}

	public void Prev() {
		if (vitri > 0) {
			vitri--;
			loadForm(vitri);
			table.setRowSelectionInterval(vitri, vitri);
		}
	}

	public void Next() {
		if (vitri < arr.size() - 1) {
			vitri++;
			loadForm(vitri);
			table.setRowSelectionInterval(vitri, vitri);
		}
	}

	public void Last() {
		vitri = arr.size() - 1;
		loadForm(vitri);
		table.setRowSelectionInterval(vitri, vitri);
	}

	public boolean find() {
		id = txtTim.getText();
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement(
					"SELECT MaPhieu, NgayNhap, pn.MaNCC, pn.MaNV, nv.HoTen, ncc.TenNCC FROM PhieuNhap AS pn JOIN NhaCungCap AS ncc ON pn.MaNCC = ncc.MaNCC JOIN NhanVien AS nv ON pn.MaNV = nv.MaNV WHERE MaPhieu LIKE '%"
							+ txtTim.getText() + "%'");
			ResultSet rs = ps.executeQuery();
			arr.clear();
			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();
				pn.setMaPhieuNhap(rs.getString(1));
				pn.setNgayNhapHang(rs.getString(2));
				pn.setMaNCC(rs.getString(3));
				pn.setMaNhanVien(rs.getString(4));
				pn.setTenNhanVien(rs.getString(5));
				pn.setNhaCungCap(rs.getString(6));
				arr.add(pn);
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (PhieuNhap pn : arr) {
				Object[] row = new Object[] { pn.getMaPhieuNhap(), pn.getNhaCungCap(), pn.getNgayNhapHang(),
						pn.getTenNhanVien() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void XuatFile() {
		try (XSSFWorkbook wordbook = new XSSFWorkbook()) {
			XSSFSheet sheet = wordbook.createSheet("PhieuNhap");
			XSSFRow row = null;
			Cell cell = null;
			row = sheet.createRow(3);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã Phiếu");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Ngày Nhập");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("MaNCC");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Tên nhân viên");

			for (int i = 0; i < arr.size(); i++) {
				row = sheet.createRow(4 + i);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(arr.get(i).getMaPhieuNhap());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(arr.get(i).getNgayNhapHang());
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(arr.get(i).getMaNCC());
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue(arr.get(i).getTenNhanVien());
			}

			File f = new File("C:\\Users\\non\\Desktop\\PhieuNhap.xlsx");

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
