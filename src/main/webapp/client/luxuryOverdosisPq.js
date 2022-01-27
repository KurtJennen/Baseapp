function doActionPqGrid(sMethod) {
    doPosition(sMethod);
    window.document.forms[0].submit();
}

function cellDblClickPq(ui, sSelectedIdsName, sMethod) {
	var unSelectedRows = "input:hidden";
	$(unSelectedRows).remove();

	createHiddenInputTag(ui.rowData.id, sSelectedIdsName)

	doActionPqGrid(sMethod);
}

function loadPq(oData, iId, sSelectedIdsName) {
	for (var i = 0, len = oData.length; i < len; i++) {
		if (oData[i].id == iId) {
			oData[i].selectedRow = true;
			createHiddenInputTag(oData[i].id, sSelectedIdsName);
			break;
		}
	}
}

function checkPq(ui, sId, sSelectedIdsName) {
	if (ui.source == "header") {
		var data = $("#" + sId).pqGrid("option", "dataModel.data");
		for (var i = 0, len = data.length; i < len; i++) {
			var unSelectedRow = "input:hidden[name=" + sSelectedIdsName + "][value=" + data[i].id + "]";
			if($(unSelectedRow).length == 0) {
				createHiddenInputTag(data[i].id, sSelectedIdsName);
			}
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
		var totalData = new Object();
		
		var cols = $("#" + sId).pqGrid("option", "colModel");
		
		for (var i = 0, lenCols = cols.length; i < lenCols; i++) {
			if(cols[i].dataType != undefined && cols[i].dataType == "float" && cols[i].totalizable == true) {
				var total = 0;
				var data = $("#" + sId).pqGrid("option", "dataModel.data");
				
				for (var j = 0, lenData = data.length; j < lenData; j++) {
					var rowData = data[j];
					total += rowData[cols[i].dataIndx];
				}
				
				totalData[cols[i].dataIndx] = total;
			} else {
				totalData[cols[i].dataIndx] = "";
			}
		}
		
		var data = [totalData];
		var obj = { data: data, $cont: sSummary };
		$("#" + sId).pqGrid("createTable", obj);
	}
}
