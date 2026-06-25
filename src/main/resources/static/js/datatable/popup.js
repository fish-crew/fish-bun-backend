/**
 * 작성일 : 2019년-10-24
 * 작성자 : 서윤철
 * 설명 : 팝업 기능
 */

var type = "";	// 모달 타입
var conductorList = [];		// 지휘자번호
var artistList = [];		// 아티스트 번호
var instructorList = [];	// 강사번호
var perfList = [];			// 공연번호
var videoList = [];			// 영상번호
var articleList = [];		// 기사번호
var sponsorList = [];		// 협찬사번호
var allMenuList = [];		// {menuNo, menuName} 저장
var memberMap = {};		// {menuNo, menuName} 저장
/* 팝업 함수 */
var popup =  {

	/* 팝업 열기 */
	open : function(popupType) {
		type = popupType;
		if(type == 'conductor') { allMenuList = conductorList.slice(0); }
		else if(type == 'video') { allMenuList = videoList.slice(0); }
		else if(type == 'article') {allMenuList = articleList.slice(0); }
		else if(type == 'instructor') {allMenuList = instructorList.slice(0); }
		else if(type == 'perf') {allMenuList = perfList.slice(0); }
		else if(type == 'artist') {allMenuList = artistList.slice(0);}
		else if(type == 'artistTeacher') {allMenuList = artistList.slice(0);}
		else if(type == 'sponsor') {allMenuList = sponsorList.slice(0);}

		//모달 창열기(메뉴조회)
		$(".modal#"+type+ " input[name=menu]").prop("checked",false);
		$(".modal#"+type+ " #chkAll").prop("checked",false);
		$(".modal#"+type+ " #searchParam").val("");
		this.searchList();
		$(".modal#"+type).addClass("active");
	},
	/* 팝업 닫기 */
	close :  function () {
		$(".modal#"+type).removeClass("active");
		allMenuList = [];
		type = "";
	},
	/* 팝업 취소 */
	cancel : function() {
		$(".modal#"+type).removeClass("active");
		allMenuList = [];
		var size = $("#"+type+" #pop_form [name=menu_no]").length;
		for(var i=0; i<size; i++){
			var menu = new Object();
			menu.menuNo = $("#"+type+" pop_form [name=menu_no]").eq(i).val();
			menu.menuName = $("#"+type+" #pop_form [name=menu_no]").eq(i).closest("li").find("span").text();
			allMenuList.push(menu);
		}
		type = "";
	},
	/* 선택 버튼 */
	select : function() {
		$("#"+type+"_menu li").remove();
		var html = "";
		console.log('allMenuList :', allMenuList);
		for(var i = 0; i<allMenuList.length; i++){
			if(type == 'sponsor'){
				html += ' <li aria-artistname="'+allMenuList[i].menuName+'">';
				html += ' <div class="pic">';
				var filePath = "";
				if(allMenuList[i].fileNo != undefined){
					filePath = "/file/fileDownLoad?fileNo="+allMenuList[i].fileNo;
				} else{
					filePath = "/static/webdoc/img/logo-goobit.svg";
				}
				html += ' 		<img src="'+filePath+'" alt="배너로고" >';
				html += ' </div>';
				html += '<div class="artistname">'+allMenuList[i].menuName+'</div>';
				html += '	<button type="button" class="del" onclick="popup.onRemove(this,'+allMenuList[i].menuNo+', \''+type+'\');">삭제</button>';
				html += '</li>';
			}else{
				html += "<li id='popup" + (i+2) +"' name='popup" + (i+2) + "'>";
				html += "	<span>" + allMenuList[i].menuName + "</span>";
				html += "	<button type='button' class='del' onclick='popup.onRemove(this,"+allMenuList[i].menuNo+", \""+type+"\");'>삭제</button>";
				html += "</li>";
			}
		}
		$("#"+type+"_menu").append(html);
		if(type == 'sponsor'){
			$("#"+type+"_menu li").each(function(){
				var $this =  $(this);
				if($this.find('img').attr('src').indexOf('logo-goobit') == -1 ){
					$this.closest('li').addClass('nopic');
				}
			});
		}

		if(type == 'conductor') { conductorList = allMenuList.slice(0) }
		else if(type == 'video') { videoList = allMenuList.slice(0) }
		else if(type == 'article') {articleList = allMenuList.slice(0) }
		else if(type == 'instructor') {instructorList = allMenuList.slice(0) }
		else if(type == 'perf') {perfList = allMenuList.slice(0) }
		else if(type == 'artist') {artistList = allMenuList.slice(0);}
		else if(type == 'artistTeacher') {artistList = allMenuList.slice(0);}
		else if(type == 'sponsor') {sponsorList = allMenuList.slice(0);}

		allMenuList = [];

		$(".modal#"+type).removeClass("active");
	},
	/* 검색 */
	searchList : function() {
		var that = this;
		var param = new Object();
		param.popupType = type;
		param.langCd = $("#"+type+" select[name=langCd]").val();
		param.musicianCate = $("#"+type+" [name=musicianCate]").val();
		param.searchKeyword = $("#"+type+" #searchParam").val();

		$.ajax({
			type : 'POST',
			url : '/popup/selectPopList',
			data : param,
			dataType : 'JSON',
			error: function(xhr, status, error){
				alert('메뉴 검색 실패!');
			},
			success : function(data){
				$("#"+type+" #chkAll").prop("checked",false);
				// 내용 삭제
				$("#"+type+" [data-table=menu] > tbody").empty();

				var list = data.list;
				var articleList = data.articleList;
				var videoList = data.videoList;
				var sponsorList = data.sponsorList;

				// 테이블 그리기
				if(type == "conductor" || type == "artist" || type == "artistTeacher"){
					popup.drawArtist(list);
				}else if(type == "video") {
					popup.drawVideo(videoList);
				}else if(type == "article") {
					popup.drawArticle(articleList);
				}else if(type == "instructor") {
					popup.drawInstructor(list);
				}else if(type == "perf") {
					popup.drawPerf(list);
				}else if(type == "member"){
					popup.drawMember(list);
				}else if(type == "sponsor"){
					popup.drawSponsor(sponsorList);
				}

				that.isCheckAll();
			}
	    });
	},
	/* 언어코드 변경 이벤트  */
	changelangCd: function() {
		this.searchList();
	},

	changeCategory: function() {
		this.searchList();
	},
	/* 지휘자 리스트 그리기  */
	drawArtist: function(list) {
		var html = "";
		console.log(list);
		if(list.length == 0) {
			html = '<tr><td colspan="3">검색된 아티스트가 없습니다.</td></tr>';
		}else {
			$.each(list , function(index, item){
				html += '<tr onclick="popup.selectRow(this)">';
				$("#"+type+" #chkAll").css("display","inline-block");
				var chk = false;

				for(var i = 0; i < allMenuList.length; i++){
					if(allMenuList[i].menuNo == item.musicianNo)
						chk = true;
				}
				if(chk)
					html += '	<th class="ac"><input type="checkbox" name="menu" value="' + item.musicianNo + '" checked></th>';
				else
					html += '	<th class="ac"><input type="checkbox" name="menu" value="' + item.musicianNo + '"></th>';
				html += '	<td class="al"><span class="name">' + item.nmKr + '</span></td>';
				html += '	<td class="al">' + item.musCateNm + '</td>';
				html += '</tr>';
			});
		}

		$("#"+type+ " table > tbody").html(html);
	},
	/* 영상 리스트 그리기  */
	drawVideo : function(list) {
		var html = "";
		console.log(list);
		if(list.length == 0) {
			html = '<tr><td colspan="3">검색된 영상이 없습니다.</td></tr>';
		}else {
			$.each(list , function(index, item){
				html += '<tr onclick="popup.selectRow(this)">';
				$("#"+type+" #chkAll").css("display","inline-block");
				var chk = false;

				for(var i = 0; i < allMenuList.length; i++){
					if(allMenuList[i].menuNo == item.postNo)
						chk = true;
				}
				if(chk)
					html += '	<th class="ac"><input type="checkbox" name="menu" value="' + item.postNo + '" checked></th>';
				else
					html += '	<th class="ac"><input type="checkbox" name="menu" value="' + item.postNo + '"></th>';
				html += '	<td class="al"><span class="name">' + item.title + '</span></td>';
				html += '	<td class="al">' + item.regDate + '</td>';
				html += '</tr>';
			});
		}

		$("#"+type+ " table > tbody").html(html);
		$('[name=menu]').click(function(e){
			$(this).prop('checked', !$(this).is(':checked'));
		});
	},
	/* 기사 리스트 그리기  */
	drawArticle : function(list) {
		var html = "";
		console.log(list);
		if(list.length == 0) {
			html = '<tr><td colspan="3">검색된 기사 없습니다.</td></tr>';
		}else {
			$.each(list , function(index, item){
				html += '<tr onclick="popup.selectRow(this)"onclick="popup.selectRow()">';
				$("#"+type+" #chkAll").css("display","inline-block");
				var chk = false;

				for(var i = 0; i < allMenuList.length; i++){
					if(allMenuList[i].menuNo == item.postNo)
						chk = true;
				}
				if(chk)
					html += '	<th class="ac"><input type="checkbox" name="menu" value="' + item.postNo + '" checked></th>';
				else
					html += '	<th class="ac"><input type="checkbox" name="menu" value="' + item.postNo + '"></th>';
				html += '	<td class="al"><span class="name">' + item.title + '</span></td>';
				html += '	<td class="al">' + item.regDate + '</td>';
				html += '</tr>';
			});
		}

		$("#"+type+ " table > tbody").html(html);
		$('[name=menu]').click(function(e){
			$(this).prop('checked', !$(this).is(':checked'));
		});
	},
	/* 강사 리스트 그리기  */
	drawInstructor: function(list) {
		var html = "";
		console.log(list);
		if(list.length == 0) {
			html = '<tr><td colspan="3">검색된 강사가 없습니다.</td></tr>';
		}else {
			$.each(list , function(index, item){
				html += '<tr onclick="popup.selectRow(this)">';
				$("#"+type+" #chkAll").css("display","inline-block");
				var chk = false;

				for(var i = 0; i < allMenuList.length; i++){
					if(allMenuList[i].menuNo == item.insNo)
						chk = true;
				}
				if(chk)
					html += '	<th class="ac"><input type="checkbox" name="menu" value="' + item.insNo + '" checked></th>';
				else
					html += '	<th class="ac"><input type="checkbox" name="menu" value="' + item.insNo + '"></th>';
				html += '	<td class="al"><span class="name">' + item.insNameKr + '</span></td>';
				html += '	<td class="al">' + item.regDate + '</td>';
				html += '</tr>';
			});
		}

		$("#"+type+ " table > tbody").html(html);
		$('[name=menu]').click(function(e){
			$(this).prop('checked', !$(this).is(':checked'));
		});
	},
	/* 공연 리스트 그리기  */
	drawPerf: function(list) {
		var html = "";
		console.log(list);
		if(list.length == 0) {
			html = '<tr><td colspan="3">검색된 공연이 없습니다.</td></tr>';
		}else {
			$.each(list , function(index, item){
				html += '<tr onclick="popup.selectRow(this)">';
				$("#"+type+" #chkAll").css("display","inline-block");
				var chk = false;

				for(var i = 0; i < allMenuList.length; i++){
					if(allMenuList[i].menuNo == item.perfNo)
						chk = true;
				}
				if(chk)
					html += '	<th class="ac"><input type="checkbox" name="menu" value="' + item.perfNo + '" checked></th>';
				else
					html += '	<th class="ac"><input type="checkbox" name="menu" value="' + item.perfNo + '"></th>';
				html += '	<td class="al"><span class="name">' + item.perfName + '</span></td>';
				html += '	<td class="al">' + item.perfCate1Nm + '</td>';
				html += '</tr>';
			});
		}

		$("#"+type+ " table > tbody").html(html);
		$('[name=menu]').click(function(e){
			$(this).prop('checked', !$(this).is(':checked'));
		});
	},
	/* 회원 리스트 그리기  */
	drawMember: function(list) {
		var html = "";
		if(list == null || list.length == 0) {
			html = '<tr><td colspan="6" style="text-align:center">검색된 회원이 없습니다.</td></tr>';
		}else {
			$.each(list , function(index, item){

				memberMap[item.memberNo] = item;

				var name = item.langCd == 'en'?item.nameEng:item.nameKor;
				var email = "";
				if(item.email1 != null){
					email = item.email1 + '@' + item.email2;
				}
				var mobile = "";
				if(item.mobile1 != null){
					mobile = item.mobile1 + '-' + item.mobile2 + '-' + item.mobile3;
				}
				var birthday = "";
				if(item.birthday1 != null){
					birthday = item.birthday1 + '-' + item.birthday2 + '-' + item.birthday3;
				}

				html += '<tr  id="radio" onclick="popupSelectRadio(this)">';
				
				//기존 체크된 회원은 radio disabled 처리 
		        var selected = allMenuList.filter(function (menu) {
		          return menu.memberNo === item.memberNo;
		        });
		        var disabledAttr = selected.length > 0 ? 'checked' : '';
		
		        html += '<th class="ac"><label><input type="radio" name="menu" value="' + item.memberNo + '" onclick="$(this).parent().parent().click();" ' + disabledAttr + '></label> </th>';				
				html += '	<td class="al">' + item.memberId + '</td>';
				html += '	<td class="al"><span class="name">' + name + '</span></td>';
				html += '	<td class="al">' + email + '</td>';
				html += '	<td class="al">' + mobile + '</td>';
				html += '	<td class="al">' + birthday + '</td>';
				html += '</tr>';
			});
		}
		
		$("#"+type+ " table > tbody").html(html);
		$('[name=menu]').click(function(e){
			$(this).prop('checked', !$(this).is(':checked'));
		});
	},

	/* 협찬사 그리기 */
	drawSponsor : function(list) {
		var html = "";
		if(list.length == 0) {
			html = '<tr><td colspan="3">검색된 협찬사 없습니다.</td></tr>';
		}else {
			$.each(list , function(index, item){
				html += '<tr onchange="popup.selectRow(this)">';
//				html += '<tr>';
				$("#"+type+" #chkAll").css("display","inline-block");
				var chk = false;

				for(var i = 0; i < allMenuList.length; i++){
					if(allMenuList[i].menuNo == item.sponsorNo)
						chk = true;
				}
				if(chk)
					html += '	<td class="ac"><input type="checkbox" name="menu"  value="' + item.sponsorNo + '" data-fileNo="' + item.fileNo + '" checked><span class="blind">선택</span></th>';
				else
					html += '	<td class="ac"><input type="checkbox" name="menu"  value="' + item.sponsorNo + '"  data-fileNo="' + item.fileNo + '"><span class="blind">선택</span></th>';
				html += '	<td class="al"><span class="name">' + item.sponsorName + '</span></td>';
				html += '	<td class="al">' + item.regDate + '</td>';
				html += '</tr>';
			});
		}

		$("#"+type+ " table > tbody").html(html);
	   
	},
	/* tr 클릭 시 체크박스 선택 이벤트 */
	selectRow : function(obj) {
		var checkbox =  $(obj).find('input[name="menu"]');
		var menuNo = checkbox.val();
//console.log('is checked:', checkbox.is(':checked'));
//		checkbox.prop('checked', !checkbox.is(':checked'));

		var flag = checkbox.is(':checked');

		if(type == 'conductor' || type == 'instructor' || type == 'artistTeacher'){
			var erMsg = "";
			// 지휘자는 1명만 선택 가능.
			if(type == 'conductor') {
				erMsg = "지휘자는 1명만 선택할 수 있습니다.";
				/*if(allMenuList.length == 2){
					alert("지휘자는 1명만 선택할 수 있습니다.")
					checkbox.prop('checked', !checkbox.is(':checked'));
				}*/
			}
			//강사는 1명만 선택 가능.
			else if(type == 'instructor') {
				erMsg = "강사는 1명만 선택할 수 있습니다.";
				/*if(allMenuList.length == 2){
					alert("강사는 1명만 선택할 수 있습니다.");
					checkbox.prop('checked', !checkbox.is(':checked'));
				}*/
			}
			//아티스트강사는 1명만 선택 가능.
			else if(type == 'artistTeacher') {
				erMsg = "아티스트 강사는 1명만 선택할 수 있습니다.";
				/*if(allMenuList.length > 0){
					alert("아티스트 강사는 1명만 선택할 수 있습니다.");
					checkbox.prop('checked', !checkbox.is(':checked'));
				}*/
			}
			if(allMenuList.length == 0 && flag){//체크
				var menu = new Object();
				menu.menuNo = menuNo;
				menu.menuName = $(obj).find("span").text();
				allMenuList.push(menu);
			}else if(allMenuList.length != 0 && flag && erMsg != ''){
				alert(erMsg);
				checkbox.prop('checked', !checkbox.is(':checked'));
			}else{//체크해제
				allMenuList = allMenuList.filter(function(item) { return item.menuNo != menuNo});
			}
		}else{
			var flag = checkbox.is(':checked');
			if(flag){//체크
				if(type == "sponsor") {
					var menu = new Object();
					menu.menuNo = menuNo;
					menu.menuName = $(obj).find("span").text();
					menu.fileNo = checkbox.attr("data-fileNo");
					allMenuList.push(menu);
					console.log('type sponsor:', allMenuList);
				}else{
					var menu = new Object();
					menu.menuNo = menuNo;
					menu.menuName = $(obj).find("span").text();
					allMenuList.push(menu);
					console.log('type not:', allMenuList);
				}
			}else{//체크해제
				allMenuList = allMenuList.filter(function(item) { return item.menuNo != menuNo});
			}
			console.log('total :', allMenuList);
		}

		this.isCheckAll();
	},

	selectAll : function(obj) {
		allMenuList = [];
		var isChecked = $(obj).is(':checked')

		if(isChecked) {
			$("#"+type+ " table > tbody input[name='menu']").each(function() {
				$(this).prop('checked', true);
				var menu = new Object();
				menu.menuNo = $(this).val();
				menu.menuName = $(this).closest('tr').find("span").text();
				allMenuList.push(menu);
			})
		}else {
			$("#"+type+ " table > tbody input[name='menu']").each(function() {
				$(this).prop('checked', false);
			})
		}
	},

	isCheckAll : function() {
		var isAllChecked = true;
		$("#"+type+ " table > tbody input[name='menu']").each(function() {
			if(!$(this).is(":checked")) {
				isAllChecked = false;
				return false;
			}
		});

		$('input[name="checkAll"]').prop('checked', isAllChecked);
	},

	/* 삭제  */
	onRemove : function (obj, menuNo, type) {
		$(obj).parent().remove();

		// 테이블 그리기
		if(type == "conductor"){
			conductorList = conductorList.filter(function(item) { return item.menuNo != menuNo});
		}else if(type == "video") {
			videoList = videoList.filter(function(item) { return item.menuNo != menuNo});
		}else if(type == "article") {
			articleList = articleList.filter(function(item) { return item.menuNo != menuNo});
		}else if(type == "instructor") {
			instructorList = instructorList.filter(function(item) { return item.menuNo != menuNo});
		}else if(type == "perf") {
			perfList = perfList.filter(function(item) { return item.menuNo != menuNo});
		}else if(type == "artist") {
			artistList = artistList.filter(function(item) { return item.menuNo != menuNo});
		}else if(type == "artistTeacher") {
			artistList = artistList.filter(function(item) { return item.menuNo != menuNo});
		}else if(type == "sponsor") {
			sponsorList = sponsorList.filter(function(item) { return item.menuNo != menuNo});
		}
	}
}



