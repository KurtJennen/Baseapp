function doActionIdPqGrid(sMethod, sId) {
    doPosition(sMethod);
    window.document.forms[0].action += '&id=' + sId;
    window.document.forms[0].submit();
}

function cellDblClickPq(ui, sSelectedIdsName, sMethod) {
	var unSelectedRows = "input:hidden";
	$(unSelectedRows).remove();

	createHiddenInputTag(ui.rowData.id, sSelectedIdsName)

	doActionIdPqGrid(sMethod, ui.rowData.id);
}

function checkPq(ui, sId, sSelectedIdsName) {
	if (ui.source == "header") {
		var data = $("#" + sId).pqGrid("option", "dataModel.data");
		for (var i = 0, len = data.length; i < len; i++) {
			createHiddenInputTag(data[i].id, sSelectedIdsName);
		}
	}
    if (ui.dataIndx == 'selectedRow' && ui.rowData != null ) {
    	createHiddenInputTag(ui.rowData.id, sSelectedIdsName);
    }
}

function unCheckPq(ui, sSelectedIdsName) {
	if (ui.source == "header") {
		var unSelectedRows = "input:hidden[name=" + sSelectedIdsName + "]";
		$(unSelectedRows).remove();
	}
    if (ui.dataIndx == 'selectedRow' && ui.rowData != null ) {
        var unSelectedRow = "input:hidden[name=" + sSelectedIdsName + "][value=" + ui.rowData.id + "]";
        $(unSelectedRow).remove();
    }
}

function createHiddenInputTag(id, sSelectedIdsName) {
	$("<input>").attr("type", "hidden").attr("value", id).prop("name", sSelectedIdsName).appendTo("form");
}

function formatCurrencyPq(ui, sLocale, sCurrency) {
    var myObj = {
	  style: "currency",
	  currency: sCurrency
	}

    return ((ui.cellData < 0) ? "-" : "") + ui.cellData.toLocaleString(sLocale, myObj);
}

function formatPq(ui, sLocale) {
	var myObj = {
	  minimumFractionDigits: 2
	}

    return ((ui.cellData < 0) ? "-" : "") + ui.cellData.toLocaleString(sLocale, myObj);
}

function totals(ui, sId, sSummary) {
	if (ui.pageData != undefined) {
		var totaal = 0;
		var totalData = new Object();
		
		var cols = $("#" + sId).pqGrid("option", "colModel");
		
		for (var i = 0, len = cols.length; i < len; i++) {
			if(cols[i].dataType != undefined && cols[i].dataType == "float") {
				totalData[cols[i].dataIndx] = "0";
			} else if {
				totalData[cols[i].dataIndx] = "";
			}
		}
		
//		var data = $("#" + sId).pqGrid("option", "dataModel.data");
//		
//		for (var i = 0, len = data.length; i < len; i++) {
//			totaal += data[i].bedrag;
//		}
		
		//var totalData = { name: "", userName: "", email: "", dateExpirationAsString: "", roleName: "", bedrag: totaal, pq_rowcls: 'green' };
		
		var data = [totalData];
		var obj = { data: data, $cont: sSummary };
		$("#" + sId).pqGrid("createTable", obj);
	}
}
