package FutureImplDemo;



/**
 * 方法
 * @Auth liran
 * @Create 2018/9/14 下午7:14
 * @Param
 * @Return
 * @Description  核心业务实现
 */
public class RealData implements Data {


	
	protected final String result;

	public RealData(String para) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(para);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		result = sb.toString();
	}

	@Override
	public String getResult() {
		return result;
	}
	
	

}
