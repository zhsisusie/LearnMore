package aop.spring.aspectTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import aop.spring.aspect.Foo;
import aop.spring.aspect.PerformanceTraceAspect;

public class aspectProgrammingTest {
	@Test
	public void test() {
		AspectJProxyFactory weaver=new AspectJProxyFactory();
		weaver.setProxyTargetClass(true);
		weaver.setTarget(new Foo());
		weaver.addAspect(PerformanceTraceAspect.class);
		Object proxy=weaver.getProxy();
		((Foo)proxy).method1();
		((Foo)proxy).method2();
	}
}
