
package com.thiendz.tool.fplautolms.utils;

import javax.swing.*;
import java.awt.*;

public class MsgBoxUtils {

    public static void alert(Component c, String content) {
        JOptionPane.showMessageDialog(c, content);
    }

    public static void alert(String content) {
        JOptionPane.showMessageDialog(null, content);
    }

    public static void alertInf(String s) {
        JOptionPane.showMessageDialog(null, s, "AutoLMS Info!!!", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void alertWar(String s) {
        JOptionPane.showMessageDialog(null, s, "AutoLMS Warning!!!", JOptionPane.WARNING_MESSAGE);
    }

    public static void alertErr(String s) {
        JOptionPane.showMessageDialog(null, s, "AutoLMS Error!!!", JOptionPane.ERROR_MESSAGE);
    }

    public static void alertInf(Component c, String s) {
        JOptionPane.showMessageDialog(c, s, "AutoLMS Info!!!", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void alertWar(Component c, String s) {
        JOptionPane.showMessageDialog(c, s, "AutoLMS Warning!!!", JOptionPane.WARNING_MESSAGE);
    }

    public static void alertErr(Component c, String s) {
        JOptionPane.showMessageDialog(c, s, "AutoLMS Error!!!", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean confirmWar(Component c, String s) {
        return JOptionPane.showConfirmDialog(c, s, "AutoLMS Warning!!!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == 0;
    }
}
