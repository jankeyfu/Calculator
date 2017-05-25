package com.codingk_k.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.codingk_k.utils.PolandAlgo;

public class MainPage extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tf_result;
	private JPanel pl_button;
	private JPanel pl_result;

	private JButton bt_ce;
	private JButton bt_back;
	private JButton bt_ans;
	private JButton bt_mul;
	private JButton bt_div;
	private JButton bt_sub;
	private JButton bt_add;
	private JButton bt_eql;
	private JButton bt_dot;
	private JButton bt_db0;
	private JButton[] bt_num;

	private String answer = "0.";
	private List<JButton> bt_list = null;

	public MainPage() {
		init();
		setSize(400, 600);
		draw();
		addActionListener();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.setTitle("计算器");
		this.setVisible(true);
	}

	public void init() {
		setLayout(new BorderLayout());
		tf_result = new JTextField("0.");
		tf_result.setHorizontalAlignment(JTextField.RIGHT);

		pl_button = new JPanel();
		pl_button.setLayout(new GridLayout(5, 4));
		pl_result = new JPanel();
		tf_result.setSize(50, 600);
		pl_result.setSize(50, 600);
		pl_result.add(tf_result);

		bt_ce = new JButton("C");
		bt_back = new JButton("<-");
		bt_ans = new JButton("ans");
		bt_add = new JButton("+");
		bt_sub = new JButton("-");
		bt_mul = new JButton("*");
		bt_div = new JButton("/");
		bt_db0 = new JButton("00");
		bt_dot = new JButton(".");
		bt_eql = new JButton("=");
		bt_num = new JButton[10];
		for (int i = 0; i < 10; i++) {
			bt_num[i] = new JButton(String.valueOf(i));
		}
	}

	public void draw() {
		add(tf_result, BorderLayout.NORTH);
		pl_button.add(bt_ce);
		pl_button.add(bt_back);
		pl_button.add(bt_ans);
		pl_button.add(bt_div);
		pl_button.add(bt_num[7]);
		pl_button.add(bt_num[8]);
		pl_button.add(bt_num[9]);
		pl_button.add(bt_mul);
		pl_button.add(bt_num[4]);
		pl_button.add(bt_num[5]);
		pl_button.add(bt_num[6]);
		pl_button.add(bt_sub);
		pl_button.add(bt_num[1]);
		pl_button.add(bt_num[2]);
		pl_button.add(bt_num[3]);
		pl_button.add(bt_add);

		pl_button.add(bt_db0);
		pl_button.add(bt_num[0]);
		pl_button.add(bt_dot);
		pl_button.add(bt_eql);
		add(pl_button, BorderLayout.CENTER);
		// 将按钮添加到list中
		bt_list = new ArrayList<>();
		bt_list.add(bt_ce);
		bt_list.add(bt_back);
		bt_list.add(bt_ans);
		bt_list.add(bt_div);
		bt_list.add(bt_num[7]);
		bt_list.add(bt_num[8]);
		bt_list.add(bt_num[9]);
		bt_list.add(bt_mul);
		bt_list.add(bt_num[4]);
		bt_list.add(bt_num[5]);
		bt_list.add(bt_num[6]);
		bt_list.add(bt_sub);
		bt_list.add(bt_num[1]);
		bt_list.add(bt_num[2]);
		bt_list.add(bt_num[3]);
		bt_list.add(bt_add);
		bt_list.add(bt_db0);
		bt_list.add(bt_num[0]);
		bt_list.add(bt_dot);
		bt_list.add(bt_eql);

	}

	public void addActionListener() {
		for (int i = 0; i < bt_list.size(); i++) {
			bt_list.get(i).addActionListener(this);
		}
	}

	/**
	 * 用于判断是否是初次输入，保持显示框为“0.”
	 */
	private boolean isFirst = true;

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 3; i < bt_list.size() - 1; i++) {
			if (e.getSource() == bt_list.get(i)) {
				if (isFirst) {
					tf_result.setText("");
					isFirst = false;
				}
				String temp = tf_result.getText() + bt_list.get(i).getText();
				tf_result.setText(temp);
			}
		}
		if (e.getSource() == bt_ce) {
			tf_result.setText("0.");
			isFirst = true;
		}
		/**
		 * 如果是初次按back键，不会删除“0.”
		 */
		if (e.getSource() == bt_back && !isFirst) {
			if (tf_result.getText().length() == 1 || "0.".equals(tf_result.getText())) {
				tf_result.setText("0.");
			} else {
				String temp = tf_result.getText();
				temp = temp.substring(0, temp.length() - 1);
				tf_result.setText(temp);
			}
		}
		if (e.getSource() == bt_ans) {
			tf_result.setText(answer);
		}
		if (e.getSource() == bt_eql && !isFirst) {
			String temp = tf_result.getText();
			temp = String.valueOf(PolandAlgo.calculate(temp));
			tf_result.setText(temp);
			answer = temp;
		}
	}
}
