package designpatterns.PsPictureTest;

/**
 * 手指美化:
 *
 * @auther liran
 * @create 2018-09-03-下午7:18
 */
public class FingerPSpicture implements AddPSpictureInterface {

    public IPSpicture ipSpicture;


    FingerPSpicture(IPSpicture ips){
        initIpspicture(ips);
    }

    @Override
    public void initIpspicture(IPSpicture ips) {
        ipSpicture=ips;
    }

    @Override
    public String psPicture() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("进行手指头美化");
        stringBuilder.append(ipSpicture.psPicture());
        return stringBuilder.toString();
    }
}
