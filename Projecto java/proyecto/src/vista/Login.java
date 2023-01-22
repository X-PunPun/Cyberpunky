package vista;

import Controlador.*;
import Modelo.*;
import java.awt.Color;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.util.*;

public class Login extends javax.swing.JFrame {

    //variable de movimiento x y de la barra
    private int xMouse, yMouse;
    Conexion cone;

    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);//centrar
    }

    public void borrarUser() {
        if (TxtUserLG.getText().equals("Ingrese un usuario")) {
            TxtUserLG.setText("");
            TxtUserLG.setForeground(Color.RED);
        }
        if (String.valueOf(TxtPassLG.getPassword()).isEmpty()) {
            TxtPassLG.setText("jPasswordField1");
            TxtPassLG.setForeground(new Color(221, 14, 44));
        }

    }

    public void borrarPass() {
        if (String.valueOf(TxtPassLG.getPassword()).equals("jPasswordField1")) {
            TxtPassLG.setText("");
            TxtPassLG.setForeground(Color.RED);
        }
        if (TxtUserLG.getText().isEmpty()) {
            TxtUserLG.setText("Ingrese un usuario");
            TxtUserLG.setForeground(new Color(221, 14, 44));
        }
    }
    
    
    public void CargaMenu(){
        Menus p= new Menus();
        this.dispose();
        p.setVisible(true);
    }
    
    public void ingresar(){
        String usuario=TxtUserLG.getText();
        String clave=new String(TxtPassLG.getPassword());
        cone= new Conexion();
        Connection reg = cone.getConnection();
        ArrayList<Usuarios> re=cone.Login(usuario, clave);
        String datos[]=new String[3];
        
        for(Usuarios lo:re){
            datos[0]=lo.getId();
            datos[1]=lo.getNombre();
            datos[2]=lo.getContraseña();
        }
        
        if (re.isEmpty()){
            JOptionPane.showConfirmDialog(null, "No Existe usuario");
        }
        else{
            CargaMenu();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BG = new javax.swing.JPanel();
        TituloLG = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        TxtUserLG = new javax.swing.JTextField();
        separador1 = new javax.swing.JSeparator();
        jLabelContraseña = new javax.swing.JLabel();
        TxtPassLG = new javax.swing.JPasswordField();
        separador2 = new javax.swing.JSeparator();
        jPanelIngresar = new javax.swing.JPanel();
        BottonIngresar = new javax.swing.JLabel();
        JPanelSalir = new javax.swing.JPanel();
        BottonSalir = new javax.swing.JLabel();
        BottonX = new javax.swing.JPanel();
        BottonxX = new javax.swing.JLabel();
        Barra = new javax.swing.JPanel();
        botton_barra = new javax.swing.JLabel();
        FondoLG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        BG.setBackground(new java.awt.Color(255, 255, 255));
        BG.setMaximumSize(new java.awt.Dimension(0, 0));
        BG.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TituloLG.setBackground(new java.awt.Color(255, 255, 255));
        TituloLG.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        TituloLG.setForeground(new java.awt.Color(215, 14, 44));
        TituloLG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloLG.setText("Login");
        BG.add(TituloLG, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 100, 40));

        jLabelUsuario.setBackground(new java.awt.Color(255, 255, 255));
        jLabelUsuario.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        jLabelUsuario.setForeground(new java.awt.Color(215, 14, 44));
        jLabelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUsuario.setText("Usuario");
        BG.add(jLabelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 100, 30));

        TxtUserLG.setBackground(new java.awt.Color(128, 14, 44));
        TxtUserLG.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        TxtUserLG.setForeground(new java.awt.Color(221, 14, 44));
        TxtUserLG.setText("Ingrese un usuario");
        TxtUserLG.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TxtUserLG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TxtUserLGMousePressed(evt);
            }
        });
        BG.add(TxtUserLG, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 320, 30));

        separador1.setBackground(new java.awt.Color(197, 14, 44));
        separador1.setForeground(new java.awt.Color(197, 14, 44));
        BG.add(separador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, 330, 10));

        jLabelContraseña.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        jLabelContraseña.setForeground(new java.awt.Color(215, 14, 44));
        jLabelContraseña.setText("Contraseña");
        BG.add(jLabelContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, 130, 30));

        TxtPassLG.setBackground(new java.awt.Color(128, 14, 44));
        TxtPassLG.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        TxtPassLG.setForeground(new java.awt.Color(221, 14, 44));
        TxtPassLG.setText("jPasswordField1");
        TxtPassLG.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TxtPassLG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TxtPassLGMousePressed(evt);
            }
        });
        BG.add(TxtPassLG, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 390, 320, 30));

        separador2.setBackground(new java.awt.Color(197, 14, 44));
        separador2.setForeground(new java.awt.Color(197, 14, 44));
        BG.add(separador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, 330, 20));

        jPanelIngresar.setBackground(new java.awt.Color(128, 14, 44));
        jPanelIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelIngresarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelIngresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelIngresarMouseExited(evt);
            }
        });

        BottonIngresar.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BottonIngresar.setForeground(new java.awt.Color(255, 14, 44));
        BottonIngresar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BottonIngresar.setText("Ingresar");
        BottonIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanelIngresarLayout = new javax.swing.GroupLayout(jPanelIngresar);
        jPanelIngresar.setLayout(jPanelIngresarLayout);
        jPanelIngresarLayout.setHorizontalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BottonIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        jPanelIngresarLayout.setVerticalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BottonIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        BG.add(jPanelIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 450, 130, 40));

        JPanelSalir.setBackground(new java.awt.Color(128, 14, 44));
        JPanelSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPanelSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPanelSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPanelSalirMouseExited(evt);
            }
        });

        BottonSalir.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BottonSalir.setForeground(new java.awt.Color(255, 14, 44));
        BottonSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BottonSalir.setText("Salir");
        BottonSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout JPanelSalirLayout = new javax.swing.GroupLayout(JPanelSalir);
        JPanelSalir.setLayout(JPanelSalirLayout);
        JPanelSalirLayout.setHorizontalGroup(
            JPanelSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BottonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        JPanelSalirLayout.setVerticalGroup(
            JPanelSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BottonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        BG.add(JPanelSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 450, 130, 40));

        BottonX.setBackground(new java.awt.Color(153, 0, 0));
        BottonX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BottonX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BottonXMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BottonXMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BottonXMouseExited(evt);
            }
        });

        BottonxX.setBackground(new java.awt.Color(102, 0, 204));
        BottonxX.setFont(new java.awt.Font("Roboto Thin", 0, 48)); // NOI18N
        BottonxX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BottonxX.setText("X");
        BottonxX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout BottonXLayout = new javax.swing.GroupLayout(BottonX);
        BottonX.setLayout(BottonXLayout);
        BottonXLayout.setHorizontalGroup(
            BottonXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BottonxX, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        BottonXLayout.setVerticalGroup(
            BottonXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BottonXLayout.createSequentialGroup()
                .addComponent(BottonxX, javax.swing.GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        BG.add(BottonX, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        Barra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Barra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BarraMouseDragged(evt);
            }
        });
        Barra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BarraMousePressed(evt);
            }
        });

        botton_barra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fotos/fonds4.jpg"))); // NOI18N

        javax.swing.GroupLayout BarraLayout = new javax.swing.GroupLayout(Barra);
        Barra.setLayout(BarraLayout);
        BarraLayout.setHorizontalGroup(
            BarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botton_barra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BarraLayout.setVerticalGroup(
            BarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botton_barra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        BG.add(Barra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 50));

        FondoLG.setBackground(new java.awt.Color(255, 255, 255));
        FondoLG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        FondoLG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fotos/fonds4.jpg"))); // NOI18N
        BG.add(FondoLG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //eventos de ventana
    private void BarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraMousePressed
        // Evento de Ejes x y
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_BarraMousePressed

    private void BarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraMouseDragged
        // evento de arrastrar
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_BarraMouseDragged

    //botones de cierre
    private void BottonXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BottonXMouseClicked
        // Salir
        System.exit(0);
    }//GEN-LAST:event_BottonXMouseClicked

    private void JPanelSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPanelSalirMouseClicked
        // Salir
        System.exit(0);
    }//GEN-LAST:event_JPanelSalirMouseClicked

