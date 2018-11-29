package be.luxuryoverdosis.framework.data.wrapperdto;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.Document;
import be.luxuryoverdosis.framework.data.to.Role;

public class SearchUserWrapperDTO {
	private ArrayList<Role> roleList;
	private ArrayList<Document> documentList;
	
	public ArrayList<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(ArrayList<Role> roleList) {
		this.roleList = roleList;
	}
	public ArrayList<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(ArrayList<Document> documentList) {
		this.documentList = documentList;
	}
}
