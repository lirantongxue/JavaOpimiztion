package AlgorithmDateStructure.SequenceListDemo;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SequenceList list = new SequenceList(100);
		try
		{
			list.insert(list.size, new Students("S0001","张三","男",18));
			list.insert(list.size, new Students("S0002","李四","男",19));
			list.insert(list.size, new Students("S0003","王五","女",21));

			for(int i=0;i<list.size;i++)
			{
				System.out.println(list.get(i));
			}

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}

}
