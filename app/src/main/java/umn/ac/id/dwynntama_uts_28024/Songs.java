package umn.ac.id.dwynntama_uts_28024;

import android.net.Uri;
import java.io.Serializable;

public class Songs implements Serializable {
    private String judul;
    private String keterangan;
    private String songUri;
    public Songs(String judul, String keterangan,
                 String songUri ){
        this.judul = judul;
        this.keterangan = keterangan;
        this.songUri = songUri;
    }
    public String getJudul() { return this.judul; }
    public String getKeterangan() { return this.keterangan; }
    public String getSongUri() { return this.songUri; }
    public void setJudul(String judul){ this.judul = judul; }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    public void setSongUri(String songUri) {
        this.songUri = songUri;
    }
    public String toString() { return this.getJudul() + " => "
            + this.getKeterangan(); }
}
