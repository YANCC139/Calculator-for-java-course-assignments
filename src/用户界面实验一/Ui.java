package �û�����ʵ��һ;

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
	private String first="0";//ȡĬ�ϼ���ĵ�һ������Ϊ��
	private String second="";//Ĭ�ϼ���ĵڶ�������
	private short flag=0;
	private String operator="+";//����Ĳ�����Ĭ��Ϊ+
	private ActionListener newAction() {
		ActionListener actionlistener=new ActionListener() {
						public void actionPerformed(ActionEvent e) {
				String cmd=e.getActionCommand();//getActionCommand()�������ڻ�ȡ����ϵ�valueֵ��Ҳ����������ַ�������չ��getSource()���ڻ�ȡ���������
				//���������ַ�����+-*/
				if("+-*/".contains(cmd)) {
					//����ǵ�һ�����㣬�����������second��ֵ��first
					if(flag==0) {
						flag=1;
						first=second;//second��ֵ��ֵ��first
						second="";
					}
						caculate(operator);//ʹ���ϴ�����Ĳ����������������
						operator=cmd;
				}
				else if(cmd.equals("=")) {
					caculate(operator);
				}
				//��������ΪС����
				else if(cmd.equals(".")) {
					//�������������С���㣬�򷵻�
					if(second.contains(".")) {
						return;
					}
					//С����������Ӹ�second��֮���valueOf()����ʶ��ó���
					second=second+cmd;//���������ֵ
					outfield.setText(second);		
				}
				else {
					second=second+cmd;//���������ֵ
					outfield.setText(second);
				}
			}
			//���㺯��
			private void caculate(String cmd) {
				double d1=Double.valueOf(first);//valueof������������ԭʼֵ����ȡ�ַ����е��ַ���Ϊ����
				double d2=0;
				//�������ĵڶ���ֵΪ�գ��򲻼���
				if(second.equals("")) {
					return;
				}
				//���ڶ���ֵsecondԭʼֵ����d2
				d2=Double.valueOf(second);
				String result = "";
				switch (cmd) {
				case "+":
					result=String.valueOf(d1+d2);//�ӷ�����
					break;
				case "-":
					result=String.valueOf(d1-d2);//��������
					break;
				case "*":
					result=String.valueOf(d1*d2);//�˷�����
					break;
				case "/":
					result=String.valueOf(d1/d2);//��������
					break;
					
				}
				//���ϴβ�����Ľ����ֵ��num��second�ÿգ�׼�����¸�ֵ
				first=result;
				second="";
				outfield.setText(result);
			};
		};
		return actionlistener;
		
	}
 public Ui(){
	 	frame=new JFrame("������");
		frame.setSize(300, 300);
		frame.setLayout(new BorderLayout());
		//һ����壬���������ϲ㰴ť���ı������
		JPanel jp=new JPanel();
//		jp.setSize(300, 100);
		frame.add(jp,BorderLayout.NORTH);
		jp.setLayout(new BorderLayout());
		outfield=new JTextField();
//		outfield.setSize(300,25);
		outfield.setHorizontalAlignment(SwingConstants.RIGHT);//�������������ˮƽ����Ϊ�ұ߿�ʼ
		outfield.setFont(new Font("΢���ź�",Font.BOLD,20));//��������Ϊ΢���źڣ��Ӵ֣����ô�С
		jp.add(outfield);
		//һ����ť������������������
		JButton jb1=new JButton("���");
		//��գ����¹���
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outfield.setText("");
				flag=0;
				first="0";
				second="";
			}
		});
		jp.add(jb1,BorderLayout.EAST);
		//����һ���µ���壬���������水ť���
		JPanel jp1=new JPanel();
		jp1.setBackground(Color.white);//������屳����ɫΪ��ɫ
		jp1.setLayout(new GridLayout(4, 4, 4, 4));
//		jp1.setSize(300,270);
		frame.add(jp1,BorderLayout.CENTER);
		String[] btnstr=new String[] {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
		for(int i=0;i<16;i++) {
			JButton btn=new JButton(btnstr[i]);
			btn.setFont(new Font("����",Font.BOLD,20));
			btn.addActionListener(newAction());
			jp1.add(btn);
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
 }
}
