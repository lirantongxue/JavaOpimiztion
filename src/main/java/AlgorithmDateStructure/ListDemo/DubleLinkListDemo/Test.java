package AlgorithmDateStructure.ListDemo.DubleLinkListDemo;


import java.util.Random;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DoubleLinkList list = new DoubleLinkList();
		Random a=new Random(1);
		for(int i=0;i<10;i++)
		{
			int temp=a.nextInt();
			list.insert(i, temp);
			System.out.print(temp+" ");
		}
		list.delete(5);
		System.out.println("\n------删除第五个元素之后-------");
		for(int i=0;i<list.size;i++)
		{
			System.out.print(list.get(i)+" ");
		}
	}

}



