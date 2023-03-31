/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.LoginDAO;
import Model.Product;
import Model.User;
import View.ProductView;
import View.LoginView;
import View.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author camil
 */
public class LoginController implements ActionListener {

    private LoginDAO loginDAO = new LoginDAO();
    private User user = new User();
    private LoginView loginView = new LoginView();
    private RegisterView registerView = new RegisterView();
    
    public LoginController(LoginView loginView, User user) {
        this.loginView = loginView;
        this.user = user;

        //Registro de usuarios
        this.loginView.btnEnter.addActionListener(this);
        this.loginView.btnRegister.addActionListener(this);
    }

    public void Login() {
        if ("".equals(loginView.txtCorreo.getText()) || "".equals(loginView.txtPass.getText())) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden etar vacios");
        } else {
            String email = loginView.txtCorreo.getText();
            String password = loginView.txtPass.getText();

            if (loginDAO.authentication(email, password)) {
                JOptionPane.showMessageDialog(null, "Ha ingresado correctamente");
                ProductView homeView = new ProductView();
                Product product = new Product();
                
                ProductController productController = new ProductController(homeView, product);
                homeView.setVisible(true);
                loginView.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error, correo o contraseña incorrectos");
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginView.btnEnter) {
            System.out.println("Click login");
            Login();
        }
        
        if (e.getSource() == loginView.btnRegister) {
            System.out.println("Clic en registrarse");
            loginView.setVisible(false);
            //loginView.dispose();
            RegisterController registerController = new RegisterController(registerView, user);
            registerView.setVisible(true);
            

        }
    }

}
