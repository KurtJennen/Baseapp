package be.luxuryoverdosis.framework.data.dto;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum;

public class MenuDTO extends BaseDTO {
	private String fullName;
	private String name;
	private String title;
	private String fullLevel;
	private int level;
	private JaNeeEnum hidden;
	private JaNeeEnum disabled;
	private JaNeeEnum forPay;
	private JaNeeEnum payed;
	private int userId;
	
	public MenuDTO() {
	}
	
	public MenuDTO(final int id, final String fullName, final String name, final String title, final String fullLevel, final int level, final JaNeeEnum hidden, final JaNeeEnum disabled, final JaNeeEnum forPay, final JaNeeEnum payed, final int userId) {
		super();
		setId(id);
		this.fullLevel = fullLevel;
		this.fullName = fullName;
		this.name = name;
		this.title = title;
		this.level = level;
		this.hidden = hidden;
		this.disabled = disabled;
		this.forPay = forPay;
		this.payed = payed;
		this.userId = userId;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	public String getFullLevel() {
		return fullLevel;
	}
	public void setFullLevel(final String fullLevel) {
		this.fullLevel = fullLevel;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(final int level) {
		this.level = level;
	}
	public JaNeeEnum getHidden() {
		return hidden;
	}
	public void setHidden(final JaNeeEnum hidden) {
		this.hidden = hidden;
	}
	public JaNeeEnum getDisabled() {
		return disabled;
	}
	public void setDisabled(final JaNeeEnum disabled) {
		this.disabled = disabled;
	}
	public JaNeeEnum getForPay() {
		return forPay;
	}
	public void setForPay(final JaNeeEnum forPay) {
		this.forPay = forPay;
	}
	public JaNeeEnum getPayed() {
		return payed;
	}
	public void setPayed(final JaNeeEnum payed) {
		this.payed = payed;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(final int userId) {
		this.userId = userId;
	}
	
}
