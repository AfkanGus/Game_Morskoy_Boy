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
	//Координаты курсоры мыши
	private int mX,mY;
	
	public class myMouse1 implements MouseListener {
		public void mouseClicked(MouseEvent e) {}
		//При нажатии кнопки мыши
		public void mousePressed(MouseEvent e) {
			//Если сделано одиночное нажатие леврй клавишей мыши
			if((e.getButton()==1)&&(e.getClickCount()==1)) {
				//Получаем текущие координаты курсора мыши
				mX = e.getX();
				mY = e.getY();
				//Если курсор мыши внутри игрового поля компьютера
				if((mX>100)&&(mY>100)&&(mX<400)&&(mY<400)) {
					//Если не конец игры и ход игрока
					if((myGame.endg==0)&&(myGame.compHod==false)) {
						//Вычисляем номера элемента в строке в массиве
						int i = (mX -100)/30;
						//Вычисляем номер элемента в строке в массиве
						int j = (mX-100)/30;
						//Если ячейка подходит для выстрела
						if(myGame.masComp[i][j]<=4)
							//Производим выстрел
							myGame.vistrelPlay(i, j);
					}
				}
				
			}
		}
		
		public void mouseReleased(MouseEvent e) {}
		
		public void mouseEntered(MouseEvent e) {}
		
		public void mouseExited(MouseEvent e) {}
	}

	public class myMouse2 implements MouseMotionListener {
		public void mouseDragged(MouseEvent e) {}

		//При перемещении курсора мыши
		public void mouseMoved(MouseEvent e) {
			//Получение координаты курсора
			mX = e.getX();
			mY = e.getY();
			//Если курсор в области игрока
			if((mX>= 100)&&(mY>=100)&&(mX<=400)&&(mY<=400))
				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			else
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
// конструктор класса
	public pole() {
		addMouseListener(new myMouse1());
		addMouseMotionListener(new myMouse2());
		setFocusable(true);//Передаем фокус панели
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
		
		//Отрисвока игровых полей Компьютера и игрока на осовании массивав
		for(int i =0; i< 10; i++) {
			for(int j =0; j <10; j++){
				//ИГровое поле компьютера
				if(myGame.masComp[i][j]!=0) {
					//Если это подбитая полуба корабля
					if((myGame.masComp[i][j]>=8)&&(myGame.masComp[i][j]<=11)) {
						gr.drawImage(ranen,100+j*30,100+i*30,30,30,null);
					}
					//Если это палуба полностью подбитого кораблся
					else if(myGame.masComp[i][j]>=15) {
						gr.drawImage(ubit,100+j*30,100+i*30,30,30,null);
					}
					//Если был выстрел
					if(myGame.masComp[i][j]>=5) {
						gr.drawImage(bomba,100+j*30,100+i*30,30,30,null);
					}
				}
				//Игрвое поле игрока
				if(myGame.masPlay[i][j]!=0)
				{
				//Если это палуба корабля
				if((myGame.masPlay[i][j]>= 1)&&(myGame.masPlay[i][j] <= 4)) {
					gr.drawImage(paluba, 500 + j*30, 100 + i * 30,30,30,null);
				}
				//Если это подбитая полуба корабля
				else if((myGame.masPlay[i][j]>=8)&&(myGame.masPlay[i][j]<=11)) {
					gr.drawImage(ranen, 500+j*30,100+i*30,30,30,null);
				}
				//если это поалуба польностью подбитого корабля
				else if (myGame.masPlay[i][j]>=15) {
					gr.drawImage(ubit,500+j*30,100+i*30,30,30,null);
				}
				//Если был выстрел
				if(myGame.masPlay[i][j] >= 5) {
					gr.drawImage(bomba,500+j*30,100+i*30,30,30,null);
				}
				
			}
				
		}
		}
	
		gr.setColor(Color.red);//Красный цвет
		//Елси курсор мыши внутри игрового поля компьютера
		if((mX>100)&&(mY>100)&&(mX<400)&&(mY<400)) {
			//Если не конец игры и ход игрока
			if((myGame.endg==0)&&(myGame.compHod==false)) 
			{
			//Вычисляем номер строки в массиве
			int i = (mY-100)/30;
			//Вычисляем номер элемента в строкке в массиве
			int j = (mX-100)/30;
			//Если ячейка подходит для выстрела
			if(myGame.masComp[i][j]<=4)
			//Рисуем квадрат с заливкой
				gr.fillRect(100+j*30, 100+i*30, 30, 30);
			
		
	}
	}	
	
		
		// отисовка сетки игрового поля Компьютера
		gr.setColor(Color.blue);
		for (int i = 0; i <= 10; i++) {
			// рисование линий сетки игрового поля Компьютера
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
		//Вывод изображения конца игры - при окончании игры
		if(myGame.endg==1)//Если победил игрок
		{ 
			gr.drawImage(end1, 300, 200,300,100,null);
		}
		else if(myGame.endg==2)//Если победил компьютер
		{
			gr.drawImage(end2, 300, 200, 300, 100, null);
		}
		}
	
}


