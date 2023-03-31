/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.RegisterDAO;
import Model.User;
import View.LoginView;
import View.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author camil
 */
public class RegisterController implements ActionListener {

    RegisterDAO registerDAO = new RegisterDAO();
    User user = new User();
    RegisterView registerView = new RegisterView();
    LoginView loginView = new LoginView();
    

    public RegisterController(RegisterView registerView, User user) {
        this.registerView = registerView;
        this.user = user;

        /*
         */
        this.registerView.btnRegister.addActionListener(this);
        this.registerView.btnLogin.addActionListener(this);
    }

    public void Register() {
        if ("".equals(registerView.txtCorreo.getText()) || "".equals(registerView.txtPass.getText())
                || "".equals(registerView.txtName.getText())) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden etar vacios");
        } else {
            user.setName(registerView.txtName.getText());
            user.setEmail(registerView.txtCorreo.getText());
            user.setPassword(registerView.txtPass.getText());

            if (registerDAO.RegisterUser(user)) {
                JOptionPane.showMessageDialog(null, "Usuario registrado");
            } else {
                JOptionPane.showMessageDialog(null, "Error en el registro de usuario");
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerView.btnRegister) {
            System.out.println("CLICK Register ");
            Register();
        }
        if (e.getSource() == registerView.btnLogin) {
            System.out.println("Clic en iniciar sesion");
            registerView.setVisible(false);
            //registerView.dispose();
            LoginController loginController = new LoginController(loginView, user);
            loginView.setVisible(true);
            

        }
    }

}
