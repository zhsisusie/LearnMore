package aop.spring.aspectTest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class YourAspect {

	@Pointcut("execution(void method1())")
	private void method1Exec(){}
	
	@Pointcut("execution(void method2())")
	private void method2Exec(){}
	
	@Pointcut("execution(void method1())||execution(void method2())")
	public void bothMethodExec1(){}
	
	@Pointcut("method1Exec()||method2Exec()")
	public void bothMethodExec2(){}
	
	@Pointcut("execution(* *(String))")
	public void doSthExec(){}
	
	@Pointcut("execution(* *(..))")
	public void all(){}
	/**
	 * within()所指定的连接点最小范围只能是类
	 * @param joinpoint
	 * @return
	 * @throws Throwable
	 */
	@Around("within(aop.spring.aspect.Foo)")
	public Object performanceTrace(ProceedingJoinPoint joinpoint)throws Throwable
	{
		try{
			System.out.println("start.......");
			return joinpoint.proceed();
		}finally{
			System.out.println("stop......");
		}
	}
}
