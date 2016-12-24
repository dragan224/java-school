package aspekti;

import java.lang.reflect.Field;

public aspect DependencyInjectionAspect {
	pointcut constructorTestClass() : initialization (TestClass.new(..));
	
	before() : constructorTestClass() {
		Object object = thisJoinPoint.getTarget();
		for (Field field: object.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(ManagedBean.class)) {
				field.setAccessible(true);
				try {
					// Object instance = field.getType().getDeclaredConstructor(field.getType()).newInstance(42);
					Object instance = field.getType().newInstance(); 
					field.set(object, instance);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				field.setAccessible(false);
			}
		}
	}
}
