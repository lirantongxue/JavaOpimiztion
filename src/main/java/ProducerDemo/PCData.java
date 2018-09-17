package ProducerDemo;

/**
 * :
 * 不变模式
 *
 * @auther liran
 * @create 2018-09-17-上午8:53
 */
public final class PCData {


    private final int intData;


    public PCData(int intData) {
        this.intData = intData;
    }


    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}
