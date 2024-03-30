package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import Utils.JDBC;
import Utils.XImage;

public class Main extends JFrame {

	private JPanel contentPane;
	private panelNhanVien qlNhanVien;
	private panelSanPham qlSanPham;
	private panelKhachHang qlKhachHang;
	private panelPhieuNhap qlPhieuNhap;
	private panelHoaDon qlHoaDon;
	private panelLoaiSanPham qlLoaiSanPham;
	private panelNhaCungCap qlNhaCungCap;
	private panelThuongHieu qlThuongHieu;
	private panelKhuyenMai qlKhuyenMai;
	private panelThongKe qlThongKe;
	private DoiMatKhau doiMatKhau;

	public Main(String id) {
		setIconImage(XImage.APP_ICON);
		setBackground(new Color(119, 136, 153));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 806);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		qlNhanVien = new panelNhanVien();
		qlSanPham = new panelSanPham();
		qlKhachHang = new panelKhachHang();
		qlPhieuNhap = new panelPhieuNhap();
		qlHoaDon = new panelHoaDon(id);
		qlLoaiSanPham = new panelLoaiSanPham();
		qlNhaCungCap = new panelNhaCungCap();
		qlThuongHieu = new panelThuongHieu();
		qlKhuyenMai = new panelKhuyenMai();
		qlThongKe = new panelThongKe();
		doiMatKhau = new DoiMatKhau(id);

		JPanel paneMenu = new JPanel();
		paneMenu.setBounds(0, 0, 343, 806);
		paneMenu.setBackground(new Color(47, 79, 79));
		contentPane.add(paneMenu);
		paneMenu.setLayout(null);

		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setIcon(new ImageIcon(Main.class.getResource("/res/Logo4.png")));
		lblIconLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogo.setBounds(10, 11, 323, 193);
		paneMenu.add(lblIconLogo);

