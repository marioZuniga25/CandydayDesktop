
package candydaydesktop;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.softday.candyday.db.ConexionMySQL;
import com.softday.candyday.model.Productos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author mazu1
 */
public class View extends javax.swing.JFrame {

    /**
     * Creates new form View
     */
    public View() throws Exception {
        initComponents();
        cargarTabla();
    }
    
   
        
    public void insertProducto(Productos p) throws Exception
    {
        //Definimos la consulta SQL que invoca al Stored Procedure:
        String sql = "INSERT INTO productos "
                + "(nombreProducto, marca, existencias, presentacion,"
                + "codigoBarras, estatus, precioVenta, tipo)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        //Aquí guardaremos los ID's que se generarán:
        //int idProductoGenerado = -1;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();
        
        //Con este objeto invocaremos al StoredProcedure:
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //Productos p = new Productos();
        //Establecemos los valores de lso parametros  de los datos personales 
        //en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:
        cstmt.setString(1, p.getNombreProducto());
        cstmt.setString(2, p.getMarca());
        cstmt.setInt(3, p.getExistencias());
        cstmt.setString(4, p.getPresentacion());
        cstmt.setString(5, p.getCodigoBarras());
        cstmt.setInt(6, p.getEstatus());
        cstmt.setInt(7, p.getPrecioVenta());
        cstmt.setString(8, p.getTipo());

        
        //Ejecutamos el Stored Procedure:
        cstmt.executeUpdate();
        /*
        //Recuperamos los ID's generados:
        idProductoGenerado = cstmt.getInt(7);
        
        p.setIdProducto(idProductoGenerado);
        
        */
        cstmt.close();
        connMySQL.close();
        
        //Devolvemos el ID de Cliente generado:
        //return idProductoGenerado;
    }
    
    
     public void updateProducto(Productos p) throws Exception
    {
        //Definimos la consulta SQL que invoca al Stored Procedure:
        String sql =    "UPDATE productos SET nombreProducto = ?, marca = ?, existencias = ?, presentacion = ?, codigoBarras = ?, estatus = ?, precioVenta = ?, Tipo = ? WHERE idProduco = ?";
        
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();
        
        //Con este objeto invocaremos al StoredProcedure:
        CallableStatement cstmt = conn.prepareCall(sql);
        
        int fila = jTable1.getSelectedRow();
        int idProducto = Integer.parseInt(jTable1.getValueAt(fila,0).toString());
        
        
        cstmt.setString(1, p.getNombreProducto());
        cstmt.setString(2, p.getMarca());
        cstmt.setInt(3, p.getExistencias());
        cstmt.setString(4, p.getPresentacion());
        cstmt.setString(5, p.getCodigoBarras());
        cstmt.setInt(6, p.getEstatus());
        cstmt.setInt(7, p.getPrecioVenta());
        cstmt.setString(8, p.getTipo());
        cstmt.setInt(9, idProducto);
        
        //Ejecutamos el Stored Procedure:
        cstmt.executeUpdate();
                
        cstmt.close();
        connMySQL.close();        
    }
    
    
    
    
    
    public void cargarTabla() throws SQLException{
        DefaultTableModel modeloTabla = (DefaultTableModel) jTable1.getModel();
        modeloTabla.setRowCount(0);
        PreparedStatement pstmt;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;
        try{
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        pstmt = conn.prepareStatement("SELECT * FROM productos;");
        rs = pstmt.executeQuery();
        rsmd = (ResultSetMetaData) rs.getMetaData();
        columnas = rsmd.getColumnCount();
        
        while (rs.next()){
            Object[] fila = new Object[columnas];
            for (int i = 0; i < columnas; i++) {
                fila[i] = rs.getObject(i+1);
            }
            modeloTabla.addRow(fila);}
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.toString());
                }
        
    }
    
    public void guardar(){
        
        Productos p = new Productos();
        
        p.setNombreProducto(jTextField1.getText());
        p.setMarca(jTextField2.getText());
        p.setExistencias(Integer.parseInt(jTextField3.getText()));
        p.setPresentacion(jTextField4.getText());
        p.setCodigoBarras(jTextField5.getText());
        p.setEstatus(Integer.parseInt(jTextField6.getText()));
        p.setPrecioVenta(Integer.parseInt(jTextField7.getText()));
        p.setTipo(jTextField8.getText());
        
        try {
            insertProducto(p);
        } catch (Exception ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idProducto", "NombreProducto", "Marca", "Existencias", "Pesentacion", "CodigoBarras", "Estatus", "Foto", "precioVenta", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 860, 290));

        jLabel1.setText("CandyDay");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, -1, -1));

        jLabel2.setText("Productos");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jLabel3.setText("Nombre:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 120, -1));

        jLabel4.setText("Marca:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 100, -1));

        jLabel5.setText("Existencias:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 110, -1));

        jLabel6.setText("Presentacion:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, -1, -1));
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 110, -1));

        jLabel7.setText("codigoBarras:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 140, -1));

        jLabel8.setText("Estatus:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, -1, -1));
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 80, -1));

        jLabel9.setText("Precio Venta:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, -1, -1));
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 80, -1));

        jLabel10.setText("Tipo:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, -1));
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 120, -1));

        jButton1.setText("Guardar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, -1, -1));

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 880, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        try{
            int fila = jTable1.getSelectedRow();
            int idProducto = Integer.parseInt(jTable1.getValueAt(fila, 0).toString());
            PreparedStatement pstmt;
            ResultSet rs;
            ConexionMySQL connMySQL = new ConexionMySQL();
            Connection conn = connMySQL.open();
            pstmt = conn.prepareStatement("SELECT nombreProducto, marca, existencias, presentacion, codigoBarras, estatus, precioVenta, tipo FROM productos WHERE idProducto = ?;");
            pstmt.setInt(1, idProducto);
            rs = pstmt.executeQuery();
            while(rs.next()){
                String.valueOf(idProducto);
                jTextField1.setText(rs.getString("nombreProducto"));
                jTextField2.setText(rs.getString("marca"));
                jTextField3.setText(rs.getString("existencias"));
                jTextField4.setText(rs.getString("presentacion"));
                jTextField5.setText(rs.getString("codigoBarras"));
                jTextField6.setText(rs.getString("estatus"));
                jTextField7.setText(rs.getString("precioVenta"));
                jTextField8.setText(rs.getString("tipo"));
                
            }
                    
        }catch(Exception ex){
            
            JOptionPane.showConfirmDialog(null, ex.toString());
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        guardar();
        
        JOptionPane.showConfirmDialog(null, "Producto guardado.");
        
        try {
            cargarTabla();
        } catch (SQLException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling 
        
        int fila = jTable1.getSelectedRow();
        int idProducto = Integer.parseInt(jTable1.getValueAt(fila,0).toString());
        try{
         PreparedStatement pstmt;
         ResultSet rs;
         ConexionMySQL conNube = new ConexionMySQL();
         Connection conn = conNube.open(); 
         pstmt = conn.prepareStatement("DELETE FROM productos WHERE idProducto = ?;");
         pstmt.setInt(1, idProducto);
         pstmt.executeUpdate();
         JOptionPane.showMessageDialog(null,"Eliminacion Exitosa :)");
         
         cargarTabla();
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex.toString());
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new View().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables

}
