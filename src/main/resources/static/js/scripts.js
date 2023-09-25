
function search() {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("search-text-input");
	filter = input.value.toUpperCase();
	table = document.getElementById("myTable");
	tr = table.getElementsByTagName("tr");
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[1];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function make_search_str(el){
	if ($(el).value !== "") {
		document.getElementById('url_str').href =  '/food-items-search/' + el.value;
	}
}

$('.accordian-body').on('show.bs.collapse', function () {
    $(this).closest("table")
        .find(".collapse.in")
        .not(this)
        //.collapse('toggle')
})

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if ((charCode != 44 && charCode != 46) && charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function calculateWithPiece(id){
	var txtPiece = document.getElementById('txt_piece_' + id);
	var txtGram = document.getElementById('txt_gram_' + id);
	var txt_kcal = document.getElementById('txt_kcal_' + id);
	var txt_kj = document.getElementById('txt_kj_' + id);
	var txt_fat = document.getElementById('txt_fat_' + id);
	var txt_carbo = document.getElementById('txt_carbo_' + id);
	var txt_protein = document.getElementById('txt_protein_' + id);
	
	var kcalHidden = document.getElementById('kcal_hidden_' + id);
	var kjHidden = document.getElementById('kj_hidden_' + id);
	var fatHidden = document.getElementById('fat_hidden_' + id);
	var carboHidden = document.getElementById('carbo_hidden_' + id);
	var proteinHidden = document.getElementById('protein_hidden_' + id);
	
	var oldKcal = kcalHidden.value.toString().replace(',','.')
	var oldKj = kjHidden.value.toString().replace(',','.')
	var oldFat = fatHidden.value.toString().replace(',','.')
	var oldCarbo = carboHidden.value.toString().replace(',','.')
	var oldProtein = proteinHidden.value.toString().replace(',','.')
	
	txtGram.value = '';
	
	if(txtPiece.value == 0)txtPiece.value = 1;
	if(txtPiece.value.toString().replace(',','.') > 0){

		pieceVal1 = (txtPiece.value.toString().replace(',','.') * oldKcal).toFixed(2);
		txt_kcal.value = pieceVal1.toString().replace(',','.');
		
		pieceVal2 = (txtPiece.value.toString().replace(',','.') * oldKj).toFixed(2);
		txt_kj.value = pieceVal2.toString().replace(',','.');
		
		pieceVal3 = (txtPiece.value.toString().replace(',','.') * oldFat).toFixed(2);
		txt_fat.value = pieceVal3.toString().replace(',','.');
		
		pieceVal4 = (txtPiece.value.toString().replace(',','.') * oldCarbo).toFixed(2);
		txt_carbo.value = pieceVal4.toString().replace(',','.');
		
		pieceVal5 = (txtPiece.value.toString().replace(',','.') * oldProtein).toFixed(2);
		txt_protein.value = pieceVal5.toString().replace(',','.');
	}
}

function calculateWithGram(id){
	var txtPiece = document.getElementById('txt_piece_' + id);
	var txtGram = document.getElementById('txt_gram_' + id);
	var txt_kcal = document.getElementById('txt_kcal_' + id);
	var txt_kj = document.getElementById('txt_kj_' + id);
	var txt_fat = document.getElementById('txt_fat_' + id);
	var txt_carbo = document.getElementById('txt_carbo_' + id);
	var txt_protein = document.getElementById('txt_protein_' + id);
	
	var kcalHidden = document.getElementById('kcal_hidden_' + id);
	var kjHidden = document.getElementById('kj_hidden_' + id);
	var fatHidden = document.getElementById('fat_hidden_' + id);
	var carboHidden = document.getElementById('carbo_hidden_' + id);
	var proteinHidden = document.getElementById('protein_hidden_' + id);
	
	var oldKcal = kcalHidden.value.toString().replace(',','.')
	var oldKj = kjHidden.value.toString().replace(',','.')
	var oldFat = fatHidden.value.toString().replace(',','.')
	var oldCarbo = carboHidden.value.toString().replace(',','.')
	var oldProtein = proteinHidden.value.toString().replace(',','.')
	
	txtPiece.value = '';
	
	var dGram = txtGram.value.toString().replace(',','.');
	if(dGram > 0 || dGram == ''){
		if(dGram == '')dGram = 100;
		dGram = parseFloat(dGram) / 100;
		
		gramVal1 = (dGram * oldKcal).toFixed(2);
		txt_kcal.value = gramVal1.toString().replace(',','.');
		
		gramVal2 = (dGram * oldKj).toFixed(2);
		txt_kj.value = gramVal2.toString().replace(',','.');
		
		gramVal3 = (dGram * oldFat).toFixed(2);
		txt_fat.value = gramVal3.toString().replace(',','.');
		
		gramVal4 = (dGram * oldCarbo).toFixed(2);
		txt_carbo.value = gramVal4.toString().replace(',','.');
		
		gramVal5 = (dGram * oldProtein).toFixed(2);
		txt_protein.value = gramVal5.toString().replace(',','.');
	}
}

function add_food_item(id,name,kcal,kj,fat,carbo,protein){
	var tableBody = document.getElementById("myTable3-tbody");
	var row = document.createElement("tr");
	row.setAttribute('class','table-success');
	row.setAttribute('id','id-for-del');


	
	name = name.replace("'","\'");
	
	if(kcal == null)kcal = 0.0;
	if(kj == null)kj = 0.0;
	if(fat == null)fat = 0.0;
	if(carbo == null)carbo = 0.0;
	if(protein == null)protein = 0.0;
	
	var cId = document.createElement("td");
	cId.innerHTML = id;
	
	var cName = document.createElement("td");
	cName.innerHTML = "<input type='text' name='name' value=" + name + " class='txtDisabled'>";
	
	var cPiece = document.createElement("td");
	cPiece.innerHTML = "<input type='text' onclick='this.value=\"\"' oninput='calculateWithPiece(" + id + ");' onkeypress='return isNumber();' min='1' value='1' name='piece' id='txt_piece_"+id+"' class='txtSmall'>";
	cPiece.setAttribute('class','tble3-little-td');
	cPiece.setAttribute('onchange','calculateWithPiece(' + id + ');')	
	//   th:field="${calendar_item.add_date}"
	
	var cGram = document.createElement("td");
	cGram.innerHTML = "<input type='text' onclick='this.value=\"\"' oninput='calculateWithGram(" + id + ");' onkeypress='return isNumber();' min='0' name='gram' id='txt_gram_"+id+"' class='txtSmall'>";
	cGram.setAttribute('class','tble3-little-td');
//	cGram.setAttribute('onchange','calculateWithGram(' + id + ');')	
	
	var cKcal = document.createElement("td");
//	cKcal.innerHTML = kcal;
	cKcal.innerHTML = "<input type='text' value='" + kcal + "' th:field='${*kcal}' onkeypress='return isNumber();' min='0' name='kcal' id='txt_kcal_"+id+"' class='txtSmall'>";
	cKcal.setAttribute('class','tble3-little-td');
//	cKcal.setAttribute('th:field','${food_items.kcal}');
	//cKcal.setAttribute('id','kcal_'+ id);
	
	var cKj = document.createElement("td");
	cKj.innerHTML = "<input type='text' value='" + kj + "' th:field='${*kj}' onkeypress='return isNumber();' min='0' name='kj' id='txt_kj_"+id+"' class='txtSmall'>";
	cKj.setAttribute('class','tble3-little-td');	
	//cKj.setAttribute('id','kj_'+ id);

	var cFat = document.createElement("td");
	cFat.innerHTML = "<input type='text' value='" + fat + "' th:field='${*fat}' onkeypress='return isNumber();' min='0' name='fat' id='txt_fat_"+id+"' class='txtSmall'>";
	cFat.setAttribute('class','tble3-little-td');	
	//cFat.setAttribute('id','fat_'+ id);
		
	var cCarbo = document.createElement("td");
	cCarbo.innerHTML = "<input type='text' value='" + carbo + "' th:field='${*carbo}' onkeypress='return isNumber();' min='0' name='carbo' id='txt_carbo_"+id+"' class='txtSmall'>";
	cCarbo.setAttribute('class','tble3-little-td');	
	//cCarbo.setAttribute('id','carbo_'+ id);
	
	var cProtein = document.createElement("td");
	cProtein.innerHTML = "<input type='text' value='" + protein + "' th:field='${*protein}' onkeypress='return isNumber();' min='0' name='protein' id='txt_protein_"+id+"' class='txtSmall'>";
	cProtein.setAttribute('class','tble3-little-td');	
	//cProtein.setAttribute('id','protein_'+ id);
	
	var deleteElem = document.createElement("td");
	deleteElem.innerHTML = "<button onclick='document.getElementById(\'id-for-del\').remove();' class='btn btn-danger btn-sm very-small-red'>X</button>";
	
	var cIdHidden = document.createElement("input");
	cIdHidden.setAttribute('name','food_id');
	cIdHidden.setAttribute('type','hidden');
	cIdHidden.setAttribute('value',id);
		
	var cKcalHidden = document.createElement("input");
	cKcalHidden.setAttribute('id','kcal_hidden_' + id);
	cKcalHidden.setAttribute('type','hidden');
	cKcalHidden.setAttribute('value',kcal);
	
	var cKjHidden = document.createElement("input");
	cKjHidden.setAttribute('id','kj_hidden_' + id);
	cKjHidden.setAttribute('type','hidden');
	cKjHidden.setAttribute('value',kj);	
	
	var cFatlHidden = document.createElement("input");
	cFatlHidden.setAttribute('id','fat_hidden_' + id);
	cFatlHidden.setAttribute('type','hidden');
	cFatlHidden.setAttribute('value',fat);
	
	var cCarboHidden = document.createElement("input");
	cCarboHidden.setAttribute('id','carbo_hidden_' + id);
	cCarboHidden.setAttribute('type','hidden');
	cCarboHidden.setAttribute('value',carbo);
	
	var cProteinHidden = document.createElement("input");
	cProteinHidden.setAttribute('id','protein_hidden_' + id);
	cProteinHidden.setAttribute('type','hidden');
	cProteinHidden.setAttribute('value',protein);
	
	
	row.appendChild(cId);
	row.appendChild(cIdHidden);
	row.appendChild(cName);
	row.appendChild(cPiece);
	row.appendChild(cGram);
	row.appendChild(cKcal);
	row.appendChild(cKcalHidden	);
	row.appendChild(cKj);
	row.appendChild(cKjHidden);
	row.appendChild(cFat);
	row.appendChild(cFatlHidden);
	row.appendChild(cCarbo);
	row.appendChild(cCarboHidden);
	row.appendChild(cProtein);
	row.appendChild(deleteElem);
	row.appendChild(cProteinHidden);	
	
	
	tableBody.appendChild(row);
}