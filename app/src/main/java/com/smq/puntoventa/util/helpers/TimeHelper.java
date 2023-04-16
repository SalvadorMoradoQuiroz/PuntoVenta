package com.smq.puntoventa.util.helpers;

import android.content.ContentResolver;
import android.provider.Settings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHelper {
    /**
     * @return retorna la fecha en formato año-mes-dia hrs:min:seg formato 24 hs
     */
    public static String obtenerFecha() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    /**
     * @return retorna la hora actual en formato de 12 hrs
     */
    public static String obtenerHora12AM() {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm  aa");
        return df.format(new Date()).toUpperCase();
    }

    /**
     * @return retorna la fecha actual en formato de 24 hrs
     */
    public static String obtenerHora24() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(new Date()).toUpperCase();
    }

    /**
     * @return retorna la fecha actual en formato dia-mes-año
     */
    public static String obtenerFechaActual() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    public boolean timeAutomatically(ContentResolver contentResolver) {
        boolean status = false;
        try {
            if (Settings.Global.getInt(contentResolver, Settings.Global.AUTO_TIME) == 1) {
                // Enabled
                status = true;
            } else {
                // Disabed
                status = false;
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;

    }
}
