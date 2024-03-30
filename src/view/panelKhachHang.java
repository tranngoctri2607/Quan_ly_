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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Entity.KhachHang;
import Utils.BatLoi;
import Utils.JDBC;

public class panelKhachHang extends JPanel {

	ArrayList<KhachHang> list = new ArrayList<>();
	int current = 0;
	int vitri = -1;

	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JTextField txtEmail;
	private JTextField txtTim;
	private JLabel lblLoiSDT;
	private JLabel lblLoiTen;
	private JLabel lblLoiDC;
	private JLabel lblLoiEmail;

	public panelKhachHang() {
		setForeground(Color.BLACK);
		setBackground(new Color(176, 196, 222));
		setBounds(0, 0, 837, 754);
		setLayout(null);
		setVisible(true);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(176, 196, 222));
		panel_2.setForeground(Color.BLACK);
		panel_2.setLayout(null);
		panel_2.setBounds(428, 606, 398, 57);
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
		btnThem.setIcon(new ImageIcon(panelKhachHang.class.getResource("/res/add.png")));
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
		btnCapNhat.setIcon(new ImageIcon(panelKhachHang.class.getResource("/res/Refresh.png")));
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
				txtSDT.setText("");
				txtName.setText("");
				txtDiaChi.setText("");
				txtEmail.setText("");
				load_data();
			}
		});
		btnMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMoi.setIcon(new ImageIcon(panelKhachHang.class.getResource("/res/icon/new.png")));
		btnMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMoi.setForeground(Color.BLACK);
		btnMoi.setBounds(265, 11, 108, 35);
		panel_2.add(btnMoi);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(176, 196, 222));
		panel_2_1.setForeground(Color.BLACK);
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(20, 606, 398, 57);
		add(panel_2_1);

		JButton btnFirst = new JButton("");
		btnFirst.setBackground(new Color(176, 196, 222));
		btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				First();
			}
		});
		btnFirst.setIcon(new ImageIcon(panelKhachHang.class.getResource("/res/skip_backward.png")));
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
		btnPrev.setIcon(new ImageIcon(panelKhachHang.class.getResource("/res/rewind.png")));
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
		btnNext.setIcon(new ImageIcon(panelKhachHang.class.getResource("/res/fast_forward.png")));
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
		btnLast.setIcon(new ImageIcon(panelKhachHang.class.getResource("/res/skip_forward.png")));
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLast.setForeground(Color.BLACK);
		btnLast.setBounds(298, 11, 79, 35);
		panel_2_1.add(btnLast);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiaChi.setForeground(Color.BLACK);
		lblDiaChi.setBounds(555, 235, 150, 30);
		add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDiaChi.setForeground(Color.BLACK);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(555, 276, 272, 30);
		add(txtDiaChi);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSDT.setForeground(Color.BLACK);
		txtSDT.setColumns(10);
		txtSDT.setBounds(555, 72, 272, 30);
		add(txtSDT);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSDT.setForeground(Color.BLACK);
		lblSDT.setBounds(555, 31, 150, 30);
		add(lblSDT);

		JLabel lblHoVaTen = new JLabel("Họ và tên:");
		lblHoVaTen.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHoVaTen.setForeground(Color.BLACK);
		lblHoVaTen.setBounds(555, 133, 150, 30);
		add(lblHoVaTen);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtName.setForeground(Color.BLACK);
		txtName.setColumns(10);
		txtName.setBounds(555, 174, 272, 30);
		add(txtName);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 535, 508);
		add(scrollPane);

		table = new JTable();
		table.setForeground(Color.BLACK);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vitri = table.getSelectedRow();
				LoadDataToControl(vitri);
			}
		});
