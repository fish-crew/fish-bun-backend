/**
 작성자		: 전영현
 설명			: dataTable 생성 함수
 @param targetId        : String Grid div 아이디
 @param columns    : 컬럼명 정보
 @param columnOrders    : 초기 order 설정
 @param ajaxInfo    : ajax로 받은 데이터
 */
/*
   fnDrawCallback  - 매 draw 이벤트시, 생성된 DOM의 모든 것을 동적으로 변경가능
   fnFooterCallback  -  fnHeaderCallback() 과 동일하나 매 draw이벤트시 footer를 동적으로 변경가능
   fnHeaderCallback  -  매 draw이벤트시 header 줄을 동적으로 변경가능
   fnInitComplete  -  테이블초기화 완료시, DataTables는 일반적으로 순서대로 초기화되고, 이 함수가 필요없음
                      그러나 외부언어정보의 async XHR 콜 완료시 까지 true를 잡지 못한다.
   fnRowCallback  - 각 테이블의 draw에 의해서 화면에 그려진 후에, 생성된 각 row에 대해 다음처리를 가능하게 한다.
                    row의 클래스명등을 설정하는데 사용
   fnServerData  -  서버에서 데이터를 받을 때 기본함수를 override 할 수 있게 한다.
                    fnServerData는 Ajax Sourced Data 또는 Server-side Processing 을 사용 가능하다.
*/


let initOrder = true;	// 초기 ordering 설정을 따라 갈건지 여부  		
let resetOrder = [];	// 다시 세팅된 order

//  tableOption 설정 값
let tableOption = {}
// datatables 변수
let table;
// localStorage key 값
let tableKey = "DataTable_" + location.pathname;
// 검색 설정 변수 저장 값
const searchOpt = {}

// Table reset api
DataTable.Api.register('reset()', function () {
    return this.iterator('table', function (s) {
        s.aaSorting.length = 0;
        s.aiDisplay.sort(function (a, b) {
            return a - b;
        });
        s.aiDisplayMaster.sort(function (a, b) {
            return a - b;
        });
        let colIndex = s.aoColumns.findIndex(({order}) => order === true)
        if (colIndex === -1) {
            colIndex = 1
        }
        s.aaSorting.push([colIndex, 'desc'])
        s._iDisplayLength = s.aLengthMenu[0].value
        $('.dt-length [name="listGrid_length"] option:first').prop("selected", true)

        localStorage.removeItem(tableKey)
    });
});

