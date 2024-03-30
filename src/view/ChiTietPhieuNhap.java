package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Entity.ChiTietPN;
import Utils.BatLoi;
import Utils.JDBC;
import Utils.XImage;

public class ChiTietPhieuNhap extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaPN;
	private JTextField txtMaSP;
	private JTextField txtSoLuong;
	private JLabel lblLoiMa;
	private JLabel lblLoiSoLuong;
	private JLabel lblRecord;

	public ChiTietPhieuNhap(String id) {
		setIconImage(XImage.APP_ICON);
		setForeground(Color.BLACK);
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 851, 574);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("CHI TIẾT PHIẾU NHẬP");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setBackground(SystemColor.activeCaption);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(281, 23, 260, 42);
		contentPane.add(lblTitle);

		JLabel lblMaPN = new JLabel("Mã Phiếu Nhập:");
		lblMaPN.setForeground(Color.BLACK);
		lblMaPN.setBackground(SystemColor.activeCaption);
		lblMaPN.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaPN.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMaPN.setBounds(237, 84, 196, 34);
		contentPane.add(lblMaPN);

		txtMaPN = new JTextField(id);
		txtMaPN.setBackground(Color.WHITE);
		txtMaPN.setEditable(false);
		txtMaPN.setForeground(Color.BLACK);
		txtMaPN.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMaPN.setColumns(10);
		txtMaPN.setBounds(237, 129, 358, 32);
		contentPane.add(txtMaPN);

		txtMaSP = new JTextField();
		txtMaSP.setForeground(Color.BLACK);
		txtMaSP.setBackground(Color.WHITE);
		txtMaSP.setColumns(10);
		txtMaSP.setBounds(237, 216, 358, 32);
		contentPane.add(txtMaSP);

		JLabel lblMaSP = new JLabel("Mã Sản Phẩm:");
		lblMaSP.setForeground(Color.BLACK);
		lblMaSP.setBackground(SystemColor.activeCaption);
		lblMaSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMaSP.setBounds(237, 172, 184, 34);
		contentPane.add(lblMaSP);

		txtSoLuong = new JTextField();
		txtSoLuong.setForeground(Color.BLACK);
		txtSoLuong.setBackground(Color.WHITE);
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(237, 302, 358, 32);
		contentPane.add(txtSoLuong);

		JLabel lblSoLuong = new JLabel("Số Lượng:");
		lblSoLuong.setForeground(Color.BLACK);
		lblSoLuong.setBackground(SystemColor.activeCaption);
		lblSoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSoLuong.setBounds(237, 259, 107, 34);
		contentPane.add(lblSoLuong);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(SystemColor.activeCaption);
		panel_2_1.setLayout(null);
		panel_2_1.setForeground(Color.BLACK);
		panel_2_1.setBounds(254, 456, 341, 57);
		contentPane.add(panel_2_1);

		JButton btnThm = new JButton("Thêm");
		btnThm.setBackground(SystemColor.activeCaption);
		btnThm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Them(id);
			}
		});
		btnThm.setIcon(new ImageIcon(ChiTietPhieuNhap.class.getResource("/res/add.png")));
		btnThm.setForeground(Color.BLACK);
		btnThm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThm.setBounds(24, 11, 112, 35);
		panel_2_1.add(btnThm);

		JButton btnMi = new JButton("Mới");
		btnMi.setBackground(SystemColor.activeCaption);
		btnMi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtMaSP.setText("");
				txtSoLuong.setText("");
				arr.clear();
				loadArr(id);
			}
		});
		btnMi.setIcon(new ImageIcon(ChiTietPhieuNhap.class.getResource("/res/icon/new.png")));
		btnMi.setForeground(Color.BLACK);
		btnMi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMi.setBounds(186, 11, 112, 35);
		panel_2_1.add(btnMi);

		lblRecord = new JLabel("");
		lblRecord.setForeground(Color.BLACK);
		lblRecord.setBackground(SystemColor.activeCaption);
		lblRecord.setBounds(679, 346, 46, 14);
		contentPane.add(lblRecord);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setForeground(Color.BLACK);
		panel_2_1_1.setBackground(SystemColor.activeCaption);
		panel_2_1_1.setBounds(229, 384, 398, 57);
		contentPane.add(panel_2_1_1);

		JButton btnFirst = new JButton("");
		btnFirst.setBackground(SystemColor.activeCaption);
		btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				first();
			}
		});
		btnFirst.setIcon(new ImageIcon(ChiTietPhieuNhap.class.getResource("/res/skip_backward.png")));
		btnFirst.setForeground(Color.BLACK);
		btnFirst.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFirst.setBounds(1, 11, 79, 35);
		panel_2_1_1.add(btnFirst);

		JButton btnPrev = new JButton("");
		btnPrev.setBackground(SystemColor.activeCaption);
		btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});
		btnPrev.setIcon(new ImageIcon(ChiTietPhieuNhap.class.getResource("/res/rewind.png")));
		btnPrev.setForeground(Color.BLACK);
		btnPrev.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrev.setBounds(100, 11, 79, 35);
		panel_2_1_1.add(btnPrev);

		JButton btnNext = new JButton("");
		btnNext.setBackground(SystemColor.activeCaption);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		btnNext.setIcon(new ImageIcon(ChiTietPhieuNhap.class.getResource("/res/fast_forward.png")));
		btnNext.setForeground(Color.BLACK);
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNext.setBounds(199, 11, 79, 35);
		panel_2_1_1.add(btnNext);

		JButton btnLast = new JButton("");
		btnLast.setBackground(SystemColor.activeCaption);
		btnLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				last();
			}
		});
		btnLast.setIcon(new ImageIcon(ChiTietPhieuNhap.class.getResource("/res/skip_forward.png")));
		btnLast.setForeground(Color.BLACK);
		btnLast.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLast.setBounds(298, 11, 79, 35);
		panel_2_1_1.add(btnLast);

		lblLoiMa = new JLabel("");
		lblLoiMa.setBackground(SystemColor.activeCaption);
		lblLoiMa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiMa.setForeground(Color.BLACK);
		lblLoiMa.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiMa.setBounds(323, 248, 272, 14);
		contentPane.add(lblLoiMa);

		lblLoiSoLuong = new JLabel("");
		lblLoiSoLuong.setBackground(SystemColor.activeCaption);
		lblLoiSoLuong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiSoLuong.setForeground(Color.BLACK);
		lblLoiSoLuong.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoiSoLuong.setBounds(323, 333, 272, 14);
		contentPane.add(lblLoiSoLuong);
		loadArr(id);
	}

	ArrayList<ChiTietPN> arr = new ArrayList<>();

	void loadArr(String input) {
		String sql = "SELECT * FROM ChiTietPhieuNhap WHERE MaPhieu = '" + input + "';";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ChiTietPN pn = new ChiTietPN();
				pn.setMaPhieuNhap(rs.getString(1));
				pn.setMaSanPham(rs.getString(2));
				pn.setSoLuong(rs.getInt(3));
				arr.add(pn);
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
			txtMaPN.setText(arr.get(viTri).getMaPhieuNhap());
			txtMaSP.setText(arr.get(viTri).getMaSanPham());
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
		String maSanPham = txtMaSP.getText();
		String maPhieuNhap = txtMaPN.getText();
		if (!BatLoi.Ma(maSanPham) || !checkMaSP(maSanPham)) {
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
		} catch (Exception e) {
			lblLoiSoLuong.setText("Số lượng không hợp lệ.");
			check = false;
		}
		if (check) {
			String sql = "INSERT INTO ChiTietPhieuNhap(MaPhieu, MaSP, SoLuong) VALUES('" + maPhieuNhap + "', '"
					+ maSanPham + "', '" + soLuong + "');";
			try {
				Connection con = DriverManager.getConnection(JDBC.url());
				PreparedStatement ps = con.prepareStatement(sql);
				int kq = ps.executeUpdate();
				if (kq == 1) {
					JOptionPane.showMessageDialog(this, "Thêm thành công");
				}
				CapNhatSoLuongSP(soLuong, maSanPham);
				ps.close();
				con.close();
				loadArr(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	boolean checkMaSP(String input) {
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

	void CapNhatSoLuongSP(int soLuong, String maSP) {
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement("DECLARE @x INT; SET @x = " + soLuong
					+ "; UPDATE SanPham SET SoLuong = SoLuong + @x WHERE MaSP = '" + maSP + "';");
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
