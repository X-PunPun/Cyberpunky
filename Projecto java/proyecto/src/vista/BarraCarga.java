package vista;

public class BarraCarga extends javax.swing.JFrame {

    public BarraCarga() {
        initComponents();
        this.setLocationRelativeTo(null);//centrar
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        TituloCarga = new javax.swing.JLabel();
        Porcentaje = new javax.swing.JLabel();
        BarraCarga1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TituloCarga.setFont(new java.awt.Font("Roboto Black", 0, 36)); // NOI18N
        TituloCarga.setForeground(new java.awt.Color(255, 0, 51));
        TituloCarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloCarga.setText("Ingresando  a Cyberpunky");
        jPanel1.add(TituloCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 450, 50));

        Porcentaje.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        Porcentaje.setForeground(new java.awt.Color(255, 0, 51));
        Porcentaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Porcentaje.setText("1%");
        jPanel1.add(Porcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 150, 50));

        BarraCarga1.setBackground(new java.awt.Color(153, 0, 51));
        BarraCarga1.setForeground(new java.awt.Color(255, 0, 0));
        BarraCarga1.setBorder(null);
        jPanel1.add(BarraCarga1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 730, 50));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fotos/fonds carga.gif"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 800, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(BarraCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BarraCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BarraCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BarraCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BarraCarga().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JProgressBar BarraCarga1;
    public javax.swing.JLabel Porcentaje;
    private javax.swing.JLabel TituloCarga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
