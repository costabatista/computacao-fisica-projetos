//Programa : Medidor de corrente com Arduino e SCT-013 100A
//Autor : FILIPEFLOP
 
//Baseado no programa exemplo da biblioteca EmonLib
 
//Carrega as bibliotecas

#include "EmonLib.h"
#include <SPI.h>

 
EnergyMonitor emon1;

 
//Pino do sensor SCT
int pino_sct = A1;
 
void setup()
{
  Serial.begin(9600);
  emon1.current(pino_sct, 0.12);
}
 
void loop()
{
  //Calcula a corrente
  float Irms = emon1.calcIrms(1480);

  if(Irms <= 0.01) {
    Irms = 0.0;
  }
 
  Serial.print(Irms);
  Serial.print("\n");
    
  
}
