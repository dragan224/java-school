package refleksija_anotacija;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class AssocMap {
	public AssocMap() {
		// TODO Auto-generated constructor stub
		data = new ArrayList<>();
	}
	
	public void store(Object object) {
		Field field = findFieldByAnnotation(Key.class, object);
		if (findByPrimaryKey(field) == null) {
			data.add(new Pair<Object, Object>(field, object));
			
			executeMethod(AfterStore.class, object);
			
			try {
				field = findFieldByAnnotation(Date.class, object);
				field.setAccessible(true);
				field.set(object, new java.util.Date());
				field.setAccessible(false);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void remove(Object object) {
		for (Pair<?, ?> par: data) {
			if (par.getR().equals(object)) {
				executeMethod(BeforeRemove.class, object);
				data.remove(par);
				break;
			}
		}
		
	}
	public Object findByPrimaryKey(Object key) {
		for (Pair<?, ?> par: data) {
			try {
				if (((Field)par.getL()).get(par.getR()).equals(key)) {
					return par.getR();
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private Field findFieldByAnnotation(Class<? extends Annotation> annotation, Object object) {
		for (Field field : object.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(annotation)) {
				return field;
			}
		}
		return null;
	}
	
	// Izvrsava metodu sa datom annotacijom u objecty object.
	private void executeMethod(Class<? extends Annotation> annotation, Object object) {
		for (Method method : object.getClass().getDeclaredMethods()) {
			try {
				if (method.isAnnotationPresent(annotation)) {
					method.invoke(object, null);
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private ArrayList<Pair<Object, Object>> data;
	
	boolean isEmpty() {
		return data.size() == 0 ? true : false;
	}
}
