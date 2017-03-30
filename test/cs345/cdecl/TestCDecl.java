package cs345.cdecl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestCDecl {
	public String input;
	public String expectedOutput;

	public static final Object[][] tests = new Object[][] {
		{"int i;",      "i is a int"},
		{"float f;",    "f is a float"},
		{"int *p;",     "p is a pointer to int"},
		{"int **p;",    "p is a pointer to pointer to int"},
		{"int a[];",    "a is a array of int"},
		{"int *a[];",   "a is a array of pointer to int"},
		{"int (*a)[];", "a is a pointer to array of int"},
		{"int *(*a)[];","a is a pointer to array of pointer to int"},
		{"int **a[];",  "a is a array of pointer to pointer to int"},
		{"int f();",    "f is a function returning int"},
		{"void f();",   "f is a function returning nothing"},
		{"float f();",  "f is a function returning float"},
		{"int (*f)();", "f is a pointer to function returning int"},
		{"int *(*f[])();","f is a array of pointer to function returning pointer to int"},
		{"int *f();",   "f is a function returning pointer to int"},
	};

	public TestCDecl(String input, String expectedOutput) {
		this.input = input;
		this.expectedOutput = expectedOutput;
	}

	@Test
	public void test() {
		assertEquals(expectedOutput, Tool.translate(input));
	}

	@Parameterized.Parameters(name="{index}: {0} => {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(tests);
	}
}
