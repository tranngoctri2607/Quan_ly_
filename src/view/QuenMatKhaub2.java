package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import javax.swing.border.*;
import Utils.SendEmail;
import Utils.XImage;

public class QuenMatKhaub2 extends JFrame {

	private JPanel contentPane;
	private JTextField txtOTP;
	private JButton btnSendAgain;
	private JLabel lblBaoLoi;
	private int rand = -1;
	private JLabel lblBack;
	private JLabel lblExit;
	private JLabel lblTitle;
	private Timer timer;
	private int remainingSeconds = 120;
	private JLabel lblTime;

	public QuenMatKhaub2(String email) {

		setIconImage(XImage.APP_ICON);
		setForeground(Color.BLACK);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtOTP = new JTextField();
		txtOTP.setForeground(Color.BLACK);
		txtOTP.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtOTP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					next(email);
				}
			}
		});
		txtOTP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtOTP.getText().equals(" OTP")) {
					txtOTP.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtOTP.getText().equals("")) {
					txtOTP.setText(" OTP");
				}
			}
		});
		txtOTP.setText(" OTP");
		txtOTP.setColumns(10);
		txtOTP.setBounds(145, 120, 298, 42);
		contentPane.add(txtOTP);

		btnSendAgain = new JButton("Send again");
		btnSendAgain.setForeground(Color.BLACK);
		btnSendAgain.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSendAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendEmail(email);
				resetTimer();
			}
		});
		btnSendAgain.setBounds(488, 119, 131, 42);
		contentPane.add(btnSendAgain);

		lblBaoLoi = new JLabel("");
		lblBaoLoi.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblBaoLoi.setForeground(Color.BLACK);
		lblBaoLoi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBaoLoi.setBounds(217, 219, 298, 25);
		contentPane.add(lblBaoLoi);

		lblBack = new JLabel(" Back to login");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setForeground(Color.BLACK);
		lblBack.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblBack.setBounds(283, 176, 129, 32);
		contentPane.add(lblBack);

		lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Exit?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
					QuenMatKhaub2.this.dispose();
				}
			}
		});
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Montserrat", Font.PLAIN, 20));
		lblExit.setBackground(Color.BLACK);
		lblExit.setBounds(670, 0, 30, 30);
		contentPane.add(lblExit);

		lblTitle = new JLabel("RESET PASSWORD");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitle.setBounds(219, 50, 260, 42);
		contentPane.add(lblTitle);

		setLocationRelativeTo(null);
		lblTime = new JLabel();
		lblTime.setBounds(145, 173, 109, 25);
		contentPane.add(lblTime);
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (remainingSeconds > 0) {
					remainingSeconds--;
					updateTimerLabel();
					btnSendAgain.setEnabled(false);
					txtOTP.setEditable(true);
				} else {
					btnSendAgain.setEnabled(true);
					txtOTP.setEditable(false);
					timer.cancel();
				}
			}
		}, 0, 1000);

		updateTimerLabel();
		sendEmail(email);
	}

	private void updateTimerLabel() {
		int minutes = remainingSeconds / 60;
		int seconds = remainingSeconds % 60;
		String formattedTime = String.format("%02d:%02d", minutes, seconds);
		lblTime.setText(formattedTime);
	}

	private void resetTimer() {
		timer.cancel();
		remainingSeconds = 180; // Reset to 3 minutes
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (remainingSeconds > 0) {
					remainingSeconds--;
					updateTimerLabel();
					btnSendAgain.setEnabled(false);
					txtOTP.setEditable(true);
				} else {
					btnSendAgain.setEnabled(true);
					txtOTP.setEditable(false);
					timer.cancel();
				}
			}
		}, 0, 1000); // Run every 1 second
		updateTimerLabel();
	}

	void next(String email) {
		try {
			int otp = Integer.valueOf(txtOTP.getText());
			if (otp > 0) {
				if (otp == rand) {
					lblBaoLoi.setText("");
					new QuenMatKhaub3(email).setVisible(true);
					dispose();
				} else {
					lblBaoLoi.setText("Incorrect OTP code.");
				}
			}
		} catch (NumberFormatException e) {
			lblBaoLoi.setText("OTP code is number.");
		}
	}

	void sendEmail(String mail) {
		rand = SendEmail.send(mail);
	}
}
