package vista;
//importaciones
import Controlador.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;











//codigo general
public class Menus extends javax.swing.JFrame {
    //variables x y
    int xMouse, yMouse;
    
    
    Conexion con1 = new Conexion();
    Connection conet;
    private DefaultTableModel modelo;
    private Statement st;
    private ResultSet rs;
    private int iddb;
    
    Conexion connUno = new Conexion();
    private Connection connect;
    
    //comienzo del programa
    public Menus() {
        initComponents();
        this.setLocationRelativeTo(null);//centrar
        Consultar();
        limpiarStax();
        limpiarStaxTABLA();
        
    }
    
    
    
    //Crear
    public void BotonCrearP(){
        String apodo= txtApodo.getText();
        String clase = (String) BoxClase.getSelectedItem();
        int reflejo= Integer.parseInt(txtReflejos.getText());
        int habilidada= Integer.parseInt(txtHabilidadArmas.getText());
        int tc= Integer.parseInt(txtTipoCorporal.getText());
        int humanidad= Integer.parseInt(txtHumanidad.getText());;
        try {
            if((apodo !=null && clase !=null)&&(reflejo <=10 && reflejo >=1)&&(habilidada <=10 && habilidada >=1)&&(tc <=10 && tc>=1)&&(humanidad <=10 && humanidad>=1)){
                PreparedStatement pps =conet.prepareStatement("insert into persodb (Apodo,Clase,Reflejo,Habilidada,Tc,Humanidad) values(?,?,?,?,?,?)");
                pps.setString(1, (String) txtApodo.getText());
                pps.setString(2, (String) BoxClase.getSelectedItem().toString());
                pps.setInt(3, Integer.parseInt(txtReflejos.getText()));
                pps.setInt(4, Integer.parseInt(txtHabilidadArmas.getText()));
                pps.setInt(5,Integer.parseInt(txtTipoCorporal.getText()));
                pps.setInt(6, Integer.parseInt(txtHumanidad.getText()));
                pps.executeUpdate();
                limpiarStax();
                JOptionPane.showMessageDialog(null, "Se a agregado Tu Personaje 😊");
                limpiarTabla();
            }
            else{
                JOptionPane.showMessageDialog(null, "Tu Ficha de personaje posee un o mas valor incorecto."
                    + "\nRecueda que los valores "
                    + "\n-Reflejos"
                    + "\n-Habilidad armas"
                    + "\n-Tipo Corporal"
                    + "\n-Humanidad "
                    + "\nNo deben ser menor a 0 o mayor a 10 😥");
        }
        } catch (Exception e) {
            System.out.println("Error Localizado en: "+ e);
        }
    }
    
    
    //-------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------    
    
    
  
    public void Consultar(){
        String sql= "select * from persodb";
        
        try {
            conet= con1.getConnection();
            st= conet.createStatement();
            rs=st.executeQuery(sql);
            Object[] personajes = new Object[7];
            modelo=(DefaultTableModel) TablaDB.getModel();
            while(rs.next()){
                personajes[0]= rs.getInt("Id");
                personajes[1]= rs.getString("Apodo");
                personajes[2]= rs.getString("Clase");
                personajes[3]= rs.getInt("Reflejo");
                personajes[4]= rs.getInt("Habilidada");
                personajes[5]= rs.getInt("Tc");
                personajes[6]= rs.getInt("Humanidad");
                modelo.addRow(personajes);
            }
            TablaDB.setModel(modelo);
        } catch (Exception e) {
        }
    }
    public void limpiarTabla(){
        for(int i=0; i <=TablaDB.getRowCount(); i++){
            modelo.removeRow(i);
            i--;
        }
    }
    
