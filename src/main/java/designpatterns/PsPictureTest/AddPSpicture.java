package designpatterns.PsPictureTest;

/**
 *  装饰者主题  抽象类： 也可以是接口
 * @Auth liran
 * @Create 2018/9/3 下午6:59
 * @Param
 * @Return
 * @Description
 */
public abstract class AddPSpicture implements  IPSpicture{

    public IPSpicture ipSpicture;


    public AddPSpicture(IPSpicture ips){
        ipSpicture=ips;
    }


}
