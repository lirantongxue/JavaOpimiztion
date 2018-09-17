package FutureImplDemo;

public class Test {
	public static void main(String[] args) {
		Client client = new Client();
		Data data = client.request("异步银行模拟业务");
		System.out.println("业务实现");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("银行业务返回"+data.getResult());
		
		
	}

}
