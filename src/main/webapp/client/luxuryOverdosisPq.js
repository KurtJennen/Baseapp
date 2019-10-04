function doActionIdPqGrid(sMethod, sId) {
    doPosition(sMethod);
    window.document.forms[0].action += '&id=' + sId;
    window.document.forms[0].submit();
}

function cellDblClickPq(ui, sSelectedIdsName) {
	var unSelectedRows = "input:hidden";
	$(unSelectedRows).remove();

	createHiddenInputTag(ui.rowData.id, sSelectedIdsName)

	doActionIdPqGrid("read", ui.rowData.id);
}

function checkPq(ui, sSelectedIdsName) {
	if (ui.source == "header") {
		for (var i = 0, len = ui.data.length; i < len; i++) {
			createHiddenInputTag(ui.data[i].id, sSelectedIdsName);
		}
	}
    if (ui.dataIndx == 'selectedRow' && ui.rowData != null ) {
    	createHiddenInputTag(ui.rowData.id, sSelectedIdsName);
    }
}

function unCheckPq(ui) {
	if (ui.source == "header") {
		var unSelectedRows = "input:hidden";
		$(unSelectedRows).remove();
	}
    if (ui.dataIndx == 'selectedRow' && ui.rowData != null ) {
        var unSelectedRow = "input:hidden[value=" + ui.rowData.id + "]";
        $(unSelectedRow).remove();
    }
}

function createHiddenInputTag(id, sSelectedIdsName) {
	$("<input>").attr("type", "hidden").attr("value", id).prop("name", sSelectedIdsName).appendTo("form");
}
