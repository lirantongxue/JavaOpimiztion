package designpatterns.PsPictureTest;

/**
 * 测试装饰者模式:
 *
 * @auther liran
 * @create 2018-09-03-下午7:08
 *
 *
 *   一： 定义核心 基础功能接口。  并创建 核心功能实现类  PSpictureImpl
 *   二： 创建  装饰者 接口或者 抽象类 并继承 基础核心接口。 目的是 所有装饰者实现。都是统一的基础功能接口。多态。 很重要。
 *        并且内联 基础功能接口。为每个装饰者提供 初始化 核心实现类的基础入口。 很重哟。
 *
 *   三： 创建 装饰者实现类 。
 *
 *
 */
public class Test {
    public static void main(String[] args) {
        //抽象类主题进行测试
        IPSpicture ipSpicture = new FootPSpicture(new FacePSpicture(new PSpictureImpl()));
        System.out.println(ipSpicture.psPicture());
        //接口主题进行测试
        IPSpicture ipSpicture1 =new FootPSpicture(new FingerPSpicture(new PSpictureImpl()));
        System.out.println(ipSpicture1.psPicture());

    }
}
