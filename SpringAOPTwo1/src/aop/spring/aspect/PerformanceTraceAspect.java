package aop.spring.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class PerformanceTraceAspect {

	private final Log logger=LogFactory.getLog(PerformanceTraceAspect.class);
	@Pointcut("execution(public void *.method1())||execution(public void *.method2())")
	public void pointcutName(){}
	
	@Around("pointcutName()")
	public Object performanceTrace(ProceedingJoinPoint joinpoint)throws Throwable
	{
		try{
			System.out.println("start.......");
			return joinpoint.proceed();
		}finally{
			System.out.println("stop......");
			if(logger.isInfoEnabled()){
				logger.info("PT in method["+joinpoint.getSignature().getName()+"]>>>>>");
			}
		}
	}
}
