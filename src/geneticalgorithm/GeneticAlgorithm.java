/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.ArrayList;
import java.util.Random;



/**
 *
 * @author lenovo
 */
public class GeneticAlgorithm {
        public static Sekil u = new Sekil(1, 1, 1);
        public static Sekil d = new Sekil(2, 1, 2);
        public static Sekil y = new Sekil(2, 1, 2);
        public static Sekil k = new Sekil(1, 1, 1);
    
    public static void main(String[] args) {
        
        
        ArrayList<Sekil> parent_1  = new ArrayList();
        
        parent_1.add(u);
        parent_1.add(y);
        parent_1.add(k);
        parent_1.add(u);
        parent_1.add(d);
        parent_1.add(u);
        
        ArrayList<Sekil> parent_2  = new ArrayList();
        
        parent_2.add(y);
        parent_2.add(u);
        parent_2.add(d);
        parent_2.add(k);
        parent_2.add(u);
        parent_2.add(k);
        
        int alan1;
        int alan2;
        int alan3;
        int en_iyi_sonuc = 100;
        int en_kotu;
        ArrayList<Sekil> dizi = new ArrayList();
        
        for(int i=0;i<50;i++){
            dizi = caprazla(parent_1,parent_2);
            dizi = mutasyon(dizi);
            alan1 = alan_hesapla(parent_1);
            alan2 = alan_hesapla(parent_2);
            alan3 = alan_hesapla(dizi);
            en_kotu = k_alan(alan1, alan2, alan3);
            en_iyi_sonuc = i_alan(alan1, alan2, alan3);
            if(alan1 == en_kotu){
                parent_1 = parent_2;
                parent_2 = dizi;
            }else if(alan2 == en_kotu){
                parent_2 = dizi;
            }else if(alan3 == en_kotu){
                dizi = parent_1;
                parent_1 = parent_2;
                parent_2 = dizi;
            }
            
        }
        System.out.println(en_iyi_sonuc);        
        yazdir(dizi);
        
    }
    
    public static ArrayList<Sekil> mutasyon(ArrayList<Sekil> list){
        ArrayList<Sekil> hepsi = new ArrayList();
        hepsi.add(u);
        hepsi.add(y);
        hepsi.add(k);
        hepsi.add(d);
        
        int rand1 = new Random().nextInt(4);
        int rand2 = new Random().nextInt(6);
        
        Sekil s;
        s = hepsi.get(rand1);
        list.set(rand2, s);
        
        
        list = kontrol_et(list);
        
        return list;   
    }
    
    public static int alan_hesapla(ArrayList<Sekil> list) {
        int kalan_alan = 100;
        int toplam_alan = 0;
        int satır_kalan = 10;
        for(int i=0;i<6;i++){
            if(satır_kalan > 1){
                if(list.get(i) == u) {
                        toplam_alan = toplam_alan + u.alan + 1;
                        satır_kalan = satır_kalan - (u.en + 1);
                }else if( list.get(i) == d ){
                        toplam_alan = toplam_alan + d.alan + 1;
                        satır_kalan = satır_kalan - (d.en + 1);
                }else if( list.get(i) == k ){
                        toplam_alan = toplam_alan + k.alan + 1;
                        satır_kalan = satır_kalan - (k.en + 1);
                }else if( list.get(i) == y ){
                        toplam_alan = toplam_alan + y.alan + 1; 
                        satır_kalan = satır_kalan - (y.en + 1);
                }
            }
             else if(satır_kalan == 1){
               
                if(list.get(i) == u) {
                        toplam_alan = toplam_alan + u.alan;
                        satır_kalan = satır_kalan - (u.en);
                }else if( list.get(i) == d ){
                        toplam_alan = toplam_alan + d.alan;
                        satır_kalan = satır_kalan - (d.en);
                }else if( list.get(i) == k ){
                        toplam_alan = toplam_alan + k.alan;
                        satır_kalan = satır_kalan - (k.en);
                }else if( list.get(i) == y ){
                        toplam_alan = toplam_alan + y.alan; 
                        satır_kalan = satır_kalan - (y.en);
                }
                }
                }
                satır_kalan = 10;
            
        
        kalan_alan = kalan_alan - toplam_alan;
    return kalan_alan;
    }
    
    public static ArrayList<Sekil> caprazla(ArrayList<Sekil> list1,ArrayList<Sekil> list2){
        ArrayList<Sekil> list3 = new ArrayList();
        ArrayList<Sekil> kontrol = new ArrayList();
        kontrol.add(u);
        kontrol.add(y);
        kontrol.add(k);
        kontrol.add(d);
        
        
        for(int i=0;i<3;i++){
            list3.add(list1.get(i));
        }
        
        for(int j=3;j<6;j++){
            list3.add(list2.get(j));
        }
        
        if(list3.containsAll(kontrol)){
            return list3;
        }else{
            list3 = kontrol_et(list3);
        }
        
        return list3;
    }
    
    public static void yazdir(ArrayList<Sekil> list){
        
        for(int i=0;i<6;i++){
            if(list.get(i) == u){
                System.out.print("u ");
            }else if(list.get(i) == d){
                System.out.print("d ");
            }else if(list.get(i) == k){
                System.out.print("k ");
            }else if(list.get(i) == y){
                System.out.print("y ");
            }
        }
    
    }
    
    public static ArrayList<Sekil> kontrol_et(ArrayList<Sekil> list){
        
        for(int j=0;j<6;j++){
        for(int i=0;i<6;i++){
            if(!list.contains(u)){
            list.set(i,u);
            }else if(!list.contains(d)){
            list.set(i,d);
            }else if(!list.contains(y)){
            list.set(i,y);
            }else if(!list.contains(k)){
            list.set(i,k);
            }
        }
        }
        return list;
        
    }
    
    public static int k_alan(int alan1,int alan2, int alan3){
       int en_kotu = 100;
        if(alan1 <= alan2 && alan1 <= alan3){
            en_kotu = alan1;
        }else if(alan2 <= alan1 && alan2 <= alan3){
            en_kotu = alan2;
        }else if(alan3 <= alan1 && alan3 <= alan2){
            en_kotu = alan3;
        }
       
       return en_kotu;
   }
   
    public static int i_alan(int alan1,int alan2, int alan3){
       int en_iyi = 100;
        if(alan1 >= alan2 && alan1 >= alan3){
            en_iyi = alan1;
        }else if(alan2 >= alan1 && alan2 >= alan3){
            en_iyi = alan2;
        }else if(alan3 >= alan1 && alan3 >= alan2){
            en_iyi = alan3;
        }
     return en_iyi;
   
   }
}

