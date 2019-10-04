<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<html:form action="/listRole.do">
	<div class="center">
		<h2><i><fmt:message key="displayList.title" />&nbsp;<fmt:message key="displayRole.title" /></i></h2>
	</div>
	<lo:button image="table_edit.png" method="read" key="button.edit"></lo:button>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<hr />
	<lo:pqGrid selectedIds="selectedIds" url="/listRole.do?method=ajaxAllRole" title="rollen" id="rollen">
	</lo:pqGrid>
	
	<script>
	$(function () {
        var colModel= [
        	{ title: "", dataIndx: "selectedRow", maxWidth: 30, minWidth: 30, align: "center", resizable: false,
                type: 'checkBoxSelection', cls: 'ui-state-default', sortable: false, editable: false,
                cb: { all: true, header: true }
            },
            //{ title: "ID", dataType: "integer", dataIndx: "id", width: 80, editable: false, filter: { type: 'textbox', condition: 'contain', listeners: ['keyup'] } },
            { title: "Rol", dataType: "string", dataIndx: "name", width: 200, filter: { type: 'textbox', condition: 'contain', listeners: ['keyup'] }}
        ];
        var dataModel = {
            location: "remote",
            dataType: "json",
            method: "GET",
            url: "http://localhost:8080/Baseapp/listRole.do?method=ajaxAllRole",
            getData: function (dataJSON) {
                var data = dataJSON;
                return { data: data };
            }
        };

        var $grid = $("#grid_jsonp").pqGrid({
        	width: "99%",
            height: 500,
            title: "Rollen",
            freezeCols: 1,
            scrollModel: {horizontal: true, autoFit: false},
            dataModel: dataModel,
            colModel: colModel,
            pageModel: { type: "local", rPP: 15, strRpp: "", rPPOptions: "" },
            sorting: "local",
            filterModel: { on: true, mode: "AND", header: true, type: "local" },
            check: function (evt, ui) {
            	checkPq(ui, "selectedIds");
            },
            unCheck: function (evt, ui) {
            	unCheckPq(ui);
            },
            cellDblClick: function (evt, ui) {
            	cellDblClickPq(ui, "selectedIds");
            },
            toolbar: {
                cls: 'pq-toolbar-export',
                items: [{
	                    type: 'button',
	                    label: "Export to CSV",
	                    icon: 'ui-icon-document',
	                    listeners: [{
	                        "click": function (evt) {
	                            $("#grid_jsonp").pqGrid("exportCsv", { url: "http://localhost:8080/Baseapp/rest/export" });
	                        }
	                    }]
	            	},
                	{
                        type: 'button',
                        label: "Export to Excel",
                        icon: 'ui-icon-document',
                        listeners: [{
                            "click": function (evt) {
                                $("#grid_jsonp").pqGrid("exportExcel", { url: "http://localhost:8080/Baseapp/rest/export", sheetName: "pqGrid sheet" });
                            }
                        }]
                	}]
            }
        });
        
        $grid.pqGrid("option", $.paramquery.pqGrid.regional['nl']);
        $grid.find(".pq-pager").pqPager("option", $.paramquery.pqPager.regional['nl']);
    });
	</script>
	
	<div id="grid_jsonp"></div>
</html:form>