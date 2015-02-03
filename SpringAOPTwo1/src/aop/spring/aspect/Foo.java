package aop.spring.aspect;

public class Foo {

	public void method1(){
		System.out.println("method1 execution");
	}
	public void method2(){
		System.out.println("method2 execution");
	}
	
	public void method3(){
		System.out.println("method3 execution");
	}
	
	public void doSomething(String str){
		System.out.println(str+"do something");
	}
}
