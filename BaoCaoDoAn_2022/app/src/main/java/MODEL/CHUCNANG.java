package MODEL;

import java.io.Serializable;

public class CHUCNANG implements Serializable {
    String tenchucnang;
    int icon;

    public CHUCNANG(int icon, String tenchucnang) {
        this.icon = icon;
        this.tenchucnang = tenchucnang;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTenchucnang() {
        return tenchucnang;
    }

    public void setTenchucnang(String tenchucnang) {
        this.tenchucnang = tenchucnang;
    }
}
