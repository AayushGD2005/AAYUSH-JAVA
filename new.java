package concepts;

public class StaticVar {
	String name;
	int age;
 static	String college="its";
	
public StaticVar(int age,String name){
	this.age=age;
	this.name=name;
}
public static void change() {
	String college="bits";
}
public void display() {
	System.out.println(name+age+college);
}
public static void main(String[] args) {
	StaticVar p1=new StaticVar(22,"aayush");
	p1.change();
	p1.display();
	
	
}


}