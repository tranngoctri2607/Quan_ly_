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

import Entity.LoaiSP;
import Utils.BatLoi;
import Utils.JDBC;
import Utils.TaoMa;

public class panelLoaiSanPham extends JPanel {
	ArrayList<LoaiSP> list = new ArrayList<>();
	int current = 0;
	int vitri = -1;

	private JTextField txtIDLoai;
	private JTextField txtTenLoai;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JTextField txtTim;
	private JLabel lblLoiMa;
	private JLabel lblLoiTen;

	public panelLoaiSanPham() {
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
		btnThem.setIcon(new ImageIcon(panelLoaiSanPham.class.getResource("/res/add.png")));
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
				load_data();
			}
		});
		btnCapNhat.setIcon(new ImageIcon(panelLoaiSanPham.class.getResource("/res/Refresh.png")));
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
				txtIDLoai.setText(TaoMa.create());
				txtTenLoai.setText("");
				load_data();
			}
		});
		btnMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMoi.setIcon(new ImageIcon(panelLoaiSanPham.class.getResource("/res/icon/new.png")));
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
		btnFirst.setIcon(new ImageIcon(panelLoaiSanPham.class.getResource("/res/skip_backward.png")));
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
		btnPrev.setIcon(new ImageIcon(panelLoaiSanPham.class.getResource("/res/rewind.png")));
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
		btnNext.setIcon(new ImageIcon(panelLoaiSanPham.class.getResource("/res/fast_forward.png")));
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
		btnLast.setIcon(new ImageIcon(panelLoaiSanPham.class.getResource("/res/skip_forward.png")));
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLast.setForeground(Color.BLACK);
		btnLast.setBounds(298, 11, 79, 35);
		panel_2_1.add(btnLast);

		txtIDLoai = new JTextField();
		txtIDLoai.setEditable(false);
		txtIDLoai.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtIDLoai.setForeground(Color.BLACK);
		txtIDLoai.setColumns(10);
		txtIDLoai.setBounds(555, 72, 272, 30);
		add(txtIDLoai);

		JLabel lblIDLoai = new JLabel("Mã loại sản phẩm:");
		lblIDLoai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIDLoai.setForeground(Color.BLACK);
		lblIDLoai.setBounds(555, 31, 150, 30);
		add(lblIDLoai);

		JLabel lblTenLoai = new JLabel("Tên loại sản phẩm:");
		lblTenLoai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenLoai.setForeground(Color.BLACK);
		lblTenLoai.setBounds(555, 133, 150, 30);
		add(lblTenLoai);

		txtTenLoai = new JTextField();
		txtTenLoai.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTenLoai.setForeground(Color.BLACK);
		txtTenLoai.setColumns(10);
		txtTenLoai.setBounds(555, 174, 272, 30);
		add(txtTenLoai);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 535, 522);
		add(scrollPane);

		table = new JTable();
		table.setForeground(Color.BLACK);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				current = table.getSelectedRow();
				txtIDLoai.setText(table.getValueAt(current, 0).toString());
				maCu = table.getValueAt(current, 0).toString();
				txtTenLoai.setText(table.getValueAt(current, 1).toString());
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 lo\u1EA1i s\u1EA3n ph\u1EA9m", "T\u00EAn lo\u1EA1i s\u1EA3n ph\u1EA9m" }));
		scrollPane.setViewportView(table);

		txtTim = new JTextField();
		txtTim.setForeground(Color.BLACK);
		txtTim.setBounds(10, 23, 317, 30);
		add(txtTim);
		txtTim.setColumns(10);

		JButton btnTim = new JButton("");
		btnTim.setBackground(new Color(176, 196, 222));
		btnTim.setForeground(Color.BLACK);
		btnTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TimKiem();
			}
		});
		btnTim.setIcon(new ImageIcon(panelLoaiSanPham.class.getResource("/res/Zoom.png")));
		btnTim.setBounds(337, 23, 71, 30);
		add(btnTim);

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
		lblLoiTen.setBounds(555, 203, 272, 19);
		add(lblLoiTen);
		load_data();
	}

	String maCu = "";

	public void load_data() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			String sqlInsert = "select * from LoaiSP";
			ResultSet rs = st.executeQuery(sqlInsert);
			list.clear();
			while (rs.next()) {
				LoaiSP lsp = new LoaiSP();
				lsp.setMaLoai(rs.getString("MaLoai"));
				lsp.setTenLoai(rs.getString("TenLoai"));
				list.add(lsp);
			}
			rs.close();
			st.close();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (LoaiSP lsp : list) {
				Object[] row = new Object[] { lsp.getMaLoai(), lsp.getTenLoai() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean Them() {
		String ma = TaoMa.create();
		String ten = txtTenLoai.getText();
		if (checkLoi(ma, ten)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement("insert into LoaiSP values (?,?);");
				ps.setString(1, ma);
				ps.setString(2, ten);
				int kq = ps.executeUpdate();
				if (kq == 1) {
					lblLoiTen.setText("Thêm thành công.");
				} else {
					lblLoiTen.setText("Thêm thất bại.");
				}
				ps.close();
				con.close();
				load_data();
			} catch (Exception e) {
				lblLoiTen.setText("");
				e.printStackTrace();
			}
		}
		return true;
	}

	public void TimKiem() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con
					.prepareStatement("select * from LoaiSP WHERE TenLoai LIKE '%" + txtTim.getText() + "%';");
			ResultSet rs = ps.executeQuery();
			list.clear();
			while (rs.next()) {
				LoaiSP lsp = new LoaiSP();
				lsp.setMaLoai(rs.getString("MaLoai"));
				lsp.setTenLoai(rs.getString("TenLoai"));
				list.add(lsp);
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (LoaiSP lsp : list) {
				Object[] row = new Object[] { lsp.getMaLoai(), lsp.getTenLoai() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CapNhat() {
		String ma = maCu;
		String ten = txtTenLoai.getText();
		if (checkLoiUpdate(ma, ten)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement("UPDATE LoaiSP SET TenLoai = ? where MaLoai = ?");
				ps.setString(2, ma);
				ps.setString(1, ten);
				int kq = ps.executeUpdate();
				if (kq == 1) {
					lblLoiTen.setText("Cập nhật thành công.");
				} else {
					lblLoiTen.setText("Cập nhật thất bại.");
				}
				ps.close();
				con.close();
				this.load_data();
				lblLoiTen.setText("Cập nhật thành công.");
			} catch (Exception ex) {
				lblLoiTen.setText("");
				ex.printStackTrace();
			}
		}
	}

	public void LoadDataToControl(int vitri) {
		txtIDLoai.setText(list.get(vitri).getMaLoai());
		txtTenLoai.setText(list.get(vitri).getTenLoai());
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
		String sql = "SELECT MaLoai FROM LoaiSP;";
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

	boolean checkLoi(String ma, String ten) {
		if (!BatLoi.Ma(ma) || checkMa(ma)) {
			lblLoiMa.setText("Mã không hợp lệ.");
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
		return true;
	}

	boolean checkLoiUpdate(String ma, String ten) {
		if (!BatLoi.Ma(ma) || !checkMa(ma)) {
			lblLoiMa.setText("Mã không hợp lệ.");
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
		return true;
	}
}
