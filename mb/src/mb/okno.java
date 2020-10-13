
package mb;

import java.awt.*;
import javax.swing.*;

public class okno extends JFrame {
	//конструктор коасса
	public okno()
	{
		//Создание объекта поанели и подключание ее к окна
		pole pan = new pole();
		Container cont = getContentPane();
		cont.add(pan);
		
		//Заголовок окна
		setTitle(" Игра\"Морской бой\"");
		//Границы окна: разположение и размеры
		setBounds(0,0,900,600);
		
		//Операция при закрытии окна - звершение приложения
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Запрет изменения размеров окна
		setResizable(false);
		//Отображение(показ)окна
		setVisible(true);
	}

}
