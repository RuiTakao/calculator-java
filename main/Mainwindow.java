package main;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import status.ButtonStatus;
import status.CalcStatus;

public class Mainwindow {
	
	private JFrame frame;
	
	private String[] strings;
	
	private String[] strings2;
	
	private JLabel label;
		
	private String outputSum;
	
	private String firstOutputNum;
	
	private String secondOutputNum;
	
	boolean calcFlag = true;
	
	boolean sumFlag = false;
	
	boolean diffFlag = false;
	
	boolean prodFlag = false;
	
	boolean quotFlag = false;
	
	List<String> aaa = new ArrayList<>();
	
	List<String> bbb = new ArrayList<>();
	
	Mainwindow() {
		
		this.frame = new JFrame("たかおの電卓");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setBounds(100, 200, 450, 480);
		
		var pane = this.frame.getContentPane();
		
		var canvas = new JPanel();
		
		canvas.setLayout(null);
		
		this.label = new JLabel();
		this.label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 28));
		this.label.setOpaque(true);
		this.label.setBackground(Color.WHITE);
		
		this.label.setBounds(70, 30, 300, 60);
		canvas.add(label);
		
		canvas.add(btn("1", ButtonStatus.ROW[0], ButtonStatus.COLUMN[0]));
		canvas.add(btn("2", ButtonStatus.ROW[1], ButtonStatus.COLUMN[0]));
		canvas.add(btn("3", ButtonStatus.ROW[2], ButtonStatus.COLUMN[0]));
		canvas.add(btn("4", ButtonStatus.ROW[0], ButtonStatus.COLUMN[1]));
		canvas.add(btn("5", ButtonStatus.ROW[1], ButtonStatus.COLUMN[1]));
		canvas.add(btn("6", ButtonStatus.ROW[2], ButtonStatus.COLUMN[1]));
		canvas.add(btn("7", ButtonStatus.ROW[0], ButtonStatus.COLUMN[2]));
		canvas.add(btn("8", ButtonStatus.ROW[1], ButtonStatus.COLUMN[2]));
		canvas.add(btn("9", ButtonStatus.ROW[2], ButtonStatus.COLUMN[2]));
		canvas.add(btn("0", ButtonStatus.ROW[0], ButtonStatus.COLUMN[3]));
		canvas.add(btn("00", ButtonStatus.ROW[1], ButtonStatus.COLUMN[3]));
		canvas.add(calcBtn(CalcStatus.EQUAL, ButtonStatus.ROW[2], ButtonStatus.COLUMN[3]));
		canvas.add(calcBtn(CalcStatus.SUM, ButtonStatus.ROW[3], ButtonStatus.COLUMN[0]));
		canvas.add(calcBtn(CalcStatus.DIFF, ButtonStatus.ROW[3], ButtonStatus.COLUMN[1]));
		canvas.add(calcBtn(CalcStatus.PROD, ButtonStatus.ROW[3], ButtonStatus.COLUMN[2]));
		canvas.add(calcBtn(CalcStatus.QUOT, ButtonStatus.ROW[3], ButtonStatus.COLUMN[3]));
		canvas.add(cBtn(CalcStatus.C, ButtonStatus.ROW[0], ButtonStatus.COLUMN[4]));
		
		pane.add(canvas);
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public JButton btn(String num, int row, int column) {
		JButton btn = new JButton(num);
		btn.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
		btn.setForeground(Color.WHITE);
		btn.setBackground(Color.GRAY);
		btn.setBounds(row, column, ButtonStatus.WIDTH, ButtonStatus.HEIGHT);
		btn.addActionListener((e) -> action(num));
		return btn;
	}
	
	public JButton calcBtn(String num, int row, int column) {
		JButton btn = new JButton(num);
		btn.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
		btn.setForeground(Color.BLACK);
		btn.setBackground(Color.WHITE);
		btn.setBounds(row, column, ButtonStatus.WIDTH, ButtonStatus.HEIGHT);
		btn.addActionListener((e) -> action(num));
		return btn;
	}
	
	public JButton cBtn(String num, int row, int column) {
		JButton btn = new JButton(num);
		btn.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 24));
		btn.setForeground(Color.WHITE);
		btn.setBackground(Color.RED);
		btn.setBounds(row, column, 300, ButtonStatus.HEIGHT);
		btn.addActionListener((e) -> action(num));
		return btn;
	}
	
	public void action(String num) {
		if (num.equals(CalcStatus.SUM)) {
			sum();
		} else if(num.equals(CalcStatus.DIFF)) {
			diff();
		} else if(num.equals(CalcStatus.PROD)) {
			prod();
		} else if(num.equals(CalcStatus.QUOT)) {
			quot();
		} else if(num.equals(CalcStatus.EQUAL)) {
			equal();
		} else if(num.equals(CalcStatus.C)) {
			c();
		} else {
			if (this.calcFlag) {
				num(num);
			} else {
				num2(num);
			}
		}
	}
	
	public void init () {
		this.calcFlag = true;
		this.aaa.clear();
		this.bbb.clear();
		this.sumFlag = false;
		this.diffFlag = false;
		this.prodFlag = false;
		this.quotFlag = false;
	}
	
	public void c() {
		init();
		this.label.setText("");
	}
	
	public void equal() {
		int num1 = Integer.parseInt(firstOutputNum);
		int num2 = Integer.parseInt(secondOutputNum);
		
		if (this.sumFlag) {
			int sumLast = num1 + num2;
			this.label.setText(firstOutputNum + CalcStatus.SUM_OUTPUT + secondOutputNum + CalcStatus.EQUAL_OUTPUT + sumLast);
		} else if (this.diffFlag) {
			int sumLast = num1 - num2;
			this.label.setText(firstOutputNum + CalcStatus.DIFF_OUTPUT + secondOutputNum + CalcStatus.EQUAL_OUTPUT + sumLast);
		} else if (this.prodFlag) {
			int sumLast = num1 * num2;
			this.label.setText(firstOutputNum + CalcStatus.PROD_OUTPUT + secondOutputNum + CalcStatus.EQUAL_OUTPUT + sumLast);
		} else if (this.quotFlag) {
			int sumLast = num1 / num2;
			this.label.setText(firstOutputNum + CalcStatus.QUOT_OUTPUT + secondOutputNum + CalcStatus.EQUAL_OUTPUT + sumLast);
		} else {
			init();
		}
		
		init();
	}
	
	public void sum() {
		this.calcFlag = false;
		this.sumFlag = true;
		this.label.getText();
		this.outputSum = firstOutputNum + CalcStatus.SUM_OUTPUT;
		this.label.setText(outputSum);
	}
	
	public void diff() {
		this.calcFlag = false;
		this.diffFlag = true;
		this.label.getText();
		this.outputSum = firstOutputNum + CalcStatus.DIFF_OUTPUT;
		this.label.setText(outputSum);
	}
	
	public void prod() {
		this.calcFlag = false;
		this.prodFlag = true;
		this.label.getText();
		this.outputSum = firstOutputNum + CalcStatus.PROD_OUTPUT;
		this.label.setText(outputSum);
	}
	
	public void quot() {
		this.calcFlag = false;
		this.quotFlag = true;
		this.label.getText();
		this.outputSum = firstOutputNum + CalcStatus.QUOT_OUTPUT;
		this.label.setText(outputSum);
	}
	
	public void num(String num) {
		this.label.setText(num);
		aaa.add(this.label.getText());
		this.strings = aaa.toArray(new String[aaa.size()]);
		this.label.setText(String.join("", this.strings));
		this.firstOutputNum = this.label.getText();
	}
	
	public void num2(String num) {
		this.label.setText(num);
		bbb.add(this.label.getText());
		this.strings2 = bbb.toArray(new String[bbb.size()]);
		this.label.setText(String.join("", this.strings2));
		this.secondOutputNum = this.label.getText();
		String sss = "";
		
		if (this.sumFlag) {
			sss = this.firstOutputNum + CalcStatus.SUM_OUTPUT + this.label.getText();
		} else if (this.diffFlag) {
			sss = this.firstOutputNum + CalcStatus.DIFF_OUTPUT + this.label.getText();
		} else if (this.prodFlag) {
			sss = this.firstOutputNum + CalcStatus.PROD_OUTPUT + this.label.getText();
		} else if (this.quotFlag) {
			sss = this.firstOutputNum + CalcStatus.QUOT_OUTPUT + this.label.getText();
		} else {
			init();
		}
		this.label.setText(sss);
	}
}