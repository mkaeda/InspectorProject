package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Inspector {

	public void inspect(Object obj, boolean recursive)
	{
		Class<?> objClass = obj.getClass();
		// Print object introspection.
		printClass(objClass);
		printSuperclass(objClass);
		printInterfaces(objClass);
		printDeclaredMethods(objClass);
		printDeclaredConstructors(objClass);
		printDeclaredFields(obj, recursive);
		
		// TODO
		// Traverse the inheritance hierarchy to find all the methods, constructors,
		// fields, and field values that each superclass and super-interface declares.
		// Be sure you can also handle any array you might encounter, printing out its
		// name, component type, length, and all its contents.
	}
	
	private void printClass(Class<?> objClass)
	{
		// Print name of the declaring class.
		System.out.println(objClass.getName());
	}
	
	private void printSuperclass(Class<?> objClass)
	{
		Class<?> superClass;
		if ((superClass = objClass.getSuperclass()) != null) {
			// Get name of the immediate superclass.
			System.out.println(superClass.getName());
		}
	}
	
	private void printInterfaces(Class<?> objClass)
	{
		Class<?>[] interfaces = objClass.getInterfaces();
		if (interfaces.length > 0) {
			// Print names of the interfaces the class implements.
			Arrays.stream(interfaces).map(Class::getName).forEach(System.out::println);
			// TODO if recursive traverse hierarchy
		}
	}
	
	private void printDeclaredMethods(Class<?> objClass)
	{
		// Get methods the class declares.
		Method[] declaredMethods = objClass.getDeclaredMethods();

		if (declaredMethods.length > 0) {
			// Sort by method name to ensure order for testing.
			Arrays.sort(declaredMethods, Comparator.comparing(Method::getName));
			
			// For each method, find the: exceptions thrown, parameter types, return type,
			// and modifiers.
			Arrays.stream(declaredMethods).forEach(m -> {
	            printModifier(m.getModifiers());
	            printTypeName(m.getReturnType());
	            System.out.print(" " + m.getName());

	            Parameter[] parameters = m.getParameters();
	            printParameters(parameters);

	            Class<?>[] exceptions = m.getExceptionTypes();
	            if (exceptions.length > 0) {
	                printExceptions(exceptions);
	            }

	            System.out.println();
	        });
		}
	}
	
	private void printDeclaredConstructors(Class<?> objClass)
	{
		// Get the constructors the class declares.
		Constructor<?>[] constructors = objClass.getDeclaredConstructors();
		// For each constructor, find the: parameter types and modifiers.
		Arrays.stream(constructors).forEach(c -> {
			printModifier(c.getModifiers());
			
			Parameter[] parameters = c.getParameters();
			printParameters(parameters);

			Class<?>[] exceptions = c.getExceptionTypes();
			if (exceptions.length > 0) {
				printExceptions(exceptions);
			}
			System.out.println();
        });
	}
	
	private void printDeclaredFields(Object obj, boolean recursive)			
	{
		Class<?> objClass = obj.getClass();
		// Get the fields the class declares.		
		Field[] fields = objClass.getDeclaredFields();
		// For each, find the type, modifiers, and current value.
		Arrays.stream(fields).forEach(field -> {
	        try
	        {
	            printModifier(field.getModifiers());
	        
	            Class<?> fieldType = field.getType();
	            printTypeName(fieldType);
	            System.out.print(" = ");

	            field.setAccessible(true);
	            Object fieldObj = field.get(obj);
	            if (fieldType.isPrimitive() || !recursive)
	            {
	                // Print the primitive value or object “reference value”.
	                System.out.println(fieldObj);
	            }
	            else
	            {
	                // TODO Recursive inspect call on field object
	            }
	        }
	        catch (IllegalArgumentException | IllegalAccessException e)
	        {
	            e.printStackTrace();
	            // This should never happen!
	        }
	    });
	}
	
	private void printModifier(int modifier)
	{
		System.out.print(Modifier.toString(modifier) + " ");
	}
	
	private void printTypeName(Class<?> type)
	{
		System.out.print(type.getTypeName());
	}
	
	private void printParameters(Parameter[] parameters)
	{
		List<String> names = new ArrayList<>();
		System.out.print("(");
		for (Parameter p : parameters) {
			String type = p.getType().getTypeName();
			names.add(type);
		}
		printList(names, ", ");
		System.out.print(")");
	}
	
	private void printExceptions(Class<?>[] exceptions)
	{
		List<String> names = new ArrayList<>();
		System.out.print(" throws ");
		for (Class<?> ex : exceptions) {
			names.add(ex.getName());
		}
		printList(names, ", ");
	}

	private void printList(List<String> list, String delimiter) {
		System.out.print(String.join(delimiter, list));
	}
}
