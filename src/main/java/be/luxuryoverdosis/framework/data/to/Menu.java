package be.luxuryoverdosis.framework.data.to;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum;
import be.luxuryoverdosis.framework.data.convertor.JaNeeConvertor;

@Entity
@Table(name = "base_menu")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = Menu.SELECT_MENUS_BY_FULL_NAME_AND_USER, query = Menu.Queries.SELECT_MENUS_BY_FULL_NAME_AND_USER),
	@NamedQuery(name = Menu.SELECT_MENUS_DTO_BY_USER, query = Menu.Queries.SELECT_MENUS_DTO_BY_USER),
	@NamedQuery(name = Menu.SELECT_MENUS_HIDDEN_DTO_BY_USER, query = Menu.Queries.SELECT_MENUS_HIDDEN_DTO_BY_USER),
	@NamedQuery(name = Menu.SELECT_MENUS_DISABLED_DTO_BY_USER, query = Menu.Queries.SELECT_MENUS_DISABLED_DTO_BY_USER),
	@NamedQuery(name = Menu.SELECT_MENUS_FORPAY_DTO_BY_USER_AND_PAYED, query = Menu.Queries.SELECT_MENUS_FORPAY_DTO_BY_USER_AND_PAYED),
	@NamedQuery(name = Menu.DELETE_MENUS_BY_USER, query = Menu.Queries.DELETE_MENUS_BY_USER),
	@NamedQuery(name = Menu.COUNT_MENUS_BY_USER, query = Menu.Queries.COUNT_MENUS_BY_USER),
	@NamedQuery(name = Menu.COUNT_MENUS_BY_FULL_NAME_AND_USER, query = Menu.Queries.COUNT_MENUS_BY_FULL_NAME_AND_USER)
})
@Proxy(lazy = false)
public class Menu extends BaseTO {
	public static final String SELECT_MENUS_BY_FULL_NAME_AND_USER = "selectMenusByFullNameAndUser";
	public static final String SELECT_MENUS_DTO_BY_USER = "selectMenusDtoByUser";
	public static final String SELECT_MENUS_HIDDEN_DTO_BY_USER = "selectMenusHiddenDtoByUser";
	public static final String SELECT_MENUS_DISABLED_DTO_BY_USER = "selectMenusDisabledDtoByUser";
	public static final String SELECT_MENUS_FORPAY_DTO_BY_USER_AND_PAYED = "selectMenusForPayDtoByUserAndPayed";
	public static final String DELETE_MENUS_BY_USER = "deleteMenusByUser";
	public static final String COUNT_MENUS_BY_USER = "countMenusByUser";
	public static final String COUNT_MENUS_BY_FULL_NAME_AND_USER = "counMenustByFullNameAndUser";
	
	@Column(name = "FullName")
	private String fullName;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name = "FullLevel")
	private String fullLevel;
	
	@Column(name = "Level")
	private int level;
	
	@Column(name = "Hidden")
	@Convert(converter = JaNeeConvertor.class)
	private JaNeeEnum hidden;
	
	@Column(name = "Disabled")
	@Convert(converter = JaNeeConvertor.class)
	private JaNeeEnum disabled;
	
	@Column(name = "ForPay")
	@Convert(converter = JaNeeConvertor.class)
	private JaNeeEnum forPay;
	
	@Column(name = "Payed")
	@Convert(converter = JaNeeConvertor.class)
	private JaNeeEnum payed;
	
	@ManyToOne
	@JoinColumn(name = "User_Id")
	private User user;
	
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
	public User getUser() {
		return user;
	}
	public void setUser(final User user) {
		this.user = user;
	}
	
	public static final class Queries {
		public static final String SELECT_MENUS_BY_FULL_NAME_AND_USER = "from Menu m "
				+ "where m.fullName = :fullName "
				+ "and m.user.id = :userId";
		
		public static final String SELECT_MENUS_DTO_BY_USER = "select new be.luxuryoverdosis.framework.data.dto.MenuDTO( "
				+ "m.id, "
				+ "m.fullName, "
				+ "m.name, "
				+ "m.title, "
				+ "m.fullLevel, "
				+ "m.level, "
				+ "m.hidden, "
				+ "m.disabled, "
				+ "m.forPay, "
				+ "m.payed, "
				+ "u.id "
				+ ") "
				+ "from Menu m "
				+ "inner join m.user u "
				+ "where u.id = :userId "
				+ "order by m.fullLevel ";
		
		public static final String SELECT_MENUS_HIDDEN_DTO_BY_USER = "select new be.luxuryoverdosis.framework.data.dto.MenuDTO( "
				+ "m.id, "
				+ "m.fullName, "
				+ "m.name, "
				+ "m.title, "
				+ "m.fullLevel, "
				+ "m.level, "
				+ "m.hidden, "
				+ "m.disabled, "
				+ "m.forPay, "
				+ "m.payed, "
				+ "u.id "
				+ ") "
				+ "from Menu m "
				+ "inner join m.user u "
				+ "where u.id = :userId "
				+ "and m.hidden = :hidden "
				+ "order by m.fullLevel desc";
		
		public static final String SELECT_MENUS_DISABLED_DTO_BY_USER = "select new be.luxuryoverdosis.framework.data.dto.MenuDTO( "
				+ "m.id, "
				+ "m.fullName, "
				+ "m.name, "
				+ "m.title, "
				+ "m.fullLevel, "
				+ "m.level, "
				+ "m.hidden, "
				+ "m.disabled, "
				+ "m.forPay, "
				+ "m.payed, "
				+ "u.id "
				+ ") "
				+ "from Menu m "
				+ "inner join m.user u "
				+ "where u.id = :userId "
				+ "and m.disabled = :disabled "
				+ "order by m.fullLevel desc";
		
		public static final String SELECT_MENUS_FORPAY_DTO_BY_USER_AND_PAYED = "select new be.luxuryoverdosis.framework.data.dto.MenuDTO( "
				+ "m.id, "
				+ "m.fullName, "
				+ "m.name, "
				+ "m.title, "
				+ "m.fullLevel, "
				+ "m.level, "
				+ "m.hidden, "
				+ "m.disabled, "
				+ "m.forPay, "
				+ "m.payed, "
				+ "u.id "
				+ ") "
				+ "from Menu m "
				+ "inner join m.user u "
				+ "where u.id = :userId "
				+ "and m.forPay = 'J' "
				+ "and m.payed = :payed "
				+ "order by m.fullLevel desc";
		
		public static final String DELETE_MENUS_BY_USER = "delete from Menu m "
				+ "where m.user.id = :userId";
		
		public static final String COUNT_MENUS_BY_USER = "select count(*) "
				+ "from Menu m "
				+ "where m.user.id = :userId";
		
		public static final String COUNT_MENUS_BY_FULL_NAME_AND_USER = "select count(*) "
				+ "from Menu m "
				+ "where m.fullName = :fullName "
				+ "and m.user.id = :userId";
	}
	
}
