/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.furnisoft.views;

import com.furnisoft.pojos.Admin;
import com.furnisoft.pojos.AmountPaid;
import com.furnisoft.pojos.Bill;
import com.furnisoft.pojos.Category;
import com.furnisoft.pojos.Hardwares;
import com.furnisoft.pojos.Message;
import com.furnisoft.pojos.ShopDetails;
import com.furnisoft.pojos.SoldItems;
import com.furnisoft.pojos.StockDetails;
import com.furnisoft.services.AdminService;
import com.furnisoft.services.BillService;
import com.furnisoft.services.CategoryService;
import com.furnisoft.services.HardwareService;
import com.furnisoft.services.PDFManager;
import com.furnisoft.services.ShopDetailsService;
import com.furnisoft.services.StockDetailsService;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author koju5658
 */
public class MainFrame extends javax.swing.JFrame {

    private AdminService adminServ;
    private Message message;
    private CategoryService catServ;
    private List<Category> categories = new ArrayList<Category>();
    private List<Hardwares> hardwares = new ArrayList<Hardwares>();
    private HardwareService hardServ;
    private ShopDetails shopDetails;
    private ShopDetailsService shopDetailsServ;
    private boolean systemState = false;
    private BillService billServ;
    private List<Bill> bills;
    private Bill bill;
    private Hardwares hardware;
    private Category threadCategory;
    //For date validation
    String regex = "^[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;
    //For date validation ends
    public MainFrame() {
        initComponents();
        this.initRightPanels();
        //this.setHardwareAndCategoryDetails();
        
        this.getHardwaresFromDB();
        this.getCategoryFromDB();
        this.setCategoryInDashbordPageInHardwareRegister();
       
        this.setShopDetails();
        this.bills = new ArrayList<Bill>();
        this.getBillsFromDatabase();
        this.addTableListener();
        this.systemState = true;
    }
    private void setCategoryInDashbordPageInHardwareRegister(){
        selectCategory.removeAllItems();
        for(Category cat : this.categories){
            selectCategory.addItem(cat.getCategoryName());
        }
    }
    private boolean checkDoubleValueParsable(String doubleValue){
        boolean check = true;
        try{
            Double.parseDouble(doubleValue);
        }
        catch(Exception er){
            check = false;
        }
        return check;
    }
    public void setSystemState(boolean state){
        this.systemState = state;
    }
    private void setShopDetails(){
        this.shopDetailsServ = new ShopDetailsService();
        this.shopDetails = new ShopDetails();
        this.shopDetails = this.shopDetailsServ.getShopDetails();
        txtShopName.setText(this.shopDetails.getShopName());
        txtShopAddress.setText(this.shopDetails.getShopAddress());
        txtVatNumber.setText(Long.toString(this.shopDetails.getVatNo()));
        this.setTitle(this.shopDetails.getShopName());
    }
    
    private boolean checkDate(String date){
        this.matcher = this.pattern.matcher(date);
        return this.matcher.matches();
    }
    public void getCategoryFromDB(){
        this.catServ = new CategoryService();
        this.categories = new ArrayList<Category>();
        this.categories = this.catServ.getCategory();
    }
    public void getHardwaresFromDB(){
        this.hardServ = new HardwareService();
        this.hardwares = new ArrayList<Hardwares>();
        this.hardwares = this.hardServ.getHardwares();
    }
    
    private void setCategoryInStockManage(){
        stockCategoryList.removeAllItems();
        for(Category cat : this.categories){
            stockCategoryList.addItem(cat.getCategoryName());

        }
    }
    private void setHardwareNameInSelection(String category){
        stockHardwareName.removeAllItems();
        for(Hardwares hardware:this.hardwares){
            if(category.equals(hardware.getCategory())){
                stockHardwareName.addItem(hardware.getHardwareName());
            }
        }
    }
    
    private boolean checkQuantityParsable(String quantity){
        boolean quantityCheck = true;
        try{
            Integer.parseInt(quantity);
        }catch(Exception er){
            quantityCheck = false;
        }
        return quantityCheck;
    }
    private void displayHardwareDetailsTable(){
        DefaultTableModel model = (DefaultTableModel) hardwaresDisplayTable.getModel();
        model.setRowCount(0);
        
        JTable table = new JTable();
        table.setModel(model);
        Object rowData[] = new Object[7];
        long i=1;
        for(Hardwares hardware: this.hardwares)
        {
            rowData[0] = i;
            rowData[1] = hardware.getId();
            rowData[2] = hardware.getCategory();
            rowData[3] = hardware.getHardwareName();
            rowData[4] = hardware.getSize();
            rowData[5] = hardware.getPrice();
            rowData[6] = hardware.getRegDate();
            model.addRow(rowData);
            i++;
        }
    }
    private void initRightPanels(){
        panelDashboard.setVisible(true);
        panelStockManage.setVisible(false);
        panelChangeCredentials.setVisible(false);
        panelHardwareDetails.setVisible(false);
        panelCategoryDetails.setVisible(false);
        panelStockDetails.setVisible(false);
        panelBillManagement.setVisible(false);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        leftMenu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lvlDashboard = new javax.swing.JLabel();
        lvlHardwareDetails = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lvlCredentials = new javax.swing.JLabel();
        lvlLogout = new javax.swing.JLabel();
        lvlStockManagement = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lvlStockDetails = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        lvlCategoryDetails = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lvlBillManagement = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        rightPanels = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        panelDashboard = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCategoryName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCatRegDate = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        catRegButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtShopName = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtShopAddress = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtVatNumber = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtHardwareName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        selectCategory = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        txtHardwarePrice = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtHardwareSize = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtHardwareRegDate = new javax.swing.JTextField();
        hardwareRegisterButton = new javax.swing.JButton();
        panelStockManage = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        stockCategoryList = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        stockHardwareName = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        stockAction = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stockComment = new javax.swing.JTextArea();
        jLabel28 = new javax.swing.JLabel();
        stockQuantity = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        stockDate = new javax.swing.JTextField();
        stockSubmitButton = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        panelChangeCredentials = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtOldEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNewEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        changeButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtOldPass = new javax.swing.JPasswordField();
        txtNewPass = new javax.swing.JPasswordField();
        txtReNewPass = new javax.swing.JPasswordField();
        lvlCheck = new javax.swing.JLabel();
        panelHardwareDetails = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        hardwaresDisplayTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        updateHardware = new javax.swing.JButton();
        hardwareDeleteButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        categorySearchHardware = new javax.swing.JComboBox<>();
        hardwareNameSearchHardware = new javax.swing.JComboBox<>();
        hardwareSearch = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        panelCategoryDetails = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        categoryUpdateButton = new javax.swing.JButton();
        categoryDelButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        categoryDisplayTable = new javax.swing.JTable();
        panelStockDetails = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        viewStockDetailsButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        stockDisplayTable = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        stockCategorySearch = new javax.swing.JComboBox<>();
        stockSearch = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        panelBillManagement = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        billDetails = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        txtBillNo = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtCustomerAddress = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtContactNumber = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        billingTable = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        selectHardware = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        txtNetTotal = new javax.swing.JTextField();
        createBillButton = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        selectPaymentStatus = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        txtVat = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtGrandTotal = new javax.swing.JTextField();
        txtDiscount = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtAmountPaid = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        viewBill = new javax.swing.JButton();
        deleteBill = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        billDisplayTable = new javax.swing.JTable();
        viewPaymentDetails = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Furniture Showroom");
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(0, 0, 0));

