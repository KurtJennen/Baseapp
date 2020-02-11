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
	
	public MenuDTO(int id, String fullName, String name, String title, String fullLevel, int level, JaNeeEnum hidden, JaNeeEnum disabled, JaNeeEnum forPay, JaNeeEnum payed,int userId) {
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
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFullLevel() {
		return fullLevel;
	}
	public void setFullLevel(String fullLevel) {
		this.fullLevel = fullLevel;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public JaNeeEnum getHidden() {
		return hidden;
	}
	public void setHidden(JaNeeEnum hidden) {
		this.hidden = hidden;
	}
	public JaNeeEnum getDisabled() {
		return disabled;
	}
	public void setDisabled(JaNeeEnum disabled) {
		this.disabled = disabled;
	}
	public JaNeeEnum getForPay() {
		return forPay;
	}
	public void setForPay(JaNeeEnum forPay) {
		this.forPay = forPay;
	}
	public JaNeeEnum getPayed() {
		return payed;
	}
	public void setPayed(JaNeeEnum payed) {
		this.payed = payed;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
