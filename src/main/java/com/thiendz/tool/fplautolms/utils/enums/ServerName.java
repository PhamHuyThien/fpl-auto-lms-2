
package com.thiendz.tool.fplautolms.utils.enums;


public enum ServerName {
    HaNoi, HoChiMinh, CanTho, TayNguyen, DaNang;

    @Override
    public String toString() {
        switch (this) {
            case HaNoi:
                return "hn";
            case HoChiMinh:
                return "hcm";
            case CanTho:
            case TayNguyen:
            case DaNang:
                return "dn";
        }
        return null;
    }

    public static ServerName toServerName(String name) {
        switch (name) {
            case "HaNoi":
                return ServerName.HaNoi;
            case "HoChiMinh":
                return ServerName.HoChiMinh;
            case "DaNang":
                return ServerName.DaNang;
            case "CanTho":
                return ServerName.CanTho;
            case "TayNguyen":
                return ServerName.TayNguyen;
        }
        return null;
    }
}
