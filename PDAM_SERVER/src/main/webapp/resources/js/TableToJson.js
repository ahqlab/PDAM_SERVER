var makeJsonFromTable = function(tableID, deviceIdx) {
	
	var tbl = $('#' + tableID);
	var tblhead = $(tbl).find('thead');
	var tblbody = $(tbl).find('tbody');
	var tblbodyCount = $(tbl).find('tbody>tr').length;
	var header = [];
	var JObjectArray = [];
	$.each($(tblhead).find('tr>th'), function(i, j) {
		var subject = "";
		switch ($(j).text().trim()) {
		case "파일타입":
			subject = "pileType";
			break;
		case "공법":
			subject = "method";
			break;
		case "위치":
			subject = "location";
			break;
		case "파일번호":
			subject = "pileNo";
			break;
		case "파일규격":
			subject = "pileStandard";
			break;
		case "설계심도":
			subject = "designDepth";
			break;
		}
		header.push(subject);
		
	})
	header.push("deviceIdx");
	
	
	$.each($(tblbody).find('tr'), function(key, value) {
		var jObject = {};
		for (var x = 0; x < header.length; x++) {
		
				if(x == header.length - 1){
					jObject[header[x]] = deviceIdx;
				}else{
					if($(this).find('td').eq(x).text() != ""){
						jObject[header[x]] = $(this).find('td').eq(x).text();
					}
					
				}
		}
		JObjectArray.push(jObject);

	});
	var jsonObject = {};
	jsonObject["count"] = tblbodyCount
	jsonObject["value"] = JObjectArray;
	return JObjectArray;
}