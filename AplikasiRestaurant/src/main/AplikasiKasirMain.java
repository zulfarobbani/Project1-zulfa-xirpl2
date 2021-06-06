/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Classes.DaftarMenu;
import Classes.Kuah;
import Classes.Menu;
import Classes.Minuman;
import Classes.Pesanan;
import Classes.Ramen;
import Classes.Toping;
import Classes.Transaksi;
import java.util.InputMismatchException;
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
    String no_transaksi, nama_pemesan,tanggal,  no_meja = "";
    String transaksi = "", pesan_lagi = "", keterangan = "", makan_ditempat = "";
    int jumlah_pesanan, no_menu;
    //selesai
    AplikasiKasirMain app = new AplikasiKasirMain();
     app.generateDaftarMenu();
     
     //transaksi mulai
        System.out.println("================TRANSAKSI================");
        
        //ambil data transaksi
        System.out.print("No transaksi : ");
        no_transaksi = input.next();
        System.out.print("pemesan : ");
        nama_pemesan = input.next();
        System.out.print("Tanggal : {dd-mm-yy}");
        tanggal = input.next();
        System.out.print("Makan Ditempat?{Y/N}");
        makan_ditempat = input.next();
        
        if(makan_ditempat.equalsIgnoreCase("Y")){
            System.out.print("Nomor Meja : ");
            no_meja = input.next();
        }
        Transaksi trans = new Transaksi(no_transaksi, nama_pemesan, tanggal, no_meja);
        System.out.println("==========Pesanan==========");
        int no_kuah;
        do{
            //ambil menu berdasarkan nomor urut yg dipilih
            Menu menu_yang_dipilih = app.daftarMenu.pilihMenu();
            
            jumlah_pesanan = (int) app.cekInputNumber("Jumlah : ");
            
            //bbuat pesanan
            Pesanan pesanan = new Pesanan(menu_yang_dipilih, jumlah_pesanan);
            trans.tambahPesanan(pesanan);
            //khusu untuk menu ramen, pesanan kuahnya diinput langsung juga
            if(menu_yang_dipilih.getKategori().equals("Ramen")){
                int jumlah_ramen = jumlah_pesanan;
                do{
                    Menu kuah_yang_dipilih = app.daftarMenu.pilihKuah();
                    
                    System.out.print("Level : (0 - 5) : ");
                    String level = input.next();
                    
                    int jumlah_kuah = 0;
                    do{
                        jumlah_kuah = (int) app.cekInputNumber("jumlah : ");
                        
                        if (jumlah_kuah > jumlah_ramen){
                            System.out.println("(err)Jumlah kuah melebihi ramen yang dipesan!!");                        
                        }else{
                            break;
                        }
                    }while (jumlah_kuah > jumlah_ramen);
                    //set pesanan kuah
                   Pesanan pesanan_kuah = new Pesanan(kuah_yang_dipilih, jumlah_kuah);
                   pesanan_kuah.setKeterangan("level" + level);
                   //tambahkan pesanan kuahg ke transaksi
                   trans.tambahPesanan(pesanan_kuah);
                   
                   jumlah_ramen -= jumlah_kuah;
                }while(jumlah_ramen>0);
              
            }else{
                System.out.print("Keterangan (jika kosong) : ");
                keterangan = input.next();
            }
            
            if(!keterangan.equals("-")){
                pesanan.setKeterangan(keterangan);
            }
            //konfirmasi mau tambah pesanan lagi atau tidak
            System.out.print("Tambah Pesanan lagi? (Y/N)");
            pesan_lagi = input.next();
        }while (pesan_lagi.equalsIgnoreCase("Y"));
        trans.cetakStruk();
        double total_pesanan = trans.hitungTotalPesanan();
        System.out.println("==========================");
        System.out.println("Total : \t\t" +total_pesanan);
        
        trans.setPajak(PAJAK_PPN);
        double ppn = trans.hitungPajak();
        System.out.println("Pajak 10% : \t\t" + ppn);
        
        double biaya_service = 0;
        if(makan_ditempat.equalsIgnoreCase("Y")){
            trans.setBiayaService(BIAYA_SERVICE);
            biaya_service = trans.hitungBiayaService();
            System.out.println("Biaya Service 5% : \t " + biaya_service);
        }
        System.out.println("Total : \t\t" + trans.hitungTotalBayar(ppn, biaya_service));
    
        double kembalian = 0;
        do{
            double uang_bayar = app.cekInputNumber("Uang Bayar : \t\t");
            
            kembalian = trans.hitungKembalian(uang_bayar);
            if(kembalian<0){
                System.out.println("(err) Uang anda kurang");
            }else{
                System.out.println("Kembalian : \t\t"+kembalian);
                break;
            }
        }while(kembalian<0);
        System.out.println("==========TERIMA KASIH==========");
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
     public double cekInputNumber(String label){
        try {
            Scanner get_input = new Scanner(System.in);
            System.out.print(label);
            double nilai = get_input.nextDouble();

            return nilai;
        } catch (InputMismatchException ex){
            System.out.println("[Err] Harap masukkan angka");
            return cekInputNumber(label);
        }
    }
}
