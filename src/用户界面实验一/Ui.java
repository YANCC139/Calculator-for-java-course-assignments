package 用户界面实验一;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class Ui {
	public JFrame frame;
	public JTextField outfield;
	private String first="0";//取默认计算的第一个数字为零
	private String second="";//默认计算的第二个数字
	private short flag=0;
	private String operator="+";//计算的操作，默认为+
	private ActionListener newAction() {
		ActionListener actionlistener=new ActionListener() {
						public void actionPerformed(ActionEvent e) {
				String cmd=e.getActionCommand();//getActionCommand()方法用于获取组件上的value值，也就是上面的字符串，拓展：getSource()用于获取组件变量名
				//如果输入的字符包含+-*/
				if("+-*/".contains(cmd)) {
					//如果是第一次运算，则将我们输入的second赋值给first
					if(flag==0) {
						flag=1;
						first=second;//second的值赋值给first
						second="";
					}
						caculate(operator);//使用上次输入的操作符进行运算操作
						operator=cmd;
				}
				else if(cmd.equals("=")) {
					caculate(operator);
				}
				//如果输入的为小数点
				else if(cmd.equals(".")) {
					//如果连续输入多个小数点，则返回
					if(second.contains(".")) {
						return;
					}
					//小数点照样添加给second，之后的valueOf()函数识别得出来
					second=second+cmd;//更新输入的值
					outfield.setText(second);		
				}
				else {
					second=second+cmd;//更新输入的值
					outfield.setText(second);
				}
			}
			//计算函数
			private void caculate(String cmd) {
				double d1=Double.valueOf(first);//valueof方法用来返回原始值，即取字符串中的字符变为数字
				double d2=0;
				//如果输入的第二个值为空，则不计算
				if(second.equals("")) {
					return;
				}
				//将第二个值second原始值附给d2
				d2=Double.valueOf(second);
				String result = "";
				switch (cmd) {
				case "+":
					result=String.valueOf(d1+d2);//加法计算
					break;
				case "-":
					result=String.valueOf(d1-d2);//减法计算
					break;
				case "*":
					result=String.valueOf(d1*d2);//乘法计算
					break;
				case "/":
					result=String.valueOf(d1/d2);//除法计算
					break;
					
				}
				//将上次操作完的结果赋值给num，second置空，准备重新赋值
				first=result;
				second="";
				outfield.setText(result);
			};
		};
		return actionlistener;
		
	}
 public Ui(){
	 	frame=new JFrame("计算器");
		frame.setSize(300, 300);
		frame.setLayout(new BorderLayout());
		//一个面板，用来放置上层按钮和文本输入框
		JPanel jp=new JPanel();
//		jp.setSize(300, 100);
		frame.add(jp,BorderLayout.NORTH);
		jp.setLayout(new BorderLayout());
		outfield=new JTextField();
//		outfield.setSize(300,25);
		outfield.setHorizontalAlignment(SwingConstants.RIGHT);//设置数据输入的水平布局为右边开始
		outfield.setFont(new Font("微软雅黑",Font.BOLD,20));//设置字体为微软雅黑，加粗，设置大小
		jp.add(outfield);
		//一个按钮，用来清空输入框内容
		JButton jb1=new JButton("清空");
		//清空，重新归零
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outfield.setText("");
				flag=0;
				first="0";
				second="";
			}
		});
		jp.add(jb1,BorderLayout.EAST);
		//定义一个新的面板，用来做下面按钮框框
		JPanel jp1=new JPanel();
		jp1.setBackground(Color.white);//设置面板背景颜色为黄色
		jp1.setLayout(new GridLayout(4, 4, 4, 4));
//		jp1.setSize(300,270);
		frame.add(jp1,BorderLayout.CENTER);
		String[] btnstr=new String[] {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
		for(int i=0;i<16;i++) {
			JButton btn=new JButton(btnstr[i]);
			btn.setFont(new Font("黑体",Font.BOLD,20));
			btn.addActionListener(newAction());
			jp1.add(btn);
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
 }
}
