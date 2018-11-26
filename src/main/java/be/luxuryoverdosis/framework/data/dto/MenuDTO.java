package be.luxuryoverdosis.framework.data.dto;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeType;

public class MenuDTO extends BaseDTO {
	private String fullName;
	private String name;
	private String title;
	private String fullLevel;
	private int level;
	private JaNeeType hidden;
	private JaNeeType disabled;
	private JaNeeType forPay;
	private JaNeeType payed;
	private int userId;
	
	public MenuDTO() {
	}
	
	public MenuDTO(int id, String fullName, String name, String title, String fullLevel, int level, JaNeeType hidden, JaNeeType disabled, JaNeeType forPay, JaNeeType payed,int userId) {
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
	public JaNeeType getHidden() {
		return hidden;
	}
	public void setHidden(JaNeeType hidden) {
		this.hidden = hidden;
	}
	public JaNeeType getDisabled() {
		return disabled;
	}
	public void setDisabled(JaNeeType disabled) {
		this.disabled = disabled;
	}
	public JaNeeType getForPay() {
		return forPay;
	}
	public void setForPay(JaNeeType forPay) {
		this.forPay = forPay;
	}
	public JaNeeType getPayed() {
		return payed;
	}
	public void setPayed(JaNeeType payed) {
		this.payed = payed;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
