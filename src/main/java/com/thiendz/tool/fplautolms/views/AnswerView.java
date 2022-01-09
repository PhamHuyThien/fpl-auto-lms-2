package com.thiendz.tool.fplautolms.views;


import com.thiendz.tool.fplautolms.models.AnswerBase;
import com.thiendz.tool.fplautolms.models.Quiz;
import com.thiendz.tool.fplautolms.utils.MsgBoxUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnswerView extends javax.swing.JFrame {

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        List<AnswerBase> answerBaseList = new ArrayList<>();
        answerBaseList.add(new AnswerBase(1, "câu hỏi 1", Arrays.asList("a", "b"), Collections.singletonList(0)));
        answerBaseList.add(new AnswerBase(2, "câu hỏi 2", Arrays.asList("a", "b"), Collections.singletonList(0)));
        answerBaseList.add(new AnswerBase(3, "câu hỏi 3", Arrays.asList("a", "b"), Collections.singletonList(0)));
        answerBaseList.add(new AnswerBase(4, "câu hỏi 4", Arrays.asList("a", "b"), Collections.singletonList(0)));
        answerBaseList.add(new AnswerBase(5, "câu hỏi 5", Arrays.asList("a", "b"), Collections.singletonList(0)));
        quiz.setAnswerBaseList(answerBaseList);
        AnswerView answerView = new AnswerView();
        answerView.setQuiz(quiz);
        answerView.updateComponent();
        answerView.setVisible(true);
    }

    private DashboardView dashboardView;
    private Quiz quiz;

    public AnswerView() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jPanel8 = new javax.swing.JPanel();
        lbQuestionNum = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbQuestion = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbBestSolution = new javax.swing.JLabel();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        tfPageNum = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lbQuestionNum.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Câu hỏi số ... :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 24))); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Câu hỏi:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 0, 14))); // NOI18N

        lbQuestion.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lbQuestion.setText("<html>Không có dữ liệu...</html>");
        lbQuestion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbQuestion.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lbQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đáp án:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 0, 14))); // NOI18N

        lbBestSolution.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lbBestSolution.setText("<html>Không có dữ liệu</html>");
        lbBestSolution.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbBestSolution, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lbBestSolution, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addContainerGap())
        );

        btnPrev.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btnPrev.setText("Trở lại");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btnNext.setText("Tiếp theo");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        tfPageNum.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        tfPageNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfPageNum.setText("1");
        tfPageNum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfPageNumMouseClicked(evt);
            }
        });
        tfPageNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPageNumKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout lbQuestionNumLayout = new javax.swing.GroupLayout(lbQuestionNum);
        lbQuestionNum.setLayout(lbQuestionNumLayout);
        lbQuestionNumLayout.setHorizontalGroup(
                lbQuestionNumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(lbQuestionNumLayout.createSequentialGroup()
                                .addComponent(btnPrev)
                                .addGap(43, 43, 43)
                                .addComponent(tfPageNum, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(btnNext))
        );

        lbQuestionNumLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[]{btnNext, btnPrev, tfPageNum});

        lbQuestionNumLayout.setVerticalGroup(
                lbQuestionNumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(lbQuestionNumLayout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(lbQuestionNumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNext)
                                        .addComponent(btnPrev)
                                        .addComponent(tfPageNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbQuestionNumLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[]{btnNext, btnPrev, tfPageNum});

        lbQuestionNumLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[]{jPanel5, jPanel7});

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTitle.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("< ... />");

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 10)); // NOI18N
        jLabel2.setText("ThienDepZaii is the best! =))");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel2)))
                                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbQuestionNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbQuestionNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        onclickNext();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        onclickPrev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void tfPageNumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPageNumKeyReleased
        actionPageNum();
    }//GEN-LAST:event_tfPageNumKeyReleased

    private void tfPageNumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfPageNumMouseClicked
        tfPageNum.selectAll();
    }//GEN-LAST:event_tfPageNumMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        setVisible(false);
        dashboardView.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void actionPageNum() {
        int id = getPageText();
        if (id == -1) {
            return;
        }
        if (id < 1 || id > getSizAnswerBase()) {
            return;
        }
        updateComponent(--id);
    }

    private void onclickPrev() {
        int prev = getPageText() - 1;
        if (prev == -2) {
            MsgBoxUtils.alertErr(this, "Câu hỏi cần prev không hợp lệ!");
            return;
        }
        if (prev < 0 || prev >= getSizAnswerBase()) {
            MsgBoxUtils.alertErr("Câu hỏi này không tồn tại!");
            return;
        }
        if (prev <= 0) {
            prev = getSizAnswerBase();
        }
        updateComponent(--prev);
    }

    private void onclickNext() {
        int next = getPageText() - 1;
        if (next == -2) {
            MsgBoxUtils.alertErr(this, "Câu hỏi cần next không hợp lệ!");
            return;
        }
        if (next < 0 || next >= getSizAnswerBase()) {
            MsgBoxUtils.alertErr("Câu hỏi này không tồn tại!");
            return;
        }
        if (next >= getSizAnswerBase() - 1) {
            next = -1;
        }
        updateComponent(++next);
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
//        for (int i = 0; i < quiz.getAnswerBases().length; i++) {
//            System.out.println(quiz.getAnswerBases()[i].toString());
//        }
    }

    public void setDashboardView(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
    }

    public void updateComponent() {
        updateComponent(0);
    }

    private void updateComponent(int id) {
        if (this.quiz == null) {
            return;
        }
        setTitle(this.quiz.getName() + " - ThienDepZaii");
        lbTitle.setText("<" + this.quiz.getName() + "/>");
        if (id > -1 && id < getSizAnswerBase()) {
            setPageText(id + 1);
            updateQuestionNum("Câu hỏi số " + (id + 1) + ":");
            updateQuestion(quiz.getAnswerBaseList().get(id).getQuestion());
            List<Integer> bestSolutionList = quiz.getAnswerBaseList().get(id).getBestSolutionIdList();
            StringBuilder sb = new StringBuilder();
            for (Integer integer : bestSolutionList) {
                sb.append("- ").append(quiz.getAnswerBaseList().get(id).getAnswerTextList().get(integer)).append("<br/>");
            }
            updateAnswer(sb.toString());
        }
    }

    private void updateQuestion(String text) {
        lbQuestion.setText("<html>" + text + "</html>");
    }

    private void updateAnswer(String text) {
        lbBestSolution.setText("<html>" + text + "</html>");
    }

    private void updateQuestionNum(String text) {
        lbQuestionNum.setBorder(javax.swing.BorderFactory.createTitledBorder(null, text, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 24)));
    }

    private int getSizAnswerBase() {
        if (quiz.getAnswerBaseList() == null) {
            return -1;
        }
        return quiz.getAnswerBaseList().size();
    }

    private int getPageText() {
        try {
            return Integer.parseInt(tfPageNum.getText().trim());
        } catch (NumberFormatException ignored) {
        }
        return -1;
    }

    private void setPageText(int id) {
        tfPageNum.setText(id + "");
    }

    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbBestSolution;
    private javax.swing.JLabel lbQuestion;
    private javax.swing.JPanel lbQuestionNum;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField tfPageNum;
}
