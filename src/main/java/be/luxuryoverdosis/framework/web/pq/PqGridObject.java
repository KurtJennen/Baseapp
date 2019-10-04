package be.luxuryoverdosis.framework.web.pq;

import java.util.ArrayList;

public class PqGridObject {
	private String id;
	private String title;
	private String selectedIds;
	private String url;
	private String width = "99%";
	private String height = "500";
	private int freezeCols = 1;
	private int rPP = 15;
	private ArrayList<PqGridColumnObject> pqGridColumnObjects;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getFreezeCols() {
		return freezeCols;
	}
	public void setFreezeCols(int freezeCols) {
		this.freezeCols = freezeCols;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getrPP() {
		return rPP;
	}
	public void setrPP(int rPP) {
		this.rPP = rPP;
	}
	public String getSelectedIds() {
		return selectedIds;
	}
	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}
	public ArrayList<PqGridColumnObject> getPqGridColumnObjects() {
		return pqGridColumnObjects;
	}
	public void setPqGridColumnObjects(ArrayList<PqGridColumnObject> pqGridColumnObjects) {
		this.pqGridColumnObjects = pqGridColumnObjects;
	}
	
}
