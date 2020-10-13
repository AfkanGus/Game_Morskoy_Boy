package mb;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

//����� ������ �������� ����
public class pole extends JPanel {
	// ������ ���������
	private Timer tmDraw;
	// ����������� ������������ � ����
	private Image fon, paluba, ubit, ranen, end1, end2, bomba;
	// ��� ������
	private JButton btn1, btn2;
	//���������� ��� ���������� ������ ����
	private game myGame;
// ����������� ������
	public pole() {
		//������� ������ ����� ����
		myGame = new game();
		//��������� ����
		myGame.start();
		//������� �������� ���� ����������� ��� ���
		try {
			fon = ImageIO.read(new File("c:\\mb_img\\fon.png"));
			paluba = ImageIO.read(new File("c:\\mb_img\\paluba.png"));
			ranen = ImageIO.read(new File("c:\\mb_img\\ranen.png"));
			ubit = ImageIO.read(new File("c:\\mb_img\\ubut.png"));
			end1 = ImageIO.read(new File("c:\\mb_img\\end1.png"));
			end2 = ImageIO.read(new File("c:\\mb_img\\end2.png"));
			bomba = ImageIO.read(new File("c:\\mb_img\\bomba.png"));
		} catch (Exception ex) {
		}
		// �������,����������� � �������� ������
		// ��� ���������
		tmDraw = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// �������� ����������� - paintComponent()
				repaint();
			}
		});
		tmDraw.start();

		// �������� ����������� ������������� ���������� ��������� ���������� �� ������
		setLayout(null);

		// ������� ������ ����� ����
		btn1 = new JButton();
		btn1.setText("����� ����");
		btn1.setForeground(Color.blue);
		btn1.setFont(new Font("serif", 0, 30));
		btn1.setBounds(130, 450, 200, 80);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//������ - ������ ����
				myGame.start();
			}
		});
		add(btn1);
		// ������� ������ �����
		btn2 = new JButton();
		btn2.setText("�����");
		btn2.setForeground(Color.red);
		btn2.setFont(new Font("serif", 0, 20));
		btn2.setBounds(530, 450, 200, 80);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(btn2);
	}
	// ����� ���������
	public void paintComponent(Graphics gr) {
		// �������� �������� ����
		super.paintComponent(gr);
		// ��������� ����
		gr.drawImage(fon, 0, 0, 900, 600, null);
		// ��������� ������
		gr.setFont(new Font("serif", 3, 40));
		// ��������� �����
		gr.setColor(Color.blue);
		// ��������� ��������
		gr.drawString("���������", 150, 50);
		gr.drawString("�����", 590, 50);
		//��������� �������� ���� ����� �� ��������� �������
		for(int i =0; i< 10; i++) {
			for(int j =0; j <10; j++){
				//���� ������ �������
				if((myGame.masPlay[i][j]>= 1)&&(myGame.masPlay[i][j] <= 4)) {
					gr.drawImage(paluba, 500 + j*30, 100 + i * 30,30,30,null);
				}
			}
		}
		// �������� ����� �������� ���� ����������
		gr.setColor(Color.blue);
		for (int i = 0; i <= 10; i++) {
			// ��������� ����� ����� �������� ����
			gr.drawLine(100 + i * 30, 100, 100 + i * 30, 400);
			gr.drawLine(100, 100 + i * 30, 400, 100 + i * 30);
			// ��������� ����� ����� ������� ���� ������
			gr.drawLine(500 + i * 30, 100, 500 + i * 30, 400);
			gr.drawLine(500, 100 + i * 30, 800, 100 + i * 30);
		}
		// ����� ���� � ���� ��� �������� ����� � ��������
		// ��������� ������
		gr.setFont(new Font("serif", 0, 20));
		gr.setColor(Color.red);
	
		// ��������� ���� � ���� ����� � ������ �� �������� �����
		for (int i = 1; i <= 10; i++) {
			// ����� ����
			gr.drawString("" + i, 73, 93 + i * 30);
			gr.drawString("" + i, 473, 93 + i * 30);
			
			// ����� ����
			gr.drawString("" + (char) ('A' + i - 1), 78 + i * 30, 93);
			gr.drawString("" + (char) ('A' + i - 1), 478 + i * 30, 93);
		}

	}

}