//		table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//                            current = table.getSelectedRow();
//                            txtSDT.setText(table.getValueAt(current, 0).toString());
//                            txtName.setText(table.getValueAt(current, 1).toString());
//                            txtDiaChi.setText(table.getValueAt(current, 3).toString());
//                            txtEmail.setText(table.getValueAt(current, 4).toString());
//			}
//		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i",
				"H\u1ECD t\u00EAn", "\u0110\u1ECBa ch\u1EC9", "Email" }));
		scrollPane.setViewportView(table);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setBounds(555, 337, 150, 30);
		add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setColumns(10);
		txtEmail.setBounds(555, 378, 272, 30);
		add(txtEmail);

		txtTim = new JTextField();
		txtTim.setForeground(Color.BLACK);
		txtTim.setColumns(10);
		txtTim.setBounds(10, 23, 331, 30);
		add(txtTim);

		JButton btnTim = new JButton("");
		btnTim.setForeground(Color.BLACK);
		btnTim.setBackground(new Color(176, 196, 222));
		btnTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TimKiem();
			}
		});
		btnTim.setIcon(new ImageIcon(panelKhachHang.class.getResource("/res/Zoom.png")));
		btnTim.setBounds(351, 23, 57, 30);
		add(btnTim);

		lblLoiSDT = new JLabel("");
		lblLoiSDT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiSDT.setForeground(Color.BLACK);
		lblLoiSDT.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiSDT.setBounds(555, 101, 272, 19);
		add(lblLoiSDT);

		lblLoiTen = new JLabel("");
		lblLoiTen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiTen.setForeground(Color.BLACK);
		lblLoiTen.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiTen.setBounds(555, 203, 272, 19);
		add(lblLoiTen);

		lblLoiDC = new JLabel("");
		lblLoiDC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiDC.setForeground(Color.BLACK);
		lblLoiDC.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiDC.setBounds(555, 305, 272, 19);
		add(lblLoiDC);

		lblLoiEmail = new JLabel("");
		lblLoiEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiEmail.setForeground(Color.BLACK);
		lblLoiEmail.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiEmail.setBounds(555, 406, 272, 19);
		add(lblLoiEmail);

		JLabel lblLoiTen_1 = new JLabel("");
		lblLoiTen_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiTen_1.setForeground(Color.BLACK);
		lblLoiTen_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiTen_1.setBounds(555, 321, 272, 19);
		add(lblLoiTen_1);
		load_data();
	}

	public void load_data() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			String sqlInsert = "select * from KhachHang";
			ResultSet rs = st.executeQuery(sqlInsert);
			list.clear();
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setSdt(rs.getString("SDT"));
				kh.setHoTen(rs.getString("HoTen"));
				kh.setDiaChi(rs.getString("DiaChi"));
				kh.setEmail(rs.getString("Email"));
				list.add(kh);
			}
			rs.close();
			st.close();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (KhachHang kh : list) {
				Object[] row = new Object[] { kh.getSdt(), kh.getHoTen(), kh.getDiaChi(), kh.getEmail() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CapNhat() {
		String sdt = txtSDT.getText();
		String ten = txtName.getText();
		String diaChi = txtDiaChi.getText();
		String email = txtEmail.getText();
		if (checkLoiUpdate(sdt, ten, email, diaChi)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con
						.prepareStatement("UPDATE KhachHang SET  HoTen = ?, DiaChi = ?, Email = ? where SDT = ?");
				ps.setString(1, ten);
				ps.setString(2, diaChi);
				ps.setString(3, email);
				ps.setString(4, sdt);
				int kq = ps.executeUpdate();
				if (kq == 1) {
					lblLoiEmail.setText("Cập nhật thành công");
				} else {
					lblLoiEmail.setText("Cập nhật thất bại");
				}
				ps.close();
				con.close();
				load_data();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void Them() {
		String sdt = txtSDT.getText();
		String ten = txtName.getText();
		String email = txtEmail.getText();
		String diaChi = txtDiaChi.getText();
		if (checkLoi(sdt, ten, email, diaChi)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement("insert into KhachHang values (?,?,?,?);");
				ps.setString(1, sdt);
				ps.setString(2, ten);
				ps.setString(3, diaChi);
				ps.setString(4, email);
				int kq = ps.executeUpdate();
				if (kq == 1) {
					lblLoiEmail.setText("Thêm thành công");
				} else {
					lblLoiEmail.setText("Thêm thất bại");
				}
				ps.close();
				con.close();
				load_data();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean TimKiem() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con
					.prepareStatement("select * from KhachHang WHERE SDT LIKE '%" + txtTim.getText() + "%'");
			ResultSet rs = ps.executeQuery();
			list.clear();
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setSdt(rs.getString("SDT"));
				kh.setHoTen(rs.getString("HoTen"));
				kh.setDiaChi(rs.getString("DiaChi"));
				kh.setEmail(rs.getString("Email"));
				list.add(kh);
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (KhachHang kh : list) {
				Object[] row = new Object[] { kh.getSdt(), kh.getHoTen(), kh.getDiaChi(), kh.getEmail() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public void LoadDataToControl(int vitri) {
		txtSDT.setText(list.get(vitri).getSdt());
		txtName.setText(list.get(vitri).getHoTen());
		txtDiaChi.setText(list.get(vitri).getDiaChi());
		txtEmail.setText(list.get(vitri).getEmail());
	}

	public void First() {
		vitri = 0;
		LoadDataToControl(vitri);
		table.setRowSelectionInterval(vitri, vitri);
	}

	public void Prev() {
		if (vitri > 0) {
			vitri--;
			LoadDataToControl(vitri);
			table.setRowSelectionInterval(vitri, vitri);
		}
	}

	public void Next() {
		if (vitri < list.size() - 1) {
			vitri++;
			LoadDataToControl(vitri);
			table.setRowSelectionInterval(vitri, vitri);
		}
	}

	public void Last() {
		vitri = list.size() - 1;
		LoadDataToControl(vitri);
		table.setRowSelectionInterval(vitri, vitri);
	}

	boolean checkMa(String input) {
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

	boolean checkLoi(String sdt, String ten, String email, String diaChi) {
		if (!BatLoi.SoDienThoai(sdt) || checkMa(sdt)) {
			lblLoiSDT.setText("Số điện thoại khách hàng không hợp lệ.");
			return false;
		} else {
			lblLoiSDT.setText("");
		}
		if (!BatLoi.TenTiengViet(ten)) {
			lblLoiTen.setText("Tên không hợp lệ.");
			return false;
		} else {
			lblLoiTen.setText("");
		}
		if (diaChi.equals("")) {
			lblLoiDC.setText("Địa chỉ trống.");
			return false;
		} else {
			lblLoiDC.setText("");
		}
		if (!BatLoi.Email(email)) {
			lblLoiEmail.setText("Email không hợp lệ.");
			return false;
		} else {
			lblLoiEmail.setText("");
		}
		return true;
	}

	boolean checkLoiUpdate(String sdt, String ten, String email, String diaChi) {
		if (!BatLoi.SoDienThoai(sdt) || !checkMa(sdt)) {
			lblLoiSDT.setText("Số điện thoại khách hàng không hợp lệ.");
			return false;
		} else {
			lblLoiSDT.setText("");
		}
		if (!BatLoi.TenTiengViet(ten)) {
			lblLoiTen.setText("Tên không hợp lệ.");
			return false;
		} else {
			lblLoiTen.setText("");
		}
		if (diaChi.equals("")) {
			lblLoiDC.setText("Địa chỉ trống.");
			return false;
		} else {
			lblLoiDC.setText("");
		}
		if (!BatLoi.Email(email)) {
			lblLoiEmail.setText("Email không hợp lệ.");
			return false;
		} else {
			lblLoiEmail.setText("");
		}
		return true;
	}
}