    public void atualizar(){
        String apodo= txtApodoDB.getText();
        String clase = (String) BoxClaseDB.getSelectedItem();
        int reflejo= Integer.parseInt(txtReflejosDB.getText());
        int habilidadeA= Integer.parseInt(txtHabilidadArmasDB.getText());
        int tc= Integer.parseInt(txtTipoCorporalDB.getText());
        int humanidad= Integer.parseInt(txtHumanidadDB.getText());
        
        try{
            if((reflejo <=10 && reflejo >=1)&&(habilidadeA <=10 && habilidadeA >=1)&&(tc <=10 && tc>=1)){
                String sql = "update persodb set Apodo= '"+apodo +"', Clase = '"+clase +"', Reflejo = '"+reflejo +"', Habilidada= '"+habilidadeA +"', Tc= '"+tc +"', Humanidad= '"+humanidad +"' where id = " +iddb;
                connect = connUno.getConnection();
                st = connect.createStatement();
                st.executeUpdate(sql);
                limpiarStaxTABLA();
                JOptionPane.showMessageDialog(null, "Se atualizado Tu Personaje 😊");
                limpiarTabla();
                
            }
        }
        catch(Exception e){
            System.out.println("Error: " +e);
        }
    }
    
    
    
    
    public void Eliminar(){
        int fila = TablaDB.getSelectedRow();
        if(fila == 1){
            JOptionPane.showMessageDialog(null, "Seleciona un Personaje");
        } else{
        try{
            
            String sql = "delete from persodb where id ="+iddb;
            connect = connUno.getConnection();
            st = connect.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se a Eliminado el Personaje 😊");
            limpiarTabla();
        }
        catch(Exception e){
            System.out.println("Error: " +e);
        }
        }
    }
    
    
    public void ClickTabla(){
        int fila = TablaDB.getSelectedRow();
        if(fila == -1){
            JOptionPane.showConfirmDialog(null, "Seleciona una ID Personaje");
        }
        else{
            iddb = Integer.parseInt((String)TablaDB.getValueAt(fila, 0).toString());
            String ApodoDB = (String)TablaDB.getValueAt(fila, 1);
            String ClaseDB = (String)TablaDB.getValueAt(fila, 2).toString();
            int ReflejosDB = Integer.parseInt((String)TablaDB.getValueAt(fila, 3).toString());
            int HabilidadaDB = Integer.parseInt((String)TablaDB.getValueAt(fila, 4).toString());
            int TipocorporalDB = Integer.parseInt((String)TablaDB.getValueAt(fila, 5).toString());
            int HumanidadDB = Integer.parseInt((String)TablaDB.getValueAt(fila, 6).toString());
            
            
            txtID.setText(""+iddb);
            txtApodoDB.setText(String.valueOf(ApodoDB));
            BoxClaseDB.setSelectedItem(String.valueOf(ClaseDB));
            txtReflejosDB.setText(String.valueOf(ReflejosDB));
            txtHabilidadArmasDB.setText(String.valueOf(HabilidadaDB));
            txtTipoCorporalDB.setText(String.valueOf(TipocorporalDB));
            txtHumanidadDB.setText(String.valueOf(HumanidadDB));
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------
    //metodos de limpieza
    
    public void limpiarStax(){
        txtApodo.setText("");
        txtReflejos.setText("");
        txtHabilidadArmas.setText("");
        txtTipoCorporal.setText("");
        txtHumanidad.setText("");
    }
    public void limpiarStaxTABLA(){
        txtApodoDB.setText("");
        txtID.setEnabled(false);
        txtID.setText("");
        txtReflejosDB.setText("");
        txtHabilidadArmasDB.setText("");
        txtTipoCorporalDB.setText("");
        txtHumanidadDB.setText("");
    }
    
    //-------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------
    //Animaciones de colores para Botones
    public void BotonXentrada(){
        CuadradoDeCierre.setBackground(Color.red);
        XdeCierre.setForeground(Color.white);
    }
    public void BotonXSalida(){
        CuadradoDeCierre.setBackground(new Color(153, 0, 0));
        XdeCierre.setForeground(Color.BLACK);
    }
    public void BotonBentrada(){
        BotonBienvenida.setForeground(Color.red);
    }
    public void BotonBsalida(){
        BotonBienvenida.setForeground(new Color(153,0,51));
    }
    public void BotonCPentrada(){
        BotonCP.setBackground(Color.gray);
        jlCreacionpeso.setForeground(Color.WHITE);
    }
    public void BotonCPsalida(){
        BotonCP.setBackground(new Color(51, 51, 51));
        jlCreacionpeso.setForeground(new Color(153,0,51));
    }
    public void BotonLCentrada(){
        BotonLC.setBackground(Color.gray);
        jlLIstaperso.setForeground(Color.WHITE);
    }
    public void BotonLCsalida(){
        BotonLC.setBackground(new Color(51, 51, 51));
        jlLIstaperso.setForeground(new Color(153,0,51));
    }
    public void BotonCrearEntrada(){
        BotonCrear.setBackground(Color.gray);
        jLCrear.setForeground(Color.WHITE);
    }
    public void BotonCrearSalida(){
        BotonCrear.setBackground(new Color(51, 51, 51));
        jLCrear.setForeground(new Color(153,0,51));
    }
    public void BotonActualizarENTRADA(){
        BotonAtualizar.setBackground(Color.gray);
        jLActualizar.setForeground(Color.WHITE);
    }
    public void BotonActualizarSALIDA(){
        BotonAtualizar.setBackground(new Color(51, 51, 51));
        jLActualizar.setForeground(new Color(153,0,51));
    }
    public void BotonEliminarENTRADA(){
        BotonEliminar.setBackground(Color.gray);
        jLEliminar.setForeground(Color.WHITE);
    }
    public void BotonEliminarSALIDA(){
        BotonEliminar.setBackground(new Color(51, 51, 51));
        jLEliminar.setForeground(new Color(153,0,51));
    }
    public void BotonLimpiarENTRADA(){
        BotonLimpiar.setBackground(Color.gray);
        jLLimpiar.setForeground(Color.WHITE);
    }
    public void BotonLimpiarSALIDA(){
        BotonLimpiar.setBackground(new Color(51, 51, 51));
        jLLimpiar.setForeground(new Color(153,0,51));
    }
    public void BotonCreditosENTRADA(){
        BotonCreditos.setBackground(Color.red);
    }
    public void BotonCreditosSALIDA(){
        BotonCreditos.setBackground(new Color(153, 0, 51));
    }
    
    
    //-------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------
    
    //Eventos de cliker Menus
    public void ClickBienvenidos(){
        MenuPrincipal.setVisible(true);
        MenuCreacion.setVisible(false);
        MenuLista.setVisible(false);
        MenuCreditos.setVisible(false);
    }
    public void ClickCP(){
        MenuPrincipal.setVisible(false);
        MenuCreacion.setVisible(true);
        MenuLista.setVisible(false);
        MenuCreditos.setVisible(false);
    }
    public void ClickLC(){
        MenuPrincipal.setVisible(false);
        MenuCreacion.setVisible(false);
        MenuLista.setVisible(true);
        MenuCreditos.setVisible(false);
    }
    public void ClickCreditos(){
        MenuPrincipal.setVisible(false);
        MenuCreacion.setVisible(false);
        MenuLista.setVisible(false);
        MenuCreditos.setVisible(true);
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BarraDeMovimiento = new javax.swing.JPanel();
        CuadradoDeCierre = new javax.swing.JPanel();
        XdeCierre = new javax.swing.JLabel();
        BotonBienvenida = new javax.swing.JLabel();
        BotonCP = new javax.swing.JPanel();
        jlCreacionpeso = new javax.swing.JLabel();
        BotonLC = new javax.swing.JPanel();
        jlLIstaperso = new javax.swing.JLabel();
        BotonCreditos = new javax.swing.JPanel();
        jLCreditos = new javax.swing.JLabel();
        PanelMenus = new javax.swing.JPanel();
        MenuPrincipal = new javax.swing.JPanel();
        FondoB = new javax.swing.JLabel();
        MenuCreacion = new javax.swing.JPanel();
        FondoCp = new javax.swing.JLabel();
        jLApodo = new javax.swing.JLabel();
        jLClase = new javax.swing.JLabel();
        jLReflejo = new javax.swing.JLabel();
        jLHabilidadArmas = new javax.swing.JLabel();
        jLTipoCorporal = new javax.swing.JLabel();
        jLHumanidad = new javax.swing.JLabel();
        txtApodo = new javax.swing.JTextField();
        BoxClase = new javax.swing.JComboBox<>();
        txtReflejos = new javax.swing.JTextField();
        txtHabilidadArmas = new javax.swing.JTextField();
        txtTipoCorporal = new javax.swing.JTextField();
        txtHumanidad = new javax.swing.JTextField();
        BotonCrear = new javax.swing.JPanel();
        jLCrear = new javax.swing.JLabel();
        MenuLista = new javax.swing.JPanel();
        FondoLista = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaDB = new javax.swing.JTable();
        jLApodo2 = new javax.swing.JLabel();
        jLClase2 = new javax.swing.JLabel();
        jLReflejos2 = new javax.swing.JLabel();
        jLHabilidadArmas2 = new javax.swing.JLabel();
        jLTipoCorporal2 = new javax.swing.JLabel();
        jLHumanidad2 = new javax.swing.JLabel();
        txtApodoDB = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        BoxClaseDB = new javax.swing.JComboBox<>();
        txtReflejosDB = new javax.swing.JTextField();
        txtHabilidadArmasDB = new javax.swing.JTextField();
        txtTipoCorporalDB = new javax.swing.JTextField();
        txtHumanidadDB = new javax.swing.JTextField();
        BotonAtualizar = new javax.swing.JPanel();
        jLActualizar = new javax.swing.JLabel();
        BotonEliminar = new javax.swing.JPanel();
        jLEliminar = new javax.swing.JLabel();
        BotonLimpiar = new javax.swing.JPanel();
        jLLimpiar = new javax.swing.JLabel();
        MenuCreditos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BarraDeMovimiento.setBackground(new java.awt.Color(153, 0, 51));
        BarraDeMovimiento.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BarraDeMovimientoMouseDragged(evt);
            }
        });
        BarraDeMovimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BarraDeMovimientoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout BarraDeMovimientoLayout = new javax.swing.GroupLayout(BarraDeMovimiento);
        BarraDeMovimiento.setLayout(BarraDeMovimientoLayout);
        BarraDeMovimientoLayout.setHorizontalGroup(
            BarraDeMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        BarraDeMovimientoLayout.setVerticalGroup(
            BarraDeMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(BarraDeMovimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 780, 30));

        CuadradoDeCierre.setBackground(new java.awt.Color(153, 0, 51));

        XdeCierre.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        XdeCierre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        XdeCierre.setText("X");
        XdeCierre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        XdeCierre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XdeCierreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                XdeCierreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                XdeCierreMouseExited(evt);
            }
        });

        javax.swing.GroupLayout CuadradoDeCierreLayout = new javax.swing.GroupLayout(CuadradoDeCierre);
        CuadradoDeCierre.setLayout(CuadradoDeCierreLayout);
        CuadradoDeCierreLayout.setHorizontalGroup(
            CuadradoDeCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(XdeCierre, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        CuadradoDeCierreLayout.setVerticalGroup(
            CuadradoDeCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(XdeCierre, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(CuadradoDeCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        BotonBienvenida.setFont(new java.awt.Font("Roboto", 3, 40)); // NOI18N
        BotonBienvenida.setForeground(new java.awt.Color(153, 0, 51));
        BotonBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotonBienvenida.setText("Bienvenidos");
        BotonBienvenida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonBienvenida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonBienvenidaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonBienvenidaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonBienvenidaMouseExited(evt);
            }
        });
        jPanel1.add(BotonBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 230, 80));

        BotonCP.setBackground(new java.awt.Color(51, 51, 51));

        jlCreacionpeso.setBackground(new java.awt.Color(255, 255, 255));
        jlCreacionpeso.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jlCreacionpeso.setForeground(new java.awt.Color(153, 0, 51));
        jlCreacionpeso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlCreacionpeso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fotos/icon user.png"))); // NOI18N
        jlCreacionpeso.setText("Creacion Personaje");
        jlCreacionpeso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlCreacionpeso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlCreacionpesoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlCreacionpesoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlCreacionpesoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout BotonCPLayout = new javax.swing.GroupLayout(BotonCP);
        BotonCP.setLayout(BotonCPLayout);
        BotonCPLayout.setHorizontalGroup(
            BotonCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlCreacionpeso, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        BotonCPLayout.setVerticalGroup(
            BotonCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlCreacionpeso, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel1.add(BotonCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 230, 80));

        BotonLC.setBackground(new java.awt.Color(51, 51, 51));

        jlLIstaperso.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jlLIstaperso.setForeground(new java.awt.Color(153, 0, 51));
        jlLIstaperso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlLIstaperso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fotos/icon save.png"))); // NOI18N
        jlLIstaperso.setText("Lista de Creados");
        jlLIstaperso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlLIstaperso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlLIstapersoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlLIstapersoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlLIstapersoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout BotonLCLayout = new javax.swing.GroupLayout(BotonLC);
        BotonLC.setLayout(BotonLCLayout);
        BotonLCLayout.setHorizontalGroup(
            BotonLCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlLIstaperso, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        BotonLCLayout.setVerticalGroup(
            BotonLCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlLIstaperso, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel1.add(BotonLC, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 230, 80));

        BotonCreditos.setBackground(new java.awt.Color(153, 0, 51));

        jLCreditos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLCreditos.setIcon(new javax.swing.ImageIcon("C:\\Users\\andres marin\\Desktop\\᲼᲼᲼᲼᲼\\Ilustración.png")); // NOI18N
        jLCreditos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLCreditos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLCreditosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLCreditosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLCreditosMouseExited(evt);
            }
        });

        javax.swing.GroupLayout BotonCreditosLayout = new javax.swing.GroupLayout(BotonCreditos);
        BotonCreditos.setLayout(BotonCreditosLayout);
        BotonCreditosLayout.setHorizontalGroup(
            BotonCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLCreditos, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        BotonCreditosLayout.setVerticalGroup(
            BotonCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLCreditos, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(BotonCreditos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 40, 40));

        PanelMenus.setBackground(new java.awt.Color(0, 0, 0));
        PanelMenus.setLayout(new java.awt.CardLayout());

        MenuPrincipal.setBackground(new java.awt.Color(0, 0, 0));

        FondoB.setForeground(new java.awt.Color(255, 255, 255));
        FondoB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fotos/fondo.gif"))); // NOI18N

        javax.swing.GroupLayout MenuPrincipalLayout = new javax.swing.GroupLayout(MenuPrincipal);
        MenuPrincipal.setLayout(MenuPrincipalLayout);
        MenuPrincipalLayout.setHorizontalGroup(
            MenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FondoB, javax.swing.GroupLayout.PREFERRED_SIZE, 584, Short.MAX_VALUE))
        );
        MenuPrincipalLayout.setVerticalGroup(
            MenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPrincipalLayout.createSequentialGroup()
                .addComponent(FondoB, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PanelMenus.add(MenuPrincipal, "card2");

        MenuCreacion.setBackground(new java.awt.Color(0, 0, 0));

        FondoCp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fotos/a1.gif"))); // NOI18N

        jLApodo.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLApodo.setForeground(new java.awt.Color(255, 0, 0));
        jLApodo.setText("Apodo");

        jLClase.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLClase.setForeground(new java.awt.Color(255, 0, 0));
        jLClase.setText("Clase");

        jLReflejo.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLReflejo.setForeground(new java.awt.Color(255, 0, 0));
        jLReflejo.setText("Reflejos");

        jLHabilidadArmas.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLHabilidadArmas.setForeground(new java.awt.Color(255, 0, 0));
        jLHabilidadArmas.setText("Habilidad Armas");

        jLTipoCorporal.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLTipoCorporal.setForeground(new java.awt.Color(255, 0, 0));
        jLTipoCorporal.setText("Tipo Corporal");

        jLHumanidad.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLHumanidad.setForeground(new java.awt.Color(255, 0, 0));
        jLHumanidad.setText("Humanidad");

        txtApodo.setBackground(new java.awt.Color(153, 0, 0));
        txtApodo.setFont(new java.awt.Font("Roboto", 3, 24)); // NOI18N
        txtApodo.setForeground(new java.awt.Color(255, 255, 255));
        txtApodo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApodo.setText("jTextField1");
        txtApodo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BoxClase.setBackground(new java.awt.Color(153, 0, 0));
        BoxClase.setFont(new java.awt.Font("Roboto", 3, 24)); // NOI18N
        BoxClase.setForeground(new java.awt.Color(255, 255, 255));
        BoxClase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rockero", "Mercenario", "NetRunner", "Tecnico", "Periodista", "Policia", "Ejecutivo", "Nomada", "Fixer" }));
        BoxClase.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtReflejos.setBackground(new java.awt.Color(153, 0, 0));
        txtReflejos.setFont(new java.awt.Font("Roboto", 3, 24)); // NOI18N
        txtReflejos.setForeground(new java.awt.Color(255, 255, 255));
        txtReflejos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtReflejos.setText("jTextField3");
        txtReflejos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtHabilidadArmas.setBackground(new java.awt.Color(153, 0, 0));
        txtHabilidadArmas.setFont(new java.awt.Font("Roboto", 3, 24)); // NOI18N
        txtHabilidadArmas.setForeground(new java.awt.Color(255, 255, 255));
        txtHabilidadArmas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHabilidadArmas.setText("jTextField4");
        txtHabilidadArmas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTipoCorporal.setBackground(new java.awt.Color(153, 0, 0));
        txtTipoCorporal.setFont(new java.awt.Font("Roboto", 3, 24)); // NOI18N
        txtTipoCorporal.setForeground(new java.awt.Color(255, 255, 255));
        txtTipoCorporal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipoCorporal.setText("jTextField5");
        txtTipoCorporal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtHumanidad.setBackground(new java.awt.Color(153, 0, 0));
        txtHumanidad.setFont(new java.awt.Font("Roboto", 3, 24)); // NOI18N
        txtHumanidad.setForeground(new java.awt.Color(255, 255, 255));
        txtHumanidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHumanidad.setText("jTextField6");
        txtHumanidad.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BotonCrear.setBackground(new java.awt.Color(51, 51, 51));

        jLCrear.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        jLCrear.setForeground(new java.awt.Color(153, 0, 51));
        jLCrear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLCrear.setText("Crear");
        jLCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLCrearMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLCrearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLCrearMouseExited(evt);
            }
        });

        javax.swing.GroupLayout BotonCrearLayout = new javax.swing.GroupLayout(BotonCrear);
        BotonCrear.setLayout(BotonCrearLayout);
        BotonCrearLayout.setHorizontalGroup(
            BotonCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLCrear, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
        );
        BotonCrearLayout.setVerticalGroup(
            BotonCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLCrear, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout MenuCreacionLayout = new javax.swing.GroupLayout(MenuCreacion);
        MenuCreacion.setLayout(MenuCreacionLayout);
        MenuCreacionLayout.setHorizontalGroup(
            MenuCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuCreacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BotonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(MenuCreacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FondoCp, javax.swing.GroupLayout.PREFERRED_SIZE, 510, Short.MAX_VALUE)
                    .addGroup(MenuCreacionLayout.createSequentialGroup()
                        .addGroup(MenuCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLTipoCorporal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLHabilidadArmas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLHumanidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLReflejo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLClase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLApodo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(MenuCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtApodo)
                            .addComponent(txtReflejos)
                            .addComponent(txtHabilidadArmas)
                            .addComponent(txtHumanidad)
                            .addComponent(txtTipoCorporal)
                            .addComponent(BoxClase, 0, 312, Short.MAX_VALUE))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        MenuCreacionLayout.setVerticalGroup(
            MenuCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuCreacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FondoCp, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MenuCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtApodo)
                    .addComponent(jLApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MenuCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLClase, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoxClase))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(MenuCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtReflejos)
                    .addComponent(jLReflejo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MenuCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHabilidadArmas)
                    .addComponent(jLHabilidadArmas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MenuCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLTipoCorporal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTipoCorporal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(MenuCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuCreacionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtHumanidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuCreacionLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLHumanidad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(BotonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );

        PanelMenus.add(MenuCreacion, "card3");

        MenuLista.setBackground(new java.awt.Color(0, 0, 0));

        FondoLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fotos/fondoTabla.gif"))); // NOI18N

        TablaDB.setBackground(new java.awt.Color(153, 0, 51));
        TablaDB.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        TablaDB.setForeground(new java.awt.Color(255, 255, 255));
        TablaDB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Apodo", "Clase", "Reflejos", "HabilidadA", "Tc", "Humanidad"
            }
        ));
        TablaDB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaDBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaDB);

        jLApodo2.setFont(new java.awt.Font("Roboto Black", 0, 16)); // NOI18N
        jLApodo2.setForeground(new java.awt.Color(255, 0, 0));
        jLApodo2.setText("Apodo");

        jLClase2.setFont(new java.awt.Font("Roboto Black", 0, 16)); // NOI18N
        jLClase2.setForeground(new java.awt.Color(255, 0, 0));
        jLClase2.setText("Clase");

        jLReflejos2.setFont(new java.awt.Font("Roboto Black", 0, 16)); // NOI18N
        jLReflejos2.setForeground(new java.awt.Color(255, 0, 0));
        jLReflejos2.setText("Reflejos");

        jLHabilidadArmas2.setFont(new java.awt.Font("Roboto Black", 0, 16)); // NOI18N
        jLHabilidadArmas2.setForeground(new java.awt.Color(255, 0, 0));
        jLHabilidadArmas2.setText("Habilidad Armas");

        jLTipoCorporal2.setFont(new java.awt.Font("Roboto Black", 0, 16)); // NOI18N
        jLTipoCorporal2.setForeground(new java.awt.Color(255, 0, 0));
        jLTipoCorporal2.setText("Tipo Corporal");

        jLHumanidad2.setFont(new java.awt.Font("Roboto Black", 0, 16)); // NOI18N
        jLHumanidad2.setForeground(new java.awt.Color(255, 0, 0));
        jLHumanidad2.setText("Humanidad");

        txtApodoDB.setBackground(new java.awt.Color(153, 0, 0));
        txtApodoDB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtApodoDB.setForeground(new java.awt.Color(255, 255, 255));
        txtApodoDB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApodoDB.setText("jTextField1");
        txtApodoDB.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtID.setBackground(new java.awt.Color(153, 0, 0));
        txtID.setForeground(new java.awt.Color(255, 255, 255));
        txtID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtID.setText("jTextField1");
        txtID.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtID.setDisabledTextColor(new java.awt.Color(153, 0, 0));

        BoxClaseDB.setBackground(new java.awt.Color(153, 0, 0));
        BoxClaseDB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        BoxClaseDB.setForeground(new java.awt.Color(255, 255, 255));
        BoxClaseDB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rockero", "Mercenario", "NetRunner", "Tecnico", "Periodista", "Policia", "Ejecutivo", "Nomada", "Fixer" }));
        BoxClaseDB.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtReflejosDB.setBackground(new java.awt.Color(153, 0, 0));
        txtReflejosDB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtReflejosDB.setForeground(new java.awt.Color(255, 255, 255));
        txtReflejosDB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtReflejosDB.setText("jTextField1");
        txtReflejosDB.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtHabilidadArmasDB.setBackground(new java.awt.Color(153, 0, 0));
        txtHabilidadArmasDB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtHabilidadArmasDB.setForeground(new java.awt.Color(255, 255, 255));
        txtHabilidadArmasDB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHabilidadArmasDB.setText("jTextField1");
        txtHabilidadArmasDB.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTipoCorporalDB.setBackground(new java.awt.Color(153, 0, 0));
        txtTipoCorporalDB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtTipoCorporalDB.setForeground(new java.awt.Color(255, 255, 255));
        txtTipoCorporalDB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipoCorporalDB.setText("jTextField1");
        txtTipoCorporalDB.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtHumanidadDB.setBackground(new java.awt.Color(153, 0, 0));
        txtHumanidadDB.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtHumanidadDB.setForeground(new java.awt.Color(255, 255, 255));
        txtHumanidadDB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHumanidadDB.setText("jTextField1");
        txtHumanidadDB.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BotonAtualizar.setBackground(new java.awt.Color(51, 51, 51));

        jLActualizar.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLActualizar.setForeground(new java.awt.Color(153, 0, 51));
        jLActualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLActualizar.setText("Actualizar");
        jLActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLActualizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLActualizarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout BotonAtualizarLayout = new javax.swing.GroupLayout(BotonAtualizar);
        BotonAtualizar.setLayout(BotonAtualizarLayout);
        BotonAtualizarLayout.setHorizontalGroup(
            BotonAtualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        BotonAtualizarLayout.setVerticalGroup(
            BotonAtualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        BotonEliminar.setBackground(new java.awt.Color(51, 51, 51));

        jLEliminar.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLEliminar.setForeground(new java.awt.Color(153, 0, 51));
        jLEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLEliminar.setText("Eliminar");
        jLEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLEliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLEliminarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout BotonEliminarLayout = new javax.swing.GroupLayout(BotonEliminar);
        BotonEliminar.setLayout(BotonEliminarLayout);
        BotonEliminarLayout.setHorizontalGroup(
            BotonEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );
        BotonEliminarLayout.setVerticalGroup(
            BotonEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        BotonLimpiar.setBackground(new java.awt.Color(51, 51, 51));

        jLLimpiar.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLLimpiar.setForeground(new java.awt.Color(153, 0, 51));
        jLLimpiar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLLimpiar.setText("Limpiar");
        jLLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLLimpiarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLLimpiarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLLimpiarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout BotonLimpiarLayout = new javax.swing.GroupLayout(BotonLimpiar);
        BotonLimpiar.setLayout(BotonLimpiarLayout);
        BotonLimpiarLayout.setHorizontalGroup(
            BotonLimpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BotonLimpiarLayout.setVerticalGroup(
            BotonLimpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout MenuListaLayout = new javax.swing.GroupLayout(MenuLista);
        MenuLista.setLayout(MenuListaLayout);
        MenuListaLayout.setHorizontalGroup(
            MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(MenuListaLayout.createSequentialGroup()
                        .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLHumanidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MenuListaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(BotonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BotonLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(MenuListaLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtHumanidadDB))))
                    .addGroup(MenuListaLayout.createSequentialGroup()
                        .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(MenuListaLayout.createSequentialGroup()
                                .addComponent(jLApodo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(41, 41, 41)
                                .addComponent(txtApodoDB, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(FondoLista, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addGroup(MenuListaLayout.createSequentialGroup()
                        .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLClase2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLReflejos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLHabilidadArmas2, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(jLTipoCorporal2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTipoCorporalDB)
                            .addComponent(txtHabilidadArmasDB)
                            .addComponent(txtReflejosDB)
                            .addComponent(BoxClaseDB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        MenuListaLayout.setVerticalGroup(
            MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FondoLista, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLApodo2)
                    .addComponent(txtApodoDB, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLClase2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(BoxClaseDB, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLReflejos2)
                    .addComponent(txtReflejosDB, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLHabilidadArmas2)
                    .addComponent(txtHabilidadArmasDB, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLTipoCorporal2)
                    .addComponent(txtTipoCorporalDB, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLHumanidad2)
                    .addComponent(txtHumanidadDB, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MenuListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BotonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BotonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonAtualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelMenus.add(MenuLista, "card4");

        MenuCreditos.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Gracias por usar mi Programa");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fotos/yo.gif"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Esta en una alfa 0.1 del Programa de Creacion Cyberpunk 2020 ");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Basandome en el manual de la segunda edicion eh creado un sistema el cual guarda tu personaje");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Junto a tus estadistica (Aun faltan muchas como la ficha lo indica) al ser alfa aun queda mucho por");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Pulir. aun asi posee las estadisticas principales. espero que dentro de un futuro este proyecto Tendra");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText(" todas las estadisticas pedidas por la ficha del jugador y un avanze de lanzado de dados.");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Gracias denuevo espero te haya gustado :0");

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Paracyte in love");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fotos/logo23.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fotos/ed2.gif"))); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 0, 0), new java.awt.Color(153, 0, 0)));

        javax.swing.GroupLayout MenuCreditosLayout = new javax.swing.GroupLayout(MenuCreditos);
        MenuCreditos.setLayout(MenuCreditosLayout);
        MenuCreditosLayout.setHorizontalGroup(
            MenuCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuCreditosLayout.createSequentialGroup()
                .addGroup(MenuCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, MenuCreditosLayout.createSequentialGroup()
                        .addGroup(MenuCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(MenuCreditosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(MenuCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuCreditosLayout.createSequentialGroup()
                                        .addGroup(MenuCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(MenuCreditosLayout.createSequentialGroup()
                                        .addGroup(MenuCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3)
                                            .addGroup(MenuCreditosLayout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(MenuCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuCreditosLayout.createSequentialGroup()
                                                .addGap(0, 61, Short.MAX_VALUE)
                                                .addComponent(jLabel9)
                                                .addGap(9, 9, 9))))))
                            .addGroup(MenuCreditosLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)))
                        .addGap(9, 9, 9))
                    .addGroup(MenuCreditosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        MenuCreditosLayout.setVerticalGroup(
            MenuCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuCreditosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MenuCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(MenuCreditosLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MenuCreditosLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38))
        );

        PanelMenus.add(MenuCreditos, "card5");

        jPanel1.add(PanelMenus, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 590, 590));

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



//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------

//EVENTO colores Para Todos los botones
    private void XdeCierreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XdeCierreMouseEntered
        BotonXentrada();
    }//GEN-LAST:event_XdeCierreMouseEntered

    private void XdeCierreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XdeCierreMouseExited
        BotonXSalida();
    }//GEN-LAST:event_XdeCierreMouseExited

    private void BotonBienvenidaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonBienvenidaMouseEntered
        BotonBentrada();
    }//GEN-LAST:event_BotonBienvenidaMouseEntered

    private void BotonBienvenidaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonBienvenidaMouseExited
        BotonBsalida();
    }//GEN-LAST:event_BotonBienvenidaMouseExited

    private void jlCreacionpesoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCreacionpesoMouseEntered
        BotonCPentrada();
    }//GEN-LAST:event_jlCreacionpesoMouseEntered

    private void jlCreacionpesoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCreacionpesoMouseExited
        BotonCPsalida();
    }//GEN-LAST:event_jlCreacionpesoMouseExited

    private void jlLIstapersoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlLIstapersoMouseEntered
        BotonLCentrada();
    }//GEN-LAST:event_jlLIstapersoMouseEntered

    private void jlLIstapersoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlLIstapersoMouseExited
        BotonLCsalida();
    }//GEN-LAST:event_jlLIstapersoMouseExited

    private void jLCrearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLCrearMouseEntered
        BotonCrearEntrada();
    }//GEN-LAST:event_jLCrearMouseEntered

    private void jLCrearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLCrearMouseExited
        BotonCrearSalida();
    }//GEN-LAST:event_jLCrearMouseExited

    private void jLActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLActualizarMouseEntered
        BotonActualizarENTRADA();
    }//GEN-LAST:event_jLActualizarMouseEntered

    private void jLActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLActualizarMouseExited
        BotonActualizarSALIDA();
    }//GEN-LAST:event_jLActualizarMouseExited

    private void jLEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLEliminarMouseEntered
        BotonEliminarENTRADA();
    }//GEN-LAST:event_jLEliminarMouseEntered

    private void jLEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLEliminarMouseExited
        BotonEliminarSALIDA();
    }//GEN-LAST:event_jLEliminarMouseExited

    private void jLLimpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLLimpiarMouseEntered
        BotonLimpiarENTRADA();
    }//GEN-LAST:event_jLLimpiarMouseEntered

    private void jLLimpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLLimpiarMouseExited
        BotonLimpiarSALIDA();
    }//GEN-LAST:event_jLLimpiarMouseExited









