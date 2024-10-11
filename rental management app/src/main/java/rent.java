
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Lucas
 */

public class rent extends javax.swing.JFrame {
    private String bikeid;
    private String customer_id;
    private int hours;
    private int totalCost;
    private String model;
    
    public rent() {
        initComponents();
        AutoComplete();
        table_update();
    }
    public rent(String bikeid, String customer_id, int hours, int totalCost, String model){
        this.bikeid = bikeid;
        this.customer_id = customer_id;
        this.hours = hours;
        this.totalCost = totalCost;
        this.model = model;
    }

    Connection con;
    ResultSet rs;
    ResultSet rs2;
    ResultSet rs5;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst3;
    PreparedStatement pst4; 
    PreparedStatement pst5;
    LinkedList<String> basket = new LinkedList<>();
    
    private void AutoComplete(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bikerent","root","");
            pst5 = con.prepareStatement("select * from customer");
            rs5 = pst5.executeQuery();
            
            
            while(rs5.next()){
                String name = rs5.getString("customer_name");
                name_dropdownlist.addItem(name);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(rent.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "didn't work class");
        } catch (SQLException ex) {
            Logger.getLogger(rent.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "didn't work sql");
        }
        
        AutoCompleteDecorator.decorate(name_dropdownlist);
    }
    
    public void table_update(){
        String strPR = (txtpricerange.getSelectedItem() == null) ? null : txtpricerange.getSelectedItem().toString(); //PR = price range
        String model = (choosemodel.getSelectedItem() == "Select model") ? null : choosemodel.getSelectedItem().toString();
        int c;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bikerent","root","");
            
            int minprice = 0;
            int maxprice = 0;
            if (strPR != null && strPR.indexOf(" - ") == -1) {
                strPR = strPR.replaceAll("\\D+", "");
                minprice = Integer.parseInt(strPR);
                maxprice = minprice;
            } else if (strPR!=null) {
                String[] parts = strPR.split(" - ");
                minprice = Integer.parseInt(parts[0]);
                maxprice = Integer.parseInt(parts[1]);
            }
            
            String query = "SELECT * FROM registration";
            //if no filter selected, query will not have WHERE clause and return all records
            //if some kind of filter is selected (model or PR), appropriate WHERE clause added to query
            if (model != null || (minprice != 0 && maxprice != 0)) { 
                query += " WHERE";
                if (model != null) {    //
                    query += " model = '" + model + "'";
                    if (minprice != 0 && maxprice != 0) {
                        query += " AND";
                    }
                }
                if (minprice != 0 && maxprice != 0) {
                    query += " cost >= " + minprice + " AND cost <= " + maxprice;
                }
            }
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet Rs = pst.executeQuery();
            ResultSetMetaData rd = Rs.getMetaData();
            c = rd.getColumnCount();
            DefaultTableModel d1 = (DefaultTableModel) jTable1.getModel();
            d1.setRowCount(0);

            while(Rs.next()){
                if(Rs.getBoolean("available")==true){
                    Vector v2 = new Vector();
                    for(int i=1;i<c;i++){
                        v2.add(Rs.getString("bikeID"));
                        v2.add(Rs.getString("model"));
                        v2.add(Rs.getInt("cost"));
                    }
                    d1.addRow(v2);
                }
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
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

        jDateChooserBeanInfo1 = new com.toedter.calendar.JDateChooserBeanInfo();
        dateUtil1 = new com.toedter.calendar.DateUtil();
        jDateChooserCellEditor1 = new com.toedter.calendar.JDateChooserCellEditor();
        jSpinFieldBeanInfo1 = new com.toedter.components.JSpinFieldBeanInfo();
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        testDateEvaluator1 = new com.toedter.calendar.demo.TestDateEvaluator();
        dateChooserPanel1 = new com.toedter.calendar.demo.DateChooserPanel();
        dateTimePicker2 = new com.github.lgooddatepicker.components.DateTimePicker();
        dateTimePicker3 = new com.github.lgooddatepicker.components.DateTimePicker();
        txthourdifference1 = new javax.swing.JTextField();
        jSlider2 = new javax.swing.JSlider();
        jSlider3 = new javax.swing.JSlider();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtcustomer_id = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        confirm_button = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtcost = new javax.swing.JTextField();
        name_dropdownlist = new javax.swing.JComboBox<>();
        choosemodel = new javax.swing.JComboBox<>();
        txtavl = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        dateTimePickerReturn = new com.github.lgooddatepicker.components.DateTimePicker();
        dateTimePickerStart = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel8 = new javax.swing.JLabel();
        txtpricerange = new javax.swing.JComboBox<>();
        pricerange_jlabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtsuspension = new javax.swing.JTextField();
        txtmodel = new javax.swing.JTextField();
        txtframe = new javax.swing.JTextField();
        txtTireTPI = new javax.swing.JTextField();
        txtwheelSize = new javax.swing.JTextField();
        speedSlider = new javax.swing.JSlider();
        comfortSlider = new javax.swing.JSlider();
        skillSlider = new javax.swing.JSlider();
        steepnessSlider = new javax.swing.JSlider();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        addToBasket = new javax.swing.JButton();
        clearBasket = new javax.swing.JButton();
        confirmBasket = new javax.swing.JButton();

        txthourdifference1.setEditable(false);

        jSlider2.setMaximum(5);
        jSlider2.setMinimum(1);

        jSlider3.setMaximum(5);
        jSlider3.setMinimum(1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rental management", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 12))); // NOI18N

        jLabel3.setText("Customer Name");
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtcustomer_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcustomer_idActionPerformed(evt);
            }
        });

