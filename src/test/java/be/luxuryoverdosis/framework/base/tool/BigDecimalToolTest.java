package be.luxuryoverdosis.framework.base.tool;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class BigDecimalToolTest {
	@Test
	public void testToStringBigDecimal() {
		BigDecimal decimal = new BigDecimal(123.45);
		
		String result = BigDecimalTool.toString(decimal);
		
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
		BigDecimal decimal = new BigDecimal(2020.00);
		
		String result = BigDecimalTool.toString(decimal, 0, false);
		
		assertThat("2020").isEqualTo(result);
	}
	
	@Test
	public void testToStringDouble() {
		BigDecimal decimal = new BigDecimal(123.4567);
		
		String result = BigDecimalTool.toStringDouble(decimal);
		
		assertThat("123,4567").isEqualTo(result);
	}
	
	@Test
	public void testToBigDecimal() {
		String string = "123,45";
		BigDecimal decimal = new BigDecimal(123.45).setScale(BigDecimalTool.FRACTION, RoundingMode.HALF_UP);
		
		BigDecimal result = BigDecimalTool.toBigDecimal(string);
		
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
		String string = "12,3456";
		BigDecimal decimal = new BigDecimal(12.35).setScale(BigDecimalTool.FRACTION_DOUBLE, RoundingMode.HALF_UP);
		
		BigDecimal result = BigDecimalTool.toBigDecimal(string);
		
		assertThat(decimal).isEqualByComparingTo(result);
	}
	
	@Test
	public void testToBigDecimalDouble() {
		String string = "123,4567";
		BigDecimal decimal = new BigDecimal(123.4567).setScale(BigDecimalTool.FRACTION_DOUBLE, RoundingMode.HALF_UP);
		
		BigDecimal result = BigDecimalTool.toBigDecimalDouble(string);
		
		assertThat(decimal).isEqualByComparingTo(result);
	}
	
	@Test
	public void testToBigDecimalLongDouble() {
		String string = "123,456789";
		BigDecimal decimal = new BigDecimal(123.4568).setScale(BigDecimalTool.FRACTION_DOUBLE, RoundingMode.HALF_UP);
		
		BigDecimal result = BigDecimalTool.toBigDecimalDouble(string);
		
		assertThat(decimal).isEqualByComparingTo(result);
	}
}
