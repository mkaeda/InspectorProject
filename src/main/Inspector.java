package main;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Inspector {

	public void inspect(Object obj, boolean recursive) {
		Class<?> objClass = obj.getClass();

		// Get name of the declaring class.
		System.out.println(objClass.getName());

		Class<?> superClass;
		if ((superClass = objClass.getSuperclass()) != null) {
			// Get name of the immediate superclass.
			System.out.println(superClass.getName());
		}

		Class<?>[] interfaces = objClass.getInterfaces();
		if (interfaces.length > 0) {
			// Get name of the interfaces the class implements.
			for (Class<?> c : interfaces) {
				System.out.println(c.getName());
			}
		}

		// Get methods the class declares.
		Method[] declaredMethods = objClass.getDeclaredMethods();

		if (declaredMethods.length > 0) {
			// Sort by method name to ensure order for testing.
			Arrays.sort(declaredMethods, Comparator.comparing(Method::getName));
			
			// For each method, find the: exceptions thrown, parameter types, return type,
			// and modifiers.
			Class<?>[] exceptions;
			Parameter[] parameters;
			List<String> names = new ArrayList<>();
			for (Method method : declaredMethods) {
				System.out.print(Modifier.toString(method.getModifiers()) + " ");
				System.out.print(method.getReturnType().getTypeName() + " ");
				System.out.print(method.getName() + "(");

				parameters = method.getParameters();
				for (Parameter p : parameters) {
					names.add(p.getType().getTypeName());
				}
				printList(names, ", ");
				names.clear();
				System.out.print(")");

				exceptions = method.getExceptionTypes();
				if (exceptions.length > 0) {
					System.out.print(" throws ");
					for (Class<?> ex : exceptions) {
						names.add(ex.getName());
					}
					printList(names, ", ");
					names.clear();
				}

				System.out.println();
			}
		}

		// Get the constructors the class declares.
		// For each constructor, find the: parameter types and modifiers.

		// Get the fields the class declares. For each, find the type, modifiers, and
		// current value.

		// If the field is an object reference, and recursive is set to false,
		// print the “reference value” directly (the name of the object’s class plus the
		// object’s “identity hash code”).

		// Else, make a recursive call on that object.

		// Traverse the inheritance hierarchy to find all the methods, constructors,
		// fields, and field values that each superclass and super-interface declares.
		// Be sure you can also handle any array you might encounter, printing out its
		// name, component type, length, and all its contents.
	}

	private void printList(List<String> list, String delimiter) {
		System.out.print(String.join(delimiter, list));
	}
}
