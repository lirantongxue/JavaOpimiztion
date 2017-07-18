package AlgorithmDateStructure.SequenceListDemo;

//学生类
public class Students {

	private String sid;// 学号
	private String name;// 姓名
	private String gender;// 性别
	private int age;// 年龄

	public Students()
	{

	}

	public Students(String sid,String name,String gender,int age)
	{
		this.sid = sid;
		this.name = name;
		this.gender = gender;
		this.age =age;
	}

	public String toString()
	{
		return "学号："+this.getSid()+" 姓名："+this.getName()+" 性别："+this.getGender()+" 年龄:"+this.getAge();
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

