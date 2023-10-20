package main;

import java.lang.reflect.Array;
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
		System.out.println("{");
		printSuperclass(objClass, obj, recursive);
		printInterfaces(objClass, obj, recursive);
		printDeclaredMethods(objClass);
		printDeclaredConstructors(objClass);
		printDeclaredFields(objClass, obj, recursive);
	}
	
	private void printClass(Class<?> objClass)
	{
		System.out.println(objClass.getName());
	}
	
	private void printSuperclass(Class<?> objClass, Object obj, boolean recursive)
	{
		Class<?> superClass;
		if ((superClass = objClass.getSuperclass()) != null) {
			// Get name of the immediate superclass.
			System.out.print("SuperClass >> ");
			if (recursive)
			{
				if (Modifier.isAbstract(superClass.getModifiers()))
				{
					// Instantiation not possible
					printClass(superClass);
					System.out.println("{");
					printSuperclass(superClass, obj, recursive);
					printInterfaces(superClass, obj, recursive);
					printDeclaredMethods(superClass);
					printDeclaredConstructors(superClass);
					printDeclaredFields(superClass, obj, recursive);
					System.out.println("}");
				}
				else
				{
					Constructor<?>[] constructors = superClass.getConstructors();
					if (constructors.length > 0)
					{
						try 
						{
							inspect(constructors[0].newInstance(), recursive);
						} 
						catch (Exception e) 
						{
							System.out.println("err: " + e.getMessage());
						}
					}
					else
					{
						// Object instantiation not possible.
						printClass(superClass);
						System.out.println("{");
						printSuperclass(superClass, obj, recursive);
						printInterfaces(superClass, obj, recursive);
						printDeclaredMethods(superClass);
						printDeclaredConstructors(superClass);
						printDeclaredFields(superClass, obj, recursive);
						System.out.println("}");
					}
				}
				
			}
		}
	}
	
	private void printInterfaces(Class<?> objClass, Object obj, boolean recursive)
	{
		Class<?>[] interfaces = objClass.getInterfaces();
		if (interfaces.length > 0) {
			// Print names of the interfaces the class implements.
			Arrays.stream(interfaces).forEach(i -> {
				
				System.out.println("Interface >> " + i.toString() + " {");
				Class<?>[] superInterfaces = i.getInterfaces();
				// Traverse interface hierarchy.
				Arrays.stream(superInterfaces).forEach(si -> {
					printClass(si);
					printSuperclass(si, obj, recursive);
					printInterfaces(si, obj, recursive);
					printDeclaredMethods(si);
					printDeclaredConstructors(si);
					printDeclaredFields(si, obj, recursive);					
				});
				System.out.println("}");
			});
		}
	}
	
	private void printDeclaredMethods(Class<?> objClass)
	{
		System.out.println("Methods >> {");
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
		System.out.println("}");
	}
	
	private void printDeclaredConstructors(Class<?> objClass)
	{
		System.out.println("Constructors >> {");
		// Get the constructors the class declares.
		Constructor<?>[] constructors = objClass.getDeclaredConstructors();
		// For each constructor, find the: parameter types and modifiers.
		Arrays.stream(constructors)
			.map(Constructor::toString)
			.forEach(System.out::println);
		System.out.println("}");
	}
	
	private void printDeclaredFields(Class<?> objClass, Object obj, boolean recursive)			
	{
		System.out.println("Fields >> {");
		// Get the fields the class declares.		
		Field[] fields = objClass.getDeclaredFields();
		// For each, find the type, modifiers, and current value.
		Arrays.stream(fields).forEach(field -> {
	        try
	        {
	        	System.out.print(field.toString());

	            field.setAccessible(true);
	            Object fieldObj = field.get(obj);
	            if (field.getType().isPrimitive() || !recursive)
	            {
	            	System.out.print(" = ");
	                System.out.println(fieldObj);
	            }
	            else if (field.getType().isArray() || fieldObj instanceof List<?>)
	            {
	            	// Field is a list / array of objects
	            	Object array;
	            	if (fieldObj instanceof List<?>)
	            	{
	            		List<?> listObj = (List<?>) fieldObj;
	            		array = listObj.toArray();
	            	}
	            	else
	            	{
	            		array = fieldObj;
	            	}
	            	int length = Array.getLength(array);
	            	System.out.print(array.getClass().getComponentType());
	            	System.out.println("[" + length + "] = {");	            	
	            	for(int i = 0; i < length; i++)
	            	{
	            		inspect(Array.get(array, i), recursive);
	            	}	  
	            	System.out.println("}");
	            }	            
	            else
	            {
	            	System.out.print(" = {");
	            	inspect(fieldObj, recursive);
	            	System.out.println("}");
	            }	            
	        }
	        catch (IllegalArgumentException | IllegalAccessException e)
	        {
	            e.printStackTrace();
	            // This should never happen!
	        }
	    });
		System.out.println("}");
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
