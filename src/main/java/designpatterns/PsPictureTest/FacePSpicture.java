package designpatterns.PsPictureTest;

/**
 * 面部功能装饰:
 *
 * @auther liran
 * @create 2018-09-03-下午7:02
 */
public class FacePSpicture extends AddPSpicture {


    public FacePSpicture(IPSpicture ips) {
        super(ips);
    }

    @Override
    public String psPicture() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("在脸部进行优化");
        stringBuilder.append(ipSpicture.psPicture());
        return stringBuilder.toString();
    }
}