		JPanel paneNhanVien = new JPanel();
		paneNhanVien.addMouseListener(new PanelButtonMouseAdapter(paneNhanVien) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (TrangThai(id)) {
					if (VaiTro(id)) {
						menuClicked(qlNhanVien);
					} else
						JOptionPane.showMessageDialog(null, "Bạn không phải quản lý, không được phép thao tác.");
				} else
					JOptionPane.showMessageDialog(null, "Bạn đã hết làm.");
			}
		});
		paneNhanVien.setBackground(new Color(47, 79, 79));
		paneNhanVien.setBounds(0, 406, 343, 50);
		paneMenu.add(paneNhanVien);
		paneNhanVien.setLayout(null);

		JLabel lblNhanVien = new JLabel("NHÂN VIÊN");
		lblNhanVien.setForeground(new Color(255, 255, 255));
		lblNhanVien.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNhanVien.setBounds(115, 11, 218, 28);
		paneNhanVien.add(lblNhanVien);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/res/Users.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(55, 0, 50, 50);
		paneNhanVien.add(lblNewLabel);

		JPanel panelKhachHang = new JPanel();
		panelKhachHang.addMouseListener(new PanelButtonMouseAdapter(panelKhachHang) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (TrangThai(id)) {
					menuClicked(qlKhachHang);
				} else
					JOptionPane.showMessageDialog(null, "Bạn đã hết làm.");
			}
		});
		panelKhachHang.setBackground(new Color(47, 79, 79));
		panelKhachHang.setBounds(0, 256, 343, 50);
		paneMenu.add(panelKhachHang);
		panelKhachHang.setLayout(null);

		JLabel lblKhachHang = new JLabel("KHÁCH HÀNG");
		lblKhachHang.setForeground(Color.WHITE);
		lblKhachHang.setFont(new Font("Dialog", Font.BOLD, 16));
		lblKhachHang.setBounds(115, 11, 218, 28);
		panelKhachHang.add(lblKhachHang);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/res/User group.png")));
		lblNewLabel_1.setBounds(55, 0, 50, 50);
		panelKhachHang.add(lblNewLabel_1);

		JPanel panelPhieuNhap = new JPanel();
		panelPhieuNhap.addMouseListener(new PanelButtonMouseAdapter(panelPhieuNhap) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (TrangThai(id)) {
					menuClicked(qlPhieuNhap);
				} else
					JOptionPane.showMessageDialog(null, "Bạn đã hết làm.");
			}
		});
		panelPhieuNhap.setBackground(new Color(47, 79, 79));
		panelPhieuNhap.setBounds(0, 306, 343, 50);
		paneMenu.add(panelPhieuNhap);
		panelPhieuNhap.setLayout(null);

		JLabel lblPhieuNhap = new JLabel("PHIẾU NHẬP");
		lblPhieuNhap.setForeground(Color.WHITE);
		lblPhieuNhap.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPhieuNhap.setBounds(115, 11, 218, 28);
		panelPhieuNhap.add(lblPhieuNhap);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Main.class.getResource("/res/Numbered list.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(55, 0, 50, 50);
		panelPhieuNhap.add(lblNewLabel_2);

		JPanel panelSanPham = new JPanel();
		panelSanPham.addMouseListener(new PanelButtonMouseAdapter(panelSanPham) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (TrangThai(id)) {
					menuClicked(qlSanPham);
				} else
					JOptionPane.showMessageDialog(null, "Bạn đã hết làm.");
			}
		});
		panelSanPham.setBackground(new Color(47, 79, 79));
		panelSanPham.setBounds(0, 356, 343, 50);
		paneMenu.add(panelSanPham);
		panelSanPham.setLayout(null);

		JLabel lblSanPham = new JLabel("SẢN PHẨM");
		lblSanPham.setForeground(Color.WHITE);
		lblSanPham.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSanPham.setBounds(115, 11, 218, 28);
		panelSanPham.add(lblSanPham);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Main.class.getResource("/res/computer.png")));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(55, 0, 50, 50);
		panelSanPham.add(lblNewLabel_3);

		JPanel panelNhaCungCap = new JPanel();
		panelNhaCungCap.addMouseListener(new PanelButtonMouseAdapter(panelNhaCungCap) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (TrangThai(id)) {
					if (VaiTro(id)) {
						menuClicked(qlNhaCungCap);
					} else
						JOptionPane.showMessageDialog(null, "Bạn không phải quản lý, không được phép thao tác.");
				} else
					JOptionPane.showMessageDialog(null, "Bạn đã hết làm.");
			}
		});
		panelNhaCungCap.setBackground(new Color(47, 79, 79));
		panelNhaCungCap.setBounds(0, 556, 343, 50);
		paneMenu.add(panelNhaCungCap);
		panelNhaCungCap.setLayout(null);

		JLabel lblNhaCungCap = new JLabel("NHÀ CUNG CẤP");
		lblNhaCungCap.setForeground(Color.WHITE);
		lblNhaCungCap.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNhaCungCap.setBounds(115, 11, 218, 28);
		panelNhaCungCap.add(lblNhaCungCap);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(Main.class.getResource("/res/business_user.png")));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(55, 0, 50, 50);
		panelNhaCungCap.add(lblNewLabel_7);

		JPanel paneThuongHieu = new JPanel();
		paneThuongHieu.addMouseListener(new PanelButtonMouseAdapter(paneThuongHieu) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (TrangThai(id)) {
					if (VaiTro(id)) {
						menuClicked(qlThuongHieu);
					} else
						JOptionPane.showMessageDialog(null, "Bạn không phải quản lý, không được phép thao tác.");
				} else
					JOptionPane.showMessageDialog(null, "Bạn đã hết làm.");
			}
		});
		paneThuongHieu.setBackground(new Color(47, 79, 79));
		paneThuongHieu.setBounds(0, 506, 343, 50);
		paneMenu.add(paneThuongHieu);
		paneThuongHieu.setLayout(null);

		JLabel lblThuongHieu = new JLabel("THƯƠNG HIỆU");
		lblThuongHieu.setForeground(Color.WHITE);
		lblThuongHieu.setFont(new Font("Dialog", Font.BOLD, 16));
		lblThuongHieu.setBounds(115, 11, 218, 28);
		paneThuongHieu.add(lblThuongHieu);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(Main.class.getResource("/res/Monitor.png")));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(55, 0, 50, 50);
		paneThuongHieu.add(lblNewLabel_6);

		JPanel panelLoaiSanPham = new JPanel();
		panelLoaiSanPham.addMouseListener(new PanelButtonMouseAdapter(panelLoaiSanPham) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (TrangThai(id)) {
					if (VaiTro(id)) {
						menuClicked(qlLoaiSanPham);
					} else
						JOptionPane.showMessageDialog(null, "Bạn không phải quản lý, không được phép thao tác.");
				} else
					JOptionPane.showMessageDialog(null, "Bạn đã hết làm.");
			}
		});
		panelLoaiSanPham.setBackground(new Color(47, 79, 79));
		panelLoaiSanPham.setBounds(0, 456, 343, 50);
		paneMenu.add(panelLoaiSanPham);
		panelLoaiSanPham.setLayout(null);

		JLabel lblLoaiSanPham = new JLabel("LOẠI SẢN PHẨM");
		lblLoaiSanPham.setForeground(Color.WHITE);
		lblLoaiSanPham.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLoaiSanPham.setBounds(115, 11, 218, 28);
		panelLoaiSanPham.add(lblLoaiSanPham);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(Main.class.getResource("/res/computers.png")));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(55, 0, 50, 50);
		panelLoaiSanPham.add(lblNewLabel_5);

		JPanel panelHoaDon = new JPanel();
		panelHoaDon.addMouseListener(new PanelButtonMouseAdapter(panelHoaDon) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (TrangThai(id)) {
					menuClicked(qlHoaDon);
				} else
					JOptionPane.showMessageDialog(null, "Bạn đã hết làm.");
			}
		});
		panelHoaDon.setBackground(new Color(47, 79, 79));
		panelHoaDon.setBounds(0, 206, 343, 50);
		paneMenu.add(panelHoaDon);
		panelHoaDon.setLayout(null);

		JLabel lblHoaDon = new JLabel("HÓA ĐƠN");
		lblHoaDon.setForeground(Color.WHITE);
		lblHoaDon.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHoaDon.setBounds(115, 11, 218, 28);
		panelHoaDon.add(lblHoaDon);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Main.class.getResource("/res/Text.png")));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(55, 0, 50, 50);
		panelHoaDon.add(lblNewLabel_4);

		JPanel panelKhuyenMai = new JPanel();
		panelKhuyenMai.addMouseListener(new PanelButtonMouseAdapter(panelKhuyenMai) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (TrangThai(id)) {
					if (VaiTro(id)) {
						menuClicked(qlKhuyenMai);
					} else
						JOptionPane.showMessageDialog(null, "Bạn không phải quản lý, không được phép thao tác.");
				} else
					JOptionPane.showMessageDialog(null, "Bạn đã hết làm.");
			}
		});
		panelKhuyenMai.setLayout(null);
		panelKhuyenMai.setBackground(new Color(47, 79, 79));
		panelKhuyenMai.setBounds(0, 606, 343, 50);
		paneMenu.add(panelKhuyenMai);

		JLabel lblKhuyenMai = new JLabel("KHUYẾN MÃI");
		lblKhuyenMai.setForeground(Color.WHITE);
		lblKhuyenMai.setFont(new Font("Dialog", Font.BOLD, 16));
		lblKhuyenMai.setBounds(115, 11, 218, 28);
		panelKhuyenMai.add(lblKhuyenMai);

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(Main.class.getResource("/res/shopping_cart.png")));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(55, 0, 50, 50);
		panelKhuyenMai.add(lblNewLabel_8);

		JPanel panelThongKe = new JPanel();
		panelThongKe.addMouseListener(new PanelButtonMouseAdapter(panelThongKe) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (TrangThai(id)) {
					if (VaiTro(id)) {
						menuClicked(qlThongKe);
					} else
						JOptionPane.showMessageDialog(null, "Bạn không phải quản lý, không được phép thao tác.");
				} else
					JOptionPane.showMessageDialog(null, "Bạn đã hết làm.");
			}
		});
		panelThongKe.setLayout(null);
		panelThongKe.setBackground(new Color(47, 79, 79));
		panelThongKe.setBounds(0, 656, 343, 50);
		paneMenu.add(panelThongKe);

		JLabel lblThongke = new JLabel("THỐNG KÊ");
		lblThongke.setForeground(Color.WHITE);
		lblThongke.setFont(new Font("Dialog", Font.BOLD, 16));
		lblThongke.setBounds(115, 11, 218, 28);
		panelThongKe.add(lblThongke);

		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(Main.class.getResource("/res/Bar chart.png")));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(55, 0, 50, 50);
		panelThongKe.add(lblNewLabel_9);

		JPanel panelDoiMatKhau = new JPanel();
		panelDoiMatKhau.addMouseListener(new PanelButtonMouseAdapter(panelDoiMatKhau) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(doiMatKhau);
			}
		});
		panelDoiMatKhau.setLayout(null);
		panelDoiMatKhau.setBackground(new Color(47, 79, 79));
		panelDoiMatKhau.setBounds(0, 706, 343, 50);
		paneMenu.add(panelDoiMatKhau);

		JLabel lblDoiMatKhau = new JLabel("ĐỔI MẬT KHẨU");
		lblDoiMatKhau.setForeground(Color.WHITE);
		lblDoiMatKhau.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDoiMatKhau.setBounds(115, 11, 218, 28);
		panelDoiMatKhau.add(lblDoiMatKhau);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(Main.class.getResource("/res/Key.png")));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(55, 0, 50, 50);
		panelDoiMatKhau.add(lblNewLabel_10);

		JPanel panelDangXuat = new JPanel();
		panelDangXuat.addMouseListener(new PanelButtonMouseAdapter(panelDangXuat) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất?", "Xác nhận",
						JOptionPane.YES_NO_OPTION) == 0) {
					Main.this.dispose();
					new DangNhap().setVisible(true);
				}
			}
		});
		panelDangXuat.setLayout(null);
		panelDangXuat.setBackground(new Color(47, 79, 79));
		panelDangXuat.setBounds(0, 756, 343, 50);
		paneMenu.add(panelDangXuat);

		JLabel lblDangXuat = new JLabel("ĐĂNG XUẤT");
		lblDangXuat.setForeground(Color.WHITE);
		lblDangXuat.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDangXuat.setBounds(115, 11, 218, 28);
		panelDangXuat.add(lblDangXuat);

		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon(Main.class.getResource("/res/shut_down.png")));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(55, 0, 50, 50);
		panelDangXuat.add(lblNewLabel_11);

		JLabel lblExit = new JLabel("X");
		lblExit.setBounds(1170, 0, 30, 30);
		lblExit.setBackground(new Color(0, 0, 0));
		lblExit.setForeground(new Color(255, 255, 255));
		lblExit.setFont(new Font("Montserrat", Font.PLAIN, 20));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát?", "Xác nhận",
						JOptionPane.YES_NO_OPTION) == 0) {
					Main.this.dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblExit.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblExit.setForeground(Color.white);
			}
		});
		contentPane.add(lblExit);

		JPanel panelContent = new JPanel();
		panelContent.setBackground(new Color(119, 136, 153));
		panelContent.setBounds(353, 41, 837, 754);
		panelContent.setLayout(null);
		panelContent.add(qlNhanVien);
		panelContent.add(qlSanPham);
		panelContent.add(qlKhachHang);
		panelContent.add(qlPhieuNhap);
		panelContent.add(qlHoaDon);
		panelContent.add(qlLoaiSanPham);
		panelContent.add(qlNhaCungCap);
		panelContent.add(qlThuongHieu);
		panelContent.add(qlKhuyenMai);
		panelContent.add(qlThongKe);
		panelContent.add(doiMatKhau);
		contentPane.add(panelContent);
		lblIconLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelContent);
			}
		});
		menuClicked(panelContent);
	}

	boolean TrangThai(String input) {
		String sql = "SELECT TrangThai FROM NhanVien WHERE MaNV = '" + input + "';";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean(1)) {
					return false;
				} else {
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

	boolean VaiTro(String input) {
		String sql = "SELECT VaiTro FROM NhanVien WHERE MaNV = '" + input + "';";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean(1)) {
					return false;
				} else {
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

	public void menuClicked(JPanel panel) {
		qlNhanVien.setVisible(false);
		qlSanPham.setVisible(false);
		qlKhachHang.setVisible(false);
		qlPhieuNhap.setVisible(false);
		qlHoaDon.setVisible(false);
		qlLoaiSanPham.setVisible(false);
		qlNhaCungCap.setVisible(false);
		qlThuongHieu.setVisible(false);
		qlKhuyenMai.setVisible(false);
		qlThongKe.setVisible(false);
		doiMatKhau.setVisible(false);
		panel.setVisible(true);
	}

	private class PanelButtonMouseAdapter extends MouseAdapter {
		JPanel panel;

		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(112, 128, 144));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(47, 79, 79));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(60, 79, 133));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(112, 128, 144));
		}
	}
}
