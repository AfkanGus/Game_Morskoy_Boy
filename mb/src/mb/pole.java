package mb;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

//класс панели игрового поля
public class pole extends JPanel {
	// таймер отрисовки
	private Timer tmDraw;
	// изображения используемые в игре
	private Image fon, paluba, ubit, ranen, end1, end2, bomba;
	// две кнопки
	private JButton btn1, btn2;
	//Переменная для реализации лигоки игры
	private game myGame;
// конструктор класса
	public pole() {
		//Создаем объект новой игры
		myGame = new game();
		//Запускаем игру
		myGame.start();
		//попытка загрузки всех изображений для игр
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
		// создаем,настраиваем и запусаем таймер
		// для отрисовки
		tmDraw = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// вызываем перерисовку - paintComponent()
				repaint();
			}
		});
		tmDraw.start();

		// Включаем возможность произвольного размещения элементов интерфейса на панели
		setLayout(null);

		// Создаем кнопку новая игра
		btn1 = new JButton();
		btn1.setText("Новая игра");
		btn1.setForeground(Color.blue);
		btn1.setFont(new Font("serif", 0, 30));
		btn1.setBounds(130, 450, 200, 80);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Запуск - начало игры
				myGame.start();
			}
		});
		add(btn1);
		// СОздаем кнопку выход
		btn2 = new JButton();
		btn2.setText("Выход");
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
	// Метод отрисовки
	public void paintComponent(Graphics gr) {
		// Очищение игрового поля
		super.paintComponent(gr);
		// отрисовка фона
		gr.drawImage(fon, 0, 0, 900, 600, null);
		// отрисовка шрифта
		gr.setFont(new Font("serif", 3, 40));
		// установка цвета
		gr.setColor(Color.blue);
		// выведение надписей
		gr.drawString("Компьютер", 150, 50);
		gr.drawString("Игрок", 590, 50);
		//Отрисовка игрового поля Игрок на оснвоании массива
		for(int i =0; i< 10; i++) {
			for(int j =0; j <10; j++){
				//Если палуба корабля
				if((myGame.masPlay[i][j]>= 1)&&(myGame.masPlay[i][j] <= 4)) {
					gr.drawImage(paluba, 500 + j*30, 100 + i * 30,30,30,null);
				}
			}
		}
		// отисовка сетки игрового поля Компьютера
		gr.setColor(Color.blue);
		for (int i = 0; i <= 10; i++) {
			// рисование линий сетки игрового поля
			gr.drawLine(100 + i * 30, 100, 100 + i * 30, 400);
			gr.drawLine(100, 100 + i * 30, 400, 100 + i * 30);
			// рисование линий сетки ирового поля Игрока
			gr.drawLine(500 + i * 30, 100, 500 + i * 30, 400);
			gr.drawLine(500, 100 + i * 30, 800, 100 + i * 30);
		}
		// Вывод цифр и букв для нумпрции строк и столбцов
		// Установка шрифта
		gr.setFont(new Font("serif", 0, 20));
		gr.setColor(Color.red);
	
		// выведения цифр и букв слева и сверху от игрового полей
		for (int i = 1; i <= 10; i++) {
			// вывод цифр
			gr.drawString("" + i, 73, 93 + i * 30);
			gr.drawString("" + i, 473, 93 + i * 30);
			
			// вывод букв
			gr.drawString("" + (char) ('A' + i - 1), 78 + i * 30, 93);
			gr.drawString("" + (char) ('A' + i - 1), 478 + i * 30, 93);
		}

	}

}
