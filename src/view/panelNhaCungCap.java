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
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Entity.NhaCungCap;
import Utils.BatLoi;
import Utils.JDBC;
import Utils.TaoMa;

public class panelNhaCungCap extends JPanel {

	private JTextField txtSDT;
	private JTextField txtIDNCC;
	private JTextField txtTenNCC;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtDiaChi;
	private JTextField txtTim;

	int current = -1;
	int vitri = 0;
	private String header[] = { "Mã nhà cung cấp", "Tên nhà cung cấp", "SDT", "Địa chỉ" };
	private DefaultTableModel tblModel = new DefaultTableModel(header, 0);
	private JTable table_1;
	private JButton btnPrev;
	private JButton btnNext;
	private JLabel lblLoiMa;
	private JLabel lblLoiTen;
	private JLabel lblLoiDiaChi;
	private JLabel lblLoiSdt;

	public panelNhaCungCap() {
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
				String idNCC = TaoMa.create();
				String tenNCC = txtTenNCC.getText();
				String sdt = txtSDT.getText();
				String diaChi = txtDiaChi.getText();
				if (checkLoi(idNCC, tenNCC, sdt, diaChi)) {
					try {
						Connection con = DriverManager.getConnection(JDBC.url());
						PreparedStatement ps = con.prepareStatement("insert into NhaCungCap values (?,?,?,?);");
						ps.setString(1, idNCC);
						ps.setString(2, tenNCC);
						ps.setString(3, sdt);
						ps.setString(4, diaChi);
						ps.execute();
						lblLoiDiaChi.setText("Thêm thành công.");
						load_data();
					} catch (Exception e1) {
						lblLoiDiaChi.setText("");
						e1.printStackTrace();
					}
				}
			}
		});
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setIcon(new ImageIcon(panelNhaCungCap.class.getResource("/res/add.png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setForeground(Color.BLACK);
		btnThem.setBounds(1, 11, 113, 35);
		panel_2.add(btnThem);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(176, 196, 222));
		btnCapNhat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idNCC = txtIDNCC.getText();
				String tenNCC = txtTenNCC.getText();
				String sdt = txtSDT.getText();
				String diaChi = txtDiaChi.getText();
				if (checkLoiUpdate(idNCC, tenNCC, sdt, diaChi)) {
					try {
						Connection con = DriverManager.getConnection(JDBC.url());
						PreparedStatement ps = con
								.prepareStatement("update NhaCungCap set TenNCC=?,SDT=?,DiaChi=? where MaNCC=? ");
						ps.setString(1, tenNCC);
						ps.setString(2, sdt);
						ps.setString(3, diaChi);
						ps.setString(4, idNCC);
						ps.execute();
						load_data();
						lblLoiDiaChi.setText("Cập nhật thành công.");
					} catch (Exception e1) {
						lblLoiDiaChi.setText("");
						e1.printStackTrace();
					}
				}
			}
		});
		btnCapNhat.setIcon(new ImageIcon(panelNhaCungCap.class.getResource("/res/Refresh.png")));
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
				txtIDNCC.setText(TaoMa.create());
				txtTenNCC.setText("");
				txtSDT.setText("");
				txtDiaChi.setText("");
			}
		});

		btnMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMoi.setIcon(new ImageIcon(panelNhaCungCap.class.getResource("/res/icon/new.png")));
		btnMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMoi.setForeground(Color.BLACK);
		btnMoi.setBounds(265, 11, 108, 35);
		panel_2.add(btnMoi);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSDT.setForeground(Color.BLACK);
		txtSDT.setColumns(10);
		txtSDT.setBounds(555, 276, 272, 30);
		add(txtSDT);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSDT.setForeground(Color.BLACK);
		lblSDT.setBounds(555, 235, 150, 30);
		add(lblSDT);

		txtIDNCC = new JTextField();
		txtIDNCC.setEditable(false);
		txtIDNCC.setBackground(new Color(255, 255, 255));
		txtIDNCC.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtIDNCC.setForeground(Color.BLACK);
		txtIDNCC.setColumns(10);
		txtIDNCC.setBounds(555, 72, 272, 30);
		add(txtIDNCC);

		JLabel lblIDNCC = new JLabel("Mã nhà cung cấp:");
		lblIDNCC.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIDNCC.setForeground(Color.BLACK);
		lblIDNCC.setBounds(555, 31, 150, 30);
		add(lblIDNCC);

		JLabel lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNCC.setForeground(Color.BLACK);
		lblNCC.setBounds(555, 133, 150, 30);
		add(lblNCC);

		txtTenNCC = new JTextField();
		txtTenNCC.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTenNCC.setForeground(Color.BLACK);
		txtTenNCC.setColumns(10);
		txtTenNCC.setBounds(555, 174, 272, 30);
		add(txtTenNCC);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(10, 70, 535, 522);
		add(scrollPane);

		this.table_1 = new JTable();
		table_1.setForeground(Color.BLACK);
		table_1.setCellSelectionEnabled(true);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				current = table_1.getSelectedRow();
				showDetails();
			}
		});

		this.table_1.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 nh\u00E0 cung c\u1EA5p",
				"T\u00EAn nh\u00E0 cung c\u1EA5p", "SDT", "\u0110\u1ECBa ch\u1EC9" }));
		scrollPane.setViewportView(table_1);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setForeground(Color.BLACK);
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiaChi.setBounds(555, 337, 150, 30);
		add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setForeground(Color.BLACK);
		txtDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(555, 378, 272, 30);
		add(txtDiaChi);

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
		btnTim.setIcon(new ImageIcon(panelNhaCungCap.class.getResource("/res/Zoom.png")));
		btnTim.setBounds(337, 23, 71, 30);
		add(btnTim);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(176, 196, 222));
		panel_2_1.setLayout(null);
		panel_2_1.setForeground(Color.BLACK);
		panel_2_1.setBounds(80, 607, 398, 57);
		add(panel_2_1);

		JButton btnFirst = new JButton("");
		btnFirst.setBackground(new Color(176, 196, 222));
		btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				First();
			}
		});
		btnFirst.setIcon(new ImageIcon(panelNhaCungCap.class.getResource("/res/skip_backward.png")));
		btnFirst.setForeground(Color.BLACK);
		btnFirst.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFirst.setBounds(1, 11, 79, 35);
		panel_2_1.add(btnFirst);

		btnPrev = new JButton("");
		btnPrev.setBackground(new Color(176, 196, 222));
		btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Prev();
			}
		});
		btnPrev.setIcon(new ImageIcon(panelNhaCungCap.class.getResource("/res/rewind.png")));
		btnPrev.setForeground(Color.BLACK);
		btnPrev.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrev.setBounds(100, 11, 79, 35);
		panel_2_1.add(btnPrev);

		btnNext = new JButton("");
		btnNext.setBackground(new Color(176, 196, 222));
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Next();
			}
		});
		btnNext.setIcon(new ImageIcon(panelNhaCungCap.class.getResource("/res/fast_forward.png")));
		btnNext.setForeground(Color.BLACK);
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		btnLast.setIcon(new ImageIcon(panelNhaCungCap.class.getResource("/res/skip_forward.png")));
		btnLast.setForeground(Color.BLACK);
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLast.setBounds(288, 11, 79, 35);
		panel_2_1.add(btnLast);

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

		lblLoiDiaChi = new JLabel("");
		lblLoiDiaChi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiDiaChi.setForeground(Color.BLACK);
		lblLoiDiaChi.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiDiaChi.setBounds(555, 407, 272, 19);
		add(lblLoiDiaChi);

		lblLoiSdt = new JLabel("");
		lblLoiSdt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiSdt.setForeground(Color.BLACK);
		lblLoiSdt.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiSdt.setBounds(555, 306, 272, 19);
		add(lblLoiSdt);
		load_data();
	}

	ArrayList<NhaCungCap> arr = new ArrayList<>();

	public void load_data() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement("select * from NhaCungCap");
			ResultSet rs = ps.executeQuery();
			arr.clear();
			while (rs.next()) {
				NhaCungCap CC = new NhaCungCap();
				CC.setMaNCC(rs.getString("MaNCC"));
				CC.setTenNCC(rs.getString("TenNCC"));
				CC.setSDT(rs.getString("SDT"));
				CC.setDiaChi(rs.getString("DiaChi"));
				arr.add(CC);
			}
			rs.close();
			ps.close();
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.setRowCount(0);
			for (NhaCungCap CC : arr) {
				Object[] row = new Object[] { CC.getMaNCC(), CC.getTenNCC(), CC.getSDT(), CC.getDiaChi() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showDetails() {
		txtIDNCC.setText(table_1.getValueAt(current, 0).toString());
		txtTenNCC.setText(table_1.getValueAt(current, 1).toString());
		txtSDT.setText(table_1.getValueAt(current, 2).toString());
		txtDiaChi.setText(table_1.getValueAt(current, 3).toString());
	}

	public void LoadDataToControl(int vitri) {
		txtIDNCC.setText(arr.get(vitri).getMaNCC());
		txtTenNCC.setText(arr.get(vitri).getTenNCC());
		txtSDT.setText(arr.get(vitri).getSDT());
		txtDiaChi.setText(arr.get(vitri).getDiaChi());
	}

	public void First() {
		vitri = 0;
		LoadDataToControl(vitri);
		table_1.setRowSelectionInterval(vitri, vitri);
	}

	public void Prev() {
		if (vitri > 0) {
			vitri--;
			LoadDataToControl(vitri);
			table_1.setRowSelectionInterval(vitri, vitri);
		} else {
//			btnPrev.setEnabled(false);
		}
	}

	public void Next() {
		if (vitri < arr.size() - 1) {
			vitri++;
			LoadDataToControl(vitri);
			table_1.setRowSelectionInterval(vitri, vitri);
		} else {
//			btnNext.setEnabled(false);
		}
	}

	public void Last() {
		vitri = arr.size() - 1;
		LoadDataToControl(vitri);
		table_1.setRowSelectionInterval(vitri, vitri);
	}

	public void TimKiem() {
		String search = txtTim.getText();
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con
					.prepareStatement("select * from NhaCungCap where TenNCC LIKE '%" + search + "%'");
			ResultSet rs = ps.executeQuery();
			arr.clear();
			while (rs.next()) {
				NhaCungCap CC = new NhaCungCap();
				CC.setMaNCC(rs.getString("MaNCC"));
				CC.setTenNCC(rs.getString("TenNCC"));
				CC.setSDT(rs.getString("SDT"));
				CC.setDiaChi(rs.getString("DiaChi"));
				arr.add(CC);
			}
			rs.close();
			ps.close();
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.setRowCount(0);
			for (NhaCungCap CC : arr) {
				Object[] row = new Object[] { CC.getMaNCC(), CC.getTenNCC(), CC.getSDT(), CC.getDiaChi() };
				model.addRow(row);
			}
			lblLoiDiaChi.setText("");
		} catch (Exception e) {
			lblLoiDiaChi.setText("");
			e.printStackTrace();
		}
	}

	boolean checkMa(String input) {
		String sql = "SELECT MaNCC FROM NhaCungCap;";
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

	boolean checkLoi(String ma, String ten, String sdt, String diaChi) {
		if (!BatLoi.Ma(ma) || checkMa(ma)) {
			lblLoiMa.setText("Mã nhà cung cấp không hợp lệ.");
			return false;
		} else {
			lblLoiMa.setText("");
		}
		if (ten.length() <= 0 || ten.length() > 50) {
			lblLoiTen.setText("Tên không hợp lệ.");
			return false;
		} else {
			lblLoiTen.setText("");
		}
		if (!BatLoi.SoDienThoai(sdt)) {
			lblLoiSdt.setText("Số điện thoại không hợp lệ.");
			return false;
		} else {
			lblLoiSdt.setText("");
		}
		if (diaChi.equals("")) {
			lblLoiDiaChi.setText("Địa chỉ trống");
			return false;
		} else {
			lblLoiDiaChi.setText("");
		}
		return true;
	}

	boolean checkLoiUpdate(String ma, String ten, String sdt, String diaChi) {
		if (!BatLoi.Ma(ma) || !checkMa(ma)) {
			lblLoiMa.setText("Mã nhà cung cấp trùng.");
			return false;
		} else {
			lblLoiMa.setText("");
		}
		if (0 >= ten.length() || ten.length() > 50) {
			lblLoiTen.setText("Tên không hợp lệ.");
			return false;
		} else {
			lblLoiTen.setText("");
		}
		if (!BatLoi.SoDienThoai(sdt)) {
			lblLoiSdt.setText("Số điện thoại không hợp lệ.");
			return false;
		} else {
			lblLoiSdt.setText("");
		}
		if (diaChi.equals("")) {
			lblLoiDiaChi.setText("Địa chỉ trống");
			return false;
		} else {
			lblLoiDiaChi.setText("");
		}
		return true;
	}
}
