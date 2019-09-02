package lab4;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("people2")//생략시 클래스 이름과 동일하게. People(default name)
public class People {
	String name;
	String phone;
	
	//AutoWired - byType
	//@Autowired
	//Resource - byName
	@Resource
	Car car;	//lab4.xml에서 지정한 값이 들어옴. id명이 같으니까
	List<String> major;
	List<License> licenses;
	
	public People() {
		
	}

	public People(String name, String phone, Car car) {
		super();
		this.name = name;
		this.phone = phone;
		this.car = car;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public List<String> getMajor() {
		return major;
	}
	
	public void setMajor(List<String> major) {
		this.major = major;
	}

	public List<License> getLicenses() {
		return licenses;
	}

	@Autowired
	public void setLicenses(List<License> licenses) {
		this.licenses = licenses;
	}

	@Override
	public String toString() {
		return "People [name=" + name + ", phone=" + phone + ", car=" + car + ", major=" + major + ", licenses="
				+ licenses + "]";
	}

	
}
