package com.codingk_k.utils;

import java.util.Arrays;
import java.util.Stack;

/**
 * TODO 还要很多需要优化的地方：括号的匹配，乘除应该有double，而不是用Integer，包括小数点的读取 Fighting
 * 
 * @author apple
 *
 */
public class PolandAlgo {
	private static Stack<String> polandAlgo(String str) {
		String temp = "";
		Stack<String> num = new Stack<>();
		Stack<Character> operator = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c >= 48 && c <= 57) {
				temp += c;
			} else {

				if (!temp.isEmpty()) {
					num.push(temp);
				}
				if (operator.isEmpty()) {
					operator.push(c);
				} else {
					if (operatorPriority(c) >= operatorPriority(operator.peek())) {
						operator.push(c);
					} else {
						while (operator != null && operatorPriority(c) < operatorPriority(operator.peek())) {
							num.push(operator.pop().toString());
						}
						operator.push(c);
					}
				}
				temp = "";
			}
		}
		if (!temp.isEmpty()) {
			num.push(temp);
		}
		while (!operator.isEmpty()) {
			num.push(operator.pop().toString());
		}
		return num;
	}

	public static int getRes(Stack<String> num) {
		double res = 0;
		Stack<String> result = new Stack<>();
		String[] str = new String[num.size()];
		int index = str.length;
		while (!num.isEmpty()) {
			str[--index] = num.pop();
			// System.out.println(Arrays.toString(str));
		}
		for (int i = 0; i < str.length; i++) {
			try {
				int data = Integer.valueOf(str[i]);
				result.push(String.valueOf(data));
			} catch (Exception e) {
				switch (str[i]) {

				case "+":
					int add = Integer.valueOf(result.pop()) + Integer.valueOf(result.pop());
					// System.out.println(add);
					result.push(String.valueOf(add));
					break;
				case "-":
					add = -(Integer.valueOf(result.pop()) - Integer.valueOf(result.pop()));
					// System.out.println(add);
					result.push(String.valueOf(add));
					break;
				case "*":
					add = Integer.valueOf(result.pop()) * Integer.valueOf(result.pop());
					// System.out.println(add);
					result.push(String.valueOf(add));
					break;
				case "/":
					add = Integer.valueOf(result.pop()) / Integer.valueOf(result.pop());
					// System.out.println(add);
					result.push(String.valueOf(add));
					break;
				default:
					break;
				}
			}
		}
		return Integer.valueOf(result.pop());
	}

	private static final int MIN_PRIORITY = 0;
	private static final int NORMAL_PRIORITY = 1;
	private static final int MAX_PRIORITY = 2;

	/**
	 * 设置运算符优先级： + -优先级最低 * /优先级次之 ( )优先级最高
	 * 
	 * @param c
	 * @return
	 */
	private static int operatorPriority(Character c) {
		if (c == '+' || c == '-')
			return MIN_PRIORITY;
		else if (c == '*' || c == '/')
			return NORMAL_PRIORITY;
		else
			return MAX_PRIORITY;
	}
	//
	// public static void main(String[] args) {
	// Stack<String> num = polandAlgo("1+2*3-4");
	// System.out.println(getRes(num));
	// }

	public static int calculate(String str) {
		Stack<String> num = polandAlgo(str);
		return getRes(num);
	}
}
