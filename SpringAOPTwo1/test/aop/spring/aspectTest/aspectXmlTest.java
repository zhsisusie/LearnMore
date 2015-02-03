package aop.spring.aspectTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import aop.spring.aspect.Foo;
public class aspectXmlTest {
	@Test
	public void test() {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("/beans.xml");
		Object proxy=ctx.getBean("target");
		((Foo)proxy).method1();
		((Foo)proxy).method2();
		((Foo)proxy).doSomething("zhsi...");
	}
}
