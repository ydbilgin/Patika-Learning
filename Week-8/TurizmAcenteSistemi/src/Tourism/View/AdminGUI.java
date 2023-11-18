package Tourism.View;

import Tourism.Helper.Helper;
import Tourism.Model.User;

import javax.management.ObjectName;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;

public class AdminGUI extends JFrame {
    private JPanel wrapper;
    private JButton btn_logout;
    private JPanel pnl_top;
    private JTextField fld_search_user_name;
    private JTextField fld_search_user_uname;
    private JComboBox cmb_search_user_type;
    private JButton btn_user_search;
    private JTable tbl_user_list;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JButton btn_user_delete;
    private JPanel pnl_user_form;
    private JTextField fld_user_delete_id;
    private JLabel lbl_welcome_admin;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private User user;


    public AdminGUI(User user){
        add(wrapper);
        setSize(1000,500);
        setTitle("Admin Paneli");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        int x = Helper.screenCenter("x", this.getSize());
        int y = Helper.screenCenter("y", this.getSize());
        setLocation(x, y);
        this.user = user;
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                LoginGUI loginGUI = new LoginGUI();

            }
        });

        if (user == null){
            dispose();
        }
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                LoginGUI loginGUI = new LoginGUI();

            }
        });

        lbl_welcome_admin.setText("Hoşgeldiniz "+ user.getName());



        //User List
        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];
        loadUserModel();
        tbl_user_list.setModel(mdl_user_list);
        btn_logout.addActionListener(e -> {
            LoginGUI loginGUI = new LoginGUI();
            dispose();
        });


        btn_user_search.addActionListener(e -> {
            String name = fld_search_user_name.getText();
            String uname = fld_search_user_uname.getText();
            String type = cmb_search_user_type.getSelectedItem().toString();

            String query = User.searchQuery(name, uname, type);
            ArrayList<User> filter = User.searchUserList(query);
            loadUserModel(filter);
        });
        btn_user_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)) {
                Helper.showMsg("fill");
            } else {
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String pass = fld_user_pass.getText();
                String type = cmb_user_type.getSelectedItem().toString();
                if (User.add(name, uname, pass, type)) {
                    Helper.showMsg("done");
                    loadUserModel();
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_pass.setText(null);
                }

            }

        });
        btn_user_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_delete_id)) {
                Helper.showMsg("fill");
            } else {
                int user_id = Integer.parseInt(fld_user_delete_id.getText());
                if (User.delete(user_id)) {
                    Helper.showMsg("done");
                    loadUserModel();
                } else {
                    Helper.showMsg("error");
                }
            }

        });
        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selected_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                fld_user_delete_id.setText(selected_user_id);
            } catch (Exception exception) {
            }

        });
        tbl_user_list.addComponentListener(new ComponentAdapter() {
        });
        tbl_user_list.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString());
                String user_name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 1).toString();
                String user_uname = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 2).toString();
                String user_pass = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 3).toString();
                String user_type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 4).toString();
                if (User.update(user_id, user_name, user_uname, user_pass, user_type)) {
                    Helper.showMsg("done");
                }
                loadUserModel();
            }
        });
    }


    public void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);


        for (User obj : User.getList()) {
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();

            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadUserModel(ArrayList<User> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);


        for (User obj : list) {
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();

            mdl_user_list.addRow(row_user_list);
        }
    }

}
