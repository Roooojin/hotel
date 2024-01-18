import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotelManagement extends JFrame {
    private ArrayList<Room> rooms;
    private ArrayList<Guest> guests;
    private ArrayList<Reservation> reservations;
    private ArrayList<Employee> employees;
    private static HashMap<String,String> emailANDpassword;
     public HotelManagement(){
         this.employees=new ArrayList<>();
         this.guests=new ArrayList<>();
         this.rooms=new ArrayList<>();
         this.reservations=new ArrayList<>();
         this.emailANDpassword=new HashMap<>();
     }

     public void loginPage(){
         setTitle("Welcome Page");
         setSize(600, 700);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         try {
             setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\LENOVO\\Desktop\\Hotel-Motto-2-1024x679.jpeg")))));
         } catch (IOException e) {
             e.printStackTrace();
         }

         setLayout(new FlowLayout());

         JPanel panel = new JPanel();
         placeComponents(panel);
         add(panel);

         setLocationRelativeTo(null);
         setVisible(true);

     }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new FlowLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Our Hotel");
        panel.add(welcomeLabel);

        JButton guestButton = createButton("Guest Login");
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Guest login selected");
                Login();
            }
        });

        JButton employeeButton = createButton("Employee Login");
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Employee login selected");

            }
        });

        JButton bossButton = createButton("Boss Login");
        bossButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Boss login selected");
            }
        });

        panel.add(guestButton);
        panel.add(employeeButton);
        panel.add(bossButton);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        return button;
    }

    private boolean checkEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // ایجاد یک الگو
        Pattern pattern = Pattern.compile(emailRegex);

        // ایجاد یک مچر با استفاده از الگو
        Matcher matcher = pattern.matcher(email);

        // چک کردن مطابقت و بازگشت نتیجه
        return matcher.matches();

    }

    public boolean checkPasswordStrength(String password){
         if (calculatePasswordStrength(password)>=3)
             return true;
         else return false;
    }


    private static int calculatePasswordStrength(String password) {
        int strength = 0;


        if (password.matches("[a-z]+") || password.matches("[A-Z]+") || password.matches("[0-9]+")) {
            strength++;
        }


        if (password.matches("(?=.*[a-z])(?=.*[A-Z]).*")) {
            strength++;
        }


        if (password.matches(".*\\d.*")) {
            strength++;
        }


        if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            strength++;
        }


        if (password.length() > 8) {
            strength++;
        }

        return strength;
    }

    public void GuestMenue(){
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setTitle("Guests Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // افزودن تصویر متحرک به پسزمینه فریم
        String imagePath = "C:\\Users\\LENOVO\\Desktop\\Rotating_earth_(large).gif";
        ImageIcon animatedImage = new ImageIcon(imagePath);
        JLabel backgroundLabel = new JLabel(animatedImage);
        frame.setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(animatedImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        });

        // ایجاد یک BoxLayout عمودی
        Box box = Box.createVerticalBox();

        // افزودن دکمهها به Box
        box.add(createButton("Delivering room"));
        box.add(createButton("Reserving room"));

        // افزودن Box به فریم
        frame.add(box);

        // تنظیم محل نمایش فریم
        frame.setLocationRelativeTo(null);

        // نمایش فریم
        frame.setVisible(true);
    }

    public void Login(){
        JFrame frame = new JFrame("MyLogin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTextField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JLabel selectionLabel = new JLabel("Select an option:");

        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(selectionLabel);
        panel.add(loginButton);
        panel.add(registerButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailTextField.getText();
                String password = new String(passwordField.getPassword());

                if (checkLogin(email, password)) {
                    JOptionPane.showMessageDialog(frame, "Welcome back!");
                } else {
                    JOptionPane.showMessageDialog(frame, "User not found or incorrect password.");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailTextField.getText();
                String password = new String(passwordField.getPassword());

                if (checkEmail(email)) {
                    int passwordStrength = calculatePasswordStrength(password);
                    JOptionPane.showMessageDialog(frame, "Password strength: " + passwordStrength);
                    saveUser(email,password);

                    JOptionPane.showMessageDialog(frame, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid email format. Please try again.");
                }
            }
        });

        selectionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        selectionLabel.setHorizontalAlignment(JLabel.CENTER);

        String[] options = {"Login", "Register"};
        int selectedOption = JOptionPane.showOptionDialog(frame, "Select an option:", "MyLogin", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (selectedOption == 0) {
            selectionLabel.setText("Login");
            registerButton.setVisible(false);
        } else if (selectedOption == 1) {
            selectionLabel.setText("Register");
            loginButton.setVisible(false);
        }

    }


    private static boolean checkLogin(String loginEmail, String loginPassword) {
         if(emailANDpassword.get(loginEmail).equals(loginPassword))
             return true;
         else return false;

    }

    private static HashMap<String,String> saveUser(String email, String Password) {
        emailANDpassword.put(email, Password);
        return emailANDpassword;
        }
    }






