/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Classes.DaftarMenu;
import Classes.Kuah;
import Classes.Minuman;
import Classes.Ramen;
import Classes.Toping;
import java.util.Scanner;

/**
 *
 * @author lenovo
 */
public class AplikasiKasirMain {
    public DaftarMenu daftarMenu;
    //tambahkan
    public static double PAJAK_PPN = 0.10;
    public static double BIAYA_SERVICE = 0.05;
    //selesai menambahkan
    public static void main (String[] args) { 
    //init
    Scanner input = new Scanner(System.in);
    //tambahkan 
    String no_transaksi, nama_pemesan, no_meja = "";
    String transaksi = "", pesan_lagi = "", keterangan = "", makan_ditempat = "";
    int jumlah_pesanan, no_menu;
    //selesai
    AplikasiKasirMain app = new AplikasiKasirMain();
     app.generateDaftarMenu();
     
     //transaksi mulai
        System.out.println("================TRANSAKSI================");
        
        //ambil data transaksi
        System.out.println("");
    }
    public void generateDaftarMenu() { 
     daftarMenu = new DaftarMenu();

        daftarMenu.tambahMenu(new Ramen("Ramen Seafood", 25000));
        daftarMenu.tambahMenu(new Ramen("Ramen Original", 18000));
        daftarMenu.tambahMenu(new Ramen("Ramen Vegetarian", 22000));
        daftarMenu.tambahMenu(new Ramen("Ramen Karnivor", 28000));

        daftarMenu.tambahMenu(new Kuah("Kuah Orisinil"));
        daftarMenu.tambahMenu(new Kuah("Kuah Internasional"));
        daftarMenu.tambahMenu(new Kuah("Kuah Spicy Lada"));
        daftarMenu.tambahMenu(new Kuah("Kuah Soto Padang"));

        daftarMenu.tambahMenu(new Toping("Crab Stick Bakar", 6000));
        daftarMenu.tambahMenu(new Toping("Chicken Katsu", 8000));
        daftarMenu.tambahMenu(new Toping("Gyoza Goreng", 4000));
        daftarMenu.tambahMenu(new Toping("Bakso Goreng", 7000));
        daftarMenu.tambahMenu(new Toping("Enoki Goreng", 5000));

        daftarMenu.tambahMenu(new Minuman("Jus Alpukat SPC", 10000));
        daftarMenu.tambahMenu(new Minuman("Jus Stroberi", 11000));
        daftarMenu.tambahMenu(new Minuman("Capucino Coffee", 15000));
        daftarMenu.tambahMenu(new Minuman("Vietnam Dripp", 14000));

        daftarMenu.tampilDaftarMenu();
    }
}
