package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Utils.DoanhThu;
import Utils.JDBC;

public class panelThongKe extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JLabel lbl;

	public panelThongKe() {
		setBackground(new Color(119, 136, 153));
		setBounds(0, 0, 837, 754);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 817, 732);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("THỐNG KÊ BÁN HÀNG");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblNewLabel.setBounds(316, 11, 178, 25);
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 132, 758, 355);
		panel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Th\u00E1ng", "L\u01B0\u1EE3ng kh\u00E1ch", "Doanh thu" }));
		scrollPane.setViewportView(table);

		lbl = new JLabel("Tổng doanh thu:");
		lbl.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lbl.setBounds(33, 513, 461, 30);
		panel.add(lbl);

		JLabel lblTong = new JLabel("");
		lblTong.setHorizontalAlignment(SwingConstants.CENTER);
		lblTong.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblTong.setBounds(167, 513, 133, 30);
		panel.add(lblTong);

		JButton btnIn = new JButton("In");
		btnIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				XuatFile();
			}
		});
		btnIn.setBounds(466, 87, 89, 23);
		panel.add(btnIn);
		setVisible(true);
		loadData();
	}

	ArrayList<DoanhThu> listDoanhThu = new ArrayList<>();

	void loadData() {
		int tongDoanhThu = 0;
		String sql = "exec sp_DoanhThuThang";
		try {
			Connection con = DriverManager.getConnection(JDBC.url());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				DoanhThu dt = new DoanhThu();
				dt.setThang(rs.getInt(1));
				dt.setLuongKhach(rs.getInt(2));
				dt.setDoanhThu(rs.getInt(3));
				tongDoanhThu += rs.getInt(3);
				listDoanhThu.add(dt);
			}
			rs.close();
			st.close();
			con.close();
			loadTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
		lbl.setText("Tổng doanh thu: " + tongDoanhThu / 1000 + "K VND");
	}

	void loadTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (DoanhThu dt : listDoanhThu) {
			Object[] row = new Object[] { dt.getThang(), dt.getLuongKhach(), dt.getDoanhThu() };
			model.addRow(row);
		}
	}

	public void XuatFile() {
		try (XSSFWorkbook wordbook = new XSSFWorkbook()) {
			XSSFSheet sheet = wordbook.createSheet("PhieuNhap");
			XSSFRow row = null;
			Cell cell = null;
			row = sheet.createRow(3);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Tháng");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Lượng khách");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Doanh thu");

			for (int i = 0; i < listDoanhThu.size(); i++) {
				row = sheet.createRow(4 + i);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(listDoanhThu.get(i).getThang());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(listDoanhThu.get(i).getLuongKhach());
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(listDoanhThu.get(i).getDoanhThu());
			}

			File f = new File("C:\\Users\\non\\Desktop\\DoanhThu.xlsx");

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