//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//EVENTO Clickers Botones Principales Y Barra de Movimiento
    private void XdeCierreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XdeCierreMouseClicked
        System.exit(0);
    }//GEN-LAST:event_XdeCierreMouseClicked

    private void BarraDeMovimientoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraDeMovimientoMousePressed
        // Evento de Ejes x y
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_BarraDeMovimientoMousePressed

    private void BarraDeMovimientoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraDeMovimientoMouseDragged
        // evento de arrastrar
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_BarraDeMovimientoMouseDragged

    private void BotonBienvenidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonBienvenidaMouseClicked
        ClickBienvenidos();
    }//GEN-LAST:event_BotonBienvenidaMouseClicked

    private void jlCreacionpesoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCreacionpesoMouseClicked
        ClickCP();
    }//GEN-LAST:event_jlCreacionpesoMouseClicked

    private void jlLIstapersoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlLIstapersoMouseClicked
        ClickLC();
    }//GEN-LAST:event_jlLIstapersoMouseClicked

    private void jLCreditosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLCreditosMouseExited
        BotonCreditosSALIDA();
    }//GEN-LAST:event_jLCreditosMouseExited

    private void jLCreditosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLCreditosMouseEntered
        BotonCreditosENTRADA();
    }//GEN-LAST:event_jLCreditosMouseEntered

    private void jLCreditosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLCreditosMouseClicked
        ClickCreditos();
    }//GEN-LAST:event_jLCreditosMouseClicked

    
    
    
    
    
    
    //evento de Seleciones y Botones
    //evento de creacion
    private void jLCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLCrearMouseClicked
        BotonCrearP();
        Consultar();
    }//GEN-LAST:event_jLCrearMouseClicked

    private void TablaDBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaDBMouseClicked
        ClickTabla();
    }//GEN-LAST:event_TablaDBMouseClicked

    private void jLActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLActualizarMouseClicked
        atualizar();
        Consultar();
    }//GEN-LAST:event_jLActualizarMouseClicked

    private void jLEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLEliminarMouseClicked
        Eliminar();
        Consultar();
    }//GEN-LAST:event_jLEliminarMouseClicked

    private void jLLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLLimpiarMouseClicked
        limpiarStaxTABLA();
    }//GEN-LAST:event_jLLimpiarMouseClicked

    
    
    
    
    
    
    
    
    
    
    
    
    
    //Codigo interno del jframe
    
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
            java.util.logging.Logger.getLogger(Menus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BarraDeMovimiento;
    private javax.swing.JPanel BotonAtualizar;
    private javax.swing.JLabel BotonBienvenida;
    private javax.swing.JPanel BotonCP;
    private javax.swing.JPanel BotonCrear;
    private javax.swing.JPanel BotonCreditos;
    private javax.swing.JPanel BotonEliminar;
    private javax.swing.JPanel BotonLC;
    private javax.swing.JPanel BotonLimpiar;
    private javax.swing.JComboBox<String> BoxClase;
    private javax.swing.JComboBox<String> BoxClaseDB;
    private javax.swing.JPanel CuadradoDeCierre;
    private javax.swing.JLabel FondoB;
    private javax.swing.JLabel FondoCp;
    private javax.swing.JLabel FondoLista;
    private javax.swing.JPanel MenuCreacion;
    private javax.swing.JPanel MenuCreditos;
    private javax.swing.JPanel MenuLista;
    private javax.swing.JPanel MenuPrincipal;
    private javax.swing.JPanel PanelMenus;
    private javax.swing.JTable TablaDB;
    private javax.swing.JLabel XdeCierre;
    private javax.swing.JLabel jLActualizar;
    private javax.swing.JLabel jLApodo;
    private javax.swing.JLabel jLApodo2;
    private javax.swing.JLabel jLClase;
    private javax.swing.JLabel jLClase2;
    private javax.swing.JLabel jLCrear;
    private javax.swing.JLabel jLCreditos;
    private javax.swing.JLabel jLEliminar;
    private javax.swing.JLabel jLHabilidadArmas;
    private javax.swing.JLabel jLHabilidadArmas2;
    private javax.swing.JLabel jLHumanidad;
    private javax.swing.JLabel jLHumanidad2;
    private javax.swing.JLabel jLLimpiar;
    private javax.swing.JLabel jLReflejo;
    private javax.swing.JLabel jLReflejos2;
    private javax.swing.JLabel jLTipoCorporal;
    private javax.swing.JLabel jLTipoCorporal2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlCreacionpeso;
    private javax.swing.JLabel jlLIstaperso;
    private javax.swing.JTextField txtApodo;
    private javax.swing.JTextField txtApodoDB;
    private javax.swing.JTextField txtHabilidadArmas;
    private javax.swing.JTextField txtHabilidadArmasDB;
    private javax.swing.JTextField txtHumanidad;
    private javax.swing.JTextField txtHumanidadDB;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtReflejos;
    private javax.swing.JTextField txtReflejosDB;
    private javax.swing.JTextField txtTipoCorporal;
    private javax.swing.JTextField txtTipoCorporalDB;
    // End of variables declaration//GEN-END:variables
}
