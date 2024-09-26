import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class awt {
    String s="";
	public awt() {
        JFrame f1 = new JFrame();
        f1.setLayout(null);
        f1.setSize(400, 400);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");
        JButton b0 = new JButton("0");
        JButton add = new JButton("+");
        JButton sub = new JButton("-");
        JButton mul = new JButton("*");
        JButton div = new JButton("/");
        JButton eq = new JButton("=");
        JButton cl = new JButton("CE");
        JButton dot = new JButton(".");

        JTextField t1 = new JTextField();

        // Add buttons to the frame
        f1.add(b1);
        f1.add(b2);
        f1.add(b3);
        f1.add(b4);
        f1.add(b5);
        f1.add(b6);
        f1.add(b7);
        f1.add(b8);
        f1.add(b9);
        f1.add(b0);
        f1.add(add);
        f1.add(sub);
        f1.add(mul);
        f1.add(div);
        f1.add(eq);
        f1.add(dot);
        f1.add(cl);
        f1.add(t1);

        b1.setBounds(100, 100, 50, 50);
        b2.setBounds(150, 100, 50, 50);
        b3.setBounds(200, 100, 50, 50);
        b4.setBounds(100, 150, 50, 50);
        b5.setBounds(150, 150, 50, 50);
        b6.setBounds(200, 150, 50, 50);
        b7.setBounds(100, 200, 50, 50);
        b8.setBounds(150, 200, 50, 50);
        b9.setBounds(200, 200, 50, 50);
        b0.setBounds(150, 250, 50, 50);
        add.setBounds(250, 100, 50, 50);
        sub.setBounds(250, 150, 50, 50);
        mul.setBounds(250, 200, 50, 50);
        eq.setBounds(200, 250, 50, 50);
        div.setBounds(250, 250, 50, 50);
        dot.setBounds(100, 250, 50, 50);
        t1.setBounds(100, 50, 150, 50);
        cl.setBounds(250, 50, 50, 50);

        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cl) {
                    t1.setText("");
                    s = "";
                } else {
                    if (e.getSource() != eq) {
                        JButton clickedButton = (JButton) e.getSource();
                        s += clickedButton.getText();
                        t1.setText(s);
                    }
                }

                if (e.getSource() == eq) {
                    try {
                        int result = eval(s);
                        t1.setText(Integer.toString(result));
                    } catch (Exception ex) {
                        t1.setText("Error");
                    }
                }
            }
        };

        // Add ActionListener to each button
        b1.addActionListener(action);
        b2.addActionListener(action);
        b3.addActionListener(action);
        b4.addActionListener(action);
        b5.addActionListener(action);
        b6.addActionListener(action);
        b7.addActionListener(action);
        b8.addActionListener(action);
        b9.addActionListener(action);
        b0.addActionListener(action);
        add.addActionListener(action);
        mul.addActionListener(action);
        sub.addActionListener(action);
        div.addActionListener(action);
        eq.addActionListener(action);
        dot.addActionListener(action);
        cl.addActionListener(action);

           f1.setVisible(true);
    }

    
    public static int eval( String str) {

    	 char[] tokens =str.toCharArray();
         Stack<Integer> values = new Stack<Integer>();
         Stack<Character> ops = new Stack<Character>();
         for (int i = 0; i < tokens.length; i++)
         {
             if (tokens[i] == ' ')
                 continue;
             if (tokens[i] >= '0' &&  tokens[i] <= '9')
             {
                 StringBuffer sbuf = new StringBuffer();
          while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                     sbuf.append(tokens[i++]);
                 values.push(Integer.parseInt(sbuf.toString()));
                 i--;
             }
  
             else if (tokens[i] == '(')
                 ops.push(tokens[i]);
   
             else if (tokens[i] == ')')
             {
                 while (ops.peek() != '(')
                   values.push(applyOp(ops.pop(),values.pop(),values.pop())); 
                 ops.pop();
             }
  
          
        else if (tokens[i] == '+' || tokens[i] == '-' ||tokens[i] == '*' || tokens[i] == '/')
             {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                   values.push(applyOp(ops.pop(), values.pop(),values.pop()));
  
                        ops.push(tokens[i]);
             }
         }
         while (!ops.empty())
         	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
  
         return values.pop();
     }
  
    
        public static boolean hasPrecedence(char op1, char op2)
     {
         if (op2 == '(' || op2 == ')')
             return false;
         if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
             return false;
         else
             return true;
     }
  

     public static int applyOp(char op,int b, int a)
     {
         switch (op)
         {
         case '+':
             return a + b;
         case '-':
             return a - b;
         case '*':
             return a * b;
         case '/':
             if (b == 0)
                 throw new
                 UnsupportedOperationException(
                       "Cannot divide by zero");
             return a / b;
         }
         return 0;
     }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new awt());
    }
}