        leftMenu.setBackground(new java.awt.Color(153, 0, 153));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/furnisoft/icons/admin.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        jLabel1.setFont(new java.awt.Font("Castellar", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Admin");

        jLabel2.setFont(new java.awt.Font("Castellar", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SECTION");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lvlDashboard.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lvlDashboard.setForeground(new java.awt.Color(255, 255, 255));
        lvlDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/furnisoft/icons/dashboard-24.png"))); // NOI18N
        lvlDashboard.setText("Dashboard");
        lvlDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lvlDashboard.setIconTextGap(18);
        lvlDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lvlDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lvlDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lvlDashboardMouseExited(evt);
            }
        });

        lvlHardwareDetails.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lvlHardwareDetails.setForeground(new java.awt.Color(255, 255, 255));
        lvlHardwareDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/furnisoft/icons/sofa-24.png"))); // NOI18N
        lvlHardwareDetails.setText("Hardware Details");
        lvlHardwareDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lvlHardwareDetails.setIconTextGap(18);
        lvlHardwareDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lvlHardwareDetailsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lvlHardwareDetailsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lvlHardwareDetailsMouseExited(evt);
            }
        });

        lvlCredentials.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lvlCredentials.setForeground(new java.awt.Color(255, 255, 255));
        lvlCredentials.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/furnisoft/icons/literature-24.png"))); // NOI18N
        lvlCredentials.setText("Change Credentials");
        lvlCredentials.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lvlCredentials.setIconTextGap(18);
        lvlCredentials.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lvlCredentialsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lvlCredentialsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lvlCredentialsMouseExited(evt);
            }
        });

        lvlLogout.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lvlLogout.setForeground(new java.awt.Color(255, 255, 255));
        lvlLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/furnisoft/icons/logout-24.png"))); // NOI18N
        lvlLogout.setText("Logout");
        lvlLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lvlLogout.setIconTextGap(18);
        lvlLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lvlLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lvlLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lvlLogoutMouseExited(evt);
            }
        });

        lvlStockManagement.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lvlStockManagement.setForeground(new java.awt.Color(255, 255, 255));
        lvlStockManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/furnisoft/icons/stack-24.png"))); // NOI18N
        lvlStockManagement.setText("Stock Management");
        lvlStockManagement.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lvlStockManagement.setIconTextGap(18);
        lvlStockManagement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lvlStockManagementMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lvlStockManagementMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lvlStockManagementMouseExited(evt);
            }
        });

        lvlStockDetails.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lvlStockDetails.setForeground(new java.awt.Color(255, 255, 255));
        lvlStockDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/furnisoft/icons/stackoverflow-24.png"))); // NOI18N
        lvlStockDetails.setText("Stock Details");
        lvlStockDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lvlStockDetails.setIconTextGap(18);
        lvlStockDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lvlStockDetailsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lvlStockDetailsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lvlStockDetailsMouseExited(evt);
            }
        });

        lvlCategoryDetails.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lvlCategoryDetails.setForeground(new java.awt.Color(255, 255, 255));
        lvlCategoryDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/furnisoft/icons/view-details-24.png"))); // NOI18N
        lvlCategoryDetails.setText("Category Details");
        lvlCategoryDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lvlCategoryDetails.setIconTextGap(18);
        lvlCategoryDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lvlCategoryDetailsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lvlCategoryDetailsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lvlCategoryDetailsMouseExited(evt);
            }
        });

        lvlBillManagement.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lvlBillManagement.setForeground(new java.awt.Color(255, 255, 255));
        lvlBillManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/furnisoft/icons/bill-24.png"))); // NOI18N
        lvlBillManagement.setText("Bill Management");
        lvlBillManagement.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lvlBillManagement.setIconTextGap(18);
        lvlBillManagement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lvlBillManagementMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lvlBillManagementMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lvlBillManagementMouseExited(evt);
            }
        });

        javax.swing.GroupLayout leftMenuLayout = new javax.swing.GroupLayout(leftMenu);
        leftMenu.setLayout(leftMenuLayout);
        leftMenuLayout.setHorizontalGroup(
            leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(leftMenuLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lvlCategoryDetails, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lvlDashboard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(leftMenuLayout.createSequentialGroup()
                .addGroup(leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftMenuLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lvlLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(leftMenuLayout.createSequentialGroup()
                                .addGroup(leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lvlCredentials, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                    .addComponent(jSeparator5))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftMenuLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lvlStockDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lvlStockManagement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(lvlHardwareDetails, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lvlBillManagement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        leftMenuLayout.setVerticalGroup(
            leftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftMenuLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lvlDashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lvlCategoryDetails)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lvlHardwareDetails)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lvlStockManagement)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lvlStockDetails)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lvlBillManagement)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(lvlCredentials)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(lvlLogout)
                .addGap(40, 40, 40))
        );

        rightPanels.setPreferredSize(new java.awt.Dimension(840, 600));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        panelDashboard.setBackground(new java.awt.Color(204, 204, 204));
        panelDashboard.setPreferredSize(new java.awt.Dimension(840, 600));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel10.setBackground(new java.awt.Color(153, 153, 255));
        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Register Hardware Category");
        jLabel10.setOpaque(true);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Category Name ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Registered Date");

        txtCatRegDate.setToolTipText("YYYY-MM-DD");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel14.setText("Registered Date should be in Format  YYYY-MM-DD [ Nepali BS ]");

        catRegButton.setText("Register");
        catRegButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catRegButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCatRegDate, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(catRegButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel14))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCatRegDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(catRegButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jLabel15.setBackground(new java.awt.Color(153, 153, 255));
        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Update Shop Details");
        jLabel15.setOpaque(true);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("Shop Name");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("Address");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("Pan No");

        jButton1.setText("Update Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtShopAddress))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtShopName))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVatNumber)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtShopName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtShopAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVatNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setPreferredSize(new java.awt.Dimension(417, 549));

        jLabel16.setBackground(new java.awt.Color(153, 153, 255));
        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Register Hardwares");
        jLabel16.setOpaque(true);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Hardware Name ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Category");

        selectCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Price");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Size");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Registered Date");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel22.setText("Registered Date should be in Format  YYYY-MM-DD [ Nepali BS ]");

        hardwareRegisterButton.setText("Register");
        hardwareRegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hardwareRegisterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHardwareSize, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHardwarePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(selectCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtHardwareName, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHardwareRegDate, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hardwareRegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHardwareName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHardwarePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHardwareSize, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHardwareRegDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(hardwareRegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelDashboardLayout = new javax.swing.GroupLayout(panelDashboard);
        panelDashboard.setLayout(panelDashboardLayout);
        panelDashboardLayout.setHorizontalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDashboardLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panelDashboardLayout.setVerticalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDashboardLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDashboardLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        panelStockManage.setBackground(new java.awt.Color(204, 204, 204));
        panelStockManage.setPreferredSize(new java.awt.Dimension(840, 600));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Category Name ");

        stockCategoryList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        stockCategoryList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stockCategoryListItemStateChanged(evt);
            }
        });
        stockCategoryList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stockCategoryListMouseEntered(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Hardware Name");

        stockHardwareName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Action");

        stockAction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADD", "REMOVE" }));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Comment");

        stockComment.setColumns(20);
        stockComment.setRows(5);
        jScrollPane1.setViewportView(stockComment);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Quantity");

        stockQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockQuantityActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Date");

        stockSubmitButton.setText("Submit");
        stockSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockSubmitButtonActionPerformed(evt);
            }
        });

        jLabel30.setBackground(new java.awt.Color(153, 0, 153));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Add or Remove Hardware Stock");
        jLabel30.setOpaque(true);

        javax.swing.GroupLayout panelStockManageLayout = new javax.swing.GroupLayout(panelStockManage);
        panelStockManage.setLayout(panelStockManageLayout);
        panelStockManageLayout.setHorizontalGroup(
            panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStockManageLayout.createSequentialGroup()
                .addGroup(panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelStockManageLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                                .addComponent(stockHardwareName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stockAction, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stockCategoryList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stockQuantity)
                                .addComponent(stockDate))
                            .addComponent(stockSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelStockManageLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        panelStockManageLayout.setVerticalGroup(
            panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStockManageLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockCategoryList, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockHardwareName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockAction, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelStockManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(stockSubmitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(36, 36, 36))
        );

        panelChangeCredentials.setBackground(new java.awt.Color(204, 204, 204));
        panelChangeCredentials.setPreferredSize(new java.awt.Dimension(840, 600));

        jLabel4.setBackground(new java.awt.Color(153, 0, 153));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Change Your Login Details");
        jLabel4.setOpaque(true);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Old Email           :");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Old Password      :");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("New Email          :");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("New Password     :");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Retype New        :");

        changeButton.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        changeButton.setText("Change Credential");
        changeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeButtonActionPerformed(evt);
            }
        });

        jLabel11.setText("All Fields are Required.");

        txtNewPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNewPassKeyReleased(evt);
            }
        });

        txtReNewPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReNewPassKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelChangeCredentialsLayout = new javax.swing.GroupLayout(panelChangeCredentials);
        panelChangeCredentials.setLayout(panelChangeCredentialsLayout);
        panelChangeCredentialsLayout.setHorizontalGroup(
            panelChangeCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChangeCredentialsLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(panelChangeCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelChangeCredentialsLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addGroup(panelChangeCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelChangeCredentialsLayout.createSequentialGroup()
                                .addGroup(panelChangeCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelChangeCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtOldEmail)
                                    .addComponent(txtNewEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(changeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtOldPass)
                                    .addComponent(txtNewPass)
                                    .addComponent(txtReNewPass))
                                .addGap(18, 18, 18)
                                .addComponent(lvlCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        panelChangeCredentialsLayout.setVerticalGroup(
            panelChangeCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChangeCredentialsLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelChangeCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelChangeCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txtOldPass))
                .addGap(18, 18, 18)
                .addGroup(panelChangeCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelChangeCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(panelChangeCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lvlCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(changeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        panelHardwareDetails.setPreferredSize(new java.awt.Dimension(840, 600));

        jScrollPane2.setBackground(new java.awt.Color(204, 255, 204));

        hardwaresDisplayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.N", "ID", "Category", "Hardware Name", "Size", "Price [ RS ]", "Registered Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(hardwaresDisplayTable);
        if (hardwaresDisplayTable.getColumnModel().getColumnCount() > 0) {
            hardwaresDisplayTable.getColumnModel().getColumn(0).setMinWidth(60);
            hardwaresDisplayTable.getColumnModel().getColumn(0).setPreferredWidth(60);
            hardwaresDisplayTable.getColumnModel().getColumn(0).setMaxWidth(100);
            hardwaresDisplayTable.getColumnModel().getColumn(1).setMinWidth(60);
            hardwaresDisplayTable.getColumnModel().getColumn(1).setPreferredWidth(60);
            hardwaresDisplayTable.getColumnModel().getColumn(1).setMaxWidth(100);
            hardwaresDisplayTable.getColumnModel().getColumn(5).setMinWidth(60);
            hardwaresDisplayTable.getColumnModel().getColumn(5).setPreferredWidth(60);
            hardwaresDisplayTable.getColumnModel().getColumn(6).setResizable(false);
            hardwaresDisplayTable.getColumnModel().getColumn(6).setPreferredWidth(35);
        }

        jPanel5.setBackground(new java.awt.Color(153, 0, 153));

        jLabel23.setBackground(new java.awt.Color(153, 0, 153));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("All Hardwares Details Are Listed Below");
        jLabel23.setOpaque(true);

        updateHardware.setText("Update");
        updateHardware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateHardwareActionPerformed(evt);
            }
        });

        hardwareDeleteButton.setText("Delete");
        hardwareDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hardwareDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(updateHardware, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hardwareDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateHardware, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hardwareDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(153, 0, 153));

        jLabel31.setBackground(new java.awt.Color(153, 0, 153));
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Search Hardware By :");
        jLabel31.setOpaque(true);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Hardware Name");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Category");

        categorySearchHardware.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                categorySearchHardwareItemStateChanged(evt);
            }
        });
        categorySearchHardware.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categorySearchHardwareMouseEntered(evt);
            }
        });
        categorySearchHardware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorySearchHardwareActionPerformed(evt);
            }
        });

        hardwareNameSearchHardware.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hardwareNameSearchHardwareMouseEntered(evt);
            }
        });

        hardwareSearch.setText("Search");
        hardwareSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hardwareSearchActionPerformed(evt);
            }
        });

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/furnisoft/icons/refresh-2-16.png"))); // NOI18N
        jLabel34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categorySearchHardware, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hardwareNameSearchHardware, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hardwareSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addGap(18, 18, 18))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32)
                        .addComponent(categorySearchHardware, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hardwareNameSearchHardware, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hardwareSearch)))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout panelHardwareDetailsLayout = new javax.swing.GroupLayout(panelHardwareDetails);
        panelHardwareDetails.setLayout(panelHardwareDetailsLayout);
        panelHardwareDetailsLayout.setHorizontalGroup(
            panelHardwareDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHardwareDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHardwareDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelHardwareDetailsLayout.setVerticalGroup(
            panelHardwareDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHardwareDetailsLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(153, 0, 153));

        jLabel35.setBackground(new java.awt.Color(153, 0, 153));
        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("All Category Details Are Listed Below");
        jLabel35.setOpaque(true);

        categoryUpdateButton.setText("Update");
        categoryUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryUpdateButtonActionPerformed(evt);
            }
        });

        categoryDelButton.setText("Delete");
        categoryDelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryDelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(categoryUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(categoryDelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(categoryUpdateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoryDelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        categoryDisplayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "S.N", "ID", "Category Name", "Register Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(categoryDisplayTable);
        if (categoryDisplayTable.getColumnModel().getColumnCount() > 0) {
            categoryDisplayTable.getColumnModel().getColumn(0).setMinWidth(40);
            categoryDisplayTable.getColumnModel().getColumn(0).setPreferredWidth(60);
            categoryDisplayTable.getColumnModel().getColumn(0).setMaxWidth(100);
            categoryDisplayTable.getColumnModel().getColumn(1).setMinWidth(40);
            categoryDisplayTable.getColumnModel().getColumn(1).setPreferredWidth(60);
            categoryDisplayTable.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        javax.swing.GroupLayout panelCategoryDetailsLayout = new javax.swing.GroupLayout(panelCategoryDetails);
        panelCategoryDetails.setLayout(panelCategoryDetailsLayout);
        panelCategoryDetailsLayout.setHorizontalGroup(
            panelCategoryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCategoryDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCategoryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelCategoryDetailsLayout.setVerticalGroup(
            panelCategoryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCategoryDetailsLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelStockDetails.setPreferredSize(new java.awt.Dimension(840, 600));

        jPanel7.setBackground(new java.awt.Color(153, 0, 153));

        jLabel36.setBackground(new java.awt.Color(153, 0, 153));
        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("All Stock Details Are Listed Below");
        jLabel36.setOpaque(true);

        viewStockDetailsButton.setText("View Stock Details");
        viewStockDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewStockDetailsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewStockDetailsButton)
                .addGap(41, 41, 41))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(viewStockDetailsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(11, 11, 11))
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        stockDisplayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "S.N", "ID", "Category", "Hardware Name", "Size", "Price [ RS ]", "Available Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(stockDisplayTable);
        if (stockDisplayTable.getColumnModel().getColumnCount() > 0) {
            stockDisplayTable.getColumnModel().getColumn(0).setMinWidth(40);
            stockDisplayTable.getColumnModel().getColumn(0).setPreferredWidth(60);
            stockDisplayTable.getColumnModel().getColumn(0).setMaxWidth(100);
            stockDisplayTable.getColumnModel().getColumn(1).setMinWidth(40);
            stockDisplayTable.getColumnModel().getColumn(1).setPreferredWidth(60);
            stockDisplayTable.getColumnModel().getColumn(1).setMaxWidth(100);
            stockDisplayTable.getColumnModel().getColumn(5).setMinWidth(80);
            stockDisplayTable.getColumnModel().getColumn(5).setPreferredWidth(80);
            stockDisplayTable.getColumnModel().getColumn(5).setMaxWidth(110);
            stockDisplayTable.getColumnModel().getColumn(6).setMinWidth(100);
            stockDisplayTable.getColumnModel().getColumn(6).setPreferredWidth(100);
            stockDisplayTable.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        jPanel10.setBackground(new java.awt.Color(153, 0, 153));

        jLabel37.setBackground(new java.awt.Color(153, 0, 153));
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Search Hardware By :");
        jLabel37.setOpaque(true);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Category");

        stockCategorySearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stockCategorySearchItemStateChanged(evt);
            }
        });

        stockSearch.setText("Search");
        stockSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockSearchActionPerformed(evt);
            }
        });

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/furnisoft/icons/refresh-2-16.png"))); // NOI18N
        jLabel40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockCategorySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(stockSearch)
                .addGap(79, 79, 79)
                .addComponent(jLabel40)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stockCategorySearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockSearch)))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout panelStockDetailsLayout = new javax.swing.GroupLayout(panelStockDetails);
        panelStockDetails.setLayout(panelStockDetailsLayout);
        panelStockDetailsLayout.setHorizontalGroup(
            panelStockDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStockDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelStockDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelStockDetailsLayout.setVerticalGroup(
            panelStockDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStockDetailsLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel38.setBackground(new java.awt.Color(153, 0, 153));
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Billing Section");
        jLabel38.setOpaque(true);

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));
        jPanel9.setAutoscrolls(true);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Bill No");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Date");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Customer Name");

        txtCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNameActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Customer Address");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Contact No");

        txtContactNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactNumberActionPerformed(evt);
            }
        });

        billingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.N", "Particulars", "Rate", "Quantity", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(billingTable);
        if (billingTable.getColumnModel().getColumnCount() > 0) {
            billingTable.getColumnModel().getColumn(0).setMinWidth(40);
            billingTable.getColumnModel().getColumn(0).setPreferredWidth(60);
            billingTable.getColumnModel().getColumn(0).setMaxWidth(100);
            billingTable.getColumnModel().getColumn(2).setMinWidth(60);
            billingTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            billingTable.getColumnModel().getColumn(2).setMaxWidth(100);
            billingTable.getColumnModel().getColumn(3).setMinWidth(60);
            billingTable.getColumnModel().getColumn(3).setPreferredWidth(60);
            billingTable.getColumnModel().getColumn(3).setMaxWidth(100);
            billingTable.getColumnModel().getColumn(4).setMinWidth(80);
            billingTable.getColumnModel().getColumn(4).setPreferredWidth(80);
            billingTable.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("13 % Vat");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("Net Total");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Discount");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("Hardware Name");

        selectHardware.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Quantity");

        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        addButton.setText("Add Row");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove Row");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        txtTotal.setEditable(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        txtNetTotal.setEditable(false);

        createBillButton.setText("Create Bill");
        createBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBillButtonActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("Payment Status");

        selectPaymentStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Payment Complete", "Payment Due" }));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("Total");

        txtVat.setEditable(false);
        txtVat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVatActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Grand Total");

        txtGrandTotal.setEditable(false);
        txtGrandTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGrandTotalActionPerformed(evt);
            }
        });

        txtDiscount.setText("0.00");
        txtDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiscountActionPerformed(evt);
            }
        });
        txtDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiscountKeyReleased(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("Amount Paid");

        txtAmountPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountPaidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator8)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel46)
                                            .addComponent(jLabel48))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel52)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(selectHardware, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel47)
                                            .addComponent(jLabel54))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCustomerAddress)
                                            .addComponent(txtDate)
                                            .addComponent(selectPaymentStatus, 0, 248, Short.MAX_VALUE)))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel53)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(addButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(removeButton)))))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel50))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNetTotal)
                            .addComponent(txtVat, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(txtTotal))
                        .addGap(124, 124, 124)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel56)
                                    .addComponent(jLabel51))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGrandTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                    .addComponent(createBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDiscount)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAmountPaid)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel47)
                        .addComponent(txtCustomerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel46)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(txtContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel54)
                        .addComponent(selectPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(selectHardware, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton)
                    .addComponent(removeButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51)
                            .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel49)
                                .addComponent(txtVat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtGrandTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtNetTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57)
                    .addComponent(txtAmountPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        billDetails.addTab("Create Bill", jPanel9);

        jPanel12.setBackground(new java.awt.Color(204, 255, 204));

        viewBill.setText("View Bill");
        viewBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBillActionPerformed(evt);
            }
        });

        deleteBill.setText("Delete Bill");
        deleteBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBillActionPerformed(evt);
            }
        });

        billDisplayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "SN", "Bill No", "Customer Name", "Contact Number", "Payment Status", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(billDisplayTable);
        if (billDisplayTable.getColumnModel().getColumnCount() > 0) {
            billDisplayTable.getColumnModel().getColumn(0).setMinWidth(40);
            billDisplayTable.getColumnModel().getColumn(0).setPreferredWidth(60);
            billDisplayTable.getColumnModel().getColumn(0).setMaxWidth(100);
            billDisplayTable.getColumnModel().getColumn(1).setMinWidth(80);
            billDisplayTable.getColumnModel().getColumn(1).setPreferredWidth(80);
            billDisplayTable.getColumnModel().getColumn(1).setMaxWidth(120);
            billDisplayTable.getColumnModel().getColumn(5).setMinWidth(100);
            billDisplayTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            billDisplayTable.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        viewPaymentDetails.setText("View Payemnt Details");
        viewPaymentDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPaymentDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(viewBill, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170)
                .addComponent(viewPaymentDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addComponent(deleteBill)
                .addGap(54, 54, 54))
            .addComponent(jScrollPane6)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewPaymentDetails)
                    .addComponent(viewBill)
                    .addComponent(deleteBill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))
        );

        billDetails.addTab("Bill Details", jPanel12);

        javax.swing.GroupLayout panelBillManagementLayout = new javax.swing.GroupLayout(panelBillManagement);
        panelBillManagement.setLayout(panelBillManagementLayout);
        panelBillManagementLayout.setHorizontalGroup(
            panelBillManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(billDetails)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBillManagementLayout.setVerticalGroup(
            panelBillManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBillManagementLayout.createSequentialGroup()
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(billDetails))
        );

        javax.swing.GroupLayout rightPanelsLayout = new javax.swing.GroupLayout(rightPanels);
        rightPanels.setLayout(rightPanelsLayout);
        rightPanelsLayout.setHorizontalGroup(
            rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelStockManage, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelChangeCredentials, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelHardwareDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelCategoryDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelStockDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelBillManagement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rightPanelsLayout.setVerticalGroup(
            rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelStockManage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelChangeCredentials, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightPanelsLayout.createSequentialGroup()
                    .addComponent(panelHardwareDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelCategoryDetails, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelStockDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelBillManagement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(leftMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightPanels, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rightPanels, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1116, 639));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lvlDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlDashboardMouseEntered
        lvlDashboard.setForeground(Color.MAGENTA);
    }//GEN-LAST:event_lvlDashboardMouseEntered

    private void lvlDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlDashboardMouseExited
        lvlDashboard.setForeground(Color.WHITE);
    }//GEN-LAST:event_lvlDashboardMouseExited

    private void lvlCredentialsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlCredentialsMouseEntered
        lvlCredentials.setForeground(Color.MAGENTA);
    }//GEN-LAST:event_lvlCredentialsMouseEntered

    private void lvlCredentialsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlCredentialsMouseExited
        lvlCredentials.setForeground(Color.WHITE);
    }//GEN-LAST:event_lvlCredentialsMouseExited

    private void lvlLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlLogoutMouseEntered
        lvlLogout.setForeground(Color.MAGENTA);
    }//GEN-LAST:event_lvlLogoutMouseEntered

    private void lvlLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlLogoutMouseExited
        lvlLogout.setForeground(Color.WHITE);
    }//GEN-LAST:event_lvlLogoutMouseExited

    private void lvlDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlDashboardMouseClicked
        panelDashboard.setVisible(true);
        panelStockManage.setVisible(false);
        panelChangeCredentials.setVisible(false);
        panelHardwareDetails.setVisible(false);
        panelCategoryDetails.setVisible(false);
        panelStockDetails.setVisible(false);
        panelBillManagement.setVisible(false);
        
        PleaseWait wait = new PleaseWait();
        wait.setVisible(true);
        SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
        {
            @Override
            protected Void doInBackground()
            {
                setCategoryInDashbordPageInHardwareRegister();
                return null;
            }

            @Override
            protected void done()
            {
               wait.dispose();
            }
        };
        worker.execute();
    }//GEN-LAST:event_lvlDashboardMouseClicked

    private void lvlCredentialsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlCredentialsMouseClicked
        panelDashboard.setVisible(false);
        panelStockManage.setVisible(false);
        panelChangeCredentials.setVisible(true);
        panelHardwareDetails.setVisible(false);
        panelCategoryDetails.setVisible(false);
        panelStockDetails.setVisible(false);
        panelBillManagement.setVisible(false);
    }//GEN-LAST:event_lvlCredentialsMouseClicked

    private void lvlLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlLogoutMouseClicked
        int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to Logout ?","Confirm Message",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==0){
            this.dispose();
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_lvlLogoutMouseClicked

    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeButtonActionPerformed
     
            String oldEmail;
            String oldPass;
            String newEmail;
            String newPass;
            String retypedPass;

            oldEmail = txtOldEmail.getText();
            oldPass = txtOldPass.getText();
            newEmail = txtNewEmail.getText();
            newPass = txtNewPass.getText();
            retypedPass = txtReNewPass.getText();

            if(!oldEmail.equals("") || !oldPass.equals("") || !newEmail.equals("") || !newPass.equals("") || !retypedPass.equals("")){
                if(newPass.equals(retypedPass)){
                    int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to Change ?","Confirm Message",JOptionPane.INFORMATION_MESSAGE);
                    if(confirm==0){
                        Admin oldAdmin,newAdmin;
                        oldAdmin = new Admin();
                        newAdmin = new Admin();
                        oldAdmin.setEmail(oldEmail);
                        oldAdmin.setPass(oldPass);
                        oldAdmin.setInvalidCount(0);
                        newAdmin.setEmail(newEmail);
                        newAdmin.setPass(newPass);
                        newAdmin.setInvalidCount(0);

                        PleaseWait wait = new PleaseWait();
                        wait.setVisible(true);
                        SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
                        {
                            @Override
                            protected Void doInBackground()
                            {
                                adminServ = new AdminService();
                                message = new Message();
                                message = adminServ.changeCredentials(oldAdmin, newAdmin);
                                if(message.isMessageStatus()){
                                    JOptionPane.showMessageDialog(null,message.getMessage(), "Failed To change",JOptionPane.ERROR_MESSAGE);
                                }
                                else{
                                    txtOldEmail.setText("");
                                    txtOldPass.setText("");
                                    txtNewEmail.setText("");
                                    txtNewPass.setText("");
                                    txtReNewPass.setText("");
                                    lvlCheck.setText("");
                                    JOptionPane.showMessageDialog(null,message.getMessage(), "Succeed",JOptionPane.INFORMATION_MESSAGE);
                                }
                                return null;
                            }

                            @Override
                            protected void done()
                            {
                               wait.dispose();
                            }
                        };
                        worker.execute();
                        
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Retyped Password Not matched..!!!", "Not Matched",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"All Fields are required..!!!", "Required Field",JOptionPane.INFORMATION_MESSAGE);
            }
    }//GEN-LAST:event_changeButtonActionPerformed
    private void checkNewPas(){
         if(txtNewPass.getText().equals(txtReNewPass.getText())){
            lvlCheck.setForeground(Color.BLUE);
            lvlCheck.setText("Matched");
        }
        else{
            lvlCheck.setForeground(Color.RED);
            lvlCheck.setText("Retyped Not Matched.!!");
        }
    }
    private void txtReNewPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReNewPassKeyReleased
       this.checkNewPas();
    }//GEN-LAST:event_txtReNewPassKeyReleased

    private void txtNewPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewPassKeyReleased
        this.checkNewPas();
    }//GEN-LAST:event_txtNewPassKeyReleased

    private void catRegButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catRegButtonActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to register ?","Confirm Message",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==0){
            String categoryName = txtCategoryName.getText();
            String regDate = txtCatRegDate.getText();

            if(categoryName.equals("") || regDate.equals("")){
                JOptionPane.showMessageDialog(null,"Both fields are required.!!!","Empty Fields",JOptionPane.INFORMATION_MESSAGE);
            }
            else{

                if(this.checkDate(regDate)){
                    boolean categoryCheck = false;
                    for(Category category : this.categories){
                        if(category.getCategoryName().equals(categoryName)){
                            categoryCheck= true;
                            break;
                        }
                    }
                    if(categoryCheck){
                        JOptionPane.showMessageDialog(null,"Same Category Name already exist.!!","Similar content",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        PleaseWait wait = new PleaseWait();
                        wait.setVisible(true);
                        SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
                        {
                            @Override
                            protected Void doInBackground()
                            {
                                Category category = new Category();
                                category.setCategoryName(categoryName);
                                category.setRegDate(regDate);

                                message = new Message();
                                catServ = new CategoryService();

                                message = catServ.registerCategory(category);
                                if(message.isMessageStatus()){
                                    JOptionPane.showMessageDialog(null,message.getMessage(),"Error Occured",JOptionPane.ERROR_MESSAGE);
                                }
                                else{
                                    systemState = false;
                                    txtCategoryName.setText("");
                                    txtCatRegDate.setText("");
                                    getCategoryFromDB();
                                    setCategoryInDashbordPageInHardwareRegister();
//                                    selectCategory.addItem(category.getCategoryName());
//                                    stockCategoryList.addItem(category.getCategoryName());
//                                    Collections.reverse(categories);
//                                    categories.add(category);
//                                    Collections.reverse(categories);
//                                    setCategoryInHardwareSearch();
                                    JOptionPane.showMessageDialog(null,message.getMessage(),"Succeed",JOptionPane.INFORMATION_MESSAGE);
                                }
                                return null;
                            }

                            @Override
                            protected void done()
                            {
                                systemState = true;
                               wait.dispose();
                            }
                        };
                        worker.execute();
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Date Must be in YYYY-MM-DD eg : 2077-02-06","Invalid Date",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_catRegButtonActionPerformed

    private void hardwareRegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hardwareRegisterButtonActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to register ?","Confirm Message",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==0){
            String hardwareName = txtHardwareName.getText();
            String category = selectCategory.getSelectedItem().toString();
            String size = txtHardwareSize.getText();
            String price = txtHardwarePrice.getText();
            String regDate = txtHardwareRegDate.getText();

            if(hardwareName.equals("") || category.equals("") || size.equals("") || price.equals("") || regDate.equals("")){
                JOptionPane.showMessageDialog(null,"All fields are required.!!!","Empty Fields",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                if(this.checkDate(regDate)){
                    boolean hardwareTest = false;
                    for(Hardwares hardware : this.hardwares){
                        if(hardware.getHardwareName().equals(hardwareName)){
                            hardwareTest = true;
                            break;
                        }
                    }
                    if(hardwareTest){
                        JOptionPane.showMessageDialog(null,"Hardware with same name exist. Please enter another name.","Hardware Exist",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        PleaseWait wait = new PleaseWait();
                        wait.setVisible(true);
                        SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
                        {
                            @Override
                            protected Void doInBackground()
                            {
                                HardwareService hardServ = new HardwareService();
                                Hardwares hardware = new Hardwares();
                                hardware.setHardwareName(hardwareName);
                                hardware.setCategory(category);
                                hardware.setSize(size);
                                hardware.setPrice(Integer.parseInt(price));
                                hardware.setRegDate(regDate);
                                message = hardServ.registerHardware(hardware);
                                if(message.isMessageStatus()){
                                    JOptionPane.showMessageDialog(null,message.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                                }
                                else{
                                    txtHardwareName.setText("");
                                    txtHardwareSize.setText("");
                                    txtHardwarePrice.setText("");
                                    txtHardwareRegDate.setText("");
                                    getHardwaresFromDB();
//                                    Collections.reverse(hardwares);
//                                    hardwares.add(hardware);
//                                    Collections.reverse(hardwares);
//                                    String cate = stockCategoryList.getSelectedItem().toString();
//                                    systemState = false;
//                                    setHardwareNameInSelection(cate);
//                                    systemState = true;
                                    JOptionPane.showMessageDialog(null,message.getMessage(),"Succeed",JOptionPane.INFORMATION_MESSAGE);
                                }
                                return null;
                            }

                            @Override
                            protected void done()
                            {
                               wait.dispose();
                            }
                        };
                        worker.execute();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Date Must be in YYYY-MM-DD eg : 2077-02-06","Invalid Date",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_hardwareRegisterButtonActionPerformed

    private void stockCategoryListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockCategoryListMouseEntered
       this.systemState = true;
    }//GEN-LAST:event_stockCategoryListMouseEntered
    
    
    private void stockSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockSubmitButtonActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null,"Are you sure ?","Confirm Message",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==0){
            String category = stockCategoryList.getSelectedItem().toString();
            String hardwareName = stockHardwareName.getSelectedItem().toString();
            String action = stockAction.getSelectedItem().toString();
            String comment = stockComment.getText();
            String quantity = stockQuantity.getText();
            String date = stockDate.getText();
            if(category.equals("") || hardwareName.equals("") || action.equals("") || comment.equals("") || quantity.equals("") || date.equals("")){
                JOptionPane.showMessageDialog(null,"All fields are required.!!!","Empty Fields",JOptionPane.INFORMATION_MESSAGE);
            }else{
                if(this.checkDate(date)){
                    if(this.checkQuantityParsable(quantity)){
                        PleaseWait wait = new PleaseWait();
                        wait.setVisible(true);
                        SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
                        {
                            @Override
                            protected Void doInBackground()
                            {
                                StockDetails stockDetails = new StockDetails();
                                Hardwares hardware = new Hardwares();
                                hardware.setCategory(category);
                                hardware.setHardwareName(hardwareName);
                                hardware.setAvailableQuantity(Integer.parseInt(quantity));

                                stockDetails.setComment(comment);
                                stockDetails.setAction(action);
                                stockDetails.setQuantity(Integer.parseInt(quantity));
                                stockDetails.setRegDate(date);

                                message =new Message();
                                StockDetailsService stockDetailsService = new StockDetailsService();
                                message = stockDetailsService.updateStock(hardware, stockDetails, action);
                                if(message.isMessageStatus()){
                                    JOptionPane.showMessageDialog(null,message.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                                }
                                else{
                                    stockComment.setText("");
                                    stockQuantity.setText("");
                                    stockDate.setText("");
                                    getHardwaresFromDB();
                                    JOptionPane.showMessageDialog(null,message.getMessage(),"Succeed",JOptionPane.INFORMATION_MESSAGE);
                                }
                                return null;
                            }

                            @Override
                            protected void done()
                            {
                               wait.dispose();
                            }
                        };
                        worker.execute();
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Quantity must be in number.","Invalid Quantity",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Date Must be in YYYY-MM-DD eg : 2077-02-06","Invalid Date",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_stockSubmitButtonActionPerformed

    private void setCategoryInPanelStockManagement(){
        this.systemState = false;
        stockCategoryList.removeAllItems();
        for(Category category : this.categories){
            stockCategoryList.addItem(category.getCategoryName());
        }
        this.systemState =true;
    }
    private void setHardwareNameCorrespondingToCategoryInPanelStockManagement(){
        stockHardwareName.removeAllItems();
        String category="";
        try{
            category = stockCategoryList.getSelectedItem().toString();
        }catch(Exception er){
            category = stockCategoryList.getItemAt(0);
        }
        if(!category.equals("")){
            for(Hardwares hardware: this.hardwares){
                if(hardware.getCategory().equals(category)){
                    stockHardwareName.addItem(hardware.getHardwareName());
                }
            }
        }
    }
    private void lvlStockManagementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlStockManagementMouseClicked
        panelDashboard.setVisible(false);
        panelStockManage.setVisible(true);
        panelChangeCredentials.setVisible(false);
        panelHardwareDetails.setVisible(false);
        panelCategoryDetails.setVisible(false);
        panelStockDetails.setVisible(false);
        panelBillManagement.setVisible(false);
        
        PleaseWait wait = new PleaseWait();
        wait.setVisible(true);
        SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
        {
            @Override
            protected Void doInBackground()
            {
                setCategoryInPanelStockManagement();
                setHardwareNameCorrespondingToCategoryInPanelStockManagement();
                return null;
            }

            @Override
            protected void done()
            {
               wait.dispose();
            }
        };
        worker.execute();
    }//GEN-LAST:event_lvlStockManagementMouseClicked

    private void lvlStockManagementMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlStockManagementMouseEntered
        lvlStockManagement.setForeground(Color.MAGENTA);
    }//GEN-LAST:event_lvlStockManagementMouseEntered

    private void lvlStockManagementMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlStockManagementMouseExited
        lvlStockManagement.setForeground(Color.WHITE);
    }//GEN-LAST:event_lvlStockManagementMouseExited
    
    private void displayStockTable(){
        DefaultTableModel model = (DefaultTableModel) stockDisplayTable.getModel();
        model.setRowCount(0);
        
        JTable table = new JTable();
        table.setModel(model);
        Object rowData[] = new Object[7];
     
        long i=1;
        for(Hardwares hardware: this.hardwares)
        {
            rowData[0] = i;
            rowData[1] = hardware.getId();
            rowData[2] = hardware.getCategory();
            rowData[3] = hardware.getHardwareName();
            rowData[4] = hardware.getSize();
            rowData[5] = hardware.getPrice();
            rowData[6] = hardware.getAvailableQuantity();
            model.addRow(rowData);
            i++;
        }
    }
    private void setCategoryInStockSearch(){
        stockCategorySearch.removeAllItems();
        for(Category category:this.categories){
            stockCategorySearch.addItem(category.getCategoryName());
        }
    }
    private void lvlStockDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlStockDetailsMouseClicked
        panelDashboard.setVisible(false);
        panelStockManage.setVisible(false);
        panelChangeCredentials.setVisible(false);
        panelHardwareDetails.setVisible(false);
        panelCategoryDetails.setVisible(false);
        panelStockDetails.setVisible(true);
        panelBillManagement.setVisible(false);
        
        PleaseWait wait = new PleaseWait();
        wait.setVisible(true);
        SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
        {
            @Override
            protected Void doInBackground()
            {
                displayStockTable();
                setCategoryInStockSearch();
                return null;
            }

            @Override
            protected void done()
            {
               wait.dispose();
            }
        };
        worker.execute();
    }//GEN-LAST:event_lvlStockDetailsMouseClicked

    private void lvlStockDetailsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlStockDetailsMouseEntered
        lvlStockDetails.setForeground(Color.MAGENTA);
    }//GEN-LAST:event_lvlStockDetailsMouseEntered

    private void lvlStockDetailsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlStockDetailsMouseExited
        lvlStockDetails.setForeground(Color.WHITE);
    }//GEN-LAST:event_lvlStockDetailsMouseExited

    private void lvlHardwareDetailsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlHardwareDetailsMouseEntered
       lvlHardwareDetails.setForeground(Color.MAGENTA);
    }//GEN-LAST:event_lvlHardwareDetailsMouseEntered

    private void lvlHardwareDetailsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlHardwareDetailsMouseExited
        lvlHardwareDetails.setForeground(Color.WHITE);
    }//GEN-LAST:event_lvlHardwareDetailsMouseExited
    
    public void setCategoryInHardwareSearch(){
        this.systemState = false;
        categorySearchHardware.removeAllItems();
        for(Category category: this.categories){
            categorySearchHardware.addItem(category.getCategoryName());
        }
        this.systemState = true;
    }
    
    private void lvlHardwareDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlHardwareDetailsMouseClicked
        panelDashboard.setVisible(false);
        panelHardwareDetails.setVisible(true);
        panelStockManage.setVisible(false);
        panelChangeCredentials.setVisible(false);
        panelCategoryDetails.setVisible(false);
        panelStockDetails.setVisible(false);
        panelBillManagement.setVisible(false);
        
        PleaseWait wait = new PleaseWait();
        wait.setVisible(true);
        SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
        {
            @Override
            protected Void doInBackground()
            {
                displayHardwareDetailsTable();
                setCategoryInHardwareSearch();
                String localCategory;
                try{
                    localCategory = categorySearchHardware.getSelectedItem().toString();
                }
                catch(Exception er){
                     localCategory = categorySearchHardware.getItemAt(0);
                }
                setHardwareNameCorrespondingToCategoryInHardwareSearch(localCategory);
                return null;
            }

            @Override
            protected void done()
            {
               wait.dispose();
            }
        };
        worker.execute();
    }//GEN-LAST:event_lvlHardwareDetailsMouseClicked
    
    private Hardwares getSelectedHardware(){
        Hardwares hardware = new Hardwares();
        try{
            int rowIndex = hardwaresDisplayTable.getSelectedRow();
            TableModel model = hardwaresDisplayTable.getModel();
            long hardwareId = Long.parseLong(model.getValueAt(rowIndex,1).toString());
            for(Hardwares hardwaree : this.hardwares){
                if(hardwaree.getId()==hardwareId){
                    hardware = hardwaree;
                }
            }
        }catch(Exception er){
            hardware = null;
        }
        
        return hardware;
    }
    public void applyHardwareUpdate(){
        this.getHardwaresFromDB();
        this.displayHardwareDetailsTable();
        String localCategory;
        try{
            localCategory = categorySearchHardware.getSelectedItem().toString();
        }
        catch(Exception er){
             localCategory = categorySearchHardware.getItemAt(0);
        }
        setHardwareNameCorrespondingToCategoryInHardwareSearch(localCategory);
    }
    private void updateHardwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateHardwareActionPerformed
        Hardwares hardware = new Hardwares();
        hardware = this.getSelectedHardware();
        if(hardware==null){
            JOptionPane.showMessageDialog(null,"Please Select any row","Empty Selection",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            SingleHardware singleHardware = new SingleHardware();
            singleHardware.setVisible(true);
            singleHardware.setHardware(hardware,this.categories,this.hardwares);
            singleHardware.setMainFrame(this);
        }
    }//GEN-LAST:event_updateHardwareActionPerformed
    public void setHardwareNameInHardwareSearchAfterUpdate(){
        String category;
        try{
            category = categorySearchHardware.getSelectedItem().toString();
        }
        catch(Exception er){
            System.out.println("Error : "+er.getMessage());
            category = categorySearchHardware.getItemAt(0);
        }
        this.setHardwareNameCorrespondingToCategoryInHardwareSearch(category);
    }
    private void setHardwareNameCorrespondingToCategoryInHardwareSearch(String category){
        hardwareNameSearchHardware.removeAllItems();
        hardwareNameSearchHardware.addItem("----");
        for(Hardwares hardware : this.hardwares){
            if(hardware.getCategory().equals(category)){
                hardwareNameSearchHardware.addItem(hardware.getHardwareName());
            }
        }
    }
    
    private void hardwareSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hardwareSearchActionPerformed
        String category = categorySearchHardware.getSelectedItem().toString();
        String hardwareName = hardwareNameSearchHardware.getSelectedItem().toString();
        if(hardwareName.equals("----")){
            DefaultTableModel model = (DefaultTableModel) hardwaresDisplayTable.getModel();
            model.setRowCount(0);

            JTable table = new JTable();
            table.setModel(model);
            Object rowData[] = new Object[7];
           // Collections.reverse(this.hardwares);
            long i=1;
            for(Hardwares hardware: this.hardwares)
            {
                if(hardware.getCategory().equals(category)){
                    rowData[0] = i;
                    rowData[1] = hardware.getId();
                    rowData[2] = hardware.getCategory();
                    rowData[3] = hardware.getHardwareName();
                    rowData[4] = hardware.getSize();
                    rowData[5] = hardware.getPrice();
                    rowData[6] = hardware.getRegDate();
                    model.addRow(rowData);
                    i++;
                }
            }
           // Collections.reverse(this.hardwares);
        }
        else{
            DefaultTableModel model = (DefaultTableModel) hardwaresDisplayTable.getModel();
            model.setRowCount(0);

            JTable table = new JTable();
            table.setModel(model);
            Object rowData[] = new Object[7];
           // Collections.reverse(this.hardwares);
            long i=1;
            for(Hardwares hardware: this.hardwares)
            {
                if(hardware.getCategory().equals(category) && hardware.getHardwareName().equals(hardwareName)){
                    rowData[0] = i;
                    rowData[1] = hardware.getId();
                    rowData[2] = hardware.getCategory();
                    rowData[3] = hardware.getHardwareName();
                    rowData[4] = hardware.getSize();
                    rowData[5] = hardware.getPrice();
                    rowData[6] = hardware.getRegDate();
                    model.addRow(rowData);
                    i++;
                }
            }
           // Collections.reverse(this.hardwares);
        }
    }//GEN-LAST:event_hardwareSearchActionPerformed

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        this.displayHardwareDetailsTable();
    }//GEN-LAST:event_jLabel34MouseClicked
    private Category getSelectedCategory(){
        Category category = new Category();
        try{
            int rowIndex = categoryDisplayTable.getSelectedRow();
            TableModel model = categoryDisplayTable.getModel();
            long categoryId = Long.parseLong(model.getValueAt(rowIndex,1).toString());
            for(Category categorye : this.categories){
                if(categorye.getId()==categoryId){
                    category = categorye;
                }
            }
        }catch(Exception er){
            category = null;
        }
        return category;
    }
    private void categoryUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryUpdateButtonActionPerformed
        Category category = new Category();
        category = this.getSelectedCategory();
        if(category==null){
            JOptionPane.showMessageDialog(null,"Please Select any row","Empty Selection",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            SingleCategory singleCategory = new SingleCategory();
            singleCategory.setVisible(true);
            singleCategory.setCategoryObject(this,category,this.categories);
        }
    }//GEN-LAST:event_categoryUpdateButtonActionPerformed
    public void updateCategoryList(){
        this.getCategoryFromDB();
        this.displayCategoryDetailsTable();
        this.getHardwaresFromDB();
    }
    private void lvlCategoryDetailsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlCategoryDetailsMouseEntered
        lvlCategoryDetails.setForeground(Color.MAGENTA);
    }//GEN-LAST:event_lvlCategoryDetailsMouseEntered

    private void lvlCategoryDetailsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlCategoryDetailsMouseExited
        lvlCategoryDetails.setForeground(Color.WHITE);
    }//GEN-LAST:event_lvlCategoryDetailsMouseExited
    private void displayCategoryDetailsTable(){
        DefaultTableModel model = (DefaultTableModel) categoryDisplayTable.getModel();
        model.setRowCount(0);

        JTable table = new JTable();
        table.setModel(model);
        Object rowData[] = new Object[4];
        long i=1;
        for(Category category: this.categories)
        {
           rowData[0] = i;
            rowData[1] = category.getId();
            rowData[2] = category.getCategoryName();
            rowData[3] = category.getRegDate();
            model.addRow(rowData);
            i++;
        }
    }
    private void lvlCategoryDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlCategoryDetailsMouseClicked
        panelDashboard.setVisible(false);
        panelHardwareDetails.setVisible(false);
        panelStockManage.setVisible(false);
        panelChangeCredentials.setVisible(false);
        panelCategoryDetails.setVisible(true);
        panelStockDetails.setVisible(false);
        panelBillManagement.setVisible(false);
        
        PleaseWait wait = new PleaseWait();
        wait.setVisible(true);
        SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
        {
            @Override
            protected Void doInBackground()
            {
                displayCategoryDetailsTable();
                return null;
            }

            @Override
            protected void done()
            {
               wait.dispose();
            }
        };
        worker.execute();
    }//GEN-LAST:event_lvlCategoryDetailsMouseClicked

    private void hardwareDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hardwareDeleteButtonActionPerformed

        this.hardware = new Hardwares();
        this.hardware = this.getSelectedHardware();
        if(this.hardware==null){
            JOptionPane.showMessageDialog(null,"Please Select any row","Empty Selection",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String expDate = JOptionPane.showInputDialog(null,"If you are sure to delete. Enter today's date :","Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            if(expDate!=null){
                if(this.checkDate(expDate)){
                    
                    PleaseWait wait = new PleaseWait();
                    wait.setVisible(true);
                    SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
                    {
                        @Override
                        protected Void doInBackground()
                        {
                            hardServ = new HardwareService();
                            message = new Message();
                            hardware.setExpDate(expDate);
                            message = hardServ.deleteHardware(hardware);
                            if(message.isMessageStatus()){
                                JOptionPane.showMessageDialog(null,message.getMessage(),"Error.",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
//                                hardwares.remove(hardware);
//                                displayHardwareDetailsTable();
                                getHardwaresFromDB();
                                displayHardwareDetailsTable();
                                JOptionPane.showMessageDialog(null,message.getMessage(),"Succeed",JOptionPane.INFORMATION_MESSAGE);
                            }
                            return null;
                        }

                        @Override
                        protected void done()
                        {
                           //systemState = true;
                           wait.dispose();
                        }
                    };
                    worker.execute();
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Date Must be in YYYY-MM-DD eg : 2077-02-06","Invalid Date",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_hardwareDeleteButtonActionPerformed

    private void categoryDelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryDelButtonActionPerformed
        this.threadCategory = new Category();
        this.threadCategory = this.getSelectedCategory();
        if(this.threadCategory==null){
            JOptionPane.showMessageDialog(null,"Please Select any row","Empty Selection",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String expDate = JOptionPane.showInputDialog(null,"If you are sure to delete. Enter today's date : ","Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            if(expDate!=null){
                if(this.checkDate(expDate)){
                    
                    PleaseWait wait = new PleaseWait();
                    wait.setVisible(true);
                    SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
                    {
                        @Override
                        protected Void doInBackground()
                        {
                            catServ = new CategoryService();
                            message = new Message();
                            threadCategory.setExpDate(expDate);
                            message = catServ.deleteCategory(threadCategory);
                            if(message.isMessageStatus()){
                                JOptionPane.showMessageDialog(null,message.getMessage(),"Error.",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                updateCategoryList();
//                                systemState = false;
//                                setHardwareAndCategoryDetails();
//                                setCategoryInHardwareSearch();
//                                setCategoryInStockSearch();
//                                displayCategoryDetailsTable();
//                                systemState = true;
                                JOptionPane.showMessageDialog(null,message.getMessage(),"Succeed",JOptionPane.INFORMATION_MESSAGE);
                            }
                            return null;
                        }

                        @Override
                        protected void done()
                        {
                           wait.dispose();
                        }
                    };
                    worker.execute();
                    
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Date Must be in YYYY-MM-DD eg : 2077-02-06","Invalid Date",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_categoryDelButtonActionPerformed

    private void stockCategorySearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stockCategorySearchItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_stockCategorySearchItemStateChanged
    private void displayStockDetailsAccordingToCategory(String category){
        DefaultTableModel model = (DefaultTableModel) stockDisplayTable.getModel();
        model.setRowCount(0);
        
        JTable table = new JTable();
        table.setModel(model);
        Object rowData[] = new Object[7];
     
        long i=1;
        for(Hardwares hardware: this.hardwares)
        {
            if(hardware.getCategory().equals(category)){
                rowData[0] = i;
                rowData[1] = hardware.getId();
                rowData[2] = hardware.getCategory();
                rowData[3] = hardware.getHardwareName();
                rowData[4] = hardware.getSize();
                rowData[5] = hardware.getPrice();
                rowData[6] = hardware.getAvailableQuantity();
                model.addRow(rowData);
                i++;
            }
        }
    }
    private void stockSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockSearchActionPerformed
        String category = stockCategorySearch.getSelectedItem().toString();
        this.displayStockDetailsAccordingToCategory(category);
    }//GEN-LAST:event_stockSearchActionPerformed

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        this.displayStockTable();
    }//GEN-LAST:event_jLabel40MouseClicked
    private Hardwares getSelectedHardwareFromStockDisplayTable(){
         Hardwares hardware = new Hardwares();
        try{
            int rowIndex = stockDisplayTable.getSelectedRow();
            TableModel model = stockDisplayTable.getModel();
            long hardwareId = Long.parseLong(model.getValueAt(rowIndex,1).toString());
            for(Hardwares hardwaree : this.hardwares){
                if(hardwaree.getId()==hardwareId){
                    hardware = hardwaree;
                }
            }
        }catch(Exception er){
            hardware = null;
        }
        
        return hardware;
    }
    private void viewStockDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewStockDetailsButtonActionPerformed
        Hardwares hardware = new Hardwares();
        hardware = this.getSelectedHardwareFromStockDisplayTable();
        if(hardware==null){
            JOptionPane.showMessageDialog(null,"Please Select any row","Empty Selection",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            SingleStockDetails singleStockDetails = new SingleStockDetails();
            singleStockDetails.setVisible(true);
            singleStockDetails.setStockDetails(hardware);
        }
    }//GEN-LAST:event_viewStockDetailsButtonActionPerformed

    private void stockQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockQuantityActionPerformed
    private void setHardwareOnBilling(){
        selectHardware.removeAllItems();
        for(Hardwares hardware: this.hardwares){
            selectHardware.addItem(hardware.getHardwareName());
        }
    }
    private void lvlBillManagementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlBillManagementMouseClicked
        panelDashboard.setVisible(false);
        panelHardwareDetails.setVisible(false);
        panelStockManage.setVisible(false);
        panelChangeCredentials.setVisible(false);
        panelCategoryDetails.setVisible(false);
        panelStockDetails.setVisible(false);
        panelBillManagement.setVisible(true);
        
        PleaseWait wait = new PleaseWait();
        wait.setVisible(true);
        SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
        {
            @Override
            protected Void doInBackground()
            {
                setHardwareOnBilling();
                displayBillTable();
                return null;
            }

            @Override
            protected void done()
            {
               wait.dispose();
            }
        };
        worker.execute();
    }//GEN-LAST:event_lvlBillManagementMouseClicked

    private void lvlBillManagementMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlBillManagementMouseEntered
        lvlBillManagement.setForeground(Color.MAGENTA);
    }//GEN-LAST:event_lvlBillManagementMouseEntered

    private void lvlBillManagementMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvlBillManagementMouseExited
        lvlBillManagement.setForeground(Color.WHITE);
    }//GEN-LAST:event_lvlBillManagementMouseExited
    private boolean checkVatNoParsable(String vatNo){
        boolean vatCheck = true;
        try{
            Long.parseLong(vatNo);
        }
        catch(Exception er){
            vatCheck = false;
        }
        return vatCheck;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String shopName = txtShopName.getText();
        String shopAddress = txtShopAddress.getText();
        String vatNo = txtVatNumber.getText();
        
        if(shopName.equals("") || shopAddress.equals("") || vatNo.equals("")){
            JOptionPane.showMessageDialog(null,"All fields are required.!!1","Empty Field",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to update ?","Confirm message",JOptionPane.INFORMATION_MESSAGE);
            if(confirm==0){
                if(this.checkVatNoParsable(vatNo)){
                    PleaseWait wait = new PleaseWait();
                    wait.setVisible(true);
                    SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
                    {
                        @Override
                        protected Void doInBackground()
                        {
                            shopDetails.setShopName(shopName);
                            shopDetails.setShopAddress(shopAddress);
                            shopDetails.setVatNo(Long.parseLong(vatNo));
                            message = new Message();
                            shopDetailsServ = new ShopDetailsService();
                            message = shopDetailsServ.updateShop(shopDetails);
                            if(message.isMessageStatus()){
                                JOptionPane.showMessageDialog(null,message.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                setTitle(shopName);
                                JOptionPane.showMessageDialog(null,message.getMessage(),"Update succeed.",JOptionPane.INFORMATION_MESSAGE);
                            }
                            return null;
                        }

                        @Override
                        protected void done()
                        {
                           wait.dispose();
                        }
                    };
                    worker.execute();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Vat No must be in numbers.","Invalid Vat Number",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNameActionPerformed

    private void txtContactNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactNumberActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtVatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVatActionPerformed

    private void txtGrandTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGrandTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrandTotalActionPerformed
    private boolean checkContactNumber(String contactNumber){
        boolean checkStatus = true;
        try{
            Long.parseLong(contactNumber);
            if(contactNumber.length()!=10){
                checkStatus = false;
            }
        }catch(Exception er){
            checkStatus = false;
        }
        return checkStatus;
    }
    private void createBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBillButtonActionPerformed
        if(txtDiscount.getText().equals("")){
            txtDiscount.setText("0.00");
        }
        String billNo = txtBillNo.getText();
        String billDate = txtDate.getText();
        String customerName = txtCustomerName.getText();
        String customerAddress = txtCustomerAddress.getText();
        String customerNumber = txtContactNumber.getText();
        String paymentStatus = selectPaymentStatus.getSelectedItem().toString();
        String total = txtTotal.getText();
        String netTotal = txtNetTotal.getText();
        String vatAmount = txtVat.getText();
        String discount = txtDiscount.getText();
        String grandTotal = txtGrandTotal.getText();
        String amountPaid = txtAmountPaid.getText();
        
        if(billNo.equals("")||billDate.equals("")||customerName.equals("")||customerAddress.equals("")||
                customerNumber.equals("")||discount.equals("")||amountPaid.equals("")){
            JOptionPane.showMessageDialog(null,"All fields are required.!!","Empty Fields.",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(this.checkQuantityParsable(billNo)){
                if(this.checkDate(billDate)){
                    if(this.checkContactNumber(customerNumber)){
                        if(this.checkDoubleValueParsable(amountPaid)){
                            int check = JOptionPane.showConfirmDialog(null,"Are you sure to Create ?","Confirm message",JOptionPane.INFORMATION_MESSAGE );
                            if(check==0){
                                List<SoldItems> soldItems = new ArrayList<SoldItems>();
                                SoldItems item;
                                int row = billingTable.getRowCount();
                                if(row>0){
                                    for(int i=0;i<row;i++){
                                        item = new SoldItems();
                                        item.setHardwareName(billingTable.getValueAt(i,1).toString());
                                        item.setRate(Double.parseDouble(billingTable.getValueAt(i,2).toString()));
                                        item.setQuantity(Integer.parseInt(billingTable.getValueAt(i,3).toString()));
                                        item.setTotal(Double.parseDouble(billingTable.getValueAt(i,4).toString()));
                                        soldItems.add(item);
                                    }
                                    Bill bill = new Bill();
                                    bill.setBillNo(Integer.parseInt(billNo));
                                    bill.setBillDate(billDate);
                                    bill.setCustomerName(customerName);
                                    bill.setCustomerAddress(customerAddress);
                                    bill.setCustomerContactNumber(customerNumber);
                                    bill.setPaymentStatus(paymentStatus);
                                    bill.setNetTotal(Math.round((Double.parseDouble(netTotal)) * 100D) / 100D);
                                    bill.setVatAmount(Math.round((Double.parseDouble(vatAmount))*100D) / 100D);
                                    bill.setTotal(Math.round((Double.parseDouble(total))*100D) / 100D);
                                    bill.setDiscountAmount(Math.round((Double.parseDouble(discount))*100D) / 100D);
                                    bill.setGrandTotal(Math.round((Double.parseDouble(grandTotal))*100D) / 100D);
                                    bill.setSoldItems(soldItems);

                                    AmountPaid amountsPaid = new AmountPaid();
                                    amountsPaid.setAmountPaid(Math.round((Double.parseDouble(amountPaid))*100D) / 100D);
                                    amountsPaid.setDate(billDate);
                                    amountsPaid.setAmountDue(Math.round(((Double.parseDouble(grandTotal)-Double.parseDouble(amountPaid)))*100D) / 100D);

                                    List<AmountPaid> ap = new ArrayList<AmountPaid>();
                                    ap.add(amountsPaid);
                                    bill.setAmountPaid(ap);
                                    
                                    
                                    PleaseWait wait = new PleaseWait();
                                    wait.setVisible(true);
                                    SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
                                    {
                                        @Override
                                        protected Void doInBackground()
                                        {
                                            PDFManager manager = new PDFManager();
                                            if(manager.saveBill(bill, shopDetails)){
                                                bill.setBillName(manager.getFileName());
                                                billServ = new BillService();
                                                message = new Message();
                                                message = billServ.saveBill(bill);
                                                if(message.isMessageStatus()){
                                                    File file = new File(bill.getBillName());
                                                    if(file.exists()){
                                                        file.delete();
                                                    }
                                                    JOptionPane.showMessageDialog(null,"Error Generating Bill."+message.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                                                }
                                                else{
                                                     txtBillNo.setText("");
                                                     txtDate.setText("");
                                                     txtCustomerName.setText("");
                                                     txtCustomerAddress.setText("");
                                                     txtContactNumber.setText("");
                                                     txtTotal.setText("");
                                                     txtNetTotal.setText("");
                                                     txtVat.setText("");
                                                     txtDiscount.setText("");
                                                     txtGrandTotal.setText("");
                                                     txtAmountPaid.setText("");
                                                     getBillsFromDatabase();
                                                     displayBillTable();
                                                     DefaultTableModel tableModel = (DefaultTableModel)billingTable.getModel();
                                                     tableModel.setRowCount(0);
                                                     manager.showBill();
                                                }
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null,"Error Generating Bill.","Error",JOptionPane.ERROR_MESSAGE);
                                            }
                                            return null;
                                        }

                                        @Override
                                        protected void done()
                                        {
                                           wait.dispose();
                                        }
                                    };
                                    worker.execute();

                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"Atlease one item is need to create bill.","Empty Particulars",JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Amount Paid must be in number","Invalid Amount Paid",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Customer Number must be in 10 digit number","Invalid Customer Number",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Date Must be in YYYY-MM-DD eg : 2077-02-06","Invalid Date",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Bill NO be in number","Invalid bill No.",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_createBillButtonActionPerformed
    
    private void setAmountsInBill(){
        double subTotal=0;
        double vatAmount = 0;
        double netTotal = 0;
        double discount = 0;
        double grandTotal = 0;

        for(int i=0;i<billingTable.getRowCount();i++){
            subTotal+=Double.parseDouble(billingTable.getValueAt(i,4).toString());
        }
        subTotal = Math.round(subTotal*100D) / 100D;
        vatAmount = 0.13 * subTotal;
        vatAmount = Math.round(vatAmount*100D) / 100D;
        netTotal = subTotal+vatAmount;
        netTotal = Math.round(netTotal*100D) / 100D;
        
        txtTotal.setText(Double.toString(subTotal));
        txtVat.setText(Double.toString(vatAmount));
        txtNetTotal.setText(Double.toString(netTotal));
        if(txtDiscount.getText().equals("")){
            txtDiscount.setText("0.00");
            grandTotal = netTotal;
        }
        else{
            discount = Double.parseDouble(txtDiscount.getText());
            grandTotal = netTotal - discount;
        }
        txtGrandTotal.setText(Double.toString(grandTotal));
    }
    private void addTableListener() {
        DefaultTableModel model = (DefaultTableModel) billingTable.getModel();
        model.addTableModelListener(new TableModelListener() {
       
            @Override
            public void tableChanged(TableModelEvent tme) {
                if (tme.getType() == TableModelEvent.UPDATE) {
                    int row = billingTable.getSelectedRow();
                    int column = billingTable.getSelectedColumn();
                    String strRate = billingTable.getValueAt(row, 2).toString();
                    String strQuantity = billingTable.getValueAt(row, 3).toString();
                    
                    if(checkDoubleValueParsable(strRate)){
                        if(checkQuantityParsable(strQuantity)){
                            double rate = Double.parseDouble(strRate);
                            int quantity = Integer.parseInt(strQuantity);
                            double total = rate*quantity;
                            model.removeTableModelListener(this);
                            billingTable.setValueAt(total, row, 4);
                            model.addTableModelListener(this);
                            setAmountsInBill(); 
                        }
                        else{
                            billingTable.setValueAt(0, row, 3);
                            JOptionPane.showMessageDialog(null,"Quantity must be in number","Invalid quantity",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else{
                        billingTable.setValueAt(0, row, 2);
                        JOptionPane.showMessageDialog(null,"Rate must be in number","Invalid rate",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else if(tme.getType()==TableModelEvent.DELETE){
                    model.removeTableModelListener(this);
                    for(int i=0;i<billingTable.getRowCount();i++){
                        model.setValueAt((i+1), i, 0);
                    }
                    model.addTableModelListener(this);
                }
            }
        });
    }
    
    
    private void addItemsInParticulars(String hardwareName,int quantity){
        DefaultTableModel model = (DefaultTableModel) billingTable.getModel();
        
        JTable table = new JTable();
        table.setModel(model);
        Object rowData[] = new Object[5];
        
        double rate = 0.0;
        for(Hardwares hardware : this.hardwares){
            if(hardware.getHardwareName().equals(hardwareName)){
                rate = hardware.getPrice();
                break;
            }
        }
        
        
        rowData[0] = model.getRowCount()+1;
        rowData[1] = hardwareName;
        rowData[2] = Math.round(rate*100D) / 100D;
        rowData[3] = quantity;
        rowData[4] = Math.round((rate*quantity)*100D) / 100D;
        model.addRow(rowData);
    }
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String hardwareName = selectHardware.getSelectedItem().toString();
        String quantity = txtQuantity.getText();
        if(quantity.equals("")){
            JOptionPane.showMessageDialog(null,"Quantity required.!!!","Empty quantity",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(this.checkQuantityParsable(quantity)){
                this.addItemsInParticulars(hardwareName,Integer.parseInt(quantity));
                this.setAmountsInBill();
                txtQuantity.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null,"Quantity must be in number","Invalid quantity",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void txtDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscountActionPerformed
        String strNetTotal = txtNetTotal.getText();
        String strDiscount = txtDiscount.getText();
        if(!strDiscount.equals("")){
            if(this.checkDoubleValueParsable(strDiscount)){
                double netTotal = Double.parseDouble(strNetTotal);
                double discount = Double.parseDouble(strDiscount);
                double grandTotal = netTotal - discount;
                txtGrandTotal.setText(Double.toString(grandTotal));
            }
            else{
                txtDiscount.setText("0.00");
                JOptionPane.showMessageDialog(null,"Discount must be in number","Invalid Discount",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtDiscountActionPerformed
    
     
     
    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel)billingTable.getModel();
        int rowIndex = billingTable.getSelectedRow();
        if(rowIndex!=-1){
            model.removeRow(rowIndex);
            this.setAmountsInBill();
        }
        else{
            JOptionPane.showMessageDialog(null,"Please select any row","Invalid Selection",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_removeButtonActionPerformed
    
    private Bill getSelectedBillFromBillDisplayTable(){
         Bill bill = new Bill();
        try{
            int rowIndex = billDisplayTable.getSelectedRow();
            TableModel model = billDisplayTable.getModel();
            long billNo = Long.parseLong(model.getValueAt(rowIndex,1).toString());
            for(Bill bil : this.bills){
                if(bil.getBillNo()==billNo){
                    bill = bil;
                    break;
                }
            }
        }catch(Exception er){
            bill = null;
        }
        
        return bill;
    }
    
    private void viewBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBillActionPerformed
        Bill bill = new Bill();
        bill = this.getSelectedBillFromBillDisplayTable();
        if(bill==null){
            JOptionPane.showMessageDialog(null,"Please Select any row","Empty Selection",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            PDFManager manager = new PDFManager();
            manager.setFileName(bill.getBillName());
            manager.showBill();
        }
    }//GEN-LAST:event_viewBillActionPerformed

    private void viewPaymentDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPaymentDetailsActionPerformed
        Bill bill = new Bill();
        bill = this.getSelectedBillFromBillDisplayTable();
        if(bill==null){
            JOptionPane.showMessageDialog(null,"Please Select any row","Empty Selection",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            SinglePaymentDetails singlePaymentDetails = new SinglePaymentDetails();
            singlePaymentDetails.setBill(bill,this);
            singlePaymentDetails.setVisible(true);
        }
    }//GEN-LAST:event_viewPaymentDetailsActionPerformed

    private void deleteBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBillActionPerformed
        this.bill = new Bill();
        this.bill = this.getSelectedBillFromBillDisplayTable();
        if(this.bill==null){
            JOptionPane.showMessageDialog(null,"Please Select any row","Empty Selection",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete ? ","Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            if(confirm==0){
                
                PleaseWait wait = new PleaseWait();
                wait.setVisible(true);
                SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
                {
                    @Override
                    protected Void doInBackground()
                    {
                        billServ = new BillService();
                        message = new Message();
                        message = billServ.deleteBill(bill);
                        if(message.isMessageStatus()){
                            JOptionPane.showMessageDialog(null,message.getMessage(),"Failed",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            bills.remove(bill);
                            displayBillTable();
                            JOptionPane.showMessageDialog(null,message.getMessage(),"Success",JOptionPane.INFORMATION_MESSAGE);
                        }
                        return null;
                    }

                    @Override
                    protected void done()
                    {
                       wait.dispose();
                    }
                };
                worker.execute();
            }
        } 
    }//GEN-LAST:event_deleteBillActionPerformed

    private void hardwareNameSearchHardwareMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hardwareNameSearchHardwareMouseEntered
       this.systemState = true;
    }//GEN-LAST:event_hardwareNameSearchHardwareMouseEntered

    private void stockCategoryListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stockCategoryListItemStateChanged
       try{
           if(this.systemState){
                String category = stockCategoryList.getSelectedItem().toString();
                this.setHardwareNameInSelection(category);
            } 
       }catch(Exception er){
           this.systemState = false;
            System.out.println("Error : "+er.getMessage());
       }
        
    }//GEN-LAST:event_stockCategoryListItemStateChanged

    private void categorySearchHardwareItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_categorySearchHardwareItemStateChanged
        try{
            if(this.systemState){
                String category = categorySearchHardware.getSelectedItem().toString();
                this.setHardwareNameCorrespondingToCategoryInHardwareSearch(category);
            }
        }catch(Exception er){
            this.systemState = false;
            System.out.println("Error : "+er.getMessage());
        }
        
    }//GEN-LAST:event_categorySearchHardwareItemStateChanged

    private void categorySearchHardwareMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorySearchHardwareMouseEntered
        this.systemState = true;
    }//GEN-LAST:event_categorySearchHardwareMouseEntered

    private void categorySearchHardwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorySearchHardwareActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categorySearchHardwareActionPerformed

    private void txtDiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscountKeyReleased
        String strNetTotal = txtNetTotal.getText();
        String strDiscount = txtDiscount.getText();
        if(!strDiscount.equals("")){
            if(this.checkDoubleValueParsable(strDiscount)){
                double netTotal = Double.parseDouble(strNetTotal);
                double discount = Double.parseDouble(strDiscount);
                double grandTotal = netTotal - discount;
                txtGrandTotal.setText(Double.toString(grandTotal));
            }
            else{
                txtDiscount.setText("0.00");
                JOptionPane.showMessageDialog(null,"Discount must be in number","Invalid Discount",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(strDiscount.equals("")){
            txtGrandTotal.setText(txtNetTotal.getText());
        }
    }//GEN-LAST:event_txtDiscountKeyReleased

    private void txtAmountPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountPaidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountPaidActionPerformed
    
    public void displayBillTable(){
        DefaultTableModel model = (DefaultTableModel)billDisplayTable.getModel();
        model.setRowCount(0);
        
        Object[] row = new Object[6];
        int i=1;
        for(Bill bill:this.bills){
            row[0] = i;
            row[1] = bill.getBillNo();
            row[2] = bill.getCustomerName();
            row[3] = bill.getCustomerContactNumber();
            row[4] = bill.getPaymentStatus();
            row[5] = bill.getBillDate();
            model.addRow(row);
            i++;
        }
    }
    public void getBillsFromDatabase(){
        this.billServ = new BillService();
        this.bills = this.billServ.getAllBills();
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTabbedPane billDetails;
    private javax.swing.JTable billDisplayTable;
    private javax.swing.JTable billingTable;
    private javax.swing.JButton catRegButton;
    private javax.swing.JButton categoryDelButton;
    private javax.swing.JTable categoryDisplayTable;
    private javax.swing.JComboBox<String> categorySearchHardware;
    private javax.swing.JButton categoryUpdateButton;
    private javax.swing.JButton changeButton;
    private javax.swing.JButton createBillButton;
    private javax.swing.JButton deleteBill;
    private javax.swing.JButton hardwareDeleteButton;
    private javax.swing.JComboBox<String> hardwareNameSearchHardware;
    private javax.swing.JButton hardwareRegisterButton;
    private javax.swing.JButton hardwareSearch;
    private javax.swing.JTable hardwaresDisplayTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JPanel leftMenu;
    private javax.swing.JLabel lvlBillManagement;
    private javax.swing.JLabel lvlCategoryDetails;
    private javax.swing.JLabel lvlCheck;
    private javax.swing.JLabel lvlCredentials;
    private javax.swing.JLabel lvlDashboard;
    private javax.swing.JLabel lvlHardwareDetails;
    private javax.swing.JLabel lvlLogout;
    private javax.swing.JLabel lvlStockDetails;
    private javax.swing.JLabel lvlStockManagement;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panelBillManagement;
    private javax.swing.JPanel panelCategoryDetails;
    private javax.swing.JPanel panelChangeCredentials;
    private javax.swing.JPanel panelDashboard;
    private javax.swing.JPanel panelHardwareDetails;
    private javax.swing.JPanel panelStockDetails;
    private javax.swing.JPanel panelStockManage;
    private javax.swing.JButton removeButton;
    private javax.swing.JPanel rightPanels;
    private javax.swing.JComboBox<String> selectCategory;
    private javax.swing.JComboBox<String> selectHardware;
    private javax.swing.JComboBox<String> selectPaymentStatus;
    private javax.swing.JComboBox<String> stockAction;
    private javax.swing.JComboBox<String> stockCategoryList;
    private javax.swing.JComboBox<String> stockCategorySearch;
    private javax.swing.JTextArea stockComment;
    private javax.swing.JTextField stockDate;
    private javax.swing.JTable stockDisplayTable;
    private javax.swing.JComboBox<String> stockHardwareName;
    private javax.swing.JTextField stockQuantity;
    private javax.swing.JButton stockSearch;
    private javax.swing.JButton stockSubmitButton;
    private javax.swing.JTextField txtAmountPaid;
    private javax.swing.JTextField txtBillNo;
    private javax.swing.JTextField txtCatRegDate;
    private javax.swing.JTextField txtCategoryName;
    private javax.swing.JTextField txtContactNumber;
    private javax.swing.JTextField txtCustomerAddress;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtGrandTotal;
    private javax.swing.JTextField txtHardwareName;
    private javax.swing.JTextField txtHardwarePrice;
    private javax.swing.JTextField txtHardwareRegDate;
    private javax.swing.JTextField txtHardwareSize;
    private javax.swing.JTextField txtNetTotal;
    private javax.swing.JTextField txtNewEmail;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JTextField txtOldEmail;
    private javax.swing.JPasswordField txtOldPass;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JPasswordField txtReNewPass;
    private javax.swing.JTextField txtShopAddress;
    private javax.swing.JTextField txtShopName;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtVat;
    private javax.swing.JTextField txtVatNumber;
    private javax.swing.JButton updateHardware;
    private javax.swing.JButton viewBill;
    private javax.swing.JButton viewPaymentDetails;
    private javax.swing.JButton viewStockDetailsButton;
    // End of variables declaration//GEN-END:variables

}
