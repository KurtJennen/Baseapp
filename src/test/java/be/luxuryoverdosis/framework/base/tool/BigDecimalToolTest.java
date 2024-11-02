package be.luxuryoverdosis.framework.base.tool;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class BigDecimalToolTest {
	public static final BigDecimal AMOUNT1 = new BigDecimal(12.35);
	public static final BigDecimal AMOUNT2 = new BigDecimal(123.45);
	public static final BigDecimal AMOUNT3 = new BigDecimal(123.4567);
	public static final BigDecimal AMOUNT4 = new BigDecimal(123.4568);
	public static final BigDecimal AMOUNT5 = new BigDecimal(2020.00);
	
	@Test
	public void testToStringBigDecimal() {
		String result = BigDecimalTool.toString(AMOUNT2);
		
		assertThat("123,45").isEqualTo(result);
		//assertEquals("123,45", result);
	}
	
	@Test
	public void testToStringBigDecimalNull() {
		BigDecimal decimal = null;
		
		String result = BigDecimalTool.toString(decimal);
		
		assertThat("").isEqualTo(result);
		//assertEquals("", result);
	}
	
	@Test
	public void testToStringBigDecimalWithFractionAndNoGrouping() {
		String result = BigDecimalTool.toString(AMOUNT5, 0, false);
		
		assertThat("2020").isEqualTo(result);
	}
	
	@Test
	public void testToStringDouble() {
		String result = BigDecimalTool.toStringDouble(AMOUNT3);
		
		assertThat("123,4567").isEqualTo(result);
	}
	
	@Test
	public void testToBigDecimal() {
		BigDecimal decimal = AMOUNT2.setScale(BigDecimalTool.FRACTION, RoundingMode.HALF_UP);
		
		BigDecimal result = BigDecimalTool.toBigDecimal("123,45");
		
		assertThat(decimal).isEqualByComparingTo(result);
	}
	
	@Test
	public void testToBigDecimalNull() {
		String string = null;
		
		BigDecimal result = BigDecimalTool.toBigDecimal(string);
		
		assertThat(BigDecimal.ZERO).isEqualByComparingTo(result);
	}
	
	@Test
	public void testToBigDecimalString() {
		String string = "Aa;";
		
		BigDecimal result = BigDecimalTool.toBigDecimal(string);
		
		assertThat(BigDecimal.ZERO).isEqualByComparingTo(result);
	}
	
	@Test
	public void testToBigDecimalLongString() {
		BigDecimal decimal = AMOUNT1.setScale(BigDecimalTool.FRACTION_DOUBLE, RoundingMode.HALF_UP);
		
		BigDecimal result = BigDecimalTool.toBigDecimal("12,3456");
		
		assertThat(decimal).isEqualByComparingTo(result);
	}
	
	@Test
	public void testToBigDecimalDouble() {
		BigDecimal decimal = AMOUNT3.setScale(BigDecimalTool.FRACTION_DOUBLE, RoundingMode.HALF_UP);
		
		BigDecimal result = BigDecimalTool.toBigDecimalDouble("123,4567");
		
		assertThat(decimal).isEqualByComparingTo(result);
	}
	
	@Test
	public void testToBigDecimalLongDouble() {
		BigDecimal decimal = AMOUNT4.setScale(BigDecimalTool.FRACTION_DOUBLE, RoundingMode.HALF_UP);
		
		BigDecimal result = BigDecimalTool.toBigDecimalDouble("123,456789");
		
		assertThat(decimal).isEqualByComparingTo(result);
	}
}
