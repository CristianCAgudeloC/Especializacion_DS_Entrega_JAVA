/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Product;
import Model.ProductDAO;
import View.ProductView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author camil
 */
public class ProductController implements ActionListener {

    ProductDAO productDAO = new ProductDAO();
    Product product = new Product();
    ProductView productView = new ProductView();

    public ProductController(ProductView productView, Product product) {
        this.productView = productView;
        this.product = product;

        this.productView.btnSaveProduct.addActionListener(this);
        this.productView.btnUpdateProduct.addActionListener(this);
        this.productView.btnDeleteProduct.addActionListener(this);
        this.productView.btnProducts.addActionListener(this);
        this.productView.btnListProduct.addActionListener(this);

    }

    public void ListProducts() {
        DefaultTableModel tableModel = new DefaultTableModel();

        try {
            ResultSet resultSet = productDAO.ListProducts();
            tableModel.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Precio", "Descripcion", "Tipo_producto"});
            
            while (resultSet.next()) {
                tableModel.addRow(new Object[]{resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price"),
                    resultSet.getString("description"), resultSet.getString("type")});

                productView.tableProduct.setModel(tableModel);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void RegisterProduct() {
        if ("".equals(productView.txtNameProduct.getText()) || "".equals(productView.txtDescriptionProduct.getText())
                || "".equals(productView.txtPriceProduct.getText()) || "".equals(productView.itemTypeProduct.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Error, todos los productos deben estar diligenciados");
        } else {
            product.setName(productView.txtNameProduct.getText());
            product.setDescription(productView.txtDescriptionProduct.getText());
            product.setPrice(Integer.parseInt(productView.txtPriceProduct.getText()));
            product.setType(productView.itemTypeProduct.getSelectedItem().toString());

            if (productDAO.RegisterProduct(product)) {
                JOptionPane.showMessageDialog(null, "Producto registrado");
            } else {
                JOptionPane.showMessageDialog(null, "Error en el registro del Producto");
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == productView.btnSaveProduct) {
            System.out.println("CLICK Register Product ");
            RegisterProduct();
        }
        if (e.getSource() == productView.btnListProduct) {
            System.out.println("CLICK List Product ");
            ListProducts();
        }
        if (e.getSource() == productView.btnUpdateProduct) {
            System.out.println("CLICK Update Product ");
        }
        if (e.getSource() == productView.btnDeleteProduct) {
            System.out.println("CLICK Delete Product ");
        }

    }

}