//Evento de animaciones botones
//boton X
    private void BottonXMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BottonXMouseEntered
        // al pasar por el boton x
        BottonX.setBackground(Color.red);
        BottonxX.setForeground(Color.white);
    }//GEN-LAST:event_BottonXMouseEntered

    private void BottonXMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BottonXMouseExited
        // al sacar el mouse del boton x
        BottonX.setBackground(new Color(153, 0, 0));
        BottonxX.setForeground(Color.BLACK);
    }//GEN-LAST:event_BottonXMouseExited

//boton Salir
    private void JPanelSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPanelSalirMouseEntered
        // al pasar por el boton salir
        JPanelSalir.setBackground(Color.red);
        BottonSalir.setForeground(Color.white);
    }//GEN-LAST:event_JPanelSalirMouseEntered

    private void JPanelSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPanelSalirMouseExited
        // al sacar el mouse del boton salir
        JPanelSalir.setBackground(new Color(128, 14, 44));
        BottonSalir.setForeground(new Color(255, 14, 44));
    }//GEN-LAST:event_JPanelSalirMouseExited

    //Boron Ingresar
    private void jPanelIngresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelIngresarMouseEntered
        // al pasar por el boton salir
        jPanelIngresar.setBackground(Color.red);
        BottonIngresar.setForeground(Color.white);
    }//GEN-LAST:event_jPanelIngresarMouseEntered

    private void jPanelIngresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelIngresarMouseExited
        // al sacar el mouse del boton salir
        jPanelIngresar.setBackground(new Color(128, 14, 44));
        BottonIngresar.setForeground(new Color(255, 14, 44));
    }//GEN-LAST:event_jPanelIngresarMouseExited

//evento de borrado user pass
    private void TxtUserLGMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtUserLGMousePressed
        // evento de borrado user
        borrarUser();
    }//GEN-LAST:event_TxtUserLGMousePressed

    private void TxtPassLGMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtPassLGMousePressed
        // evento de borrado pass
        borrarPass();
    }//GEN-LAST:event_TxtPassLGMousePressed

//evento de ingreso
    private void jPanelIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelIngresarMouseClicked
        // boton de ingreso.
        ingresar();
        //barra de trancicion y menu
    }//GEN-LAST:event_jPanelIngresarMouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BG;
    private javax.swing.JPanel Barra;
    private javax.swing.JLabel BottonIngresar;
    private javax.swing.JLabel BottonSalir;
    private javax.swing.JPanel BottonX;
    private javax.swing.JLabel BottonxX;
    private javax.swing.JLabel FondoLG;
    private javax.swing.JPanel JPanelSalir;
    private javax.swing.JLabel TituloLG;
    private javax.swing.JPasswordField TxtPassLG;
    private javax.swing.JTextField TxtUserLG;
    private javax.swing.JLabel botton_barra;
    private javax.swing.JLabel jLabelContraseña;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanelIngresar;
    private javax.swing.JSeparator separador1;
    private javax.swing.JSeparator separador2;
    // End of variables declaration//GEN-END:variables
}
