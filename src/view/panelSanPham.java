package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Entity.LoaiSP;
import Entity.SanPham;
import Utils.BatLoi;
import Utils.JDBC;
import Utils.TaoMa;

public class panelSanPham extends JPanel {
	private JTextField txtID;
	private JTextField txtName;
	private JTable tblsp;
	private JTextField txtPrice;
	private JTextField txtCount;
	private JTextField txtTim;
	private JComboBox cboLoai;
	private JComboBox cboHieu;
	private JTextArea txtDescribe;
	private JLabel lblImg;
	private JLabel lblLoai;
	private JLabel lblHieu;
	private JLabel lblName;
	private JComponent lblDescribe;
	private JLabel lblID;
	private JLabel lblPrice;
	private JFileChooser fileChooser;

	public panelSanPham() {
		setForeground(Color.BLACK);

		setBackground(new Color(176, 196, 222));
		setBounds(0, 0, 837, 754);
		setLayout(null);
		setVisible(true);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(176, 196, 222));
		panel_2.setForeground(Color.BLACK);
		panel_2.setLayout(null);
		panel_2.setBounds(30, 676, 398, 57);
		add(panel_2);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(176, 196, 222));
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				them();
			}
		});
		btnThem.setIcon(new ImageIcon(panelSanPham.class.getResource("/res/add.png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setForeground(Color.BLACK);
		btnThem.setBounds(1, 11, 122, 35);
		panel_2.add(btnThem);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(176, 196, 222));
		btnCapNhat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CapNhat();
			}
		});
		btnCapNhat.setIcon(new ImageIcon(panelSanPham.class.getResource("/res/Refresh.png")));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCapNhat.setForeground(Color.BLACK);
		btnCapNhat.setBounds(122, 11, 145, 35);
		panel_2.add(btnCapNhat);

		JButton btnMoi = new JButton("Mới");
		btnMoi.setBackground(new Color(176, 196, 222));
		btnMoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtID.setText(TaoMa.create());
				txtName.setText("");
				txtPrice.setText("");
				txtCount.setText("");
				txtDescribe.setText("");
				cboLoai.setSelectedIndex(0);
				cboHieu.setSelectedIndex(0);
				lblImg.setIcon(null);
				load_data();
			}
		});
		btnMoi.setIcon(new ImageIcon(panelSanPham.class.getResource("/res/icon/new.png")));
		btnMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMoi.setForeground(Color.BLACK);
		btnMoi.setBounds(265, 11, 108, 35);
		panel_2.add(btnMoi);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(176, 196, 222));
		panel_2_1.setForeground(Color.BLACK);
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(30, 608, 398, 57);
		add(panel_2_1);

		JButton btnFirst = new JButton("");
		btnFirst.setBackground(new Color(176, 196, 222));
		btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				First();
			}
		});
		btnFirst.setIcon(new ImageIcon(panelSanPham.class.getResource("/res/skip_backward.png")));
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
		btnPrev.setIcon(new ImageIcon(panelSanPham.class.getResource("/res/rewind.png")));
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
		btnNext.setIcon(new ImageIcon(panelSanPham.class.getResource("/res/fast_forward.png")));
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
		btnLast.setIcon(new ImageIcon(panelSanPham.class.getResource("/res/skip_forward.png")));
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLast.setForeground(Color.BLACK);
		btnLast.setBounds(298, 11, 79, 35);
		panel_2_1.add(btnLast);

		JLabel lblCount = new JLabel("Số lượng:");
		lblCount.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCount.setForeground(Color.BLACK);
		lblCount.setBounds(458, 133, 72, 30);
		add(lblCount);

		lblDescribe = new JLabel("Mô tả:");
		lblDescribe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescribe.setForeground(Color.BLACK);
		lblDescribe.setBounds(458, 173, 52, 30);
		add(lblDescribe);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtID.setForeground(Color.BLACK);
		txtID.setColumns(10);
		txtID.setBounds(556, 10, 271, 30);
		add(txtID);

		lblID = new JLabel("ID Sản phẩm:");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblID.setForeground(Color.BLACK);
		lblID.setBounds(458, 10, 100, 30);
		add(lblID);

		lblName = new JLabel("Tên SP:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setForeground(Color.BLACK);
		lblName.setBounds(458, 51, 93, 30);
		add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtName.setForeground(Color.BLACK);
		txtName.setColumns(10);
		txtName.setBounds(556, 51, 271, 30);
		add(txtName);

		lblPrice = new JLabel("Đơn giá:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrice.setForeground(Color.BLACK);
		lblPrice.setBounds(458, 92, 64, 30);
		add(lblPrice);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 438, 526);
		add(scrollPane);

		tblsp = new JTable();
		tblsp.setForeground(Color.BLACK);
		tblsp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoadDataToControl(tblsp.getSelectedRow());
			}
		});
		tblsp.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 s\u1EA3n ph\u1EA9m",
				"T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng" }));
		load_data();

		scrollPane.setViewportView(tblsp);

		txtPrice = new JTextField();
		txtPrice.setForeground(Color.BLACK);
		txtPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(556, 92, 271, 30);
		add(txtPrice);

		txtCount = new JTextField();
		txtCount.setEditable(false);
		txtCount.setForeground(Color.BLACK);
		txtCount.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCount.setColumns(10);
		txtCount.setBounds(556, 134, 271, 30);
		add(txtCount);

		lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(panelSanPham.class.getResource("/res/Logo3.png")));
		lblImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectImage();
			}
		});
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setForeground(Color.BLACK);
		lblImg.setBackground(new Color(173, 216, 230));
		lblImg.setBounds(458, 345, 369, 251);
		lblImg.setOpaque(true);
		add(lblImg);

		lblLoai = new JLabel("Loại sản phẩm:");
		lblLoai.setForeground(Color.BLACK);
		lblLoai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoai.setBounds(456, 608, 109, 30);
		add(lblLoai);

		lblHieu = new JLabel("Thương hiệu:");
		lblHieu.setForeground(Color.BLACK);
		lblHieu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHieu.setBounds(456, 662, 109, 30);
		add(lblHieu);

		cboHieu = new JComboBox();
		cboHieu.setMaximumRowCount(16);
		cboHieu.setForeground(Color.BLACK);
		cboHieu.setBackground(new Color(255, 255, 255));
		cboHieu.setBounds(456, 690, 371, 30);
		cboHieu.getEditor().getEditorComponent().setBackground(Color.BLACK);
		((JTextField) cboHieu.getEditor().getEditorComponent()).setOpaque(true);
		add(cboHieu);
		ComboBoxTH();

		txtTim = new JTextField();
		txtTim.setForeground(Color.BLACK);
		txtTim.setBackground(new Color(255, 255, 255));
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
		btnTim.setIcon(new ImageIcon(panelSanPham.class.getResource("/res/Zoom.png")));
		btnTim.setBounds(351, 23, 57, 30);
		add(btnTim);

		cboLoai = new JComboBox();
		cboLoai.setForeground(Color.BLACK);
		cboLoai.setMaximumRowCount(20);
		cboLoai.setBounds(456, 637, 371, 28);
		add(cboLoai);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(458, 203, 369, 131);
		add(scrollPane_1);

		txtDescribe = new JTextArea();
		txtDescribe.setForeground(Color.BLACK);
		scrollPane_1.setViewportView(txtDescribe);
		ComboBoxLoai();
		fileChooser = new JFileChooser("/Users/non/eclipse-workspace/DashboardMenu/hinh");
	}

	ArrayList<SanPham> list = new ArrayList<>();
	int current = 0;
	int vitri = 1;

	public void load_data() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			String sql = "select MaSP, TenSP, DonGia, LoaiSP.MaLoai, ThuongHieu.MaTH, SoLuong ,Mota, HinhSP, LoaiSP.TenLoai, ThuongHieu.TenTH from SanPham inner join LoaiSP on LoaiSP.MaLoai = SanPham.MaLoai inner join ThuongHieu on ThuongHieu.MaTH = SanPham.MaTH;";
			ResultSet rs = st.executeQuery(sql);
			list.clear();
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaSP(rs.getString("MaSP"));
				sp.setTenSP(rs.getString("TenSP"));
				sp.setDonGia(rs.getInt("DonGia"));
				sp.setSoLuong(rs.getInt("SoLuong"));
				sp.setMaLoai(rs.getString("MaLoai"));
				sp.setMaTH(rs.getString("MaTH"));
				sp.setMoTa(rs.getString("Mota"));
				sp.setHinhSP(rs.getString("HinhSP"));
				sp.setTenLoaiSP(rs.getString("TenLoai"));
				sp.setTenTH(rs.getString("TenTH"));
				list.add(sp);
			}
			DefaultTableModel model = (DefaultTableModel) tblsp.getModel();
			model.setRowCount(0);
			for (SanPham sp : list) {
				Object[] row = new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getSoLuong() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void them() {
		String ma = TaoMa.create();
		String ten = txtName.getText();
		String gia = txtPrice.getText();
		if (checkLoi(ma, ten, gia)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement pt = con.prepareStatement(
						"insert into SanPham(MaSP,TenSP,DonGia,MaLoai,MaTH,Mota,HinhSP,SoLuong) Values (?,?,?,?,?,?,?,0);");
				pt.setString(1, ma);
				pt.setString(2, ten);
				pt.setInt(3, Integer.parseInt(gia));
				pt.setString(4, ListL.get(cboLoai.getSelectedIndex()).getMaLoai());
				pt.setString(5, ListTH.get(cboHieu.getSelectedIndex()).getMaTH());
				pt.setString(6, txtDescribe.getText());
				pt.setString(7, lblImg.getToolTipText());
				pt.execute();
				pt.close();
				con.close();
				this.load_data();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void CapNhat() {
		String ma = txtID.getText();
		String ten = txtName.getText();
		String gia = txtPrice.getText();
		if (checkLoiUpdate(ma, ten, gia)) {
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement(
						"UPDATE SanPham SET TenSP = ?, DonGia = ?, MaLoai = ?, MaTH = ?, Mota = ?, HinhSP = ? where MaSP = ?");
				ps.setString(1, ten);
				ps.setInt(2, Integer.parseInt(gia));
				ps.setString(3, ListL.get(cboLoai.getSelectedIndex()).getMaLoai());
				ps.setString(4, ListTH.get(cboHieu.getSelectedIndex()).getMaTH());
				ps.setString(5, txtDescribe.getText());
				ps.setString(6, lblImg.getToolTipText());
				ps.setString(7, ma);
				int kq = ps.executeUpdate();
				if (kq == 1) {
					JOptionPane.showMessageDialog(this, "Cập Nhật Thành Công.");
				} else {
					JOptionPane.showMessageDialog(this, "Cập Nhật Thất Bại!");
				}
				ps.close();
				con.close();
				this.load_data();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void TimKiem() {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement(
					"select masp, tensp, soluong, tenloai, tenth from SanPham as sp join loaisp as loai on sp.maloai = loai.maloai join ThuongHieu as th on sp.MaTH = th.MaTH WHERE tensp like ?");
			ps.setString(1, "%" + txtTim.getText() + "%");
			ResultSet rs = ps.executeQuery();
			list.clear();
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaSP(rs.getString("MaSp"));
				sp.setTenSP(rs.getString("TenSP"));
				sp.setSoLuong(rs.getInt("SoLuong"));
				sp.setTenLoaiSP(rs.getString("TenLoai"));
				sp.setTenTH(rs.getString("TenTH"));
				list.add(sp);
			}
			DefaultTableModel model = (DefaultTableModel) tblsp.getModel();
			model.setRowCount(0);
			for (SanPham sp : list) {
				Object[] row = new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getSoLuong() };
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void LoadDataToControl(int vitri) {
		txtID.setText(list.get(vitri).getMaSP());
		txtName.setText(list.get(vitri).getTenSP());
		txtPrice.setText(Integer.toString(list.get(vitri).getDonGia()));
		txtCount.setText(Integer.toString(list.get(vitri).getSoLuong()));
		cboLoai.setSelectedItem(list.get(vitri).getTenLoaiSP());
		cboHieu.setSelectedItem(list.get(vitri).getTenTH());
		txtDescribe.setText(list.get(vitri).getMoTa());
		if (list.get(vitri).getHinhSP() != null) {
			lblImg.setIcon(scale(list.get(vitri).getHinhSP()));
		} else {
			lblImg.setIcon(null);
		}
	}

	public void First() {
		vitri = 0;
		LoadDataToControl(vitri);
		tblsp.setRowSelectionInterval(vitri, vitri);
	}

	public void Prev() {
		vitri = tblsp.getSelectedRow();
		if (vitri > 0) {
			vitri--;
			LoadDataToControl(vitri);
			tblsp.setRowSelectionInterval(vitri, vitri);
		}
	}

	public void Next() {
		vitri = tblsp.getSelectedRow();
		if (vitri < list.size() - 1) {
			vitri++;
			LoadDataToControl(vitri);
			tblsp.setRowSelectionInterval(vitri, vitri);
		}
	}

	public void Last() {
		vitri = list.size() - 1;
		LoadDataToControl(vitri);
		tblsp.setRowSelectionInterval(vitri, vitri);
	}

	ArrayList<LoaiSP> ListL = new ArrayList<>();

	public void ComboBoxLoai() {
		DefaultComboBoxModel cbbLoai = (DefaultComboBoxModel) cboLoai.getModel();
		cbbLoai.removeAllElements();
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			String url = "Select * from LoaiSP";
			ResultSet rs = st.executeQuery(url);
			while (rs.next()) {
				String TenLoai = rs.getString("TenLoai");
				String MaLoai = rs.getString("Maloai");
				LoaiSP lsp = new LoaiSP();
				lsp.setTenLoai(TenLoai);
				lsp.setMaLoai(MaLoai);
				ListL.add(lsp);
			}
			for (LoaiSP loaiSP : ListL) {
				cbbLoai.addElement(loaiSP.getTenLoai());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	ArrayList<SanPham> ListTH = new ArrayList<>();

	public void ComboBoxTH() {
		DefaultComboBoxModel cbbTH = (DefaultComboBoxModel) cboHieu.getModel();
		cbbTH.removeAllElements();
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			String url = "Select MaTH, TenTH from ThuongHieu ";
			ResultSet rs = st.executeQuery(url);
			while (rs.next()) {
				String TenTH = rs.getString("TenTH");
				String MaTH = rs.getString("MaTH");
				SanPham ThuongHieu = new SanPham();
				ThuongHieu.setTenTH(TenTH);
				ThuongHieu.setMaTH(MaTH);
				ListTH.add(ThuongHieu);
			}
			for (SanPham sanPham : ListTH) {
				cbbTH.addElement(sanPham.getTenTH());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void selectImage() {
		try {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				if (saveLogo(file)) {
					lblImg.setIcon(scale(file.getName()));
					lblImg.setToolTipText(file.getName());
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sai ảnh.");
		}
	}

	public static boolean saveLogo(File file) {
		File dir = new File("hinh");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			File newFile = new File(dir, file.getName());
			Path source = Paths.get(file.getAbsolutePath());
			Path destination = Paths.get(newFile.getAbsolutePath());
			Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static File readLogo(String fileName) {
		File path = new File("hinh", fileName);
		return path;
	}

	public ImageIcon scale(String file) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(readLogo(file));
		} catch (IOException e) {
			return null;
		}
		Image dimg = img.getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		return imageIcon;
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

	boolean checkLoi(String ma, String ten, String gia) {
		if (!BatLoi.Ma(ma) || checkMa(ma)) {
			lblID.setForeground(new Color(255, 0, 0));
			return false;
		} else {
			lblID.setForeground(new Color(0, 0, 0));
		}
		if (0 >= ten.length() || ten.length() > 50) {
			lblName.setForeground(new Color(255, 0, 0));
			return false;
		} else {
			lblName.setForeground(new Color(0, 0, 0));
		}
		try {
			int price = Integer.valueOf(gia);
			lblPrice.setForeground(new Color(0, 0, 0));
			if (price < 1000) {
				lblPrice.setForeground(new Color(255, 0, 0));
				return false;
			}
		} catch (Exception e) {

		}
		if (gia.equals("")) {
			lblPrice.setForeground(new Color(255, 0, 0));
			return false;
		}
		return true;
	}

	boolean checkLoiUpdate(String ma, String ten, String gia) {
		if (!BatLoi.Ma(ma) || !checkMa(ma)) {
			lblID.setForeground(new Color(255, 0, 0));
			return false;
		} else {
			lblID.setForeground(new Color(0, 0, 0));
		}
		if (0 >= ten.length() || ten.length() > 50) {
			lblName.setForeground(new Color(255, 0, 0));
			return false;
		} else {
			lblName.setForeground(new Color(0, 0, 0));
		}
		try {
			int price = Integer.valueOf(gia);
			lblPrice.setForeground(new Color(0, 0, 0));
			if (price < 1000) {
				lblPrice.setForeground(new Color(255, 0, 0));
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		if (gia.equals("")) {
			lblPrice.setForeground(new Color(255, 0, 0));
			return false;
		}
		return true;
	}
}