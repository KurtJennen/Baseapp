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
	public void setAction(final String action) {
		this.action = action;
	}
	public String getNameIds() {
		return nameIds;
	}
	public void setNameIds(final String nameIds) {
		this.nameIds = nameIds;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(final boolean visible) {
		this.visible = visible;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(final String image) {
		this.image = image;
	}
	
}
