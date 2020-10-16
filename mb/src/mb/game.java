package mb;
//������� �����������,����� ���������� ������ ����
public class game {
//������ ��� �������� ���� ������
	public int[][] masPlay;
// ������ ��� ������� ���� ����������
	public int[][] masComp;
	// ������� ���� ����������(false-����� �����)
	public boolean compHod;
	// ������� ����� ����(0-���� ����,1-������� �����, 2-������� ���������)
	public int endg;

	// ����������� ������
	public game() {
		// ������� ������ 10*10 - ������� ��� ������
		masPlay = new int[10][10];
		// ������� ������ 10*10 - ������� ���� ����������
		masComp = new int[10][10];
	}

	// ������ ���� - ������ ����
	public void start() {
		// ������� ������� ���� ������
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				masPlay[i][j] = 0;
				masComp[i][j] = 0;
			}
		}
		// �������� ������� ���� �� ������
		endg = 0;
		// �������� ����� ��� ������
		compHod = false;
		// ���������� ������� ������
		rasstanovkaKorabley(masPlay);
		// ���������� ������� ����������
		rasstanovkaKorabley(masComp);
	}
	private void analizUbit(int[][]mas,int i,int j, int kolPalub) {
		//���������� ������� �����
		int kolRanen = 0;
		//��������� ������� ������� �����
		
		for(int k = i-(kolPalub-1); k<=i+(kolPalub-1); k++) {
			for(int g=j-(kolPalub-1);g<=j+(kolPalub-1); g++) {
				//���� ��� ������ �������� �������
				if(testMasPoz(k,g)&&(mas[k][g]==kolPalub+7))kolRanen++;
			}
		}
		//���� ���������� ������� ����� ��������� � �����������  �����
		//�������, �� �� ���� - ���������� ����� 7	
		if(kolRanen==kolPalub) {
			for(int k =i-(kolPalub -1); k<=i+(kolPalub-1);k++) {
				for(int g = j-(kolPalub-1);g<=(j+kolPalub-1);g++){
					//���� ��� ������ ������� �������
					if(testMasPoz(k,g)&&(mas[k][g]==kolPalub+7)) {
						//�������� ������� ������� ������
						mas[k][g]+=7;
						//�������� ������ ������� �������
						okrPodbit(mas,k,g);
					}
				}
			}
		}
	}
	//�������� ���� �� �������
	private void testUbit(int[][]mas,int i, int j) {
		//���� ������������
		if(mas[i][j] == 8)
		{
			//������ �������
			mas[i][j]+=7;
			//�������� ������ �������
			okrPodbit(mas,i,j);
		}
		//���� ������������
		else if(mas[i][j]==9)analizUbit(mas,i,j,2);
		//���� �����������
		else if(mas[i][j]==10)analizUbit(mas,i,j,3);
		//���� ������ ��������
		else if(mas[i][j]==11)analizUbit(mas,i,j,4);
	}
	// ����������� ��������
		private void rasstanovkaKorabley(int[][] mas) {
			// ������� ���� ��������������� �������
			make4P(mas, 4);
			// ������� ��� ������������ ��������
			for (int i = 1; i <= 2; i++)
				make4P(mas, 3);
			// ������� ��� ������������ �������
			for (int i = 1; i <= 3; i++)
				make4P(mas, 2);
			// ������� ������ ������������ �������
			make1P(mas);
		
		}
	
	//������� ������
		public void vistrelPlay(int i, int j) {
			//��� �������� ���������� ����� 7
			masComp[i][j]+=7;
			//��������� ���� �� �������
			testUbit(masComp,i,j);
			//��������� ����� ����
			testEndGame();
			//���� ��� ������  - �������� ��� ����������
			if(masComp[i][j]<8) {
				compHod = true;//�������� ��� ����������
				//����� ��������� - ���� �������� � ����
				while(compHod==true)compHod = compHodit();
			}
		}
		
		//������� �����,������� ��������� ������� ����������,���������� �������
		//���� �����
		private boolean compHodit() 
		{
		//������� ��������� � ����
			boolean rez = false;
			//������� �������� � ������� �������
			boolean flag = false;
		
			//��������� ��� ������� ���� ������
				//_for1:
					for(int i = 0; i<10; i++) {
				for(int j =0; j<10; j++) {
					//���� ������� ������� ������
					if((masPlay[i][j]>=9)&&(masPlay[i][j]<=11))
					{
						flag = true;
						//������ ������
						//���������, ��� ����� ������� �������
						
						if(testMasPoz(i-1,j)&&(masPlay[i-1][j]<=4)&&(masPlay[i-1][j]!=-2))
						{
							//������ �������
							masPlay[i-1][j]+=7;
							//��������� ��� ����
							testUbit(masPlay,i-1,j);
							//���� ��������� ���������
							if(masPlay[i-1][j]>=8)rez = true;
							//��������� ����� ��� �����
							break;
						}
						//������ �����
						//���������, ��� ����� ������� �������
				else if (testMasPoz(i+1,j)&&(masPlay[i+1][j]<=4)&&(masPlay[i+1][j]!=-2)) {
					//������ �������
				masPlay[i+1][j]+=7;
				//���������,��� ����
				testUbit(masPlay,i+1,j);
				//���� ��������� ���������
				if(masPlay[i+1][j]>=8)rez=true;
				//��������� ������ ��� �����
				break;
				}
						
						//������ �����
						//���������,��� ����� ������� �������
				if(testMasPoz(i,j-1)&&(masPlay[i][j-1]<=4) &&(masPlay[i][j-1]!=-2)) {
					//������ �������
					masPlay[i][j-1]+=7;
					//���������,��� ����
					testUbit(masPlay,i,j-1);
					//���� ��������� ���������
					if(masPlay[i][j-1]>=8)rez = true;
					//��������� ����� ��� �����
					break;
				}		
				//���� ������ ������
				//���������,��� ����� ������� �������
				else if(testMasPoz(i,j+1)&&(masPlay[i][j+1]<=4)&&(masPlay[i][j+1]!=-2)) {
					//������ �������
					masPlay[i][j+1]+=7;
					//���������,��� ����
					testUbit(masPlay,i,j+1);
					//���� ��������� ���������
					if(masPlay[i][j+1]>=8)rez = true;
					//��������� ����� ��� �����
					break;
				}
				}
				}
			}
		
			//���� �� ���� ������� � ������� ������
			if(flag == false){
				//������  100 ��������� ������� �������� � ��������� �����
				for(int l = 1; l<=100;l++) {
					// ������� ��������� �������  �� ������� ����
					int i = (int)(Math.random()*10);
					int j = (int)(Math.random()*10);
					//��������� ��� ����� ������� �������
					if((masPlay[i][j]<=4)&&(masPlay[i][j]!=-2)) {
						//������ �������
						masPlay[i][j]+=7;
						//��������� ��� ����
						testUbit(masPlay,i,j);
						//���� ��������� ���������
						if(masPlay[i][j]>=8) 
							rez = true;
							flag = true;
							//��������� ����
							break;
						}
							
					}
					//���� �������� ��� �� ����
					if(flag == false) {
						//�������� ��������� ������ �� ������ �� �����
						
							//_for2:
								for(int i = 0; i<10; i++) {
							for(int j = 0; j<10; j++) {
								//��������� ��� ����� ������� �������
								if((masPlay[i][j]<=4)&&(masPlay[i][j]!=-2)) {
									//������ �������
									masPlay[i][j]+=7;
									//��������� ��� ����
									testUbit(masPlay,i,j);
									//������������� ���������
									if(masPlay[i][j]>=8)
									rez = true;
									//��������� ����� ��� �����
									break;
									
								}
							}
						}
					}
				}
				//��������� ����� ����
				testEndGame();
				//����������� ���������
				return rez;
			}
		
		//���������� ���� ������� ��������� ��������� �������
		private void setOkrPodbit(int[][] mas,int i,int j) {
			//���� �� ���������� ����� �� ������� ������� � � ������ ������� ��������
			if(testMasPoz(i,j)==true)
			{
				//������������� ���������� �������
				if((mas[i][j]==-1)||(mas[i][j]==6))mas[i][j]--;
			}
		}
		//��������� ����� ������ ��������� ������
		private void okrPodbit(int[][]mas,int i,int j) {
			setOkrPodbit(mas,i-1,j-1);//������ �����
			setOkrPodbit(mas,i-1,j);//������
			setOkrPodbit(mas,i-1,j+1);//������ ������
			setOkrPodbit(mas,i,j+1);//������
			setOkrPodbit(mas,i+1,j+1);//�����
			setOkrPodbit(mas,i+1,j-1);//�����
			setOkrPodbit(mas,i,j-1);//�����
		}
		//�������� ��������� ����
		private void testEndGame() {
			
			//�������� ����� = 15*4+16*2*3+17*3*2+18*4	
			//��������, ����� ��� ������ �����
			int testNumber = 330;
			int kolComp = 0;//����� ������ ����� ����������
			int kolPlay = 0;//����� ������ ����� ������
			//���������� ��� �������� ����� ���� ��������
			for(int i =0; i<10;i++) {
				for(int j = 0; j<10; j++) {
					//��������� �������� ������ ������
					if(masPlay[i][j]>=15)kolPlay+=masPlay[i][j];
					//��������� �������� ������ ����������
					if(masComp[i][j]>=15)kolComp+=masComp[i][j];	
				}
			}
			if(kolPlay==testNumber)endg=2;//���� ������� �����
			else if(kolComp==testNumber) endg=1;//���� ������� ���������	
		}
		// �������� �������� �� ������� �������
		private boolean testMasPoz(int i, int j) {
			if (((i >= 0) && (i <= 9)) && ((j >= 0) && (j <= 9))) {
				return true;
			} else
				return false;
		}

		// ������ �������� � ������ � �������� ������ �������
		private void setMasValue(int[][] mas, int i, int j, int val) {
			// ���� �� ���������� ����� �� ������� �������
			if (testMasPoz(i, j) == true) {
				// ���������� �������� � ������
				mas[i][j] = val;
			}
		}
		// ���������� ���� ������� ��������
		private void setOkr(int[][] mas, int i, int j, int val) {
			// ���� �� ���������� ����� �� ������� ������� �� ������ ������� ��������
			if (testMasPoz(i, j) && (mas[i][j] == 0))
				// ������������� ����������� ��������
				setMasValue(mas, i, j, val);
		}
		// ��������� ����� ������ ������
		private void okrBegin(int[][] mas, int i, int j, int val) {
			setOkr(mas, i - 1, j - 1, val);// ������ �����
			setOkr(mas, i - 1, j, val);// ������
			setOkr(mas, i - 1, j + 1, val);// ������ ������
			setOkr(mas, i, j + 1, val);// ������
			setOkr(mas, i + 1, j + 1, val);// ����� ������
			setOkr(mas, i + 1, j, val);// �����
			setOkr(mas, i + 1, j - 1, val);// ����� �����
			setOkr(mas, i, j - 1, val);// �����
		}
		// �������� ���������
		private void okrEnd(int[][] mas) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					// ���� �������� �������� ������� -2,�� �������� ��� �� -1
					if (mas[i][j] == -2)
						mas[i][j] = -1;
				}
			}
		}
		// �������� ������� ������������� ��������
		private void make1P(int[][] mas) {
			// ���� ������ ������ ���� - ��� ������� ��������
			for (int k = 1; k <= 4; k++) {
				// ������ ���� while
				while (true) {
					// ������� ��������� ������� �� ������� ����
					int i = (int) (Math.random() * 10);
					int j = (int) (Math.random() * 10);
					// ��������� ��� ��� ������ ��� � ����� ���������� �������
					if (mas[i][j] == 0) {
						// ��������� ������������ �������
						mas[i][j] = 1;
						// ��������� ���������
						okrBegin(mas, i, j, -1);
						// ��������� ����
						break;
					}
				}
			}
		}
		// �������� ������ ��� ����������� ���������� � ��� ������ �������
		private boolean testNewPaluba(int[][] mas, int i, int j) {
			// ���� ����� �� ������� �������
			if (testMasPoz(i, j) == false)
				return false;
			// ���� � ���� ������ 0 ��� -2,�� ��� ��� ��������
			if ((mas[i][j] == 0) || (mas[i][j] == -2))
				return true;
			return false;
		}
		
	// �������� ������� � ����������� �������� �� 2-� �� 4-�
	private void make4P(int[][] mas, int kolPaluba) {
		// ������ ���
		while (true) {
			boolean flag = false;
			// ��������� ������ �������
			int i = 0, j = 0;
			// �������� ������ ������ - ������ �������
			// ��������� ��������� ������
			i = (int) (Math.random() * 10);
			// �������� ��������� �������
			j = (int) (Math.random() * 10);
			
			// �������� ��������� ����������� ���������� �������
			// 0 - �����, 1- ������, 2- ����, 3 - �����
			int napr = (int) (Math.random() * 4);
			if (testNewPaluba(mas, i, j) == true) {
				if (napr == 0)// ����
				{
					// ���� ����� ����������� ������
					if (testNewPaluba(mas, i - (kolPaluba - 1), j) == true)
						flag = true;
				} else if (napr == 1)// ������
				{
					// ���� ����� ����������� ������
					if (testNewPaluba(mas, i, j + (kolPaluba - 1)) == true)
						flag = true;
				} else if (napr == 2)// ����
				{
					// ���� ����� ����������� ������
					if (testNewPaluba(mas, i + (kolPaluba - 1), j) == true)
						flag = true;
				} else if (napr == 3)// �����
				{
					// ���� ����� ����������� ������
					if (testNewPaluba(mas, i, j - (kolPaluba - 1)) == true)
						flag = true;
				}
			}
			if (flag == true) {
				// �������� � ������ ����� �����
				mas[i][j] = kolPaluba;
				// �������� ����� ��������
				okrBegin(mas, i, j, -2);

				if (napr == 0)// �����
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// �������� � ������ ����� �����
						mas[i - k][j] = kolPaluba;
						// �������� ����� ��������
						okrBegin(mas, i - k, j, -2);
					}
				} else if (napr == 1)// ������
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// �������� � ������ ����� �����
						mas[i][j] = kolPaluba;
						// �������� ����� ��������
						okrBegin(mas, i, j + k, -2);
					}
				} else if (napr == 2)// ����
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// �������� � ������ ����� �����
						mas[i + k][j] = kolPaluba;
						// �������� ����� ��������
						okrBegin(mas, i + k, j, -2);
					}
				} else if (napr == 3)// ������
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// �������� � ������ ����� �����
						mas[i][j - k] = kolPaluba;
						// �������� ���� ��������
						okrBegin(mas, i, j - k, -2);
					}
				}
				break;
			}
		}
		// �������� ���������
		okrEnd(mas);
	}
}



	// ��������� ������ ���������������� �������
