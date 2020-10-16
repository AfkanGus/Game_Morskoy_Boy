package mb;
//Добавим конструктор,класс реализации логики игры
public class game {
//Массив для игрового поля игрока
	public int[][] masPlay;
// массив для игового поля компьютера
	public int[][] masComp;
	// Признак хода компьютера(false-ходит игрок)
	public boolean compHod;
	// Признак конца игры(0-игра идет,1-победиь игрок, 2-победил компьютер)
	public int endg;

	// Констурктор класса
	public game() {
		// Создаем массив 10*10 - игровое пое игрока
		masPlay = new int[10][10];
		// Создаем массив 10*10 - игровое поле компьютера
		masComp = new int[10][10];
	}

	// Запуск игры - начало игры
	public void start() {
		// Очищаем игровое поле игрока
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				masPlay[i][j] = 0;
				masComp[i][j] = 0;
			}
		}
		// Обниляем прихнак чьей то победы
		endg = 0;
		// Передаем первы ход игроку
		compHod = false;
		// Раставляем коробли игрока
		rasstanovkaKorabley(masPlay);
		// Раставляем коробли компьютера
		rasstanovkaKorabley(masComp);
	}
	private void analizUbit(int[][]mas,int i,int j, int kolPalub) {
		//Количество раненых палуб
		int kolRanen = 0;
		//Выполняем подсчет раненых палуб
		
		for(int k = i-(kolPalub-1); k<=i+(kolPalub-1); k++) {
			for(int g=j-(kolPalub-1);g<=j+(kolPalub-1); g++) {
				//Если это палуба раненого коробля
				if(testMasPoz(k,g)&&(mas[k][g]==kolPalub+7))kolRanen++;
			}
		}
		//Если количество раненых палуб совподает с количеством  палуб
		//коробля, то он убит - прибавляем число 7	
		if(kolRanen==kolPalub) {
			for(int k =i-(kolPalub -1); k<=i+(kolPalub-1);k++) {
				for(int g = j-(kolPalub-1);g<=(j+kolPalub-1);g++){
					//Если эта палуба рненого коробля
					if(testMasPoz(k,g)&&(mas[k][g]==kolPalub+7)) {
						//Помечаем полубой убитого кообля
						mas[k][g]+=7;
						//окружаем палубу убитого корабля
						okrPodbit(mas,k,g);
					}
				}
			}
		}
	}
	//Проверка убит ли корабль
	private void testUbit(int[][]mas,int i, int j) {
		//Если однопалубный
		if(mas[i][j] == 8)
		{
			//Делаем выстрел
			mas[i][j]+=7;
			//Окружаем убитый корабль
			okrPodbit(mas,i,j);
		}
		//Если двухпалубный
		else if(mas[i][j]==9)analizUbit(mas,i,j,2);
		//Елси трузпалубны
		else if(mas[i][j]==10)analizUbit(mas,i,j,3);
		//Если чеырех палубный
		else if(mas[i][j]==11)analizUbit(mas,i,j,4);
	}
	// Расстоновка кораблей
		private void rasstanovkaKorabley(int[][] mas) {
			// Создаем один четырехпалубный корабль
			make4P(mas, 4);
			// Создаем два трехпалубных кораблся
			for (int i = 1; i <= 2; i++)
				make4P(mas, 3);
			// Создаем три двухпалубных корабля
			for (int i = 1; i <= 3; i++)
				make4P(mas, 2);
			// Создаем четыре однопалубных корабля
			make1P(mas);
		
		}
	
	//Выстрел игрока
		public void vistrelPlay(int i, int j) {
			//При выстреле прибавляем число 7
			masComp[i][j]+=7;
			//Проверяем убит ли корабль
			testUbit(masComp,i,j);
			//Проверяем конец игры
			testEndGame();
			//Если был промах  - передаем ход компьютеру
			if(masComp[i][j]<8) {
				compHod = true;//Передаем ход компьютеру
				//Ходит компьютер - пока попадает в цель
				while(compHod==true)compHod = compHodit();
			}
		}
		
		//Добавим метод,который выполняем выстрел компьютера,возвращаем инстину
		//если попал
		private boolean compHodit() 
		{
		//Признак попадания в цель
			boolean rez = false;
			//Признак выстрела в раненый корабль
			boolean flag = false;
		
			//Пробегаем все игровое поле игрока
				//_for1:
					for(int i = 0; i<10; i++) {
				for(int j =0; j<10; j++) {
					//Если находим раненую палубу
					if((masPlay[i][j]>=9)&&(masPlay[i][j]<=11))
					{
						flag = true;
						//Ячейка сверху
						//Проверяем, что можно сделать выстрел
						
						if(testMasPoz(i-1,j)&&(masPlay[i-1][j]<=4)&&(masPlay[i-1][j]!=-2))
						{
							//Делаем выстрел
							masPlay[i-1][j]+=7;
							//Проверяем что убит
							testUbit(masPlay,i-1,j);
							//Если произошло попадание
							if(masPlay[i-1][j]>=8)rez = true;
							//прерываем сразу все циклы
							break;
						}
						//ячейка снизу
						//Проверяем, что можно сделать выстрел
				else if (testMasPoz(i+1,j)&&(masPlay[i+1][j]<=4)&&(masPlay[i+1][j]!=-2)) {
					//Делаем выстрел
				masPlay[i+1][j]+=7;
				//Проверяем,что убит
				testUbit(masPlay,i+1,j);
				//Если произошло попадание
				if(masPlay[i+1][j]>=8)rez=true;
				//Прерываем стразу все циклы
				break;
				}
						
						//Ячейка слева
						//Проверяем,что можно сделать выстрел
				if(testMasPoz(i,j-1)&&(masPlay[i][j-1]<=4) &&(masPlay[i][j-1]!=-2)) {
					//Делаем выстрел
					masPlay[i][j-1]+=7;
					//Проверяем,что убит
					testUbit(masPlay,i,j-1);
					//если произошло попадание
					if(masPlay[i][j-1]>=8)rez = true;
					//прерываем сразу все циклы
					break;
				}		
				//Если ячейка справа
				//Проверяем,что можно сделать выстрел
				else if(testMasPoz(i,j+1)&&(masPlay[i][j+1]<=4)&&(masPlay[i][j+1]!=-2)) {
					//Делаем выстрел
					masPlay[i][j+1]+=7;
					//Проверяем,что убит
					testUbit(masPlay,i,j+1);
					//если произошло попадание
					if(masPlay[i][j+1]>=8)rez = true;
					//прерываем сразу все циклы
					break;
				}
				}
				}
			}
		
			//Елси не было высрела в раненую палубу
			if(flag == false){
				//Деалем  100 случайных попыток выстрела в случайном месте
				for(int l = 1; l<=100;l++) {
					// находим случайную позицию  на игорвом поле
					int i = (int)(Math.random()*10);
					int j = (int)(Math.random()*10);
					//ПРоверяем что можно сдеоать выстрел
					if((masPlay[i][j]<=4)&&(masPlay[i][j]!=-2)) {
						//Делаем выстрел
						masPlay[i][j]+=7;
						//Проверяем что убит
						testUbit(masPlay,i,j);
						//Если произошло поподание
						if(masPlay[i][j]>=8) 
							rez = true;
							flag = true;
							//Прерываем цикл
							break;
						}
							
					}
					//Если выстрела еще не было
					if(flag == false) {
						//Начинаем пробигать массив от начало до конца
						
							//_for2:
								for(int i = 0; i<10; i++) {
							for(int j = 0; j<10; j++) {
								//Проверяем что можно сделать выстрел
								if((masPlay[i][j]<=4)&&(masPlay[i][j]!=-2)) {
									//Делаем выстрел
									masPlay[i][j]+=7;
									//Проверяем что убит
									testUbit(masPlay,i,j);
									//Еслипроизошло попадание
									if(masPlay[i][j]>=8)
									rez = true;
									//Прерываем сразц все циклы
									break;
									
								}
							}
						}
					}
				}
				//Проверяем конец игры
				testEndGame();
				//Возвращщаем результат
				return rez;
			}
		
		//Установить один элемент окружения подбитого корабля
		private void setOkrPodbit(int[][] mas,int i,int j) {
			//Если не произходит выход за пределф массива и в ячейке нулевое значение
			if(testMasPoz(i,j)==true)
			{
				//Устанавливаем необхоимое значние
				if((mas[i][j]==-1)||(mas[i][j]==6))mas[i][j]--;
			}
		}
		//Окружение одной ячейки подбитого вокруг
		private void okrPodbit(int[][]mas,int i,int j) {
			setOkrPodbit(mas,i-1,j-1);//сверху слева
			setOkrPodbit(mas,i-1,j);//Сверху
			setOkrPodbit(mas,i-1,j+1);//сверху справа
			setOkrPodbit(mas,i,j+1);//Справа
			setOkrPodbit(mas,i+1,j+1);//Снизу
			setOkrPodbit(mas,i+1,j-1);//Снизу
			setOkrPodbit(mas,i,j-1);//Слева
		}
		//Проверка окончании игры
		private void testEndGame() {
			
			//Тестовое число = 15*4+16*2*3+17*3*2+18*4	
			//Ситуация, когда все крабли убиты
			int testNumber = 330;
			int kolComp = 0;//Сумма убитых палуб компьютера
			int kolPlay = 0;//Сумма убитых палуб игрока
			//Перебираем все элементы сразу двух массивов
			for(int i =0; i<10;i++) {
				for(int j = 0; j<10; j++) {
					//суммируем подбитые палубы игрока
					if(masPlay[i][j]>=15)kolPlay+=masPlay[i][j];
					//Суммируем подбитые палубы компьютера
					if(masComp[i][j]>=15)kolComp+=masComp[i][j];	
				}
			}
			if(kolPlay==testNumber)endg=2;//если победил игрок
			else if(kolComp==testNumber) endg=1;//Если победил компьютер	
		}
		// Проверка невыхода за границы массива
		private boolean testMasPoz(int i, int j) {
			if (((i >= 0) && (i <= 9)) && ((j >= 0) && (j <= 9))) {
				return true;
			} else
				return false;
		}

		// запись значения в массив с провркой границ массива
		private void setMasValue(int[][] mas, int i, int j, int val) {
			// Если не происходит выход за границы массива
			if (testMasPoz(i, j) == true) {
				// ЗАписываем значение в массив
				mas[i][j] = val;
			}
		}
		// Установить один элемент окужения
		private void setOkr(int[][] mas, int i, int j, int val) {
			// если не происходит выход за пределы массива ив ячейке нулевое значение
			if (testMasPoz(i, j) && (mas[i][j] == 0))
				// УСтанавливаем необзодимое значение
				setMasValue(mas, i, j, val);
		}
		// окружение одной ячейки вокруг
		private void okrBegin(int[][] mas, int i, int j, int val) {
			setOkr(mas, i - 1, j - 1, val);// СВеръу слева
			setOkr(mas, i - 1, j, val);// сверху
			setOkr(mas, i - 1, j + 1, val);// Сверху справа
			setOkr(mas, i, j + 1, val);// справа
			setOkr(mas, i + 1, j + 1, val);// Снизу справа
			setOkr(mas, i + 1, j, val);// снизу
			setOkr(mas, i + 1, j - 1, val);// Снизу слева
			setOkr(mas, i, j - 1, val);// Слева
		}
		// Конечное окружение
		private void okrEnd(int[][] mas) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					// Если значение элемента массива -2,то заменяем его на -1
					if (mas[i][j] == -2)
						mas[i][j] = -1;
				}
			}
		}
		// Создание четырех однопаолубных кораблей
		private void make1P(int[][] mas) {
			// Цикл делает четыре шага - для четырех кораблей
			for (int k = 1; k <= 4; k++) {
				// Глухой цикл while
				while (true) {
					// Находим случайную позицию на игровом поле
					int i = (int) (Math.random() * 10);
					int j = (int) (Math.random() * 10);
					// Проверяем что там ничего нет и можно разместить корабль
					if (mas[i][j] == 0) {
						// Размещаем однопалубный корабль
						mas[i][j] = 1;
						// выполняем окружение
						okrBegin(mas, i, j, -1);
						// Прерываем цикл
						break;
					}
				}
			}
		}
		// Проверяе ячейки для возможности размещения в ней палубы карабля
		private boolean testNewPaluba(int[][] mas, int i, int j) {
			// Если выход за границы массива
			if (testMasPoz(i, j) == false)
				return false;
			// Елси в этой ячейке 0 или -2,то она нам подходит
			if ((mas[i][j] == 0) || (mas[i][j] == -2))
				return true;
			return false;
		}
		
	// Создание корабля с несколькими паоубами от 2-ч до 4-ч
	private void make4P(int[][] mas, int kolPaluba) {
		// Глухой цкл
		while (true) {
			boolean flag = false;
			// Кординаты головы коробля
			int i = 0, j = 0;
			// Создание первой палубы - головы коробля
			// получение случайной строки
			i = (int) (Math.random() * 10);
			// Плучение случайной колонки
			j = (int) (Math.random() * 10);
			
			// Выбираем случайное направление построения коробля
			// 0 - вверх, 1- вправо, 2- вниз, 3 - влево
			int napr = (int) (Math.random() * 4);
			if (testNewPaluba(mas, i, j) == true) {
				if (napr == 0)// Верх
				{
					// Если можно разположить палубу
					if (testNewPaluba(mas, i - (kolPaluba - 1), j) == true)
						flag = true;
				} else if (napr == 1)// Вправо
				{
					// Если можно разположить палубу
					if (testNewPaluba(mas, i, j + (kolPaluba - 1)) == true)
						flag = true;
				} else if (napr == 2)// Вниз
				{
					// Если можно разположить палубу
					if (testNewPaluba(mas, i + (kolPaluba - 1), j) == true)
						flag = true;
				} else if (napr == 3)// влево
				{
					// Если можно разположить палубу
					if (testNewPaluba(mas, i, j - (kolPaluba - 1)) == true)
						flag = true;
				}
			}
			if (flag == true) {
				// Помещаем в ячейку число палуб
				mas[i][j] = kolPaluba;
				// окружаем минус двойками
				okrBegin(mas, i, j, -2);

				if (napr == 0)// Вверх
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// Помещаем в ячейку число палуб
						mas[i - k][j] = kolPaluba;
						// Окружаем имнус двойками
						okrBegin(mas, i - k, j, -2);
					}
				} else if (napr == 1)// вправо
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// Помещаем в ячейку число палуб
						mas[i][j] = kolPaluba;
						// Окуржаем минус двойками
						okrBegin(mas, i, j + k, -2);
					}
				} else if (napr == 2)// вниз
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// Помещаем в ячейку число палуб
						mas[i + k][j] = kolPaluba;
						// Окружаем минус двойками
						okrBegin(mas, i + k, j, -2);
					}
				} else if (napr == 3)// влевач
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// Помещаем в ячейку число палуб
						mas[i][j - k] = kolPaluba;
						// окружаем инус двойками
						okrBegin(mas, i, j - k, -2);
					}
				}
				break;
			}
		}
		// Конечное окружение
		okrEnd(mas);
	}
}



	// Создаение одного четырехполубного коробля