        jLabel6.setText("Planned return date");
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        confirm_button.setText("Search for bike");
        confirm_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirm_buttonActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Cost");
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel4)
                .addGap(47, 47, 47)
                .addComponent(txtcost, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))))
        );

        name_dropdownlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_dropdownlistActionPerformed(evt);
            }
        });

        choosemodel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select model", "Urban", "Trail", "Mountain", "Electric" }));
        choosemodel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosemodelActionPerformed(evt);
            }
        });

        jLabel2.setText("Available:");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("h:mm"))));

        jLabel5.setText("Start time");
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtpricerange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpricerangeActionPerformed(evt);
            }
        });

        pricerange_jlabel.setText("Price range");
        pricerange_jlabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setText("-----Select bike automatically-----");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(confirm_button, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(choosemodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pricerange_jlabel)))
                        .addGap(18, 18, 18)
                        .addComponent(txtpricerange, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(name_dropdownlist, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(163, 163, 163))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(dateTimePickerStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dateTimePickerReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(197, 197, 197))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtavl, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(txtcustomer_id, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(txtcustomer_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtavl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(name_dropdownlist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateTimePickerStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(dateTimePickerReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(choosemodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpricerange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pricerange_jlabel))
                        .addGap(30, 30, 30)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(confirm_button, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))))
        );

        jTabbedPane1.addTab("Rent", jPanel1);

        jLabel7.setText("Model:");

        jLabel9.setText("Suspension:");

        jLabel10.setText("Frame:");

        jLabel11.setText("tire TPI:");

        jLabel12.setText("wheel size:");

        txtsuspension.setEditable(false);

        txtmodel.setEditable(false);

        txtframe.setEditable(false);

        txtTireTPI.setEditable(false);

        txtwheelSize.setEditable(false);

        speedSlider.setMaximum(10);
        speedSlider.setValue(5);
        speedSlider.setToolTipText("");

        comfortSlider.setMaximum(10);
        comfortSlider.setValue(5);

        skillSlider.setMaximum(10);
        skillSlider.setValue(5);

        steepnessSlider.setMaximum(10);
        steepnessSlider.setValue(5);

        jLabel13.setText("Speed Level");

        jLabel14.setText("Comfort Level");

        jLabel15.setText("Skill Level");

        jLabel16.setText("Steepness Level");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comfortSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(steepnessSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(skillSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtmodel, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(txtsuspension)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(txtframe))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTireTPI)
                                    .addComponent(txtwheelSize))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtsuspension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtframe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTireTPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtwheelSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comfortSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(skillSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(steepnessSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Additional information", jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Model", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        jButton1.setText("Log out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        addToBasket.setText("Add to Basket");
        addToBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToBasketActionPerformed(evt);
            }
        });

        clearBasket.setText("Clear Basket");
        clearBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBasketActionPerformed(evt);
            }
        });

        confirmBasket.setText("Confirm Basket");
        confirmBasket.setBackground(new java.awt.Color(255, 204, 204));
        confirmBasket.setForeground(new java.awt.Color(0, 0, 0));
        confirmBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBasketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(398, 398, 398)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(addToBasket)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearBasket)
                        .addGap(18, 18, 18)
                        .addComponent(confirmBasket)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addToBasket)
                            .addComponent(clearBasket)
                            .addComponent(confirmBasket)))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtcustomer_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcustomer_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcustomer_idActionPerformed

    private void confirm_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirm_buttonActionPerformed
        String model = "";
        //initializing dates
        LocalDateTime returntime = dateTimePickerReturn.getDateTimePermissive();
        LocalDateTime starttime = dateTimePickerStart.getDateTimePermissive();
        
        //check if selected times are inside working hours
        LocalTime buisnessOpeningTime = LocalTime.of(7,0); //opening hours
        LocalTime buisnessClosingTime = LocalTime.of(19,0); //closing hours
        if(starttime ==null || returntime==null || choosemodel.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(this, "Field(s) are blank or incorrectly formatted");
            return;
        }
        if(starttime.toLocalTime().isBefore(buisnessOpeningTime) || starttime.toLocalTime().isAfter(buisnessClosingTime)
                || returntime.toLocalTime().isBefore(buisnessOpeningTime) || returntime.toLocalTime().isAfter(buisnessClosingTime)){
            JOptionPane.showMessageDialog(this, "Current time is not within buisness hours of 7AM - 7PM");
            return;
        }
        String customer_id = txtcustomer_id.getText();
        String strPR = txtpricerange.getSelectedItem().toString(); //PR = price range
        model = choosemodel.getSelectedItem().toString();
        //parsing String to get min and max range
        int min =0;
        int max =0;
        if (strPR.indexOf(" - ") == -1) {
            strPR = strPR.replaceAll("\\D+", "");
            min = Integer.parseInt(strPR);
            max = min;
        } else { 
            String[] parts = strPR.split(" - ");
            min = Integer.parseInt(parts[0]);
            max = Integer.parseInt(parts[1]);
        }
        //passing min and max to get bikeID of bicycle within said PR
        String bikeid = getBikeByPriceRange(min, max);
        System.out.println(bikeid);
                
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/bikerent","root","");
                pst1 = con.prepareStatement("select cost from registration where bikeID = ?");
                pst1.setString(1, bikeid);
                rs2 = pst1.executeQuery();
                int costperhour = 0;
                if (rs2.next()){
                    costperhour = rs2.getInt("cost");
                    System.out.println(costperhour);
                }
                // Calculating total cost for bike rental
                //Calulating difference in dates and rental time in hours
                int hour_difference = (int) Duration.between(starttime, returntime).toHours();
                if (hour_difference <= 0){
                    JOptionPane.showMessageDialog(this, "Time is not legal. Minimum rental time is 1 hour.");
                }
                else{
                    //Calulating a discount based on rental period
                    double discount = 0.0;
                    if (hour_difference >= 24 && hour_difference < 72){
                        discount = 0.2;  //20% discount for rentals between 24 and 72 hours
                    } else if (hour_difference >=72) {
                        discount = 0.3; //30% discount for rentals longer than 72 hours
                    }
                    double totalcost = costperhour*hour_difference*(1-discount);
                    String strcost = String.format("%.2f", totalcost); //formats total cost to 2 d.p              

                    pst3 = con.prepareStatement("insert into rental(bikeID, customer_id, total_cost, start_time, planned_return_time)values(?,?,?,?,?)");
                    pst3.setString(1, bikeid);
                    pst3.setString(2, customer_id);
                    pst3.setInt(3, (int) totalcost); //lossy conversion from double to int
                    pst3.setTimestamp(4, Timestamp.valueOf(starttime));
                    pst3.setTimestamp(5, Timestamp.valueOf(returntime));
                    pst3.executeUpdate();

                    pst4 = con.prepareStatement("update registration set available=0 where bikeID=?");
                    pst4.setString(1, bikeid);
                    pst4.executeUpdate();
                    
                    //get instance of identitymap class
                    IdentityMap identityMap = IdentityMap.getInstance();
                    //Add bike and customer ID to identity map
                    rent rental = new rent(bikeid, customer_id, hour_difference, (int) totalcost, model);
                    identityMap.addRentedBike(bikeid, rental);
                    
                    int prompt = JOptionPane.showOptionDialog(null, "Total: "+ strcost + "\nWould you like to add more bicycles to your basket?", "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Yes", "No"}, "No");
                    if (prompt ==1){
                        Login login = new Login();
                        this.dispose();
                        login.setVisible(true);
                    }
                }
            
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(rent.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }//GEN-LAST:event_confirm_buttonActionPerformed
    private boolean isBikeBooked(String bikeID, LocalDateTime starttime_LDT, LocalDateTime plannedReturnTime_LDT) throws SQLException{
        //checking if bike is already booked for time period
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bikerent","root","");
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM rental WHERE bikeID = ? AND start_time BETWEEN ? AND ? OR planned_return_time BETWEEN ? AND ?");
            pst.setString(1, bikeID);
            Timestamp starttime= Timestamp.valueOf(starttime_LDT);
            Timestamp plannedReturnTime= Timestamp.valueOf(plannedReturnTime_LDT);
            pst.setTimestamp(2, starttime);
            pst.setTimestamp(3, plannedReturnTime);
            pst.setTimestamp(4, starttime);
            pst.setTimestamp(5, plannedReturnTime);
            //Checking to see if there is at least one instance of bike that is booked for that time period
            try(ResultSet rs = pst.executeQuery()){
                rs.next();
                System.out.println("isbikebooked");
                System.out.println(rs.getInt(1)>0);
                return rs.getInt(1) > 0;
            }
        } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(rent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            customer c = new customer();
            this.dispose();
            c.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(rent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void name_dropdownlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_dropdownlistActionPerformed
        // TODO add your handling code here:
        String customer_name = name_dropdownlist.getSelectedItem().toString();
        try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/bikerent","root","");
                pst2 = con.prepareStatement("select * from customer where customer_name = ?");
                pst2.setString(1, customer_name);
                rs = pst2.executeQuery();
                if(rs.next()==false){
                    JOptionPane.showMessageDialog(this, "Customer not found");
                }
                else{
                    String customer_id = rs.getString("customer_id");
                    txtcustomer_id.setText(customer_id.trim());
                }

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(rent.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_name_dropdownlistActionPerformed
    protected String getBikeByPriceRange(int minprice, int maxprice){
        String bikeID = null;
        LocalDateTime returntime = dateTimePickerReturn.getDateTimePermissive();
        LocalDateTime starttime = dateTimePickerStart.getDateTimePermissive();
        System.out.println(minprice);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikerent","root","");
            PreparedStatement pst = con.prepareStatement("SELECT bikeID FROM registration WHERE cost >= ? AND cost <= ? AND available=1 AND model = ?");
            pst.setInt(1, minprice);
            pst.setInt(2, maxprice);
            pst.setString(3, choosemodel.getSelectedItem().toString());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String currbikeID = rs.getString("bikeID");
                System.out.println("currbikeid"+currbikeID);
                //checking to see if bike has already been booked in time period
                if(!isBikeBooked(currbikeID, starttime, returntime)){
                    bikeID = currbikeID;
                    System.out.println("isBikeBooked");
                    break;
                }
            }
            if(rs.next()){
                bikeID = rs.getString("bikeID");
            }
            con.close();
            if (bikeID == null){
                JOptionPane.showMessageDialog(this, "Cannot find bicycle for given price range. Please select another");
            }
        } catch (ClassNotFoundException | SQLException ex){
            System.err.println(ex.getMessage());
        }
        return bikeID;
    }
    
    private void choosemodelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosemodelActionPerformed
        table_update();
        String bikeModel = choosemodel.getSelectedItem().toString();
        int maxCost =0;
        int minCost =0;
        int numBikes = 0;
        Set<Integer> costSet = new HashSet<>(); //set used to keep track of bicycles with unique costs
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikerent","root","");
            //retrieve min and max cost of particular bicycle model
            PreparedStatement pst = con.prepareStatement("select MAX(cost), MIN(cost), COUNT(DISTINCT bikeID) FROM registration WHERE model = ?");
            pst.setString(1, bikeModel);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                maxCost = rs.getInt(1);
                minCost = rs.getInt(2);
                numBikes = rs.getInt(3);
            }
            else {JOptionPane.showMessageDialog(this, "No bicycles of this model left in stock due to popular demand. Please select another.");
            txtpricerange.removeAllItems(); }
            //Adding all bicycles with unique cost to set
            pst2 = con.prepareStatement("SELECT DISTINCT cost FROM registration WHERE model = ?");
            pst2.setString(1, bikeModel);
            rs2 = pst2.executeQuery();
            while (rs2.next()){
                costSet.add(rs2.getInt(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(rent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(maxCost == 0 && minCost == 0){
            JOptionPane.showMessageDialog(this, "No bicycles of this model left in stock due to popular demand. Please select another.");
            txtpricerange.removeAllItems();
        }
        else{
            // Calculate the increment based on the range between min max cost
            int numPriceRanges = Math.min(numBikes, 4); //limit number of price ranges to 4 or number of unique bikes (whichever is lower)
            //splits range into segments depending on bikes in stock, set to at least 20
            int increment = Math.max((int) Math.ceil((maxCost - minCost) / (double) numPriceRanges), 10);
            txtpricerange.removeAllItems();
            
            //populate price_range combobox with price ranges appropriate to min, max cost
            
            int i = minCost;
            while(i <= maxCost){
                int j = i + increment - 1;
                if (j >maxCost){
                    j = maxCost;
                }
                boolean hasBike = false;
                for (int k = i; k <= j; k++) {
                    if (costSet.contains(k)) { //checks if any bikes with cost equal to upperbound of range
                        hasBike = true;
                        break;
                    }
                }
                if (hasBike) {
                    if (i==j){ //check to detect last range
                        txtpricerange.addItem(String.format("%d+", i));
                    } else{
                        txtpricerange.addItem(String.format("%d - %d", i, j));
                    }
                }
                i += increment;
            }
        }
    }//GEN-LAST:event_choosemodelActionPerformed

    private void txtpricerangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpricerangeActionPerformed
        table_update();
    }//GEN-LAST:event_txtpricerangeActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        String bikeID = d1.getValueAt(selectIndex, 0).toString();
        txtmodel.setText(d1.getValueAt(selectIndex, 1).toString());
        String suspension = "";
        String frame = "";
        int tireTPI = 0;
        int wheelSize =0;
        int weight =0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikerent","root","");
            //retrieve min and max cost of particular bicycle model
            PreparedStatement pst = con.prepareStatement("SELECT suspension, frame, tireTPI, wheelSize, weight FROM registration WHERE bikeID = ?");
            pst.setString(1, bikeID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                suspension = rs.getString(1);
                frame = rs.getString(2);
                tireTPI = rs.getInt(3);
                wheelSize = rs.getInt(4);
            }
        } catch (SQLException e){
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(rent.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtsuspension.setText(suspension);
        txtframe.setText(frame);
        txtTireTPI.setText(Integer.toString(tireTPI));
        txtwheelSize.setText(Integer.toString(wheelSize));
        
        //calculating scores for speed, comfort, etc. based on set of weightings
        //ternary operator used for readability
        double weightScore = weight <= 11 ? 1 : weight <= 13 ? 0.8 : weight <= 15 ? 0.6 : weight <= 17 ? 0.4 : 0.2;
        double tireScore = tireTPI >= 120 ? 1 : tireTPI >= 90 ? 0.8 : tireTPI >= 60 ? 0.6 : tireTPI >= 30 ? 0.4 : 0.2;
        double suspensionScore = (suspension.equals("fully")) ? 1 : (suspension.equals("hard-tail")) ? 0.6 : 0.2;
        double frameScore = frame.equals("carbon fibre") ? 1 : frame.equals("aluminium") ? 0.8 : frame.equals("steel") ? 0.6 : 0.2;
        double wheelScore = wheelSize == 29 ? 1 : weight == 27.5 ? 0.8 : weight == 26 ? 0.6 : 0.2;
        
        //calculate comfort score
        double comfortScore = weightScore * 0.2 + tireScore * 0.3 + suspensionScore * 0.3 + frameScore * 0.1 + wheelScore * 0.1;
        //calculate steepness score
        double steepnessLevel = suspensionScore * 0.5 + wheelScore * 0.5;
        //calculate experience level (skill)
        double experienceLevel = weightScore * 0.4 + tireScore * 0.1 + suspensionScore * 0.1 + frameScore * 0.2 + wheelScore * 0.2;
        //calculate speed score
        double speedscore = 0.6*weightScore + 0.2*tireScore + 0.2*wheelScore;
        
        speedSlider.setValue((int) (speedscore*10));
        comfortSlider.setValue((int) (comfortScore*10));
        steepnessSlider.setValue((int) (steepnessLevel*10));
        skillSlider.setValue((int) (experienceLevel*10));
        System.out.println(speedscore);
        System.out.println(comfortScore);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Login login = new Login();
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addToBasketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToBasketActionPerformed
        String bikeID = null;
        LocalDateTime returntime = dateTimePickerReturn.getDateTimePermissive();
        LocalDateTime starttime = dateTimePickerStart.getDateTimePermissive();
        DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        LocalTime buisnessOpeningTime = LocalTime.of(7, 0); //opening hours
        LocalTime buisnessClosingTime = LocalTime.of(19, 0); //closing hours
        if (starttime.toLocalTime().isBefore(buisnessOpeningTime) || starttime.toLocalTime().isAfter(buisnessClosingTime)
                || returntime.toLocalTime().isBefore(buisnessOpeningTime) || returntime.toLocalTime().isAfter(buisnessClosingTime)) {
            JOptionPane.showMessageDialog(this, "Current time is not within buisness hours of 7AM - 7PM");
            return;
        }
        try {
            if(isBikeBooked(bikeID, starttime, returntime)){ //checking if bike has already been booked
                JOptionPane.showMessageDialog(this, "This bicycle has already been booked for the selected time period. Please select another bicycle or another time/date");
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(rent.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Checking if any bike record has been selected
        if(selectIndex >= 0){
            bikeID = d1.getValueAt(selectIndex, 0).toString();
            if(!basket.contains(bikeID)){
                basket.add(bikeID);
                JOptionPane.showMessageDialog(this, "Item added");
            }
            else{
                JOptionPane.showMessageDialog(this, "Item already added to basket");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please select a bicycle first");
        }
        System.out.println(basket);
    }//GEN-LAST:event_addToBasketActionPerformed

    private void clearBasketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBasketActionPerformed
        //clearing basket
        basket.clear();
        JOptionPane.showMessageDialog(this, "Basket cleared");
    }//GEN-LAST:event_clearBasketActionPerformed

    private void confirmBasketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBasketActionPerformed
        String customer_id = txtcustomer_id.getText();
        String bikeid = null;
        //initializing dates
        LocalDateTime returntime = dateTimePickerReturn.getDateTimePermissive();
        LocalDateTime starttime = dateTimePickerStart.getDateTimePermissive();

        //check if selected times are inside working hours
        LocalTime buisnessOpeningTime = LocalTime.of(7, 0); //opening hours
        LocalTime buisnessClosingTime = LocalTime.of(19, 0); //closing hours
        if (starttime.toLocalTime().isBefore(buisnessOpeningTime) || starttime.toLocalTime().isAfter(buisnessClosingTime)
                || returntime.toLocalTime().isBefore(buisnessOpeningTime) || returntime.toLocalTime().isAfter(buisnessClosingTime)) {
            JOptionPane.showMessageDialog(this, "Current time is not within buisness hours of 7AM - 7PM");
            return;
        }
        for(int i=0;i<basket.size();i++){
            bikeid = basket.get(i);
            System.out.println(bikeid);
            try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bikerent", "root", "");
            //Retrieving cost per hour of bike
            pst1 = con.prepareStatement("select cost from registration where bikeID = ?");
            pst1.setString(1, bikeid);
            rs2 = pst1.executeQuery();
            int costperhour = 0;
            if (rs2.next()) {
                costperhour = rs2.getInt("cost");
            }
            // Calculating total cost for bike rental
            //Calulating difference in dates and rental time in hours
            int hour_difference = (int) Duration.between(starttime, returntime).toHours();
            if (hour_difference <= 0) {
                JOptionPane.showMessageDialog(this, "Time is not legal. Minimum rental time is 1 hour.");
                return;
            } else {
                //Calulating a discount based on rental period
                double discount = 0.0;
                if (hour_difference >= 24 && hour_difference < 72) {
                    discount = 0.2;  //20% discount for rentals between 24 and 72 hours
                } else if (hour_difference >= 72) {
                    discount = 0.3; //30% discount for rentals longer than 72 hours
                }
                double totalcost = costperhour * hour_difference * (1 - discount);
                
                pst3 = con.prepareStatement("insert into rental(bikeID, customer_id, total_cost, start_time, planned_return_time)values(?,?,?,?,?)");
                pst3.setString(1, bikeid);
                pst3.setString(2, customer_id);
                pst3.setInt(3, (int) totalcost); //lossy conversion from double to int
                pst3.setTimestamp(4, Timestamp.valueOf(starttime));
                pst3.setTimestamp(5, Timestamp.valueOf(returntime));
                pst3.executeUpdate();

                //get instance of identitymap class
                IdentityMap identityMap = IdentityMap.getInstance();
                //Add bike and customer ID to identity map
                rent rental = new rent(bikeid, customer_id, hour_difference, (int) totalcost, model);
                identityMap.addRentedBike(bikeid, rental);
                int prompt = JOptionPane.showOptionDialog(null, "Total: "+ totalcost + "\nWould you like to add more bicycles to your basket?", "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Yes", "No"}, "No");
                    if (prompt ==1){
                        Login login = new Login();
                        this.dispose();
                        login.setVisible(true);
                    }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(rent.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        JOptionPane.showMessageDialog(this, "Bike successfully booked");
        
    }//GEN-LAST:event_confirmBasketActionPerformed
    public String getCustomerID(){
        return customer_id;
    }
    public int getHours(){
        return hours;
    }
    public int getTotalCost(){
        return totalCost;
    }
    public String getModel(){
        return model;
    }

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
            java.util.logging.Logger.getLogger(rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new rent().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToBasket;
    private javax.swing.JComboBox<String> choosemodel;
    private javax.swing.JButton clearBasket;
    private javax.swing.JSlider comfortSlider;
    private javax.swing.JButton confirmBasket;
    private javax.swing.JButton confirm_button;
    private com.toedter.calendar.demo.DateChooserPanel dateChooserPanel1;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker2;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker3;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePickerReturn;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePickerStart;
    private com.toedter.calendar.DateUtil dateUtil1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooserBeanInfo jDateChooserBeanInfo1;
    private com.toedter.calendar.JDateChooserCellEditor jDateChooserCellEditor1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    private com.toedter.components.JSpinFieldBeanInfo jSpinFieldBeanInfo1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> name_dropdownlist;
    private javax.swing.JLabel pricerange_jlabel;
    private javax.swing.JSlider skillSlider;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JSlider steepnessSlider;
    private com.toedter.calendar.demo.TestDateEvaluator testDateEvaluator1;
    private javax.swing.JTextField txtTireTPI;
    private javax.swing.JTextField txtavl;
    private javax.swing.JTextField txtcost;
    private javax.swing.JTextField txtcustomer_id;
    private javax.swing.JTextField txtframe;
    private javax.swing.JTextField txthourdifference1;
    private javax.swing.JTextField txtmodel;
    private javax.swing.JComboBox<String> txtpricerange;
    private javax.swing.JTextField txtsuspension;
    private javax.swing.JTextField txtwheelSize;
    // End of variables declaration//GEN-END:variables
}
