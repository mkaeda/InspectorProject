package main;

public class Inspector {

	public void inspect(Object obj, boolean recursive) {
		// Get name of the declaring class.

		// Get name of the immediate superclass.

		// Get name of the interfaces the class implements.

		// Get methods the class declares.
		// For each method, find the: exceptions thrown, parameter types, return type,
		// and modifiers.

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
}
