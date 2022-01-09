package com.thiendz.tool.fplautolms.views;

import com.thiendz.tool.fplautolms.controllers.*;
import com.thiendz.tool.fplautolms.models.User;
import com.thiendz.tool.fplautolms.selenium.interf.LmsDriver;
import com.thiendz.tool.fplautolms.utils.consts.Messages;
import lombok.Data;

import java.awt.*;

@Data
public class DashboardView extends javax.swing.JFrame {

    LmsDriver lmsDriver;
    User user;
    AnswerView answerView;

    public static void start() {
        new DashboardView();
    }

    public DashboardView() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        lbSlogan = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tfCookie = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        cbbServer = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnSolution = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lbRole = new javax.swing.JLabel();
        lbGender = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbHello = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        tfRefIdCourse = new javax.swing.JTextField();
        btnGetQuiz = new javax.swing.JButton();
        cbbQuiz = new javax.swing.JComboBox<>();
        btnView = new javax.swing.JButton();
        cbAutoStartQuiz = new javax.swing.JCheckBox();
        lbProcess = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbInfo = new javax.swing.JLabel();
        btnContact = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(Messages.APP_NAME + " V" + Messages.APP_VER + " - " + Messages.APP_SLOGAN);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing();
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTitle.setFont(new java.awt.Font("Consolas", Font.BOLD, 36)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(0, 204, 204));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("FPL@utoLMS");

        lbSlogan.setFont(new java.awt.Font("Consolas", Font.BOLD, 14)); // NOI18N
        lbSlogan.setForeground(new java.awt.Color(0, 51, 255));
        lbSlogan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSlogan.setText("Version v" + Messages.APP_VER + " - " + Messages.APP_SLOGAN);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbSlogan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                                        .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(lbSlogan)
                                .addContainerGap(19, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lbTitle)
                                        .addContainerGap(34, Short.MAX_VALUE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đăng nhập bằng cookie:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", Font.PLAIN, 11))); // NOI18N

        tfCookie.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N

        btnLogin.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        btnLogin.setText("Đăng nhập");
        btnLogin.addActionListener(evt -> btnLoginActionPerformed());

        cbbServer.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        cbbServer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"HaNoi", "HoChiMinh", "DaNang", "CanTho", "TayNguyen"}));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tfCookie, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbServer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfCookie, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbServer, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, btnLogin, tfCookie);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giải bài:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", Font.PLAIN, 11))); // NOI18N

        btnSolution.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        btnSolution.setText("Tự động giải bài tập (VIP)");
        btnSolution.setEnabled(false);
        btnSolution.addActionListener(this::btnSolutionActionPerformed);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbRole.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        lbRole.setText("Quyền: ..............");

        lbGender.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        lbGender.setText("Giới tính: ..........");

        lbEmail.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        lbEmail.setText("Email: ..............");

        lbHello.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        lbHello.setText("Chào:................");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbHello, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, lbEmail, lbGender, lbHello, lbRole);

        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbHello, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tfRefIdCourse.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        tfRefIdCourse.setEnabled(false);

        btnGetQuiz.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        btnGetQuiz.setText("Lấy Quiz");
        btnGetQuiz.setEnabled(false);
        btnGetQuiz.addActionListener(this::btnGetQuizActionPerformed);

        cbbQuiz.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        cbbQuiz.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Quiz....", " "}));
        cbbQuiz.setEnabled(false);

        btnView.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        btnView.setText("Xem đáp án");
        btnView.setEnabled(false);
        btnView.addActionListener(this::btnViewActionPerformed);

        cbAutoStartQuiz.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        cbAutoStartQuiz.setText("Tự động khởi động quiz");
        cbAutoStartQuiz.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbbQuiz, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(tfRefIdCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnGetQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(cbAutoStartQuiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfRefIdCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnGetQuiz))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbAutoStartQuiz)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, btnView, cbbQuiz);

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, btnGetQuiz, tfRefIdCourse);

        lbProcess.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        lbProcess.setForeground(new java.awt.Color(0, 153, 0));
        lbProcess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbProcess.setText(Messages.THIEN_DZ_IS_THE_BEST);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbProcess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSolution)
                                .addGap(113, 113, 113))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(lbProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSolution, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Liên hệ:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", Font.PLAIN, 11))); // NOI18N

        lbInfo.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        lbInfo.setForeground(new java.awt.Color(255, 0, 0));
        lbInfo.setText(Messages.APP_NAME + " - Code By " + Messages.APP_AUTHOR + " - " + Messages.APP_NICKNAME);

        btnContact.setFont(new java.awt.Font("Consolas", Font.PLAIN, 12)); // NOI18N
        btnContact.setText("Liên hệ tôi");
        btnContact.addActionListener(this::btnContactActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbInfo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnContact)
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbInfo)
                                        .addComponent(btnContact))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }

    private void btnContactActionPerformed(java.awt.event.ActionEvent evt) {
        ContactController.start();
    }

    private void btnLoginActionPerformed() {
        LoginController.start(this);
    }

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {
        BestSolutionController.start(this);
    }

    private void btnGetQuizActionPerformed(java.awt.event.ActionEvent evt) {
        QuizController.start(this);
    }

    private void btnSolutionActionPerformed(java.awt.event.ActionEvent evt) {
        SolutionController.start(this);
    }

    private void formWindowClosing() {
        windowOnClosing();
    }

    private void windowOnClosing() {
        if (lmsDriver != null && lmsDriver.getWebDriver() != null) {
            lmsDriver.getWebDriver().close();
        }
        if (answerView != null) {
            answerView.dispose();
        }
        System.exit(0);
    }

    public void setEnabledAll(boolean enb) {
        tfCookie.setEnabled(enb);
        cbbServer.setEnabled(enb);
        btnLogin.setEnabled(enb);
        tfRefIdCourse.setEnabled(enb);
        btnGetQuiz.setEnabled(enb);
        cbbQuiz.setEnabled(enb);
        cbAutoStartQuiz.setEnabled(enb);
        btnView.setEnabled(enb);
        btnSolution.setEnabled(enb);
    }

    public void setProcess(String text) {
        lbProcess.setText(text);
    }

    private javax.swing.JToggleButton btnContact;
    private javax.swing.JButton btnGetQuiz;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSolution;
    private javax.swing.JButton btnView;
    private javax.swing.JCheckBox cbAutoStartQuiz;
    private javax.swing.JComboBox<String> cbbQuiz;
    private javax.swing.JComboBox<String> cbbServer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbGender;
    private javax.swing.JLabel lbHello;
    private javax.swing.JLabel lbInfo;
    private javax.swing.JLabel lbProcess;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbSlogan;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField tfCookie;
    private javax.swing.JTextField tfRefIdCourse;
}