function initSearchField() {
    $('.srch_daterangepicker').each((idx, elem) => {
        $(elem).dateRangePicker({
            inline: true,
            container: $(elem).closest('dd').find('.daterange_bx'),
            alwaysOpen: true,
            language: 'ko',
            separator: ' ~ ',
            extraClass: 'date-range-9bit',
            monthSelect: true,
            yearSelect: true
        });
    })

    $(document).mouseup(function (e) {
        const axMask = '.ax-mask, .ax5-ui-dialog'
        const toplayer = '.sch_box2 .sch > dl dd'
        const popup = $(".pop_slide ").filter(":visible").length ? '.wrap' : ''
        const select2 = '.select2-container'

        const whitelist = [axMask, toplayer, popup, select2]

        const $toplayer = $(toplayer).filter(':visible')

        const ui = $toplayer.closest('dl')

        if (whitelist.filter((selector) => $(e.target).closest(selector).length !== 0).length === 0) {
            $toplayer.hide();
            ui.removeClass('_open');
        }
    });

    $('.sch_box2 .sch > dl').each(function () {
        var ui = $(this);
        var lobt = ui.find('>dt>button');
        var toplayer = ui.find("dd");
        var valuetxt = ui.find('dt .value');
        const blackListName = ['year', 'month']
        const opt = toplayer.find('input,select').map((idx, elem) => elem.name).toArray().reduce((acc, cur) => {
            if (cur && !blackListName.find(v => v === cur) && !acc.find((v) => v === cur)) {
                acc.push(cur)
            }

            return acc
        }, [])
        var reset = toplayer.find('.bt_reset');
        var save = toplayer.find('.bt_save');
        var inputText = toplayer.find("input[type=text]")
        //검색 레이어열기닫기
        toplayer.hide();
        ui.removeClass('_open');

        inputText.on("keyup", function (e) {
            if (e.keyCode === 13) {
                save.click()
            }
        })

        lobt.on("click", function (_e) {
            ui.toggleClass('_open');
            toplayer.toggle();
            _e.stopPropagation();
        });

        toplayer.click(function (_e) {
            _e.stopPropagation();
        });

        save.on("click", function (_e) {
            const currentVal = [];
            const searchVal = {}
            const validFn = window?.[$(this).data('function')]

            opt.forEach(function (name) {
                const target = $('[name=' + name + ']')
                const type = target[0].type
                let val = ''

                switch (type) {
                    case "text":
                        val = target.val()
                        if (target.data('dateRangePicker')) {
                            const [dateFrom, dateTo] = target.val().split(' ~ ')
                            searchVal[name] = target.val()
                            searchVal[name + 'From'] = dateFrom
                            searchVal[name + 'To'] = dateTo
                        } else {
                            searchVal[name] = target.val()
                        }
                        break;
                    case 'select-multiple':
                    case "select-one":
                        searchVal[name] = target.val()
                        if (searchVal[name]) {
                            const selected = target.find('option:selected')
                            if (selected.length === 1) {
                                val = selected.text()
                            } else if (selected.length > 1) {
                                val = selected.first().text() + ', 외 ' + (selected.length - 1) + '개'
                            }
                        }
                        break;
                    case "checkbox":
                        searchVal[name] = []
                        val = []
                        target.filter(':checked').each((i, elem) => {
                            if (elem.value) {
                                searchVal[name].push(elem.value)
                                val.push($(elem).closest('label').find('span').text())
                            }
                        })
                        break;
                    case "radio":
                        searchVal[name] = target.filter(':checked').val()
                        if (searchVal[name]) {
                            val = target.filter(':checked').closest('label').find('span').text()
                        }
                        break;
                    case "hidden":
                        searchVal[name] = target.val();
                        break;
                    default:
                        val = target.val()
                        searchVal[name] = target.val()
                        break;
                }

                if (Array.isArray(val)) {
                    currentVal.push(...val)
                } else {
                    if (val) {
                        currentVal.push(val)
                    }
                }

                return true
            })

            // 값 검증
            if (validFn) {
                if (!validFn(searchVal)) {
                    return false
                }
            }

            if (currentVal.length > 0) {
                const html = currentVal.map((v) => '<i>' + v + '</i>').join('');
                valuetxt.html(html);
                ui.removeClass('_open').addClass('active');
            } else {
                valuetxt.html('');
                ui.removeClass('_open').removeClass('active');
            }

            // 저장
            Object.keys(searchVal).forEach((name) => {
                searchOpt[name] = searchVal[name]
            })

            toplayer.hide();
            table && table.draw()
        });

        reset.on("click", function (_e) {
            valuetxt.text('');
            ui.removeClass('active');

            opt.forEach(function (name) {
                const target = $('[name=' + name + ']')
                const type = target[0].type

                target.removeClass('-hasvalue')
                switch (type) {
                    case 'select-multiple':
                    case 'select-one':
                        $(target).val('').change()
                        break;
                    case 'text':
                        const drp = target.data('dateRangePicker')
                        if (drp) {
                            drp.clear()
                            drp.resetMonthsView();
                            searchOpt[name + 'From'] = null
                            searchOpt[name + 'To'] = null
                            break;
                        }
                        $(target).val('');
                        break;
                    case 'textarea':
                        $(target).val('');
                        break;
                    case 'checkbox':
                        target.prop("checked", false)
                        break;
                    case 'radio':
                        target.prop("checked", false).first().prop("checked", true)
                        break;
                    case 'file':
                        $(target).val('');
                        break;
                    default :
                        $(target).val('');
                        break;
                }

                searchOpt[name] = null;
            });

            table && table.draw()
        });
    });
}

