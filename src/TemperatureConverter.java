
public class TemperatureConverter {

   public static double CtoF(double tem){
	   return rounding(tem*1.8+32);
   }
   public static double FtoC(double tem){
	   return rounding((tem-32)/1.8);
   }
   private static double rounding(double num){   
	   
	   return Math.round(num*10)/10.0;
   }
}
