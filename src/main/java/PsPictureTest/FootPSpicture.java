package PsPictureTest;

/**
 * 脚步优化:
 *
 * @auther liran
 * @create 2018-09-03-下午7:06
 */
public class FootPSpicture extends AddPSpicture {


    public FootPSpicture(IPSpicture ips) {
        super(ips);
    }

    @Override
    public String psPicture() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(ipSpicture.psPicture());
        stringBuilder.append("进行脚步优化");
        return stringBuilder.toString();
    }
}