/**	private void make4P(int[][] mas) {
		// Координаты голы коробля
		int i = 0, j = 0;
		// создание первой палубы - головы корабля,получение случайной строки
		i = (int) (Math.random() * 10);
		// Получение случайной колонки
		j = (int) (Math.random() * 10);

		// Помещаем в ячейку число 4
		mas[i][j] = 4;

		// Окуржаем минус двойками
		okrBegin(mas, i, j, -2);
		// Выбираем случайное направлние построения коробля
		// 0-вверх,1-вправо,2-вниз,3-влево
		int napr = (int) (Math.random() * 4);

		if (napr == 0)// Вверх
		{
			// Если выход за границы массива
			if (testNewPaluba(mas, i - 3, j) == false)
				napr = 2;// Меняем вниз
		} else if (napr == 1)// вправо
		{
			// если выход за границы массива
			if (testNewPaluba(mas, i, j + 3) == false)
				napr = 3;// меняем на влево

		} else if (napr == 2)// вниз
		{
			// Если выхоод за границы массива
			if (testNewPaluba(mas, i + 3, j) == false)
				napr = 0;// меняем на вверх

		} else if (napr == 3)// Влево
		{
			// Если выход за границы массива
			if (testNewPaluba(mas, i, j - 3) == false)
				napr = 1;// меняем на вправо
		}

		if (napr == 0)// вверх
		{
			// Помещаем в ячейу число 4
			mas[i - 3][j] = 4;
			// окружаем минус двойками
			okrBegin(mas, i - 3, j, -2);
			// Помещаем в ячейку число четыре 4
			mas[i - 2][j] = 4;
			// Окружаем минус двойками
			okrBegin(mas, i - 2, j, -2);
			// Помещаем в ячейку число четыре 4
			mas[i - 1][j] = 4;
			// Окуражем минус двойками
			okrBegin(mas, i - 1, j, -2);
		} else if (napr == 1)// вправо
		{
			// Помещаем в ячейку число 4
			mas[i][j + 3] = 4;
			// Окружаем его минус двойкоми
			okrBegin(mas, i, j + 3, -2);
			// Помешаем в ячейку число четыре 4
			mas[i][j + 2] = 4;
			// Окружаем минус дваойками
			okrBegin(mas, i, j + 2, -2);
			// Помещаем в ячейку число 4
			mas[i][j + 1] = 4;
			// Окружаем минус двойками
			okrBegin(mas, i, j + 1, -2);
		} else if (napr == 2)// Вниз
		{
			mas[i + 3][j] = 4;
			okrBegin(mas, i + 3, j, -2);
			mas[i + 2][j] = 4;
			okrBegin(mas, i + 2, j, -2);
			mas[i + 1][j] = 4;
			okrBegin(mas, i + 1, j, -2);

		} else if (napr == 3)// Влево
		{
			mas[i][j - 3] = 4;
			okrBegin(mas, i, j - 3, -2);
			mas[i][j - 2] = 4;
			okrBegin(mas, i, j - 2, -2);
			mas[i][j - 1] = 4;
			okrBegin(mas, i, j - 1, -2);
		}
		// Конечное окружение
		okrEnd(mas);

	}**/
	
	
	
	



