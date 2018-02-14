package be.luxuryoverdosis.framework.web.jmesa;

import org.jmesa.view.html.toolbar.AbstractToolbar;
import org.jmesa.view.html.toolbar.ToolbarItemType;

public class CustomToolbar extends AbstractToolbar {

	@Override
	public String render() {
		addToolbarItem(ToolbarItemType.FIRST_PAGE_ITEM);
	    addToolbarItem(ToolbarItemType.PREV_PAGE_ITEM);
	    addToolbarItem(ToolbarItemType.NEXT_PAGE_ITEM);
	    addToolbarItem(ToolbarItemType.LAST_PAGE_ITEM);
	    
	    addToolbarItem(ToolbarItemType.SEPARATOR);
	    
	    addExportToolbarItems(getExportTypes());
	    
        addToolbarItem(ToolbarItemType.SEPARATOR);
        
        addToolbarItem(ToolbarItemType.FILTER_ITEM);
        addToolbarItem(ToolbarItemType.CLEAR_ITEM);

		return super.render();
	}

}
