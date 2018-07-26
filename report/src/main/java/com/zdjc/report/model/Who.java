package com.zdjc.report.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@Slf4j
@ToString
public class Who {
	
	public String name;
	
	public Integer age;

/*	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Who [name=" + name + ", age=" + age + "]";
	}
	*/
	public static void main(String[] args) {
		
		Who who  = new Who();
		
		log.info(who.toString());
}

}
