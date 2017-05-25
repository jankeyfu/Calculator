package com.codingk_k.utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * TODO 还要很多需要优化的地方：括号的匹配，乘除应该有double，而不是用Integer，包括小数点的读取 Fighting
 * 
 * @Date 2017/05/25 修复了括号匹配,修复double算法
 * @author apple
 */
public class PolandAlgo {
	/**
	 * 将中缀表达式转换为后缀表达式
	 * 
	 * @param str
	 * @return
	 */
	private static Queue<String> polandAlgo(String str) {
		String temp = "";
		// 操作数栈
		Queue<String> num = new LinkedList<String>();
		// 操作符栈
		Stack<Character> operator = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 48 && c <= 57 || c == '.') {
				temp += c;
			} else {
				if (!temp.isEmpty()) {
					num.add(temp);
				}
				if (operator.isEmpty()) {
					operator.push(c);
				} else {
					switch (c) {
					case '(':
						operator.push(c);
						break;
					case ')':
						while (operator.peek() != '(') {
							num.add(operator.pop().toString());
						}
						operator.pop();
						break;
					default:
						if (operatorPriority(c) >= operatorPriority(operator.peek())) {
							operator.push(c);
						} else {
							while (!operator.isEmpty() && operatorPriority(c) < operatorPriority(operator.peek())
									&& operator.peek() != '(') {
								num.add(operator.pop().toString());
							}
							operator.push(c);
						}
						break;
					}
				}
				temp = "";
			}
		}

		if (!temp.isEmpty()) {
			num.add(temp);
		}
		while (!operator.isEmpty()) {
			num.add(operator.pop().toString());
		}
		return num;
	}

	public static double getRes(Queue<String> num) {
		Stack<String> result = new Stack<>();
		int size = num.size();
		for (int i = 0; i < size; i++) {
			String sdata = num.poll();
			try {
				double data = Double.valueOf(sdata);
				result.push(String.valueOf(data));
			} catch (Exception e) {
				switch (sdata) {
				case "+":
					double add = Double.valueOf(result.pop()) + Double.valueOf(result.pop());
					result.push(String.valueOf(add));
					break;
				case "-":
					add = -(Double.valueOf(result.pop()) - Double.valueOf(result.pop()));
					result.push(String.valueOf(add));
					break;
				case "*":
					add = Double.valueOf(result.pop()) * Double.valueOf(result.pop());
					result.push(String.valueOf(add));
					break;
				case "/":
					double num1 = Double.valueOf(result.pop());
					double num2 = Double.valueOf(result.pop());
					add = num2 / num1;
					result.push(String.valueOf(add));
					break;
				default:
					break;
				}
			}
		}
		return Double.valueOf(result.pop());
	}

	private static final int MIN_PRIORITY = 0;
	private static final int NORMAL_PRIORITY = 1;
	private static final int MAX_PRIORITY = 2;

	/**
	 * 设置运算符优先级：
	 * 
	 * + -优先级最低
	 * 
	 * * /优先级次之
	 * 
	 * ( )优先级最高
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

	public static String calculate(String str) {
		Queue<String> num = polandAlgo(str);
		double result = getRes(num);
		if (String.valueOf(result).endsWith(".0"))
			return String.valueOf((int) result);
		return String.valueOf(result);
	}

}