/**	private void make4P(int[][] mas) {
		// ���������� ���� �������
		int i = 0, j = 0;
		// �������� ������ ������ - ������ �������,��������� ��������� ������
		i = (int) (Math.random() * 10);
		// ��������� ��������� �������
		j = (int) (Math.random() * 10);

		// �������� � ������ ����� 4
		mas[i][j] = 4;

		// �������� ����� ��������
		okrBegin(mas, i, j, -2);
		// �������� ��������� ���������� ���������� �������
		// 0-�����,1-������,2-����,3-�����
		int napr = (int) (Math.random() * 4);

		if (napr == 0)// �����
		{
			// ���� ����� �� ������� �������
			if (testNewPaluba(mas, i - 3, j) == false)
				napr = 2;// ������ ����
		} else if (napr == 1)// ������
		{
			// ���� ����� �� ������� �������
			if (testNewPaluba(mas, i, j + 3) == false)
				napr = 3;// ������ �� �����

		} else if (napr == 2)// ����
		{
			// ���� ������ �� ������� �������
			if (testNewPaluba(mas, i + 3, j) == false)
				napr = 0;// ������ �� �����

		} else if (napr == 3)// �����
		{
			// ���� ����� �� ������� �������
			if (testNewPaluba(mas, i, j - 3) == false)
				napr = 1;// ������ �� ������
		}

		if (napr == 0)// �����
		{
			// �������� � ����� ����� 4
			mas[i - 3][j] = 4;
			// �������� ����� ��������
			okrBegin(mas, i - 3, j, -2);
			// �������� � ������ ����� ������ 4
			mas[i - 2][j] = 4;
			// �������� ����� ��������
			okrBegin(mas, i - 2, j, -2);
			// �������� � ������ ����� ������ 4
			mas[i - 1][j] = 4;
			// �������� ����� ��������
			okrBegin(mas, i - 1, j, -2);
		} else if (napr == 1)// ������
		{
			// �������� � ������ ����� 4
			mas[i][j + 3] = 4;
			// �������� ��� ����� ��������
			okrBegin(mas, i, j + 3, -2);
			// �������� � ������ ����� ������ 4
			mas[i][j + 2] = 4;
			// �������� ����� ���������
			okrBegin(mas, i, j + 2, -2);
			// �������� � ������ ����� 4
			mas[i][j + 1] = 4;
			// �������� ����� ��������
			okrBegin(mas, i, j + 1, -2);
		} else if (napr == 2)// ����
		{
			mas[i + 3][j] = 4;
			okrBegin(mas, i + 3, j, -2);
			mas[i + 2][j] = 4;
			okrBegin(mas, i + 2, j, -2);
			mas[i + 1][j] = 4;
			okrBegin(mas, i + 1, j, -2);

		} else if (napr == 3)// �����
		{
			mas[i][j - 3] = 4;
			okrBegin(mas, i, j - 3, -2);
			mas[i][j - 2] = 4;
			okrBegin(mas, i, j - 2, -2);
			mas[i][j - 1] = 4;
			okrBegin(mas, i, j - 1, -2);
		}
		// �������� ���������
		okrEnd(mas);

	}**/
	
	
	
	



