package lab3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lab3Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lab3.xml");

		Car c = context.getBean("car", Car.class);
		System.out.println(c.model);
		System.out.println(c.price);
		System.out.println(c);
		People p1 = context.getBean("people", People.class);
		System.out.println(p1);
		People p2 = context.getBean("people", People.class);
		System.out.println(p2);
		
		System.out.println(p1==p2);
		System.out.println(System.identityHashCode(p1));
		System.out.println(System.identityHashCode(p2));
	}

}
