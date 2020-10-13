package mb;
//������� �����������,����� ���������� ������ ����
public class game {
//������ ��� �������� ���� ������
	public int[][] masPlay;
	//����������� ������
	public game() {
		//������� ������ 10*10 - ������� ��� ������
		masPlay = new int[10][10];
	}	
	//������ ���� - ������ ����
	public void start() {
		//������� ������� ���� ������
		for(int i = 0; i<10; i++) {
			for(int j=0;j<10;j++) {
				masPlay[i][j]=0;
			}
		}
		//��������� ������������ �������� ��� �������� ���� ������
		make1P(masPlay);
		//�������� ���������������� �������.
		make4P(masPlay);
	}
	//�������� �������� �� ������� �������
	private boolean testMasPoz(int i,int j) {
		if(((i>=0) && (i<=9))&&((j>=0)&&(j<=9))) {
			return true;
		}
	else 
		return false;
	}
	
	//������ �������� � ������ � �������� ������ �������
	private void setMasValue(int[][] mas, int i,int j, int  val) {
		//���� �� ���������� ����� �� ������� �������
		if(testMasPoz(i,j)==true) {
			//���������� �������� � ������
			mas[i][j] = val;
		}
	}
	
	//���������� ���� ������� ��������
	private void setOkr(int[][] mas, int i, int j, int val) {
		//���� �� ���������� ����� �� ������� ������� �� ������ ������� ��������
		if(testMasPoz(i,j) && (mas[i][j]==0))
			//������������� ����������� ��������
			setMasValue(mas,i,j,val);
	}
	//��������� ����� ������ ������
	private void okrBegin(int[][]mas, int i, int j, int val) {
		setOkr(mas,i-1,j-1,val);//������ �����
		setOkr(mas,i-1,j,val);//������
		setOkr(mas,i-1,j+1,val);//������ ������
		setOkr(mas,i,j+1,val);//������
		setOkr(mas,i+1,j+1,val);//����� ������
		setOkr(mas,i+1,j,val);//�����
		setOkr(mas,i+1,j-1,val);//����� �����
		setOkr(mas,i,j-1,val);//�����
	}
	
	//�������� ������� ������������� ��������
	private void make1P(int[][]mas) {
		//���� ������ ������ ���� - ��� ������� ��������
		for(int k = 1; k<=4; k++) {
			//������ ���� while
			while(true) {
				//�������  ��������� ������� �� ������� ����
				int i = (int)(Math.random()*10);
				int j = (int)(Math.random()*10);
				//��������� ��� ��� ������ ��� � ����� ���������� �������
			 if(mas[i][j]==0) {
				 //��������� ������������ �������
				 mas[i][j]=1;
				 //��������� ���������
				 okrBegin(mas,i,j,-1);
				 //��������� ����
				 break;
			 }		
			}
		}
	}
	
	//�������� ���������
	private void okrEnd(int[][]mas) {
		for(int i =0; i<10; i++) {
			for(int j = 0; j<10; j++) {
				//���� �������� �������� ������� -2,�� �������� ��� �� -1
				if(mas[i][j]==-2)
					mas[i][j]=-1;
			}
		}
	}
	
	//�������� ������ ��� ����������� ���������� � ��� ������ �������
	private boolean testNewPaluba(int[][] mas, int i, int j) {
		//���� ����� �� ������� �������
		if(testMasPoz(i,j)==false)
			return false;
		//���� � ���� ������ 0 ��� -2,�� ��� ��� ��������
		if((mas[i][j]==0)||(mas[i][j]==-2))
			return true;
		return false;
	}
	
	//��������� ������ ���������������� ������� 
	private void make4P(int[][]mas) {
		//���������� ���� �������
		int i  = 0, j =0;
		//�������� ������ ������ - ������ �������,��������� ��������� ������
		i = (int)(Math.random()*10);
		//��������� ��������� �������
		j = (int)(Math.random()*10);
		
		//�������� � ������ ����� 4
		mas[i][j] = 4;
		
		//�������� ����� ��������
		okrBegin(mas,i,j,-2);
		//�������� ��������� ���������� ���������� ������� 0-�����,1-������,2-����,3-�����
		int napr=(int)(Math.random()*4);
		
		if(napr==0)//�����
		{
			//���� ����� �� ������� �������
			if(testNewPaluba(mas,i-3,j)==false)
				napr=2;//������ ����
		}
		else if(napr==1)//������
		{
			//���� ����� �� ������� �������
			if(testNewPaluba(mas,i,j+3)==false)
				napr=3;//������ �� �����
			
		}
		else if(napr==2)//����
		{
			//���� ������ �� ������� �������
			if(testNewPaluba(mas,i+3,j)==false)
				napr=0;//������ �� �����

		}
		else if(napr==3)//�����
		{
			//���� ����� �� ������� �������
			if(testNewPaluba(mas,i,j-3)==false)
				napr=1;//������ �� ������
		}
		
		if(napr==0)//�����
		{
			//�������� � ����� ����� 4
			mas[i-3][j]=4;
			//�������� ����� ��������
			okrBegin(mas,i-3,j,-2);
			//�������� � ������ ����� ������ 4
			mas[i-2][j]=4;
			//�������� ����� ��������
			okrBegin(mas,i-2,j,-2);
			//�������� � ������ ����� ������ 4
			mas[i-1][j]=4;
			//�������� ����� ��������
			okrBegin(mas,i-1,j,-2);
		}
		else if(napr==1)//������
		{
			//�������� � ������ ����� 4
			mas[i][j+3]=4;
			//�������� ��� ����� ��������
			okrBegin(mas,i,j+3,-2);
			//�������� � ������ ����� ������ 4
			mas[i][j+2]=4;
			//�������� ����� ���������
			okrBegin(mas,i,j+2,-2);
			//�������� � ������ ����� 4
			mas[i][j+1]=4;
			//�������� ����� ��������
			okrBegin(mas,i,j+1,-2);
		}
		else if(napr==2)//����
		{
			mas[i+3][j]=4;
			okrBegin(mas,i+3,j,-2);
			mas[i+2][j]=4;
			okrBegin(mas,i+2,j,-2);
			mas[i+1][j]=4;
			okrBegin(mas,i+1,j,-2);
			
		}
		else if(napr==3)//�����
		{
			mas[i][j-3]=4;
			okrBegin(mas,i,j-3,-2);
			mas[i][j-2]=4;
			okrBegin(mas,i,j-2,-2);
			mas[i][j-1]=4;
			okrBegin(mas,i,j-1,-2);
		}
		//�������� ���������
		okrEnd(mas);
		
	}
			
	
	
	
	
}

	




