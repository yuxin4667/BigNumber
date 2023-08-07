import java.util.*;

class num
{
	int n;
	int carry;
	num prev;
	num next;
	num(int i, int c, num prev, num ptr)
	{
		this.n=i;
		this.carry=c;
		this.prev=prev;
		this.next=ptr;
	}
}
public class BigNumber 
{
	static Scanner sc=new Scanner(System.in);
	static String n1;
	static String n2;
	static int mcarry=0;
	public static void add()//設function:add
	{

		System.out.print("輸入兩正整數\n");
	    System.out.print("輸入n1=");
		n1=sc.nextLine();
		System.out.print("輸入n2=");
		n2=sc.nextLine();
		while(n1.charAt(0)=='-' || n2.charAt(0)=='-')
		{
			System.out.print("輸入有誤\n");
		    System.out.print("輸入n1=");
			n1=sc.nextLine();
			System.out.print("輸入n2=");
			n2=sc.nextLine();
		}

		num root1=new num(0, 0, null, null);
		num root2=new num(0, 0, null, null);
		num root=new num(0, 0, null, null);
		num ptemp1=root1, temp1=ptemp1;
		num ptemp2=root2, temp2=ptemp2;
		num temp=root, ptemp=root;
		for(int i=n1.length()-1;i>=0;i--)
		{
			ptemp1=temp1;
			getnum1(temp1, i, ptemp1);
			temp1=temp1.next;
			
		}
		for(int i=n2.length()-1;i>=0;i--)
		{
			ptemp2=temp2;
			getnum2(temp2, i, ptemp2);
			temp2=temp2.next;	
		}

		temp1=root1.next;
		temp2=root2.next;
		ptemp=root;
		while(temp1!=null && temp2!=null)
		{
			ptemp=temp;
			plusnum(temp, temp1, temp2, ptemp);
			ptemp1=temp1;
			temp1=temp1.next;
			ptemp2=temp2;
			temp2=temp2.next;
			temp=temp.next;
		}

	    if(temp1==null)
	    {
	    	while(temp2!=null)
			{
				ptemp=temp;
				temp.next=new num((temp2.n+ptemp.carry)%10, (temp2.n+ptemp.carry)/10, ptemp, null);
				ptemp2=temp2;
				temp2=temp2.next;
				temp=temp.next;
			}
	    }
	    if(temp2==null)
	    {
	    	while(temp1!=null)
			{
				ptemp=temp;
				temp.next=new num((temp1.n+ptemp.carry)%10, (temp1.n+ptemp.carry)/10, ptemp, null);
				ptemp1=temp1;
				temp1=temp1.next;
				temp=temp.next;
			}
	    }

	    
	    System.out.print("n1 + n2 = ");
	    if(temp.carry!=0)
	    {
	    	System.out.print(temp.carry);
	    }
	    while (temp.prev!=null)
	    {
	    	System.out.print(temp.n);
	    	temp=temp.prev;
	    }
	    
	}
	
	public static void minus()//設function:minus
	{

		System.out.print("輸入兩正整數\n");
	    System.out.print("輸入n1=");
		n1=sc.nextLine();
		System.out.print("輸入n2=");
		n2=sc.nextLine();
		while(n1.charAt(0)=='-' || n2.charAt(0)=='-')
		{
			System.out.print("輸入有誤\n");
		    System.out.print("輸入n1=");
			n1=sc.nextLine();
			System.out.print("輸入n2=");
			n2=sc.nextLine();
		}
		System.out.print("n1 - n2 = ");

		num root1=new num(0, 0, null, null);
		num root2=new num(0, 0, null, null);
		num root=new num(0, 0, null, null);
		num ptemp1=root1, temp1=ptemp1;
		num ptemp2=root2, temp2=ptemp2;
		num temp=root, ptemp=root;
		for(int i=n1.length()-1;i>=0;i--)
		{
			ptemp1=temp1;
			getnum1(temp1, i, ptemp1);
			temp1=temp1.next;
			
		}
		for(int i=n2.length()-1;i>=0;i--)
		{
			ptemp2=temp2;
			getnum2(temp2, i, ptemp2);
			temp2=temp2.next;	
		}

		ptemp=root;
		
		if(n1.length()<n2.length())
		{
			temp1=root2.next;
			temp2=root1.next;
			num t;
			t=ptemp1;
			ptemp1=ptemp2;
			ptemp2=t;
	    	System.out.print("-");
		}
		else if(n1.length()==n2.length()) 
		{
			while(temp1.n==temp2.n)
			{
				temp1=temp1.prev;
				temp2=temp2.prev;
				if(temp1.prev==null && temp2.prev==null)
					break;
			}
			if(temp1.n<temp2.n)
			{
				temp1=root2.next;
				temp2=root1.next;
				num t;
				t=ptemp1;
				ptemp1=ptemp2;
				ptemp2=t;
				System.out.print("-");
			}
			else
			{
				temp1=root1.next;
				temp2=root2.next;
			}
				
		}
		else
		{
			temp1=root1.next;
			temp2=root2.next;
		}

		ptemp=root;
		while(temp1!=null && temp2!=null)
		{
			ptemp=temp;
			minusnum(temp, temp1, temp2, ptemp);
			ptemp1=temp1;
			temp1=temp1.next;
			ptemp2=temp2;
			temp2=temp2.next;
			temp=temp.next;
		}

	    if(temp2==null)
	    {
	    	while(temp1!=null)
			{
				ptemp=temp;
				temp.next=new num((10+temp1.n+ptemp.carry)%10, (temp1.n+ptemp.carry)/10, ptemp, null);
				ptemp1=temp1;
				temp1=temp1.next;
				temp=temp.next;
			}
	    }

	    if(ptemp1.n+ptemp.carry==0)
	    {
	    	temp=temp.prev;
	    }
	    while (temp.prev!=null)
	    {
	    	System.out.print(temp.n);
	    	temp=temp.prev;
	    } 
	}
	public static void getnum1(num temp, int i, num ptemp)
	{
			temp.next=new num(n1.charAt(i)-48, 0, ptemp, null);
	}
	public static void getnum2(num temp, int i, num ptemp)
	{
			temp.next=new num(n2.charAt(i)-48, 0, ptemp, null);
	}
	public static void plusnum(num temp, num temp1, num temp2, num ptemp)
	{
		temp.next=new num((temp1.n+temp2.n+ptemp.carry)%10, (temp1.n+temp2.n+ptemp.carry)/10, ptemp, null);
	}
	public static void minusnum(num temp, num temp1, num temp2, num ptemp)
	{
		if(temp1.n<temp2.n)
			temp.next=new num((10+temp1.n-temp2.n+ptemp.carry)%10, -1, ptemp, null);
		else if(temp1.n+ptemp.carry<temp2.n)
			temp.next=new num((temp1.n-temp2.n+ptemp.carry)%10, ptemp.carry--, ptemp, null);
		else 
			temp.next=new num((temp1.n-temp2.n+ptemp.carry)%10, 0, ptemp, null);
	}
	public static void main(String[] args) 
	{
		int k = 0;
		while(true)
		{
			System.out.print("\n要進行加法運算請輸入1；進行減法運算請輸入2；結束請按3: ");
		    k=sc.nextInt();
		    String a=sc.nextLine();
		    if (k == 1)
		        add();
		    else if(k==2)
		    	minus();
		    else
		    	break;
		}
		sc.close();

	}

}
