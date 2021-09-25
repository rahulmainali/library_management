import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;


public class Home {
	
	public static void main(String[] args) {
		Home regobj=new Home();
		
	}
	
	JButton btnLogin;
	JFrame log= new JFrame("Login");
	
	Home(){
		

			
		ImageIcon backgroundimage= new ImageIcon("C:\\Users\\banis\\eclipse-workspace\\CW-II\\background.jpg");
		Image img= backgroundimage.getImage();
		Image temp_img= img.getScaledInstance(600, 600, Image.SCALE_DEFAULT);
		backgroundimage= new ImageIcon(temp_img);
		JLabel background= new JLabel("",backgroundimage, JLabel.CENTER);
		log.add(background);
		background.setBounds(0, 0, 600, 600);
		
		//font
		Font f= new Font("Serif",Font.BOLD,120);
		//JPanel
		JPanel jpan= new JPanel();
		background.add(jpan);
		jpan.setBounds(0, 10,600, 600);
		jpan.setBackground(new Color(0,0,0,125));
		jpan.setLayout(null);
		
		
	
		JLabel  lUsername, lPassword, jreg;
		JTextField tfUsername;
		JPasswordField pfPassword;
		JButton btnLogin, btnSignUp;
		
		jreg = new JLabel("Login  Form");
		jpan.add(jreg);
		jreg.setBounds(250, 10, 300, 100);
		jreg.setFont(new Font("Serif",Font.BOLD,30));
		jreg.setForeground(Color.WHITE);
		
			 
		
		lUsername= new JLabel("Username");
		jpan.add(lUsername);
		lUsername.setBounds(60, 140, 300, 40);
		lUsername.setFont(new Font("Arial",Font.BOLD,15));
		lUsername.setForeground(Color.WHITE);
		
		tfUsername= new JTextField(30);
		jpan.add(tfUsername);
		tfUsername.setBounds(180, 140, 330, 30);
		
		
		
		lPassword= new JLabel("Password");
		jpan.add(lPassword);
		lPassword.setBounds(60, 220, 300, 40);
		lPassword.setFont(new Font("Arial",Font.BOLD,15));
		lPassword.setForeground(Color.WHITE);
		
		pfPassword= new JPasswordField(30);
		jpan.add(pfPassword);
		pfPassword.setBounds(180, 220, 330, 30);
		
		
		btnLogin = new JButton("Login");
		jpan.add(btnLogin);
		btnLogin.setBounds(160, 400, 130, 40);
		btnLogin.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		
		
		btnSignUp= new JButton("Signup");
		jpan.add(btnSignUp);
		btnSignUp.setBounds(340, 400, 130, 40);
		btnSignUp.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		
		
		// sign up
		btnSignUp.addActionListener(e->{
			new SignUp();
			log.dispose();
			
		});
		
		
		// login
		btnLogin.addActionListener(e1->{
			String user= tfUsername.getText();
			String pass= pfPassword.getText();
			
			boolean result= userLogin(user, pass);
			
			if (result) {
				new BookForm();
				log.dispose();
				
			}else {
				JOptionPane.showMessageDialog(log, "Username or Password invalid");
			}
		
			
		});
			
		log.setLayout(null);
		log.setSize(600,600);
		log.setVisible(true);
		log.setLocationRelativeTo(null);
		log.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public boolean userLogin(String user, String password) {
        try {
            DbConnect db=new DbConnect();
            String query="Select * from user_register where username='"+user+"'and password='"+password+"'";
            ResultSet result=db.connection().executeQuery(query);
            while(result.next()) {
                JOptionPane.showMessageDialog(btnLogin, "Login Successful ");
                new BookForm();
                log.dispose();
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return true;
    }
	


}

	

