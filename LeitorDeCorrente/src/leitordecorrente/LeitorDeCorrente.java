/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leitordecorrente;

import leitordecorrente.arduino.LeitorArduinoSerial;

/**
 *
 * @author paulo
 */
public class LeitorDeCorrente {

    public static void main(String[] args) throws Exception {
        LeitorArduinoSerial leitorArduino = new LeitorArduinoSerial();
        leitorArduino.initialize();
        Thread t = new Thread() {
            public void run() {

                while(true) {
                    
                }
                /*try {

                    Thread.sleep(100000);
                    
                } catch (InterruptedException ie) {
                    System.out.println(ie.getMessage());
                }*/
            }
        };
       
        t.start();

        System.out.println("Started");
    }

}
