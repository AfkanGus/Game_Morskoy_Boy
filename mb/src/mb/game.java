package mb;
//Добавим конструктор,класс реализации логики игры
public class game {
//Массив для игрового поля игрока
	public int[][] masPlay;
	//Констурктор класса
	public game() {
		//Создаем массив 10*10 - игровое пое игрока
		masPlay = new int[10][10];
	}	
	//Запуск игры - начало игры
	public void start() {
		//Очищаем игровое поле игрока
		for(int i = 0; i<10; i++) {
			for(int j=0;j<10;j++) {
				masPlay[i][j]=0;
			}
		}
		//Генерация однопалубных кораблей для игрового поля игрока
		make1P(masPlay);
		//Создание четырехпалубного коробля.
		make4P(masPlay);
	}
	//Проверка невыхода за границы массива
	private boolean testMasPoz(int i,int j) {
		if(((i>=0) && (i<=9))&&((j>=0)&&(j<=9))) {
			return true;
		}
	else 
		return false;
	}
	
	//запись значения в массив с провркой границ массива
	private void setMasValue(int[][] mas, int i,int j, int  val) {
		//Если не происходит выход за границы массива
		if(testMasPoz(i,j)==true) {
			//ЗАписываем значение в массив
			mas[i][j] = val;
		}
	}
	
	//Установить один элемент окужения
	private void setOkr(int[][] mas, int i, int j, int val) {
		//если не происходит выход за пределы массива ив ячейке нулевое значение
		if(testMasPoz(i,j) && (mas[i][j]==0))
			//УСтанавливаем необзодимое значение
			setMasValue(mas,i,j,val);
	}
	//окружение одной ячейки вокруг
	private void okrBegin(int[][]mas, int i, int j, int val) {
		setOkr(mas,i-1,j-1,val);//СВеръу слева
		setOkr(mas,i-1,j,val);//сверху
		setOkr(mas,i-1,j+1,val);//Сверху справа
		setOkr(mas,i,j+1,val);//справа
		setOkr(mas,i+1,j+1,val);//Снизу справа
		setOkr(mas,i+1,j,val);//снизу
		setOkr(mas,i+1,j-1,val);//Снизу слева
		setOkr(mas,i,j-1,val);//Слева
	}
	
	//Создание четырех однопаолубных кораблей
	private void make1P(int[][]mas) {
		//Цикл делает четыре шага - для четырех кораблей
		for(int k = 1; k<=4; k++) {
			//Глухой цикл while
			while(true) {
				//Находим  случайную позицию на игровом поле
				int i = (int)(Math.random()*10);
				int j = (int)(Math.random()*10);
				//Проверяем что там ничего нет и можно разместить корабль
			 if(mas[i][j]==0) {
				 //Размещаем однопалубный корабль
				 mas[i][j]=1;
				 //выполняем окружение
				 okrBegin(mas,i,j,-1);
				 //Прерываем цикл
				 break;
			 }		
			}
		}
	}
	
	//Конечное окружение
	private void okrEnd(int[][]mas) {
		for(int i =0; i<10; i++) {
			for(int j = 0; j<10; j++) {
				//Если значение элемента массива -2,то заменяем его на -1
				if(mas[i][j]==-2)
					mas[i][j]=-1;
			}
		}
	}
	
	//Проверяе ячейки для возможности размещения в ней палубы карабля
	private boolean testNewPaluba(int[][] mas, int i, int j) {
		//Если выход за границы массива
		if(testMasPoz(i,j)==false)
			return false;
		//Елси в этой ячейке 0 или -2,то она нам подходит
		if((mas[i][j]==0)||(mas[i][j]==-2))
			return true;
		return false;
	}
	
	//Создаение одного четырехполубного коробля 
	private void make4P(int[][]mas) {
		//Координаты голы коробля
		int i  = 0, j =0;
		//создание первой палубы - головы корабля,получение случайной строки
		i = (int)(Math.random()*10);
		//Получение случайной колонки
		j = (int)(Math.random()*10);
		
		//Помещаем в ячейку число 4
		mas[i][j] = 4;
		
		//Окуржаем минус двойками
		okrBegin(mas,i,j,-2);
		//Выбираем случайное направлние построения коробля 0-вверх,1-вправо,2-вниз,3-влево
		int napr=(int)(Math.random()*4);
		
		if(napr==0)//Вверх
		{
			//Если выход за границы массива
			if(testNewPaluba(mas,i-3,j)==false)
				napr=2;//Меняем вниз
		}
		else if(napr==1)//вправо
		{
			//если выход за границы массива
			if(testNewPaluba(mas,i,j+3)==false)
				napr=3;//меняем на влево
			
		}
		else if(napr==2)//вниз
		{
			//Если выхоод за границы массива
			if(testNewPaluba(mas,i+3,j)==false)
				napr=0;//меняем на вверх

		}
		else if(napr==3)//Влево
		{
			//Если выход за границы массива
			if(testNewPaluba(mas,i,j-3)==false)
				napr=1;//меняем на вправо
		}
		
		if(napr==0)//вверх
		{
			//Помещаем в ячейу число 4
			mas[i-3][j]=4;
			//окружаем минус двойками
			okrBegin(mas,i-3,j,-2);
			//Помещаем в ячейку число четыре 4
			mas[i-2][j]=4;
			//Окружаем минус двойками
			okrBegin(mas,i-2,j,-2);
			//Помещаем в ячейку число четыре 4
			mas[i-1][j]=4;
			//Окуражем минус двойками
			okrBegin(mas,i-1,j,-2);
		}
		else if(napr==1)//вправо
		{
			//Помещаем в ячейку число 4
			mas[i][j+3]=4;
			//Окружаем его минус двойкоми
			okrBegin(mas,i,j+3,-2);
			//Помешаем в ячейку число четыре 4
			mas[i][j+2]=4;
			//Окружаем минус дваойками
			okrBegin(mas,i,j+2,-2);
			//Помещаем в ячейку число 4
			mas[i][j+1]=4;
			//Окружаем минус двойками
			okrBegin(mas,i,j+1,-2);
		}
		else if(napr==2)//Вниз
		{
			mas[i+3][j]=4;
			okrBegin(mas,i+3,j,-2);
			mas[i+2][j]=4;
			okrBegin(mas,i+2,j,-2);
			mas[i+1][j]=4;
			okrBegin(mas,i+1,j,-2);
			
		}
		else if(napr==3)//Влево
		{
			mas[i][j-3]=4;
			okrBegin(mas,i,j-3,-2);
			mas[i][j-2]=4;
			okrBegin(mas,i,j-2,-2);
			mas[i][j-1]=4;
			okrBegin(mas,i,j-1,-2);
		}
		//Конечное окружение
		okrEnd(mas);
		
	}
			
	
	
	
	
}

	




