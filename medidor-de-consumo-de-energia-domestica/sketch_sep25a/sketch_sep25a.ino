//Programa : Medidor de corrente com Arduino e SCT-013 100A
//Autor : FILIPEFLOP
 
//Baseado no programa exemplo da biblioteca EmonLib
 
//Carrega as bibliotecas

#include "EmonLib.h"
#include <SPI.h>
#include <LiquidCrystal.h>
 
EnergyMonitor emon1;
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

float energy = 0.0;
int lastUpdate = 0;
int currentIndex = 0;
char* lyrics[] = { 
  "M. Energia R$" 
};
 
 
//Tensao da rede eletrica
int rede = 220;
 
//Pino do sensor SCT
int pino_sct = A1;
 
void setup()
{
  Serial.begin(9600);
  //Pino, calibracao - Cur Const= Ratio/BurdenR. 2000/33 = 60
  emon1.current(pino_sct, 0.12);
  lcd.begin(16, 2);
  
  
}
 
void loop()
{
  //Calcula a corrente
  double Irms = emon1.calcIrms(1480);
  int time = millis();
  //Mostra o valor da corrente no serial monitor e display
  Serial.print("Corrente : ");
  if(Irms <= 0.01) {
    Irms = 0.0;
  }
  Serial.print(Irms); // Irms
  
  Serial.print("A\n");
  
  
  energy = energy + (Irms * 127.0);
  Serial.print("energia: ");
  Serial.print(energy);
  Serial.print("\n");  
  float valor = (energy * 0.0003  ); // caso o kw custe 30 centavos
  Serial.print("R$ ");
  Serial.print(valor);
  Serial.print("\n"); 
  char buff[10];
  dtostrf(valor, 4, 2, buff); 
  char valueToPrint[] = "R$ ";
  strcat(valueToPrint,buff);
  char *lyrics[] = {valueToPrint};
  
  delay(1000);
  if ((time  - lastUpdate) >= 800)
  {
    // Move the cursor back to the first column of the first row.
    lcd.setCursor(0, 0);
    
    // If we are writing "Drink all the..." or "Hack all the..."
    // then clear the screen and print the appropriate line.
    if (currentIndex == 0 || currentIndex == 2)
    {
      lcd.clear();
      lcd.setCursor(0, 0);
      lcd.print(lyrics[0]);
      
    }
    else
    {
      // If we are writing the second line, move the cursor there
      // and print the appropriate line.
      lcd.setCursor(0, 1);
      lcd.print(lyrics[0]);
    }
    
    // Increment or reset the current index.
    if (currentIndex == 3)
    {
      currentIndex = 0;
    }
    else
    {
      currentIndex += 1;
    }
    
    // Update the time that we last refreshed the screen to track
    // when to update it again.
    lastUpdate = time;
  } 
}
