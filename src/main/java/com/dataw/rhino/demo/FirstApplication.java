package com.dataw.rhino.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author Kinyi_Chan
 * @since 2018-11-23.
 */

//@EnableSwagger2
//@SpringBootApplication
public class FirstApplication {

	public static void main(String[] args) throws Exception {
//		SpringApplication.run(FirstApplication.class, args);

//		People people = new People();
//		people.setName("abc");
//
//		Field name = people.getClass().getDeclaredField("name");
//		name.setAccessible(true);
//		System.out.println(name.get(people));
//		System.out.println(people);
//		System.out.println(name);

		Class<?> clazz = Class.forName("com.dataw.rhino.demo.People");

		Constructor<?> constructor = clazz.getDeclaredConstructor();
		constructor.setAccessible(true);
		People instance = (People) constructor.newInstance();
		instance.setName("abcd");

		Field name = clazz.getDeclaredField("name");
		name.setAccessible(true);
		System.out.println(name.get(instance));
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//
//		};
//	}
}