function initDataTable(targetId, pOption) {
    initSearchField()
    tableOption = pOption
    let {columns, columnOrders, ajaxInfo, columnDefs, layout} = tableOption
    if (columnDefs == undefined) columnDefs = {};

    // ajaxInfo.data 초기화
    const orgDataFn = ajaxInfo.data
    ajaxInfo.data = (param = {}) => {
        Object.keys(searchOpt).forEach((key) => {
            if (searchOpt[key]) {
                param[key] = searchOpt[key]
            }
        })
        orgDataFn && orgDataFn(param)
    }

    // 초기화 버튼 이벤트
    $(".sch_box2 .tool_box > .bt_reset").on("click", () => {
        $('.sch_box2 .sch > dl').each(function () {
            const $ui = $(this)
            const $valuetxt = $ui.find("dt .value")
            $ui.removeClass("active")
            $valuetxt.html('')
        })
        srchReset()
        $('.srch_daterangepicker').each((idx, elem) => {
            const drp = $(elem).data('dateRangePicker')
            drp.clear()
            drp.resetMonthsView();
        })
        Object.keys(searchOpt).forEach(((key) => {
            searchOpt[key] = null;
        }))
        table.reset().draw()
    })

    // dom 생성
    if (layout) {
        Object.keys(layout).forEach((key) => {
            if (layout[key]) layout[key] = $(layout[key])
        })
    }

    if (initOrder === true) {
        if (columnOrders === "Y") {
            let colIndex = columns.findIndex(({order}) => order === true)
            if (colIndex === -1) {
                colIndex = 1
            }

            columnOrders = [colIndex, 'desc'];
        } else {
            columnOrders = [0, 'desc'];
        }
    } else {
        columnOrders = resetOrder;
    }

    $("#" + targetId).on("preXhr.dt", (e, settings, data) => {	// 가공해서 보내는 데이터
        if (data.order.length) {
            var sequence = data.order[0].column;	//순서
            var colNm = data.columns[sequence].data;
            data.orderDir = data.order[0].dir;	//ASC , DESC 인지
            data.order = colNm // // 컬럼명;
        } else { // 초기화
            data.order = columns[columnOrders[0]].data
            data.orderDir = 'desc'
        }

        resetOrder = [sequence, data.orderDir];
    })

    table = new DataTable("#" + targetId, {
        layout: {
            topStart: ["info", layout?.topStart],
            topEnd: ["pageLength", layout?.topEnd],
            bottomStart: null,
            bottomEnd: null,
            bottom: [{
                "paging": {
                    "numbers": true,
                    "boundaryNumbers": true,
                    "type": "full_numbers_no_ellipses",
                    "id": targetId
                },
            },
            ]
        },
        colReorder: true,
        //fixedHeader: true,
        bDestroy: true,
        autoWidth: false,	// 자동 열 너비 계산
        bPaginate: true, // 페이징을 할 것인가
        // pagingType: "full_numbers_no_ellipses", //페이징 타입 -simple,simple_numbers, full, full_numbers, first_last_numbers , full_numbers_no_ellipses
        pageLength: 10, // 한 페이지에 기본으로 보여줄 항목 수를 뜻한다.
        ordering: true, // 컬럼 클릭 시 오더링을 적용할 것인가
        rowReorder: true,	// 페이지 제정렬
        order: columnOrders,// 초기에 기본 컬럼 정렬 설정 - 0:인덱스 컬럼 , desc : 정렬 방법  // 초기 표시 시 정렬 안하고 자 할 경우++엔  order : []
        // info: true, // 페이지 상태에대한 정보를 표시할 것인가.
        filter: true, //검색창을 보여줄 것인가
        bLengthChange: true, //표시 건 수 블록 단위 변경 기능을 사용할 것인가
        // 표시 건수 기능 숨기기
        lengthChange: true,	//좌측 건수 셀렉트 박스
        lengthMenu: [{label: '10개', value: 10}, {label: '20개', value: 20}, {label: '30개', value: 30}, {
            label: '40개',
            value: 40
        }, {label: '50개', value: 50},], // 표시 건수를 10건 단위로 설정
        // 기본 표시 건수를 10건으로 설정
        //displayLength : param.length,
        //displayStart: this.pageIndex ? (Number(this.pageIndex) -1 ) *10 : 0,
        searching: false, // 검색 사용여부
        stateSave: true, //클라이언트 사이드 랜더링의 경우 새로 고침 시 현재 페이지 상태정보가 저장되지 않으나 해당 정보를 true로 변경시 새로고침시에도 현재 상태가 저장되어 유지됨
        stateSaveCallback: function (settings, data) { // table.draw() 할때마다 실행 localStorage에 현재 페이지 정보 저장
            const searchOption = {}
            const ajaxData = settings.oAjaxData
            const blacklistKey = ['columns', 'draw', 'length', 'order', 'orderDir', 'search', 'start']

            Object.keys(ajaxData).forEach(((key) => {
                if (!blacklistKey.find(bKey => bKey === key)) {
                    searchOption[key] = ajaxData[key]
                }
            }))

            data.searchOption = searchOption

            // localStorage
            localStorage.setItem(
                tableKey,
                JSON.stringify(data)
            );
        },
        stateLoadCallback: function (settings) { // 처음 데이터테이블을 선언할 때 실행 localStorage에 저장된 정보를 현재 페이지에 맞게 세팅
            // localStorage
            const data = JSON.parse(localStorage.getItem(tableKey));

            if (data) {
                // data = JSON.parse(decodeURI(atob(data)))
                const searchOption = data?.searchOption

                if (searchOption) {
                    const keys = Object.keys(searchOption)
                    keys?.forEach(async (key) => {
                        const value = searchOption[key]
                        searchOpt[key] = value
                        if (value) {
                            const $target = $('[name=' + key + ']')
                            const $ui = $target.closest('dl')
                            const $valuetxt = $ui.find("dt .value")
                            const type = $target[0]?.type

                            let html = ''
                            let text = ''
                            switch (type) {
                                case 'radio':
                                    $target.filter('[value=' + value + ']').prop("checked", true).change()
                                    text = $target.filter('[value=' + value + ']').closest('label').find('span').text();
                                    html = '<i>' + text + '</i>'
                                    break;
                                case 'checkbox':
                                    if (Array.isArray(value)) {
                                        if (value.length > 0) {
                                            html = value.map((v) => {
                                                $target.filter('[value=' + v + ']').prop("checked", true).change()
                                                v = $target.filter('[value=' + v + ']').closest('label').find('span').text();
                                                return '<i>' + v + '</i>'
                                            }).join('');
                                        }
                                    }
                                    break;
                                case 'select-multiple':
                                case 'select-one':
                                    $target.val(value).change()
                                    const selected = $target.find('option:selected')
                                    if (selected.length === 1) {
                                        html = '<i>' + selected.text() + '</i>'
                                    } else if (selected.length > 1) {
                                        html = '<i>' + selected.first().text() + '</i><i>' + '외 ' + (selected.length - 1) + '개' + '</i>'
                                    }
                                    break;
                                case 'hidden':
                                    $target.val(value).change()
                                    break;
                                case 'text':
                                    if ($target.data('dateRangePicker')) {
                                        const drp = $target.data('dateRangePicker')
                                        const [dateFrom, dateTo] = value.split(" ~ ")
                                        drp.setDateRange(dateFrom, dateTo)
                                    } else {
                                        $target.val(value).change()
                                    }
                                    html = '<i>' + $target.val() + '</i>'
                                    break;
                                case "range":
                                    if($target.filter(".hour-range,.minute-range").length === 0) {
                                        $target.val(value).change()
                                    }
                                    break;
                                default:
                                    $target.val(value).change()
                                    html = '<i>' + $target.val() + '</i>'
                                    break;
                            }

                            if (html) {
                                $ui.addClass("active")
                                $valuetxt.append(html)
                            }
                        }
                    })
                }

                return data;
            }

            return settings
        },
        scrollX: false, // x축 스크롤을 활성화 할지 여부
        // scrollY : false, // y축 스크롤의 크릭 지정
        bAutoWidth: false, //자동 컬럼 폭을 계산하여 반영한다.
        processing: true,//progress bar
        serverSide: true, //false 로 처리 할 경우 최초 한꺼번에 모든 데이터를 가져와 처리 한다. 데이터 건수가 많을경우 로드가 심해질수 있다. 천건 이하일 경우에만 false로 쓰자
        scrollCollapse: false, //y축 스크롤을 활성화 할지 여부 해당 값을 true로 할경우 페이징은 false로 처리
        ajax: ajaxInfo,
        columns: columns,
        columnDefs: columnDefs,
        select: {
            style: "multi",
            selector: "td:first-child input"
        },
        language: {
            emptyTable: "<div class='nodata'>조회된 데이터가 없습니다.</div>",
            "info": "<span class='total'>전체:<strong>_TOTAL_</strong></span> <span class='page'>페이지:<strong>_PAGE_</strong>/_PAGES_</span>",
            "infoEmpty": "<span class='total'>전체:<strong>0</strong></span> <span class='page'>페이지:<strong>0</strong>/0</span>",
            infoFiltered: "( _MAX_항목 표시 )",
            "lengthMenu": "_MENU_",
            //"search" : "검색어 ",
            zeroRecords: "<div class='nodata'>조회된 데이터가 없습니다.</div>",
            loadingRecords: "로딩중입니다",
            //processing : "데이터를 조회 중이니 잠시만 기다려 주세요.",
            paginate: {
                "next": "다음",
                "previous": "이전",
                "first": "처음",
                "last": "끝"
            },
            select: {
                rows: {
                    _: '선택: <strong>%d</strong>',
                    0: ''
                }
            }
        }, initComplete: function (settings, json) {//ajax 처리 완료
            $("[name=check_all]").prop("checked", false);

            $("[name=check_all]").click(function () {
                if ($(this).prop("checked")) {
                    table.rows().select();
                    $("[name=check_item]").prop("checked", true);
                } else {
                    table.rows().deselect();
                    $("[name=check_item]").prop("checked", false);
                }
            });
            initOrder = false;
        }, createdRow: function (row, data, dataTable) {

        }, fnHeaderCallback: function (thead, data, start, end, display) {

        }, fnDrawCallback: function (settings) {
            $("[name=check_all]").prop("checked", false);
        }

    });
}

// $.fn.DataTable.ext.pager.numbers_length = 10;	// 테이터테이블 페이지 10까지 보이게
//  table.column( '8:visible' ).order( 'desc' ).draw();

function resizeDataTables(targetId) {
    var target = $('#' + targetId).DataTable();
    target.columns.adjust();
}

/*function camelToUnderscore(key) {
    return key.replace( /([A-Z])/g, "_$1").toLowerCase();
}*/

function excelParam(targetId, params) {
    var table = $('#' + targetId).DataTable();
    var size = table.order()[0].length;	// 데이터 테이블 order data
    var orderDir = "";
    var order = "";

    if (size == undefined) {	// 맨 처음 데이터 테이블 그릴때
        order = table.context[0].aoColumns[table.order()[0]].data;
        orderDir = table.order()[1];
    } else {
        order = table.context[0].aoColumns[table.order()[0][0]].data;
        orderDir = table.order()[0][1];
    }
    params.orderDir = orderDir;
    params.order = order;

    params.pageNm = $("#pageNm").text().split('/')[0];
    return params;
}

// 데이터 테이블 datepicker
/*
$(function(){
	new DateTime(document.getElementsByClassName('dates_from'));
	new DateTime(document.getElementsByClassName('dates_to'));

});*/

