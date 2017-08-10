package AlgorithmDateStructure.StackDemo;

public class TestStack {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s1 = "china";
        String s2 = "china";
        //凡是用new关键创建的对象，都是在堆内存中分配空间。
        String s3 = new String("china");

        //凡是new出来的对象，绝对是不同的两个对象。
        String s4 = new String("china");

        System.out.println(s1==s2);
        System.out.println(s1==s3);
        System.out.println(s3==s4);
        System.out.println(s3.equals(s4));

        System.out.println("\n-----------------\n");
      /*String很特殊，重写从父类继承过来的hashCode方法，使得两个
       *字符串如果内容相等，那么hashCode也相等。
       **/
        System.out.println(s3.hashCode());
        System.out.println(s4.hashCode());

        System.out.println("\n-----------------\n");
        System.out.println(System.identityHashCode(s3));
        System.out.println(System.identityHashCode(s4));

        System.out.println("\n-----------------\n");
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        System.out.println("\n-----------------\n");
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));

    }


}
