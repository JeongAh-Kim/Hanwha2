package lab2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lab2.xml");
		//TV tv = (TV)context.getBean("stv");

		//Resource resource = new ClassPathResource("lab2.xml");
		//BeanFactory factory = new XmlBeanFactory(resource);

		TV tv = context.getBean("ltv", TV.class);
		tv.powerOn();
		tv.powerOff();
	}
}
