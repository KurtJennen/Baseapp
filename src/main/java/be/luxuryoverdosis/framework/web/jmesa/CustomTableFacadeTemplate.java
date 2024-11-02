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

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum;
import be.luxuryoverdosis.framework.data.dto.BaseDTO;
import be.luxuryoverdosis.framework.data.to.BaseTO;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public abstract class CustomTableFacadeTemplate extends TableFacadeTemplate {
	private static final int MAX_ROWS = 15;
	
	private boolean clickable;
	private String actionMethod;
	private String javascriptMethod;
	private String nameIds;
	private String caption;
	private ArrayList<?> items;
	private HttpServletRequest request;
	
	protected abstract void setTitles(Row row);

	public CustomTableFacadeTemplate(final TableFacade tableFacade, final ArrayList<?> items, final HttpServletRequest request, final String caption) {
		super(tableFacade);
		this.items = items;
		this.request = request;
		this.caption = caption;
		this.actionMethod = BaseWebConstants.READ;
		this.javascriptMethod = BaseWebConstants.DO_ACTION_DETAIL;
		this.clickable = true;
	}
	public CustomTableFacadeTemplate(final TableFacade tableFacade, final ArrayList<?> items, final HttpServletRequest request, final String caption, final String nameIds) {
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

	public void setClickable(final boolean clickable) {
		this.clickable = clickable;
	}

	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(final String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public String getJavascriptMethod() {
		return javascriptMethod;
	}

	public void setJavascriptMethod(final String javascriptMethod) {
		this.javascriptMethod = javascriptMethod;
	}

	@Override
	protected int getMaxRows() {
		return MAX_ROWS;
	}
	
	@Override
	protected final ExportType[] getExportTypes() {
//		if(items.size() < 10000) {
//			return new ExportType[]{CSV, EXCEL, PDF};
//		} else {
//			return new ExportType[]{CSV, EXCEL};
//		}
		return new ExportType[]{CSV, EXCEL};
	}

	@Override
	protected final String getStateAttr() {
		return "restore";
	}
	
	@Override
	protected final Toolbar createToolbar() {
		return new CustomToolbar();
	}

	@Override
	protected final Collection<?> getItems() {
		return items;
	}
	
	@Override
	protected void modifyTable(final Table table) {
		if (isExporting()) {
			table.setCaption(MessageLocator.getMessage(request, caption));

			Row row = table.getRow();
			
			this.setTitles(row);
		} else {
			HtmlTable htmlTable = (HtmlTable) table;
			
			HtmlRow htmlRow = htmlTable.getRow();
			if (clickable) {
				htmlRow.setOnclick(new RowEvent() {
					public String execute(final Object item, final int rowcount) {
						Object id = ItemUtils.getItemValue(item, "id");
	
						if (nameIds != null) {
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

	public String[] getColumnsInString(final ArrayList<String> columnsList) {
		String[] columns = new String[columnsList.size()];
		int teller = 0;
		
		for (Iterator<String> iterator = columnsList.iterator(); iterator.hasNext();) {
			String column = (String) iterator.next();
			
			columns[teller] = column;
			teller++;
		}
		
		return columns;
	}
	
	public int[] getIds() {
		if (nameIds == null) {
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
	
	public int setTitleRow(final Row row, final int teller, final String title) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		column.setTitle(title);
		return teller + 1;
	}
	
	public int setTitleKeyRow(final Row row, final int teller, final String titleKey) {
		return this.setTitleRow(row, teller, MessageLocator.getMessage(request, titleKey));
	}
	
	public int setTitleRow(final Row row, final int teller, final String title, final DateCellEditor dateCellEditor) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		column.setTitle(title);
		column.getCellRenderer().setCellEditor(dateCellEditor);
		return teller + 1;
	}
	
	public int setTitleKeyRow(final Row row, final int teller, final String titleKey, final DateCellEditor dateCellEditor) {
		return this.setTitleRow(row, teller, MessageLocator.getMessage(request, titleKey), dateCellEditor);
	}
	
	public int setTitleRow(final Row row, final int teller, final String title, final NumberCellEditor numberCellEditor) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		column.setTitle(title);
		if (!isExporting()) {
			HtmlColumn htmlColumn = (HtmlColumn) column;
			htmlColumn.getCellRenderer().setCellEditor(numberCellEditor);
			htmlColumn.getCellRenderer().setStyle("text-align: right");
		}
		return teller + 1;
	}
	
	public int setTitleKeyRow(final Row row, final int teller, final String titleKey, final NumberCellEditor numberCellEditor) {
		return this.setTitleRow(row, teller, MessageLocator.getMessage(request, titleKey), numberCellEditor);
	}
	
	@Deprecated
	public int setTitleRowCheckbox(final Row row, final int teller, final String title) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		column.setTitle(title);
		if (!isExporting()) {
			column.getCellRenderer().setCellEditor(new CellEditor() {
			    public Object getValue(final Object item, final String property, final int rowcount) {
			        Object value = new BasicCellEditor().getValue(item, property, rowcount);
			        
			        HtmlBuilder html = new HtmlBuilder();
			        if (JaNeeEnum.JA.getCode().equals(value)) {
			        	html.input().type("checkbox").checked().disabled().close();
			        } else {
			        	html.input().type("checkbox").disabled().close();
			        }
			        
					return html.toString();
			    }
			});
		}
		return teller + 1;
	}
	
	@Deprecated
	public int setTitleKeyRowCheckbox(final Row row, final int teller, final String titleKey) {
		return this.setTitleRowCheckbox(row, teller, MessageLocator.getMessage(request, titleKey));
	}
	
	public int setTitleRowCheckboxEnum(final Row row, final int teller, final String title) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		column.setTitle(title);
		if (!isExporting()) {
			HtmlColumn htmlColumn = (HtmlColumn) column;
			htmlColumn.getCellRenderer().setCellEditor(new CellEditor() {
				public Object getValue(final Object item, final String property, final int rowcount) {
					JaNeeEnum value = (JaNeeEnum) new BasicCellEditor().getValue(item, property, rowcount);
					
					HtmlBuilder html = new HtmlBuilder();
					if (JaNeeEnum.JA.getCode().equals(value.getCode())) {
						html.input().type("checkbox").checked().disabled().close();
					} else {
						html.input().type("checkbox").disabled().close();
					}
					
					return html.toString();
				}
			});
			htmlColumn.getCellRenderer().setStyle("text-align: center");
		}
		return teller + 1;
	}
	
	public int setTitleKeyRowCheckboxEnum(final Row row, final int teller, final String titleKey) {
		return this.setTitleRowCheckboxEnum(row, teller, MessageLocator.getMessage(request, titleKey));
	}
	
	public int setTitleRowComment(final Row row, final int teller, final String title) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		column.setTitle(title);
		if (!isExporting()) {
			HtmlColumn htmlColumn = (HtmlColumn) column;
			htmlColumn.getCellRenderer().setCellEditor(new CellEditor() {
			    public Object getValue(final Object item, final String property, final int rowcount) {
			        Object value = new BasicCellEditor().getValue(item, property, rowcount);
			        String valueAsString = (String) value;
			        
			        if (valueAsString != null) {
						if (valueAsString.length() > BaseConstants.TIEN) {
							valueAsString = valueAsString.substring(BaseConstants.NUL, BaseConstants.ACHT);
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
		return teller + 1;
	}
	
	public int setTitleKeyRowComment(final Row row, final int teller, final String titleKey) {
		return this.setTitleRowComment(row, teller, MessageLocator.getMessage(request, titleKey));
	}
	
	public int setTitleKeyRowHidden(final Row row, final int teller) {
		Column column = row.getColumn(getColumnProperties()[teller]);
		if (!isExporting()) {
			column.setTitle(StringUtils.EMPTY);
			HtmlColumn htmlColumn = (HtmlColumn) column;
			htmlColumn.getCellRenderer().setStyle("visibility: hidden");
		}
		return teller + 1;
	}
}
