import javax.swing.*;


import java.awt.*;
import java.sql.SQLException;


public class SignUp {

	
	SignUp(){
		JFrame sign= new JFrame("SignUp");
		ImageIcon backgroundimage= new ImageIcon("C:\\Users\\banis\\eclipse-workspace\\CW-II\\SignupBG.jpeg");
		
		Image img= backgroundimage.getImage();
		Image temp_img= img.getScaledInstance(1000, 600, Image.SCALE_DEFAULT);
		backgroundimage= new ImageIcon(temp_img);
		JLabel background= new JLabel("",backgroundimage, JLabel.CENTER);
		sign.add(background);
		background.setBounds(0, 0, 1000, 600);
		
		//font
		Font f= new Font("Serif",Font.BOLD,40);
		//JPanel
		JPanel jpan= new JPanel();
		background.add(jpan);
		jpan.setBounds(100, 20,800, 500);
		jpan.setBackground(new Color(0,0,0,100));
		jpan.setLayout(null);
				
		
		
		JLabel lUsername, lPassword, lCPassword, jsign;
		JTextField tfUsername;
		JPasswordField pfPassword, pfCPassword;
		JButton btnSignUp, btnCancel;
		
		
		jsign= new JLabel("SignUp  Form");
		jpan.add(jsign);
		jsign.setBounds(330, 10, 300, 100);
		jsign.setFont(new Font("Serif", Font.BOLD, 30));
		jsign.setForeground(Color.WHITE);
		
		lUsername= new JLabel("Username");
		jpan.add(lUsername);
		lUsername.setBounds(120, 150, 300, 30);
		lUsername.setFont(new Font("Arial", Font.BOLD,15));
		lUsername.setForeground(Color.WHITE);
		
		tfUsername= new JTextField(30);
		jpan.add(tfUsername);
		tfUsername.setBounds(265, 150, 330, 30);
		
		
		
		lPassword= new JLabel("Password");
		jpan.add(lPassword);
		lPassword.setBounds(120, 200, 300, 30);
		lPassword.setFont(new Font("Arial", Font.BOLD,15));
		lPassword.setForeground(Color.WHITE);
			
		pfPassword= new JPasswordField(30);
		jpan.add(pfPassword);
		pfPassword.setBounds(265, 200, 330, 30);
		
		
		lCPassword= new JLabel("Confirm Password");
		jpan.add(lCPassword);
		lCPassword.setBounds(120, 250, 300, 30);
		lCPassword.setFont(new Font("Arial", Font.BOLD,15));
		lCPassword.setForeground(Color.WHITE);
			
		pfCPassword= new JPasswordField(30);
		jpan.add(pfCPassword);
		pfCPassword.setBounds(265, 250, 330, 30);
		
		
		btnSignUp = new JButton("Signup");
		jpan.add(btnSignUp);
		btnSignUp.setBounds(265, 330, 140, 40);
		btnSignUp.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		
		btnCancel = new JButton("Cancel");
		jpan.add(btnCancel);
		btnCancel.setBounds(450, 330, 140, 40);
		btnCancel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		
		
		btnCancel.addActionListener(e->{
			int select= JOptionPane.showConfirmDialog(btnCancel,"Are you sure?");
			if (select==0) {
				new Home();
				sign.dispose();
			}
			
		});
		
		btnSignUp.addActionListener(e->{
			String username= tfUsername.getText();
			String password= pfPassword.getText();
			String cpassword= pfCPassword.getText();
			
			
			try {
				DbConnect db = new DbConnect();
				String query= "insert into user_register values ('"+username+"','"+password+"','"+cpassword+"')";
				int result = db.connection().executeUpdate(query);
			 	if (result>0) {
					JOptionPane.showMessageDialog(btnSignUp, "User registered successfully");
					new Home();
					sign.dispose();
				}
				
			}catch(SQLException e1) {
				e1.printStackTrace();
				}
			
		});
		
			
	
		
		sign.setLayout(null);
		sign.setSize(1000,600);
		sign.setVisible(true);
		sign.setLocationRelativeTo(null);
		sign.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
	}

}
