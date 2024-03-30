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

import Entity.ThuongHieu;
import Utils.BatLoi;
import Utils.JDBC;
import Utils.TaoMa;

public class panelThuongHieu extends JPanel {

	private JTextField txtEmail;
	private JTextField txtIDTH;
	private JTextField txtTenTH;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JTextField txtTim;
	int current = 0;
	private String header[] = { "Mã thương hiệu ", "Tên nhà thương hiệu", "Email" };
	private DefaultTableModel tblModel = new DefaultTableModel(header, 0);
	private JLabel lblLoiMa;
	private JLabel lblLoiTen;
	private JLabel lblLoiEmail;

	public panelThuongHieu() {
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
				String idTH = TaoMa.create();
				String tenTH = txtTenTH.getText();
				String email = txtEmail.getText();
				String sql = "insert into ThuongHieu values ('" + idTH + "', N'" + tenTH + "', '" + email + "');";
				if (checkLoi(idTH, tenTH, email)) {
					try {
						Connection con = DriverManager.getConnection(JDBC.url());
						PreparedStatement ps = con.prepareStatement(sql);
						ps.executeUpdate();
						ps.close();
						con.close();
						load_data();
						lblLoiEmail.setText("Thêm thành công.");
					} catch (Exception e1) {
						lblLoiEmail.setText("Thêm thất bại");
						e1.printStackTrace();
					}
				}
			}
		});
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setIcon(new ImageIcon(panelThuongHieu.class.getResource("/res/add.png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setForeground(Color.BLACK);
		btnThem.setBounds(1, 11, 113, 35);
		panel_2.add(btnThem);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(176, 196, 222));
		btnCapNhat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idTH = txtIDTH.getText();
				String tenTH = txtTenTH.getText();
				String email = txtEmail.getText();
				if (checkLoiUpdate(idTH, tenTH, email)) {
					try {
						Connection con = DriverManager.getConnection(JDBC.url());
						PreparedStatement ps = con
								.prepareStatement("update ThuongHieu set TenTH=?,Email=? where MaTH=? ");
						ps.setString(1, tenTH);
						ps.setString(2, email);
						ps.setString(3, idTH);
						ps.execute();
						lblLoiEmail.setText("Cập nhật thành công.");
						load_data();
					} catch (Exception e1) {
						lblLoiEmail.setText("");
						e1.printStackTrace();
					}
				}
			}
		});
		btnCapNhat.setIcon(new ImageIcon(panelThuongHieu.class.getResource("/res/Refresh.png")));
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
				txtIDTH.setText(TaoMa.create());
				txtTenTH.setText("");
				txtEmail.setText("");
				load_data();
			}
		});
		btnMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMoi.setIcon(new ImageIcon(panelThuongHieu.class.getResource("/res/icon/new.png")));
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
		btnFirst.setIcon(new ImageIcon(panelThuongHieu.class.getResource("/res/skip_backward.png")));
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
		btnPrev.setIcon(new ImageIcon(panelThuongHieu.class.getResource("/res/rewind.png")));
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
		btnNext.setIcon(new ImageIcon(panelThuongHieu.class.getResource("/res/fast_forward.png")));
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
		btnLast.setIcon(new ImageIcon(panelThuongHieu.class.getResource("/res/skip_forward.png")));
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLast.setForeground(Color.BLACK);
		btnLast.setBounds(298, 11, 79, 35);
		panel_2_1.add(btnLast);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setColumns(10);
		txtEmail.setBounds(555, 276, 272, 30);
		add(txtEmail);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setBounds(555, 235, 150, 30);
		add(lblEmail);

		txtIDTH = new JTextField();
		txtIDTH.setEditable(false);
		txtIDTH.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtIDTH.setForeground(Color.BLACK);
		txtIDTH.setColumns(10);
		txtIDTH.setBounds(555, 72, 272, 30);
		add(txtIDTH);

		JLabel lblIDTH = new JLabel("Mã thương hiệu:");
		lblIDTH.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIDTH.setForeground(Color.BLACK);
		lblIDTH.setBounds(555, 31, 150, 30);
		add(lblIDTH);

		JLabel lblThuongHieu = new JLabel("Tên thương hiệu:");
		lblThuongHieu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblThuongHieu.setForeground(Color.BLACK);
		lblThuongHieu.setBounds(555, 133, 150, 30);
		add(lblThuongHieu);

		txtTenTH = new JTextField();
		txtTenTH.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTenTH.setForeground(Color.BLACK);
		txtTenTH.setColumns(10);
		txtTenTH.setBounds(555, 174, 272, 30);
		add(txtTenTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				current = table.getSelectedRow();
				showDetails();
			}
		});
		scrollPane.setBounds(10, 70, 525, 522);
		add(scrollPane);

		table = new JTable();
		table.setForeground(Color.BLACK);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				current = table.getSelectedRow();
				showDetails();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 th\u01B0\u01A1ng hi\u1EC7u", "Th\u01B0\u01A1ng hi\u1EC7u", "Email" }));
		scrollPane.setViewportView(table);

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
		btnTim.setIcon(new ImageIcon(panelThuongHieu.class.getResource("/res/Zoom.png")));
		btnTim.setBounds(343, 23, 71, 30);
		add(btnTim);

		lblLoiMa = new JLabel("");
		lblLoiMa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiMa.setForeground(Color.BLACK);
		lblLoiMa.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiMa.setBounds(555, 103, 272, 19);
		add(lblLoiMa);

		lblLoiTen = new JLabel("");
		lblLoiTen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiTen.setForeground(Color.BLACK);
		lblLoiTen.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiTen.setBounds(555, 203, 272, 19);
		add(lblLoiTen);

		lblLoiEmail = new JLabel("");
		lblLoiEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiEmail.setForeground(Color.BLACK);
		lblLoiEmail.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiEmail.setBounds(555, 306, 272, 19);
		add(lblLoiEmail);
		load_data();

	}

	ArrayList<ThuongHieu> arr = new ArrayList<>();

	public void load_data() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement("select * from ThuongHieu");
			ResultSet rs = ps.executeQuery();
			arr.clear();
			while (rs.next()) {
				ThuongHieu TH = new ThuongHieu();
				TH.setMaTH(rs.getString("MaTH"));
				TH.setTenTH(rs.getString("TenTH"));
				TH.setEmail(rs.getString("Email"));
				arr.add(TH);
			}
			rs.close();
			ps.close();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (ThuongHieu TH : arr) {
				Object[] row = new Object[] { TH.getMaTH(), TH.getTenTH(), TH.getEmail() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void load_data() {
//		try {
//			Connection con = DriverManager.getConnection(url, user, passWord);
//			String string = "select * from ThuongHieu";
//			PreparedStatement ps = con.prepareStatement(string);
//			ResultSet rs = ps.executeQuery();
//			tblModel.setRowCount(0);
//			Vector vector = null;
//			while (rs.next()) {
//				vector = new Vector();
//				vector.add(rs.getString("MaTH"));
//				vector.add(rs.getString("TenTH"));
//				vector.add(rs.getString("Email"));
//
//				tblModel.addRow(vector);
//			}
//			table.setModel(tblModel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void showDetails() {
		txtIDTH.setText(table.getValueAt(current, 0).toString());
		txtTenTH.setText(table.getValueAt(current, 1).toString());
		txtEmail.setText(table.getValueAt(current, 2).toString());

	}

	public void LoadDataToControl(int current) {
		txtIDTH.setText(arr.get(current).getMaTH());
		txtTenTH.setText(arr.get(current).getTenTH());
		txtEmail.setText(arr.get(current).getEmail());

	}

	public void First() {
		current = 0;
		LoadDataToControl(current);
		table.setRowSelectionInterval(current, current);
	}

	public void Prev() {
		if (current > 0) {
			current--;
			LoadDataToControl(current);
			table.setRowSelectionInterval(current, current);
		}
	}

	public void Next() {
		if (current < arr.size() - 1) {
			current++;
			LoadDataToControl(current);
			table.setRowSelectionInterval(current, current);
		}
	}

	public void Last() {
		current = arr.size() - 1;
		LoadDataToControl(current);
		table.setRowSelectionInterval(current, current);
	}

	public void TimKiem() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM ThuongHieu WHERE TenTH LIKE '%" + txtTim.getText() + "%';");
			ResultSet rs = ps.executeQuery();
			arr.clear();
			while (rs.next()) {
				ThuongHieu TH = new ThuongHieu();
				TH.setMaTH(rs.getString("MaTH"));
				TH.setTenTH(rs.getString("TenTH"));
				TH.setEmail(rs.getString("Email"));
				arr.add(TH);
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (ThuongHieu TH : arr) {
				Object[] row = new Object[] { TH.getMaTH(), TH.getTenTH(), TH.getEmail() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	boolean checkMa(String input) {
		String sql = "SELECT MaTH FROM ThuongHieu;";
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

	boolean checkLoi(String ma, String ten, String email) {
		if (!BatLoi.Ma(ma) || checkMa(ma)) {
			lblLoiMa.setText("Mã thương hiệu không hợp lệ.");
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
		if (!BatLoi.Email(email)) {
			lblLoiEmail.setText("Sai định dạng email.");
			return false;
		} else {
			lblLoiEmail.setText("");
		}
		return true;
	}

	boolean checkLoiUpdate(String ma, String ten, String email) {
		if (!BatLoi.Ma(ma) || !checkMa(ma)) {
			lblLoiMa.setText("Mã thương hiệu không hợp lệ.");
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
		if (!BatLoi.Email(email)) {
			lblLoiEmail.setText("Sai định dạng email.");
			return false;
		} else {
			lblLoiEmail.setText("");
		}
		return true;
	}
}
