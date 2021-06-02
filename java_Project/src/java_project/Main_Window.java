package java_project;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saqib khan
 */
public class Main_Window extends javax.swing.JFrame {

    /**
     * Creates new form Main_Window
     */
    public Main_Window() {
        initComponents();
        Show_Products_In_JTable();
        
    }
    
    String ImgPath=null;
    int pos=0;
    
    public Connection getConnection()
    {
        Connection con=null;
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost/products_db2","root","");
           
             return con;
            
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
          
             return null;
        }
    }
    
     public boolean checkInputs(){
          if(
              txt_name.getText() == null
           || txt_price.getText() == null
           || txt_AddDate.getDate() == null
          ){
            return false;
        }
        else{
            try{
                Float.parseFloat(txt_price.getText());
                return true;
            }catch(Exception ex)
            {
                return false;
            }
        }
     }
    
    public ImageIcon ResizeImage(String imagePath, byte[] pic)
    {
          ImageIcon myImage = null;
        
        if(imagePath != null)
        {
            myImage = new ImageIcon(imagePath);
        }else{
            myImage = new ImageIcon(pic);
        }
        
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
        
    }
    
     // Display Data In JTable: 
    //      1 - Fill ArrayList With The Data
     public ArrayList<Product> getProductList()
     {
        
            ArrayList<Product> productList  = new ArrayList<Product>();
            Connection con = getConnection();
            String query = "SELECT * FROM products";
            
            Statement st;
            ResultSet rs;
            try{
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Product product;
            while(rs.next())
            {
                 product = new Product(rs.getInt("id"),rs.getString("name"),Float.parseFloat(rs.getString("price")),rs.getString("add_date"),rs.getBytes("image"));
                productList.add(product);
                
            }
            
        
        }catch(SQLException ex){
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
            
        }
            return productList;
                  
     }
     
     //      2 - Populate The JTable
     
     public void Show_Products_In_JTable()
     {
         ArrayList<Product> list = getProductList();
        DefaultTableModel model = (DefaultTableModel)JTable_Products.getModel();
        
        //clear Jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++)
        {
              row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getAddDate();
            
            model.addRow(row);
            
        }
         
     }
     
      // Show Data In Inputs
    public void ShowItem(int index)
    {
        txt_id.setText(Integer.toString(getProductList().get(index).getId()));
            txt_name.setText(getProductList().get(index).getName());
            txt_price.setText(Float.toString(getProductList().get(index).getPrice()));
            
        try {
            
            
            Date addDate = null;
            addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getProductList().get(index).getAddDate());
            txt_AddDate.setDate(addDate);
            
        } catch (ParseException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl_image.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
    }

        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_price = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_AddDate = new com.toedter.calendar.JDateChooser();
        lbl_image = new javax.swing.JLabel();
        Btn_Choos_Image = new javax.swing.JButton();
        Btn_Insert = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        Btn_Previous = new javax.swing.JButton();
        Btn_Last = new javax.swing.JButton();
        Btn_First = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Product Id");

        txt_id.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_id.setEnabled(false);
        txt_id.setPreferredSize(new java.awt.Dimension(6, 16));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Product Name");

        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Product Price");

        txt_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_priceActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Date Of Poduct");

        txt_AddDate.setDateFormatString("yyyy-MM-dd");
        txt_AddDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lbl_image.setBackground(new java.awt.Color(255, 255, 255));
        lbl_image.setOpaque(true);

        Btn_Choos_Image.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn_Choos_Image.setText("Select Product");
        Btn_Choos_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Choos_ImageActionPerformed(evt);
            }
        });

        Btn_Insert.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn_Insert.setText("Insert");
        Btn_Insert.setIconTextGap(3);
        Btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InsertActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        Btn_Previous.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn_Previous.setText("Previous");
        Btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PreviousActionPerformed(evt);
            }
        });

        Btn_Last.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn_Last.setText("Last");
        Btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LastActionPerformed(evt);
            }
        });

        Btn_First.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Btn_First.setText("First");
        Btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FirstActionPerformed(evt);
            }
        });

        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Add Date"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                        .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(Btn_Previous)
                        .addGap(45, 45, 45)
                        .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Btn_Choos_Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_AddDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .addComponent(lbl_image, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_name)
                            .addComponent(txt_price))
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(Btn_Choos_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(268, 268, 268))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_priceActionPerformed

    private void Btn_Choos_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Choos_ImageActionPerformed
        // TODO add your handling code here:
        JFileChooser file=new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter Filter= new FileNameExtensionFilter("*.images","jpg","png");
        file.addChoosableFileFilter(Filter);
        int result =file.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION)
        {
            File selectedFile=file.getSelectedFile();
            String path =selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path,null));
            ImgPath = path;
        }
        else{
            System.out.print("N0 file selected");
        }
    }//GEN-LAST:event_Btn_Choos_ImageActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed
        // TODO add your handling code here:
        if(checkInputs() && ImgPath !=null)
        {
            Connection con=getConnection();
            try {
                PreparedStatement ps=con.prepareStatement("INSERT INTO products(name,price,add_date,image)"+"values(?,?,?,?)");
                ps.setString(1, txt_name.getText());
                 ps.setString(2, txt_price.getText());
                 SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                 String addDate=dateFormat.format(txt_AddDate.getDate());
                 ps.setString(3, addDate);
                 InputStream img=new FileInputStream(new File(ImgPath));
                 ps.setBlob(4,img);
                 ps.executeUpdate(); 
                 Show_Products_In_JTable();
                JOptionPane.showMessageDialog(null,"Data Inserted");
                 
                 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"one or more Field are Empty");
        }
        
         System.out.println("Name => "+txt_name.getText());
        System.out.println("Price => "+txt_price.getText());
        System.out.println("Image => "+ImgPath);
    }//GEN-LAST:event_Btn_InsertActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(checkInputs() && txt_id.getText() !=null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            if(ImgPath==null)
            {
                try {
                    UpdateQuery = "UPDATE products SET name = ?, price = ?" + ", add_date = ? WHERE id = ?";
                    
                    ps = con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(txt_AddDate.getDate());
                    
                     ps.setString(3, addDate);
                   
                    ps.setInt(4, Integer.parseInt(txt_id.getText()));
                     ps.executeUpdate();
                     Show_Products_In_JTable();
                     JOptionPane.showMessageDialog(null, "Product Updated");
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            // update With Image
            else{
                try{
                    
                
                
                InputStream img = new FileInputStream(new File(ImgPath));
                        
                UpdateQuery = "UPDATE products SET name = ?, price = ?"
                            + ", add_date = ?, image = ? WHERE id = ?";
                
                ps = con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(txt_AddDate.getDate());
                    
                     ps.setString(3, addDate);
                      ps.setBlob(4, img);
                   
                    ps.setInt(5, Integer.parseInt(txt_id.getText()));
                     ps.executeUpdate();
                     Show_Products_In_JTable();
                     JOptionPane.showMessageDialog(null, "Product Updated");
                   
                    
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            
                
            }
            
        }else{
             JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        if(!txt_id.getText().equals(""))
        {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id = ?");
                int id = Integer.parseInt(txt_id.getText());
                 ps.setInt(1, id);
                ps.executeUpdate();
                Show_Products_In_JTable();
                JOptionPane.showMessageDialog(null, "Product Deleted");
                
            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Product Not Deleted");
            }
            
        }else{
             JOptionPane.showMessageDialog(null, "Product Not Deleted : No Id To Delete");
            
        }
        
    }//GEN-LAST:event_deleteActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
        // TODO add your handling code here:
         int index = JTable_Products.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_JTable_ProductsMouseClicked

    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
        // TODO add your handling code here:
        pos = 0;
        ShowItem(pos);
        
    }//GEN-LAST:event_Btn_FirstActionPerformed

    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed
        // TODO add your handling code here:
        pos = getProductList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_LastActionPerformed

    private void Btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PreviousActionPerformed
        // TODO add your handling code here:
         pos--;
       
        if(pos < 0)
        {
            pos = 0;
        }
       
        ShowItem(pos);
       
    }//GEN-LAST:event_Btn_PreviousActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Choos_Image;
    private javax.swing.JButton Btn_First;
    private javax.swing.JButton Btn_Insert;
    private javax.swing.JButton Btn_Last;
    private javax.swing.JButton Btn_Previous;
    private javax.swing.JTable JTable_Products;
    private javax.swing.JButton delete;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private com.toedter.calendar.JDateChooser txt_AddDate;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
