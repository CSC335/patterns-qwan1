import static org.junit.Assert.*;

import org.junit.Test;

public class TemperatureConverterTest {
 
	 @Test
	  public void TestFtoC() {
	  assertEquals( 40.6, TemperatureConverter.FtoC(105.0), 0.0001);  // C equivalent is 40.55555555555556
	 }
	 @Test
	  public void TestCtoF() {
	  assertEquals( 105.1, TemperatureConverter.CtoF(40.6), 0.0001);  // C equivalent is 40.55555555555556
	 }
}
