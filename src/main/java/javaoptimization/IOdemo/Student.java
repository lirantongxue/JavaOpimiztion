package javaoptimization.IOdemo;
/**
 * Created by liran on 17/8/11.
 */

import java.io.Serializable;

/**
 * 序列号测试:
 *
 * @auther liran
 * @create 2017-08-11-下午5:30
 */
public class Student implements Serializable{

    private static final long serialVersionUID = 6268347861913805461L;


    private String name;
    private int age;

    public Student(String name,int age){
        this.name=name;
        this.age=age;
    }

    public void greeting(){
        System.out.println("hello ,my name is "+name);
    }

    public String toString(){
        return "RealStudent["+name+","+age+"]";
    }
}
