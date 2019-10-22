package be.luxuryoverdosis.framework.web.jmesa;

import static org.jmesa.limit.ExportType.CSV;
import static org.jmesa.limit.ExportType.EXCEL;
//import static org.jmesa.limit.ExportType.PDF;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeTemplate;
import org.jmesa.limit.ExportType;
import org.jmesa.util.ItemUtils;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.BasicCellEditor;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.DateCellEditor;
import org.jmesa.view.editor.NumberCellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.jmesa.view.html.event.RowEvent;
import org.jmesa.view.html.toolbar.Toolbar;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeType;
import be.luxuryoverdosis.framework.data.dto.BaseDTO;
import be.luxuryoverdosis.framework.data.to.BaseTO;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

@Deprecated
public abstract class CustomTableFacadeTemplate extends TableFacadeTemplate {
	private final int MAX_ROWS = 15;
	
	private boolean clickable;
	private String actionMethod;
	private String javascriptMethod;
	private String nameIds;
	private String caption;
	private ArrayList<?> items;
	protected HttpServletRequest request;
	
	protected abstract void setTitles(Row row);

	public CustomTableFacadeTemplate(TableFacade tableFacade, ArrayList<?> items, HttpServletRequest request, String caption) {
		super(tableFacade);
		this.items = items;
		this.request = request;
		this.caption = caption;
		this.actionMethod = BaseWebConstants.READ;
		this.javascriptMethod = BaseWebConstants.DO_ACTION_DETAIL;
		this.clickable = true;
	}
	public CustomTableFacadeTemplate(TableFacade tableFacade, ArrayList<?> items, HttpServletRequest request, String caption, String nameIds) {
		super(tableFacade);
		this.items = items;
		this.request = request;
		this.caption = caption;
		this.nameIds = nameIds;
		this.actionMethod = BaseWebConstants.READ;
		this.javascriptMethod = BaseWebConstants.DO_ACTION_DETAIL;
		this.clickable = true;
	}

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}

	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public String getJavascriptMethod() {
		return javascriptMethod;
	}

	public void setJavascriptMethod(String javascriptMethod) {
		this.javascriptMethod = javascriptMethod;
	}

	@Override
	protected int getMaxRows() {
		return MAX_ROWS;
	}
	
	@Override
	final protected ExportType[] getExportTypes() {
//		if(items.size() < 10000) {
//			return new ExportType[]{CSV, EXCEL, PDF};
//		} else {
//			return new ExportType[]{CSV, EXCEL};
//		}
		return new ExportType[]{CSV, EXCEL};
	}

	@Override
	final protected String getStateAttr() {
		return "restore";
	}
	
	@Override
	final protected Toolbar createToolbar() {
		return new CustomToolbar();
	}

	@Override
	final protected Collection<?> getItems() {
		return items;
	}
	
	@Override
	protected void modifyTable(Table table) {
		if (isExporting()) {
			table.setCaption(MessageLocator.getMessage(request, caption));

			Row row = table.getRow();
			
			this.setTitles(row);
		} else {
			HtmlTable htmlTable = (HtmlTable)table;
			
			HtmlRow htmlRow = htmlTable.getRow();
			if(clickable) {
				htmlRow.setOnclick(new RowEvent() {
					public String execute(Object item, int rowcount) {
						Object id = ItemUtils.getItemValue(item, "id");
	
						if(nameIds != null) {
							return "javascript:" + javascriptMethod + "('" + actionMethod +  "&id=" + id + "&nameIds=" + nameIds + "');";
						} else {
							return "javascript:" + javascriptMethod + "('" + actionMethod +  "&id=" + id + "');";
						}
					}
				});
			}
			
			this.setTitles(htmlRow);
		}
	}
	
	@Override
	public String render() {
		this.getIds();
		return super.render();
	}

	public String[] getColumnsInString(ArrayList<String> columnsList) {
		String[] columns = new String[columnsList.size()];
		int teller = 0;
		
		for (Iterator<String> iterator = columnsList.iterator(); iterator.hasNext();) {
			String column = (String) iterator.next();
			
			columns[teller] = column;
			teller++;
		}
		
		return columns;
	}
	
	public int[] getIds(){
		if(nameIds == null) {
			return null;
		}
		
		int[] ids = new int[items.size()];
		int teller = 0;
		
		for (Iterator<?> iterator = items.iterator(); iterator.hasNext();) {
			Object record = iterator.next();
			if (record instanceof BaseTO) {
				BaseTO baseTO = (BaseTO) record;
				ids[teller] = baseTO.getId();
			}
			if (record instanceof BaseDTO) {
				BaseDTO baseDTO = (BaseDTO) record;
				ids[teller] = baseDTO.getId();
			}
			
			teller++;
		}
	
		SessionManager.putInSession(request, nameIds, ids);
		
		return ids;
	}
	
	public int setTitleRow(Row row, int teller, String title) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		column.setTitle(title);
		return ++teller;
	}
	
	public int setTitleKeyRow(Row row, int teller, String titleKey) {
		return this.setTitleRow(row, teller, MessageLocator.getMessage(request, titleKey));
	}
	
	public int setTitleRow(Row row, int teller, String title, DateCellEditor dateCellEditor) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		column.setTitle(title);
		column.getCellRenderer().setCellEditor(dateCellEditor);
		return ++teller;
	}
	
	public int setTitleKeyRow(Row row, int teller, String titleKey, DateCellEditor dateCellEditor) {
		return this.setTitleRow(row, teller, MessageLocator.getMessage(request, titleKey), dateCellEditor);
	}
	
	public int setTitleRow(Row row, int teller, String title, NumberCellEditor numberCellEditor) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		column.setTitle(title);
		if(!isExporting()) {
			HtmlColumn htmlColumn = (HtmlColumn) column;
			htmlColumn.getCellRenderer().setCellEditor(numberCellEditor);
			htmlColumn.getCellRenderer().setStyle("text-align: right");
		}
		return ++teller;
	}
	
	public int setTitleKeyRow(Row row, int teller, String titleKey, NumberCellEditor numberCellEditor) {
		return this.setTitleRow(row, teller, MessageLocator.getMessage(request, titleKey), numberCellEditor);
	}
	
	public int setTitleRowCheckbox(Row row, int teller, String title) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		column.setTitle(title);
		if(!isExporting()) {
			column.getCellRenderer().setCellEditor(new CellEditor() {
			    public Object getValue(Object item, String property, int rowcount) {
			        Object value = new BasicCellEditor().getValue(item, property, rowcount);
			        
			        HtmlBuilder html = new HtmlBuilder();
			        if(JaNeeType.JA.getCode().equals(value)) {
			        	html.input().type("checkbox").checked().disabled().close();
			        } else {
			        	html.input().type("checkbox").disabled().close();
			        }
			        
					return html.toString();
			    }
			});
		}
		return ++teller;
	}
	
	public int setTitleKeyRowCheckbox(Row row, int teller, String titleKey) {
		return this.setTitleRowCheckbox(row, teller, MessageLocator.getMessage(request, titleKey));
	}
	
	public int setTitleRowComment(Row row, int teller, String title) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		column.setTitle(title);
		if(!isExporting()) {
			HtmlColumn htmlColumn = (HtmlColumn) column;
			htmlColumn.getCellRenderer().setCellEditor(new CellEditor() {
			    public Object getValue(Object item, String property, int rowcount) {
			        Object value = new BasicCellEditor().getValue(item, property, rowcount);
			        String valueAsString = (String) value;
			        
			        if(valueAsString != null) {
						if(valueAsString.length() > 10) {
							valueAsString = valueAsString.substring(0, 8);
						}
					}
			        
			        HtmlBuilder path = new HtmlBuilder();
			        path.append(request.getContextPath()).append("/images/comment.png");
			        
			        HtmlBuilder html = new HtmlBuilder();
			        html.append(valueAsString).img().src(path.toString()).title((String) value).end();
			        
					return html.toString();
			    }
			});
		}
		return ++teller;
	}
	
	public int setTitleKeyRowComment(Row row, int teller, String titleKey) {
		return this.setTitleRowComment(row, teller, MessageLocator.getMessage(request, titleKey));
	}
	
	public int setTitleKeyRowHidden(Row row, int teller) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		if(!isExporting()) {
			column.setTitle(StringUtils.EMPTY);
			HtmlColumn htmlColumn = (HtmlColumn) column;
			htmlColumn.getCellRenderer().setStyle("visibility: hidden");
		}
		return ++teller;
	}
}
