package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
		printSuperclass(objClass, obj, recursive);
		printInterfaces(objClass);
		printDeclaredMethods(objClass);
		printDeclaredConstructors(objClass);
		printDeclaredFields(objClass, obj, recursive);
		
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
	
	private void printSuperclass(Class<?> objClass, Object obj, boolean recursive)
	{
		Class<?> superClass;
		if ((superClass = objClass.getSuperclass()) != null) {
			// Get name of the immediate superclass.
			System.out.println(superClass.getName());
			if (recursive)
			{
				if (Modifier.isAbstract(superClass.getModifiers()))
				{
					// Instantiation not possible
					printSuperclass(superClass, obj, recursive);
					printInterfaces(superClass);
					printDeclaredMethods(superClass);
					printDeclaredConstructors(superClass);
					printDeclaredFields(superClass, obj, recursive);
				}
				else
				{
					Constructor<?>[] constructors = superClass.getConstructors();
					if (constructors.length > 0)
					{
						boolean traversed = false;
						int i = 0;
						while (!traversed)
						{
							try {
								Constructor<?> ctor = constructors[i];
								inspect(ctor.newInstance(), recursive);
								traversed = true;
							} catch (InstantiationException e) {
								i++;
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								i++;
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								i++;
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								i++;
								e.printStackTrace();
							}							
						}
					}
					else
					{
						// Object instantiation not possible.
						printSuperclass(superClass, obj, recursive);
						printInterfaces(superClass);
						printDeclaredMethods(superClass);
						printDeclaredConstructors(superClass);
						printDeclaredFields(superClass, obj, recursive);
					}
				}
			}
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
		Method[] methods = objClass.getDeclaredMethods();

		if (methods.length > 0) {
			// Sort by method name to ensure order for testing.
			Arrays.sort(methods, Comparator.comparing(Method::getName));
			
			// For each method, find the: exceptions thrown, parameter types, return type,
			// and modifiers.
			Arrays.stream(methods)
				.map(Method::toString)
				.forEach(System.out::println);
		}
	}
	
	private void printDeclaredConstructors(Class<?> objClass)
	{
		// Get the constructors the class declares.
		Constructor<?>[] constructors = objClass.getDeclaredConstructors();
		// For each constructor, find the: parameter types and modifiers.
		Arrays.stream(constructors)
			.map(Constructor::toString)
			.forEach(System.out::println);
	}
	
	private void printDeclaredFields(Class<?> objClass, Object obj, boolean recursive)			
	{
		// Get the fields the class declares.		
		Field[] fields = objClass.getDeclaredFields();
		// For each, find the type, modifiers, and current value.
		Arrays.stream(fields).forEach(field -> {
	        try
	        {
	        	System.out.print(field.toString());
	            System.out.print(" = ");

	            field.setAccessible(true);
	            Object fieldObj = field.get(obj);
	            if (field.getType().isPrimitive() || !recursive)
	            {
	                System.out.println(fieldObj);
	            }
	            else
	            {
	            	inspect(fieldObj, recursive);
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
