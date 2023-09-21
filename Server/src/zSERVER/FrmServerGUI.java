package zSERVER;
import PACKAGES.ComputerTableModel;
import PACKAGES.PacketTruyenFile;
import UTILS.DataUtils;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class FrmServerGUI extends javax.swing.JFrame implements Runnable {
    
    private final int mainThreadPortNumber = 1999;
    private final int theoDoiClientThreadPortNumber = 1997;
    private final int fileTransferThreadPortNumber = 1996;
    
    Timer timerUpdateListSocket;
    private int timeUpdateTable = 5; // second
    public FrmServerGUI() {
        initComponents();
        setLocation(300, 100);
        tbComputerInfo.setModel(new ComputerTableModel(new ArrayList()));
        tbComputerInfo.getColumnModel().getColumn(0).setMaxWidth(100);
        setVisible(true);
        // Cập nhật list socket sau mỗi timeUpdateTable giây
        timerUpdateListSocket = new Timer();
        timerUpdateListSocket.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getTbModel().updateAllElement();
            }
        }, timeUpdateTable * 1000, timeUpdateTable * 1000);
        runThreadTransferFile();
        // server lắng nghe theo dõi client
        
    }
    
    private ComputerTableModel getTbModel() {
        return (ComputerTableModel) tbComputerInfo.getModel();
    }


    @Override
    public void run() {
       // chat, gởi thông điệp
       try {
            final ServerSocket server = new ServerSocket(mainThreadPortNumber);
            while (true) {
                Socket socket;
                try {
                    // Nếu không dùng thread
                    // chương trình sẽ chờ 1 client đầu tiên ở đây
                    socket = server.accept();
                    getTbModel().addElement(socket);
                    System.out.println("Server: Đã kết nối client thứ "
                            + getTbModel().getSize());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }   
    }
     
    //<editor-fold defaultstate="collapsed" desc="Thread transfer file">
    private void runThreadTransferFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ServerSocket server = new ServerSocket(fileTransferThreadPortNumber);
                    while (true) {
                        Socket socket;
                        try {
                            socket = server.accept();
                            new Thread(new FrmGoiFile(socket)).start();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Thread theo dõi client">
    
    //</editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnChatClient = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnGoiThongDiep = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnTruyenTapTin = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbComputerInfo = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblTrangThai = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        btnChatClient.setBackground(java.awt.SystemColor.activeCaption);
        btnChatClient.setText("Chat Client");
        btnChatClient.setFocusable(false);
        btnChatClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChatClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChatClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatClientActionPerformed(evt);
            }
        });
        jToolBar1.add(btnChatClient);
        jToolBar1.add(jSeparator1);
        jToolBar1.add(jSeparator2);

        btnGoiThongDiep.setBackground(java.awt.SystemColor.activeCaption);
        btnGoiThongDiep.setText("Gởi thông điệp");
        btnGoiThongDiep.setFocusable(false);
        btnGoiThongDiep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGoiThongDiep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGoiThongDiep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoiThongDiepActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGoiThongDiep);
        jToolBar1.add(jSeparator3);
        jToolBar1.add(jSeparator4);

        btnTruyenTapTin.setBackground(java.awt.SystemColor.activeCaption);
        btnTruyenTapTin.setText("Truyền tập tin");
        btnTruyenTapTin.setFocusable(false);
        btnTruyenTapTin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTruyenTapTin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTruyenTapTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruyenTapTinActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTruyenTapTin);

        tbComputerInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên máy", "IP", "Lịch sử truy cập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbComputerInfo);
        if (tbComputerInfo.getColumnModel().getColumnCount() > 0) {
            tbComputerInfo.getColumnModel().getColumn(0).setResizable(false);
            tbComputerInfo.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbComputerInfo.getColumnModel().getColumn(1).setResizable(false);
            tbComputerInfo.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbComputerInfo.getColumnModel().getColumn(2).setResizable(false);
            tbComputerInfo.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbComputerInfo.getColumnModel().getColumn(3).setResizable(false);
            tbComputerInfo.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        lblTrangThai.setText("Trạng thái");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTrangThai)
                .addContainerGap(558, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblTrangThai)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Thông tin máy tính trong mạng LAN", jPanel1);

        jMenu1.setText("Quản lý");

        jMenuItem1.setText("Thoát");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("THÔNG TIN MÁY TÍNH TRONG MẠNG LAN");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChatClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatClientActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để chat!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Mở form chat với client đã chọn
        new Thread(new FrmChatVoiClient(mayClient)).start();
    }//GEN-LAST:event_btnChatClientActionPerformed
    
    private void btnGoiThongDiepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoiThongDiepActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để gởi!");
            return;
        }
        int[] rowsSelected = tbComputerInfo.getSelectedRows();
        List<Socket> dsMayClient = new ArrayList();
        for (int i : rowsSelected) {
            dsMayClient.add(getTbModel().getItem(i));
        }
        // Mở form chat với các client đã chọn
        FrmGoiThongDiep goiThongDiep = new FrmGoiThongDiep(dsMayClient);
        goiThongDiep.setVisible(true);
    }//GEN-LAST:event_btnGoiThongDiepActionPerformed
   
    private void btnTruyenTapTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTruyenTapTinActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane,
                    "Bạn chưa chọn máy để gởi file!");
            return;
        }
        Socket mayClient =
                getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Gởi lệnh yêu cầu client kết nối đến socket server file transfer
        PacketTruyenFile packetTruyenFile = new PacketTruyenFile();
        packetTruyenFile.setCmd(PacketTruyenFile.CMD_KHOIDONG);
        packetTruyenFile.setMessage(String.valueOf(fileTransferThreadPortNumber));
        DataUtils.goiDuLieu(mayClient, packetTruyenFile.toString()); 
    }//GEN-LAST:event_btnTruyenTapTinActionPerformed
         
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChatClient;
    private javax.swing.JButton btnGoiThongDiep;
    private javax.swing.JButton btnTruyenTapTin;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JTable tbComputerInfo;
    // End of variables declaration//GEN-END:variables
}
