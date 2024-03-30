package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import Entity.ChiTietHD;
import Utils.BatLoi;
import Utils.JDBC;
import Utils.XImage;

public class ChiTietHoaDon extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaHD;
	private JTextField txtMaSP;
	private JTextField txtSoLuong;
	private JLabel lblRecord;
	private JLabel lblLoiMa;
	private JLabel lblLoiSoLuong;
	private JLabel lblTongTien;
	private JLabel lblNotf;

	public ChiTietHoaDon(String id) {
		setIconImage(XImage.APP_ICON);
		setForeground(Color.BLACK);
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 829, 581);
		setLocation(710, 160);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBackground(SystemColor.activeCaption);
		panel.setLayout(null);
		panel.setBounds(0, 0, 813, 542);
		contentPane.add(panel);

		JLabel lblTitle = new JLabel("Chi tiết hóa đơn");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setBackground(SystemColor.activeCaption);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lblTitle.setBounds(319, 11, 188, 35);
		panel.add(lblTitle);

		JLabel lblIDHoaDon = new JLabel("Mã hóa đơn:");
		lblIDHoaDon.setBackground(SystemColor.activeCaption);
		lblIDHoaDon.setForeground(Color.BLACK);
		lblIDHoaDon.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIDHoaDon.setBounds(158, 111, 96, 30);
		panel.add(lblIDHoaDon);

		txtMaHD = new JTextField(id);
		txtMaHD.setBackground(Color.WHITE);
		txtMaHD.setEditable(false);
		txtMaHD.setForeground(Color.BLACK);
		txtMaHD.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(264, 111, 272, 30);
		panel.add(txtMaHD);

		JLabel lblIDSanPham = new JLabel("Mã sản phẩm:");
		lblIDSanPham.setBackground(SystemColor.activeCaption);
		lblIDSanPham.setForeground(Color.BLACK);
		lblIDSanPham.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIDSanPham.setBounds(158, 186, 96, 30);
		panel.add(lblIDSanPham);

		txtMaSP = new JTextField();
		txtMaSP.setBackground(Color.WHITE);
		txtMaSP.setForeground(Color.BLACK);
		txtMaSP.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMaSP.setColumns(10);
		txtMaSP.setBounds(264, 186, 272, 30);
		panel.add(txtMaSP);

		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setBackground(SystemColor.activeCaption);
		lblSoLuong.setForeground(Color.BLACK);
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSoLuong.setBounds(158, 260, 96, 30);
		panel.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		txtSoLuong.setBackground(Color.WHITE);
		txtSoLuong.setForeground(Color.BLACK);
		txtSoLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(264, 260, 272, 30);
		panel.add(txtSoLuong);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBounds(201, 474, 398, 57);
		panel.add(panel_2);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(SystemColor.activeCaption);
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Them(id);
			}
		});
		btnThem.setIcon(new ImageIcon(ChiTietHoaDon.class.getResource("/res/add.png")));
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setForeground(Color.BLACK);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBounds(75, 11, 113, 35);
		panel_2.add(btnThem);

		JButton btnMoi = new JButton("Mới");
		btnMoi.setBackground(SystemColor.activeCaption);
		btnMoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblLoiMa.setText("");
				lblLoiSoLuong.setText("");
				arr.clear();
				loadArr(id);
				TinhTien(id);
				capNhatTongTien(id);
			}
		});
		btnMoi.setIcon(new ImageIcon(ChiTietHoaDon.class.getResource("/res/icon/new.png")));
		btnMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnMoi.setForeground(Color.BLACK);
		btnMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMoi.setBounds(216, 11, 108, 35);
		panel_2.add(btnMoi);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(SystemColor.activeCaption);
		panel_2_1.setLayout(null);
		panel_2_1.setForeground(Color.BLACK);
		panel_2_1.setBounds(201, 399, 398, 57);
		panel.add(panel_2_1);

		JButton btnFirst = new JButton("");
		btnFirst.setBackground(SystemColor.activeCaption);
		btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				first();
			}
		});
		btnFirst.setIcon(new ImageIcon(ChiTietHoaDon.class.getResource("/res/skip_backward.png")));
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
		btnPrev.setIcon(new ImageIcon(ChiTietHoaDon.class.getResource("/res/rewind.png")));
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
		btnNext.setIcon(new ImageIcon(ChiTietHoaDon.class.getResource("/res/fast_forward.png")));
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
		btnLast.setIcon(new ImageIcon(ChiTietHoaDon.class.getResource("/res/skip_forward.png")));
		btnLast.setForeground(Color.BLACK);
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLast.setBounds(298, 11, 79, 35);
		panel_2_1.add(btnLast);

		lblRecord = new JLabel("");
		lblRecord.setForeground(Color.BLACK);
		lblRecord.setBackground(SystemColor.activeCaption);
		lblRecord.setBounds(553, 384, 46, 14);
		panel.add(lblRecord);

		lblLoiMa = new JLabel("");
		lblLoiMa.setBackground(SystemColor.activeCaption);
		lblLoiMa.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiMa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiMa.setForeground(Color.RED);
		lblLoiMa.setBounds(264, 216, 272, 14);
		panel.add(lblLoiMa);

		lblLoiSoLuong = new JLabel("");
		lblLoiSoLuong.setBackground(SystemColor.activeCaption);
		lblLoiSoLuong.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiSoLuong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiSoLuong.setForeground(Color.RED);
		lblLoiSoLuong.setBounds(264, 289, 272, 14);
		panel.add(lblLoiSoLuong);

		JLabel lblNewLabel = new JLabel("Tổng tiền hóa đơn:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(158, 314, 151, 30);
		panel.add(lblNewLabel);

		lblTongTien = new JLabel("");
		lblTongTien.setForeground(Color.BLACK);
		lblTongTien.setBackground(SystemColor.activeCaption);
		lblTongTien.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTongTien.setBounds(319, 314, 217, 30);
		panel.add(lblTongTien);

		lblNotf = new JLabel("");
		lblNotf.setBounds(264, 384, 272, 14);
		panel.add(lblNotf);
		lblNotf.setBackground(SystemColor.activeCaption);
		lblNotf.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotf.setForeground(Color.RED);
		lblNotf.setFont(new Font("Tahoma", Font.ITALIC, 11));
		loadArr(id);
	}

	ArrayList<ChiTietHD> arr = new ArrayList<>();

	void loadArr(String input) {
		String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHD = '" + input + "';";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ChiTietHD hd = new ChiTietHD();
				hd.setSoLuong(rs.getInt(1));
				hd.setMaSP(rs.getString(2));
				hd.setMaHD(rs.getString(3));
				arr.add(hd);
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
		if (!arr.isEmpty()) {
			txtMaHD.setText(arr.get(viTri).getMaHD());
			txtMaSP.setText(arr.get(viTri).getMaSP());
			txtSoLuong.setText(String.valueOf(arr.get(viTri).getSoLuong()));
			lblRecord.setText(viTri + 1 + "/" + arr.size());
		}
	}

	public void first() {
		if (arr.isEmpty()) {
		} else {
			index = 0;
			loadForm(index);
		}
	}

	public void last() {
		if (!arr.isEmpty()) {
			index = arr.size() - 1;
			loadForm(index);
		}
	}

	public void prev() {
		if (!arr.isEmpty()) {
			if (index == 0) {
				last();
			} else {
				index--;
			}
			loadForm(index);
		}
	}

	public void next() {
		if (!arr.isEmpty()) {
			if (index == arr.size() - 1) {
				first();
			} else {
				index++;
			}
			loadForm(index);
		}
	}

	void Them(String input) {
		boolean check = true;
		arr.clear();
		int soLuong = 0;
		String maSP = txtMaSP.getText();
		String maHD = txtMaHD.getText();
		if (!BatLoi.Ma(maSP) || !checkMaSP(maSP)) {
			check = false;
			lblLoiMa.setText("Mã sản phẩm không hợp lệ.");
		} else {
			lblLoiMa.setText("");
		}
		try {
			soLuong = Integer.parseInt(txtSoLuong.getText());
			lblLoiSoLuong.setText("");
			if (soLuong < 0) {
				check = false;
				lblLoiSoLuong.setText("Số lượng không hợp lệ.");
			}
			if (soLuong > conLai) {
				check = false;
				lblLoiSoLuong.setText("Không đủ số lượng để lên đơn.");
			}
		} catch (Exception e) {
			lblLoiSoLuong.setText("Số lượng không hợp lệ.");
			check = false;
		}
		if (check) {
			String sql = "INSERT INTO HoaDonChiTiet(SoLuong, MaSP, MaHD) VALUES('" + soLuong + "', '" + maSP + "', '"
					+ maHD + "');";
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement(sql);
				ps.executeUpdate();
				CapNhatSoLuongSP(soLuong, maSP);
				ps.close();
				con.close();
				loadArr(input);
				TinhTien(input);
				capNhatTongTien(input);
				lblNotf.setText("Thêm thành công.");
			} catch (Exception e) {
				lblNotf.setText("Thêm thất bại.");
				e.printStackTrace();
			}
		} else {
			lblNotf.setText("");
		}
	}
	
	int conLai = 0;
	
	boolean checkMaSP(String input) {
		String sql = "SELECT MaSP, SoLuong FROM SanPham;";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equalsIgnoreCase(input)) {
					conLai = rs.getInt(2);
					if (conLai <= 0) {
						lblLoiSoLuong.setText("Hết hàng.");
					}
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

	double tongTien = 0;

	void TinhTien(String input) {
		String sql = "SELECT hdct.SoLuong, sp.DonGia FROM HoaDonChiTiet AS hdct INNER JOIN SanPham AS sp ON hdct.MaSP = sp.MaSP WHERE hdct.MaHD = '"
				+ input + "';";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				double soLuong = rs.getInt(1);
				double donGia = rs.getInt(2);
				tongTien = tongTien + soLuong * donGia;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void capNhatTongTien(String input) {
		String sql = "UPDATE HoaDon SET TongTien = '" + tongTien + "' WHERE MaHD ='" + input + "';";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			lblTongTien.setText("" + tongTien / 1000 + "K vnd");
			tongTien = 0;
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void CapNhatSoLuongSP(int soLuong, String maSP) {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement("DECLARE @x INT; SET @x = '" + soLuong
					+ "'; UPDATE SanPham SET SoLuong = SoLuong - @x WHERE MaSP = '" + maSP + "';");
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
