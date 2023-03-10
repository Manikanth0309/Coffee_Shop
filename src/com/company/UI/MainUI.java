package com.company.UI;

import com.company.Transaction.AddTransaction;
import com.company.connection.DbAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainUI extends JFrame
{
    private JButton quick = new JButton("QUICK OPERATION");
    private JButton user = new JButton("USER");
    private JButton sessionManagement = new JButton("SESSION MANAGEMENT");
    private JButton customer = new JButton("CUSTOMER");
    private JButton product = new JButton("PRODUCT");
    private JButton tran_pro = new JButton("TRANS_PRO");
    private JButton transaction = new JButton("TRANSACTION");

    private String userID;
    private int maxID;

    private static DbAdapter dbAdapter;

    public MainUI(DbAdapter adapter, String userID, int maxID)
    {
        dbAdapter = adapter;
        this.userID = userID;
        this.maxID = maxID + 1;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Coffee Shop");
                frame.add(new MenuPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setMinimumSize(new Dimension(500, 500));
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setSize(400, 600);
                setActionListener();
                frame.addWindowListener(new WindowAdapter()
                {
                    public void windowClosing(WindowEvent e)
                    {
                        updateSessionManagement();
                        dbAdapter.disConnect();
                        System.exit(0);
                    }
                });
            }
        });
    }

    private void updateSessionManagement()
    {
        try
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Statement statement = dbAdapter.getConnection().createStatement();
            String sqlInsert = "INSERT INTO SESSION_MANAGEMENT (SESSION_ID, USER_ID, LOGIN_TIME, LOGOUT_TIME) "+
                    "VALUES (" + maxID + ", '" + userID + "', '" + "---" + "', '" + dtf.format(now) +"');";
            statement.executeUpdate(sqlInsert);
            statement.close();
        }
        catch(Exception e){e.printStackTrace();}
    }

    private class MenuPane extends JPanel {

        public MenuPane() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            this.add(new JLabel("<html><h1><strong><i>Welcome to Coffee Shop Database!</i></strong></h1><hr>" +
                    "<h2><center>Author: Manikanth</center></h2></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel buttons = new JPanel(new GridBagLayout());
            buttons.add(quick, gbc);
            buttons.add(Box.createVerticalStrut(50));
            buttons.add(user, gbc);
            buttons.add(sessionManagement, gbc);
            buttons.add(Box.createVerticalStrut(50));
            buttons.add(customer, gbc);
            buttons.add(product, gbc);
            buttons.add(Box.createVerticalStrut(50));
            buttons.add(tran_pro, gbc);
            buttons.add(transaction, gbc);

            gbc.weighty = 1;
            add(buttons, gbc);
        }
    }

    private void setActionListener()
    {
        customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerUI frame = new CustomerUI(dbAdapter);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(750, 500));
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenSize = toolkit.getScreenSize();
                int x = (screenSize.width - frame.getWidth()) / 2;
                int y = (screenSize.height - frame.getHeight()) / 2;
                frame.setLocation(x, y);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        tran_pro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tran_ProUI frame = new Tran_ProUI(dbAdapter);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(750, 500));
                frame.setVisible(true);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenSize = toolkit.getScreenSize();
                int x = (screenSize.width - frame.getWidth()) / 2;
                int y = (screenSize.height - frame.getHeight()) / 2;
                frame.setLocation(x, y);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserUI frame = new UserUI(dbAdapter);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(750, 500));
                frame.setVisible(true);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenSize = toolkit.getScreenSize();
                int x = (screenSize.width - frame.getWidth()) / 2;
                int y = (screenSize.height - frame.getHeight()) / 2;
                frame.setLocation(x, y);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        sessionManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SessionManagementUI frame = new SessionManagementUI(dbAdapter);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(750, 500));
                frame.setVisible(true);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenSize = toolkit.getScreenSize();
                int x = (screenSize.width - frame.getWidth()) / 2;
                int y = (screenSize.height - frame.getHeight()) / 2;
                frame.setLocation(x, y);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        transaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransactionUI frame = new TransactionUI(dbAdapter);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(750, 500));
                frame.setVisible(true);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenSize = toolkit.getScreenSize();
                int x = (screenSize.width - frame.getWidth()) / 2;
                int y = (screenSize.height - frame.getHeight()) / 2;
                frame.setLocation(x, y);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductUI frame = new ProductUI(dbAdapter);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(750, 500));
                frame.setVisible(true);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenSize = toolkit.getScreenSize();
                int x = (screenSize.width - frame.getWidth()) / 2;
                int y = (screenSize.height - frame.getHeight()) / 2;
                frame.setLocation(x, y);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        quick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTransaction frame = new AddTransaction(dbAdapter);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(750, 500));
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenSize = toolkit.getScreenSize();
                int x = (screenSize.width - frame.getWidth()) / 2;
                int y = (screenSize.height - frame.getHeight()) / 2;
                frame.setLocation(x, y);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    }
                });
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }
}
