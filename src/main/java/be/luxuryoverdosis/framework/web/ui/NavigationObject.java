package be.luxuryoverdosis.framework.web.ui;

public class NavigationObject {
	private String title;
	private String action;
	private String nameIds;
	private boolean visible;
	private String image;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getNameIds() {
		return nameIds;
	}
	public void setNameIds(String nameIds) {
		this.nameIds = nameIds;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
